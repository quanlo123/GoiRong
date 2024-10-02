package com.beatdz.real;

import com.beatdz.data.ListSkill;
import com.beatdz.data.MapTemplate;
import com.beatdz.data.Skill;
import com.beatdz.datareal.Map;
import com.beatdz.lib.Utlis;
import com.beatdz.network.Message;
import com.beatdz.server.Client;
import com.beatdz.server.DataCenter;
import java.io.IOException;
import java.util.ArrayList;

public class Char extends Entity {

    public int id;
    public String username = "";
    public byte idTypeChar;
    public byte lvPk;
    public byte typePk;
    public byte typeGiaoDich;
    public short speedMove = 700;
    public int hpGoc = 400;
    public int mpGoc = 400;
    public int hpFull = 400;
    public int mpFull = 400;
    public int bac;
    public int bacKhoa;
    public int kimCuong;
    public int vang;
    public int phamChat;
    public byte gioiTinh;
    public byte indexTypeChar;
    public long exp = DataCenter.gI().getLevel(20);
    public String name = "";
    public Client client;

    public void setClient(Client client) {
        this.client = client;
    }

    public int mapID = 75;
    public int zoneID = 0;
    public int status = 0;
    public long ki = exp;//10000000000L;
    public Skill skillFight = ListSkill.skillAttack.a();

    public Skill[] arraySkill = null;
    /*
    ,
        ListSkill.findSkill(31, 1),
        ListSkill.findSkill(32, 1),
        ListSkill.findSkill(33, 1),
        ListSkill.findSkill(34, 1),
        ListSkill.findSkill(35, 1),
        ListSkill.findSkill(36, 1),
    tancong 78,2
luc danh quai 89
chinh xac 65
chi mang 63
bo qua ne tranh 147(%)
ti le xuyen giap  197(%)
xac thuong ngau nghien 297
sat thuong suy giam 68


     */
    public Item[] arrayItemBody = new Item[16];
    public Item[] arrayItemBody2 = new Item[16];
    public Item[] arrayItemBag = new Item[999];
    public int lucDanh = 24;
    public int lucDanhQuai = 12;

    public int chinhXac = 0;
    public int neTranh = 0;
    public int chiMang = 0;
    public int giap = 0;

    public Char() {
        this.idEntity = -1;
    }

    public Char(int id) {
        this.idEntity = id;
        this.addItem(new Item(168, true));
        this.addItem(new Item(718, true));
        this.addItem(new Item(996, true));
        this.addItem(new Item(824, true));
    }

    public void writeDataChar(Message message, int tau) throws IOException {
        message.writeByte(status);
        message.writeUTF(name);
        message.writeByte(idTypeChar);
        message.writeByte(gioiTinh);
        message.writeByte(indexTypeChar);// gender
        message.writeByte(typePk);
        message.writeByte(lvPk);
        message.writeShort(speedMove);
        message.writeInt(hpGoc);
        message.writeInt(hpFull);
        message.writeInt(mpGoc);
        message.writeInt(mpFull);
        message.writeLong(exp);
        message.writeShort(x);
        message.writeShort(y);
        message.writeByte(0);
        message.writeUTF("");
        message.writeByte(0);
        message.writeByte(0);
        writeDanhHieu(message);
        message.writeByte(0);//rank
        writeTypeSelectOutChar(message);
        writeTypeMoveMap(message, tau);
        message.writeBoolean(false);
    }

