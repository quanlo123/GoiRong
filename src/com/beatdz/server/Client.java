package com.beatdz.server;

import com.beatdz.data.MapTemplate;
import com.beatdz.datareal.Map;
import com.beatdz.lib.Utlis;
import com.beatdz.network.Message;
import com.beatdz.network.Reader;
import com.beatdz.network.Service;
import com.beatdz.network.Writer;
import com.beatdz.network.mSocket;
import com.beatdz.real.Char;
import com.beatdz.real.Entity;
import com.beatdz.real.WayPoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    private static byte selectChar;

    public static Client createClient(mSocket socket) {
        System.out.println("Log");
        Client client = new Client();
        try {
            client.session = new Session(client, socket);
            client.service = new Service(client);
            client.myChar = new Char(Server.listClient.size());
            client.myChar.client = client;
            Server.listClient.add(client);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return client;
    }

    public Session session;
    public Service service;
    public Char myChar;
    public Thread threadUpdate;
    public boolean isOnline;

    public void start() {
        session.start();
    }

    public void close() {
        this.offline();
        if (Server.listClient.contains(this)) {
            Server.listClient.remove(this);
        }
    }

    public void offline() {
        Map.listMap.get(this.myChar.mapID).removeChar(this.myChar);
        isOnline = false;
        try {

            if (threadUpdate != null) {
                threadUpdate.interrupt();
            }
            threadUpdate = null;
        } catch (Exception ex) {
        }
        //this.myChar.updateToAllFriend(false);
    }

    public void online() {
        isOnline = true;
        this.myChar.updateMyChar(false);
        this.threadUpdate = new Thread(()
                -> {
            while (isOnline) {
                try {

                } catch (Exception ex) {

                } finally {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {

                    }
                }
            }
            System.gc();
        });
        this.threadUpdate.start();
        //this.myChar.updateToAllFriend(true);
    }

    public static class Session {

        public Client client;
        public mSocket socket;
        public Writer writer;
        public Reader reader;

        public Thread recvSession;
        public Thread sendSession;
        public Thread timeOutSession;

        public ArrayList<Message> listMessage = new ArrayList();
        public Object waitSendSession = new Object();
        public Object waitTimeOut = new Object();
        private boolean isConnect;
        private boolean isStartTimeOut;

        public void sendMessage(Message message) {
            if (this.isConnect()) {
                this.listMessage.add(message);
                synchronized (waitSendSession) {
                    try {
                        waitSendSession.notify();
                    } catch (Exception ex) {

                    }
                }
            }

        }

        private void createTimeOut(int i) {
            isStartTimeOut = true;
            if (timeOutSession == null || !timeOutSession.isAlive()) {
                timeOutSession = new Thread(()
                        -> {
                    try {
                        synchronized (waitTimeOut) {
                            try {
                                waitTimeOut.wait(i);
                            } catch (Exception ex) {

                            }
                        }
                        if (isStartTimeOut) {
                            this.close();
                            stopTimeOut();
                        }
                    } catch (Exception ex) {
                    }
                });
                timeOutSession.start();
            }
        }

        private void stopTimeOut() {
            if (isStartTimeOut) {
                isStartTimeOut = false;
                synchronized (waitTimeOut) {
                    try {
                        waitTimeOut.notify();
                    } catch (Exception ex) {

                    }
                    try {
                        if (timeOutSession != null) {
                            timeOutSession.interrupt();
                            timeOutSession = null;
                        }
                    } catch (Exception ex) {

                    }
                }
            }
        }

        private Session(Client client, mSocket socket) {
            this.client = client;
            this.socket = socket;
            this.writer = socket.getWriter();
            this.reader = socket.getReader();
            this.recvSession = new ThreadRecvSession(this);
            this.sendSession = new ThreadSendSession(this);
        }

        void start() {
            this.isConnect = true;
            this.recvSession.start();
            this.sendSession.start();
        }

        public boolean isConnect() {
            return this.socket != null && this.socket.isConnect() && isConnect;
        }

        public void close() {
            if (!isConnect) {
                return;
            }

            isConnect = false;
            this.listMessage.clear();
            synchronized (waitSendSession) {
                try {
                    waitSendSession.notify();
                } catch (Exception ex) {

                }
            }
            try {
                if (socket != null) {
                    socket.close();
                    socket = null;
                }
            } catch (Exception ex) {

            }
            try {
                if (writer != null) {
                    writer.close();
                    writer = null;
                }
            } catch (Exception ex) {

            }
            try {
                if (reader != null) {
                    reader.close();
                    reader = null;
                }
            } catch (Exception ex) {

            }
            try {
                if (recvSession != null) {
                    recvSession.interrupt();
                }
                recvSession = null;

            } catch (Exception ex) {

            }
            try {
                if (sendSession != null) {
                    sendSession.interrupt();
                }
                sendSession = null;

            } catch (Exception ex) {

            }
            this.client.close();
            System.gc();
        }

        public void readMessage() throws IOException {
            byte cmd1 = 0;
            byte cmd = reader.dis.readByte();
            //System.out.println("readMessage() "+cmd);

            int size = 0;
            int byte1;
            int byte2;
            int byte3;
            switch (cmd) {
                case -82:
                    int[] arrayXY = new int[]{this.client.myChar.x + reader.dis.readByte(), this.client.myChar.y};
                    this.client.myChar.updateXY(arrayXY);
                    Map.listMap.get(this.client.myChar.mapID).listZone.get(this.client.myChar.zoneID).sendMove(this.client.myChar, true);
                    return;
                case -83:
                    arrayXY = new int[]{this.client.myChar.x, this.client.myChar.y + reader.dis.readByte()};
                    this.client.myChar.updateXY(arrayXY);
                    Map.listMap.get(this.client.myChar.mapID).listZone.get(this.client.myChar.zoneID).sendMove(this.client.myChar, true);
                    return;
                case -84:
                    byte1 = (reader.dis.readByte());
                    byte2 = (reader.dis.readByte() & 255);
                    byte3 = (reader.dis.readByte() & 255);
                    this.client.myChar.updateXY(Utlis.getXYReal(byte1, byte2, byte3, DataCenter.gI().I[this.client.myChar.mapID].x));
                    Map.listMap.get(this.client.myChar.mapID).listZone.get(this.client.myChar.zoneID).sendMove(this.client.myChar, true);
                    return;
                case 123:
                    byte1 = (reader.dis.readByte());
                    byte2 = (reader.dis.readByte() & 255);
                    byte3 = (reader.dis.readByte() & 255);
                    this.client.myChar.updateXY(Utlis.getXYReal(byte1, byte2, byte3, DataCenter.gI().I[this.client.myChar.mapID].x));
                    Map.listMap.get(this.client.myChar.mapID).listZone.get(this.client.myChar.zoneID).sendMove(this.client.myChar, false);
                    return;
                case 124:
                    arrayXY = new int[]{this.client.myChar.x, this.client.myChar.y + reader.dis.readByte()};
                    this.client.myChar.updateXY(arrayXY);
                    Map.listMap.get(this.client.myChar.mapID).listZone.get(this.client.myChar.zoneID).sendMove(this.client.myChar, false);
                    return;
                case 125:
                    arrayXY = new int[]{this.client.myChar.x + reader.dis.readByte(), this.client.myChar.y};
                    this.client.myChar.updateXY(arrayXY);
                    Map.listMap.get(this.client.myChar.mapID).listZone.get(this.client.myChar.zoneID).sendMove(this.client.myChar, false);
                    return;
                case 61:
                    int indexSkill = reader.dis.readByte();
                    this.client.service.useSkill(indexSkill);
                    return;
                case -65:
                case -60:
                    indexSkill = reader.dis.readByte();
                    int idMob;
                    if (cmd == -65) {
                        idMob = reader.dis.readByte();
                    } else {
                        idMob = reader.dis.readShort();
                    }
                    this.client.service.attackMob(indexSkill, idMob);
                    return;
                case -64:
                    reader.dis.readByte();
                    reader.dis.readByte();
                    return;
                case -87:
                    reader.dis.readByte();
                    reader.dis.readShort();
                    return;
                case -81:
                    reader.dis.readByte();
                    reader.dis.readInt();
                    return;
                case 20:
                    reader.dis.readByte();
                    reader.dis.readInt();
                    return;
                case -80:
                    cmd1 = cmd;
                    cmd = reader.dis.readByte();
                    size = (reader.dis.readByte() & 255 >> 8) + (reader.dis.readByte() & 255);
                    break;
                default:
                    size = reader.dis.readUnsignedShort();
                    break;
            }
            byte[] array = new byte[size];
            if (size > 0) {
                int var24 = 0;
                while (size > 0) {
                    int var8;
                    if (size - 2048 <= 0) {
                        var8 = size;
                    } else {
                        var8 = 2048;
                    }

                    int var26;
                    if ((var26 = this.reader.dis.available()) == 0) {
                        Utlis.sleep(1L);
                    } else {
                        if (var8 > var26) {
                            var8 = var26;
                        }

                        this.reader.dis.read(array, var24, var8);
                        var24 += var8;
                        size -= var8;
                    }
                }
                if (cmd1 == -80) {
                    array = Utlis.inflateByteArray(array);
                }
            }
            readMessage(new Message(cmd, array));
        }

        private void readMessage(Message msg) {
            byte cmd = msg.cmd;
            if (cmd != -123 && cmd != -122 && cmd != 122 && cmd != -124) {
                System.out.println("readMessage() => " + cmd);
            }
            try {
                switch (cmd) {
                    case 59:
                        int idEntityItemMap = msg.readShort();
                        Map.listMap.get(this.client.myChar.mapID).listZone.get(this.client.myChar.zoneID).charPickUpItem(this.client, idEntityItemMap);
                        break;
                    case -25:
                        int index = msg.readShort();
                        byte byte1 = -1;
                        byte byte2 = -1;
                        try {
                            byte1 = msg.readByte();
                            byte2 = msg.readByte();
                        } catch (Exception ex) {

                        }
                        if (byte1 >= 0) {
                            this.client.service.actionTauBay(index, byte1, byte2);
                        }
                        break;
                    case 35:
                        int indexChangeItemBody = msg.readByte();
                        this.client.service.changeItemBody(indexChangeItemBody);
                        break;
                    case 36:
                        this.client.service.itemBagToBody2(msg.readShort());
                        break;
                    case 37:
                        this.client.service.itemBody2ToBag(msg.readByte());
                        break;
                    case 116:
                        this.client.service.useItem(msg.readShort());
                        break;
                    case 113:
                        this.client.service.itemBodyToBag(msg.readByte());
                        break;
                    case 13:
                        this.client.service.viewMob(msg.readShort());
                        break;
                    case -61:
                        this.unSleepTau();
                        break;
                    case 14:
                        this.client.service.learnSkill(msg);
                        break;

                    case -7:
                        this.client.service.changeZone(msg.readByte());
                        break;
                    case 54:
                        Map.listMap.get(this.client.myChar.mapID).listZone.get(this.client.myChar.zoneID).openNpc(this.client, msg.readShort(), null);
                        break;
                    case 53:
                        int[] array = new int[2];
                        short idNpc = msg.readShort();
                        array[0] = -1;
                        array[1] = -1;
                        try {
                            array[0] = msg.readByte();
                            array[1] = msg.readByte();
                        } catch (Exception ex) {

                        }
                        Map.listMap.get(this.client.myChar.mapID).listZone.get(this.client.myChar.zoneID).openNpc(this.client, idNpc, array);
                        break;
                    case 21:
                        String strText = msg.readUTF();
                        if (strText.length() <= 300) {
                            this.client.service.sendChatPublic(strText);
                        }
                        break;
                    case 22:
                        boolean isVip = msg.readBoolean();
                        strText = msg.readUTF();
                        if (strText.length() <= 300) {
                            this.client.service.sendChatWorld(strText, isVip);
                        }
                        break;
                    case 127:
                        MapTemplate map = DataCenter.gI().I[this.client.myChar.mapID];
                        if (map.listWayPoint.size() > 0) {
                            WayPoint way = null;
                            for (int i = 0; i < map.listWayPoint.size(); i++) {
                                if (way == null || map.listWayPoint.get(i).getRange(this.client.myChar) <= way.getRange(this.client.myChar)) {
                                    way = map.listWayPoint.get(i);
                                }
                            }
                            if (way == null || way.getRange(this.client.myChar) > 120) {
                                this.client.service.setXY(this.client.myChar.listXY.get(1)[0], this.client.myChar.listXY.get(1)[1]);
                            } else {
                                this.client.service.sendMap(way.mapIDNext);
                            }
                        } else {
                            this.client.service.setXY(this.client.myChar.listXY.get(1)[0], this.client.myChar.listXY.get(1)[1]);
                        }
//                        this.client.service.sendMessage("Chức năng này chưa được mở!.", Service.ColorMessage.YELLOW);
                        break;
                    case -15:
                        this.client.service.sendMessage("Chức năng này chưa được mở!.", Service.ColorMessage.YELLOW);
                        break;
                    case 122:
                        readData122InGame(msg);
                        break;
                    case -127:
                        readDataOS(msg);
                        break;
                    case -113:
                        readDataType(msg);
                        break;
                    case -123:
                        readData123(msg);
                        break;
                    case -124:
                        readData124(msg);
                        break;
                }
            } catch (Exception ex) {
                System.out.println("readMessage Err :" + cmd);
                ex.printStackTrace();
            } finally {
                msg.cleanup();
            }
        }

        private void readData123(Message msg) throws IOException {
            byte cmd = msg.readByte();
            System.out.println("readData123() => " + cmd);
            try {
                switch (cmd) {
                    case -48:
                        this.client.service.actionMoveTele(msg);
                        break;
                    case -73:
                        String name = msg.readUTF();
                        if (name.equals(this.client.myChar.name)) {
                            this.client.service.sendDataInfo1(this.client.myChar);
                        } else if (Server.checkOnline(name)) {

                        }

                        break;
                    case -126:
                        this.createTimeOut(30000);
                        this.client.service.showTabLogin();
                        break;
                    case -127:
                        String username = msg.readUTF();
                        String password = msg.readUTF();
                        int ver1 = msg.readInt();
                        int ver2 = msg.readInt();
                        if (username.length() <= 0 || password.length() <= 0 || !Server.checkAuthLogin(username, password)) {
                            this.client.service.sendMessage("Tài khoản hoặc mật khẩu không chính xác!", Service.ColorMessage.WHITE);
                            this.createTimeOut(30000);
                            return;
                        }
                        this.stopTimeOut();
                        this.client.service.sendData2();
                        this.client.service.showTabSelectChar();

                        break;
                }
            } catch (Exception ex) {
                System.out.println("readData123 Err :" + cmd);
                ex.printStackTrace();
            }
        }

        private void readData124(Message msg) throws IOException {
            byte cmd = msg.readByte();
            System.out.println("readData124() => " + cmd);
            try {
                switch (cmd) {
                    case -128:
                        byte selectChar = msg.readByte();
                        String nameChar = msg.readUTF();
                        if (selectChar >= 0 && selectChar <= 3) {
                            this.client.myChar.name = String.valueOf(this.client.myChar.idEntity);
                            if (nameChar.length() > 0) {
                                this.client.myChar.name = nameChar;
                            } else if (this.client.myChar.idEntity == 0 || this.client.myChar.name.equals("DungDz")) {
                                this.client.myChar.name = "DungDz";
                            } else {
                                this.client.myChar.name = "1";
                            }
                            this.client.myChar.idTypeChar = selectChar;
                            this.client.myChar.indexTypeChar = (byte) (selectChar + 1);
                            this.client.myChar.loadSkill();
                            this.client.online();
                            this.client.service.sendDataChar();
                            this.client.service.showGameScreen();
                            this.client.service.sendOnlineInMap();
                            this.client.service.setColorDanhHieu("Beo dep trai", com.badlogic.gdx.graphics.Color.YELLOW.toIntBits(), com.badlogic.gdx.graphics.Color.YELLOW.toIntBits());
                        } else {
                            this.client.service.sendMessage("Nhân vật bạn chọn không tồn tại!", Service.ColorMessage.RED);
                        }
                        break;
                }
            } catch (Exception ex) {
                System.out.println("readData124 Err :" + cmd);
                ex.printStackTrace();
            }
        }

        private void readData122InGame(Message msg) throws IOException {
            byte cmd = msg.readByte();
            System.out.println("readData122InGame() => " + cmd);
            try {
                switch (cmd) {

                }
            } catch (Exception ex) {
                System.out.println("readData122InGame Err :" + cmd);
                ex.printStackTrace();
            }
        }

        private void readDataOS(Message message) throws IOException {
            String keyAppClient = message.reader().readUTF();
            String userData = new String(message.reader().read(), "UTF-8");
            if (keyAppClient.equals(Server.keyApp)) {
                //System.out.println(keyAppClient);
                //System.out.println(userData);
            } else {
                this.close();
                return;
            }
        }

        private void readDataType(Message msg) throws IOException {
            byte khongbiet1 = msg.readByte();
            byte typeOS = msg.readByte();//0 = Android,1 = IOS,2 = PC,5 = Test
            short sizeScreenNgang = msg.readShort();
            short sizeScreenDoc = msg.readShort();
            byte zoomLevel = msg.readByte();
            byte khongbiet2 = msg.readByte();
            byte zoomLevel2 = msg.readByte();
        }

        public Object waitSleepTau = new Object();

        public boolean sleepTau(int i) {
            long time = System.currentTimeMillis();
            try {
                synchronized (waitSleepTau) {
                    try {
                        waitSleepTau.wait(i);
                    } catch (Exception ex) {

                    }
                }
                if (this.isConnect()) {
                    return System.currentTimeMillis() - time < i;
                }
                return false;
            } catch (Exception ex) {
                return false;
            }
        }

        public void unSleepTau() {
            try {
                synchronized (waitSleepTau) {
                    try {
                        waitSleepTau.notify();
                    } catch (Exception ex) {

                    }
                }
            } catch (Exception ex) {

            }
        }

        public static class ThreadRecvSession extends Thread {

            private Session session;

            private ThreadRecvSession(Session session) {
                this.session = session;
            }

            public void run() {
                while (this.session.isConnect()) {
                    try {
                        this.session.readMessage();
                    } catch (Exception ex) {
                        //ex.printStackTrace();
                        this.session.close();
                        this.session = null;
                        return;
                    }
                }
            }
        }

        public static class ThreadSendSession extends Thread {

            private Session session;

            private ThreadSendSession(Session session) {
                this.session = session;
            }

            public void run() {
                while (this.session.isConnect()) {
                    try {
                        while (this.session.listMessage.size() > 0) {
                            Message message = this.session.listMessage.get(0);
                            this.session.listMessage.remove(0);
                            if (message != null) {
                                byte[] array = message.getData();
                                switch (message.cmd) {
                                    case -92:
                                    case -91:
                                    case -87:
                                    case -81:
                                    case -65:
                                    case -64:
                                    case -63:
                                    case -41:
                                    case 20:
                                    case 61:
                                    case 123:
                                    case -84:
                                        this.session.writer.dos.writeByte(message.cmd);
                                        this.session.writer.dos.write(array);
                                        break;
                                    default:
                                        if (!message.isNen) {
                                            this.session.writer.dos.writeByte(message.cmd);
                                            if (array != null && array.length > 0) {
                                                this.session.writer.dos.writeShort(array.length);
                                                this.session.writer.dos.write(array);
                                            } else {
                                                this.session.writer.dos.writeShort(0);
                                            }
                                        } else {
                                            this.session.writer.dos.writeByte(-80);
                                            this.session.writer.dos.writeByte(-122);
                                            array = Utlis.deflateByteArray(Server.arrData2);
                                            if (array != null && array.length > 0) {
                                                this.session.writer.dos.writeShort(array.length);
                                                this.session.writer.dos.write(array);
                                            } else {
                                                this.session.writer.dos.writeShort(0);
                                            }
                                        }
                                        break;

                                }
                                this.session.writer.dos.flush();
                            }

                        }
                        synchronized (this.session.waitSendSession) {
                            try {
                                this.session.waitSendSession.wait(3600000);
                            } catch (Exception ex) {

                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        this.session.close();
                        this.session = null;
                        return;
                    }
                }
            }
        }

    }
}
