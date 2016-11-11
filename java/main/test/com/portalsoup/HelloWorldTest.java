package com.portalsoup;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringWriter;

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


        PrintStream stdout = System.out;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);

            System.setOut(ps);

            Interpreter interpreter = new Interpreter();

            interpreter.run(src.toCharArray());

            StringWriter writer = new StringWriter();

            StringBuilder output = new StringBuilder();

            for (Byte aByte : baos.toByteArray()) {
                output.append((char) aByte.byteValue());

            }

            assertThat("The output should equal 'Hello World!'", output.toString(), is(equalTo("Hello World!\n")));
        } finally {
            System.out.flush();
            System.setOut(stdout);
        }
    }
}
