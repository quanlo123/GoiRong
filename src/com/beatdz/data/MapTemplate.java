package com.beatdz.data;

import beatdz.sg316.*;
import com.beatdz.lib.Utlis;
import com.beatdz.network.Reader;
import com.beatdz.network.Writer;
import com.beatdz.real.Entity;
import com.beatdz.real.WayPoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class MapTemplate {

    public short id;
    public String name;
    public short c;
    public byte type;
    public byte typePlanet;
    public int x = -1;
    public ArrayList<WayPoint> listWayPoint = new ArrayList();
    public ArrayList<WayPoint> listWayPointIn = new ArrayList();
    public byte[] byteArray;

    public MapTemplate(int var1) {
        this.id = (short) var1;
    }

    public final boolean isMapHoatDong() {
        try {
            if (this.type == 4 || this.type == 14 || this.type == 15 || this.type == 5 || this.type == 7 || this.type == 8 || this.type == 6 || this.type == 9 || this.type == 10 || this.type == 12 || this.type == 13 || this.type == 16 || this.type == 17 || this.type == 19 || this.type == 20 || this.type == 21) {
                return true;
            }
        } catch (Exception var1) {
        }

        return false;
    }

    @Override
    public String toString() {
        return "MapTemplate{" + "id=" + id + ", name=" + name + ", c=" + c + ", type=" + type + ", typePlanet=" + typePlanet + '}';
    }

    public void write(Writer writer) {
        try {
            writer.writeUTF(name);
            writer.writeByte(c);
            writer.writeByte(type);
            writer.writeByte(typePlanet);
            writer.write(byteArray);
        } catch (IOException ex) {
        }

    }

    private static GoiRong_ft b(int var0, int var1, int var2, int var3) {
        GoiRong_ft var4;
        (var4 = new GoiRong_ft()).b = new Entity[5];
        var4.b[0] = Entity.create(var0, var1);
        var4.b[1] = Entity.create(var2, var1);
        var4.b[2] = Entity.create(var2, var3);
        var4.b[3] = Entity.create(var0, var3);
        var4.b[4] = Entity.create(var0, var1);
        return var4;
    }
    public Vector p = new Vector();
    public short J;
    public short K;
    public GoiRong_fh u;
    public Vector w = new Vector();
    public Vector v = new Vector();
    public Vector vX = new Vector();

    public Vector q = new Vector();

    public void load() {
        try {
            byte[] array = Utlis.read("DataServer\\ArrMap\\arr_map_" + this.id + ".bin");
            if (array == null && byteArray == null) {
                return;
            }
            if (array == null) {
                array = byteArray;
            }
            Reader var1 = new Reader(array);
            this.a(var1);
            this.p.clear();
            new Vector();
       this.x =      this.J = var1.dis.readShort();
            this.K = var1.dis.readShort();
            GoiRong_ft[] var2;
            (var2 = new GoiRong_ft[var1.readUnsignedByte() + 6])[var2.length - 1] = b(-50, -50, 128, 65);
            var2[var2.length - 2] = b(this.J - 100, -50, this.J + 50, 110);
            var2[var2.length - 3] = b(-50, -50, this.J + 50, 15);
            var2[var2.length - 4] = b(-50, -50, 15, this.K + 50);
            var2[var2.length - 5] = b(-50, this.K - 15, this.J + 50, this.K + 50);
            var2[var2.length - 6] = b(this.J - 15, -50, this.J + 50, this.K + 50);

            for (int var3 = 0; var3 < var2.length - 6; ++var3) {
                var2[var3] = new GoiRong_ft();
                var2[var3].b = new Entity[var1.readUnsignedByte()];

                for (int var4 = 0; var4 < var2[var3].b.length; ++var4) {
                    var2[var3].b[var4] = new Entity();
                    var2[var3].b[var4].updateXY(var1.dis.readShort(), var1.dis.readShort());
                    if (var4 == 0) {
                        var2[var3].c = var2[var3].e = var2[var3].b[var4].x;
                        var2[var3].d = var2[var3].f = var2[var3].b[var4].y;
                    } else {
                        if (var2[var3].b[var4].x < var2[var3].c) {
                            var2[var3].c = var2[var3].b[var4].x;
                        }

                        if (var2[var3].b[var4].x > var2[var3].e) {
                            var2[var3].e = var2[var3].b[var4].x;
                        }

                        if (var2[var3].b[var4].y < var2[var3].d) {
                            var2[var3].d = var2[var3].b[var4].y;
                        }

                        if (var2[var3].b[var4].y > var2[var3].f) {
                            var2[var3].f = var2[var3].b[var4].y;
                        }
                    }
                }
            }

            this.u = new GoiRong_fh(this, var2);
            GoiRong_fs[] var25 = new GoiRong_fs[var1.dis.readShort()];
            Vector var27 = new Vector();
            Vector var22 = new Vector();

            int var5;
            int var6;
            for (var5 = 0; var5 < var25.length; ++var5) {
                var25[var5] = new GoiRong_fs();
                var25[var5].a = var1.dis.readBoolean();
                if (var25[var5].a) {
                    var22.add(var25[var5]);
                } else {
                    var27.add(var25[var5]);
                }

                var25[var5].b = new Entity[var1.dis.readShort()];

                for (var6 = 0; var6 < var25[var5].b.length; ++var6) {
                    var25[var5].b[var6] = new Entity();
                    var25[var5].b[var6].updateXY(var1.dis.readShort(), var1.dis.readShort());
                }
            }

            Vector var30 = new Vector();
            var25 = new GoiRong_fs[var27.size()];

            int var7;
            int var8;
            for (var6 = 0; var6 < var27.size(); ++var6) {
                var25[var6] = (GoiRong_fs) var27.get(var6);

                for (var7 = 0; var7 < var25[var6].b.length; ++var7) {
                    if (var30.size() == 0) {
                        var30.add(var25[var6].b[var7]);
                    } else {
                        for (var8 = 0; var8 < var30.size(); ++var8) {
                            if (Utlis.getRange((Entity) var30.get(var8), var25[var6].b[var7]) < 10) {
                                var25[var6].b[var7] = (Entity) var30.get(var8);
                            }
                        }

                        var30.add(var25[var6].b[var7]);
                    }
                }
            }

            this.a(var25);

            GoiRong_fs var31;
            for (var6 = 0; var6 < var22.size(); ++var6) {
                var31 = (GoiRong_fs) var22.get(var6);

                for (var8 = 0; var8 < var31.b.length; ++var8) {
                    Entity var9 = var31.b[var8];
                    if (u.a(var9.x, var9.y)) {
                        Entity var26 = u.b(var9.x, var9.y);
                        var31.b[var8].x = var26.x;
                        var31.b[var8].y = var26.y;
                    }
                }
            }

            this.w.clear();

            for (var6 = 0; var6 < var22.size(); ++var6) {
                var31 = (GoiRong_fs) var22.get(var6);

                for (var8 = 0; var8 < var31.b.length - 1; ++var8) {
                    GoiRong_ft var34;
                    (var34 = new GoiRong_ft()).b = new Entity[2];
                    var34.b[0] = var31.b[var8];
                    var34.b[1] = var31.b[var8 + 1];
                    this.w.add(var34);
                }
            }

            short var32 = var1.dis.readShort();

            for (var7 = 0; var7 < var32; ++var7) {
                var1.dis.readShort();
            }

            this.q.removeAllElements();
            var32 = var1.dis.readShort();
            Short var33 = null;
            int var36 = 0;
            for (var5 = 0; var5 < var32; ++var5) {
                if (var36 == 0) {
                    var33 = Short.valueOf(var1.dis.readShort());
                    this.p.addElement(var33);
                    var36 = var1.dis.readShort();
                }
                var1.dis.readShort();
                var1.dis.readShort();
                var1.dis.readByte();

                if (var36 > 0) {
                    --var36;
                }
            }
        } catch (Exception ex) {

        }
    }

    public GoiRong_iq[] s;
    public GoiRong_fi[] t;

    private void a(Reader var1) throws java.io.IOException {
        this.s = new GoiRong_iq[var1.dis.readUnsignedShort()];
        Vector var4 = new Vector();

        short var2;
        short var3;
        GoiRong_ft var5;
        int var7;
        int var8;
        int var9;
        GoiRong_ft[] var11;
        for (short var6 = 0; var6 < this.s.length; ++var6) {
            var4.removeAllElements();
            this.s[var6] = new GoiRong_iq();
            this.s[var6].a = var1.dis.readShort();
            this.s[var6].b = var1.dis.readShort();
            this.s[var6].c = var1.dis.readByte();
            this.s[var6].d = var1.dis.readShort();
            this.s[var6].e = var1.dis.readShort();
            var2 = var1.readUnsignedByte();

            for (var7 = 0; var7 < var2; ++var7) {
                var3 = var1.readUnsignedByte();

                for (var8 = 0; var8 < var3; ++var8) {
                    (var5 = new GoiRong_ft()).b = new Entity[var1.dis.readByte() / 2];

                    for (var9 = 0; var9 < var5.b.length; ++var9) {
                        var5.b[var9] = new Entity();
                        var5.b[var9].updateXY(var1.dis.readShort(), var1.dis.readShort());
                        if (var9 == 0) {
                            var5.c = var5.e = var5.b[var9].x;
                            var5.d = var5.f = var5.b[var9].y;
                        } else {
                            if (var5.b[var9].x < var5.c) {
                                var5.c = var5.b[var9].x;
                            }

                            if (var5.b[var9].x > var5.e) {
                                var5.e = var5.b[var9].x;
                            }

                            if (var5.b[var9].y < var5.d) {
                                var5.d = var5.b[var9].y;
                            }

                            if (var5.b[var9].y > var5.f) {
                                var5.f = var5.b[var9].y;
                            }
                        }
                    }

                    var4.addElement(var5);
                }
            }

            if (var4.size() > 0) {
                var11 = new GoiRong_ft[var4.size()];

                for (var7 = 0; var7 < var4.size(); ++var7) {
                    var11[var7] = (GoiRong_ft) var4.elementAt(var7);
                }

                this.s[var6].f = new GoiRong_fh(this, var11);
            }
        }

        this.t = new GoiRong_fi[var1.dis.readUnsignedShort()];

        for (int var12 = 0; var12 < this.t.length; ++var12) {
            var4.removeAllElements();
            this.t[var12] = new GoiRong_fi();
            this.t[var12].a = var1.dis.readShort();
            this.t[var12].b = var1.dis.readShort();
            short size = var1.readUnsignedByte();

            for (var7 = 0; var7 < size; ++var7) {
                var1.dis.readShort();
                var1.dis.readShort();
                var1.dis.readShort();
                var1.dis.readByte();

            }

            var2 = var1.readUnsignedByte();

            for (var7 = 0; var7 < var2; ++var7) {
                var3 = var1.readUnsignedByte();

                for (var8 = 0; var8 < var3; ++var8) {
                    (var5 = new GoiRong_ft()).b = new Entity[var1.dis.readByte() / 2];

                    for (var9 = 0; var9 < var5.b.length; ++var9) {
                        var5.b[var9] = new Entity();
                        var5.b[var9].updateXY(var1.dis.readShort(), var1.dis.readShort());
                        if (var9 == 0) {
                            var5.c = var5.e = var5.b[var9].x;
                            var5.d = var5.f = var5.b[var9].y;
                        } else {
                            if (var5.b[var9].x < var5.c) {
                                var5.c = var5.b[var9].x;
                            }

                            if (var5.b[var9].x > var5.e) {
                                var5.e = var5.b[var9].x;
                            }

                            if (var5.b[var9].y < var5.d) {
                                var5.d = var5.b[var9].y;
                            }

                            if (var5.b[var9].y > var5.f) {
                                var5.f = var5.b[var9].y;
                            }
                        }
                    }

                    var4.addElement(var5);
                }
            }

            if (var4.size() > 0) {
                var11 = new GoiRong_ft[var4.size()];

                for (var7 = 0; var7 < var4.size(); ++var7) {
                    var11[var7] = (GoiRong_ft) var4.elementAt(var7);
                }

                this.t[var12].d = new GoiRong_fh(this, var11);
            }
        }

    }

    private void a(GoiRong_fs[] var1) {
        Vector var2 = new Vector();

        int var3;
        int var4;
        Entity var5;
        for (var3 = 0; var3 < var1.length; ++var3) {
            for (var4 = 0; var4 < var1[var3].b.length - 1; ++var4) {
                var5 = var1[var3].b[var4];
                Entity var6 = var1[var3].b[var4 + 1];
                Entity[] var7 = new Entity[]{var5, var6};
                var2.add(var7);
            }
        }

        this.v.clear();

        for (var3 = 0; var3 < var2.size(); ++var3) {
            Entity var12 = ((Entity[]) var2.get(var3))[0];
            var5 = ((Entity[]) var2.get(var3))[1];
            this.a(var12, var5, var2, this.v);
        }

        Vector var11 = new Vector();

        GoiRong_ft var13;
        for (var4 = this.v.size() - 1; var4 >= 0; --var4) {
            var13 = (GoiRong_ft) this.v.get(var4);
            Vector var14 = new Vector();

            for (int var16 = 0; var16 < var13.b.length; ++var16) {
                var14.add(var13.b[var16]);
            }

            Collections.sort(var14, Entity.a);
            String var17 = "";

            for (int var8 = 0; var8 < var14.size(); ++var8) {
                Entity var10 = (Entity) var14.get(var8);
                var17 = var17 + var10.x + "," + var10.y + ";";
            }

            if (!var11.contains(var17)) {
                var11.add(var17);
            } else {
                this.v.remove(var4);
            }
        }

        this.vX.clear();

        for (var4 = this.v.size() - 1; var4 >= 0; --var4) {
            var13 = (GoiRong_ft) this.v.get(var4);

            for (int var15 = 0; var15 < var13.b.length - 1; ++var15) {
                Entity var18 = var13.b[var15];
                Entity var9 = var13.b[var15 + 1];
                this.vX.add(new Entity[]{var18, var9});
            }
        }

    }

    private void a(Entity var1, Entity var2, Vector var3, Vector var4) {
        boolean var5 = false;
        boolean var6 = false;

        for (int var7 = 0; var7 < var3.size(); ++var7) {
            Entity var8 = ((Entity[]) var3.get(var7))[0];
            Entity var9 = ((Entity[]) var3.get(var7))[1];
            if ((!var8.equals(var1) || !var9.equals(var2)) && (!var9.equals(var1) || !var8.equals(var2))) {
                if (var8.equals(var1) || var9.equals(var1)) {
                    var5 = true;
                }

                if (var8.equals(var2) || var9.equals(var2)) {
                    var6 = true;
                }

                if (var5 && var6) {
                    return;
                }
            }
        }

        Vector var10 = new Vector();
        if (var5) {
            var10.add(var2);
            var10.add(var1);
        } else if (var6) {
            var10.add(var1);
            var10.add(var2);
        } else {
            GoiRong_ft var11;
            (var11 = new GoiRong_ft()).b = new Entity[2];
            var11.b[0] = var1;
            var11.b[1] = var2;
            var4.add(var11);
        }

        this.a(var3, var10, var4);
    }

    private void a(Vector var1, Vector var2, Vector var3) {
        if (var2.size() > 1) {
            Entity var4 = (Entity) var2.get(var2.size() - 2);
            Entity var5 = (Entity) var2.get(var2.size() - 1);
            Vector var6 = new Vector();

            int var7;
            for (var7 = 0; var7 < var1.size(); ++var7) {
                Entity var8 = ((Entity[]) var1.get(var7))[0];
                Entity var9 = ((Entity[]) var1.get(var7))[1];
                if ((!var8.equals(var4) || !var9.equals(var5)) && (!var9.equals(var4) || !var8.equals(var5)) && (var8.equals(var5) || var9.equals(var5))) {
                    if (var9.equals(var5)) {
                        var6.add(var8);
                    } else {
                        var6.add(var9);
                    }
                }
            }

            if (var6.size() == 0) {
                GoiRong_ft var11;
                (var11 = new GoiRong_ft()).b = new Entity[var2.size()];

                for (int var13 = 0; var13 < var2.size(); ++var13) {
                    var11.b[var13] = (Entity) var2.get(var13);
                }

                var3.add(var11);
                return;
            }

            try {
                for (var7 = 0; var7 < var6.size(); ++var7) {
                    Vector var12;
                    (var12 = new Vector()).addAll(var2);
                    var12.add(var6.get(var7));
                    this.a(var1, var12, var3);
                }

                return;
            } catch (Exception var10) {
            }
        }

    }

    public boolean isMoveWayPoint(int mapOLD) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
