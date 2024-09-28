package beatdz.sg316;

import java.io.IOException;
import java.util.Vector;

public final class GoiRong_gu {

    public short[] a;
    public short[] b;
    public int c = 0;

    public final GoiRong_gu a(int var1) {
        GoiRong_gu var2;
        (var2 = new GoiRong_gu()).a = this.a;
        var2.b = this.b;
        var2.c = var1;
        return var2;
    }

//   public final void a(Vector var1, boolean var2) {
//      try {
//         for(int var3 = this.a.length - 1; var3 >= 0; --var3) {
//            short var10001 = this.a[var3];
//            GoiRong_gs var4 = GoiRong_de.d().z[var10001].a();
//            if (!var2 || (var4.c < 325 || var4.c > 330) && (var4.c < 335 || var4.c > 337)) {
//               var4.e = this.c;
//               var1.add(var4);
//            }
//         }
//
//      } catch (Exception var5) {
//      }
//   }
//
//   public final void a(Vector var1, int var2) {
//      try {
//         if (this.b.length > 0) {
//            short var10001 = this.b[var2 - 1];
//            GoiRong_gs var6 = GoiRong_de.d().z[var10001].a();
//
//            for(int var3 = 0; var3 < var1.size(); ++var3) {
//               if (((GoiRong_gs)var1.get(var3)).c == var6.c) {
//                  return;
//               }
//            }
//
//            var1.add(var6);
//         }
//
//      } catch (Exception var5) {
//      }
//   }
//
//   public final void a() {
//      try {
//         Vector var1 = new Vector();
//
//         int var2;
//         for(var2 = this.a.length - 1; var2 >= 0; --var2) {
//            short var10001 = this.a[var2];
//            GoiRong_gs var3;
//            if ((var3 = GoiRong_de.d().z[var10001]).d != 1 && var3.d != 7 || var3.f != null && var3.f.length > 0 && GoiRong_ee.br.contains(var3.f[0].a)) {
//               var1.add(this.a[var2]);
//            }
//         }
//
//         this.a = new short[var1.size()];
//
//         for(var2 = 0; var2 < var1.size(); ++var2) {
//            this.a[var2] = (Short)var1.get(var2);
//         }
//
//      } catch (Exception var4) {
//      }
//   }
//
//   public final void b() {
//      try {
//         Vector var1 = new Vector();
//
//         int var2;
//         for(var2 = this.a.length - 1; var2 >= 0; --var2) {
//            short var10001 = this.a[var2];
//            GoiRong_gs var3;
//            if ((var3 = GoiRong_de.d().z[var10001]).d != 1 && var3.d != 7) {
//               var1.add(this.a[var2]);
//            }
//         }
//
//         this.a = new short[var1.size()];
//
//         for(var2 = 0; var2 < var1.size(); ++var2) {
//            this.a[var2] = (Short)var1.get(var2);
//         }
//
//      } catch (Exception var4) {
//      }
//   }
//
//   public final void c() {
//      try {
//         Vector var1 = new Vector();
//
//         int var2;
//         for(var2 = this.a.length - 1; var2 >= 0; --var2) {
//            short var10001 = this.a[var2];
//            GoiRong_gs var3;
//            if (((var3 = GoiRong_de.d().z[var10001]).c < 325 || var3.c > 330) && (var3.c < 335 || var3.c > 337) && var3.d != 8) {
//               var1.add(this.a[var2]);
//            }
//         }
//
//         this.a = new short[var1.size()];
//
//         for(var2 = 0; var2 < var1.size(); ++var2) {
//            this.a[var2] = (Short)var1.get(var2);
//         }
//
//      } catch (Exception var4) {
//      }
//   }
//
//    public final boolean d() {
//        try {
//            new Vector();
//            for (int i = this.a.length - 1; i >= 0; --i) {
//                final GoiRong_gs goiRong_gs;
//                if ((goiRong_gs = GoiRong_de.d().z[this.a[i]]).d == 1 || goiRong_gs.d == 7) {
//                    return true;
//                }
//            }
//        }
//        catch (Exception ex) {}
//        return false;
//    }
//    
//
//   public final void e() {
//      try {
//         Vector var1 = new Vector();
//
//         int var2;
//         for(var2 = this.a.length - 1; var2 >= 0; --var2) {
//            short var10001 = this.a[var2];
//            GoiRong_gs var3;
//            if ((var3 = GoiRong_de.d().z[var10001]).d == 1 || var3.d == 7) {
//               var1.add(this.a[var2]);
//            }
//         }
//
//         this.a = new short[var1.size()];
//
//         for(var2 = 0; var2 < var1.size(); ++var2) {
//            this.a[var2] = (Short)var1.get(var2);
//         }
//
//      } catch (Exception var4) {
//      }
//   }
//
//   public final void a(GoiRong_ee var1) {
//      try {
//         Vector var2 = new Vector();
//
//         int var3;
//         for(var3 = this.a.length - 1; var3 >= 0; --var3) {
//            short var10001 = this.a[var3];
//            GoiRong_gs var4;
//            if ((var4 = GoiRong_de.d().z[var10001]).d == 1 || var4.d == 7) {
//               var2.add(this.a[var3]);
//            }
//         }
//
//         if (var2.size() != 0) {
//            this.a = new short[var2.size()];
//
//            for(var3 = 0; var3 < var2.size(); ++var3) {
//               this.a[var3] = (Short)var2.get(var3);
//            }
//
//            return;
//         }
//
//         this.a = new short[]{GoiRong_gt.a(7, var1.Y, false)};
//      } catch (Exception var5) {
//      }
//
//   }
//
//   public final void f() {
//      try {
//         Vector var1 = new Vector();
//
//         int var2;
//         for(var2 = this.a.length - 1; var2 >= 0; --var2) {
//            short var10001 = this.a[var2];
//            if (GoiRong_de.d().z[var10001].d == 1) {
//               var1.add(this.a[var2]);
//            }
//         }
//
//         var1.add((short)238);
//         var1.add((short)239);
//         var1.add((short)241);
//         var1.add((short)242);
//         this.a = new short[var1.size()];
//
//         for(var2 = 0; var2 < var1.size(); ++var2) {
//            this.a[var2] = (Short)var1.get(var2);
//         }
//
//      } catch (Exception var4) {
//      }
//   }
    public void write(com.beatdz.network.Writer writer) throws IOException {
        writer.writeByte(a.length);
        for (int i = 0; i < a.length; i++) {
            writer.writeShort(a[i]);
        }
        writer.writeByte(b.length);
        for (int i = 0; i < b.length; i++) {
            writer.writeShort(b[i]);
        }
    }
}
