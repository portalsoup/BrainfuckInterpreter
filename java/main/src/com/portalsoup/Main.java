package com.portalsoup;

import java.io.*;

/**
 * Created by julian on 11/11/2016.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        Interpreter interpreter = new Interpreter();

        FileReader src = new FileReader(args[0]);
        StringBuilder srcStr = new StringBuilder();

        int c;
        while ((c = src.read()) != -1) {
            srcStr.append((char) c);
        }

        interpreter.run(srcStr.toString().toCharArray());
    }
}
