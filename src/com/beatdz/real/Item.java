package com.beatdz.real;

import com.beatdz.data.ItemOption;
import com.beatdz.data.ItemTemplate;
import com.beatdz.lib.Utlis;
import com.beatdz.network.Message;
import com.beatdz.server.DataCenter;

import java.io.IOException;
import java.util.Comparator;
import java.util.Vector;

public final class Item implements Cloneable {

    public static final Comparator a = new Comparator() {
        @Override
        public final int compare(Object var1, Object var2) {
            Item var10000 = (Item) var1;
            Item var4 = (Item) var2;
            Item var3 = var10000;
            if (var4.getItemTemplate().levelNeed == var3.getItemTemplate().levelNeed) {
                if (var4.isLock == var3.isLock) {
                    return var3.index - var4.index;
                } else {
                    return var3.isLock ? -1 : 1;
                }
            } else {
                return var4.getItemTemplate().levelNeed - var3.getItemTemplate().levelNeed;
            }
        }
    };
    public int idBuy;
    public int id;
    public int amount;
    public int countBuy = -1;
    public int index;
    public byte he;
    public long expiry = -1L;
    public String strData = "";
    public String nameCreate = "";
    public int ballzBuy;
    public int xuBuy;
    public int zeniBuy;
    public int zeniKhoaBuy;
    public int moneyGiaTocBuy;
    public int kimCuongBuy;
    public int newMoney;
    public int countNeedBuy;
    public byte level;
    public boolean isLock;
    public int typeItem;

    public final Item a() {
        try {
            Item var1;
            var1 = (Item) super.clone();
            return var1;
        } catch (Exception var2) {
            return null;
        }
    }

    public Item() {
    }

    public Item(int var1) {
        this.id = var1;
    }

    public Item(int var1, boolean var2) {
        this.id = (short) var1;
        this.isLock = var2;
    }

    public Item(int var1, boolean var2, ItemOption... array) {
        this.id = (short) var1;
        this.isLock = var2;
        this.strData = a(array);
    }

    public Item(int var1, boolean var2, int var3) {
        this.id = (short) var1;
        this.isLock = true;
        this.amount = var3;
    }

