/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gro.network;

import com.beatdz.datareal.Map;
import com.beatdz.lib.Utlis;
import com.beatdz.network.Message;
import com.beatdz.network.Reader;
import com.beatdz.network.Writer;
import com.beatdz.server.DataCenter;
import com.beatdz.server.Server;
import gro.controller.Controller;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Arriety
 */
public class Session {

    public ArrayList<Message> listMessage = new ArrayList<>();
    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

    private Socket socket;
    public Writer writer;
    public Reader reader;

    private Controller controller = new Controller();
    public boolean connected;

    public Session(Socket socket) {
        try {
            socket.setTcpNoDelay(true);
//            this.writer = socket.getWriter();
//            this.reader = socket.getReader();
            this.socket = socket;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {
        try {
            this.startReadThread();
            this.startSendThread();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startReadThread() {
        try {
            executorService.execute(() -> {
                while (this.connected) {
                    Messager msg;
                    try {
                        msg = this.readMessage();
                        this.controller.onMessager(this, msg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message message) {
        if (this.connected) {
            this.listMessage.add(message);
//            synchronized (waitSendSession) {
//                try {
//                    waitSendSession.notify();
//                } catch (Exception ex) {
//
//                }
//            }
        }
    }

    private void startSendThread() {
        while (this.connected) {
            try {
                while (!this.listMessage.isEmpty()) {
                    Message message = this.listMessage.get(0);
                    this.listMessage.remove(0);
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
                                this.writer.dos.writeByte(message.cmd);
                                this.writer.dos.write(array);
                                break;
                            default:
                                if (!message.isNen) {
                                    this.writer.dos.writeByte(message.cmd);
                                    if (array != null && array.length > 0) {
                                        this.writer.dos.writeShort(array.length);
                                        this.writer.dos.write(array);
                                    } else {
                                        this.writer.dos.writeShort(0);
                                    }
                                } else {
                                    this.writer.dos.writeByte(-80);
                                    this.writer.dos.writeByte(-122);
                                    array = Utlis.deflateByteArray(Server.arrData2);
                                    if (array != null && array.length > 0) {
                                        this.writer.dos.writeShort(array.length);
                                        this.writer.dos.write(array);
                                    } else {
                                        this.writer.dos.writeShort(0);
                                    }
                                }
                                break;

                        }
                        this.writer.dos.flush();
                    }

                }
//                synchronized (this.session.waitSendSession) {
//                    try {
//                        this.session.waitSendSession.wait(3600000);
//                    } catch (Exception ex) {
//
//                    }
//                }
            } catch (Exception ex) {
                ex.printStackTrace();
                this.disconnect();
                return;
            }
        }
    }

    public Messager readMessage() {
        try {
            byte cmd1 = 0;
            byte cmd = reader.dis.readByte();

            int size = 0;
            switch (cmd) {
                // nem vao controller
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
            System.out.println("cmd: " + cmd);
            return new Messager(cmd, array);
        } catch (Exception e) {
            e.printStackTrace();
            this.disconnect();
            return null;
        }
    }

    private void disconnect() {
        try {
            socket.close();
            executorService.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
