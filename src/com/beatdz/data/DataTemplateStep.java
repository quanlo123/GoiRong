package com.beatdz.data;

public final class DataTemplateStep {

    public int id;
    public String b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public String j;
    public String k;

    public final boolean a() {
        return this.id == 1 || this.id == 29 || this.id == 2 || this.id == 12 || this.id == 10 || this.id == 49;
    }

    public final int b() {
        try {
            if (this.id == 12 || this.id == 10) {
                return Integer.parseInt(this.k);
            }
        } catch (Exception var2) {

        }

        return this.c;
    }

    @Override
    public String toString() {
        return "DataTemplateStep{" + "a=" + id + ", b=" + b + ", c=" + c + ", d=" + d + ", e=" + e + ", f=" + f + ", g=" + g + ", h=" + h + ", i=" + i + ", j=" + j + ", k=" + k + '}';
    }

    public void write(com.beatdz.network.Writer writer) {
        try {
            writer.writeByte(id);
            writer.writeUTF(b);
            writer.writeShort(c);
            writer.writeShort(d);
            writer.writeShort(e);
            writer.writeShort(f);
            writer.writeShort(g);
            writer.writeShort(h);
            writer.writeShort(i);
            writer.writeUTF(j);
            writer.writeUTF(k);

        } catch (Exception ex) {
        }

    }
}
