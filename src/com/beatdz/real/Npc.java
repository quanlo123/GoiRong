/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beatdz.real;

import com.beatdz.network.Message;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author admin
 */
public class Npc extends Entity implements Cloneable {

    public int id;
    public int status;

    public void readJson(JSONObject json) {
        try {
            this.id = json.getInt("id");
            this.status = json.getInt("status");
            this.x = (short) json.getInt("x");
            this.y = (short) json.getInt("y");
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

    public final Npc a() {
        try {
            return (Npc) super.clone();
        } catch (Exception var2) {
            return null;
        }
    }

    public void writeMessage(Message message) throws IOException {
        message.writeByte(status);
        message.writeShort(id);
        message.writeShort(x);
        message.writeShort(y);
    }

    public boolean isNpcTau() {
        return this.id == 40 || this.id == 42 || this.id == 43 || this.id == 44 || this.id == 45 || this.id == 46;
    }

}
