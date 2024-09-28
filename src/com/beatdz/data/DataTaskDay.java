package com.beatdz.data;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DataTaskDay {

    public int id;
    public String name;

    @Override
    public String toString() {
        return "GoiRong_ii{" + "a=" + id + ", b=" + name + ", c=" + amount + '}';
    }
    public int amount;

    public void write(com.beatdz.network.Writer writer) {
        try {
            writer.writeByte(id);
            writer.writeUTF(name);
            writer.writeShort(amount);
        } catch (IOException ex) {
        }

    }
}
