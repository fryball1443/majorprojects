##########################################################################################################
# _                           ___            ______                  _        _    ________    _____     #
# \\            //   /\      ||  \\         //`````         /\      | \      / |  ||=======  //-----\\   #
#  \\    /\    //   //\\     ||__//        //   ____       //\\     ||\\    //||  ||_______ ||______     #
#   \\  //\\  //   //==\\    ||--\\        ||  | ----\    //==\\    || \\  // ||  ||=======  \\-----\\   #
#    \\//  \\//   //    \\   ||   \\       \\       //   //    \\   ||  \\//  ||  ||_______   _______||  #
#     \/    \/   //      \\  ||    \\       \\_____//   //      \\  ||   \/   ||  ||=======  \\-----//   #
#                                                                                                        #
##########################################################################################################
#by Nolen Jensen
#CISP 2410 -- Computer Organization and Assembly Language
#Professor Marvin Johnson Jr.
#for How to Play, scroll to .end

.macro push reg
subi sp, sp, 4
stw    \reg, 0(sp)
.endm

.macro pop reg
ldw    \reg, 0(sp)
addi sp, sp, 4
.endm

.equ IO_BASE,     0xff200000
.equ LED,               0x00
.equ SWITCH,            0x40
.equ BUTTON,            0x50
.equ SEG1,              0x20
.equ SEG2,              0x30
.equ UART,            0x1000
.equ TIME,           0x2000
.equ TIME_CTRL,     TIME+0x4
.equ TIME_START,    TIME+0x8
.equ TOS,         0x04000000
.equ NUMTODISARM,          6 #final variable.. the number of correct digits required
                             # to disarm bomb and win game

.global _start 
_start: 

# addressess
movia sp,  TOS
movia r23, IO_BASE

