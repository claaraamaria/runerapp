package org.runnerup.export.util;


public class Part<Value extends Writable> {

    private String name = null;
    private String filename = null;
    private String contentType = null;
    private String contentTransferEncoding = null;
    private Value value = null;

    public Part(String name, Value value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentTransferEncoding(String contentTransferEncoding) {
        this.contentTransferEncoding = contentTransferEncoding;
    }

    public String getContentTransferEncoding() {
        return contentTransferEncoding;
    }

    public Value getValue() {
        return value;
    }
}
