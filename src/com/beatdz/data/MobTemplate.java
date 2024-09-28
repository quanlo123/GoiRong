package com.beatdz.data;

public final class MobTemplate {

    public short[][] a;
    //public GoiRong_fb[] b;
    public short id;
    public short d;
    public short e;
    public short f;
    public short g;
    public short h;
    public short i;
    public short j;
    public String name;
    public String detail;
    public int[] m;
    public int[] n;
    public short speedMove;
    public byte p;
    public byte q;
    public byte r;
    //public GoiRong_fc s;
    public String str1;
    public String str2;

    public MobTemplate(int var1) {
        this.id = (short) var1;
    }

//   public final void a(String var1, String var2) {
//      try {
//         String[] var5 = GoiRong_ir.a(var1, ",");
//         this.m = new int[var5.length];
//
//         int var3;
//         for(var3 = 0; var3 < var5.length; ++var3) {
//            this.m[var3] = Integer.parseInt(var5[var3]);
//         }
//
//         var5 = GoiRong_ir.a(var2, ",");
//         this.n = new int[var5.length];
//
//         for(var3 = 0; var3 < var5.length; ++var3) {
//            this.n[var3] = Integer.parseInt(var5[var3]);
//         }
//
//      } catch (Exception var4) {
//      }
//   }
    @Override
    public String toString() {
        return "GoiRong_gd{" + "a=" + a + ", b=" + "" + ", c=" + id + ", d=" + d + ", e=" + e + ", f=" + f + ", g=" + g + ", h=" + h + ", i=" + i + ", j=" + j + ", k=" + name + ", l=" + detail + ", m=" + m + ", n=" + n + ", o=" + speedMove + ", p=" + p + ", q=" + q + ", r=" + r + ", s=" + "" + '}';
    }

    public void write(com.beatdz.network.Writer writer) {
        try {
            writer.writeShort(h);
            writer.writeUTF(name);
            writer.writeUTF(detail);
            writer.writeShort(speedMove);
            writer.writeByte(p);
            writer.writeByte(q);
            writer.writeByte(r);
            writer.writeShort(f);
            writer.writeShort(i);
            writer.writeUTF(str1);
            writer.writeUTF(str2);
        } catch (Exception ex) {

        }

    }
}
