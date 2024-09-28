package com.beatdz.real;

import com.beatdz.server.Client;
import com.beatdz.server.Server;

public class Friend {

    public String name;
    public int type;

    public boolean isOnline() {
        if (name == null || name.length() <= 0) {
            name = "";
            return false;
        }
        return getClient() != null;
    }

    public Client getClient() {
        if (name == null || name.length() <= 0) {
            name = "";
            return null;
        }
        return Server.getClientWithNameChar(name);
    }
}
