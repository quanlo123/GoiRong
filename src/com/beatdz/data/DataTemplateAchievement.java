package com.beatdz.data;

import com.beatdz.network.Writer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DataTemplateAchievement {

    public int a;
    public String b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public String h;

//   public final GoiRong_fk a() {
//      try {
//         if (this.h != null && this.h.length() > 0) {
//            String[] var1 = this.h.split("@");
//            GoiRong_fk var2;
//            (var2 = new GoiRong_fk()).c = Short.parseShort(var1[0]);
//            var2.t = Boolean.parseBoolean(var1[1]);
//            var2.h = Long.parseLong(var1[2]);
//            var2.c(Integer.parseInt(var1[3]));
//            var2.g = Byte.parseByte(var1[4]);
//            var2.s = Byte.parseByte(var1[5]);
//            if (var1.length > 6) {
//               var2.i = var1[6];
//               if (var2.h().gioiTinh != 2 && var2.h().gioiTinh != GoiRong_ee.e().X) {
//                  for(int var3 = 0; var3 < GoiRong_de.d().P.length; ++var3) {
//                     ItemTemplate var5;
//                     if ((var5 = GoiRong_de.d().P[var3]).gioiTinh == GoiRong_ee.e().X && var5.type == var2.h().type && var5.levelNeed == var2.h().levelNeed) {
//                        var2.c = var5.id;
//                        break;
//                     }
//                  }
//               }
//
//               if (var2.h().type == 13) {
//                  var2.g = GoiRong_ee.e().Z;
//                  switch(var2.g) {
//                  case 1:
//                     var2.i = "54,0,500;62,0,500";
//                     break;
//                  case 2:
//                     var2.i = "55,0,500;58,0,500";
//                     break;
//                  case 3:
//                     var2.i = "56,0,500;59,0,500";
//                     break;
//                  case 4:
//                     var2.i = "57,0,500;60,0,500";
//                     break;
//                  case 5:
//                     var2.i = "53,0,500;61,0,500";
//                  }
//               }
//            }
//
//            return var2;
//         }
//      } catch (Exception var4) {
//         GoiRong_ir.a(var4);
//      }
//
//      return null;
//   }
    @Override
    public String toString() {
        return "DataTemplateAchievement{" + "a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + ", e=" + e + ", f=" + f + ", g=" + g + ", h=" + h + '}';
    }

    public void write(Writer writer) {
        try {
            writer.writeByte(a);
            writer.writeUTF(b);
            writer.writeInt(c);
            writer.writeInt(d);
            writer.writeInt(e);
            writer.writeInt(f);
            writer.writeInt(g);
            writer.writeUTF(h);
        } catch (IOException ex) {
        }

    }
}
