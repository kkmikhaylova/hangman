package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader implements Reader {

    private static BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public String read() throws IOException {
        return READER.readLine();
    }
}
