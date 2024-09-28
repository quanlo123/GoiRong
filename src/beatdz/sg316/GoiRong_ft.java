package beatdz.sg316;

import com.beatdz.lib.Utlis;
import com.beatdz.real.Entity;
import java.util.Vector;

public class GoiRong_ft {
   public Entity[] b;
   public short c;
   public short d;
   public short e;
   public short f;

   public final GoiRong_ft a() {
      GoiRong_ft var1;
      (var1 = new GoiRong_ft()).c = this.c;
      var1.d = this.d;
      var1.e = this.e;
      var1.f = this.f;
      var1.b = new Entity[this.b.length];

      for(int var2 = 0; var2 < this.b.length; ++var2) {
         var1.b[var2] = new Entity();
         var1.b[var2].updateXY(this.b[var2].x, this.b[var2].y);
      }

      return var1;
   }

   public final Vector a(Entity var1, Entity var2) {
      Vector var4 = new Vector();

      for(int var5 = 0; var5 < this.b.length - 1; ++var5) {
         Entity var3;
         if ((var3 = Utlis.a(var1, var2, this.b[var5], this.b[var5 + 1])) != null) {
            var4.add(var3);
         }
      }

      return var4;
   }

   public final Entity b(Entity var1, Entity var2) {
      for(int var4 = 0; var4 < this.b.length - 1; ++var4) {
         Entity var3;
         if ((var3 = Utlis.a(var1, var2, this.b[var4], this.b[var4 + 1])) != null) {
            return var3;
         }
      }

      return null;
   }

   public final boolean a(int var1, int var2) {
      return a(var1, var2, this.b);
   }

   private static boolean a(int var0, int var1, Entity[] var2) {
      for(int var5 = 0; var5 < var2.length - 1; ++var5) {
         try {
            int[] var3 = new int[]{var2[var5].x, var2[var5].y};
            int[] var4 = new int[]{var2[var5 + 1].x, var2[var5 + 1].y};
            var3 = new int[]{var4[0] - var3[0], var4[1] - var3[1]};
            var4 = new int[]{var4[0] - var0, var4[1] - var1};
            if (var3[0] * var4[1] - var3[1] * var4[0] > 0) {
               return false;
            }
         } catch (Exception var6) {
            return false;
         }
      }

      return true;
   }

   // $FF: synthetic method
   public Object clone() {
      return this.a();
   }
}
