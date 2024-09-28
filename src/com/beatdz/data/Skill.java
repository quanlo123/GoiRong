package com.beatdz.data;

import com.beatdz.lib.Utlis;
import com.beatdz.server.DataCenter;
import java.util.Comparator;

public final class Skill implements Cloneable {

    public static final Comparator a = new Comparator() {
        @Override
        public final int compare(Object var1, Object var2) {
            Skill var10000 = (Skill) var1;
            Skill var4 = (Skill) var2;
            Skill var3 = var10000;
            if (var10000.b().i == var4.b().i) {
                return var3.b().d == var4.b().d ? var3.b().id - var4.b().id : var3.b().d - var4.b().d;
            } else {
                return var3.b().i - var4.b().i;
            }
        }
    };
    public int index;
    public short id;
    public short isSkillTemplate;
    public byte level;
    public short levelNeed;
    public short mpUsing;
    public int coolDown;
    public short rangeDoc;
    public short rangeNgang;
    public byte maxTarget;
    public long kiUpgrade;
    public String m;
    public long timeCoolDown;
    public boolean o;
    public boolean p;

    public final Skill a() {
        try {
            return (Skill) super.clone();
        } catch (Exception var2) {
            return null;
        }
    }

    public final SkillTemplate b() {
        return null;//GoiRong_de.d().R[this.isSkillTemplate];
    }

    public final boolean c() {
        return this.b().g == 5;
    }

    public final boolean d() {
        return this.b().g < 5 || this.b().g == 6;
    }

    public final boolean e() {
        return this.b().g == 1;
    }

//    public final boolean a(GoiRong_ee var1, boolean var2) {
//        if (var1.k()) {
//            return this.timeCoolDown < GoiRong_ir.a() && var1.K >= this.mpUsing;
//        } else {
//            return true;
//        }
//    }
//
    public final void f() {
        this.timeCoolDown = Utlis.a() + (long) this.coolDown - 50;

    }

    public final boolean isEndCoolDown() {
        return this.timeCoolDown <= Utlis.a();

    }
//
//    public final float g() {
//        return (float) (this.timeCoolDown - GoiRong_ir.a());
//    }

    public final ItemOption[] getItemOption() {
        if (this.m != null && this.m.length() > 0) {
            String[] var1;
            ItemOption[] var2 = new ItemOption[(var1 = Utlis.split(this.m, ";")).length];

            for (int var3 = 0; var3 < var1.length; ++var3) {
                var2[var3] = new ItemOption(var1[var3]);
            }

            return var2;
        } else {
            return null;
        }
    }

    public final boolean i() {
        return this.isSkillTemplate == 11 || this.isSkillTemplate == 9;
    }

    public final boolean j() {
        return this.isSkillTemplate == 5;
    }

//    public final short a(GoiRong_ee var1) {
//        if (this.b().id >= 30 && this.b().id <= 36) {
//            int var10001 = var1.Y - 1;
//            return (short) (this.b().getItemOption + var10001 * 7);
//        } else if (this.b().id != 8 && this.b().id != 3 && this.b().id != 15 && this.b().id != 21) {
//            return this.b().getItemOption;
//        } else {
//            return this.level > 0 ? (short) (this.b().getItemOption + this.level - 1) : (short) (this.b().getItemOption + this.level);
//        }
//    }
    // $FF: synthetic method
    public final Object clone() {
        return this.a();
    }

    @Override
    public String toString() {
        return "Skill{" + "index=" + index + ", id=" + id + ", isSkillTemplate=" + isSkillTemplate + ", level=" + level + ", levelNeed=" + levelNeed + ", mpUsing=" + mpUsing + ", coolDown=" + coolDown + ", rangeDoc=" + rangeDoc + ", rangeNgang=" + rangeNgang + ", maxTarget=" + maxTarget + ", kiUpgrade=" + kiUpgrade + ", m=" + m + ", n=" + timeCoolDown + ", o=" + o + ", p=" + p + '}';
    }

    public void write(com.beatdz.network.Writer writer) {
        try {
            writer.writeShort(id);
            writer.writeShort(isSkillTemplate);
            writer.writeByte(level);
            writer.writeByte(levelNeed);
            writer.writeShort(mpUsing);
            writer.writeInt(coolDown);
            writer.writeShort(rangeDoc);
            writer.writeShort(rangeNgang);
            writer.writeByte(maxTarget);
            writer.writeLong(kiUpgrade);
            writer.writeUTF(m);
        } catch (Exception ex) {

        }

    }

    public static Skill findSkill(int isSkillTemplate, int level) {
        for (int i = 0; i < DataCenter.gI().U.length; i++) {
            if (DataCenter.gI().U[i].isSkillTemplate == isSkillTemplate && DataCenter.gI().U[i].level == level) {
                return DataCenter.gI().U[i].a();
            }
        }
        return null;
    }

    public static Skill findSkill(Skill skill, int level) {
        for (int i = 0; i < DataCenter.gI().U.length; i++) {
            if (DataCenter.gI().U[i].isSkillTemplate == skill.isSkillTemplate && DataCenter.gI().U[i].level == level) {
                return DataCenter.gI().U[i].a();
            }
        }
        return null;
    }
}