    public void writeDataCharMe(Message message) throws IOException {
        message.writeUTF(username);
        message.writeInt(this.idEntity);
        message.writeUTF(name);
        message.writeByte(gioiTinh);
        message.writeByte(idTypeChar);
        message.writeByte(0);
        message.writeByte(indexTypeChar);
        message.writeByte(typePk);
        message.writeByte(lvPk);
        message.writeInt(phamChat);
        message.writeShort(speedMove);
        message.writeInt(hpGoc);
        message.writeInt(hpFull);
        message.writeInt(mpGoc);
        message.writeInt(mpFull);
        message.writeLong(exp);
        message.writeInt(bac);
        message.writeInt(bacKhoa);
        message.writeInt(vang);
        message.writeInt(kimCuong);
        writeTask(message);
        writeTienIch2(message);
        message.writeShort(999);
        message.writeByte(0);
        writeItemBody(message, arrayItemBody);
        writeItemBody(message, arrayItemBody2);
        writeItemBag(message, arrayItemBag);
        writeEffect(message);

        writeThu(message);
        writeFriend(message);
        writeKeThu(message);

        writeSkill(message);

        writeClean(message);

        writeDanhHieu(message);

        writeTienIch(message);

        writeTypeSelectOutChar(message);

        writeDauThan(message);

        message.writeInt(0);
        message.writeInt(0);
        message.writeLong(0);
        message.writeByte(0);

    }

    public void writeSkill(Message message) throws IOException {
        message.writeLong(ki);
        message.writeShort(0);

        message.writeShort(skillFight.id);
        message.writeShort(arraySkill.length);

        for (int i = 0; i < arraySkill.length; i++) {
            //  System.out.println("writeSkill() calls by [" + arraySkill[i].id + "," + arraySkill[i].timeCoolDown + "]");
            message.writeShort(arraySkill[i].id);
            message.writeLong(arraySkill[i].timeCoolDown);
        }
//        message.writeShort(506);
//        message.writeShort(6);
//
//        message.writeShort(0);
//        message.writeLong(0);
//
//        message.writeShort(101);
//        message.writeLong(0);
//
//        message.writeShort(202);
//        message.writeLong(0);
//
//        message.writeShort(303);
//        message.writeLong(0);
//
//        message.writeShort(404);
//        message.writeLong(0);
//
//        message.writeShort(506);
//        message.writeLong(0);

    }

    private void writeClean(Message message) throws IOException {
        message.writeUTF("GameTester");
        message.writeUTF("GameTester");
        message.writeByte(0);
        message.writeShort(0);

    }

    private void writeDanhHieu(Message message) throws IOException {
        message.writeByte(1);//số lượng
        message.writeUTF("Quản Trị Viên");
        message.writeInt(-1);
        message.writeByte(0);//chọn
    }

    private void writeTienIch(Message message) throws IOException {
        message.writeInt(0);//time use tiện ích
        message.writeByte(0);//rank
    }

    private void writeTienIch2(Message message) throws IOException {
        message.writeInt(0);
        message.writeInt(0);
    }

    private void writeDauThan(Message message) throws IOException {
        message.writeByte(0);
        message.writeInt(0);
    }

    public void writeTypeSelectOutChar(Message message) throws IOException {
        message.writeByte(0);
    }

    private void writeEffect(Message message) throws IOException {
        message.writeByte(0);
//        message.writeShort(92);
//        message.writeInt(0);
//        message.writeLong(System.currentTimeMillis());
//        message.writeInt(60 * 60000);
    }

    private void writeThu(Message message) throws IOException {
        message.writeShort(0);
    }

    private void writeFriend(Message message) throws IOException {
        message.writeShort(0);

    }

    private void writeKeThu(Message message) throws IOException {
        message.writeShort(0);
    }

    private void writeItemBag(Message message, Item[] array) throws IOException {
        ArrayList<Item> list = Utlis.cleanArrayItem(array);
        message.writeShort(list.size());
        for (int i = 0; i < list.size(); i++) {
            list.get(i).write(message);
        }
    }

    public static void writeItemBody(Message message, Item[] array) throws IOException {
        ArrayList<Item> list = Utlis.cleanArrayItem(array);
        message.writeByte(list.size());
        for (int i = 0; i < list.size(); i++) {
            Item item = list.get(i);
            message.writeShort(item.id);
            message.writeBoolean(item.isLock);
            message.writeLong(item.expiry);
            message.writeByte(item.he);
            message.writeByte(item.level);
            message.writeUTF(item.strData);
            message.writeUTF(item.nameCreate);
        }
    }

