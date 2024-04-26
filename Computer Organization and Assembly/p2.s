.equ IO_BASE, 0xFF200000
.equ LED, 0x00
.equ SWITCH, 0x40

.global _start
_start:
movui r2, 0xFFFF #store 0xffff into register r2
mov  r3, r2 #move r2 to r3
mul r5, r3, r2 #multiply r2 and r3
stw r5, 0x1000(r0) #store result into 0x1000 in memory
ldw r10, 0x1000(r0) #load from 0x1000 memory address to register r10

movia r23, IO_BASE
ldhuio r9, SWITCH(r23) #load whatever is selected with switches to r9
add r10, r10, r9 #add the value from memory with the number selected with switches, and store in r10
stwio r10, LED(r23) #display result as binary with LEDs
_stop:br _stop