.global _start
_start:

movi r2, 1

stw r2, 0x4000(r0)
stb r2, 0x4004(r0)
sth r2, 0x4008(r0)

ldw r7, 0x4000(r0)
ldw r8, 4(r0)

