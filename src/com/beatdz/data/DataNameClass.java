package com.beatdz.data;

import com.beatdz.network.Writer;
import java.io.IOException;

public final class DataNameClass {

    public byte id;
    public String name;

    public DataNameClass(int var1) {
        this.id = (byte) var1;
    }

    @Override
    public String toString() {
        return "DataNameClass{" + "id=" + id + ", name=" + name + '}';
    }

    public void write(Writer writer) {
        try {
            writer.writeUTF(name);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
