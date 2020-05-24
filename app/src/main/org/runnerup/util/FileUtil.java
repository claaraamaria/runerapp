package org.runnerup.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class FileUtil {

    private static int copy(InputStream src, OutputStream dst) throws IOException {
        int cnt = 0;
        byte buf[] = new byte[1024];
        while (src.read(buf) > 0) {
            cnt += buf.length;
            dst.write(buf);
        }
        return cnt;
    }

    public static int copyFile(String to, String from) throws IOException {
        FileInputStream input = null;
        FileOutputStream output = null;

        try {
            input = new FileInputStream(from);
            output = new FileOutputStream(to);

            return copy(input, output);
        } finally {
            close(input);
            close(output);
        }
    }

    public static void close(InputStream input) {
        if (input != null)
            try {
                input.close();
            } catch (IOException ex) {
            }
    }

    private static void close(OutputStream input) {
        if (input != null)
            try {
                input.close();
            } catch (IOException ex) {
            }
    }

    @SuppressWarnings("UnusedReturnValue")
    public static int copy(InputStream input, String dst) throws IOException {
        FileOutputStream output = null;

        try {
            output = new FileOutputStream(dst);

            return copy(input, output);
        } finally {
            close(output);
        }
    }
}
