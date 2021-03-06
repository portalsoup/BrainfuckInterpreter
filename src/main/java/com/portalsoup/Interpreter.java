package com.portalsoup;

import java.util.Scanner;

/**
 * The class that parses brainfuck source code and translates instructions into the BrainfuckSystem.
 *
 * Created by julian on 11/11/2016.
 */
public class Interpreter {

    private BrainfuckSystem system;
    Scanner in = new Scanner(System.in);

    public Interpreter() {
        system = new BrainfuckSystem();
    }

    /**
     * Process brianfuck source code and interpret it live.  Each character is interpretted and operated on
     * one by one until the end of the array is reached.
     *
     * @param srcProgram The character array containing the source code
     */
    public void run(char[] srcProgram) {

        for (int x = 0; x < srcProgram.length; x++) {

            switch (srcProgram[x]) {
                case '+':
                    system.increment();
                    break;
                case '-':
                    system.decrement();
                    break;
                case '<':
                    system.decreasePointer();
                    break;
                case '>':
                    system.increasePointer();
                    break;
                case '.':
                    System.out.print((char) system.read());
                    break;
                case ',':
                    system.write(in.nextShort());
                    break;
                case '[':
                    system.pushLoopStartPointer(x);
                    break;
                case ']':
                    if (system.read() != 0) {
                        x = system.popLoopStartPointer() - 1;
                    }
            }
        }
    }

}
