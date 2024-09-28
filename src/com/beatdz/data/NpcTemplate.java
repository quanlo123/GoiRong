package com.beatdz.data;

public final class NpcTemplate {
   public short[][] a;
   //public GoiRong_fb[] b;
   public short id;
   public short d;
   public short e;
   public short f;
   public short g;
   public short h;
   public short i;
   public String name;
   public String detail;
   public int l;
   public int m;

   public NpcTemplate(int var1) {
      this.id = (short)var1;
   }
   

    @Override
    public String toString() {
        return "GoiRong_go{" + "a=" + a + ", b=" + "" + ", c=" + id + ", d=" + d + ", e=" + e + ", f=" + f + ", g=" + g + ", h=" + h + ", i=" + i + ", j=" + name + ", k=" + detail + ", l=" + l + ", m=" + m + '}';
    }public void write(com.beatdz.network.Writer writer) {
       try
       {
           writer.writeUTF
(name);
writer.writeUTF
(detail);
writer.writeShort
(f);
writer.writeInt
(l);
writer.writeInt
(m);
writer.writeShort(i);

       }
       catch(Exception ex)
       {
           
       }
        
    }
}
