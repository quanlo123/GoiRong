package com.beatdz.data;

public final class SkillClan {

    public int id;
    public String name;
    public String detail;
    public int levelNeed;
    public String strData;
    public int iconChar;
    public int moneyBuy;
    public int h;

//   public final GoiRong_gp[] a() {
//      if (this.strData != null && this.strData.length() > 0) {
//         String[] var1;
//         GoiRong_gp[] var2 = new GoiRong_gp[(var1 = GoiRong_ir.a(this.strData, ";")).length];
//
//         for(int var3 = 0; var3 < var1.length; ++var3) {
//            var2[var3] = new GoiRong_gp(var1[var3]);
//         }
//
//         return var2;
//      } else {
//         return null;
//      }
//   }
//
//   public final String b() {
//      return GoiRong_ir.f((long)this.h * 1000L);
//   }
    @Override
    public String toString() {
        return "SkillClan{" + "id=" + id + ", name=" + name + ", detail=" + detail + ", levelNeed=" + levelNeed + ", strData=" + strData + ", iconChar=" + iconChar + ", moneyBuy=" + moneyBuy + ", h=" + h + '}';
    }

    public void write(com.beatdz.network.Writer writer) {
        try {
            writer.writeUTF(name);
            writer.writeUTF(detail);
            writer.writeByte(levelNeed);
            writer.writeUTF(strData);
            writer.writeShort(iconChar);
            writer.writeInt(moneyBuy);
            writer.writeInt(h);

        } catch (Exception ex) {

        }

    }
}
