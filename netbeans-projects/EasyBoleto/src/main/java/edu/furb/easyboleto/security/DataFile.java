package edu.furb.easyboleto.security;

import java.io.Serializable;

public class DataFile implements Serializable {

    private byte[] data;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
