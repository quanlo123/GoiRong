/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beatdz.real;

public class WayPoint extends Entity {
    
    public short mapID;//map đặt
    public short mapIDNext;//map kế tiếp
    public short h;
    public short i;
    public short j;
    public short k;
    public int l = 0;
    public int m = 0;
    public int n;
    public boolean isNext = false;

    public final void a(short var1, short var2, short var3, short var4, short var5, short var6, short var7, short var8, boolean isNext) {
        this.mapID = var1;
        this.mapIDNext = var2;
        this.h = (short) (var3 - 5);
        this.i = (short) (var4 - 5);
        this.j = (short) (var5 + 5);
        this.k = (short) (var6 + 5);
        this.updateXY(var3 + (var5 - var3) / 2, var6);
        this.l = var7;
        this.m = var8;
        this.isNext = isNext;
    }
    
    public final void a(short var1, short var2, short var3, short var4, short var5, short var6) {
        this.mapID = var1;
        this.mapIDNext = var2;
        this.h = var3;
        this.i = var4;
        this.j = var5;
        this.k = var6;
        this.updateXY(var3 + (var5 - var3) / 2, var4 + (var6 - var4) / 2);
    }
    
}