    //    public final void a(GoiRong_l var1) {
//        if (this.id == 160) {
//            this.isLock = true;
//        }
//
//        if (GoiRong_ee.e().indexTypeChar == 1) {
//            if (this.id == 830) {
//                this.getItemTemplate().idIcon = 772;
//            } else if (this.id == 831) {
//                this.getItemTemplate().idIcon = 329;
//            } else if (this.id == 832) {
//                this.getItemTemplate().idIcon = 798;
//            } else if (this.id == 835) {
//                this.getItemTemplate().idIcon = 771;
//            }
//        } else if (GoiRong_ee.e().indexTypeChar == 2) {
//            if (this.id == 830) {
//                this.getItemTemplate().idIcon = 779;
//            } else if (this.id == 831) {
//                this.getItemTemplate().idIcon = 330;
//            } else if (this.id == 832) {
//                this.getItemTemplate().idIcon = 804;
//            } else if (this.id == 835) {
//                this.getItemTemplate().idIcon = 778;
//            }
//        } else if (GoiRong_ee.e().indexTypeChar == 3) {
//            if (this.id == 830) {
//                this.getItemTemplate().idIcon = 786;
//            } else if (this.id == 831) {
//                this.getItemTemplate().idIcon = 340;
//            } else if (this.id == 832) {
//                this.getItemTemplate().idIcon = 810;
//            } else if (this.id == 835) {
//                this.getItemTemplate().idIcon = 785;
//            }
//        } else if (GoiRong_ee.e().indexTypeChar == 4) {
//            if (this.id == 830) {
//                this.getItemTemplate().idIcon = 793;
//            } else if (this.id == 831) {
//                this.getItemTemplate().idIcon = 327;
//            } else if (this.id == 832) {
//                this.getItemTemplate().idIcon = 816;
//            } else if (this.id == 835) {
//                this.getItemTemplate().idIcon = 792;
//            }
//        }
//
//        short var2 = this.getItemTemplate().idIcon;
//        if (this.strData != null && this.strData.startsWith("436,")) {
//            var2 = GoiRong_de.d().P[this.K()[0].b[1]].idIcon;
//            GoiRong_v.a(var1, var2, 0, 0, 0, 80, (byte) 3);
//        } else {
//            if (this.u != null) {
//                this.u.b();
//                this.u.a(var1);
//            }
//
//            if (this.getItemTemplate() != null) {
//                if (this.id == 655 && GoiRong_de.d().i % 100 == GoiRong_ir.a(0, 20)) {
//                    GoiRong_cx.a(var1, var2, 0, GoiRong_ir.a(-1, 1), GoiRong_ir.a(-1, 1), 3);
//                } else if (this.id == 9) {
//                    GoiRong_cx.a(var1, var2 + GoiRong_de.d().i / 7 % 4, 0, 0, 0, 3);
//                } else if (this.getItemTemplate().type == 10) {
//                    int[] var3 = new int[]{-2, -159, -5, -20, 0, 25, 70, 71, -112, -107, -71, -60};
//                    var1.d((float) var3[this.getItemTemplate().levelNeed - 10]);
//                    GoiRong_cx.a(var1, var2, 0, 0, 0, 3);
//                    var1.q();
//                } else {
//                    GoiRong_cx.a(var1, var2, 0, 0, 0, 3);
//                }
//            }
//
//            this.al();
//            if (this.x != null) {
//                this.x.b();
//                this.x.a(var1);
//            }
//
//        }
//    }
//
//    private void al() {
//        if (this.x == null) {
//            this.x = new GoiRong_ep(0, 0, 0, -1);
//            this.u = new GoiRong_ep(0, 0, 0, -1);
//            if (this.level >= 4) {
//                this.x.f = 365;
//                this.u.f = 366;
//                if (this.level >= 4 && this.level <= 5) {
//                    this.x.l = 0;
//                    this.u.l = 0;
//                } else if (this.level >= 6 && this.level <= 7) {
//                    this.x.l = 25;
//                    this.u.l = 25;
//                } else if (this.level >= 8 && this.level <= 9) {
//                    this.x.l = -112;
//                    this.u.l = -112;
//                } else if (this.level >= 10 && this.level <= 11) {
//                    this.x.l = -107;
//                    this.u.l = -107;
//                } else if (this.level >= 12 && this.level <= 13) {
//                    this.x.l = -160;
//                    this.u.l = -160;
//                } else if (this.level >= 14 && this.level <= 15) {
//                    if (this.strData.contains(";370,")) {
//                        this.x.l = -71;
//                        this.u.l = -160;
//                    } else {
//                        this.x.l = -71;
//                        this.u.l = -71;
//                    }
//                } else if (this.level == 16) {
//                    this.x.l = -71;
//                    this.u.l = 71;
//                } else if (this.level >= 17) {
//                    this.x.l = 70;
//                    this.u.l = 70;
//                }
//            }
//
//            int var1 = GoiRong_ir.a((int) 36);
//
//            int var2;
//            for (var2 = 0; var2 < var1; ++var2) {
//                this.x.b();
//            }
//
//            var1 = GoiRong_ir.a((int) 36);
//
//            for (var2 = 0; var2 < var1; ++var2) {
//                this.u.b();
//            }
//        }
//
//        if (this.level >= 4) {
//            this.x.f = 365;
//            this.u.f = 366;
//            if (this.level >= 4 && this.level <= 5) {
//                this.x.l = 0;
//                this.u.l = 0;
//                return;
//            }
//
//            if (this.level >= 6 && this.level <= 7) {
//                this.x.l = 25;
//                this.u.l = 25;
//                return;
//            }
//
//            if (this.level >= 8 && this.level <= 9) {
//                this.x.l = -112;
//                this.u.l = -112;
//                return;
//            }
//
//            if (this.level >= 10 && this.level <= 11) {
//                this.x.l = -107;
//                this.u.l = -107;
//                return;
//            }
//
//            if (this.level >= 12 && this.level <= 13) {
//                this.x.l = -160;
//                this.u.l = -160;
//                return;
//            }
//
//            if (this.level >= 14 && this.level <= 15) {
//                if (this.strData.contains(";370,")) {
//                    this.x.l = -71;
//                    this.u.l = -160;
//                    return;
//                }
//
//                this.x.l = -71;
//                this.u.l = -71;
//                return;
//            }
//
//            if (this.level == 16) {
//                this.x.l = -71;
//                this.u.l = 71;
//                return;
//            }
//
//            if (this.level >= 17) {
//                this.x.l = 70;
//                this.u.l = 70;
//                return;
//            }
//        } else {
//            this.x = null;
//            this.u = null;
//        }
//
//    }
    public final int c() {
        if (this.zeniBuy > 0) {
            return this.zeniBuy;
        } else if (this.zeniKhoaBuy > 0) {
            return this.zeniKhoaBuy;
        } else if (this.ballzBuy > 0) {
            return this.ballzBuy;
        } else if (this.xuBuy > 0) {
            return this.xuBuy;
        } else {
            return this.moneyGiaTocBuy > 0 ? this.moneyGiaTocBuy : 0;
        }
    }

