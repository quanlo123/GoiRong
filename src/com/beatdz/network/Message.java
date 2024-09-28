package com.beatdz.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Message {

    public byte cmd;
    public ByteArrayOutputStream os;
    public Writer dos;
    public ByteArrayInputStream is;
    public Reader dis;
    public boolean isNen = false;
    public int size = 0;

    public Message(int command) {
        this((byte) command);
    }

    public Message(byte command) {
        this.cmd = command;
        os = new ByteArrayOutputStream();
        dos = new Writer(new DataOutputStream(os));
    }

    public Message(byte command, byte[] data) {
        this.cmd = command;
        is = new ByteArrayInputStream(data);
        dis = new Reader(is);
        this.size = data.length;

    }

    public byte[] getData() {
        this.cleanup();
        return os.toByteArray();
    }

    public Reader reader() {
        return dis;
    }

    public Writer writer() {
        return dos;
    }

    public final boolean readBoolean() throws java.io.IOException {
        return this.dis.dis.readBoolean();
    }

    public final byte readByte() throws java.io.IOException {
        return this.dis.dis.readByte();
    }

    public final byte[] read() throws java.io.IOException {
        return this.dis.read();
    }

    public final short readShort() throws java.io.IOException {
        return this.dis.dis.readShort();
    }

    public final int readUnsignedShort() throws java.io.IOException {
        return this.dis.dis.readUnsignedShort();
    }

    public final long readLong() throws java.io.IOException {
        return this.dis.dis.readLong();
    }

    public final int readInt() throws java.io.IOException {
        return this.dis.dis.readInt();
    }

    public final String readUTF() throws java.io.IOException {
        return this.dis.readUTF();
    }

    public final void writeBoolean(boolean var1) throws java.io.IOException {
        this.dos.dos.writeBoolean(var1);
    }

    public final void writeByte(int var1) throws java.io.IOException {
        this.dos.dos.writeByte(var1);
    }

    public final void write(byte[] var1) throws java.io.IOException {
        dos.dos.writeInt(var1.length);
        dos.dos.write(var1);
    }

    public final void writeShort(int var1) throws java.io.IOException {
        this.dos.dos.writeShort(var1);
    }

    public final void writeInt(int var1) throws java.io.IOException {
        this.dos.dos.writeInt(var1);
    }

    public final void writeLong(long var1) throws java.io.IOException {
        this.dos.dos.writeLong(var1);
    }

    public final void writeUTF(String var1) throws java.io.IOException {
        this.dos.writeUTF(var1);
    }

    public void cleanup() {
        try {
            if (dis != null) {
                dis.close();
            }
            if (dos != null) {
                dos.close();
            }
        } catch (Exception e) {
        }
    }


}