full_loop:
  #since the program uses every register, at the start it completely wipes all the registers to
  #   prevent any possible issues
  movi r2,  0
  movi r3,  0  #always value of switches
  movi r4,  0  #used in main program as comparative value 
  movi r5,  0
  movi r6,  0
  movi r7,  0
  movi r8,  0
  movi r9,  0
  movi r10, 0
  movi r11, 0
  movi r12, 0
  movi r13, 0
  movi r14, 0  #always value of button
  movi r15, 0   #always value of current UART message
  movi r16, 0  #used as i value for looping
  movi r17, 0   #used as j value for looping through words for number generator from memory
  movi r18, 0  #always variable for number of correct guesses to complete game
  movi r19, 0   #stores  boolean result of cmp statements
  movi r20, 0  #always current value of randomly generated number
  movi r21, 0
  movi r22, 0  #value of timer
  call random_storage
  call seg7_output

  movi r11, 2  #used for comparing button to verify that "1" button is pressed
  movi r4,  1  #used for comparing button to verify that "0" button is pressed

  
  call start_screen         #prints "Press 1 to Start"
  ifbutton1start: #if button 1 is pressed, starts the program
    ldwio r14, BUTTON(r23)        #loads value of button
    stwio r14, LED(r23)             #displays the binary value of button presses on LED 2
    bne r14, r11, ifbutton1start  #if 1 is not pressed, then restart loop
  call waittillnobutton #doesnt continue unless all buttons  are off
  call play_screen #if button 1 is pressed, prints "Shall we play a game?"
  ifbutton1play:  #if button 1 is pressed
    ldwio r14, BUTTON(r23)    #loads value of button
    stwio r14, LED(r23)             #displays the binary value of button presses on LED 2
    beq r14, r11, continue_to_game  #if 1 is pressed, continue
    beq r14, r4,  full_loop         #if 0 is pressed, go back to start screen
    beq r14, r0,  ifbutton1play     #if no buttons are pressed, stay on this screen and wait for user input
  continue_to_game: #continue....
    push r2
    movia r2,  0 # clears timer
    call start_timer      # start the timer
    call seg7_output
    pop r2
    call thermowar_screen
    call waittillnoinput #doesnt continue unless all buttons and switches are off
                         #also loops generating random numbers to be guessed
    movi r8,  0  #current number for seven seg output
    movi r9,  0  #total number for seven seg output
    movi r10, 0  #number of loops correctly guessed (c)
    movi r18, NUMTODISARM  #(s): used for comparing against correct answers (c). success if c == s

    movi r16, 1            #loop counter (i)
    movi r17, 0            #word counter (j)
    push r2
    movia r2,  4294967295 # value for 42 second timer..... highest amount of time that i can do with a word
    call start_timer      # start the timer
    pop r2
    read_seg:
      ldwio  r22, TIME(r23)       # read timer status
      cmpeqi r22, r22, 0b10       # is it running ?
      stwio  r22, LED(r23)        #displays on the first led if the timer is running
      beq    r22, r0, _read_seg   # loop until timer expired 
      push r6                     #stack
      ldwio r12, SWITCH(r23)      #loads value of switches for since loop started to verify
      beq r12, r3, skip_disp      #if the guess is the same as before, doesnt reset display
      ldwio r3, SWITCH(r23)       #loads value of switches (n)
      cmpltui r6, r3, 10          #if n < 10
      beq r6, r0, skip_disp       #if n !< 10, doesnt change the 7seg display
      add r8, r9, r3              #saves (n + previous  correct values) to r8 to be put into 7seg display
      mov r2, r8                  #saves (n) as r2 to output to 7 segment display
      call seg7_output            #displays ((current value) + (previous correct values to left)) on 7seg
      skip_disp:
      pop r6                      #unstack
      movi r11, 2                 #button value matches this if 1 button is pressed
      ldwio r14, BUTTON(r23)      #loads value of button
      bne r14, r11, skip_store    #if 1 is not pressed, then skip "confirming"/storing answer, and restart loop

      ldw r20, num_store(r17)     #load random number from memory
      if: #if value of switches is accurate, stores the value into 7seg display and moves all digits left
        bne r20, r3, skip_store   #if value is not accurate, keeps looping until value is accurate...
        addi r16, r16, 1          #i++
        addi r17, r17, 4          #j++
        addi r10, r10, 1          #c++
        beq r18, r10, _read_seg   #if c == 6, break loop
        add r9, r9, r3            #stores the value in switches to total string of numbers
        muli r9, r9, 10           #moves the value in total string to the left by one decimal point 
        mov r2, r9                #saves value to be put in the 7seg output
        call seg7_output          #displays value on the 7seg display
      skip_store:
      br read_seg
    _read_seg:

    ifpassed:
      bne  r18, r10, iffailed
      call success_screen
      stwio r11, LED(r23)            #if you win, second LED lights up
      br endgame
    iffailed:
      call failure_screen
      stwio r0, LED(r23)            #if you fail, LED's turn off
    endgame:
    call waittillnobutton

  gameover:
  call waittillnobutton
  ifbutton1restart:  #if button 1 is pressed
    ldwio r14, BUTTON(r23)    #loads value of button
    beq r14, r11, continue_to_game  #if 1 is pressed, continue
    beq r14, r4,  full_loop         #if 0 is pressed, go back to start screen
    beq r14, r0,  ifbutton1restart  #if no buttons are pressed, stay on this screen and wait for user input
_stop: br _stop

/*
###########################################
# # # # # # # random storage: # # # # # # #
 runs Marvin's random number generator
 generates 6 random numbers and stores them 
  into memory as the values that the player
  has to guess
###########################################
*/
  random_storage:
    push r20
    push r16
    push r17
    push r19
    movi r16, 1            #loop counter (i)
    movi r17, 0            #word counter (j)
    store_loop:
      cmpleui r19, r16, 6       #if (i<=6)
      beq r19, r0, _store_loop  #else end loop
      push ra
      call random               #generate random number
      pop ra
      ldw r20, rand_numb(r0)    #load random number into r20
      stwio r20, LED(r23)       #cool effect to visualize it randomly generating the code
      stw r20, num_store(r17)   #store random number into memory
      addi r16, r16, 1          #i++
      addi r17, r17, 4          #j++
    br store_loop              #break loop
    _store_loop:
    pop r19
    pop r17
    pop r16
    pop r20
    ret
########################################
 # # # # end random storage sub # # # #
########################################

