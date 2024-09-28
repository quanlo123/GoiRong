package com.beatdz.data;

public final class DataTypeItemBody {

    public int type;

    @Override
    public String toString() {
        return "GoiRong_fp{" + "type=" + type + '}';
    }

    public void write(com.beatdz.network.Writer writer) {
        try {
            writer.writeByte(type);
        } catch (Exception ex) {

        }

    }
}
