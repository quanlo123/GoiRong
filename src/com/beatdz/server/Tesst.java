/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beatdz.server;

import com.beatdz.lib.Utlis;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author admin
 */
public class Tesst {

    public static void main(String[] a) {
        try {
            byte[] array = Utlis.read("DataServer\\Map\\86.bin");

            String data = "[\n"
                    + " {\n"
                    + "  \"hpFull\": 500,\n"
                    + "  \"hpGoc\": 500,\n"
                    + "  \"id\": 155,\n"
                    + "  \"isPaint\": false,\n"
                    + "  \"level\": 5,\n"
                    + "  \"typeMob\": 0,\n"
                    + "  \"x\": 896,\n"
                    + "  \"y\": 388\n"
                    + " },\n"
                    + " {\n"
                    + "  \"hpFull\": 500,\n"
                    + "  \"hpGoc\": 500,\n"
                    + "  \"id\": 155,\n"
                    + "  \"isPaint\": false,\n"
                    + "  \"level\": 5,\n"
                    + "  \"typeMob\": 0,\n"
                    + "  \"x\": 796,\n"
                    + "  \"y\": 388\n"
                    + " },\n"
                    + " {\n"
                    + "  \"hpFull\": 500,\n"
                    + "  \"hpGoc\": 500,\n"
                    + "  \"id\": 155,\n"
                    + "  \"isPaint\": false,\n"
                    + "  \"level\": 5,\n"
                    + "  \"typeMob\": 0,\n"
                    + "  \"x\": 696,\n"
                    + "  \"y\": 388\n"
                    + " },\n"
                    + " {\n"
                    + "  \"hpFull\": 500,\n"
                    + "  \"hpGoc\": 500,\n"
                    + "  \"id\": 155,\n"
                    + "  \"isPaint\": false,\n"
                    + "  \"level\": 5,\n"
                    + "  \"typeMob\": 0,\n"
                    + "  \"x\": 596,\n"
                    + "  \"y\": 388\n"
                    + " },\n"
                    + " {\n"
                    + "  \"hpFull\": 500,\n"
                    + "  \"hpGoc\": 500,\n"
                    + "  \"id\": 155,\n"
                    + "  \"isPaint\": false,\n"
                    + "  \"level\": 5,\n"
                    + "  \"typeMob\": 0,\n"
                    + "  \"x\": 496,\n"
                    + "  \"y\": 388\n"
                    + " },\n"
                    + " {\n"
                    + "  \"hpFull\": 500,\n"
                    + "  \"hpGoc\": 500,\n"
                    + "  \"id\": 155,\n"
                    + "  \"isPaint\": false,\n"
                    + "  \"level\": 5,\n"
                    + "  \"typeMob\": 0,\n"
                    + "  \"x\": 396,\n"
                    + "  \"y\": 388\n"
                    + " },\n"
                    + " {\n"
                    + "  \"hpFull\": 700,\n"
                    + "  \"hpGoc\": 700,\n"
                    + "  \"id\": 156,\n"
                    + "  \"isPaint\": false,\n"
                    + "  \"level\": 7,\n"
                    + "  \"typeMob\": 0,\n"
                    + "  \"x\": 1435,\n"
                    + "  \"y\": 337\n"
                    + " },\n"
                    + " {\n"
                    + "  \"hpFull\": 700,\n"
                    + "  \"hpGoc\": 700,\n"
                    + "  \"id\": 156,\n"
                    + "  \"isPaint\": false,\n"
                    + "  \"level\": 7,\n"
                    + "  \"typeMob\": 0,\n"
                    + "  \"x\": 1599,\n"
                    + "  \"y\": 331\n"
                    + " },\n"
                    + " {\n"
                    + "  \"hpFull\": 700,\n"
                    + "  \"hpGoc\": 700,\n"
                    + "  \"id\": 156,\n"
                    + "  \"isPaint\": false,\n"
                    + "  \"level\": 7,\n"
                    + "  \"typeMob\": 0,\n"
                    + "  \"x\": 1340,\n"
                    + "  \"y\": 310\n"
                    + " },\n"
                    + " {\n"
                    + "  \"hpFull\": 700,\n"
                    + "  \"hpGoc\": 700,\n"
                    + "  \"id\": 156,\n"
                    + "  \"isPaint\": false,\n"
                    + "  \"level\": 7,\n"
                    + "  \"typeMob\": 0,\n"
                    + "  \"x\": 1702,\n"
                    + "  \"y\": 287\n"
                    + " },\n"
                    + " {\n"
                    + "  \"hpFull\": 500,\n"
                    + "  \"hpGoc\": 500,\n"
                    + "  \"id\": 155,\n"
                    + "  \"isPaint\": false,\n"
                    + "  \"level\": 5,\n"
                    + "  \"typeMob\": 0,\n"
                    + "  \"x\": 956,\n"
                    + "  \"y\": 276\n"
                    + " },\n"
                    + " {\n"
                    + "  \"hpFull\": 500,\n"
                    + "  \"hpGoc\": 500,\n"
                    + "  \"id\": 155,\n"
                    + "  \"isPaint\": false,\n"
                    + "  \"level\": 5,\n"
                    + "  \"typeMob\": 0,\n"
                    + "  \"x\": 856,\n"
                    + "  \"y\": 276\n"
                    + " },\n"
                    + " {\n"
                    + "  \"hpFull\": 500,\n"
                    + "  \"hpGoc\": 500,\n"
                    + "  \"id\": 155,\n"
                    + "  \"isPaint\": false,\n"
                    + "  \"level\": 5,\n"
                    + "  \"typeMob\": 0,\n"
                    + "  \"x\": 756,\n"
                    + "  \"y\": 276\n"
                    + " },\n"
                    + " {\n"
                    + "  \"hpFull\": 500,\n"
                    + "  \"hpGoc\": 500,\n"
                    + "  \"id\": 155,\n"
                    + "  \"isPaint\": false,\n"
                    + "  \"level\": 5,\n"
                    + "  \"typeMob\": 0,\n"
                    + "  \"x\": 656,\n"
                    + "  \"y\": 276\n"
                    + " },\n"
                    + " {\n"
                    + "  \"hpFull\": 500,\n"
                    + "  \"hpGoc\": 500,\n"
                    + "  \"id\": 155,\n"
                    + "  \"isPaint\": false,\n"
                    + "  \"level\": 5,\n"
                    + "  \"typeMob\": 0,\n"
                    + "  \"x\": 365,\n"
                    + "  \"y\": 273\n"
                    + " },\n"
                    + " {\n"
                    + "  \"hpFull\": 500,\n"
                    + "  \"hpGoc\": 500,\n"
                    + "  \"id\": 155,\n"
                    + "  \"isPaint\": false,\n"
                    + "  \"level\": 5,\n"
                    + "  \"typeMob\": 0,\n"
                    + "  \"x\": 305,\n"
                    + "  \"y\": 273\n"
                    + " },\n"
                    + " {\n"
                    + "  \"hpFull\": 500,\n"
                    + "  \"hpGoc\": 500,\n"
                    + "  \"id\": 155,\n"
                    + "  \"isPaint\": false,\n"
                    + "  \"level\": 5,\n"
                    + "  \"typeMob\": 0,\n"
                    + "  \"x\": 245,\n"
                    + "  \"y\": 273\n"
                    + " },\n"
                    + " {\n"
                    + "  \"hpFull\": 700,\n"
                    + "  \"hpGoc\": 700,\n"
                    + "  \"id\": 156,\n"
                    + "  \"isPaint\": false,\n"
                    + "  \"level\": 7,\n"
                    + "  \"typeMob\": 0,\n"
                    + "  \"x\": 1554,\n"
                    + "  \"y\": 268\n"
                    + " },\n"
                    + " {\n"
                    + "  \"hpFull\": 700,\n"
                    + "  \"hpGoc\": 700,\n"
                    + "  \"id\": 156,\n"
                    + "  \"isPaint\": true,\n"
                    + "  \"level\": 7,\n"
                    + "  \"typeMob\": 0,\n"
                    + "  \"x\": 1449,\n"
                    + "  \"y\": 241\n"
                    + " },\n"
                    + " {\n"
                    + "  \"hpFull\": 700,\n"
                    + "  \"hpGoc\": 700,\n"
                    + "  \"id\": 156,\n"
                    + "  \"isPaint\": false,\n"
                    + "  \"level\": 7,\n"
                    + "  \"typeMob\": 0,\n"
                    + "  \"x\": 1371,\n"
                    + "  \"y\": 234\n"
                    + " },\n"
                    + " {\n"
                    + "  \"hpFull\": 700,\n"
                    + "  \"hpGoc\": 700,\n"
                    + "  \"id\": 156,\n"
                    + "  \"isPaint\": false,\n"
                    + "  \"level\": 7,\n"
                    + "  \"typeMob\": 0,\n"
                    + "  \"x\": 1641,\n"
                    + "  \"y\": 231\n"
                    + " },\n"
                    + " {\n"
                    + "  \"hpFull\": 700,\n"
                    + "  \"hpGoc\": 700,\n"
                    + "  \"id\": 156,\n"
                    + "  \"isPaint\": false,\n"
                    + "  \"level\": 7,\n"
                    + "  \"typeMob\": 0,\n"
                    + "  \"x\": 1224,\n"
                    + "  \"y\": 207\n"
                    + " },\n"
                    + " {\n"
                    + "  \"hpFull\": 700,\n"
                    + "  \"hpGoc\": 700,\n"
                    + "  \"id\": 156,\n"
                    + "  \"isPaint\": false,\n"
                    + "  \"level\": 7,\n"
                    + "  \"typeMob\": 0,\n"
                    + "  \"x\": 1758,\n"
                    + "  \"y\": 177\n"
                    + " },\n"
                    + " {\n"
                    + "  \"hpFull\": 700,\n"
                    + "  \"hpGoc\": 700,\n"
                    + "  \"id\": 156,\n"
                    + "  \"isPaint\": false,\n"
                    + "  \"level\": 7,\n"
                    + "  \"typeMob\": 0,\n"
                    + "  \"x\": 1356,\n"
                    + "  \"y\": 170\n"
                    + " },\n"
                    + " {\n"
                    + "  \"hpFull\": 700,\n"
                    + "  \"hpGoc\": 700,\n"
                    + "  \"id\": 156,\n"
                    + "  \"isPaint\": false,\n"
                    + "  \"level\": 7,\n"
                    + "  \"typeMob\": 0,\n"
                    + "  \"x\": 1545,\n"
                    + "  \"y\": 169\n"
                    + " },\n"
                    + " {\n"
                    + "  \"hpFull\": 700,\n"
                    + "  \"hpGoc\": 700,\n"
                    + "  \"id\": 156,\n"
                    + "  \"isPaint\": false,\n"
                    + "  \"level\": 7,\n"
                    + "  \"typeMob\": 0,\n"
                    + "  \"x\": 1653,\n"
                    + "  \"y\": 162\n"
                    + " },\n"
                    + " {\n"
                    + "  \"hpFull\": 100,\n"
                    + "  \"hpGoc\": 100,\n"
                    + "  \"id\": 239,\n"
                    + "  \"isPaint\": false,\n"
                    + "  \"level\": 1,\n"
                    + "  \"typeMob\": 0,\n"
                    + "  \"x\": 802,\n"
                    + "  \"y\": 162\n"
                    + " }\n"
                    + "]";
            JSONArray jsonArray = new JSONArray(data);
            //  System.out.println(jsonArray.length());
        } catch (JSONException ex) {
            Logger.getLogger(Tesst.class.getName()).log(Level.SEVERE, null, ex);
        }
        int count = 0;
        for (int i = 0; i <= 300; i += 3) {
            //count += i;
            System.out.println("lv"+i/2+": "+i*10);
        }
        System.out.println(count);

    }

    /*
     try {
            JSONObject jsonMain = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < 10; i++) {
                JSONObject json = new JSONObject();
                json.put("id", i);
                json.put("x", 500);
                json.put("y", 500);
                jsonArray.put(json);
            }
            jsonMain.put("array", jsonArray);
            System.out.println(jsonArray.getJSONObject(2).get("x"));
            System.out.println(jsonMain.toString(1));

        } catch (JSONException ex) {
        }
     */
}