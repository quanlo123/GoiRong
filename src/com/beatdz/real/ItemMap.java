package com.beatdz.real;

public class ItemMap extends Entity implements Cloneable {

    public Item item;
    public int playerID;
    public long timeCreate;

    public ItemMap() {

    }

    public ItemMap(int id) {
        this.idEntity = id;
    }

    public ItemMap(int id, int idItem) {
        this.idEntity = id;
        this.item = new Item(idItem);
    }

    public ItemMap(int id, Item item) {
        this.idEntity = id;
        this.item = item.a();
    }

   public void create() {

        timeCreate = System.currentTimeMillis();
    }

}
