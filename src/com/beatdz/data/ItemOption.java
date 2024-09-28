package com.beatdz.data;

import com.beatdz.lib.Utlis;
import com.beatdz.server.DataCenter;
import java.util.Comparator;

public final class ItemOption {

    public static int STAR_HEAL_HP_HALF = 347;
    public static int STAR_HEAL_MP_HALF = 348;
    public static int KHAM_SAO = 349;
    public static int KHAM_NGOC = 350;

    public static ItemOption create(int type, int num) {
        ItemOption itemOption = new ItemOption();
        itemOption.b = new int[]{type, num};

        return itemOption;

    }

    public static ItemOption create(int type, int num, int num2) {
        ItemOption itemOption = new ItemOption();
        itemOption.b = new int[]{type, num,num2};

        return itemOption;

    }

    public static final Comparator a = new Comparator() {
        @Override
        public final int compare(Object var1, Object var2) {
            ItemOption var10000 = (ItemOption) var1;
            ItemOption var4 = (ItemOption) var2;
            ItemOption var3 = var10000;
            return var4.a().name.compareTo(var3.a().name);
        }
    };
    public int[] b;

    public ItemOption(String var1) {
        String[] var3 = Utlis.split(var1, ",");
        this.b = new int[var3.length];

        for (int var2 = 0; var2 < var3.length; ++var2) {
            this.b[var2] = Integer.parseInt(var3[var2]);
        }

    }

    public ItemOption() {

    }

    public final ItemOptionTemplate a() {
        return DataCenter.gI().Q[this.b[0]];
    }

    private String[] o() {
        if (this.a().id != 344 && this.a().id != 369 && this.a().id != 375 && this.a().id != 267 && this.a().id != 343 && this.a().id != 368 && this.a().id != 412) {
            return null;
        } else {
            String var1 = "" + (float) this.b[1] / 10.0F;
            if (this.b[1] % 10 == 0) {
                var1 = "" + this.b[1] / 10;
            }

            String var2 = "" + (float) this.h() / 10.0F;
            if (this.h() % 10 == 0) {
                var2 = "" + this.h() / 10;
            }

            return new String[]{var1, var2};
        }
    }

    public final String a(int var1) {
        if (this.b[0] == 310) {
            return Utlis.replace(DataCenter.gI().Q[this.b[0]].name, DataCenter.gI().R[this.b[1]].name);
        } else if (this.a().type == 54) {
            return Utlis.replace(DataCenter.gI().Q[this.b[0]].name, Utlis.j(this.b[1]));
        } else if (this.a().type == 52) {
            String var5 = Utlis.replace(DataCenter.gI().Q[this.b[0]].name, "" + this.b[1]);

            for (int var3 = 0; var3 < DataCenter.gI().R.length; ++var3) {
                if (DataCenter.gI().R[var3].e == var1 && DataCenter.gI().R[var3].d == this.a().d) {
                    String var4 = DataCenter.gI().R[var3].name;
                    return var5.replaceAll("@", var4);
                }
            }

            return null;
        } else if (this.a().id == 98) {
            return Utlis.replace(DataCenter.gI().Q[this.b[0]].name, "" + (float) this.b[1] / 100.0F);
        } else {
            String[] var2;
            return (var2 = this.o()) != null ? Utlis.replace(DataCenter.gI().Q[this.b[0]].name, var2[0]) : Utlis.replace(DataCenter.gI().Q[this.b[0]].name, "" + this.b[1]);
        }
    }

//    public final String b() {
//        if (this.b[1] == -1) {
//            return Caption.wR;
//        } else {
//            return this.h() == -1 ? GoiRong_ir.b(DataCenter.gI().Q[this.b[0]].name, DataCenter.gI().R[this.b[1]].name + Caption.wS) : GoiRong_ir.b(DataCenter.gI().Q[this.b[0]].name, DataCenter.gI().R[this.b[1]].name + Caption.io + this.h());
//        }
//    }
//
//    public final String c() {
//        String[] var1;
//        if ((var1 = this.o()) != null) {
//            return this.b[1] >= 0 && this.h() >= 0 && this.b[1] != this.h() && this.b[0] == 412 ? GoiRong_ir.b(DataCenter.gI().Q[this.b[0]].name, "(" + var1[0] + "-" + var1[1] + ")") : GoiRong_ir.b(DataCenter.gI().Q[this.b[0]].name, var1[0]);
//        } else if (this.b[0] == 436 && this.b[1] > 0) {
//            return GoiRong_ir.b(DataCenter.gI().Q[this.b[0]].name, DataCenter.gI().P[this.b[1]].name.replaceAll(Caption.bb, "").trim());
//        } else if (this.b[0] == 427 && this.b[1] > 0) {
//            return Caption.xk + " " + DataCenter.gI().P[this.b[1]].name;
//        } else if (this.a().type == 55) {
//            return GoiRong_ir.b(DataCenter.gI().Q[this.b[0]].name, DataCenter.gI().R[this.b[1]].name);
//        } else if (this.b[1] >= 0 && this.h() >= 0 && this.b[1] != this.h()) {
//            return GoiRong_ir.b(DataCenter.gI().Q[this.b[0]].name, "(" + this.b[1] + "-" + this.h() + ")");
//        } else if (this.b[1] >= 0) {
//            return GoiRong_ir.b(DataCenter.gI().Q[this.b[0]].name, "" + this.b[1]);
//        } else {
//            return this.h() >= 0 ? GoiRong_ir.b(DataCenter.gI().Q[this.b[0]].name, "" + this.h()) : "";
//        }
//    }
//
//    public final String a(byte var1) {
//        String[] var2;
//        if (this.b[1] >= 0 && this.h() >= 0 && this.b[1] != this.h()) {
//            return (var2 = this.o()) != null ? GoiRong_ir.b(DataCenter.gI().Q[this.b[0]].name, "(" + var2[0] + "-" + var2[1] + ")") : GoiRong_ir.b(DataCenter.gI().Q[this.b[0]].name, "(" + this.b[1] + "-" + this.h() + ")");
//        } else if (this.b[1] >= 0) {
//            GoiRong_fk var3;
//            ItemOption var4;
//            String var5;
//            if ((var2 = this.o()) != null) {
//                var5 = GoiRong_ir.b(DataCenter.gI().Q[this.b[0]].name, var2[0]);
//                if (var1 > 0) {
//                    (var3 = new GoiRong_fk(314)).s = var1;
//                    var3.i = this.i();
//                    var3.a(0);
//                    if ((var4 = var3.K()[0]).b[1] % 10 == 0) {
//                        var5 = var5 + " (" + Caption.tJ + " +" + var4.b[1] / 10 + "%)";
//                    } else {
//                        var5 = var5 + " (" + Caption.tJ + " +" + (float) var4.b[1] / 10.0F + "%)";
//                    }
//                }
//            } else {
//                var5 = GoiRong_ir.b(DataCenter.gI().Q[this.b[0]].name, "" + this.b[1]);
//                if (var1 > 0) {
//                    (var3 = new GoiRong_fk(314)).s = var1;
//                    var3.i = this.i();
//                    var3.a(0);
//                    var4 = var3.K()[0];
//                    var5 = var5 + " (" + Caption.tJ + " +" + var4.b[1] + ")";
//                }
//            }
//
//            return var5;
//        } else {
//            return this.h() >= 0 ? GoiRong_ir.b(DataCenter.gI().Q[this.b[0]].name, "" + this.h()) : "";
//        }
//    }
//
//    public final String d() {
//        return GoiRong_ir.b(DataCenter.gI().Q[this.b[0]].name, GoiRong_ir.c(this.b[1]) + " / " + DataCenter.gI().a(this.h()));
//    }
//
//    public final String e() {
//        return GoiRong_ir.b(DataCenter.gI().Q[this.b[0]].name, GoiRong_ir.c(this.b[1])).replaceAll("@", GoiRong_ir.c(this.h()));
//    }
//
//    public final String b(int var1) {
//        var1 = var1;
//        if (this.b.length > 3) {
//            var1 += this.b[3];
//        }
//
//        if ((var1 = this.b[1] + var1 / 10) > this.h()) {
//            var1 = this.h();
//        }
//
//        return GoiRong_ir.b(DataCenter.gI().Q[this.b[0]].name, "" + var1).replaceAll("@", "" + this.h());
//    }
    public final int f() {
        return this.b[0];
    }

