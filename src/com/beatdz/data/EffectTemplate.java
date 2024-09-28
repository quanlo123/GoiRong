package com.beatdz.data;

public final class EffectTemplate {

    public short id;
    public String name;
    public String detail;
    public short type;
    public short idIcon;
    public short f;

    public EffectTemplate(int var1) {
        this.id = (short) var1;
    }

    @Override
    public String toString() {
        return "EffectTemplate{" + "id=" + id + ", name=" + name + ", detail=" + detail + ", type=" + type + ", idIcon=" + idIcon + ", f=" + f + '}';
    }

    
}
