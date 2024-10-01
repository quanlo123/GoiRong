package com.beatdz.server;

import com.beatdz.datareal.Map;
import com.beatdz.lib.Utlis;
import com.beatdz.network.mSocket;
import com.beatdz.real.Mob;
import com.beatdz.real.Npc;

import java.net.ServerSocket;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.*;

public class Server {

    public static String keyApp = "45635eb26fccee6esc6a6d5f6e784";
    public static int portServer = 2907;
    public static int ver1 = 1;
    public static int ver2 = 1;

    public static ServerSocket server;
    public static ServerSocket server2;
    public static String stringUTF = " 0123456789+-*='\"\\/_?.,ˋˊ~ˀ:;|<>[]{}!@#$%^&()aáàảãạâấầẩẫậăắằẳẵặbcdđeéèẻẽẹêếềểễệfghiíìỉĩịjklmnoóòỏõọôốồổỗộơớờởỡợpqrstuúùủũụưứừửữựvxyýỳỷỹỵzwAÁÀẢÃẠÂẤẦẨẪẬĂẮẰẲẴẶBCDĐEÉÈẺẼẸÊẾỀỂỄỆFGHIÍÌỈĨỊJKLMNOÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢPQRSTUÚÙỦŨỤƯỨỪỬỮỰVXYÝỲỶỸỴZW";

    //    public static String stringUTF = " 0123456789+-*='\"\\/_?.,ˋˊ~ˀ:;|<>[]{}!@#$%^&()aáàảãạâấầẩẫậăắằẳẵặbcdđeéèẻẽẹêếềểễệfghiíìỉĩịjklmnoóòỏõọôốồổỗộơớờởỡợpqrstuúùủũụưứừửữựvxyýỳỷỹỵzwAÁÀẢÃẠÂẤẦẨẪẬĂẮẰẲẴẶBCDĐEÉÈẺẼẸÊẾỀỂỄỆFGHIÍÌỈĨỊJKLMNOÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢPQRSTUÚÙỦŨỤƯỨỪỬỮỰVXYÝỲỶỸỴZW";
    public static byte[] arrData2;
    public static byte[] arrData;
    public static ArrayList<Client> listClient = new ArrayList();

    public static void main(String[] args) {
        arrData = Utlis.read("DataServer\\arr_data_game.bin");
        arrData2 = Utlis.read("DataServer\\Data2.bin");
        DataCenter.gI();
        Map.load();
        new ThreadSocket().start();
        new ThreadSocket2().start();
        tatLog();
    }

    static boolean checkAuthLogin(String username, String password) {
        return true;
    }

    public static void readDataMap(Map map) {
        try {
            byte[] dataJsonMob = Utlis.read("DataServer\\DataMap\\Mob\\" + map.mapID + ".bin");
            byte[] dataJsonNpc = Utlis.read("DataServer\\DataMap\\Npc\\" + map.mapID + ".bin");
            if (dataJsonMob != null) {
                JSONArray jsonArray = new JSONArray(new String(dataJsonMob, "UTF-8"));
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json = jsonArray.getJSONObject(i);
                    Mob mob = new Mob();
                    mob.readJson(json);
                    map.SaveMob(mob);
                }
            }
            if (dataJsonNpc != null) {
                JSONArray jsonArray = new JSONArray(new String(dataJsonNpc, "UTF-8"));
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json = jsonArray.getJSONObject(i);
                    Npc npc = new Npc();
                    npc.readJson(json);
                    map.SaveNpc(npc);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean checkOnline(String name) {
        return true;
    }

    public static Client getClientWithNameChar(String name) {
        for (int i = 0; i < listClient.size(); i++) {
            if (listClient.get(i).myChar.name.equals(name) && listClient.get(i).isOnline) {
                return listClient.get(i);
            }
        }
        return null;
    }

    public static class ThreadSocket extends Thread {

        public void run() {
            System.out.println("ThreadSocket running");
            try {
                server = new ServerSocket(portServer);
                while (true) {
                    try {
                        mSocket socket = mSocket.create(server.accept());
                        if (socket != null) {
                            socket.socket.setKeepAlive(true);
                            Client.createClient(socket).start();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    public static class ThreadSocket2 extends Thread {

        public void run() {
            System.out.println("ThreadSocket2 running");
            try {
                server2 = new ServerSocket(portServer + 1);
                while (true) {
                    try {
                        mSocket.create(server2.accept()).close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void tatLog() {
        Logger hikariLogger = (Logger) LoggerFactory.getLogger("com.zaxxer.hikari");
        hikariLogger.setLevel(Level.ERROR);

        Logger cc = (Logger) LoggerFactory.getLogger("org.reflections");
        cc.setLevel(Level.ERROR);
    }
}
