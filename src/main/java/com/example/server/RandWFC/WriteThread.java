package com.example.server.RandWFC;

import java.util.Scanner;

public class WriteThread implements Runnable{

    Thread thread;
    util.SocketWrapper socketWrapper;

    public WriteThread(util.SocketWrapper socketWrapper)
    {
        this.socketWrapper = socketWrapper;
        this.thread = new Thread(this);
        thread.start();
    }
    @Override
    public void run()
    {
        try
        {
            while(true)
            {
                System.out.println("Enter a message to send to customer");
                String s;
                Scanner scanner = new Scanner(System.in);
                s =  scanner.nextLine();
                socketWrapper.write(s);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
