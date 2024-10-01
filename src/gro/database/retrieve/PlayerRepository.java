/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gro.database.retrieve;

import com.beatdz.network.Message;
import com.beatdz.network.Service;
import com.beatdz.real.Char;
import com.beatdz.server.Client;

import gro.database.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author quanh
 */
public class PlayerRepository {

    private static PlayerRepository i;

    public static PlayerRepository gI() {
        if (i == null) {
            i = new PlayerRepository();
        }
        return i;
    }

    public void createNewPlayer(Client client, int id, String name) {

        try (Connection connection = DBManager.getConnectionForTask(ConfigDB.DATABASE_DYNAMIC, "createNewPlayer"); PreparedStatement preparedStatement = prepareStatement(connection, id, name)) {
            executeUpdate(preparedStatement);// insert player
//            this.loadListPlayer(client);
//            Service.getInstance().sendListCharBoard(session);
        } catch (SQLException e) {
            client.service.sendMessage("Nhân vật bạn chọn không tồn tại!", Service.ColorMessage.RED);
            e.printStackTrace();
        }
    }

    public Char loadListPlayer(Client client) {
        String query = "SELECT `id`, `name` FROM `player` WHERE `account_id` = ?";
        Char player = new Char();
        try (Connection conn = DBManager.getConnectionForTask(ConfigDB.DATABASE_DYNAMIC, "loadListPlayer"); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, 1);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    player = mapResultSetToPlayer(rs);
                }
            }
            return player;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Char mapResultSetToPlayer(ResultSet rs) throws SQLException {
        Char player = new Char();
        player.idEntity = rs.getInt("id");
        player.name = rs.getString("name");
        return player;
    }

    private static PreparedStatement prepareStatement(Connection connection, int id, String name) throws SQLException {
        String sql = "INSERT INTO player (account_id, name) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, name);
        return preparedStatement;
    }

    private static void executeUpdate(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.executeUpdate();
    }

}
