package com.beatdz.data;

import com.beatdz.network.Writer;
import java.io.IOException;

public final class DataNameChar {

    public byte id;
    public byte type;
    public String name;

    public DataNameChar(int var1) {
        this.id = (byte) var1;
    }

    @Override
    public String toString() {
        return "DataNameChar{" + "id=" + id + ", type=" + type + ", name=" + name + '}';
    }

    public void write(Writer writer) {
        try {
            writer.writeUTF(name);
            writer.dos.writeByte(type);
            writer.dos.writeShort(type);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
