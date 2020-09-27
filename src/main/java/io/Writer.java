package io;

import java.io.IOException;

public interface Writer {
    void write(String str) throws IOException;

    void write(String format, Object... args) throws IOException;
}