    public final int d() {
        if (this.zeniBuy > 0) {
            return -3;
        } else if (this.zeniKhoaBuy > 0) {
            return -4;
        } else if (this.ballzBuy > 0) {
            return -5;
        } else if (this.xuBuy > 0) {
            return -6;
        } else if (this.kimCuongBuy > 0) {
            return -32;
        } else {
            return this.moneyGiaTocBuy > 0 ? -9 : 0;
        }
    }

    //    public final String e() {
//        //  yc = new String[]{"Zeni", "Zeni khóa", "Ballz", "Xu", "kim cương", "đồng tiền vũ trụ"};
//        if (this.zeniBuy > 0) {
//            return GoiRong_ir.c(this.zeniBuy) + " " + Caption.yc[0];
//        } else if (this.zeniKhoaBuy > 0) {
//            return GoiRong_ir.c(this.zeniKhoaBuy) + " " + Caption.yc[1];
//        } else if (this.ballzBuy > 0) {
//            return GoiRong_ir.c(this.ballzBuy) + " " + Caption.yc[2];
//        } else if (this.xuBuy > 0) {
//            return GoiRong_ir.c(this.xuBuy) + " " + Caption.yc[3];
//        } else if (this.kimCuongBuy > 0) {
//            return GoiRong_ir.c(this.kimCuongBuy) + " " + Caption.yc[5];
//        } else {
//            return this.moneyGiaTocBuy > 0 ? GoiRong_ir.c(this.moneyGiaTocBuy) + " " + Caption.yc[4] : "";
//        }
//    }
//
//    public final String f() {
//        if (this.zeniBuy > 0) {
//            return Caption.yc[0];
//        } else if (this.zeniKhoaBuy > 0) {
//            return Caption.yc[1];
//        } else if (this.ballzBuy > 0) {
//            return Caption.yc[2];
//        } else if (this.xuBuy > 0) {
//            return Caption.yc[3];
//        } else if (this.kimCuongBuy > 0) {
//            return Caption.yc[5];
//        } else {
//            return this.moneyGiaTocBuy > 0 ? Caption.yc[4] : "";
//        }
//    }
//
//    public final String g() {
//        ItemOption[] var1 = this.K();
//        int var2 = 0;
//        if (this.i() && var1 != null) {
//            var2 = 0 + var1.length;
//        }
//
//        if (this.getItemTemplate().type == 34) {
//            var2 = 100000;
//        }
//
//        if (this.id == 185) {
//            var2 = 50000;
//        }
//
//        if (this.id == 186) {
//            var2 = 100000;
//        } else if (this.id == 187) {
//            var2 = 200000;
//        }
//
//        return GoiRong_ir.c(var2) + Caption.da;
//    }
    public final ItemTemplate getItemTemplate() {
        try {
            if (this.id >= 0) {
                return DataCenter.gI().P[this.id];
            }
        } catch (Exception var2) {
        }

        return null;
    }