/*
##########################################
# # # # # # # UART printers: # # # # # # #
 Prints various texts to UART terminal 
  using Marvin's UART subroutines.
 To see exactly what each says, go to end
##########################################
*/
  start_screen:
    push ra               #stack
    call clear_terminal   #clears terminal
    pop ra                #unstack
    movia r15, start_msg  #stores start message to be written to uart
    push ra               #stack
    call write_terminal   #writes message to UART
    pop ra                #unstack
    ret

  play_screen:
    push ra               #stack
    call clear_terminal   #clears terminal
    pop ra                #unstack
    movia r15, play_msg   #stores "shall we play a game?" to be outputted in uart
    push ra               #stack
    call write_terminal   #writes message to UART
    pop ra                #unstack
    ret

  thermowar_screen:
    push ra               #stack 
    call clear_terminal   #clears terminal
    pop ra                #unstack
    movia r15, thermowar_msg #stores message to show game start screen
    push ra               #stack
    call write_terminal   #writes message to UART
    pop ra                #unstack
    ret

  success_screen:
    push ra             #stack
    call clear_terminal #clears terminal
    pop ra              #unstack
    movia r15, success_msg  #stores message to show  success screen
    push ra             #stack
    call write_terminal   #writes message to UART
    pop ra              #unstack
    ret

  failure_screen:
    push ra             #stack
    call clear_terminal #clears terminal
    pop ra              #unstack
    movia r15, failure_msg   #stores message to show failure screen
    push ra             #stack
    call write_terminal   #writes message to UART
    pop ra              #unstack
    ret

  clear_terminal:
    push r2               #stack
    movi r2, 10           #number of new lines to be made
    clear_loop:         
      movia r15, new_line #store new line messages to be written to uart
      push ra             #stack
      call write_terminal   #writes message to UART #writes a bunch of blank "new lines to terminal"
      pop ra              #unstack
      subi r2, r2, 1      #go down to 0
      bne r2, r0, clear_loop  #break loop when at 0
    pop r2 #unstack
    ret
#######################################
 # # # # end UART printing sub # # # #
#######################################

/*
##########################################
# # # # # # Wait till no input # # # # # #
  loops until there is no input:
   all switches and buttons need to be off
##########################################
*/
  waittillnoinput:  #if all switches and buttons are released, game starts
    loop:
      ldwio r14, BUTTON(r23)  #loads value of button
      stwio r14, LED(r23)     #displays the binary value of button presses on LED 2
      ldwio r3, SWITCH(r23)   #loads value of switches (n)
      push ra
      call random_storage    #while waiting for user to clear everything, randomly generates numbers in a loop to be completely random
      pop ra
      bne r14, r0,  loop     #if no buttons are pressed, continue
      bne r3,  r0,  loop     #if no switches are on, continue
    ret
  waittillnobutton:  #if all switches and buttons are released, game starts
    buttonloop:
      ldwio r14, BUTTON(r23)  #loads value of button
      bne r14, r0,  buttonloop     #if no buttons are pressed, continue
    ret
########################################
 # # # # end wait till no input # # # #
########################################


