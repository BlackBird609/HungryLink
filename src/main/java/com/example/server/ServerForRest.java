package com.example.server;

import com.example.server.RandWFR.ReadThread;
import com.example.server.RandWFR.WriteThread;
import com.example.server.ReadAndWriteClass.WriteClass;
import com.example.server.prev_v3.Food;
import util.SocketWrapper;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

class ServerForRest implements Runnable
{
    ServerSocket serverSocket;

    ServerManager serverManager;

    int port;
    Thread thread;

    HomePageController homePageController;

    ServerForRest(int port,ServerManager serverManager,HomePageController homePageController)
    {
        this.homePageController = homePageController;
        this.serverManager = serverManager;

        try
        {
            serverSocket = new ServerSocket(port);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        this.port = port;

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run()
    {
        try
        {
            while(true)
            {
                Socket client = serverSocket.accept();
                serve(client);
                System.out.println("connected to a client");
                homePageController.print("connected to a client(restaurant)");
            }
        }
        catch(Exception e)
        {
            System.out.println("got exception while creating server socket for restaurants");
            System.out.println(e);
        }
    }

    private void serve(Socket client)
    {
        try
        {
            System.out.println("serving started");
            SocketWrapper socketWrapper = new SocketWrapper(client);

            //waiting infinitely to read or write in this thread
            new ReadThread(socketWrapper,serverManager);
            new WriteThread(socketWrapper);
            System.out.println("serving done");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

