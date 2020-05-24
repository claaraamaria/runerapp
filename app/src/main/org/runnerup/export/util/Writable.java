package org.runnerup.export.util;

import java.io.IOException;
import java.io.OutputStream;

interface Writable {
    void write(OutputStream out) throws IOException;
}