    public final boolean isTrangBi() {
        if (this.ah()) {
            return false;
        } else {
            for (int var1 = 0; var1 < DataCenter.gI().V.length; ++var1) {
                if (DataCenter.gI().V[var1].type == this.getItemTemplate().type) {
                    return true;
                }
            }

            return false;
        }
    }

    public final boolean j() {
        return this.getItemTemplate().type == 1 && !this.ah();
    }

    public final boolean k() {
        return this.getItemTemplate().type == 0 || this.getItemTemplate().type == 2 || this.getItemTemplate().type == 4 || this.getItemTemplate().type == 6 || this.getItemTemplate().type == 8;
    }

    public final boolean l() {
        return this.getItemTemplate().type == 3 || this.getItemTemplate().type == 5 || this.getItemTemplate().type == 7 || this.getItemTemplate().type == 9;
    }

    public final boolean m() {
        return this.getItemTemplate().type == 12;
    }

    public final boolean n() {
        return this.am() || this.id == 310;
    }

    private boolean am() {
        try {
            final ItemOption[] k;
            if (this.getItemTemplate().type == 11 && (k = this.K()) != null) {
                for (int i = 0; i < k.length; ++i) {
                    if (k[i].b[0] == 128 && k[i].b[1] == k[i].h() && k[i].h() < 16000) {
                        return true;
                    }
                }
            }
        } catch (Exception ex) {
        }
        return false;
    }

    public final boolean o() {
        return this.an() || this.id == 685;
    }

    private boolean an() {
        try {
            if (this.getItemTemplate().type == 13) {
                final ItemOption[] k = this.K();
                int n = 0;
                if (k != null) {
                    for (int i = 0; i < k.length; ++i) {
                        if (k[i].b[0] >= 53 && k[i].b[0] <= 62 && k[i].b[1] == k[i].h() && k[i].h() < 16000 && ++n == 2) {
                            return true;
                        }
                    }
                }
            }
        } catch (Exception ex) {
        }
        return false;
    }

    public final boolean p() {
        return this.getItemTemplate().type == 21;
    }

    //    public final boolean q() {
//        return this.getItemTemplate().type == 14 && this.getItemTemplate().levelNeed <= GoiRong_ee.e().p();
//    }
    public final boolean r() {
        ItemOption[] var1;
        if (this.getItemTemplate().type == 14 && (var1 = this.K()) != null) {
            for (int var2 = 0; var2 < var1.length; ++var2) {
                if (var1[var2].a().type == 14) {
                    return true;
                }
            }
        }

        return false;
    }

    public final boolean s() {
        return this.getItemTemplate().type == 14 && this.expiry == -1L && this.level > 0;
    }

    public final boolean t() {
        return this.p() || this.v() || this.id == 162 || this.id == 823;
    }

    public final boolean u() {
        ItemOption[] var1;
        if (this.getItemTemplate().levelNeed >= 50 && (var1 = this.K()) != null) {
            for (int var2 = 0; var2 < var1.length; ++var2) {
                if (var1[var2].b[0] == 159 || var1[var2].b[0] == 163 || var1[var2].b[0] == 164 || var1[var2].b[0] == 165) {
                    return true;
                }
            }
        }

        return false;
    }

    public final boolean v() {
        if (!this.ah() && !this.w()) {
            return this.getItemTemplate().type >= 0 && this.getItemTemplate().type <= 9 && this.level < 14;
        } else {
            return false;
        }
    }

    public final boolean w() {
        return this.getItemTemplate().type == 0;
    }

    public final boolean x() {
        return this.getItemTemplate().type == 0 || this.getItemTemplate().type == 3 || this.getItemTemplate().type == 5 || this.getItemTemplate().type == 7 || this.getItemTemplate().type == 9 || this.getItemTemplate().type == 11;
    }

    public final boolean y() {
        return this.k() || this.l() || this.j();
    }