/*
######################################################################################
#                             Marvin's subroutines                                   #       
######################################################################################
*/  
  
  /*
  ##############################################
  # # # # Marvin's random generator code # # # # 
  ##############################################
  */
    random:
    push   r16                 # stack
    push   r17                 #
    ldw    r16, rand_seed(r0)  #  / fetch seed
    addi   r16, r16, 1         # /
    movia  r17, 3141592621     #|  make new seed
    mul    r16, r16, r17       # \ 
    stw    r16, rand_seed(r0)  #  \ store new seed
    ldw    r17, rand_max(r0)   # generate number
    mulxuu r16, r16, r17       # by pulling the hi 32-bits 
    stw    r16, rand_numb(r0)  # random number stored in "rand"
    pop    r17                 # unstack 
    pop    r16                 # 
    ret
  ###########################################
  # # # Marvin's random generator end # # # 
  ###########################################

  /*
  ###############################################
  # # # # # # # Marvin's 7 seg code # # # # # # # 
  ###############################################
  */
    seg7_output:  # arg r2 number
    push r2
    push r3                 #\
    push r4                 # \
    push r5                 #  stack
    push r6                 # /
    push r7                 #/    

    stwio r0, SEG1(r23)     # clear 7seg hex0
    stwio r0, SEG2(r23)     # clear 7seg hex1

    bne   r2, r0, seg7_not_zero # if number 0 send now no math
    ldb   r4, seg7_numbers(r0)  # get 0's 7seg equivalent
    stwio r4, SEG1(r23)         # display
    br _seg7_loop               # skip most code below
    

    seg7_not_zero:
    movia r3, 1             # r3 divider
    movia r6, 0             # r6 counter
    seg7_max_digits:
      div   r4, r2, r3
      beq   r4, r0, _seg7_max_digits
      muli  r3, r3, 10
      addi  r6, r6, 1
      br seg7_max_digits
      _seg7_max_digits:
    
    seg7_loop:
      subi   r6, r6, 1
      movi   r4, 10
      div    r3, r3, r4       # new divider
      div    r4, r2, r3       # divide
      mov    r7, r4           # keep copy of raw number in r7    
      ldb    r5, seg7_numbers(r4)  # get new 7-seg number 
      muli   r4, r6, 8        # convert r6 to bytes
      sll    r5, r5, r4       # shift left
      cmpgti r4, r4, 24       # should we use the left most 7-seg address?
      bne    r4, r0, seg7_use_seg2
      ldwio  r4, SEG1(r23)    # get old 7-seg values 
      or     r4, r4, r5       # combine old and new values 
      stwio  r4, SEG1(r23)    # display
      br seg7_skip_seg2
      seg7_use_seg2: 
      ldwio  r4, SEG2(r23)    # get old 7-seg values 
      or     r4, r4, r5       # combine old and new values 
      stwio  r4, SEG2(r23)    # display
      seg7_skip_seg2:
      
      mul    r7, r7, r3       # get next number  
      sub    r2, r2, r7
      
      cmpeqi r4, r3, 1        # loop until 1s position
      beq    r4, r0, seg7_loop
      _seg7_loop:
    
      pop r7                  #\
      pop r6                  # \
      pop r5                  #  unstack     
      pop r4                  # /
      pop r3                  #/
      pop r2
      ret
  ############################################
  # # # # # # end Marvin's code  # # # # # #
  ############################################

  /*
  #############################################
  # # # # # Marvin's timer subroutine # # # # #
  #############################################
  */
    start_timer:    # arg: r2 timer start value
    push  r2                    # stack
    stwio r0, TIME(r23)         # reset timer 
    sthio r2, TIME_START(r23)   # low halfword of counter start value
    srli  r2, r2, 16            # shift to hi-bits
    sthio r2, TIME_START+4(r23) # high halfword of counter start value  
    movi  r2, 0b0100            # mask to start timer
    sthio r2, TIME_CTRL(r23)    # start the timer 
    pop   r2                    # unstack
    ret
  ##########################################
  # # # # # end timer subroutine # # # # #
  ##########################################

  /*
  #########################################
  # # # Marvin's UART terminal writer # # #
  #########################################
  */
    write_terminal:              # r15 address of string
    push r16                    # stack
    write_char:
      ldwio r16, UART+4(r23)    # read control register
      beq   r16, r0, write_char # does buffer have room ?
      ldb   r16, (r15)          # get string character
      stwio r16, LED(r23)       # displays LED binary value as its printing for 
                                      #if you dont have UART. I added this on
      beq   r16, r0, _write     # break if NULL terminator 
      stwio r16, UART(r23)      # else write_terminal to UART
      addi  r15, r15, 1           # index next character
      br write_char             # loop
    _write:
    pop r16                     # unstack
    ret
  ######################################
  # # # end UART terminal writer # # #
  ######################################


.org 0x1000
.data
num_store:    .word 0, 0, 0, 0, 0 ,0 # memory location for all 6 randomly generated numbers
rand_max:     .word 10               # maximum number (e.g. 100 gets 0-99)
rand_seed:    .word 1234567          # seed for random number generator
rand_numb:    .word 0                # store random number here
seg7_numbers: .byte 0x3f, 0x06, 0x5b, 0x4f, 0x66, 0x6d, 0x7c, 0x07, 0x7f, 0x6f
                 #  0     1     2     3     4     5     6     7     8     9        
# messages:
start_msg:     .byte 'P', 'r', 'e', 's', 's', ' ', '1', ' ', 't', 'o', ' ', 'S', 't', 'a', 'r', 't', 0
  #prints "Press 1 to start"
