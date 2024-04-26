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


.macro push reg
subi sp, sp, 4
stw	\reg, 0(sp)
.endm

.macro pop reg
ldw	\reg, 0(sp)
addi sp, sp, 4
.endm

.global _start
_start:
  movia sp,  TOS
  movia r23, IO_BASE
  movia r15, start_msg
  call start_screen
  if:
    ldwio r14, BUTTON(r23) #loads value of button
    cmpeqi r14, r14, 2
    beq r14, r0, else   #else branch
    call play_screen
   br fi               #break loop
  else:
    call play_screen
  fi:
  call play_screen
  call thermowar_screen
  call success_screen
  call failure_screen
_stop: br _stop

start_screen:
  push ra
  call clear_terminal
  pop ra
  movia r15, start_msg
  push ra
  call write_terminal
  pop ra
  ret

play_screen:
  push ra
  call clear_terminal
  pop ra
  movia r15, play_msg
  push ra
  call write_terminal
  pop ra
  ret

thermowar_screen:
  push ra
  call clear_terminal
  pop ra
  movia r15, thermowar_msg
  push ra
  call write_terminal
  pop ra
  ret

success_screen:
  push ra
  call clear_terminal
  pop ra
  movia r15, success_msg
  push ra
  call write_terminal
  pop ra
  ret

failure_screen:
  push ra
  call clear_terminal
  pop ra
  movia r15, failure_msg
  push ra
  call write_terminal
  pop ra
  ret

clear_terminal:
  push r2
  movi r2, 10
  clear_loop:
    movia r15, new_line
    push ra
    call write_terminal
    pop ra
    subi r2, r2, 1
    bne r2, r0, clear_loop
  pop r2
  ret

clear_terminal_old:
  push r2
  movi r2, 0x1b
  stwio r2, UART(r23)
  movi r2, '['
  stwio r2, UART(r23)
  movi r2, '2'
  stwio r2, UART(r23)
  movi r2, 'J'
  stwio r2, UART(r23)
  pop r2
 ret

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
 
.org 0x1000
.data
# Prompts
#pos:          .word "Esc [2J"
start_msg:     .byte 'P', 'r', 'e', 's', 's', ' ', '1', ' ', 't', 'o', ' ', 'S', 't', 'a', 'r', 't', 0
play_msg:      .byte 'S','h','a','l','l',' ','w', 'e', ' ', 'p', 'l', 'a', 'y', ' ', 'a', ' ', 'G', 'a', 'm', 'e', '?', 0
thermowar_msg: .byte 'G', 'l', 'o', 'b', 'a', 'l', ' ', 'T', 'h', 'e', 'r', 'm', 'o', 'n', 'u', 'c', 'l', 'e', 'a', 'r', ' ', 'W', 'a', 'r', ':', 10, 'y', 'o', 'u', ' ', 'h', 'a', 'v', 'e', ' ', '4', '2', ' ', 's', 'e', 'c', 'o', 'n', 'd', 's', ' ', 't', 'o', ' ', 'd', 'i', 's', 'a', 'r', 'm', ' ', 't', 'h', 'e', ' ', 'b', 'o', 'm', 'b', ' ', 'b', 'y', ' ', 'c', 'o', 'r', 'r', 'e', 'c', 't', 'l', 'y', ' ', 'g', 'u', 'e', 's', 's', 'i', 'n', 'g', ' ', 'm', 'o', 's', 't', ' ', 'o', 'f', ' ', 't', 'h', 'e', ' ', 'n', 'u', 'm', 'b', 'e', 'r', 's', ' ', 'u', 's', 'i', 'n', 'g', ' ', 'b', 'i', 'n', 'a', 'r', 'y', 0
success_msg:   .byte 'W', 'e', 'l', 'l', ' ', 'd', 'o', 'n', 'e', '.', ' ', 'Y', 'o', 'u', ' ', 'h', 'a', 'v', 'e', ' ', 's', 't', 'o', 'p', 'p', 'e', 'd', ' ', 't', 'h', 'e', ' ', 'b', 'o', 'm', 'b', '.', ' ', 'W', 'o', 'u', 'l', 'd', ' ', ' ', 'y', 'o', 'u', ' ', 'l', 'i', 'k', 'e', ' ', 't', 'o', ' ', 't', 'r', 'y', ' ', 'a', 'g', 'a', 'i', 'n', '?', 0
failure_msg:   .byte 'B', 'o', 'm', 'b', ' ', 'd', 'e', 't', 'o', 'n', 'a', 't', 'e', 'd', '.', ' ', 'Y', 'o', 'u', ' ', 'h', 'a', 'v', 'e', ' ', 'f', 'a', 'i', 'l', 'e', 'd', '.', ' ', 'W', 'o', 'u', 'l', 'd', ' ', 'y', 'o', 'u', ' ', 'l', 'i', 'k', 'e', ' ', 't', 'o', ' ', 't', 'r', 'y', ' ', 'a', 'g', 'a', 'i', 'n', '?', 0
new_line:      .byte 10, 0