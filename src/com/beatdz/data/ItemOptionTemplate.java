package com.beatdz.data;

public final class ItemOptionTemplate {

    public short id;
    public String name;
    public byte type;
    public byte d;
    public String strData;

    public ItemOptionTemplate(int var1) {
        this.id = (short) var1;
    }

    public final int[] a() {
        if (this.strData.length() == 0) {
            return new int[0];
        } else {
            String[] var1;
            int[] var2 = new int[(var1 = this.strData.split(";")).length];

            for (int var3 = 0; var3 < var2.length; ++var3) {
                var2[var3] = Integer.parseInt(var1[var3]);
            }

            return var2;
        }
    }

   
    @Override
    public String toString() {
        return "ItemOptionTemplate{" + "id=" + id + ", name=" + name + ", type=" + type + ", d=" + d + ", strData=" + strData + '}';
    }
}
