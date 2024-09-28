package com.beatdz.data;

public final class ItemTemplate {

    public short id;
    public String name;
    public String detail;
    public boolean isXepChong;
    public byte gioiTinh;
    public byte type;
    public byte typeChar;
    public short idIcon;
    public short levelNeed;
    public int phamChatNeed;
    public short k;
    public short l;

    public ItemTemplate(int var1) {
        this.id = (short) var1;
    }

    public ItemTemplate() {
    }

//   public final short a() {
//      return GoiRong_de.l() ? 0 : this.l;
//   }
//
//   public final short b() {
//      return GoiRong_de.l() ? this.l : this.k;
//   }
    @Override
    public String toString() {
        return "ItemTemplate{" + "id=" + id + ", name=" + name + ", detail=" + detail + ", isXepChong=" + isXepChong + ", gioiTinh=" + gioiTinh + ", type=" + type + ", typeChar=" + typeChar + ", idIcon=" + idIcon + ", levelNeed=" + levelNeed + ", phamChatNeed=" + phamChatNeed + ", k=" + k + ", l=" + l + '}';
    }

   
}