play_msg:      .byte 'S','h','a','l','l',' ','w', 'e', ' ', 'p', 'l', 'a', 'y', ' ', 'a', ' ', 'G', 'a', 'm', 'e', '?', 0
  #prints "Shall We play a game?"
thermowar_msg: .byte ' ', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 10, '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'G', 'l', 'o', 'b', 'a', 'l', ' ', 'T', 'h', 'e', 'r', 'm', 'o', 'n', 'u', 'c', 'l', 'e', 'a', 'r', ' ', 'W', 'a', 'r', ':', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', 10, '|', 'U', 's', 'i', 'n', 'g', ' ', 't', 'h', 'e', ' ', 's', 'w', 'i', 't', 'c', 'h', 'e', 's', ',', ' ', 'g', 'u', 'e', 's', 's', ' ', 't', 'h', 'e', ' ', 'b', 'i', 'n', 'a', 'r', 'y', ' ', 'v', 'a', 'l', 'u', 'e', ' ', 'o', 'f', ' ', 'e', 'a', 'c', 'h', ' ', 'd', 'i', 'g', 'i', 't', ' ', 't', 'o', ' ', 'd', 'i', 's', 'a', 'r', 'm', ' ', 't', 'h', 'e', ' ', 'b', 'o', 'm', 'b', ' ', ' ', ' ', ' ', ' ', '|', 10, '|', 'Y', 'o', 'u', ' ', 'h', 'a', 'v', 'e', ' ', 'u', 'n', 'l', 'i', 'm', 'i', 't', 'e', 'd', ' ', 'g', 'u', 'e', 's', 's', 'e', 's', ',', ' ', 'b', 'u', 't', ' ', 'y', 'o', 'u', ' ', 'h', 'a', 'v', 'e', ' ', '4', '2', ' ', 's', 'e', 'c', 'o', 'n', 'd', 's', ' ', 't', 'o', ' ', 'g', 'e', 't', ' ', '6', ' ', 'n', 'u', 'm', 'b', 'e', 'r', 's', ' ', 'c', 'o', 'r', 'r', 'e', 'c', 't', ' ', ' ', ' ', ' ', '|', 10, '|', 'I', 'f', ' ', 'y', 'o', 'u', ' ', 'd', 'o', ' ', 'n', 'o', 't', ',', ' ', 't', 'h', 'e', ' ', 'b', 'o', 'm', 'b', ' ', 'g', 'o', 'e', 's', ' ', 'o', 'f', 'f', ',', ' ', 'd', 'e', 'c', 'i', 'm', 'a', 't', 'i', 'n', 'g', ' ', 't', 'h', 'e', ' ', 'N', 'O', 'R', 'A', 'D', ' ', 'h', 'e', 'a', 'd', 'q', 'u', 'a', 'r', 't', 'e', 'r', 's', ' ', 'i', 'n', ' ', 'C', 'o', 'l', 'o', 'r', 'a', 'd', 'o', ':', '|', 10, '|', 'p', 'l', 'u', 'n', 'g', 'i', 'n', 'g', ' ', 't', 'h', 'e', ' ', 'w', 'o', 'r', 'l', 'd', ' ', 'i', 'n', 't', 'o', ' ', 'G', 'l', 'o', 'b', 'a', 'l', ' ', 'T', 'h', 'e', 'r', 'm', 'o', 'n', 'u', 'c', 'l', 'e', 'a', 'r', ' ', 'W', 'a', 'r', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', 10, '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', 10, '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'T', 'o', ' ', 's', 't', 'a', 'r', 't', ',', ' ', 'r', 'e', 'l', 'e', 'a', 's', 'e', ' ', 'a', 'l', 'l', ' ', 'b', 'u', 't', 't', 'o', 'n', 's', ' ', 'a', 'n', 'd', ' ', 'f', 'l', 'i', 'p', ' ', 's', 'w', 'i', 't', 'c', 'h', 'e', 's', ' ', 'o', 'f', 'f', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', 10, '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'G', 'o', 'o', 'd', ' ', 'l', 'u', 'c', 'k', '!', '!', '!', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', 10, ' ', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', 0
  #prints the game start screen. To see full, look at documentation
