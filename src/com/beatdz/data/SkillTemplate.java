package com.beatdz.data;

public final class SkillTemplate {

    public short id;
    public String name;
    public String detail;
    public short d;
    public byte e;
    public byte levelMax;
    public byte g;
    public short h;
    public short i;

    public SkillTemplate(int var1) {
        this.id = (short) var1;
    }

    @Override
    public String toString() {
        return "GoiRong_hz{" + "id=" + id + ", name=" + name + ", detail=" + detail + ", d=" + d + ", e=" + e + ", levelMax=" + levelMax + ", g=" + g + ", h=" + h + ", i=" + i + '}';
    }

    public void write(com.beatdz.network.Writer writer) {
        try {
            writer.writeUTF(name);
            writer.writeUTF(detail);
            writer.writeShort(d);
            writer.writeByte(e);
            writer.writeByte(levelMax);
            writer.writeByte(g);
            writer.writeShort(h);
            writer.writeShort(i);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
