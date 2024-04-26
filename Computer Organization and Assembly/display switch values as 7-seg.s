.macro push reg
subi sp, sp, 4
stw    \reg, 0(sp)
.endm

.macro pop reg
ldw    \reg, 0(sp)
addi sp, sp, 4
.endm

.equ IO_BASE, 0xff200000
.equ LED, 0x00
.equ SWITCH, 0x40
.equ BUTTON, 0x50
.equ SEG1, 0x20
.equ SEG2, 0x30
.equ UART, 0x1000
.equ TIME1, 0x2000
.equ TIME2, 0x2020
.equ TOS, 0x04000000

.global _start 
_start: 

# addressess
movia sp,  TOS
movia r23, IO_BASE 

#movia r2, 123456
ldwio		r2,		SWITCH(r23)
/*
if:
  push r7
  ldwio r14, BUTTON(r23) #loads value of button
  cmpeqi r14, r14, 2
  bne r14, r7, if  
  loop:
    push r3
    push r6
    ldwio r3, SWITCH(r23) #loads value of switches (n)
    cmpltui r6, r3, 10    #if n < 10
    beq r6, r0, _loop   #else branch
    mov r3, r2            #saves (n) as r2 to output to 7 segment display
    pop r6
    pop r3
    br loop
  _loop:
  pop r7
*/
/*
if:
  push r7
  ldwio r14, BUTTON(r23) #loads value of button
  cmpeqi r14, r14, 2
  bne r14, r7, if  
  #*/
  #ldwio r14, BUTTON(r23)
  loop:
    #ldwio r14, BUTTON(r23)
    push r13
    #push r3
    push r6
    ldwio r12, SWITCH(r23)
    beq r12, r3, skip_disp  #if the guess is the same as before, doesnt reset display
    ldwio r3, SWITCH(r23) #loads value of switches (n)
    cmpltui r6, r3, 10    #if n < 10
    beq r6, r0, skip_disp     #else branch
    mov r2, r3            #saves (n) as r2 to output to 7 segment display
    call seg7_output
    skip_disp:
    pop r6
    #pop r3
    movi r13, 2 #button value should be 2
    ldwio r14, BUTTON(r23) #loads value of button
    bne r14, r13, _loop #break loop if 1 button is not pressed
    pop r13
    br loop
  _loop:
  #pop r7

  




_stop: br _stop


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
################################################
 # # # # # # # end Marvin's code  # # # # # # # 
################################################

.org 0x1000
.data
seg7_numbers: .byte 0x3f, 0x06, 0x5b, 0x4f, 0x66, 0x6d, 0x7c, 0x07, 0x7f, 0x6f
                 #  0     1     2     3     4     5     6     7     8     9        