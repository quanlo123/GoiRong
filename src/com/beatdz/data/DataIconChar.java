package com.beatdz.data;

import com.beatdz.network.Writer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DataIconChar {
    
    public byte id;
    public short idIconChar;
    
    public DataIconChar(int var1) {
        this.id = (byte) var1;
    }
    
    @Override
    public String toString() {
        return "DataIconChar{" + "id=" + id + ", idIconChar=" + idIconChar + '}';
    }
    
    public void write(Writer writer) {
        try {
            writer.dos.writeShort(idIconChar);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