success_msg:   .byte ' ', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 10, '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'G', 'l', 'o', 'b', 'a', 'l', ' ', 'T', 'h', 'e', 'r', 'm', 'o', 'n', 'u', 'c', 'l', 'e', 'a', 'r', ' ', 'W', 'a', 'r', ':', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', 10, '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', 10, '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W', 'e', 'l', 'l', ' ', 'D', 'o', 'n', 'e', '.', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', 10, '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Y', 'o', 'u', ' ', 'h', 'a', 'v', 'e', ' ', 's', 'u', 'c', 'c', 'e', 's', 's', 'f', 'u', 'l', 'l', 'y', ' ', 'd', 'i', 's', 'a', 'r', 'm', 'e', 'd', ' ', 't', 'h', 'e', ' ', 'b', 'o', 'm', 'b', '.', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', 10, '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', 10, '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W', 'o', 'u', 'l', 'd', ' ', 'y', 'o', 'u', ' ', 'l', 'i', 'k', 'e', ' ', 't', 'o', ' ', 'p', 'l', 'a', 'y', ' ', 'a', 'g', 'a', 'i', 'n', '?', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', 10, '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', 10, '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '(', '1', ')', ' ', 'Y', 'E', 'S', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '(', '0', ')', ' ', 'N', 'O', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', 10, '|', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '|', 0
  #prints the success screen. see documentation below
failure_msg:   .byte ' ', '_', '_', '_', '_', '_', '_', '_', '_', '.', '-', '^', '^', '-', '-', '-', '.', '.', '.', '.', ',', ',', '-', '-', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', 10, '|', ' ', ' ', ' ', '_', '-', '-', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '-', '-', '_', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'G', 'l', 'o', 'b', 'a', 'l', ' ', 'T', 'h', 'e', 'r', 'm', 'o', 'n', 'u', 'c', 'l', 'e', 'a', 'r', ' ', 'W', 'a', 'r', ':', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', 10, '|', ' ', ' ', '<', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '>', ')', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', 10, '|', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'B', 'o', 'm', 'b', ' ', 'd', 'e', 't', 'o', 'n', 'a', 't', 'e', 'd', '.', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', 10, '|', ' ', ' ', ' ', '\', '.', '_', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '_', '.', '/', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Y', 'o', 'u', ' ', 'h', 'a', 'v', 'e', ' ', 'f', 'a', 'i', 'l', 'e', 'd', '.', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', 10, '|', ' ', ' ', ' ', ' ', ' ', ' ', '`', '`', '`', '-', '-', '.', ' ', '.', ' ', ',', ' ', ';', ' ', '.', '-', '-', ''', ''', ''', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', 10, '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'W', 'o', 'u', 'l', 'd', ' ', 'y', 'o', 'u', ' ', 'l', 'i', 'k', 'e', ' ', 't', 'o', ' ', 't', 'r', 'y', ' ', 'a', 'g', 'a', 'i', 'n', '?', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', 10, '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '.', '-', '=', '|', '|', ' ', ' ', '|', ' ', '|', '=', '-', '.', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', 10, '|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '`', '-', '=', '#', '$', '%', '&', '%', '$', '#', '=', '-', ''', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '(', '1', ')', ' ', 'Y', 'E', 'S', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '(', '0', ')', ' ', 'N', 'O', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', 10, '|', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '/', '|', ' ', ';', ' ', ' ', ':', '|', '\', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '|', 0
  #prints the failure screen. see documentation below
new_line:      .byte 10, 0
  #new line