    public final boolean z() {
        return this.getItemTemplate().type == 35 || this.getItemTemplate().type == 36 || this.getItemTemplate().type == 32 || this.as();
    }

    public final boolean A() {
        if (!this.ah() && !this.x()) {
            return this.getItemTemplate().type >= 0 && this.getItemTemplate().type <= 9 && this.level > 0;
        } else {
            return false;
        }
    }

    public final boolean B() {
        if (!this.ah() && !this.x()) {
            if (!this.y()) {
                return false;
            } else {
                ItemOption[] var1;
                if ((var1 = this.K()) != null) {
                    for (int var2 = 0; var2 < var1.length; ++var2) {
                        if (var1[var2].n()) {
                            return true;
                        }
                    }
                }

                return false;
            }
        } else {
            return false;
        }
    }

    public final boolean C() {
        return this.getItemTemplate().type == 12 || this.id == 684;
    }

    public final boolean D() {
        if (!this.ah() && !this.x()) {
            return this.getItemTemplate().type >= 0 && this.getItemTemplate().type <= 9 || this.getItemTemplate().type == 25;
        } else {
            return false;
        }
    }

    public final boolean E() {
        return this.getItemTemplate().type == 12 && this.level < 16 || this.id == 683;
    }

    public final boolean F() {
        if (this.id == 160) {
            return true;
        } else {
            ItemOption[] var1;
            if (this.getItemTemplate().type == 13 && (var1 = this.K()) != null) {
                int var2 = 0;

                for (int var3 = 0; var3 < var1.length; ++var3) {
                    if (var1[var3].b[0] >= 53 && var1[var3].b[0] <= 62 && var1[var3].b[1] == var1[var3].h()) {
                        ++var2;
                        if (var2 == 2) {
                            return false;
                        }
                    }
                }

                return true;
            } else {
                return false;
            }
        }
    }

    public final boolean G() {
        return this.getItemTemplate().type == 1 && this.getItemTemplate().levelNeed >= 50 && this.ar() || this.id == 353;
    }

    private boolean ao() {
        return this.getItemTemplate().type == 2 || this.getItemTemplate().type == 8 || this.getItemTemplate().type == 7;
    }

    private boolean ap() {
        return this.getItemTemplate().type == 6 || this.getItemTemplate().type == 5 || this.getItemTemplate().type == 9;
    }

    private boolean aq() {
        return this.getItemTemplate().type == 0 || this.getItemTemplate().type == 4 || this.getItemTemplate().type == 3;
    }

    public final boolean H() {
        return this.ao() && this.getItemTemplate().levelNeed >= 50 && this.ar() || this.id == 565;
    }

    public final boolean I() {
        return this.ap() && this.getItemTemplate().levelNeed >= 50 && this.ar() || this.id == 563;
    }

    public final boolean J() {
        return this.aq() && this.getItemTemplate().levelNeed >= 50 && this.ar() || this.id == 567;
    }

    //    public final void a(int var1) {
//        ItemOption[] var2;
//        if ((var2 = this.K()) != null) {
//            int var3;
//            int var4;
//            int[] var5;
//            if (var1 >= this.level) {
//                for (var3 = this.level + 1; var3 <= var1; ++var3) {
//                    for (var4 = 0; var4 < var2.length; ++var4) {
//                        if (var2[var4].a().type != 8) {
//                            var5 = var2[var4].a().a();
//                            if (var3 <= var5.length) {
//                                var2[var4].d(var5[var3 - 1]);
//                            }
//                        }
//                    }
//                }
//            } else {
//                for (var3 = this.level; var3 > var1; --var3) {
//                    for (var4 = 0; var4 < var2.length; ++var4) {
//                        if (var2[var4].a().type != 8) {
//                            var5 = var2[var4].a().a();
//                            if (var3 <= var5.length) {
//                                var2[var4].d(-var5[var3 - 1]);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        this.level = (byte) var1;
//        this.strData = a(var2);
//    }
    public final ItemOption[] K() {
        if (this.strData != null && this.strData.length() > 0) {
            String[] var1;
            ItemOption[] var2 = new ItemOption[(var1 = Utlis.split(this.strData, ";")).length];

            for (int var3 = 0; var3 < var1.length; ++var3) {
                var2[var3] = new ItemOption(var1[var3]);
            }

            return var2;
        } else {
            return null;
        }
    }

