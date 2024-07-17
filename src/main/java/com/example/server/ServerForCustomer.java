package com.example.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import com.example.server.RandWFC.*;
import util.SocketWrapper;

class ServerForCustomer implements Runnable
{
    ServerSocket serverSocket;
    int port;
    Thread thread;
    ServerManager serverManager;
    HomePageController homePageController;
    int count = 1;

    ServerForCustomer(int port,ServerManager serverManager,HomePageController homePageController)
    {
        this.homePageController = homePageController;
        this.serverManager = serverManager;

        try
        {
            serverSocket = new ServerSocket(port);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        this.port = port;
        thread = new Thread(this);
        thread.start();
    }


    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                Socket client = serverSocket.accept();
                serve(client);
                System.out.println("connected to a customer client");
                homePageController.print("connected to a customer client");
            }
            catch (Exception e)
            {
                System.out.println("connection closed");
                System.out.println(e);
            }
        }
    }
    private void serve(Socket client)
    {
        try
        {
            util.SocketWrapper socketWrapper = new SocketWrapper(client);
            //waiting infinitely to read or write
            new ReadThread(socketWrapper,serverManager,homePageController,count);
            count++;
            //new WriteThread(socketWrapper);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}