package com.example.customer;

import util.SocketWrapper;

public class Client
{
    util.SocketWrapper socketWrapper;
    Client(String serverAddress,int port)
    {
        try
        {
            socketWrapper = new util.SocketWrapper(serverAddress,port);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public SocketWrapper getSocketWrapper() {
        return socketWrapper;
    }

}