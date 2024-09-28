package beatdz.sg316;

import com.beatdz.data.MapTemplate;
import com.beatdz.lib.Utlis;
import com.beatdz.real.Entity;
import java.util.Vector;

public final class GoiRong_fh {
   public MapTemplate map;
   public GoiRong_ft[] a;

   public GoiRong_fh(MapTemplate map,GoiRong_ft[] var1) {
       this.map = map;
      this.a = var1;
   }

   public final boolean a(int var1, int var2) {
      if (this.a != null) {
         for(int var3 = 0; var3 < this.a.length; ++var3) {
            if (this.a[var3].a(var1, var2)) {
               return true;
            }
         }
      }

      return false;
   }

   public final Entity b(int var1, int var2) {
      if (!this.a(var1, var2)) {
         return Entity.create(var1, var2);
      } else {
         for(int var3 = 1; var3 < 1000; ++var3) {
            if (!this.a(var1 + var3, var2)) {
               return Entity.create(var1 + var3, var2);
            }

            if (!this.a(var1, var2 + var3)) {
               return Entity.create(var1, var2 + var3);
            }

            if (!this.a(var1 - var3, var2)) {
               return Entity.create(var1 - var3, var2);
            }

            if (!this.a(var1, var2 - var3)) {
               return Entity.create(var1, var2 - var3);
            }

            if (!this.a(var1 + var3, var2 + var3)) {
               return Entity.create(var1 + var3, var2 + var3);
            }

            if (!this.a(var1 - var3, var2 + var3)) {
               return Entity.create(var1 - var3, var2 + var3);
            }

            if (!this.a(var1 + var3, var2 - var3)) {
               return Entity.create(var1 + var3, var2 - var3);
            }

            if (!this.a(var1 - var3, var2 - var3)) {
               return Entity.create(var1 - var3, var2 - var3);
            }
         }

         return Entity.create(var1, var2);
      }
   }

   public final Entity c(int var1, int var2) {
      try {
         Entity var8;
         Entity var9 = Entity.create((var8 = Entity.create(var1, var2)).x, map.K + 10);
         Vector var3 = new Vector();
         int var4;
         if (this.a != null) {
            for(var4 = 0; var4 < this.a.length; ++var4) {
               var3.addAll(this.a[var4].a(var8, var9));
            }
         }

         for(var4 = 0; var4 < map.w.size(); ++var4) {
            for(int var5 = 0; var5 < ((GoiRong_ft)map.w.get(var4)).b.length - 1; ++var5) {
               Entity var6;
               if ((var6 = Utlis.a(var8, var9, ((GoiRong_ft)map.w.get(var4)).b[var5], ((GoiRong_ft)map.w.get(var4)).b[var5 + 1])) != null) {
                  var3.add(var6);
               }
            }
         }

         if (var3.size() > 0) {
            var4 = 0;
            Entity var10 = null;

            for(int var11 = 0; var11 < var3.size(); ++var11) {
               var9 = (Entity)var3.get(var11);
               if (var11 == 0 || Utlis.getRange(var9.y - var8.y) < var4) {
                  var10 = var9;
                  var4 = Utlis.getRange(var9.y - var8.y);
               }
            }

            return var10;
         }
      } catch (Exception var7) {
      }

      return null;
   }
}
