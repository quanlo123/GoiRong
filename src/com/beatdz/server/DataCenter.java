package com.beatdz.server;

import com.beatdz.data.*;
//import com.beatdz.data2.*;
import beatdz.sg316.*;
import com.beatdz.datareal.Map;
import com.beatdz.lib.Utlis;
import com.beatdz.network.Reader;
import com.beatdz.network.Writer;
import com.beatdz.real.WayPoint;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataCenter {

    private static DataCenter instance;

    public DataCenter() {
        this.createData();
    }

    public NpcTemplate[] G;
    public MobTemplate[] H;
    public MapTemplate[] I;
    public DataTemplateAchievement[] J;
    public DataTaskDay[] K;
    public DataTemplateTask[] L;
    public DataNameChar[] M;
    public DataIconChar[] N;
    public DataNameClass[] O;
    public ItemTemplate[] P;
    public ItemOptionTemplate[] Q;
    public SkillTemplate[] R;
    public SkillClan[] S;
    public EffectTemplate[] T;
    public Skill[] U;
    public DataTypeItemBody[] V;
    public short[][] dataWayPoint;
    public int[][] X;
    public GoiRong_fx[] y;
    public byte[][] Y;
    public Vector Z = new Vector();
    public Vector aa = new Vector();
    public Vector ab = new Vector();
    public Hashtable ac = new Hashtable();
    public Hashtable ad = new Hashtable();
    public Hashtable ae = new Hashtable();
    public Hashtable af = new Hashtable();
    public Hashtable ag = new Hashtable();
    public GoiRong_hq[] ah;
    public GoiRong_hp ai;
    public static boolean ak;
    public static boolean al;
    public static boolean am;
    public int[] an;
    public int[] ao;
    public int[] ap;
    public int[] aq;
    public int[] ar;
    public int[] as;
    public long[] at;
    public long[] au;
    public long[] av;
    public long[] aw;
    public String[][] ax = new String[2][];
    public long[] ay;
    public String[] dataXMap = ("2:1350\n"
            + "4:1350\n"
            + "6:1350\n"
            + "8:1350\n"
            + "9:1173\n"
            + "10:1173\n"
            + "10:1173\n"
            + "12:1923\n"
            + "13:1173\n"
            + "14:1923\n"
            + "15:1923\n"
            + "16:1766\n"
            + "17:2000\n"
            + "18:2000\n"
            + "19:2000\n"
            + "20:2300\n"
            + "30:3600\n"
            + "39:1400\n"
            + "44:989\n"
            + "45:989\n"
            + "46:989\n"
            + "46:989\n"
            + "48:1066\n"
            + "49:1066\n"
            + "50:1066\n"
            + "53:800\n"
            + "53:800\n"
            + "55:1770\n"
            + "56:1120\n"
            + "57:1800\n"
            + "58:1740\n"
            + "61:2000\n"
            + "62:2000\n"
            + "63:1800\n"
            + "64:1800\n"
            + "65:2000\n"
            + "66:1800\n"
            + "67:760\n"
            + "70:2000\n"
            + "71:1950\n"
            + "72:1990\n"
            + "73:1800\n"
            + "74:1800\n"
            + "75:1950\n"
            + "76:2000\n"
            + "77:2000\n"
            + "78:1800\n"
            + "79:1800\n"
            + "80:1800\n"
            + "80:1800\n"
            + "82:1980\n"
            + "83:800\n"
            + "84:2000\n"
            + "86:1560\n"
            + "87:1000\n"
            + "88:2000\n"
            + "89:1280\n"
            + "93:1986\n"
            + "94:2300\n"
            + "95:2300\n"
            + "96:1986\n"
            + "97:1986\n"
            + "102:2002\n"
            + "103:1820\n"
            + "104:2320\n"
            + "105:2320").split("\n");

    public static DataCenter gI() {
        if (instance == null) {
            instance = new DataCenter();
        }
        return instance;
    }

    private void createData() {
        System.out.println("aaaaaaaaaaa");
        try {
            readData(new Reader(Utlis.inflateByteArray2(Server.arrData)));
            readData2(new Reader(Server.arrData2));
//            for (int i = 0; i < dataXMap.length; i++) {
//                String[] array = dataXMap[i].split(":");
//                this.I[Integer.parseInt(array[0])].x = Integer.parseInt(array[1]);
//            }
            for (int i = 0; i < this.I.length; i++) {
                for (int var10 = 0; var10 < dataWayPoint.length; ++var10) {
                    WayPoint var11 = null;
                    if (dataWayPoint[var10][0] == i) {
                        (var11 = new WayPoint()).a(dataWayPoint[var10][0], dataWayPoint[var10][5], dataWayPoint[var10][1], dataWayPoint[var10][2], dataWayPoint[var10][3], dataWayPoint[var10][4], dataWayPoint[var10][10], dataWayPoint[var10][11], true);
                        this.I[i].listWayPoint.add(var11);
                        this.I[dataWayPoint[var10][5]].listWayPointIn.add(var11);
                    } else if (dataWayPoint[var10][5] == i) {
                        (var11 = new WayPoint()).a(dataWayPoint[var10][5], dataWayPoint[var10][0], dataWayPoint[var10][6], dataWayPoint[var10][7], dataWayPoint[var10][8], dataWayPoint[var10][9], dataWayPoint[var10][12], dataWayPoint[var10][13], false);
                        this.I[i].listWayPoint.add(var11);
                        this.I[dataWayPoint[var10][0]].listWayPointIn.add(var11);
                    }
                }

            }
//            for (int i = 0; i < this.I[75].listWayPoint.size(); i++) {
//                System.out.println("Next Sang Map Khác : " + this.I[75].listWayPoint.get(i).k);
//            }
//            for (int i = 0; i < this.I[75].listWayPoint.size(); i++) {
//                System.out.println("Next Về Map : " + this.I[75].listWayPoint.get(i).k);
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public final void readData(Reader var2) {
        try {
            this.N = new DataIconChar[var2.dis.readByte()];
            int var3;
            for (var3 = 0; var3 < this.N.length; ++var3) {
                this.N[var3] = new DataIconChar(var3);
                this.N[var3].idIconChar = var2.dis.readShort();
            }

            this.O = new DataNameClass[var2.dis.readByte()];

            for (var3 = 0; var3 < this.O.length; ++var3) {
                this.O[var3] = new DataNameClass(var3);
                this.O[var3].name = var2.readUTF();

            }

            this.M = new DataNameChar[var2.dis.readByte()];

            for (var3 = 0; var3 < this.M.length; ++var3) {
                this.M[var3] = new DataNameChar(var3);
                this.M[var3].name = var2.readUTF();
                this.M[var3].type = var2.dis.readByte();
                var2.dis.readShort();

            }

            this.J = new DataTemplateAchievement[var2.readUnsignedByte()];

            for (var3 = 0; var3 < this.J.length; ++var3) {
                this.J[var3] = new DataTemplateAchievement();
                this.J[var3].a = var2.dis.readByte();
                this.J[var3].b = var2.readUTF();
                this.J[var3].c = var2.dis.readInt();
                this.J[var3].d = var2.dis.readInt();
                this.J[var3].e = var2.dis.readInt();
                this.J[var3].f = var2.dis.readInt();
                this.J[var3].g = var2.dis.readInt();
                this.J[var3].h = var2.readUTF();

            }

            this.L = new DataTemplateTask[var2.dis.readShort()];

            int var5;
            for (var3 = 0; var3 < this.L.length; ++var3) {
                this.L[var3] = new DataTemplateTask();
                this.L[var3].id = var3;
                this.L[var3].name = var2.readUTF();
                this.L[var3].c = var2.dis.readShort();
                this.L[var3].d = var2.dis.readShort();
                this.L[var3].e = var2.dis.readShort();
                this.L[var3].f = var2.dis.readShort();
                this.L[var3].g = var2.dis.readShort();
                this.L[var3].h = var2.readUTF();
                this.L[var3].i = var2.readUTF();
                this.L[var3].j = var2.readUTF();
                this.L[var3].k = var2.dis.readInt();
                this.L[var3].l = var2.dis.readInt();
                this.L[var3].m = var2.dis.readInt();
                this.L[var3].n = var2.dis.readInt();
                this.L[var3].o = var2.readUTF();
                byte var13 = var2.dis.readByte();
                for (var5 = 0; var5 < var13; ++var5) {
                    DataTemplateStep var6;
                    (var6 = new DataTemplateStep()).id = var2.dis.readByte();
                    var6.b = var2.readUTF();
                    short var8 = var2.dis.readShort();
                    var6.c = var8;
                    var6.d = var2.dis.readShort();
                    var6.e = var2.dis.readShort();
                    var6.f = var2.dis.readShort();
                    var6.g = var2.dis.readShort();
                    var6.h = var2.dis.readShort();
                    var6.i = var2.dis.readShort();
                    var6.j = var2.readUTF();
                    var6.k = var2.readUTF();
                    this.L[var3].p.addElement(var6);
                }
            }

            this.K = new DataTaskDay[var2.readUnsignedByte()];

            for (var3 = 0; var3 < this.K.length; ++var3) {
                this.K[var3] = new DataTaskDay();
                this.K[var3].id = var2.dis.readByte();
                this.K[var3].name = var2.readUTF();
                this.K[var3].amount = var2.dis.readShort();

            }

            MapTemplate[] var21 = new MapTemplate[var2.dis.readShort()];

            for (int var14 = 0; var14 < var21.length; ++var14) {
                var21[var14] = new MapTemplate(var14);
                var21[var14].name = var2.readUTF();
                var21[var14].c = var2.readUnsignedByte();
                var21[var14].type = var2.dis.readByte();
                var21[var14].typePlanet = var2.dis.readByte();
                var21[var14].byteArray = var2.read();
                var21[var14].load();

            }

            this.I = var21;

            ItemOptionTemplate[] var15 = new ItemOptionTemplate[var2.dis.readShort()];

            for (var5 = 0; var5 < var15.length; ++var5) {
                var15[var5] = new ItemOptionTemplate(var5);
                var15[var5].name = var2.readUTF();
                var15[var5].type = var2.dis.readByte();
                var15[var5].d = var2.dis.readByte();
                var15[var5].strData = var2.readUTF();
            }

            if (this.Q == null || this.Q.length == 0) {
                this.Q = var15;
            }

            EffectTemplate[] var20 = new EffectTemplate[var2.dis.readShort()];

            for (int var19 = 0; var19 < var20.length; ++var19) {
                var20[var19] = new EffectTemplate(var19);
                var20[var19].name = var2.readUTF();
                var20[var19].detail = var2.readUTF();
                var20[var19].type = var2.readUnsignedByte();
                var20[var19].idIcon = var2.dis.readShort();
                var20[var19].f = var2.dis.readShort();
            }

            if (this.T == null || this.T.length == 0) {
                this.T = var20;
            }

            ItemTemplate[] var22 = new ItemTemplate[var2.dis.readShort()];

            for (var3 = 0; var3 < var22.length; ++var3) {
                var22[var3] = new ItemTemplate(var3);
                var22[var3].name = var2.readUTF();
                var22[var3].detail = var2.readUTF();
                var22[var3].isXepChong = var2.dis.readBoolean();
                var22[var3].gioiTinh = var2.dis.readByte();
                var22[var3].type = var2.dis.readByte();
                var22[var3].typeChar = var2.dis.readByte();
                var22[var3].idIcon = var2.dis.readShort();
                var22[var3].levelNeed = var2.readUnsignedByte();
                var22[var3].phamChatNeed = var2.dis.readUnsignedShort();
                var22[var3].k = var2.dis.readShort();
                var22[var3].l = var2.dis.readShort();
            }

            if (this.P == null || this.P.length == 0) {
                this.P = var22;
            }

            this.H = new MobTemplate[var2.dis.readShort()];

            for (var3 = 0; var3 < this.H.length; ++var3) {
                this.H[var3] = new MobTemplate(var3);
                this.H[var3].h = var2.dis.readShort();
                this.H[var3].name = var2.readUTF();
                this.H[var3].detail = var2.readUTF();
                this.H[var3].speedMove = var2.dis.readShort();
                this.H[var3].p = var2.dis.readByte();
                this.H[var3].q = var2.dis.readByte();
                this.H[var3].r = var2.dis.readByte();
                this.H[var3].f = var2.dis.readShort();
                this.H[var3].i = var2.dis.readShort();
                this.H[var3].str1 = var2.readUTF();
                this.H[var3].str2 = var2.readUTF();

            }

            this.G = new NpcTemplate[var2.dis.readShort()];

            for (var3 = 0; var3 < this.G.length; ++var3) {
                this.G[var3] = new NpcTemplate(var3);
                this.G[var3].name = var2.readUTF();
                this.G[var3].detail = var2.readUTF();
                this.G[var3].f = var2.dis.readShort();
                this.G[var3].l = var2.dis.readInt();
                this.G[var3].m = var2.dis.readInt();
                this.G[var3].i = var2.dis.readShort();

            }

            this.R = new SkillTemplate[var2.dis.readShort()];

            for (var3 = 0; var3 < this.R.length; ++var3) {
                this.R[var3] = new SkillTemplate(var3);
                this.R[var3].name = (var2.readUTF());
                this.R[var3].detail = var2.readUTF();
                this.R[var3].d = var2.dis.readShort();
                this.R[var3].e = var2.dis.readByte();
                this.R[var3].levelMax = var2.dis.readByte();
                this.R[var3].g = var2.dis.readByte();
                this.R[var3].h = var2.dis.readShort();
                this.R[var3].i = var2.dis.readShort();
            }

            this.U = new Skill[var2.dis.readShort()];

            short var23;
            for (var23 = 0; var23 < this.U.length; ++var23) {
                Skill var17;
                (var17 = new Skill()).id = var2.dis.readShort();
                var17.isSkillTemplate = var2.dis.readShort();
                var17.level = var2.dis.readByte();
                var17.levelNeed = var2.readUnsignedByte();
                var17.mpUsing = var2.dis.readShort();
                var17.coolDown = var2.dis.readInt();
                var17.rangeDoc = var2.dis.readShort();
                var17.rangeNgang = var2.dis.readShort();
                var17.maxTarget = var2.dis.readByte();
                var17.kiUpgrade = var2.dis.readLong();
                var17.m = var2.readUTF();
                this.U[var17.id] = var17;

            }

            this.S = new SkillClan[var2.readUnsignedByte()];

            for (var23 = 0; var23 < this.S.length; ++var23) {
                SkillClan var18;
                (var18 = new SkillClan()).id = var23;
                var18.name = var2.readUTF();
                var18.detail = var2.readUTF();
                var18.levelNeed = var2.readUnsignedByte();
                var18.strData = var2.readUTF();
                var18.iconChar = var2.dis.readShort();
                var18.moneyBuy = var2.dis.readInt();
                var18.h = var2.dis.readInt();
                this.S[var23] = var18;

            }

            this.V = new DataTypeItemBody[var2.dis.readByte()];

            for (var3 = 0; var3 < this.V.length; ++var3) {
                this.V[var3] = new DataTypeItemBody();
                this.V[var3].type = var2.dis.readByte();

            }

            this.ac = k(var2);

            this.ae = l(var2);

            this.ad = k(var2);

            this.af = l(var2);

            this.c(var2);

            this.d(var2);

            this.e(var2);

            this.f(var2);

            this.g(var2);

            this.i(var2);

            this.h(var2);

            this.j(var2);

            this.a(var2);

            this.b(var2);

            m(var2);
            this.an = n(var2);
            this.ao = o(var2);
            this.ap = o(var2);
            this.aq = o(var2);
            this.ar = o(var2);
            this.at = p(var2);
            this.au = p(var2);
            this.av = p(var2);
            this.aw = p(var2);
            this.as = o(var2);
            this.ax[0] = q(var2);
            this.ax[1] = q(var2);
            this.ay = p(var2);
            this.X = new int[var2.dis.readByte()][];

            for (var3 = 0; var3 < this.X.length; ++var3) {
                this.X[var3] = o(var2);
            }

            byte byte1 = var2.dis.readByte();
            return;
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            if (var2 != null) {
                var2.close();
            }

        }

    }

    public final void readData2(Reader var1) throws java.io.IOException {
        try {
            var1.dis.readByte();
            ItemOptionTemplate[] var2 = new ItemOptionTemplate[var1.dis.readShort()];
            int var3;
            for (var3 = 0; var3 < var2.length; ++var3) {
                var2[var3] = new ItemOptionTemplate(var3);
                var2[var3].name = var1.readUTF();
                var2[var3].type = var1.dis.readByte();
                var2[var3].d = var1.dis.readByte();
                var2[var3].strData = var1.readUTF();
            }
            this.Q = var2;
            EffectTemplate[] var11 = new EffectTemplate[var1.dis.readShort()];

            for (int var9 = 0; var9 < var11.length; ++var9) {
                var11[var9] = new EffectTemplate(var9);
                var11[var9].name = var1.readUTF();
                var11[var9].detail = var1.readUTF();
                var11[var9].type = (short) var1.dis.readUnsignedByte();
                var11[var9].idIcon = var1.dis.readShort();
                var11[var9].f = var1.dis.readShort();
            }

            this.T = var11;
            ItemTemplate[] var10 = new ItemTemplate[var1.dis.readShort()];

            for (var3 = 0; var3 < var10.length; ++var3) {
                var10[var3] = new ItemTemplate(var3);
                var10[var3].name = var1.readUTF();
                var10[var3].detail = var1.readUTF();
                var10[var3].isXepChong = var1.dis.readBoolean();
                var10[var3].gioiTinh = var1.dis.readByte();
                var10[var3].type = var1.dis.readByte();
                var10[var3].typeChar = var1.dis.readByte();
                var10[var3].idIcon = var1.dis.readShort();
                var10[var3].levelNeed = (short) var1.dis.readUnsignedByte();
                var10[var3].phamChatNeed = var1.dis.readUnsignedShort();
                var10[var3].k = var1.dis.readShort();
                var10[var3].l = var1.dis.readShort();
            }

            this.P = var10;
            this.d(var1);
            this.f(var1);
            this.g(var1);
            this.h(var1);
            this.j(var1);

        } catch (Exception var7) {
            var7.printStackTrace();
        } finally {
            if (var1 != null) {
                var1.close();
            }

        }

    }

    private static int[] n(Reader var0) throws java.io.IOException {
        int[] var1 = new int[var0.dis.readInt()];

        for (int var2 = 0; var2 < var1.length; ++var2) {
            var1[var2] = var0.dis.readShort();
        }

        return var1;
    }

    private static int[] o(Reader var0) throws java.io.IOException {
        int[] var1 = new int[var0.dis.readInt()];

        for (int var2 = 0; var2 < var1.length; ++var2) {
            var1[var2] = var0.dis.readInt();
        }

        return var1;
    }

    private static long[] p(Reader var0) throws java.io.IOException {
        long[] var1 = new long[var0.dis.readInt()];

        for (int var2 = 0; var2 < var1.length; ++var2) {
            var1[var2] = var0.dis.readLong();
        }

        return var1;
    }

    private static String[] q(Reader var0) throws java.io.IOException {
        String[] var1 = new String[var0.dis.readInt()];

        for (int var2 = 0; var2 < var1.length; ++var2) {
            var1[var2] = var0.readUTF();
        }

        return var1;
    }

    public Writer writerA = new Writer();

    private void a(Reader var1) throws java.io.IOException {
        writerA = new Writer();
        short var2 = var1.dis.readShort();
        writerA.writeShort(var2);
        this.Z.clear();

        for (int var5 = 0; var5 < var2; ++var5) {
            short var6 = var1.dis.readShort();
            short var7 = var1.dis.readShort();
            short var8 = var1.dis.readShort();
            byte var9 = var1.dis.readByte();
            byte var10 = var1.dis.readByte();
            writerA.writeShort(var6);
            writerA.writeShort(var7);
            writerA.writeShort(var8);
            writerA.writeByte(var9);
            writerA.writeByte(var10);
            short[][] var3 = new short[var1.dis.readByte()][];
            writerA.writeByte(var3.length);

            int var11;
            for (var11 = 0; var11 < var3.length; ++var11) {
                var3[var11] = new short[var1.dis.readShort()];
                writerA.writeShort(var3[var11].length);
                short var12 = 0;

                for (int var4 = 0; var4 < var3[var11].length; ++var4) {
                    var12 = var3[var11][var4] = var1.readUnsignedByte();
                    writerA.writeByte(var3[var11][var4]);
                }

                if (var11 == 5 && var3[var11].length > 1) {
                    var3[var11] = new short[]{(short) (var3[var11][0] * 256 + var3[var11][1])};
                }

                if (var11 == 1 && var3[var11].length <= 1) {
                    var3[var11] = new short[]{var12, var12, var12, var12, var12, var12, var12, var12};
                }
            }

            GoiRong_fb[] var18 = new GoiRong_fb[var1.dis.readByte()];
            writerA.writeByte(var18.length);

            for (var11 = 0; var11 < var18.length; ++var11) {
                var18[var11] = new GoiRong_fb();
                var18[var11].a = new GoiRong_fc[var1.dis.readByte()];
                writerA.writeByte(var18[var11].a.length);

                for (int var19 = 0; var19 < var18[var11].a.length; ++var19) {
                    var18[var11].a[var19] = new GoiRong_fc();
                    var18[var11].a[var19].a = var1.dis.readShort();
                    writerA.writeShort(var18[var11].a[var19].a);
                    a(var18[var11].a[var19].a);
                    if (!this.Z.contains(var18[var11].a[var19].a)) {
                        this.Z.add(var18[var11].a[var19].a);
                    }

                    var18[var11].a[var19].e = var1.dis.readBoolean();
                    var18[var11].a[var19].b = var1.dis.readShort();
                    var18[var11].a[var19].c = var1.dis.readShort();
                    var18[var11].a[var19].d = var1.dis.readShort();
                    writerA.writeBoolean(var18[var11].a[var19].e);
                    writerA.writeShort(var18[var11].a[var19].b);
                    writerA.writeShort(var18[var11].a[var19].c);
                    writerA.writeShort(var18[var11].a[var19].d);
                }
            }

            for (var11 = 0; var11 < this.G.length; ++var11) {
                if (this.G[var11].f == var5) {
                    this.G[var11].d = var6;
                    this.G[var11].e = var7;
                    this.G[var11].g = var8;
                    this.G[var11].h = (short) var9;
                    //this.G[var11].b = var18;
                    this.G[var11].a = var3;
                    NpcTemplate var10000 = this.G[var11];
                    var10000.e = (short) (var10000.e + var10);
                }
            }

            label125:
            for (var11 = 0; var11 < this.H.length; ++var11) {
                if (this.H[var11].f == var5) {
                    this.H[var11].d = var6;
                    this.H[var11].e = (short) (var7 - 3);
                    this.H[var11].j = var8;
                    this.H[var11].g = (short) var9;
                    MobTemplate var21 = this.H[var11];
                    var21.e = (short) (var21.e + var10);
                    //this.H[var11].b = var18;
                    this.H[var11].a = var3;
                    MobTemplate var20 = this.H[var11];

                    int var13;
                    boolean var10001;
                    try {
                        var13 = 0;
                    } catch (Exception var17) {
                        var10001 = false;
                        continue;
                    }

                    while (true) {
                        int var14;
                        try {
                            if (var13 >= var20.a[3].length) {
                                break;
                            }

                            var14 = 0;
                        } catch (Exception var15) {
                            var10001 = false;
                            break;
                        }

//                        while (true) {
//                            try {
//                                if (var14 >= var20.b[var20.isMapHoatDong[3][var13]].isMapHoatDong.length) {
//                                    break;
//                                }
//
//                                if (var20.b[var20.isMapHoatDong[3][var13]].isMapHoatDong[var14].isMapHoatDong == 0) {
//                                    var20.s = var20.b[var20.isMapHoatDong[3][var13]].isMapHoatDong[var14];
//                                    continue label125;
//                                }
//                            } catch (Exception var16) {
//                                var10001 = false;
//                                continue label125;
//                            }
//
//                            ++var14;
//                        }
                        ++var13;
                    }
                }
            }
        }

    }

    public static Writer writerb = new Writer();

    private void b(Reader var1) throws java.io.IOException {
        writerb = new Writer();
        short var2 = var1.dis.readShort();
        writerb.writeShort(var2);
        this.ab.clear();

        for (int var3 = 0; var3 < var2; ++var3) {
            String data = var1.readUTF();
            this.ab.add(data);
            writerb.writeUTF(data);
        }

        this.ab.clear();
        this.ab.add("mapscale.zip");
        this.ab.add("mapmini.zip");
        this.ab.add("map.zip");
        this.ab.add("iconTree.zip");
        this.ab.add("iconClient.zip");
        this.ab.add("iconChar.zip");
    }

    public static Writer writerc = new Writer();

    private void c(Reader var1) throws java.io.IOException {
        writerc = new Writer();
        GoiRong_fx[] var2 = new GoiRong_fx[var1.readUnsignedByte()];
        writerc.writeByte(var2.length);

        for (int var3 = 0; var3 < var2.length; ++var3) {
            var2[var3] = new GoiRong_fx();
            var2[var3].a = var1.dis.readByte();
            var2[var3].b = new GoiRong_fv[var1.readUnsignedByte()];

            for (int var4 = 0; var4 < var2[var3].b.length; ++var4) {
                var2[var3].b[var4] = new GoiRong_fv();
                var2[var3].b[var4].a = var1.dis.readShort();
                var2[var3].b[var4].b = var1.dis.readShort();
                var2[var3].b[var4].c = var1.dis.readShort();
                var2[var3].b[var4].d = var1.dis.readByte();
            }

            var2[var3].write(writerc);
        }

        this.y = var2;
    }

    private void d(Reader var1) throws java.io.IOException {

        byte[][] var2 = new byte[var1.dis.readByte()][];

        for (int var3 = 0; var3 < var2.length; ++var3) {
            var2[var3] = new byte[var1.dis.readByte()];

            for (int var4 = 0; var4 < var2[var3].length; ++var4) {
                var2[var3][var4] = var1.dis.readByte();
            }
        }

        if (this.Y == null || this.Y.length == 0) {
            this.Y = var2;
        }
    }

    private void e(Reader var1) throws java.io.IOException {
        GoiRong_gs.a = var1.dis.readShort();
        GoiRong_gs.b = var1.dis.readShort();
        short var2 = var1.readUnsignedByte();
        GoiRong_gs[] var3 = new GoiRong_gs[var1.dis.readShort()];

        int var5;
        for (int var4 = 0; var4 < var3.length; ++var4) {
            var3[var4] = new GoiRong_gs();
            var3[var4].c = var4;
            var3[var4].d = var1.dis.readByte();
            if (var3[var4].d == 9) {
                var3[var4].d = 1;
            }

            var3[var4].f = new GoiRong_gv[var2];

            for (var5 = 0; var5 < var2; ++var5) {
                var3[var4].f[var5] = new GoiRong_gv(var1.dis.readShort());
                if (var3[var4].f[var5].a != 0) {
                    var3[var4].f[var5].c = var1.dis.readByte();
                    var3[var4].f[var5].b = var1.dis.readShort();
                    var3[var4].f[var5].d = var1.dis.readShort();
                    var3[var4].f[var5].e = var1.dis.readShort();
                    var3[var4].f[var5].f = var1.dis.readBoolean();
                    a(var3[var4].f[var5].a);
                }
            }
        }

        // this.z = var3;
        GoiRong_gu[] var12 = new GoiRong_gu[var1.dis.readShort()];

        for (var5 = 0; var5 < var12.length; ++var5) {
            var12[var5] = new GoiRong_gu();
            byte var9 = var1.dis.readByte();
            var12[var5].a = new short[var9];

            int var11;
            for (var11 = 0; var11 < var9; ++var11) {
                var12[var5].a[var11] = var1.dis.readShort();
            }

            var9 = var1.dis.readByte();
            var12[var5].b = new short[var9];

            for (var11 = 0; var11 < var9; ++var11) {
                var12[var5].b[var11] = var1.dis.readShort();
            }
        }

        //  this.A = var12;
        GoiRong_gt[] var15 = new GoiRong_gt[var1.dis.readShort()];
        for (int var10 = 0; var10 < var15.length; ++var10) {
            var15[var10] = new GoiRong_gt();
            String[] var14;
            String[] var13 = (var14 = (var15[var10].UTF = var1.readUTF()).split(";"))[0].split(",");
            var15[var10].a[0] = new short[var13.length];

            int var6;
            for (var6 = 0; var6 < var13.length; ++var6) {
                try {
                    var15[var10].a[0][var6] = Short.parseShort(var13[var6]);
                } catch (Exception var7) {
                    var15[var10].a[0][var6] = 0;
                }
            }

            if (var14.length > 1) {
                var13 = var14[1].split(",");
                var15[var10].a[1] = new short[var13.length];

                for (var6 = 0; var6 < var13.length; ++var6) {
                    try {
                        var15[var10].a[1][var6] = Short.parseShort(var13[var6]);
                    } catch (Exception var8) {
                        var15[var10].a[1][var6] = 0;
                    }
                }
            }

            var15[var10].UTF2 = var1.readUTF();
            var15[var10].UTF3 = var1.readUTF();
        }

        // this.B = var15;
    }

    private void f(Reader var1) throws java.io.IOException {
        GoiRong_em[] var2 = new GoiRong_em[var1.dis.readShort()];

        for (int var3 = 0; var3 < var2.length; ++var3) {
            var2[var3] = new GoiRong_em();
            var2[var3].a = var1.dis.readByte();
            var2[var3].b = var1.dis.readByte();
            var2[var3].c = var1.dis.readByte();
            var2[var3].d = var1.dis.readByte();
            var2[var3].e = var1.dis.readShort();
            var2[var3].j = var1.dis.readBoolean();
            var2[var3].k = var1.dis.readBoolean();
            var2[var3].f = var1.dis.readShort();
            var2[var3].g = var1.dis.readShort();
            var2[var3].h = var1.dis.readShort();
            var2[var3].i = var1.dis.readShort();
            if (var2[var3].i < 25) {
                var2[var3].i = 25;
            }

            var2[var3].l = new short[var1.dis.readByte()][];
            String var4 = "";

            int var5;
            int var6;
            for (var5 = 0; var5 < var2[var3].l.length; ++var5) {
                var2[var3].l[var5] = new short[var1.dis.readByte()];

                for (var6 = 0; var6 < var2[var3].l[var5].length; ++var6) {
                    var2[var3].l[var5][var6] = var1.dis.readShort();
                    var4 = var4 + var2[var3].l[var5][var6] + ", ";
                }

                var4 = var4 + "; ";
            }

            var2[var3].m = new short[var1.dis.readByte()][];

            for (var5 = 0; var5 < var2[var3].m.length; ++var5) {
                var2[var3].m[var5] = new short[var1.dis.readByte()];

                for (var6 = 0; var6 < var2[var3].m[var5].length; ++var6) {
                    var2[var3].m[var5][var6] = var1.dis.readShort();
                }
            }

        }

//        if (this.C == null || this.C.length == 0) {
//            this.C = var2;
//        }
    }

    private void g(Reader var1) throws java.io.IOException {
        Vector var2 = new Vector();
        GoiRong_eq[] var3 = new GoiRong_eq[var1.dis.readShort()];
        for (int var4 = 0; var4 < var3.length; ++var4) {
            var3[var4] = new GoiRong_eq();
            var3[var4].a = var1.dis.readByte();
            var3[var4].b = new GoiRong_er[var1.readUnsignedByte()];

            for (int var5 = 0; var5 < var3[var4].b.length; ++var5) {
                var3[var4].b[var5] = new GoiRong_er();
                var3[var4].b[var5].a = var1.dis.readShort();
                if (!var2.contains(var3[var4].b[var5].a)) {
                    var2.add(var3[var4].b[var5].a);
                }

                a(var3[var4].b[var5].a);
                var3[var4].b[var5].b = var1.dis.readShort();
                var3[var4].b[var5].c = var1.dis.readShort();
                var3[var4].b[var5].d = var1.dis.readShort();
                var3[var4].b[var5].e = var1.dis.readBoolean();
            }
        }

//        if (this.D == null || this.D.length == 0) {
//            this.D = var3;
//            this.aa.clear();
//            this.aa.addAll(var2);
//        }
    }

    private void h(Reader var1) throws java.io.IOException {
        Hashtable var2 = new Hashtable();
        short var3 = var1.dis.readShort();

        for (int var4 = 0; var4 < var3; ++var4) {
            GoiRong_hv var5;
            (var5 = new GoiRong_hv()).a = var1.dis.readShort();
            var5.b = var1.dis.readShort();
            var5.c = var1.dis.readShort();
            var5.d = var1.dis.readByte();
            var5.e = new GoiRong_hw[var1.readUnsignedByte()][];

            for (int var6 = 0; var6 < var5.e.length; ++var6) {
                var5.e[var6] = new GoiRong_hw[var1.readUnsignedByte()];

                for (int var7 = 0; var7 < var5.e[var6].length; ++var7) {
                    var5.e[var6][var7] = new GoiRong_hw();
                    var5.e[var6][var7].a = var1.readUnsignedByte();
                    var5.e[var6][var7].b = var1.dis.readShort();
                    var5.e[var6][var7].c = var1.dis.readShort();
                    var5.e[var6][var7].d = var1.dis.readShort();
                    var5.e[var6][var7].e = var1.dis.readShort();
                    var5.e[var6][var7].f = var1.dis.readShort();
                    var5.e[var6][var7].g = var1.dis.readShort();
                }
            }

            var2.put(var5.a, var5);
        }
//
//        if (this.E == null || this.E.size() == 0) {
//            this.E = var2;
//        }

    }

    private void i(Reader var1) throws java.io.IOException {
        GoiRong_ha[] var2 = new GoiRong_ha[var1.dis.readShort()];

        for (int var3 = 0; var3 < var2.length; ++var3) {
            var2[var3] = new GoiRong_ha();
            var2[var3].a = var1.dis.readByte();
            var2[var3].b = new GoiRong_gz[var1.readUnsignedByte()];

            for (int var4 = 0; var4 < var2[var3].b.length; ++var4) {
                var2[var3].b[var4] = new GoiRong_gz();
                var2[var3].b[var4].a = var1.dis.readShort();
                var2[var3].b[var4].b = var1.dis.readByte();
                var2[var3].b[var4].c = var1.dis.readShort();
                var2[var3].b[var4].d = var1.dis.readShort();
            }
        }

    }

    private void j(Reader var1) throws java.io.IOException {
        short[][] var2 = new short[var1.dis.readShort()][14];

        for (int var3 = 0; var3 < var2.length; ++var3) {
            var2[var3][0] = var1.dis.readShort();
            var2[var3][1] = var1.dis.readShort();
            var2[var3][2] = var1.dis.readShort();
            var2[var3][3] = var1.dis.readShort();
            var2[var3][4] = var1.dis.readShort();
            var2[var3][10] = var1.dis.readShort();
            var2[var3][11] = var1.dis.readShort();

            var2[var3][5] = var1.dis.readShort();
            var2[var3][6] = var1.dis.readShort();
            var2[var3][7] = var1.dis.readShort();
            var2[var3][8] = var1.dis.readShort();
            var2[var3][9] = var1.dis.readShort();
            var2[var3][12] = var1.dis.readShort();
            var2[var3][13] = var1.dis.readShort();

        }
        if (dataWayPoint == null || dataWayPoint.length == 0) {
            this.dataWayPoint = var2;
        }
    }

    private static Hashtable k(Reader var0) throws java.io.IOException {
        Hashtable var1 = new Hashtable();
        short var2 = var0.dis.readShort();

        for (short var4 = 0; var4 < var2; ++var4) {
            short var3 = var0.dis.readShort();

            for (int var5 = 0; var5 < var3; ++var5) {
                short short1 = var0.dis.readShort();
                GoiRong_dy data = new GoiRong_dy(var4, var0.readUnsignedByte(), var0.readUnsignedByte(), var0.dis.readShort(), var0.dis.readShort());

                var1.put(Short.valueOf(short1), data);

            }
        }

        return var1;
    }

    private static Hashtable l(Reader var0) throws java.io.IOException {

        Hashtable var1 = new Hashtable();
        short var2 = var0.dis.readShort();

        for (short var3 = 0; var3 < var2; ++var3) {
            short short1 = var0.dis.readShort();

            GoiRong_dy data = new GoiRong_dy((short) -1, (short) 0, (short) 0, var0.dis.readShort(), var0.dis.readShort());

            var1.put(Short.valueOf(short1), data);
        }

        return var1;
    }

    private static void m(Reader var0) throws java.io.IOException {
        GoiRong_hq[] var1 = new GoiRong_hq[var0.dis.readByte()];

        for (int var2 = 0; var2 < var1.length; ++var2) {
            var1[var2] = new GoiRong_hq();
            var1[var2].a = var0.readUTF();
            var1[var2].b = new GoiRong_hp[var0.dis.readByte()];

            for (int var3 = 0; var3 < var1[var2].b.length; ++var3) {
                var1[var2].b[var3] = new GoiRong_hp();
                var1[var2].b[var3].a = var0.dis.readShort();
                var1[var2].b[var3].b = var0.readUTF();
                var1[var2].b[var3].c = var0.readUTF();
                var1[var2].b[var3].d = var0.dis.readShort();
                var1[var2].b[var3].e = var0.dis.readShort();
            }
        }

    }

    private static void a(short var0) throws java.io.IOException {

    }

    public final long getLevel(int var1) {
        long var2 = 0L;

        for (int var4 = 0; var4 < var1 && var4 < this.ay.length; ++var4) {
            var2 += this.ay[var4];
        }

        return var2;
    }
}
