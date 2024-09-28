package com.beatdz.network;

import com.beatdz.lib.Utlis;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class mSocket {

    public Socket socket;
    public Reader dis;
    public Writer dos;
    public String ip;
    public int port;

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public String getIpPort() {
        return getIp() + ":" + getPort();
    }

    public Reader getReader() {
        return dis;
    }

    public Writer getWriter() {
        return dos;
    }

    public static mSocket create(Socket socket) {
        mSocket my = new mSocket();
        try {
            my.socket = socket;
            my.dis = new Reader(socket);
            my.dos = new Writer(socket);
            String[] array = Utlis.split(socket.getRemoteSocketAddress().toString(), ":");
            my.ip = array[0].replace("/", "");
            my.port = Integer.parseInt(array[1]);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return my;
    }

    public boolean isConnect() {
        return this.socket != null && this.socket.isConnected();
    }

    public void close() {
        try {
            if (this.socket != null) {
                this.socket.close();
                this.socket = null;
            }
        } catch (Exception ex) {

        }
        try {
            if (this.dis != null) {
                this.dis.close();
                this.dis = null;
            }
        } catch (Exception ex) {

        }
        try {
            if (this.dos != null) {
                this.dos.close();
                this.dos = null;
            }
        } catch (Exception ex) {

        }
    }
}
