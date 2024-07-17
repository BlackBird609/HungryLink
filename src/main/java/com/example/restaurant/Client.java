package com.example.restaurant;

import com.example.restaurant.ReadandWrite.ReadThread;
import com.example.restaurant.prev_v3.Food;
import com.example.restaurant.util.SocketWrapper;

import java.util.ArrayList;
import java.util.List;

public class Client {
    SocketWrapper socketWrapper;
    ReadThread readThread;

    Client(String serverAddress,int port)
    {
        try
        {
            socketWrapper = new SocketWrapper(serverAddress,port);
            System.out.println("connected to server");
            System.out.println("keeping a continuous read thread to read from server to");
            readThread = new ReadThread(socketWrapper);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public SocketWrapper getSocketWrapper()
    {
        return socketWrapper;
    }
    public ReadThread getReadThread() {
        return readThread;
    }
    public void keepThreadWaiting()
    {
        readThread.keepWaiting();
    }
}
