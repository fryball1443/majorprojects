.macro push reg
subi sp, sp, 4
stw \reg, 0(sp)
.endm

.macro pop reg
ldw \reg, 0(sp)
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
#.equ RAND, 0xff1fffa0

.global _start 
_start: 
movia sp, TOS
  movia r23, IO_BASE
call random_storage
movi r2, 0
movi r3, 4
movi r4, 8
movi r5, 12
movi r6, 16
movi r7, 20
ldw r2, num_store(r2)
ldw r3, num_store(r3)
ldw r4, num_store(r4)
ldw r5, num_store(r5)
ldw r6, num_store(r6)
ldw r7, num_store(r7)

#call random                 # generate random number
#ldw r16, rand_numb(r0)       # load it from memory

/*
movi r17, 0            #word counter (j)
load_loop:
  cmpleui r19, r16, 6       #if (i<=6)
  beq r19, r0, _load_loop  #else end loop

  ldw r15, num_store(r17)    #load random number into memory
  addi r16, r16, 1          #i++
  addi r17, r17, 4          #j++
br load_loop              #break loop
_load_loop:*/
_stop: br _stop

/*##test
write_terminal:              # r15 address of string
 push r16                    # stack
 write_char:
   ldwio r16, UART+4(r23)    # read control register
   beq   r16, r0, write_char # does buffer have room ?
   ldb   r16, (r15)          # get string character
   beq   r16, r0, _write     # break if NULL terminator 
   stwio r16, UART(r23)      # else write_terminal to UART
   addi  r15, r15, 1           # index next character
   br write_char             # loop
 _write:
 pop r16                     # unstack
 ret
*/

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

.org 0x1000
.data
rand_max:  .word 10      # maximum number (e.g. 100 gets 0-99)
rand_seed: .word 1234567  # seed for random number generator
rand_numb: .word 0        # store random number here
num_store: .word 0, 0, 0, 0, 0 ,0