/*
Using the code below as a starting template, add your code to perform a 
"bubble" sort of the "numbers" array from 0 to 9. 

Because of little-endian memory should look like this after the program run:

|00001000|  03020100  07060504  00000908
*/ 

.equ NUM_SIZE, 10

.global _start
.text

  movia r15, numbers
  movi r2, 0            #loop counter (i)
  movi r5, 10           #length of array
  movi r8, 0            #secondary loop counter (j)

_start:
 length:
    cmpleui r3, r2, 9   #if (i<=9)
    beq r3, r0, _length  #else end loop
    nums:
      subi r6, r2, 1      #
      sub  r7, r5, r6     #
      cmpltu r4, r8, r7   #if (j<10-i-1)
      beq r4, r0, _nums  #else end loop
      if:
        ldb   r9, (r15)           # get string character
        addi  r10, r9, 1
        cmpgtu r14, r9, r10  #if (r2 + r3 > 10)
        beq r14, r0, fi    #else if branch
        mov r11, r9
        mov r9,  r10
        mov r10, r11
      br fi                 #break loop
      fi:
      addi r8, r8, 1 #j++
    br nums
    _nums:
    addi r2, r2, 1      #i++
  br length              #break loop
  _length:
  # your code here

_stop: br _stop


#array length - i - 1

.org 0x1000
.data
numbers: .byte 5, 1, 2, 9, 6, 0, 7, 4, 8, 3

.end