    private void writeTask(Message message) throws IOException {
        message.writeShort(DataCenter.gI().L.length);
        message.writeByte(-1);
        message.writeShort(0);
    }

    private void writeTypeMoveMap(Message message, int tau) throws IOException {
        message.writeByte(tau);
    }

    public Skill findSkillWithIdSkillTemplate(short idSkill) {
        for (int i = 0; i < arraySkill.length; i++) {
            if (arraySkill[i].isSkillTemplate == idSkill) {
                return arraySkill[i];
            }
        }
        return null;
    }

    public int level() {
        long var1 = this.exp;

        int var3;
        for (var3 = 0; var3 < DataCenter.gI().ay.length && var1 >= DataCenter.gI().ay[var3]; ++var3) {
            var1 -= DataCenter.gI().ay[var3];
        }

        return var3;
    }

    public void setSkill(short idSkill, Skill skillNext) {
        for (int i = 0; i < arraySkill.length; i++) {
            if (arraySkill[i].isSkillTemplate == idSkill) {
                if (arraySkill[i].id == skillFight.id) {
                    skillFight = skillNext;
                }
                arraySkill[i] = skillNext;
                return;
            }
        }
    }

    public void updateMyChar(boolean isUpdate) {
        int hpFull = this.getHpFull();
        if (this.hpFull != hpFull) {
            this.hpFull = hpFull;
            if (isUpdate) {
                this.client.service.updateHp();
            }
        }
        int mpFull = this.getMpFull();
        if (this.mpFull != mpFull) {
            this.mpFull = mpFull;
            if (isUpdate) {
                this.client.service.updateMp();
            }
        }

        this.lucDanh = this.getGetLucDanh(false, false);
        this.lucDanhQuai = this.getGetLucDanhLenQuai();
        this.chiMang = getChiMang();
        this.neTranh = getNeTranh();
        this.chinhXac = getChinhXac();

    }

    private int getHpFull() {
        int hpFull = 400;
        hpFull += Utlis.getIntItemOption(arraySkill[5].getItemOption(), 319) * 30;
        return hpFull;
    }

    private int getMpFull() {
        int mpFull = 400;
        mpFull += Utlis.getIntItemOption(arraySkill[4].getItemOption(), 318) * 30;
        return mpFull;
    }

    public int getGetLucDanh(boolean isAttack, boolean isMob) {
        int lucDanh = 24;

        if (isAttack) {
            lucDanh += Utlis.getIntItemOption(arraySkill[0].getItemOption(), 314) * 2;
        }
        if (isMob) {
            lucDanh += lucDanhQuai;
        }
        return lucDanh;
    }

    public int getDameAttack(Skill skill, boolean isAttack, boolean isMob) {
        int lucDanh = 24;
        if (isAttack) {
            lucDanh += Utlis.getIntItemOption(skill.getItemOption(), 314);
            lucDanh += Utlis.getIntItemOption(skill.getItemOption(), 78);
        }
        if (isMob) {
            lucDanh += lucDanhQuai;
        }
        return lucDanh;
    }

    private int getGetLucDanhLenQuai() {
        int lucDanhQuai = 12;

        return lucDanhQuai;
    }

    public int getChinhXac() {
        int chinhXac = 0;
        chinhXac += Utlis.getIntItemOption(arraySkill[1].getItemOption(), 316) * 5;
        return chinhXac;
    }

    public int getNeTranh() {
        int neTranh = 0;
        neTranh += Utlis.getIntItemOption(arraySkill[2].getItemOption(), 315) * 5;
        return neTranh;
    }

    public int getChiMang() {
        int chiMang = 0;
        chiMang += Utlis.getIntItemOption(arraySkill[3].getItemOption(), 317) * 5;
        return chiMang;
    }

    public int getGiap() {
        int giap = 0;
        return giap;
    }

    public int getHpHeal() {
        return 50;
    }

    public int getMpHeal() {
        return 50;
    }

    void checkXY() {

    }

