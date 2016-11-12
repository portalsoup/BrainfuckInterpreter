package com.portalsoup;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by julian on 11/11/2016.
 */
public class HelloWorldTest {

    @Test
    public void helloWorldTest() {
        String src =
                "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.";


        // Store our original System.out to restore later
        PrintStream stdout = System.out;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);

            // System.out now points to our new PrintStream so we can intercept output
            System.setOut(ps);

            // Prepare and run our source code
            Interpreter interpreter = new Interpreter();
            interpreter.run(src.toCharArray());

            StringBuilder output = new StringBuilder();

            // Read bytes from our output stream that our System.out statements printed
            for (Byte aByte : baos.toByteArray()) {
                output.append((char) aByte.byteValue());

            }

            // The program should output 'Hello World!'
            assertThat("The output should equal 'Hello World!'", output.toString(), is(equalTo("Hello World!\n")));
        } finally {

            // After we're done, restore System.out to in case more tests run.
            System.out.flush();
            System.setOut(stdout);
        }
    }
}
