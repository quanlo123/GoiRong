/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beatdz.network;

/**
 * @author admin
 */
public interface ISession {

    public abstract boolean isConnected();

    public abstract void setHandler(IMessageHandler messageHandler);

    public abstract void sendMessage(Message message);

    public abstract void close();

}
