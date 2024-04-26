/*
Create a program equivalent to:
  public static int myMethod() {
    int r2 = 1, r3 = 2, r5 = 0;
    if (r2 + r3 > 10)
        r5 = 1;
    else if (r2 * r3 == 2)
        r5 = 2;
    else
        r5 = 3;
    return r5;
  }
}
*/

.global _start
_start:
  call myMethod
_stop: br _stop

myMethod:
  #initialize registers
    movi r2, 1
    movi r3, 2
    movi r5, 0
  #code
    if:
      movi r4, 0
      add r4, r2, r3      #add r2 and r3
      cmpgtui r7, r4, 10  #if (r2 + r3 > 10)
      beq r7, r0, elif   #else if branch
      movi r5, 1          #if it is correct, store 1 into r5
    br fi                 #break loop
    elif:
      movi r4, 0
      mul r4, r2, r3      #add r2 and r3
      cmpeqi r7, r4, 2   #if (r2 * r3 == 2)
      beq r7, r0, else   #else if branch
      movi r5, 2          #if it is correct, store 2 into r5
    br fi                 #break loop
    else:
      movi r5, 3
    fi:
ret