    public final int g() {
        return this.b[1];
    }

    public final int c(int var1) {
        return this.b[1] = var1;
    }

    public final int d(int var1) {
        int[] var10000 = this.b;
        return var10000[1] += var1;
    }

    public final int e(int var1) {
        return this.b[2] = var1;
    }

    public final int h() {
        return this.b.length < 3 ? -1 : this.b[2];
    }

    public final String i() {
        String var1 = "";

        for (int var2 = 0; var2 < this.b.length; ++var2) {
            var1 = var1 + this.b[var2];
            if (var2 < this.b.length - 1) {
                var1 = var1 + ",";
            }
        }

        return var1;
    }

    public final boolean a(boolean var1) {
        try {
            return this.b[3] >= (var1 ? 17 : 16);
        } catch (Exception var2) {
            return false;
        }
    }

    public static int f(int var0) {
        switch (var0) {
            case 406:
                return 199;
            case 407:
                return 200;
            case 408:
                return 201;
            case 409:
                return 202;
            case 410:
                return 203;
            case 411:
                return 204;
            case 412:
                return 205;
            case 413:
                return 206;
            default:
                return -1;
        }
    }

    public final int j() {
        switch (this.a().type) {
            case 3:
                return 793;
            case 4:
                return 795;
            case 5:
                return 797;
            case 6:
                return 798;
            case 7:
            case 8:
            case 9:
            case 10:
            case 13:
            case 14:
            case 15:
            case 16:
            case 18:
            default:
                switch (this.b[0]) {
                    case 199:
                        return 406;
                    case 200:
                        return 407;
                    case 201:
                        return 408;
                    case 202:
                        return 409;
                    case 203:
                        return 410;
                    case 204:
                        return 411;
                    case 205:
                        return 412;
                    case 206:
                        return 413;
                    default:
                        return -1;
                }
            case 11:
                return 794;
            case 12:
                return 796;
            case 17:
                return 791;
            case 19:
                return 792;
        }
    }

    public final int k() {
        try {
            return this.b[3];
        } catch (Exception var1) {
            return 0;
        }
    }

    public static ItemOption g(int var0) {
        return new ItemOption(f(var0) + ",0,-1,0");
    }

    public final boolean l() {
        return this.a().type == 19 || this.a().type == 3 || this.a().type == 11 || this.a().type == 4 || this.a().type == 12 || this.a().type == 5 || this.a().type == 6 || this.a().type == 7 || this.a().type == 10;
    }

    public final boolean m() {
        return this.a().id == 349 || this.a().id == 350 || this.a().id == 298;
    }

    public final boolean n() {
        return this.a().type == 8 || this.l() || this.a().type == 17;
    }
}
