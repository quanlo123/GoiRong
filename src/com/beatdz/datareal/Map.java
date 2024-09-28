package com.beatdz.datareal;

import com.beatdz.data.MapTemplate;
import com.beatdz.lib.Utlis;
import com.beatdz.network.Message;
import com.beatdz.real.Char;
import com.beatdz.real.Item;
import com.beatdz.real.ItemMap;
import com.beatdz.real.Mob;
import com.beatdz.real.Npc;
import com.beatdz.server.Client;
import com.beatdz.server.DataCenter;
import com.beatdz.server.Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Map {

    public static class TypeTau {

        public static int TAU_FIDE = 1;
        public static int TAU_TUONG_LAI = 2;
        public static int TAU_XAYDA = 3;
        public static int TAU_NAMEC = 4;
        public static int TAU_YARDRAT = 5;
        public static int TAU_TRAI_DAT = 6;
        public static int TAU_TRAI_DAT_VIP = 7;

    }

    public static class DataMapTauBay {

        public static int[] FIDE = new int[]{
                57,
                61,
                62,
                66
        };
        public static int[] TUONG_LAI = new int[]{
                55,
                63,
                64,
                65
        };
        public static int[] VAMPA = new int[]{
                73,
                74,
                76,
                88
        };
        public static int[] NAMEC = new int[]{
                70,
                71,
                72,
                77
        };
        public static int[] YARDRAT = new int[]{
                78,
                79,
                80,
                81
        };
        public static int[] MAPNEW = new int[]{
                102,
                103,
                104,
                105
        };

    }

    public static void load() {
        if (listMap.size() == 0) {
            for (int i = 0; i < DataCenter.gI().I.length; i++) {
                listMap.add(new Map(i, 9));
            }
        }
    }

    public Map(int mapID, int numZone) {
        this.mapID = mapID;
        Server.readDataMap(this);
        for (int i = 0; i < numZone; i++) {
            listZone.add(new Zone(this, i, 30, this.listMobTemp, this.listNpcTemp));
        }
    }

    public static ArrayList<Map> listMap = new ArrayList<Map>();
    public int mapID;
    public ArrayList<Zone> listZone = new ArrayList<Zone>();

    public boolean addChar(Char myChar, int typeTau) {
        int mapOLD = myChar.mapID;
        for (int i = 0; i < listZone.size(); i++) {
            if (listZone.get(i).listChar.size() <= listZone.get(i).maxPlayer) {
                //Map.listMap.get(myChar.mapID).removeChar(myChar);
                //myChar.mapID = this.mapID;
                if (typeTau == -1) {
                    myChar.updateXY2(getArrayXYNext(mapOLD));
                } else {
                    myChar.updateXY2(getArrayXYRND());
                }
                listZone.get(i).addChar(myChar);
                return true;
            }
        }
        return false;
    }

    public void removeChar(Char myChar) {
        if (myChar.mapID == mapID) {
            if (myChar.zoneID >= 0 && myChar.zoneID < listZone.size()) {
                listZone.get(myChar.zoneID).removeChar(myChar);
            }
        }
    }

    private void actionMap() {
        openZone();
    }

    private void openZone() {
        for (int i = 0; i < listZone.size(); i++) {
            if (listZone.get(i).listChar.size() < 10) {
                return;
            }
        }
        for (int i = listZone.size(); i < listZone.size() + 3; i++) {
            listZone.add(new Zone(this, i, 30, this.listMobTemp, this.listNpcTemp));
        }
    }

    public ArrayList<Zone> getZoneHaveChar() {
        ArrayList<Zone> list = new ArrayList<Zone>();
        for (int i = 0; i < listZone.size(); i++) {
            if (listZone.get(i).listChar.size() > 0) {
                list.add(listZone.get(i));
            }
        }
        return list;

    }

    public static String[] test = ("Thế giới bạn không bước vào được thì đừng cố chen vào, làm khó người khác, lỡ dở mình, hà tất chứ?\n"
            + "Đôi khi, không cẩn thận biết một số chuyện, mới phát hiện ra rằng những điều bản thân để tâm lại nực cười đến thế.\n"
            + "Đừng bao giờ thay đổi mình vì người khác. Nếu họ không thể tiếp nhận một con người nhiều điểm xấu là bạn, thì cũng không xứng để có được một con người với nhiều điểm tốt là bạn.\n"
            + "Đôi khi sự đấu tranh luôn cần phải có trong cuộc sống. Nếu cuộc sống trôi qua thật suôn sẻ, chúng ta sẽ không hiểu được cuộc sống, không có được bản lĩnh, nghị lực như chúng ta cần phải có, cuộc sống thật công bằng, không phải vô cớ mà mọi điều xảy đến với ta\n"
            + "Bạn cần sức mạnh, nghị lực nên cuộc sống đã đặt ra những khó khăn nghịch cảnh để bạn vượt qua và trở nên mạnh mẽ hơn\n"
            + "Cuộc sống của bạn chỉ thật sự ý nghĩa và trọn vẹn khi bạn biết giữ gìn và nuôi dưỡng ước mơ, biết ghi nhận, biết tin vào những lời hứa\n"
            + "Đừng bao giờ cau mày hay nhăn mặt thậm chí khi bạn đang buồn, chắc chắn sẽ có ai đó yêu bạn chỉ vì nụ cười của bạn thôi. Với thế giới bạn chỉ là một cá nhân nhưng đối với một ai đó, bạn là cả thế giới\n"
            + "Bạn cần sự hiểu biết và sáng tạo nên cuộc sống đã ban cho bạn đôi bàn tay và trí óc để khám phá và làm việc\n"
            + "Nếu để ý đến những điều bạn đang có trong cuộc sống, bạn sẽ nhận được nhiều hơn thế. Còn nếu chỉ để ý đến những điều bạn không có, bạn sẽ thấy mình không bao giờ có đủ\n"
            + "Bạn sẽ tìm thấy niềm vui khi giúp đỡ người khác bằng tất cả tấm lòng\n"
            + "1Hãy cảm ơn những lúc bạn gặp khó khăn, bởi nếu không có khó khăn, bạn sẽ không có cơ hội để hiểu mình và trải nghiệm cuộc sống.\n"
            + "Lòng tin cũng giống như một tờ giấy, khi đã nhàu nát sẽ không bao giờ phẳng phiu được..\n"
            + "Cuộc sống có quyền đẩy bạn ngã nhưng ngồi đó than khóc hay đứng dậy và tiếp tục là quyền của bạn.\n"
            + "Chẳng có gì trở nên dễ dàng hơn. Chỉ là bạn trở nên mạnh mẽ hơn mà thôi.\n"
            + "Ta không thể bắt đầu lại nhưng ta có thể mở đầu bây giờ và làm nên một kết thúc mới.\n"
            + "Hãy giữ khuôn mặt bạn luôn hướng về ánh mặt trời, và bóng tối sẽ ngả phía sau bạn.\n"
            + "Cuộc sống không phải là phim ảnh, không có nhiều đến thế... những lần không hẹn mà gặp.\n"
            + "Tôi tự nhủ với bản thân mình rằng, cần phải sống chân thực. Bất kể người khác nhìn mình bằng con mắt nào đi chăng nữa, dù cả thế giới phủ định, tôi vẫn có bản thân tin tưởng mình.\n"
            + "Tôi tự nhủ với bản thân mình rằng, cần phải sống vui vẻ, không cần nghĩ có phải có ai đang để tâm tới mình hay không. Bởi một người cũng có thể sống cuộc sống tươi đẹp.\n"
            + "Tôi tự nhủ với bản thân mình rằng, những lúc buồn có thể khóc đến nhem nhuốc cả mặt, nhưng sau những giờ phút đó, cần phải ngẩng đầu cười thật xinh tươi.\n"
            + "Bất kỳ một sự đả kích nào cũng không nên trở thành cái cớ cho bạn sa ngã. Bạn không thể thay đổi thế giới nhưng bạn có thể thay đổi bản thân mình. Việc cần làm là chọn lựa một con đường đúng đắn và kiên trì bước tiếp.\n"
            + "Mỗi người đều có cách sống của riêng mình, chúng ta không cần phải ngưỡng mộ cuộc sống của người khác. Có người ngoài mặt tươi cười rạng rỡ nhưng ẩn trong đó là bao giọt nước mắt, lại có người nhìn có vẻ cơ cực nhưng kỳ thực họ lại đang trải qua một cuộc sống rất thoải mái. Hạnh phúc không có một đáp án chuẩn mực, niềm vui cũng không chỉ xuất phát từ một con đường. Thu lại ánh mắt ngưỡng mộ người khác và nhìn lại tâm hồn mình. Sống cuộc sống mình mong muốn chính là những ngày tháng tươi đẹp nhất, cách sống mà mình muốn mới chính là cách sống tốt nhất.\n"
            + "Muốn đổi thói quen, phải thay hành động.\n"
            + "Sống ở đời chẳng ai muốn mình trở thành người xấu, ai cũng muốn một cuộc sống ngẩng cao đầu không hổ với người, không thẹn với lòng. Thế nhưng sự đời đôi khi chẳng như ý muốn, đôi khi người ta phải sống hai mặt để đổi lấy hai chữ bình yên.\n"
            + "Ở thực trạng xã hội hiện nay, sự thờ ơ của người tốt còn đáng sợ hơn những thứ xấu xí của xã hội.\n"
            + "Tôi nghĩ rằng mục đích của cuộc đời là sống hữu ích, sống có trách nhiệm, được tôn trọng và biết yêu thương. Trên hết, điều thật sự có ý nghĩa là: Sống cho ai đó, vì cái gì đó và tạo nên dấu ấn riêng của bạn trên thế gian này.\n"
            + "Thiên Chúa ban cho tất cả mọi người 24 giờ, còn 24 giờ ấy có giá trị bao nhiêu là do mỗi người tự định lấy.\n"
            + "Cuộc sống quan trọng nhất là sự lựa chọn.\n"
            + "Làm người phải tự tin, nhưng không được tự tin đến mức tự phụ. Làm người nên khiêm tốn, nhưng không được khiêm tốn đến mức đánh mất lòng tự tin của mình.\n"
            + "Cuộc sống, ngay cả khi có một ngàn lý do để làm cho bạn khóc, bạn vẫn phải tìm một triệu lý do để giữ nụ cười.\n"
            + "Bạn không thể điều khiển hướng gió, chỉ có thể điều khiển cánh buồm. Bạn không cần phải thấy hết các bậc thang mà chỉ cần đi bước đầu tiên với một niềm tin.\n"
            + "Bạn bè không quan trọng đứa nào giúp đứa nào nhiều hơn. Quan trọng là lúc khó còn có đứa nào không?\n"
            + "Muốn phát tiết thật dễ dàng, muốn nhẫn nại lại rất khó khăn").split("\n");

    public static class Zone {

        //        public Mob getMobMinRange(Mob mob, Char myChar,int range) {
//            boolean isLeft = myChar.x <= mob.x;
//            Mob me = null;
//            for (int i = 0; i < listMob.size(); i++) {
//                Mob mobFind = listMob.get(i);
//                if (mobFind.hpGoc > 0 && mobFind != mob) {
//                    if (Utlis.getRange(mobFind, myChar) <= range + DataCenter.gI().H[mobFind.id].speedMove) {
//                        if (me == null || mobFind.getRange(myChar) < me.getRange(myChar)) {
//                            me = mobFind;
//                        }
//                    }
//                }
//            }
//            return me;
//        }
        private long delayMobChat;

        public Zone(Map map, int zoneID, int maxPlayer, ArrayList<Mob> listMob, ArrayList<Npc> listNpc) {
            this.map = map;
            this.mapID = map.mapID;
            this.zoneID = zoneID;
            this.maxPlayer = maxPlayer;
            for (int i = 0; i < listMob.size(); i++) {
                this.listMob.add(listMob.get(i).a());
            }
            for (int i = 0; i < listNpc.size(); i++) {
                this.listNpc.add(listNpc.get(i).a());
            }
            this.threadUpdate = new Thread(()
                    -> {
                while (this.map != null) {
                    for (int i = 0; i < this.listMob.size(); i++) {
                        Mob mob = this.listMob.get(i);
                        if (mob.hpGoc <= 0) {
                            if (System.currentTimeMillis() - mob.timeReSpawn >= 0L) {
                                mob.timeReSpawn = 0;
                                mob.hpGoc = mob.hpFull;
                                this.reSpawnMob(mob);
                            }
                        }
//                        if (System.currentTimeMillis() - delayMobChat >= 0L) {
//                                this.sendChatMob(mob,test[Utlis.nextInt(test.length)]);
//                                delayMobChat = System.currentTimeMillis() + 5000L;
//                        }
                    }
                    ArrayList<ItemMap> vDeleteItemMap = new ArrayList<ItemMap>();
                    for (int i = 0; i < vItemMap.size(); i++) {
                        ItemMap iMap = ((ItemMap) vItemMap.get(i));
                        if (System.currentTimeMillis() - iMap.timeCreate >= 5 * 60000) {
                            vDeleteItemMap.add(iMap);
                        }
                    }
                    vItemMap.removeAll(vDeleteItemMap);
                    try {
                        Thread.sleep(1000L);
                    } catch (Exception ex) {

                    }
                }
            });
            this.threadUpdate.start();
        }

        public Thread threadUpdate;

        public Map map;
        public int mapID;
        public int zoneID;
        public int maxPlayer;
        public ArrayList<Char> listChar = new ArrayList<Char>();
        public ArrayList<Mob> listMob = new ArrayList<Mob>();
        public ArrayList<Npc> listNpc = new ArrayList<Npc>();
        public Vector vItemMap = new Vector();
        public int indexItemMap = 0;

        public void addItemMap(Client c, Mob mob, Item item) {
            ItemMap iMap = new ItemMap(indexItemMap, item);
            iMap.playerID = c.myChar.idEntity;
            iMap.y = c.myChar.y;
            if (Utlis.nextInt()) {
                iMap.x = (short) (c.myChar.x - Utlis.nextInt(30));
            } else {
                iMap.x = (short) (c.myChar.x + Utlis.nextInt(30));
            }
            iMap.create();
            addItemToAllChar(iMap);
            vItemMap.add(iMap);
            indexItemMap++;
        }

        public boolean addChar(Char myChar) {
            if (listChar.size() >= maxPlayer) {
                return false;
            }
            Map.listMap.get(myChar.mapID).listZone.get(myChar.zoneID).removeChar(myChar);
            myChar.mapID = mapID;
            myChar.zoneID = zoneID;
            if (!listChar.contains(myChar)) {
                listChar.add(myChar);
                addCharToAllChar(myChar);
            }
            map.actionMap();
            return true;
        }

        private void removeChar(Char myChar) {
            if (myChar.mapID == map.mapID && myChar.zoneID == zoneID) {
                if (listChar.contains(myChar)) {
                    listChar.remove(myChar);
                    removeCharToAllChar(myChar);
                }
                map.actionMap();
            }
        }

        private void addCharToAllChar(Char myChar) {
            for (int i = 0; i < listChar.size(); i++) {
                if (listChar.get(i) != myChar) {
                    listChar.get(i).client.service.addChar(myChar);
                }
            }
        }

        private void removeCharToAllChar(Char myChar) {
            for (int i = 0; i < listChar.size(); i++) {
                listChar.get(i).client.service.removeChar(myChar);
            }
        }

        public void writeMessage(Client client, Message message, int typeTau) throws IOException {
            message.writeShort(0);
            writeChar(client, message);
            writeMob(message);
            writeNpc(message);
            message.writeByte(0);
            message.writeBoolean(false);
            message.writeByte(typeTau);
            message.writeBoolean(false);
        }

        public void writeMob(Message message) throws IOException {
            message.writeShort(this.listMob.size());
            for (int i = 0; i < this.listMob.size(); i++) {
                this.listMob.get(i).writeMessage(message);
            }
        }

        public void writeNpc(Message message) throws IOException {
            message.writeShort(this.listNpc.size());
            for (int i = 0; i < this.listNpc.size(); i++) {
                this.listNpc.get(i).writeMessage(message);
            }
        }

        public void writeChar(Client client, Message message) throws IOException {
            message.writeByte(this.listChar.size());
            for (int i = 0; i < this.listChar.size(); i++) {
                Char cChar = this.listChar.get(i);
                message.writeInt(cChar.idEntity);
                cChar.writeDataChar(message, 0);
            }
        }

        public void sendMove(Char myChar, boolean isStop) {
            for (int i = 0; i < this.listChar.size(); i++) {
                if (this.listChar.get(i) != myChar) {
                    this.listChar.get(i).client.service.sendMove(myChar, isStop);
                }
            }
        }

        public void sendMessage(Char myChar, Message message) {
            for (int i = 0; i < this.listChar.size(); i++) {
                this.listChar.get(i).client.session.sendMessage(message);
            }
        }

        public void openNpc(Client client, short idNpc, int... arrayIndex) {
            if (idNpc >= 0 && idNpc < this.listNpc.size()) {
                actionNpc(client, idNpc, arrayIndex);
            }
        }

        private void actionNpc(Client client, short idNpc, int... arrayIndex) {
            Npc npc = listNpc.get(idNpc);
            if (npc.getRange(client.myChar) <= 50) {
                if (npc.id == 48) {
                    client.service.sendTabZone(this.map);
                } else {
                    if (npc.isNpcTau()) {
                        actionNpcTauBay(client, idNpc, arrayIndex);
                    } else {
                        client.service.sendChatNpc(idNpc, "Xin chào " + client.myChar.name + ", hiện tại tôi không có gì cho bạn.");
                    }
                }
            }
        }

        private void actionNpcTauBay(Client client, short idNpc, int[] arrayIndex) {
            switch (listNpc.get(idNpc).id) {
                case 42:
                    if (arrayIndex == null) {
                        client.service.showTabNpcNotTextHi(new String[]{"Đi đến hành tinh Fide"}, idNpc);
                    } else {
                        if (arrayIndex[0] != -1) {
                            if (arrayIndex[0] == 0) {
                                client.service.actionMoveMapTauBay(DataMapTauBay.FIDE, idNpc, TypeTau.TAU_FIDE);
                            }
                        }
                    }
                    break;
                case 43:
                    if (arrayIndex == null) {
                        client.service.showTabNpcNotTextHi(new String[]{"Đi đến hành tinh Tương Lai"}, idNpc);
                    } else {
                        if (arrayIndex[0] != -1) {
                            if (arrayIndex[0] == 0) {
                                client.service.actionMoveMapTauBay(DataMapTauBay.TUONG_LAI, idNpc, TypeTau.TAU_TUONG_LAI);
                            }
                        }
                    }
                    break;
                case 44:
                    if (arrayIndex == null) {
                        client.service.showTabNpcNotTextHi(new String[]{"Đi đến hành tinh Vampa"}, idNpc);
                    } else {
                        if (arrayIndex[0] != -1) {
                            if (arrayIndex[0] == 0) {
                                client.service.actionMoveMapTauBay(DataMapTauBay.VAMPA, idNpc, TypeTau.TAU_XAYDA);
                            }
                        }
                    }
                    break;
                case 45:
                    if (arrayIndex == null) {
                        client.service.showTabNpcNotTextHi(new String[]{"Đi đến hành tinh Namếc"}, idNpc);
                    } else {
                        if (arrayIndex[0] != -1) {
                            if (arrayIndex[0] == 0) {
                                client.service.actionMoveMapTauBay(DataMapTauBay.NAMEC, idNpc, TypeTau.TAU_NAMEC);
                            }
                        }
                    }
                    break;
                case 46:
                    if (arrayIndex == null) {
                        client.service.showTabNpcNotTextHi(new String[]{"Đi đến hành tinh Yardrat"}, idNpc);
                    } else {
                        if (arrayIndex[0] != -1) {
                            if (arrayIndex[0] == 0) {
                                client.service.actionMoveMapTauBay(DataMapTauBay.YARDRAT, idNpc, TypeTau.TAU_YARDRAT);
                            }
                        }
                    }
                    break;
            }
        }

        public Mob findMobWithIdEntity(int idMob) {
            for (int i = 0; i < this.listMob.size(); i++) {
                if (this.listMob.get(i).idEntity == idMob) {
                    return this.listMob.get(i);
                }
            }
            return null;
        }

        public void timeWaitReSpawnMob(Mob mob) {
            mob.timeReSpawn = System.currentTimeMillis() + 3000L;
            mob.mob2 = null;
            int dameMin = -1;
            Client client = null;
            for (int i = 0; i < mob.listCount.size() && mob.kiNow > 0; i++) {
                if (dameMin == -1 || mob.listCount.get(i).dame >= dameMin) {
                    mob.actionAttack(mob.listCount.get(i).client, 0, false);
                    client = mob.listCount.get(i).client;
                }
            }
            if (mob.kiNow > 0) {
                mob.actionAttack(client, 0, false);
            }
            mob.loadKiNow();
            synchronized (mob.listCount) {
                mob.listCount.clear();
            }
        }

        private void reSpawnMob(Mob mob) {
            for (int i = 0; i < this.listChar.size(); i++) {
                this.listChar.get(i).client.service.reSpawnMob(mob);
            }
        }

        private void sendChatMob(Mob mob, String string) {
            for (int i = 0; i < this.listChar.size(); i++) {
                this.listChar.get(i).client.service.sendChatMob(mob, string);
            }
        }

        public ArrayList<Mob> getMobMinRange(Mob mob, Client client, int range) {
            ArrayList<Mob> list = new ArrayList<Mob>();
            list.add(mob);
            for (int i = 0; i < listMob.size(); i++) {
                Mob mobFind = listMob.get(i);
                if (mobFind.hpGoc > 0 && !list.contains(mobFind)) {
                    if (Utlis.getRange(mobFind, client.myChar) <= range + DataCenter.gI().H[mob.id].speedMove) {
                        list.add(mobFind);
                    }
                }
            }

            return list;
        }

        public void updateItemBody(Client client) {
            for (int i = 0; i < this.listChar.size(); i++) {
                this.listChar.get(i).client.service.updateItemBody(client);
            }
        }

        private void addItemToAllChar(ItemMap iMap) {
            try {
                Message message = new Message(60);
                message.writeShort(0);
                message.writeInt(iMap.playerID);
                message.writeShort(iMap.idEntity);
                message.writeShort(iMap.x);
                message.writeShort(iMap.y);
                iMap.item.write(message);
                for (int i = 0; i < this.listChar.size(); i++) {
                    this.listChar.get(i).client.session.sendMessage(message);
                }
                message.cleanup();
            } catch (Exception ex) {
            }
        }

        public void charPickUpItem(Client c, int idEntityItemMap) {
            ItemMap iMap = findItemMapWithIdEntity(idEntityItemMap);
            System.out.println(idEntityItemMap + ": " + iMap + "," + idEntityItemMap);
            if (iMap != null) {
                boolean isPick = false;
                if (iMap.playerID == -1 || c.myChar.idEntity == iMap.playerID) {
                    if (c.myChar.addItem(iMap.item)) {
                        isPick = true;
                    }
                }
                if (isPick) {
                    vItemMap.remove(iMap);
                    removePickUpItemMapToAllChar(c, iMap);
                }
            } else {
                c.service.removeItemMap(idEntityItemMap);
            }

        }

        private ItemMap findItemMapWithIdEntity(int idEntityItemMap) {
            for (int i = 0; i < vItemMap.size(); i++) {
                ItemMap iMap = ((ItemMap) vItemMap.get(i));
                if (iMap.idEntity == idEntityItemMap) {
                    return iMap;
                }
            }
            return null;
        }

        private void removeItemMapToAllChar(int idEntityItemMap) {
            for (int i = 0; i < this.listChar.size(); i++) {
                this.listChar.get(i).client.service.removeItemMap(idEntityItemMap);
            }
        }

        private void removePickUpItemMapToAllChar(Client c, ItemMap idEntityItemMap) {
            for (int i = 0; i < this.listChar.size(); i++) {
                this.listChar.get(i).client.service.pickUpItemMap(c, idEntityItemMap);
            }
        }

    }

    public int[] getArrayXYNext(int mapOLD) {
        if ((mapOLD == 58 || mapOLD == mapID) && mapID == 56) {
            return new int[]{70, 310};
        }
        if (mapOLD == 56 && mapID == 58) {
            return new int[]{180, 50};
        }

        MapTemplate map = DataCenter.gI().I[mapID];

        for (int i = 0; i < map.listWayPoint.size(); i++) {
            if (map.listWayPoint.get(i).mapIDNext == mapOLD) {
                return new int[]{(map.listWayPoint.get(i).x < 200 ? 35 : map.x - 35), map.u.c(map.listWayPoint.get(i).x < 200 ? 35 : map.x - 35, map.listWayPoint.get(i).y).y - 1};
            }
        }

        return new int[]{0, 0};
    }

    public int[] getArrayXYRND() {
        MapTemplate map = DataCenter.gI().I[mapID];
        int x = Utlis.nextInt(50, map.J - 200);
        int y = map.u.c(x, Utlis.nextInt(50, map.K - 200)).y;
        return new int[]{x, y};
    }

    public ArrayList<Mob> listMobTemp = new ArrayList<Mob>();

    public void SaveMob(Mob mob) {
        mob.idEntity = listMobTemp.size();
        listMobTemp.add(mob);
    }

    public ArrayList<Npc> listNpcTemp = new ArrayList<Npc>();

    public void SaveNpc(Npc npc) {
        npc.idEntity = listNpcTemp.size();
        listNpcTemp.add(npc);
    }

    public void SaveToZone() {

    }
}
