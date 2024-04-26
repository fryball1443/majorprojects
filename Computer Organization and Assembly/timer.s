.macro push reg
subi sp, sp, 4
stw    \reg, 0(sp)
.endm

.macro pop reg
ldw    \reg, 0(sp)
addi sp, sp, 4
.endm

.equ IO_BASE, 0xff200000
.equ TIME,      0x2000
.equ TIME_CTRL, TIME+0x4
.equ TIME_START,TIME+0x8
.equ TOS,     0x04000000

.global _start
_start:

movia sp,  TOS        # setup top of stack
movia r23, IO_BASE    # setup IO base

movia r2, 500000000   # value for 5 second timer
call start_timer      # start the timer
call wait_for_timeout # wait for it to timeout

_stop: br _stop
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
########################################
 # # # # # end timer subroutine # # # # #
########################################