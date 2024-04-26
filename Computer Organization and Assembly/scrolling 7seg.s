.equ IO_BASE, 0xFF200000
.equ HEX3_HEX0, 0x20

.global _start
_start:
    movia r23, IO_BASE
    
    # Store the ASCII values for the letters "hello" in memory
    sth 0x6F(r2), 0x100(r23)   # 'h' (0x6F)
    sth 0x65(r2), 0x102(r23)   # 'e' (0x65)
    sth 0x6C(r2), 0x104(r23)   # 'l' (0x6C)
    sth 0x6C(r2), 0x106(r23)   # 'l' (0x6C)
    sth 0x6F(r2), 0x108(r23)   # 'o' (0x6F)
    sth r2, 0x10A(r23)         # Null terminator
    
    movi r8, 0x100
    movi r9, 0
    
loop:
    # Shift the characters in memory to the left
    lhu r10, 0x100(r23) 
    lhu r11, 0x102(r23)
    lhu r12, 0x104(r23)
    lhu r13, 0x106(r23)
    lhu 00000r14, 0x108(r23)
    sth r11, 0x100(r23)
    sth r12, 0x102(r23)
    sth r13, 0x104(r23)
    sth r14, 0x106(r23)
    sth r10, 0x108(r23)
    
    # Send the first 4 characters to the 7-segment display
    lhu r10, 0x100(r23)
    lhu r11, 0x102(r23)
    lhu r12, 0x104(r23)
    lhu r13, 0x106(r23)
    sll r10, r10, 16
    sll r11, r11, 8
    or r10, r10, r11
    or r10, r10, r12
    stw r10, HEX3_HEX0(r23)
    mov r9, r10
    
    
    # Delay for a short time
    addi r9, r9, 1
    cmp r9, r8
    bne loop
    movi r9, 0
    
    # Send the last 2 characters to the 7-segment display
    lhu r14, 0x108(r23)
    sll r13, r13, 16
    sll r14, r14, 8
    or r13, r13, r14
    stw r13, HEX3_HEX0(r23)
    
    # Delay for a short time
    addi r9, r9, 1
    cmp r9, r8
    bne loop
    movi r9, 0
    
    br loop