    public int getDameAttackMob(Skill skill, boolean[] array) {
        try {
            int dameMax = this.client.myChar.getDameAttack(skill, true, true);
            int dame = Utlis.nextInt(dameMax * 90 / 100, dameMax);
            array[0] = Utlis.c.nextFloat() <= ((float) this.client.myChar.chiMang / 10000);
            if (array[0]) {
                dame = dame + ((dame * 80) / 100);
            }
            return dame;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    public boolean addItem(Item item) {
        if (item == null) {
            return false;
        }
        Item check = getItemWithId(item.id);
        if (check != null && check.getItemTemplate().isXepChong) {
            check.c(item.L());
            return true;
        }
        for (int i = 0; i < this.arrayItemBag.length; i++) {
            if (this.arrayItemBag[i] == null) {
                this.arrayItemBag[i] = item;
                item.index = i;
                return true;
            }
        }
        return false;
    }

    public void removeItemBag(Item item) {
        for (int i = 0; i < this.arrayItemBag.length; i++) {
            if (this.arrayItemBag[i] != null && item.id == this.arrayItemBag[i].id && i == item.index) {
                this.arrayItemBag[i] = null;
            }
        }
    }

    public Item getItemWithId(int id) {
        for (int i = 0; i < this.arrayItemBag.length; i++) {
            if (arrayItemBag[i] != null && arrayItemBag[i].id == id) {
                return arrayItemBag[i];
            }
        }
        return null;
    }

    public Item getItemTauBay(boolean isSida) {
        for (int i = 0; i < this.arrayItemBag.length; i++) {
            if (arrayItemBag[i] != null) {
                if (arrayItemBag[i].id == 168) {
                    if (isSida) {
                        return arrayItemBag[i];
                    }
                } else if (Item.d(arrayItemBag[i].id)) {
                    return arrayItemBag[i];
                }
            }
        }
        return null;
    }

    public boolean canSlotNull() {
        for (int i = 0; i < this.arrayItemBag.length; i++) {
            if (this.arrayItemBag[i] == null) {
                return true;
            }
        }
        return false;
    }

    public boolean canSlotNull(int size) {
        int n = 0;
        for (int i = 0; i < this.arrayItemBag.length; i++) {
            if (this.arrayItemBag[i] == null) {
                n++;
            }
        }
        return n >= size;
    }

    public void loadSkill() {
        arraySkill = new Skill[]{
            ListSkill.skillAttack.a(),
            ListSkill.skillChinhXac.a(),
            ListSkill.skillNeTranh.a(),
            ListSkill.skillChiMang.a(),
            ListSkill.skillKp.a(),
            ListSkill.skillHp.a(),
            ListSkill.getSkillChuong(idTypeChar),
            ListSkill.getSkillBienHinh(idTypeChar),};
    }

    public void itemBagToBody(Item item) {
        if (item.getItemTemplate().levelNeed > this.level() || item.getItemTemplate().typeChar != this.indexTypeChar) {
            return;
        }
        this.removeItemBag(item);
        int indexChange = item.index;
        Item itemBody = this.arrayItemBody[item.getItemTemplate().type];
        if (itemBody != null) {
            this.arrayItemBag[item.index] = itemBody;
            itemBody.index = item.index;
        }
        item.isLock = true;
        item.index = item.getItemTemplate().type;
        this.arrayItemBody[item.index] = item;
        this.client.service.updateUseItem(indexChange, true);
        Map.listMap.get(mapID).listZone.get(zoneID).updateItemBody(this.client);
    }

    public void itemBagToBody2(Item item) {
        if (item.getItemTemplate().levelNeed > this.level() || item.getItemTemplate().typeChar != this.indexTypeChar) {
            return;
        }
        this.removeItemBag(item);
        int indexChange = item.index;
        Item itemBody = this.arrayItemBody2[item.getItemTemplate().type];
        if (itemBody != null) {
            this.arrayItemBag[item.index] = itemBody;
            itemBody.index = item.index;
        }
        item.isLock = true;
        item.index = item.getItemTemplate().type;
        this.arrayItemBody2[item.index] = item;
        this.client.service.updateUseItemDuPhong(indexChange);
    }
}
