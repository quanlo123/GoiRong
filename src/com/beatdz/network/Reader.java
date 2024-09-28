/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beatdz.network;

import com.beatdz.server.Server;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Reader {

    public DataInputStream dis = null;

    public Reader(DataInputStream paramDataInputStream) {
        this.dis = paramDataInputStream;
    }

    public Reader(InputStream paramInputStream) {
        this.dis = new DataInputStream(paramInputStream);
    }

    public Reader(byte[] paramArrayOfbyte) {
        this.dis = new DataInputStream(new ByteArrayInputStream(paramArrayOfbyte));
    }

    public Reader(Socket socket) throws IOException {
        this.dis = new DataInputStream(socket.getInputStream());
    }

    public final short readUnsignedByte() throws java.io.IOException {
        return (short) this.dis.readUnsignedByte();
    }

    public final byte[] read(byte[] var1, int var2, int var3) throws java.io.IOException {
        this.dis.read(var1, var2, var3);
        return var1;
    }

    public final byte[] read() throws java.io.IOException {
        byte[] var1 = new byte[this.dis.readInt()];
        this.dis.read(var1);
        return var1;
    }

    public final byte[] readFully() throws java.io.IOException {
        byte[] var1 = new byte[this.dis.available()];
        this.dis.read(var1);
        return var1;
    }

    public final String readUTF() throws java.io.IOException {
        short var1;
        if ((var1 = (short) this.dis.readUnsignedByte()) == 0) {
            return this.dis.readUTF();
        } else {
            String var2 = "";

            for (int var3 = 0; var3 < var1; ++var3) {
                var2 = var2 + Server.stringUTF.charAt(this.dis.readUnsignedByte());
            }

            return var2;
        }
    }

    public final void close() {
        try {
            if (this.dis != null) {
                this.dis.close();
                this.dis = null;
            }
        } catch (Exception exception) {
            return;
        }
    }
}
