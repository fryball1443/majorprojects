.equ IO_BASE, 0xff200000
.equ SEG1, 0x20
.equ SEG2, 0x30
.global _start
_start:
movia r23, IO_BASE # IO base
stwio r0, SEG1(r23) # clear segments
stwio r0, SEG2(r23) #
movi r2, 15 # number to display
movi r3, 0 # segment#
call display_SEG
movi r2, 14 # number to display
movi r3, 1 # segment#
call display_SEG
movi r2, 8 # number to display
movi r3, 5 # segment#
call display_SEG
_stop: br _stop

display_SEG: # args: r2 number, r3 segment# 0-7
ldb r2, numbers(r2) # get new number
muli r3, r3, 8 # convert r3 to bytes
sll r2, r2, r3 # shift left
cmpgti r4, r3, 24
bne r4, r0, use_seg2
ldwio r5, SEG1(r23) # get old 7-seg values
or r5, r5, r2 # combine old and new values
stwio r5, SEG1(r23) # display
br skip_seg2
use_seg2:
ldwio r5, SEG2(r23) # get old 7-seg values
or r5, r5, r2 # combine old and new values
stwio r5, SEG2(r23) # display
skip_seg2:
ret
.org 0x1000

.data
numbers: .byte 0x3f, 0x06, 0x5b, 0x4f, 0x66, 0x6d, 0x7c, 0x07, 0x7f, 0x6f, 0x77, 0x7c, 0x39, 0x5e, 0x79, 0x71
           #     0     1     2     3     4     5     6     7     8     9     a     b     c     d     e    f
