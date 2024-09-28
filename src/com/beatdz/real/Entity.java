package com.beatdz.real;

import com.beatdz.lib.Utlis;
import java.util.ArrayList;
import java.util.Comparator;

public class Entity {

    public static final Comparator a = new Comparator() {
        @Override // java.util.Comparator
        public final /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
            Entity goiRong_ae = (Entity) obj;
            Entity goiRong_ae2 = (Entity) obj2;
            return goiRong_ae.y == goiRong_ae2.y ? goiRong_ae2.x - goiRong_ae.x : goiRong_ae.y - goiRong_ae2.y;
        }
    };
    public short x = 250;
    public short y = 250;
    public int idEntity;
    public int dir = -1; // -1 left, 1 right
    public ArrayList<int[]> listXY = new ArrayList<int[]>();

    public static Entity create(int x, int y) {
        Entity entity = new Entity();
        if (entity.x < x) {
            entity.dir = 1;
        } else {
            entity.dir = -1;
        }
        entity.x = (short) x;
        entity.y = (short) y;
        return entity;
    }

    public void updateXY(int[] array) {
        if (x < array[0]) {
            dir = 1;
        } else {
            dir = -1;
        }
        if (listXY.size() >= 10) {
            listXY.clear();
            listXY.add(new int[]{this.x, this.y});
        }
        listXY.add(array);
        this.x = (short) array[0];
        this.y = (short) array[1];

    }

    public void updateXY(int x, int y) {
        if (this.x < x) {
            dir = 1;
        } else {
            dir = -1;
        }
        if (listXY.size() >= 10) {
            listXY.clear();
            listXY.add(new int[]{this.x, this.y});
        }
        listXY.add(new int[]{x, y});
        this.x = (short) x;
        this.y = (short) y;
    }

    public void updateXY2(int[] array) {
        if (array[0] != 0 && array[1] != 0) {
            if (x < array[0]) {
                dir = 1;
            } else {
                dir = -1;
            }
            listXY.clear();
            listXY.add(array);
            this.x = (short) array[0];
            this.y = (short) array[1];
        }
    }
//    public Entity(int x, int y) {
//        this.x = (short) x;
//        this.y = (short) y;
//    }

    public int getRange(Entity entity) {
        return Utlis.getRange(entity, this);
    }
}
