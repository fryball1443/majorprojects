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

.global _start 
_start: 

# addressess
movia sp,  TOS
movia r23, IO_BASE
movi r8,  0  #current number for seven seg output
movi r9,  0  #total number for seven seg output
movi r10, 0  #number of loops correctly guessed (c)
movi r11, 2  #used for comparing button to verify that "1" button is pressed
call random_storage
main_loop:
  stwio r0, SEG1(r23)     # clear 7seg hex0
  stwio r0, SEG2(r23)     # clear 7seg hex1
  push r20
  push r16
  push r17
  push r19
  movi r16, 1            #loop counter (i)
  movi r17, 0            #word counter (j)
  push r2
  #movia r2, 6000000000   # value for 60 second timer
  movia r2,  4200000000
  call start_timer      # start the timer
  pop r2
  read_seg:
    ldwio  r22, TIME(r23)       # read timer status
    cmpeqi r22, r22, 0b10        # is it running ?
    beq    r22, r0, _read_seg   # loop until timer expired 
    #ldwio r14, BUTTON(r23)
    #push r13
    #push r3
    cmpleui r19, r10, 6       #if (c<=6)
    beq r19, r0, _read_seg  #else end loop
    push r6
    ldwio r12, SWITCH(r23)
    beq r12, r3, skip_disp  #if the guess is the same as before, doesnt reset display
    ldwio r3, SWITCH(r23)   #loads value of switches (n)
    cmpltui r6, r3, 10      #if n < 10
    beq r6, r0, skip_disp   #if n !< 10, doesnt change the 7seg display
    add r8, r9, r3          #saves (n + previous values) to r8 to be put into 7seg display
    mov r2, r8              #saves (n) as r2 to output to 7 segment display
    call seg7_output
    skip_disp:
    pop r6
    #pop r3
    #if button is pressed... delete later
      movi r11, 2 #button value matches this if 1 button is pressed
      ldwio r14, BUTTON(r23) #loads value of button
      bne r14, r11, skip_store  #if 1 is not pressed, then skip "confirming"/storing answer, and restart loop
      #pop r13
    #*/

    ldw r20, num_store(r17)    #load random number from memory
    if: #if value of switches is accurate, stores the value into 7seg display and moves all digits to the left
      bne r20, r3, skip_store   #if value is not accurate, keeps looping until value is accurate...
      addi r16, r16, 1          #i++
      addi r17, r17, 4          #j++
      addi r10, r10, 1          #c++
      add r9, r9, r3            #stores the value in switches to total string of numbers
      muli r9, r9, 10           #moves the value in total string to the left by one decimal point 
      #add r9, r9, r3            #store the value 
      mov r2, r9                #saves value to be put in the 7seg output
      call seg7_output

    skip_store:
    #movi r13, 2
    #beq r16, r13, _read_seg
    br read_seg
  _read_seg:
  push r19
  pop r17
  pop r16
  pop r20
#br main_loop
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
      stw r20, num_store(r17)    #store random number into memory
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


######################################################################################
#                             Marvin's subroutines                                   #       
######################################################################################

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
    
  wait_for_timeout: # no args
  push r2                     # stack
  wait_loop:
    ldwio  r2, TIME(r23)       # read timer status
    cmpeqi r2, r2, 0b10        # is it running ?
    bne    r2, r0, wait_loop   # loop until timer expired    
  pop r2                      # unstack
  ret
##########################################
 # # # # # end timer subroutine # # # # #
##########################################


.org 0x1000
.data
rand_max:  .word 10               # maximum number (e.g. 100 gets 0-99)
rand_seed: .word 1234567          # seed for random number generator
rand_numb: .word 0                # store random number here
num_store: .word 0, 0, 0, 0, 0 ,0 # memory location for all 6 randomly generated numbers
seg7_numbers: .byte 0x3f, 0x06, 0x5b, 0x4f, 0x66, 0x6d, 0x7c, 0x07, 0x7f, 0x6f
                 #  0     1     2     3     4     5     6     7     8     9        



.end
Based off of 1983 movie WarGames, the user is promoted to play a game. 
The goal of the game is to correctly guess the 6 digit
(1) "Press 1 to start"
  Use UART to display
   "Shall we play a game?"

  press 1 for yes, 0 for no
    if 0, go back to the beginning
    if 1, go on to (2)
(2) Print 
  "global thermonuclear war: 
   Using the switches, guess the binary value of each digit to disarm the bomb.
    You have unlimited guesses, but you have 42 seconds to get [4 or 6] numbers correct.
    if you do not, the bomb goes off, decimating the NORAD headquarters in Colorado,
      plunging the world into Global Thermonuclear War
    to start, release all buttons and flip switches off
            Good luck!!!"


  Then have it randomly choose the 6 numbers — ex. 629547
  And you have x seconds to use the switches to type in binary the number 1-9 and 1 button to submit

  If correct, the 7segment display shows that number and shifts everything to the left
  if incorrect, it stays the same until you get the right number

  After 42 seconds, it expires and you lose

  If [4/6 or 6/6... haven't decided yet... currently 6/6] digits are correct before all expire, print 
      "Well done. You have stopped the bomb. Would you like to try again?"
  If less than that are correct, print 
      "Bomb detonated. You have failed. Would you like to try again?"

  Press 1 for yes or 0 for no
  If 1, goto (2)
  If 0, goto (1)
