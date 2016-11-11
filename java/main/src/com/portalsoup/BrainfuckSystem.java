package com.portalsoup;

/**
 * The brains of the interpreter.
 *
 * Created by julian on 11/11/2016.
 */
public class BrainfuckSystem {

    /**
     * The interpreter's stack for storing values.
     */
    short[] stack;

    /**
     * A pointer to the current value in the stack.  All reads from the stack happen at this index.
     */
    int stackPointer;

    /**
     *  Accepts a vlaue that represents the starting position of a loop.  This should be index of the loop's starting
     *  character in the source code.
     */
    int[] loopPointerStack;

    /**
     * A pointer to the current index in the loop stack.  This number represents how many nested loops we're inside.
     */
    int loopPointerIndex;

    public BrainfuckSystem(int stackSize) {
        stack = new short[stackSize];
        stackPointer = 0;

        loopPointerStack = new int[stackSize];
        loopPointerIndex = 0;
    }

    public BrainfuckSystem() {
        this(30000);
    }

    /**
     * Given a value that has just previously been incremented or decremented, wrap around so the range of integers
     * remain 0-255.
     *
     * @param value A recently incremented or decremented value that will be going into the stack.
     *
     * @return The properly wrapped value
     */
    private short wrapValue(short value) {
        return (byte) ((256 + value % 256) % 256);
    }

    /**
     * Read the value in the stack at the current index.
     *
     * @return A single unsigned byte of data casted to a short.
     */
    public short read() {
        return stack[stackPointer];
    }

    /**
     * Accept input and write a value directly the current index in the stack.
     *
     * @param value A value that has been interactively received
     */
    public void write(short value) {
        stack[stackPointer] = wrapValue(value);
    }

    /**
     * Increment the value in the current index of the stack.
     */
    public void increment() {
        stack[stackPointer] = wrapValue((short) (stack[stackPointer] + 1));
    }

    /**
     * Decrement the value in the current index of the stack.
     */
    public void decrement() {
        stack[stackPointer] = wrapValue((short) (stack[stackPointer] - 1));
    }

    /**
     * Increment the value of the pointer that points to the current stack's index.
     */
    public void increasePointer() {
        stackPointer++;
    }

    /**
     * Decrement the value of the pointer that points to the current stack's index.
     */
    public void decreasePointer() {
        stackPointer--;
    }

    /**
     * Pop the nested-most unterminated loop starting point.  This value is the index in the src code.
     * @return
     */
    public int popLoopStartPointer() {
        return loopPointerStack[loopPointerIndex--];
    }

    /**
     * Entered a new loop, Pushing the number of the starting bracket to the loop stack.
     * @param loopPointer
     */
    public void pushLoopStartPointer(int loopPointer) {
        loopPointerIndex++;
        loopPointerStack[loopPointerIndex] = loopPointer;
    }

}
