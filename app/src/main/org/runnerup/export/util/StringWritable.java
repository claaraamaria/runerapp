package org.runnerup.export.util;

import java.io.IOException;
import java.io.OutputStream;


public class StringWritable implements Writable {
    private final byte[]  s;

    public StringWritable(String s) {
        this.s = s.getBytes();
    }

    public StringWritable(byte[] s) {
        this.s = s;
    }

    public void write(OutputStream out) throws IOException {
        out.write(s);
    }
}
