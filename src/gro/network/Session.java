/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gro.network;

import java.net.Socket;
import java.util.*;

/**
 *
 * @author quanh
 */
public class Session {

    public static final List<Session> sessions = new LinkedList<>();

    private Socket socket;

    public boolean connected;
}