    //    public static String a(Vector var0) {
//        ItemOption[] var1 = new ItemOption[var0.size()];
//
//        for (int var2 = 0; var2 < var1.length; ++var2) {
//            var1[var2] = (ItemOption) var0.get(var2);
//        }
//
//        return a(var1);
//    }
//
    public static String a(ItemOption[] var0) {
        String var1 = "";
        if (var0 != null) {
            for (int var2 = 0; var2 < var0.length; ++var2) {
                var1 = var1 + var0[var2].i();
                if (var2 < var0.length - 1) {
                    var1 = var1 + ";";
                }
            }
        }

        return var1;
    }

    //
//    public static String b(int var0) {
//        if (var0 >= 1000000000) {
//            return GoiRong_ir.c(var0 / 1000000) + "M";
//        } else {
//            return var0 >= 1000000 ? GoiRong_ir.c(var0 / 1000) + "K" : GoiRong_ir.c(var0);
//        }
//    }
    public final int L() {
        if (this.id == 190) {
            return this.amount / 10;
        } else if (this.id == 826) {
            return this.amount <= 0 ? 0 : this.amount - 1;
        } else {
            return this.amount <= 0 ? 1 : this.amount;
        }
    }

    public final void c(int var1) {
        if (var1 <= 0) {
            this.amount = 1;
        }

        this.amount = var1;
    }

    //    public final String M() {
//        if (this.expiry >= 2592000000L) {
//            return this.expiry / 2592000000L + " " + Caption.yg[0];
//        } else if (this.expiry >= 604800000L) {
//            return this.expiry / 604800000L + " " + Caption.yg[1];
//        } else if (this.expiry >= 86400000L) {
//            return this.expiry / 86400000L + " " + Caption.yg[2];
//        } else if (this.expiry >= 3600000L) {
//            return this.expiry / 3600000L + " " + Caption.yg[3];
//        } else {
//            return this.expiry >= 60000L ? this.expiry / 60000L + " " + Caption.yg[4] : this.expiry / 1000L + " " + Caption.yg[5];
//        }
//    }
//
//    public final String N() {
//        return this.expiry == -1L ? Caption.mJ : GoiRong_ir.g(this.expiry);
//    }
    public final void a(Message var1) throws IOException {
        this.id = var1.readShort();
        if (this.id >= 0) {
            this.isLock = var1.readBoolean();
            this.expiry = var1.readLong();
            if (!this.isTrangBi() && !this.ah()) {
                this.c(var1.readInt());
            } else {
                this.he = var1.readByte();
                this.level = var1.readByte();
                this.strData = var1.dis.readUTF();
                this.nameCreate = var1.dis.readUTF();
            }

            if (this.at()) {
                this.strData = var1.dis.readUTF();
            }

            this.index = var1.readShort();
        }
    }

    public final void write(Message var1) throws IOException {
        var1.writeShort(id);
        if (this.id >= 0) {
            var1.writeBoolean(isLock);
            var1.writeLong(this.expiry);
            if (!this.isTrangBi() && !this.ah()) {
                var1.writeInt(amount);
            } else {
                var1.writeByte(he);
                var1.writeByte(level);
                var1.writeUTF(strData);
                var1.writeUTF(nameCreate);
            }

            if (this.at()) {
                var1.writeUTF(strData);
            }

            var1.writeShort(index);
        }
    }

    public final boolean O() {
        ItemOption[] var1;
        if ((var1 = this.K()) != null) {
            for (int var2 = 0; var2 < var1.length; ++var2) {
                if (var1[var2].b[0] == 159) {
                    return true;
                }
            }
        }

        return false;
    }

    public final boolean P() {
        ItemOption[] var1;
        if ((var1 = this.K()) != null) {
            for (int var2 = 0; var2 < var1.length; ++var2) {
                if (var1[var2].b[0] == 165) {
                    return true;
                }
            }
        }

        return false;
    }

