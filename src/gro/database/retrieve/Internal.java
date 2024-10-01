/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gro.database.retrieve;

import com.beatdz.network.Service;
import com.beatdz.server.Client;
import gro.database.ConfigDB;
import gro.database.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author quanh
 */
public class Internal {

    public static boolean login(Client client, String tk, String mk) {
        if (tk.isEmpty() || mk.isEmpty() || tk.equals("1") || mk.equals("1")) {
            client.service.sendMessage("Vui lòng nhập thông tin đăng nhập hợp lệ!", Service.ColorMessage.WHITE);
            return false;
        }
        String query = "SELECT * FROM `account` WHERE `username` = ? AND `password` = ? LIMIT 1;";
        try (Connection conn = DBManager.getConnectionForTask(ConfigDB.DATABASE_DYNAMIC, "login"); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, tk);
            ps.setString(2, mk);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    client.service.sendMessage("Tài khoản hoặc mật khẩu không chính xác!", Service.ColorMessage.WHITE);
                    return false;
                } else {
                    if (rs.getByte("ban") == 1) {
                        client.service.sendMessage("Tài khoản đã bị khóa vì có hành vi xấu ảnh hưởng server", Service.ColorMessage.WHITE);
                        return false;
                    }
                    client.id = rs.getInt("id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
