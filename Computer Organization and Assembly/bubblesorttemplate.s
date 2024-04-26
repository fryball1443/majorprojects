.equ NUM_SIZE, 10

.global _start
.text
_start:

# Load the length of the array into r2
movi r2, NUM_SIZE

# Set the outer loop counter r3 to (NUM_SIZE - 1)
addi r3, r2, -1

# Outer loop for bubble sort
bubble_sort:
    # Set the inner loop counter r4 to 0
    movi r4, 0

    # Inner loop for bubble sort
    bubble_inner:
        # Calculate the address of the current element to compare
        mov r5, r3
        muli r5, r5, 4
        sub r5, r5, r4
        add r5, r5, numbers

        # Load the current element into r6
        ldw r6, 0(r5)

        # Calculate the address of the next element to compare
        subi r5, r5, 4

        # Load the next element into r7
        ldw r7, 0(r5)

        # Compare r6 and r7
        cmp r6, r7
        bge bubble_not_swap

        # Swap r6 and r7
        stw r7, 0(r5)
        stw r6, 4(r5)

        # Set a flag to indicate that a swap was made
        movi r8, 1

    bubble_not_swap:
        # Increment the inner loop counter r4
        addi r4, r4, 4

        # Continue inner loop if r4 < r3
        cmp r4, r3
        blt bubble_inner

    # Decrement the outer loop counter r3
    subi r3, r3, 1

    # Continue outer loop if r3 > 0
    cmp r3, 0
    bgt bubble_sort

# Stop program
_stop: br _stop

.org 0x1000
.data
numbers: .byte 5, 1, 2, 9, 6, 0, 7, 4, 8, 3

.end
