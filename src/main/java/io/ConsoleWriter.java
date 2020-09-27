package io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ConsoleWriter implements Writer {

    private static BufferedWriter WRITER = new BufferedWriter(new OutputStreamWriter(System.out));

    @Override
    public void write(String str) throws IOException {
        WRITER.write(str);
        WRITER.newLine();
        WRITER.flush();
    }

    @Override
    public void write(String format, Object... args) throws IOException {
        write(String.format(format, args));
    }

}
