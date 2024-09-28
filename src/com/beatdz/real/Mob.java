/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beatdz.real;

import com.beatdz.datareal.Map;
import com.beatdz.lib.Utlis;
import com.beatdz.network.Message;
import com.beatdz.server.Client;
import com.beatdz.server.DataCenter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author admin
 */
public class Mob extends Entity implements Cloneable {
    
    public int id;
    public boolean isPaint;
    public int level;
    public int hpGoc;
    public int hpFull;
    public int typeMob;
    public long timeReSpawn;
    public int kiNow = 0;
    public Mob mob2;
    
    public final Mob a() {
        try {
            return (Mob) super.clone();
        } catch (Exception var2) {
            return null;
        }
    }
    
    public void readJson(JSONObject json) {
        try {
            this.id = json.getInt("id");
            this.isPaint = json.getBoolean("isPaint");
            this.level = json.getInt("level");
            this.hpGoc = json.getInt("hpGoc");
            this.hpFull = json.getInt("hpFull");
            this.typeMob = json.getInt("typeMob");
            this.x = (short) json.getInt("x");
            this.y = (short) json.getInt("y");
            loadKiNow();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
    
    public void writeMessage(Message message) throws IOException {
        message.writeShort(this.idEntity);
        message.writeBoolean(isPaint);
        message.writeUTF("");
        message.writeShort(id);
        message.writeShort(x);
        message.writeShort(y);
        
        message.writeShort(level);
        
        message.writeByte(0);
        message.writeByte(isPaint ? 4 : 0);
        message.writeInt(hpGoc);
        message.writeInt(hpFull);
        message.writeByte(typeMob);
        message.writeByte(0);
    }
    
    public CountDame countDame(Client client, int dame) {
        synchronized (listCount) {
            for (int i = 0; i < listCount.size(); i++) {
                if (listCount.get(i).client == client) {
                    return listCount.get(i);
                }
            }
            CountDame count = new CountDame(client, dame);
            listCount.add(count);
            return count;
        }
        
    }
    
    public void actionAttack(Client client, int dame, boolean isMess) {
        Mob.CountDame dameCount = countDame(client, dame);
        dameCount.dame += dame;
        if (hpGoc > 0) {
            hpGoc -= dame;
        }
        float phan_tram_ki = ((float) dameCount.dame * 100.0F) / hpFull;
        if (phan_tram_ki > 10 || hpGoc <= 0) {
            float kiReal = kiNow * phan_tram_ki / 100;
            if (kiReal >= Utlis.nextInt(30, 100) || hpGoc <= 0) {
                int kiRealInt = ((int) kiReal);
                kiRealInt = ((kiRealInt / 10) + 1);
                kiRealInt = kiRealInt * 10;
                kiReal = kiRealInt;
                kiNow -= kiReal;
                dameCount.dame = 0;
                if (hpGoc > 0) {
                    synchronized (listCount) {
                        listCount.remove(dameCount);
                    }
                }
                if (kiReal > 0) {
                    if (Utlis.getRange(DataCenter.gI().H[this.id].h - client.myChar.level()) > 8) {
                        if (!DataCenter.gI().I[client.myChar.mapID].isMapHoatDong() && id != 273) {
                            
                            client.service.sendMessageNoUpKi();
                        }
                    } else {
                        dameCount.addKi(kiReal);
                    }
                }
            }
        }
    }
    
    public void loadKiNow() {
        kiNow = DataCenter.gI().H[this.id].h * 30;
    }
    
    public void dropItemToMap(Client client) {
        Map.listMap.get(client.myChar.mapID).listZone.get(client.myChar.zoneID).addItemMap(client, this, new Item(Utlis.nextInt(DataCenter.gI().P.length)));
    }
    
    public static class CountDame {
        
        public Client client;
        public int dame;
        
        private CountDame(Client client, int dame) {
            this.client = client;
            this.dame = dame;
        }
        
        public void addKi(float kiReal) {
            this.client.myChar.ki = (this.client.myChar.ki + ((long) kiReal / 10));
            this.client.myChar.exp = (this.client.myChar.exp + ((long) kiReal));
            this.client.service.updateKiMe(false);
            this.client.service.updateKi();
            
        }
    }
    public ArrayList<CountDame> listCount = new ArrayList<CountDame>();
}