.end
################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################################
  To run the code, open https://cpulator.01xz.net/?sys=nios-de10-lite, and copy and paste the code into the editor.
  Then, click "Continue" on the blue banner at the top.
  
  Loosely  based off of 1983 movie WarGames, the user is promoted to play a game. 
  The goal of the game is to correctly guess the 6 digit binary code to disarm the bomb.
  (1) print
    "Press 1 to start"
    Use UART to display
    "Shall we play a game?"

    press 1 for yes, 0 for no
      if 0, go back to the beginning
      if 1, go on to (2)
  (2) Print 
    "global thermonuclear war: 
    Using the switches, guess the binary value of each digit to disarm the bomb.
      You have unlimited guesses, but you have 42 seconds to get 6 numbers correct.
      if you do not, the bomb goes off, decimating the NORAD headquarters in Colorado,
        plunging the world into Global Thermonuclear War
      to start, release all buttons and flip switches off
              Good luck!!!"

              due to bit limitations, the longest time i could do for the limit is 42.94967295 seconds

                ______________________NOTE_______________________
              / Due to cpulator  having a toggle for a button,    \
             | I put in code so that the simulator requires you to | 
             | click then "release"/ uncheck the button and switch |
              \ in order to move on to the next part.. see below  /
                *************************************************
                  also LED lights will go on based on your button presses.
                  first LED light stays on as the game is playing.


    The game will randomly generate 6 numbers — ex. 936978
    as it is generating the code, the leds will display the value
          --cool effect to visualize it randomly generating the code
    if it is in the loop, the first LED will be on:
    Use the switches to guess each digit (1-9) in binary. (if switch value is above 9, doesnt update 7seg)
      then press the 1 button to "lock in" your guess

                ____________________NOTE_____________________
              / Due to cpulator having a toggle for a button, \
             | it technically  works by  toggling the 1 button | 
             | and randomly  clicking  switches untill you get |
             | it correct. This was before I found a solution. |
             | However, I kept this in here upon realizing how |
             | difficult it is to guess all of the numbers and |
             | click then unclick the button  after each guess |
             | in cpulator due to the 42.95 second time limit. |
             | However, I left the requirement of pressing the |
             | "1"  button  for  if someone loads this into an |
             | actual  DE-10  Lite  board,  they  dont have to |
              \  modify the code to work better on the board. /
                *********************************************
        note: All of the LED instructions i made for this code arent necessary if you have UART. However
            I thought they look cool so I kept them. If you are doing this on an actual board without
            UART, it is necessary to have the LED instructions

    If correct, the 7segment display shows that number and shifts everything to the left
        then you move on to the next number. 
    if incorrect, it still shows the number but doesnt shift it to the left...

    After 42.95 seconds, it expires and you lose

    If 6 digits are correct before all expire, print 
        "Well done. You have stopped the bomb. Would you like to try again?"
        turns on second LED
    If less than that are correct, print 
        "Bomb detonated. You have failed. Would you like to try again?"
        turns off all LEDs

    Press 1 for yes or 0 for no
    If 1, goto (2)
    If 0, goto (1)


 ascii screen artwork: 
  /*
  when it is outputting things to the UART, the LED's display what character is being outputted to UART
  start_screen:
    "Press 1 to start"

  play_screen:
    "Shall we play a game?"
  game start screen:
   ________________________________________________________________________________
  |                         Global Thermonuclear War:                              |
  |Using the switches, guess the binary value of each digit to disarm the bomb     |
  |You have unlimited guesses, but you have 42 seconds to get 6 numbers correct    |
  |If you do not, the bomb goes off, decimating the NORAD headquarters in Colorado:|
  |plunging the world into Global Thermonuclear War                                |
  |                                                                                |
  |            To start, release all buttons and flip switches off                 |
  |                              Good luck!!!                                      |
   ********************************************************************************
  
  
  success screen:
   ________________________________________________________________________________
  |                         Global Thermonuclear War:                              |
  |                                                                                |
  |                                Well Done                                       |
  |                  You have successfully disarmed the bomb.                      |
  |                                                                                |
  |                       Would you like to play again?                            |
  |                                                                                |
  |                          (1) YES         (0) NO                                |
  |________________________________________________________________________________|


  failure screen:
   ________.-^^---....,,--_________________________________________________________
  |   _--                  --_                  Global Thermonuclear War:          |
  |  <                        >)                                                   |
  |  |                         |                     Bomb detonated.               |
  |   \._                   _./                      You have failed.              |
  |      ```--. . , ; .--'''                                                       |
  |            | |   |                         Would you like to try again?        |
  |         .-=||  | |=-.                                                          |
  |         `-=#$%&%$#=-'                         (1) YES          (0) NO          |
  |___________/| ;  :|\____________________________________________________________|
          
    mushroom cloud art from asciiart.eu

  */

  583502
  261468