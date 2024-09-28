package com.beatdz.network;

import com.beatdz.lib.Utlis;
import com.beatdz.server.Server;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Writer {

    public ByteArrayOutputStream os = null;

    public DataOutputStream dos = null;

    public Writer(DataOutputStream paramDataOutputStream) {
        this.dos = paramDataOutputStream;
    }

    public Writer(Socket socket) throws IOException {
        this.dos = new DataOutputStream(socket.getOutputStream());
    }

    public Writer() {
        this.os = new ByteArrayOutputStream();
        this.dos = new DataOutputStream(this.os);
    }

    public final void writeBoolean(boolean var1) throws java.io.IOException {
        this.dos.writeBoolean(var1);
    }

    public final void writeByte(int var1) throws java.io.IOException {
        this.dos.writeByte(var1);
    }

    public final void writeShort(int var1) throws java.io.IOException {
        this.dos.writeShort(var1);
    }

    public final void writeInt(int var1) throws java.io.IOException {
        this.dos.writeInt(var1);
    }

    public final void writeLong(long var1) throws java.io.IOException {
        this.dos.writeLong(var1);
    }

    public final void write(byte[] var1) throws java.io.IOException {
        this.dos.writeInt(var1.length);
        this.dos.write(var1);
    }

    public final void writeUTF(String paramString) throws IOException {
        if (paramString.length() > 0 && paramString.length() <= 255) {
            this.dos.writeByte(paramString.length());
            for (int b = 0; b < paramString.length(); b++) {

                int i;
                if ((i = Server.stringUTF.indexOf(paramString.charAt(b))) < 0) {
                    i = 0;
                }
                this.dos.writeByte(i);
            }
            return;
        }
        this.dos.writeByte(0);
        this.dos.writeUTF(paramString);
    }

    public final void close() {
        try {
            if (this.dos != null) {
                this.dos.close();
                this.dos = null;
            }
            if (this.os != null) {
                this.os.close();
                this.os = null;
            }
            return;
        } catch (Exception exception2) {
            return;
        }
    }

    public final void close(String paramString) {
        try {
            Utlis.write(paramString, this.os.toByteArray());
            if (this.dos != null) {
                this.dos.close();
                this.dos = null;
            }
            if (this.os != null) {
                this.os.close();
                this.os = null;
            }
            return;
        } catch (Exception exception) {
            return;
        }
    }

    public void writeShortArray(int[] an) throws IOException {
        this.writeInt(an.length);
        for (int i = 0; i < an.length; i++) {
            this.writeShort(an[i]);
        }
    }

    public void writeIntArray(int[] an) throws IOException {
        this.writeInt(an.length);
        for (int i = 0; i < an.length; i++) {
            this.writeInt(an[i]);
        }
    }

    public void writeLongArray(long[] an) throws IOException {
        this.writeInt(an.length);
        for (int i = 0; i < an.length; i++) {
            this.writeLong(an[i]);
        }
    }

    public void writeUTFArray(String[] an) throws IOException {
        this.writeInt(an.length);
        for (int i = 0; i < an.length; i++) {
            this.writeUTF(an[i]);
        }
    }
}