    public final boolean Q() {
        ItemOption[] var1;
        if ((var1 = this.K()) != null) {
            for (int var2 = 0; var2 < var1.length; ++var2) {
                if (var1[var2].b[0] == 163) {
                    return true;
                }
            }
        }

        return false;
    }

    public final boolean R() {
        ItemOption[] var1;
        if ((var1 = this.K()) != null) {
            for (int var2 = 0; var2 < var1.length; ++var2) {
                if (var1[var2].b[0] == 164) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean ar() {
        ItemOption[] var1;
        if ((var1 = this.K()) != null) {
            for (int var2 = 0; var2 < var1.length; ++var2) {
                if (var1[var2].b[0] == 148) {
                    return true;
                }
            }
        }

        return false;
    }

    public final boolean S() {
        return this.getItemTemplate().type != 21 && this.getItemTemplate().type != 32 && this.getItemTemplate().type != 35 && this.getItemTemplate().type != 36 && this.getItemTemplate().type != 98 && this.getItemTemplate().type != 25 && this.id != 162 && this.id != 823 && this.id != 176;
    }

    public final ItemOption a(Vector var1, int var2) {
        var2 = ItemOption.f(var2);
        ItemOption[] var3 = this.K();
        ItemOption var4 = null;
        if (var3 != null) {
            for (int var5 = 0; var5 < var3.length; ++var5) {
                if (var3[var5].a().type == 8) {
                    var1.add(var3[var5]);
                    if (var3[var5].b[0] == var2) {
                        var4 = var3[var5];
                    }
                }
            }
        }

        return var4;
    }

    //    public final int[] a(int var1, int var2) {
//        ItemOption[] var3;
//        if ((var3 = this.K()) == null) {
//            return null;
//        } else {
//            int var4 = ItemOption.f(var2);
//            int[] var5 = null;
//            boolean var6 = this.T();
//
//            for (int var7 = 0; var7 < var3.length; ++var7) {
//                if (var3[var7].a().type == 8 && var3[var7].b[0] == var4) {
//                    int var8 = var3[var7].k();
//                    (var5 = new int[4])[0] = var2;
//                    var5[1] = var8;
//                    var5[2] = var8;
//                    int[] var9 = var3[var7].a().a();
//
//                    for (int var10 = var8 + 1; var10 <= var9.length; ++var10) {
//                        if (var1 >= GoiRong_de.d().as[var10]) {
//                            if (var6) {
//                                if (var10 > 17) {
//                                    continue;
//                                }
//                            } else if (var10 > 16) {
//                                continue;
//                            }
//
//                            var5[1] = var8;
//                            var5[2] = var10;
//                            var5[3] += GoiRong_de.d().as[var10];
//                            var1 -= GoiRong_de.d().as[var10];
//                            var3[var7].d(var9[var10 - 1]);
//                            var3[var7].b[3] = var10;
//                        }
//                    }
//                }
//            }
//
//            this.strData = a(var3);
//            return var5;
//        }
//    }
//
//    public final boolean T() {
//        ItemOption[] var1;
//        if ((var1 = this.K()) != null) {
//            for (int var2 = 0; var2 < var1.length; ++var2) {
//                if (var1[var2].b[0] == 165 || var1[var2].b[0] == 164 || var1[var2].b[0] == 163 || var1[var2].b[0] == 159) {
//                    return true;
//                }
//            }
//        }
//
//        return false;
//    }
//
//    public final int U() {
//        ItemOption[] var1;
//        if ((var1 = this.K()) != null) {
//            for (int var2 = 0; var2 < var1.length; ++var2) {
//                if (var1[var2].b[0] == 349) {
//                    return var1[var2].b[1];
//                }
//            }
//        }
//
//        return 0;
//    }
//
//    public final int V() {
//        ItemOption[] var1;
//        if ((var1 = this.K()) != null) {
//            for (int var2 = 0; var2 < var1.length; ++var2) {
//                if (var1[var2].b[0] == 350) {
//                    return var1[var2].h();
//                }
//            }
//        }
//
//        return 0;
//    }
//
//    public final int W() {
//        ItemOption[] var1;
//        if ((var1 = this.K()) != null) {
//            for (int var2 = 0; var2 < var1.length; ++var2) {
//                if (var1[var2].b[0] == 350) {
//                    return var1[var2].b[1];
//                }
//            }
//        }
//
//        return 0;
//    }
//
//    public final int X() {
//        ItemOption[] var1;
//        if ((var1 = this.K()) != null) {
//            for (int var2 = 0; var2 < var1.length; ++var2) {
//                if (var1[var2].b[0] == 298) {
//                    return var1[var2].h();
//                }
//            }
//        }
//
//        return 0;
//    }
//
//    public final boolean Y() {
//        long var1 = GoiRong_ir.a();
//        return this.expiry != -1L && this.expiry < var1;
//    }
//
//    public final boolean Z() {
//        return this.id >= 670 && this.id <= 681;
//    }
//
//    public final boolean aa() {
//        if (!this.ah() && !this.x()) {
//            if (!this.y()) {
//                return false;
//            } else {
//                ItemOption[] var1;
//                if ((var1 = this.K()) != null) {
//                    int var2 = 0;
//
//                    for (int var3 = 0; var3 < var1.length; ++var3) {
//                        if (var1[var3].b[0] == 349 && var1[var3].h() >= 4 || var1[var3].b[0] == 350 && var1[var3].h() >= 7 || var1[var3].b[0] == 298 && var1[var3].h() >= 5) {
//                            ++var2;
//                        }
//                    }
//
//                    if (var2 >= 3) {
//                        return false;
//                    }
//                }
//
//                return true;
//            }
//        } else {
//            return false;
//        }
//    }
//
    private boolean as() {
        ItemOption[] var1;
        if (this.y() && (var1 = this.K()) != null) {
            for (int var2 = 0; var2 < var1.length; ++var2) {
                if (var1[var2].m()) {
                    return true;
                }
            }
        }

        return false;
    }
//
//    public final boolean ab() {
//        return !this.isLock;
//    }
//
//    public final boolean ac() {
//        return this.amount > 1 && this.getItemTemplate().type != 24 && this.id != 499 && this.id != 500 && this.id != 927 && this.id != 952 && !this.getItemTemplate().detail.toLowerCase().contains(Caption.rS.toLowerCase()) && !this.getItemTemplate().detail.contains("1.000");
//    }
//
//    public final boolean ad() {
//        return this.getItemTemplate().isXepChong && this.amount > 1;
//    }
//

    public final boolean ae() {
        return this.id == 168 || d(this.id);
    }

    public final boolean isTauBay() {
        return this.id == 168 || d(this.id);
    }
//

    public static boolean d(int var0) {
        if (var0 == 569 || var0 >= 716 && var0 <= 720) {
            return true;
        } else {
            return false;
        }
    }

    public final boolean af() {
        return this.id == 430 || this.id == 377 || this.id == 520 || this.id == 525 || this.id == 922;
    }

    //    public final String ag() {
//        return this.level % 2 == 0 ? this.level / 2 + Caption.sY : this.level / 2 + Caption.sZ;
//    }
    private boolean at() {
        return this.getItemTemplate().type == 99 || this.getItemTemplate().type == 35 || this.getItemTemplate().type == 36;
    }

    public final boolean ah() {
        return this.id >= 799 && this.id <= 806;
    }

    public final boolean ai() {
        return this.id == 724;
    }

    public final boolean aj() {
        return this.getItemTemplate().name.toLowerCase().startsWith("viên capsule");
    }

//    public final boolean ak() {
//        ItemOption[] var1;
//        if ((var1 = this.K()) != null) {
//            for (int var2 = 0; var2 < var1.length; ++var2) {
//                if (var1[var2].b[0] == 413) {
//                    return true;
//                }
//            }
//        }
//
//        return false;
//    }
//
//    // $FF: synthetic method
//    public final Object clone() {
//        return this.a();
//    }
}
