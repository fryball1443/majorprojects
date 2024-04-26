/*
Create a program to solve the summation below using a loop. The result should be in r5 and also sent to the LEDs.  
1^2 + 2^2 + 3^2 + 4^2 + 5^2
OR
1*1 + 2*2 + 3*3 + 4*4 + 5*5 
*/

.equ IO_BASE, 0xff200000
.equ LED, 0x00
.equ NUMBER, 1

.global _start
  _start:
 #store "variables"
  movia r23, IO_BASE    #initialize io base
  movi r5, 0            #total number
  movi r2, 1            #loop counter (i)
  movi r4, 0            #current multiplication result number
 #start loop
  power:
    cmpleui r3, r2, 5   #if (i<=5)
    beq r3, r0, _power  #else end loop
    mul r4, r2, r2      #then multiply i by itself to make i^2
    add r5, r5, r4      #add result of mul to total
    addi r2, r2, 1      #i++
    stwio r5, LED(r23)  #display result as binary with LEDs
  br power              #break loop
  _power:
_stop: br _stop