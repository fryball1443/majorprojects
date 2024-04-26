/* Write a program that solves the equation: (2*(3*3+1))/4.
	Do not skip any of the math steps. In other words, do not manually calculate any of the math 
	and "hard code" the values. The purpose is to use assembled language to calculate the answer.
	1. Store the calculated value in memory at address 0x1000.
	2. Now take the value from 0x1000 and store it in register r5.
	3. Lastly, use the value from r5 to turn on LEDs. 
	Upload code in a text file titled p1.txt 
*/

.equ IO_BASE, 0xff200000
.equ LED, 0x00
.equ SWITCH, 0x40
.equ BUTTON, 0x50
.equ SEG1, 0x20
.equ SEG2, 0x30
.equ UART, 0x1000
.equ TIME1, 0x2000
.equ TIME2, 0x2020

.global _start
_start:
movi r2, 3
movi r3, 3
mul  r4, r2, r3 #multiply 3*3

addi r4, r4, 1 #add 1

muli r4, r4, 2 #multiply result by 2

movi r5, 4
divu r4, r4, r5 #divide result / 4

stw r4, 0x1000(r0) #store result into memory address 0x1000
ldw r5, 0x1000(r0) #load contents of memory address 0x1000 to r5

movia r23, IO_BASE
stwio r5, LED(r23) #display result as binary with LEDs

_stop:br _stop