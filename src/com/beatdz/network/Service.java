package com.beatdz.network;

import com.beatdz.data.ItemOption;
import com.beatdz.data.MapTemplate;
import com.beatdz.data.Skill;
import com.beatdz.datareal.Map;
import com.beatdz.datareal.Map.Zone;
import com.beatdz.lib.Utlis;
import com.beatdz.real.Char;
import com.beatdz.real.Entity;
import com.beatdz.real.Friend;
import com.beatdz.real.Item;
import com.beatdz.real.ItemMap;
import com.beatdz.real.Mob;
import com.beatdz.server.Client;
import com.beatdz.server.Client.Session;
import com.beatdz.server.DataCenter;
import com.beatdz.server.Server;
import gro.database.retrieve.PlayerRepository;
import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Service {

    public Client client;
    public Session session;
    private long timeNoUpKi;

    public Service(Client client) {
        this.client = client;
        this.session = client.session;
    }

    public void sendMessage(String data, int color) {
        try {
            Message message = null;
            if (color == ColorMessage.RED) {
                message = new Message(-105);
            } else if (color == ColorMessage.YELLOW) {
                message = new Message(-106);
            } else if (color == ColorMessage.WHITE) {
                message = new Message(-107);
            }
            message.writeUTF(data);
            this.session.sendMessage(message);
        } catch (Exception ex) {
        }
    }

    public void sendTabMessage(String data) {
        try {
            Message message = new Message(-110);
            message.writeUTF(data);
            this.session.sendMessage(message);
        } catch (Exception ex) {
        }
    }

    private void sendEmptyCharacterMessage() throws IOException {
        Message message = new Message(-122);
        message.writeByte(-128);
        message.writeByte(0);
        this.session.sendMessage(message);
    }

    public void showTabSelectChar() {
        try {
            Char player = PlayerRepository.gI().loadListPlayer(this.client);
            this.client.myChar = player;
            if (this.client.myChar == null || this.client.myChar.idEntity <= 0) {
                sendEmptyCharacterMessage();
                return;
            }

            Message message = new Message(-122);
            message.writeByte(-128);
            for (int i = 0; i < 1; i++) {
                message.writeByte(1);
                message.writeInt(this.client.myChar.idEntity);
                this.client.myChar.writeDataChar(message, -1);
            }

            this.session.sendMessage(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sendData2() {
        try {
            Message message = new Message(-122);
            message.isNen = true;
            this.session.sendMessage(message);
            String text = "";
        } catch (Exception ex) {
        }
    }

    public void showTabLogin() {
        System.out.println("showTabLogin");
        try {
            Message message = new Message(-123);
            message.writeByte(-126);
            this.session.sendMessage(message);
        } catch (Exception ex) {

        }
    }

    public void sendDataChar() {
        System.out.println("sendDataChar");
        try {
            Message message = new Message(-122);
            message.writeByte(-127);
            this.client.myChar.writeDataCharMe(message);
            this.session.sendMessage(message);
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public void showGameScreen() {
        try {
            Message message = new Message(-104);
            message.writeByte(-1);
            this.session.sendMessage(message);
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    public void sendMap(int mapID) {
        try {
            Map map = Map.listMap.get(mapID);
            if (map.addChar(this.client.myChar, -1)) {
                Message message = new Message(-103);
                message.writeShort(this.client.myChar.zoneID);
                message.writeShort(this.client.myChar.mapID);
                message.writeShort(this.client.myChar.x);
                message.writeShort(this.client.myChar.y);
                map.listZone.get(this.client.myChar.zoneID).writeMessage(this.client, message, -1);
                this.session.sendMessage(message);
            } else {
                this.sendMessage("Hiện tại bản đồ này đã đầy, bạn vui lòng thử lại sau", ColorMessage.YELLOW);
                this.client.service.setXY(this.client.myChar.listXY.get(1)[0], this.client.myChar.listXY.get(1)[1]);
            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    public void sendMap(int mapID, int typeTau) {
        try {
            Map map = Map.listMap.get(mapID);
            if (map.addChar(this.client.myChar, typeTau)) {
                Message message = new Message(-103);
                message.writeShort(this.client.myChar.zoneID);
                message.writeShort(this.client.myChar.mapID);
                message.writeShort(this.client.myChar.x);
                message.writeShort(this.client.myChar.y);
                map.listZone.get(this.client.myChar.zoneID).writeMessage(this.client, message, typeTau);
                this.session.sendMessage(message);
            } else {
                this.sendMessage("Hiện tại bản đồ này đã đầy, bạn vui lòng thử lại sau", ColorMessage.YELLOW);
                this.client.service.setXY(this.client.myChar.listXY.get(1)[0], this.client.myChar.listXY.get(1)[1]);
            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    public void sendOnlineInMap() {
        try {
            sendMap(this.client.myChar.mapID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

//        for (int i = 0; i < DataCenter_Test.gI().I.length; i++) {
//            try {
//                byte[] data = Utlis.read("DataServer\\Map\\" + i + ".bin");
//                if (data != null) {
//                    Message message = new Message(-103);
//                    message.dos.dos.write(data);
////                message.writeShort(this.client.myChar.zoneID);
////                message.writeShort(this.client.myChar.mapID);
////                message.writeShort(this.client.myChar.x);
////                message.writeShort(this.client.myChar.y);
////                message.writeShort(0);
////                message.writeByte(0);
////                message.writeShort(0);
////                message.writeShort(0);
////                message.writeByte(0);
////                message.writeBoolean(false);
////                message.writeByte(-1);
////                message.writeBoolean(false);
//                    this.session.sendMessage(message);
//                }
//
//            } catch (Exception ex) {
//
//            }
//        }
    }

    /*
      try {
            Message message = new Message(-103);
            message.writeShort(this.client.myChar.zoneID);
            message.writeShort(this.client.myChar.mapID);
            message.writeShort(this.client.myChar.x);
            message.writeShort(this.client.myChar.y);
            message.writeShort(0);
            message.writeByte(0);
            message.writeShort(0);
            message.writeShort(0);
            message.writeByte(0);
            message.writeBoolean(false);
            message.writeByte(-1);
            message.writeBoolean(false);
            this.session.sendMessage(message);
        } catch (Exception ex) {
            
        }
     */
    public void setXY(int x, int y) {
        try {
            this.client.myChar.updateXY(new int[]{x, y});
            Message message = new Message(102);
            message.writeInt(this.client.myChar.idEntity);
            message.writeShort(x);
            message.writeShort(y);
            this.session.sendMessage(message);
            Map.listMap.get(this.client.myChar.mapID).listZone.get(this.client.myChar.zoneID).sendMessage(this.client.myChar, message);
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    public void sendChatPublic(String strText) {
        try {
            Message message = new Message(21);
            message.writeUTF(this.client.myChar.name);
            message.writeUTF(strText);
            this.session.sendMessage(message);
            Map.listMap.get(this.client.myChar.mapID).listZone.get(this.client.myChar.zoneID).sendMessage(this.client.myChar, message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void sendChatWorld(String strText, boolean isVip) {
        try {
            Message message = new Message(22);
            message.writeByte(isVip ? 1 : 0);
            message.writeUTF(this.client.myChar.name);
            message.writeUTF(strText);
            for (int i = 0; i < Server.listClient.size(); i++) {
                Server.listClient.get(i).session.sendMessage(message);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    //    public void setXY() {
//        try {
//            Message message = new Message(102);
//            message.writeInt(this.client.myChar.idEntity);
//            message.writeShort(this.client.myChar.x);
//            message.writeShort(this.client.myChar.y);
//            this.session.sendMessage(message);
//        } catch (Exception ex) {
//
//        }
//    }
    public void addChar(Char myChar) {
        try {
            Message message = new Message(-102);
            message.writeInt(myChar.idEntity);
            myChar.writeDataChar(message, -1);
            this.session.sendMessage(message);
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public void sendMove(Char myChar, boolean isShop) {

        int[] byteArray = Utlis.getXYFake(myChar.x, myChar.y, DataCenter.gI().I[myChar.mapID].x);
        try {
            Message message = new Message(isShop ? -84 : 123);
            message.writeInt(myChar.idEntity);
            message.writeByte(byteArray[0]);
            message.writeByte(byteArray[1]);
            message.writeByte(byteArray[2]);
            this.session.sendMessage(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void removeChar(Char myChar) {
        try {
            Message message = new Message(-101);
            message.writeInt(myChar.idEntity);
            this.session.sendMessage(message);
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    public void sendTabZone(Map map) {
        try {
            Message message = new Message(-6);
            message.writeByte(map.listZone.size());
            message.writeByte(this.client.myChar.zoneID);
            message.writeByte(this.client.myChar.zoneID);
            ArrayList<Map.Zone> listZone = map.getZoneHaveChar();
            message.writeByte(listZone.size());
            for (int i = 0; i < listZone.size(); i++) {
                message.writeByte(listZone.get(i).zoneID);
                message.writeByte(listZone.get(i).listChar.size() >= 20 ? 2 : listZone.get(i).listChar.size() >= 10 ? 1 : 0);
            }
            this.session.sendMessage(message);
        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    public void changeZone(byte zoneID) {
        Map map = Map.listMap.get(this.client.myChar.mapID);
        if (zoneID == this.client.myChar.zoneID) {
            this.setXY(this.client.myChar.x, this.client.myChar.y);
        } else if (zoneID >= 0 && zoneID < map.listZone.size()) {
            if (map.listZone.get(zoneID).addChar(this.client.myChar)) {
                try {
                    Message message = new Message(-103);
                    message.writeShort(this.client.myChar.zoneID);
                    message.writeShort(this.client.myChar.mapID);
                    message.writeShort(this.client.myChar.x);
                    message.writeShort(this.client.myChar.y);
                    map.listZone.get(this.client.myChar.zoneID).writeMessage(this.client, message, -1);
                    this.session.sendMessage(message);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                this.client.service.setXY(this.client.myChar.listXY.get(1)[0], this.client.myChar.listXY.get(1)[1]);
                this.sendMessage("Khu vực " + zoneID + " đã đầy, bạn hãy chọn khu vực khác.", ColorMessage.YELLOW);
            }
        }
    }

    public void sendDataInfo(Char myChar) {
        try {
            Message message = new Message(63);
            myChar.writeTypeSelectOutChar(message);
            message.writeInt(myChar.lucDanh);
            message.writeInt(myChar.lucDanhQuai);
            message.writeShort(myChar.chinhXac);
            message.writeShort(myChar.neTranh);
            message.writeShort(myChar.chiMang);
            message.writeShort(myChar.giap);
            message.writeShort(0);
            message.writeShort(0);
            message.writeShort(0);
            message.writeShort(0);
            message.writeShort(0);
            message.writeShort(0);
            message.writeShort(0);
            message.writeShort(0);
            message.writeShort(0);
            message.writeShort(0);
            message.writeShort(0);
            message.writeShort(0);
            message.writeShort(0);
            message.writeShort(0);
            message.writeShort(0);
            this.session.sendMessage(message);
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public void sendDataInfo1(Char myChar) {
        try {
            myChar.updateMyChar(false);
            Message message = new Message(-123);
            message.writeByte(-73);
            message.writeInt(myChar.hpFull);
            message.writeInt(myChar.hpGoc);
            message.writeInt(myChar.mpFull);
            message.writeInt(myChar.mpGoc);
            message.writeByte(myChar.lvPk);
            message.writeShort(0);
            message.writeShort(0);
            message.writeInt(0);
            message.writeShort(0);
            message.writeShort(0);
            message.writeLong(0);
            message.writeInt(0);
            message.writeInt(0);
            message.writeInt(0);
            message.writeLong(0);
            message.writeInt(0);
            message.writeShort(0);
            message.writeShort(0);
            message.writeShort(0);
            message.writeShort(0);
            message.writeShort(0);
            message.writeByte(0);
            this.session.sendMessage(message);
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public void sendChatNpc(short idNpc, String string) {
        try {
            Message message = new Message(-48);
            message.writeShort(idNpc);
            message.writeUTF(string);
            this.session.sendMessage(message);
        } catch (IOException ex) {
        }
    }

    public void learnSkill(Message msg) throws IOException {
        short idSkill = msg.readShort();
        Skill skill = this.client.myChar.findSkillWithIdSkillTemplate(idSkill);
        if (skill != null) {
            Skill skillNext = Skill.findSkill(skill, skill.level + 1);
            if (skillNext != null) {
                if (skillNext.kiUpgrade <= this.client.myChar.ki && skillNext.levelNeed <= this.client.myChar.level()) {
                    this.client.myChar.ki -= skillNext.kiUpgrade;
                    this.client.myChar.setSkill(idSkill, skillNext);
                    this.client.myChar.updateMyChar(true);
                    this.updateSkill();
                }
            } else {
                System.out.println("skillNext not found");
            }
        } else {
            System.out.println("skill not found");
        }
    }

    private void updateSkill() {
        try {
            Message message = new Message(126);
            this.client.myChar.writeSkill(message);
            this.session.sendMessage(message);
        } catch (IOException ex) {
        }
    }

    public void showTabNpcNotTextHi(String[] arrayText, int idNpc) {
        try {
            Message message = new Message(54);
            message.writeShort(idNpc);
            message.writeUTF(Utlis.valueOf(arrayText, ";"));
            this.session.sendMessage(message);
        } catch (IOException ex) {

        }
    }

    public void sendOutMapTauBay(int typeTau) {
        try {
            Message message = new Message(-104);
            message.writeByte(typeTau);
            message.writeInt(this.client.myChar.idEntity);
            Map.listMap.get(this.client.myChar.mapID).listZone.get(this.client.myChar.zoneID).sendMessage(this.client.myChar, message);
        } catch (IOException ex) {

        }
    }

    public void actionMoveMapTauBay(int[] array, short idNpc, int typeTau) {
        int mapNew = array[Utlis.nextInt(array.length)];
        sendChatNpc(idNpc, "Được rồi lên tàu đi, nhanh lên không lỡ chuyến tàu.");
        sendMapTau(mapNew, typeTau);
    }

    public void setColorDanhHieu(String nameDanhHieu, int color1, int color2) {
        try {
            Message message = new Message(-121);
            message.writeByte(0);
            message.writeUTF(nameDanhHieu);
            message.writeInt(Integer.MIN_VALUE);
            message.writeInt(Integer.MIN_VALUE);
            message.writeInt(Integer.MIN_VALUE);
            message.writeInt(color1);
            message.writeInt(color2);
            this.session.sendMessage(message);
        } catch (IOException ex) {
        }
    }

    public void updateHp() {
        Message message = new Message(67);
        try {
            message.writeInt(this.client.myChar.hpFull);
            message.writeInt(this.client.myChar.hpGoc);
        } catch (IOException ex) {

        }
        this.session.sendMessage(message);
        message = new Message(71);
        try {
            message.writeInt(this.client.myChar.idEntity);
            message.writeInt(this.client.myChar.hpFull);
            message.writeInt(this.client.myChar.hpGoc);
        } catch (IOException ex) {

        }
        Map.listMap.get(this.client.myChar.mapID).listZone.get(this.client.myChar.zoneID).sendMessage(this.client.myChar, message);
    }

    public void updateMp() {
        Message message = new Message(65);
        try {
            message.writeInt(this.client.myChar.mpFull);
            message.writeInt(this.client.myChar.mpGoc);
        } catch (IOException ex) {

        }
        this.session.sendMessage(message);
        message = new Message(69);
        try {
            message.writeInt(this.client.myChar.idEntity);
            message.writeInt(this.client.myChar.mpFull);
            message.writeInt(this.client.myChar.mpGoc);
        } catch (IOException ex) {

        }
        Map.listMap.get(this.client.myChar.mapID).listZone.get(this.client.myChar.zoneID).sendMessage(this.client.myChar, message);
    }

    public void attackMob(int indexSkill, int idMob) {
        try {
            if (indexSkill >= 0 && indexSkill < this.client.myChar.arraySkill.length) {
                Skill skill = this.client.myChar.arraySkill[indexSkill];
                if (skill.isEndCoolDown()) {
                    if (skill.mpUsing <= this.client.myChar.mpGoc) {
                        this.client.myChar.mpGoc -= skill.mpUsing;
                        skill.f();
                        this.updateMp();
                        Map.Zone zone = Map.listMap.get(this.client.myChar.mapID).listZone.get(this.client.myChar.zoneID);
                        Mob mob = zone.findMobWithIdEntity(idMob);
                        if (mob != null && mob.hpGoc > 0 && mob.timeReSpawn == 0L && mob.getRange(this.client.myChar) <= (skill.rangeDoc + skill.rangeNgang) / 2 + DataCenter.gI().H[mob.id].speedMove) {
                            ArrayList<Mob> listMob = zone.getMobMinRange(mob, client, (skill.rangeDoc + skill.rangeNgang) / 2);
                            for (int i = 0; i < listMob.size() && i < skill.maxTarget; i++) {
                                boolean[] array = new boolean[1];
                                sendAttackMob(listMob.get(i), this.client.myChar.getDameAttackMob(skill, array), zone, array[0], skill);
                            }
                            if (Utlis.getRange(DataCenter.gI().H[mob.id].h - client.myChar.level()) > 8 && mob.id != 273 && !DataCenter.gI().I[client.myChar.mapID].isMapHoatDong()) {
                                client.service.sendMessageNoUpKi();
                            }

                        }
                    }

                }
            }

            // test();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void reSpawnMob(Mob mob) {
        Message message = new Message(57);
        try {
            message.writeShort(mob.idEntity);
            message.writeShort(mob.level);
            message.writeInt(mob.hpGoc);
            message.writeInt(mob.hpFull);
            message.writeByte(mob.typeMob);
            message.writeByte(0);
        } catch (IOException ex) {

        }
        this.session.sendMessage(message);
    }

    public void updateKiMe(boolean b) {
        try {
            Message message = null;
            if (b) {
                message = new Message(-56);
            } else {
                message = new Message(-62);
            }
            message.writeLong(this.client.myChar.ki);
            message.writeLong(this.client.myChar.exp);
            this.session.sendMessage(message);
        } catch (IOException ex) {
        }
    }

    public void updateKi() {
        try {
            Map.Zone zone = Map.listMap.get(this.client.myChar.mapID).listZone.get(this.client.myChar.zoneID);
            Message message = null;
            message = new Message(94);
            message.writeLong(this.client.myChar.exp);
            message.writeInt(this.client.myChar.idEntity);
            zone.sendMessage(this.client.myChar, message);
        } catch (IOException ex) {
        }

    }

    public void sendMessageNoUpKi() {
        if (System.currentTimeMillis() - timeNoUpKi >= 2000L) {
            timeNoUpKi = System.currentTimeMillis();
            try {
                Message message = null;
                message = new Message(-123);
                message.writeByte(-1);
                this.session.sendMessage(message);
            } catch (IOException ex) {
            }
        }
    }

    private void test() {
        try {
            Message message = new Message(-45);
            message.writeShort(441);
            message.writeShort(this.client.myChar.x);
            message.writeShort(this.client.myChar.y);
            message.writeByte(20);
            this.session.sendMessage(message);
        } catch (IOException ex) {
        }

    }

    public void viewMob(short idMob) {
        Map.Zone zone = Map.listMap.get(this.client.myChar.mapID).listZone.get(this.client.myChar.zoneID);
        Mob mob = zone.findMobWithIdEntity(idMob);
        if (mob != null) {
            try {
                Message message = new Message(13);
                message.writeShort(idMob);
                message.writeInt(mob.kiNow);
                message.writeShort(mob.hpFull / 25);
                this.session.sendMessage(message);
            } catch (IOException ex) {
            }

        }
    }

    public void sendChatMob(Mob mob, String string) {
        try {
            Message message = new Message(-12);
            message.writeShort(mob.idEntity);
            message.writeUTF(string);
            this.session.sendMessage(message);
        } catch (IOException ex) {
        }
    }

    private void sendAttackMob(Mob mob, int dame, Zone zone, boolean isChiMang, Skill skill) {
        int idMob = mob.idEntity;
        if (mob.hpGoc < dame) {
            dame = mob.hpGoc;
        }
        mob.actionAttack(this.client, dame, true);
        if (mob.hpGoc <= 0) {
            mob.hpGoc = 0;
            mob.dropItemToMap(this.client);
            zone.timeWaitReSpawnMob(mob);
        }
        try {
            Message message;
            if (idMob < 127 && mob.hpGoc < Short.MAX_VALUE) {
                message = new Message(8);
                message.writeByte(idMob);
                message.writeShort(mob.hpGoc);
            } else {
                message = new Message(52);
                message.writeShort(idMob);
                message.writeInt(mob.hpGoc);
            }
            if (isChiMang) {
                message.writeBoolean(isChiMang);
            }
            zone.sendMessage(this.client.myChar, message);
        } catch (Exception ex) {

        }
        try {
            Message message;
            if (skill.id < 127) {
                if (idMob < 127) {
                    message = new Message(-65);
                    message.writeInt(this.client.myChar.idEntity);
                    message.writeByte(skill.id);
                    message.writeByte(idMob);
                } else {
                    message = new Message(-64);
                    message.writeInt(this.client.myChar.idEntity);
                    message.writeByte(skill.id);
                    message.writeShort(idMob);
                }
            } else {
                if (idMob < 127) {
                    message = new Message(-63);
                    message.writeInt(this.client.myChar.idEntity);
                    message.writeShort(skill.id);
                    message.writeByte(idMob);
                } else {
                    message = new Message(61);
                    message.writeInt(this.client.myChar.idEntity);
                    message.writeShort(skill.id);
                    message.writeShort(idMob);
                }
            }
            zone.sendMessage(this.client.myChar, message);
        } catch (Exception ex) {

        }
    }

    public void useItem(short index) {
        if (index >= 0 && index < this.client.myChar.arrayItemBag.length) {
            Item item = this.client.myChar.arrayItemBag[index];
            if (item != null) {
                if (item.isTrangBi()) {
                    this.client.myChar.itemBagToBody(item);
                } else if (item.isTauBay()) {
                    this.showTabTauBay(item);
                } else if (ItemCombo.check(item.id)) {
                    this.showTabTauBay(item);
                }
            }
        }
    }

    private void showTabTauBay(Item item) {
        Message message = new Message(-25);
        if (item.isTauBay()) {
            if (item.id == 168) {
                try {
                    message.writeShort(item.index);
                    message.writeUTF(DataMapTauBay.getSTR(DataMapTauBay.DATA_TAU_MAC_DINH));
                } catch (Exception ex) {
                }
            } else {
                try {
                    message.writeShort(item.index);
                    message.writeUTF(DataMapTauBay.getSTR(DataMapTauBay.getArray(DataMapTauBay.DATA_TAU_MAC_DINH, DataMapTauBay.DATA_TAU_VIP)));
                } catch (Exception ex) {
                }
            }
        } else if (ItemCombo.check(item.id)) {
            ItemCombo itemcombo = ItemCombo.get(item.id);
            if (itemcombo.DATA != null && itemcombo.DATA.length > 1 && itemcombo.DATA[0].name != null) {
                try {
                    message.writeShort(item.index);
                    message.writeUTF(DataItemSelect.getSTR(ItemCombo.get(item.id).DATA));
                } catch (Exception ex) {
                }
            } else {
                message.cleanup();
                if (!itemcombo.isForChar) {
                    DataItemSelect dataTemp = itemcombo.DATA[0];
                    if (this.client.myChar.canSlotNull(dataTemp.listItem.size())) {
                        this.client.myChar.removeItemBag(item);
                        this.client.service.sendMessageRemoveItem(0, item);
                        for (int i = 0; i < dataTemp.listItem.size(); i++) {
                            Item itemAdd = dataTemp.listItem.get(i);
                            if (this.client.myChar.addItem(itemAdd)) {
                                this.sendMessageAddItem(itemAdd, true);
                            }
                        }
                    } else {
                        this.sendMessage("Bạn cần dư ít nhất " + dataTemp.listItem.size() + " ô trống mới có thể sử dụng vật phẩm này.", ColorMessage.WHITE);
                    }
                } else {
                    DataItemSelect dataTemp = itemcombo.arrayDATA[this.client.myChar.idTypeChar][0];
                    if (this.client.myChar.canSlotNull(dataTemp.listItem.size())) {
                        this.client.myChar.removeItemBag(item);
                        this.client.service.sendMessageRemoveItem(0, item);
                        for (int i = 0; i < dataTemp.listItem.size(); i++) {
                            Item itemAdd = dataTemp.listItem.get(i);
                            if (this.client.myChar.addItem(itemAdd)) {
                                this.sendMessageAddItem(itemAdd, true);
                            }
                        }
                    } else {
                        this.sendMessage("Bạn cần dư ít nhất " + dataTemp.listItem.size() + " ô trống mới có thể sử dụng vật phẩm này.", ColorMessage.WHITE);
                    }
                }
                return;
            }
        }
        this.session.sendMessage(message);
    }

    public void actionTauBay(int index, byte byte1, byte byte2) {
        if (index >= 0 && index < this.client.myChar.arrayItemBag.length) {
            Item item = this.client.myChar.arrayItemBag[index];
            if (item != null) {
                if (item.isTauBay()) {
                    DataMapTauBay[] array = null;
                    int typeTau = -1;
                    if (item.id == 168) {
                        array = DataMapTauBay.DATA_TAU_MAC_DINH;
                        typeTau = Map.TypeTau.TAU_TRAI_DAT;
                    } else {
                        array = DataMapTauBay.getArray(DataMapTauBay.DATA_TAU_MAC_DINH, DataMapTauBay.DATA_TAU_VIP);
                        if (item.id == 569) {
                            typeTau = Map.TypeTau.TAU_TRAI_DAT_VIP;
                        } else if (item.id == 716) {
                            typeTau = Map.TypeTau.TAU_FIDE;
                        } else if (item.id == 717) {
                            typeTau = Map.TypeTau.TAU_TUONG_LAI;
                        } else if (item.id == 718) {
                            typeTau = Map.TypeTau.TAU_XAYDA;
                        } else if (item.id == 719) {
                            typeTau = Map.TypeTau.TAU_NAMEC;
                        } else if (item.id == 720) {
                            typeTau = Map.TypeTau.TAU_YARDRAT;
                        }
                    }

                    if (array != null) {
                        if (byte2 <= -1) {
                            if (array[byte1].id >= 0) {
                                if (array[byte1].id != this.client.myChar.mapID) {
                                    sendMapTau(array[byte1].id, typeTau);
                                } else {
                                    this.sendMessage("Bạn đã ở đây rồi.", ColorMessage.WHITE);
                                }
                            } else {
                                int mapNext = -1;
                                do {
                                    mapNext = -1;
                                    switch (array[byte1].typePlant) {
                                        case 0:
                                            mapNext = Map.DataMapTauBay.FIDE[Utlis.nextInt(Map.DataMapTauBay.FIDE.length)];
                                            break;
                                        case 1:
                                            mapNext = Map.DataMapTauBay.TUONG_LAI[Utlis.nextInt(Map.DataMapTauBay.TUONG_LAI.length)];
                                            break;
                                        case 2:
                                            mapNext = Map.DataMapTauBay.VAMPA[Utlis.nextInt(Map.DataMapTauBay.VAMPA.length)];
                                            break;
                                        case 3:
                                            mapNext = Map.DataMapTauBay.NAMEC[Utlis.nextInt(Map.DataMapTauBay.NAMEC.length)];
                                            break;
                                        case 4:
                                            mapNext = Map.DataMapTauBay.YARDRAT[Utlis.nextInt(Map.DataMapTauBay.YARDRAT.length)];
                                            break;
                                        case 5:
                                            mapNext = Map.DataMapTauBay.MAPNEW[Utlis.nextInt(Map.DataMapTauBay.MAPNEW.length)];
                                            break;
                                    }
                                } while (mapNext == this.client.myChar.mapID);
                                sendMapTau(mapNext, typeTau);
                            }
                        } else {

                        }
                    }
                } else if (ItemCombo.check(item.id)) {
                    DataItemSelect[] data = ItemCombo.get(item.id).DATA;
                    if (byte1 >= 0 && byte1 < data.length) {
                        DataItemSelect dataTemp = data[byte1];
                        if (this.client.myChar.canSlotNull(dataTemp.listItem.size())) {
                            this.client.myChar.removeItemBag(item);
                            this.client.service.sendMessageRemoveItem(0, item);
                            for (int i = 0; i < dataTemp.listItem.size(); i++) {
                                Item itemAdd = dataTemp.listItem.get(i);
                                System.out.println(itemAdd.id);
                                if (this.client.myChar.addItem(itemAdd)) {
                                    this.sendMessageAddItem(itemAdd, true);
                                }
                            }
                        } else {
                            this.sendMessage("Bạn cần dư ít nhất " + dataTemp.listItem.size() + " ô trống mới có thể sử dụng vật phẩm này.", ColorMessage.WHITE);
                        }
                    }
                }
            }
        }
    }

    private void sendMapTau(int mapNew, int typeTau) {
        sendOutMapTauBay(typeTau);
        new Thread(()
                -> {
            try {
                session.sleepTau(5000);
                sendMap(mapNew, typeTau);

            } catch (Exception ex) {

            }
        }).start();
    }

    public void sendMessageRemoveItem(int type, Item item) {
        try {
            Message message = new Message(-16);
            message.writeByte(type);
            message.writeShort(item.index);
            this.session.sendMessage(message);
        } catch (IOException ex) {
        }
    }

    public void sendMessageAddItem(Item item, boolean isAlert) {
        try {
            Message message = null;
            if (!isAlert) {
                message = new Message(-4);
            } else {
                message = new Message(120);
                message.writeShort(1);
            }
            item.write(message);
            this.session.sendMessage(message);
        } catch (IOException ex) {
        }
    }

    public void actionMoveTele(Message msg) {
        try {
            int mapIDNext = msg.readShort();
            int idNpc = msg.readShort();
            int idMob = msg.readShort();
            int x = msg.readShort();
            int y = msg.readShort();

            Item item = this.client.myChar.getItemTauBay(false);
            if (item == null) {
                return;
            }
            if (this.client.myChar.mapID != mapIDNext) {
                int typeTau = -1;
                if (item.id == 569) {
                    typeTau = Map.TypeTau.TAU_TRAI_DAT_VIP;
                } else if (item.id == 716) {
                    typeTau = Map.TypeTau.TAU_FIDE;
                } else if (item.id == 717) {
                    typeTau = Map.TypeTau.TAU_TUONG_LAI;
                } else if (item.id == 718) {
                    typeTau = Map.TypeTau.TAU_XAYDA;
                } else if (item.id == 719) {
                    typeTau = Map.TypeTau.TAU_NAMEC;
                } else if (item.id == 720) {
                    typeTau = Map.TypeTau.TAU_YARDRAT;
                }
                sendMapTau(mapIDNext, typeTau);
            }
        } catch (IOException ex) {
        }

    }

    public void useSkill(int indexSkill) {
        if (indexSkill >= 0 && indexSkill < this.client.myChar.arraySkill.length) {
            Skill skill = this.client.myChar.arraySkill[indexSkill];
            if (skill.isEndCoolDown()) {
                if (skill.mpUsing <= this.client.myChar.mpGoc) {
                    this.client.myChar.mpGoc -= skill.mpUsing;
                    skill.f();
                    this.updateMp();
                }
            }
        }
    }

    public void updateUseItem(int index, boolean isLock) {
        try {
            Message message = new Message(116);
            message.writeShort(index);
            message.writeBoolean(isLock);
            this.session.sendMessage(message);
        } catch (IOException ex) {
        }
    }

    public void updateUseItemDuPhong(int index) {
        try {
            Message message = new Message(36);
            message.writeShort(index);
            this.session.sendMessage(message);
        } catch (IOException ex) {
        }
    }

    public void itemBodyToBag(short index) {
        if (index >= 0 && index < this.client.myChar.arrayItemBody.length) {
            Item body = this.client.myChar.arrayItemBody[index];
            if (body != null) {
                if (this.client.myChar.canSlotNull()) {
                    if (this.client.myChar.addItem(body)) {
                        this.client.myChar.arrayItemBody[index] = null;
                        try {
                            Message message = new Message(113);
                            message.writeByte(index);
                            message.writeShort(body.index);
                            this.session.sendMessage(message);
                        } catch (IOException ex) {

                        }
                        Map.listMap.get(this.client.myChar.mapID).listZone.get(this.client.myChar.zoneID).updateItemBody(this.client);
                    }
                } else {
                    this.sendMessage("Túi không đủ ô trống để chứa vật phẩm", ColorMessage.RED);
                }
            }
        }
    }

    public void itemBody2ToBag(short index) {
        if (index >= 0 && index < this.client.myChar.arrayItemBody2.length) {
            Item body = this.client.myChar.arrayItemBody2[index];
            if (body != null) {
                if (this.client.myChar.canSlotNull()) {
                    if (this.client.myChar.addItem(body)) {
                        this.client.myChar.arrayItemBody2[index] = null;
                        try {
                            Message message = new Message(37);
                            message.writeByte(index);
                            message.writeShort(body.index);
                            this.session.sendMessage(message);
                        } catch (IOException ex) {
                        }
                        Map.listMap.get(this.client.myChar.mapID).listZone.get(this.client.myChar.zoneID).updateItemBody(this.client);
                    }
                } else {
                    this.sendMessage("Túi không đủ ô trống để chứa vật phẩm", ColorMessage.RED);
                }
            }
        }
    }

    public void itemBagToBody2(short index) {
        if (index >= 0 && index < this.client.myChar.arrayItemBag.length) {
            Item item = this.client.myChar.arrayItemBag[index];
            if (item != null) {
                if (item.isTrangBi()) {
                    this.client.myChar.itemBagToBody2(item);
                }
            }
        }
    }

    public void changeItemBody(int indexChangeItemBody) {
        int indexChange = -1;
        if (indexChangeItemBody == 0) {
            indexChange = 2;
        } else if (indexChangeItemBody == 1) {
            indexChange = 4;
        } else if (indexChangeItemBody == 2) {
            indexChange = 6;
        } else if (indexChangeItemBody == 3) {
            indexChange = 8;
        } else if (indexChangeItemBody == 4) {
            indexChange = 14;
        }
        if (indexChange == -1) {
            return;
        }
        Item body1 = this.client.myChar.arrayItemBody[indexChange];
        Item body2 = this.client.myChar.arrayItemBody2[indexChange];
        this.client.myChar.arrayItemBody[indexChange] = body2;
        this.client.myChar.arrayItemBody2[indexChange] = body1;
        try {
            Message message = new Message(35);
            Char.writeItemBody(message, this.client.myChar.arrayItemBody);
            Char.writeItemBody(message, this.client.myChar.arrayItemBody2);
            this.session.sendMessage(message);
            Map.listMap.get(this.client.myChar.mapID).listZone.get(this.client.myChar.zoneID).updateItemBody(this.client);
        } catch (IOException ex) {
        }
    }

    public void updateItemBody(Client client) {
        try {
            if (this.client == client) {
                return;
            }
            Message message = new Message(-99);
            message.writeInt(client.myChar.idEntity);
            Char.writeItemBody(message, client.myChar.arrayItemBody);
            this.session.sendMessage(message);
        } catch (IOException ex) {
        }
    }

    public void removeItemMap(int idEntityItemMap) {
        try {
            Message message = new Message(58);
            message.writeShort(idEntityItemMap);
            this.session.sendMessage(message);
        } catch (Exception ex) {
        }
    }

    public void pickUpItemMap(Client c, ItemMap iMap) {
        try {
            Message message = new Message(59);
            message.writeShort(iMap.idEntity);
            message.writeInt(c.myChar.idEntity);
            iMap.item.write(message);
            this.session.sendMessage(message);
        } catch (Exception ex) {
        }
    }

    public static class ColorMessage {

        public static int YELLOW = -2560;
        public static int RED = -65536;
        public static int WHITE = -1;
    }

    public static class ItemCombo {

        public static ArrayList<ItemCombo> listComBo = new ArrayList<>();

        static {
            if (listComBo.size() == 0) {
                listComBo.add(new ItemCombo(996, DataItemSelect.DATA_CAPSULE_PHI_THUYEN));
                listComBo.add(new ItemCombo(824, new DataItemSelect[][]{DataItemSelect.DATA_CAPSULE_TRANG_BI_GOKU, DataItemSelect.DATA_CAPSULE_TRANG_BI_CADIC, DataItemSelect.DATA_CAPSULE_TRANG_BI_GOHAN, DataItemSelect.DATA_CAPSULE_TRANG_BI_POCOLO}));
            }
        }

        public DataItemSelect[] DATA;
        public DataItemSelect[][] arrayDATA;
        public boolean isForChar = false;
        public int idItem;

        public static boolean check(int idItem) {
            for (int i = 0; i < listComBo.size(); i++) {
                if (listComBo.get(i).idItem == idItem) {
                    return true;
                }
            }
            return false;
        }

        public static ItemCombo get(int idItem) {
            for (int i = 0; i < listComBo.size(); i++) {
                if (listComBo.get(i).idItem == idItem) {
                    return listComBo.get(i);
                }
            }
            return null;
        }

        private ItemCombo(int i, DataItemSelect[] data) {
            this.idItem = i;
            this.DATA = data;
        }

        private ItemCombo(int i, DataItemSelect[][] data) {
            this.idItem = i;
            this.arrayDATA = data;
            this.isForChar = true;
        }
    }

    public static class DataItemSelect {

        public static String getSTR(DataItemSelect[] array) {
            String str = "";

            for (int i = 0; i < array.length; i++) {
                str += array[i].name;
                if (i < array.length - 1) {
                    str += ";";
                }
            }

            return str;
        }

        public static DataItemSelect[] DATA_CAPSULE_PHI_THUYEN = new DataItemSelect[]{
            new DataItemSelect(569),
            new DataItemSelect(719),
            new DataItemSelect(718),
            new DataItemSelect(717),
            new DataItemSelect(716),
            new DataItemSelect(720), // new DataItemSelect("Combo", new Item(0), new Item(1), new Item(2), new Item(3), new Item(4), new Item(5), new Item(6), new Item(7), new Item(8), new Item(9)),
        };

        public static DataItemSelect[] DATA_CAPSULE_TRANG_BI_GOKU = new DataItemSelect[]{
            new DataItemSelect(null,
            new Item(
            64,
            true
            ),
            new Item(
            74,
            true
            ),
            new Item(
            84,
            true
            ),
            new Item(
            94,
            true
            )),};
        public static DataItemSelect[] DATA_CAPSULE_TRANG_BI_CADIC = new DataItemSelect[]{
            new DataItemSelect(null,
            new Item(
            69,
            true
            ),
            new Item(
            79,
            true
            ),
            new Item(
            89,
            true
            ),
            new Item(
            99,
            true
            )),};
        public static DataItemSelect[] DATA_CAPSULE_TRANG_BI_GOHAN = new DataItemSelect[]{
            new DataItemSelect(null,
            new Item(
            595,
            true
            ),
            new Item(
            610,
            true
            ),
            new Item(
            625,
            true
            ),
            new Item(
            640,
            true
            )),};
        public static DataItemSelect[] DATA_CAPSULE_TRANG_BI_POCOLO = new DataItemSelect[]{
            new DataItemSelect(null,
            new Item(
            600,
            true
            ),
            new Item(
            615,
            true
            ),
            new Item(
            630,
            true
            ),
            new Item(
            645,
            true
            )),};

        public String name;
        public ArrayList<Item> listItem = new ArrayList<Item>();

        public DataItemSelect(int idItem) {
            this.listItem.add(new Item(idItem));
            this.name = DataCenter.gI().P[idItem].name;
        }

        public DataItemSelect(int idItem, int amout) {
            this.listItem.add(new Item(idItem, false, amout));
            this.name = DataCenter.gI().P[idItem].name;
        }

        public DataItemSelect(String name, int idItem) {
            this.listItem.add(new Item(idItem, false, 1));
            this.name = name;
        }

        public DataItemSelect(String name, Item... array) {
            for (int i = 0; i < array.length; i++) {
                listItem.add(array[i]);
            }
            this.name = name;
        }
    }

    public static class DataMapTauBay {

        public static DataMapTauBay[] DATA_TAU_MAC_DINH = new DataMapTauBay[]{
            new DataMapTauBay(86),
            new DataMapTauBay(67),
            new DataMapTauBay(56),
            new DataMapTauBay(83)

        };
        public static DataMapTauBay[] DATA_TAU_VIP = new DataMapTauBay[]{
            new DataMapTauBay(-1, "Đi đến hành tinh Fide", 0),
            new DataMapTauBay(-1, "Đi đến hành tinh Tương Lai", 1),
            new DataMapTauBay(-1, "Đi đến hành tinh Vampa", 2),
            new DataMapTauBay(-1, "Đi đến hành tinh Namếc", 3),
            new DataMapTauBay(-1, "Đi đến hành tinh Yardrat", 4),
            new DataMapTauBay(-1, "Đi đến Sa mạc", 5)};
        public String name;
        public int id;
        public int typePlant = -1;

        private DataMapTauBay(int i) {
            this.id = i;
            this.name = DataCenter.gI().I[this.id].name;
        }

        private DataMapTauBay(int i, String name, int typePlant) {
            this.id = i;
            this.name = name;
            this.typePlant = typePlant;
        }

        public static String getSTR(DataMapTauBay[] array) {
            String str = "";

            for (int i = 0; i < array.length; i++) {
                str += array[i].name;
                if (i < array.length - 1) {
                    str += ";";
                }
            }

            return str;
        }

        public static DataMapTauBay[] getArray(DataMapTauBay[] array, DataMapTauBay[] array1) {
            DataMapTauBay[] arrayNew = new DataMapTauBay[array.length + array1.length];
            for (int i = 0; i < array.length; i++) {
                arrayNew[i] = array[i];
            }
            for (int i = array.length; i < arrayNew.length; i++) {
                arrayNew[i] = array1[i - array.length];
            }
            return arrayNew;
        }
    }
}
