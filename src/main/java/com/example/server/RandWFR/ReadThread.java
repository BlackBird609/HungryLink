package com.example.server.RandWFR;

import com.example.server.ReadAndWriteClass.WriteClass;
import com.example.server.ServerManager;
import com.example.server.prev_v3.Food;

import java.util.List;

public class ReadThread implements Runnable{

    Thread thread;
    util.SocketWrapper socketWrapper;
    ServerManager serverManager;
    public ReadThread(util.SocketWrapper socketWrapper,ServerManager serverManager)
    {
        this.socketWrapper = socketWrapper;
        this.serverManager = serverManager;
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
                //enter 1 for login

                int level1 = 0;
                level1 = Integer.valueOf((String) socketWrapper.read());
                System.out.println("restaurant client selected option "+level1+" at level 1");

                if(level1 == 1)
                {
                    System.out.println("login request from restaurant");

                    //enter rest id
                    //enter rest pass
                    int restId = (Integer) socketWrapper.read();
                    String pass = (String)socketWrapper.read();

                    System.out.println("restaurant id : "+restId);
                    System.out.println("restaurant pass : "+pass);

                    if(serverManager.isCorrectCredentialForRestaurant(restId,pass))
                    {
                        System.out.println("successful login");
                        socketWrapper.write("t");

                        //adding the restaurant in the hashmap of logged in restaurants
                        serverManager.getRestaurantSockets().put(restId,socketWrapper);

                        List<Food> foods = serverManager.listOfFoodOfARest(Integer.valueOf(restId));


                        WriteClass.writeListOfFood(foods,socketWrapper);

                        System.out.println("name of the logged in restaurant is "+serverManager.getRestNameFromId(restId));
                        socketWrapper.write(serverManager.getRestNameFromId(restId));

                        while(true)
                        {
                            //now we will serve the rest
                            //enter 1 for adding restaurant
                            //enter 2 for logout

                            int level2 = 0;

                            level2 = Integer.valueOf((String)socketWrapper.read());
                            System.out.println("user selected at level2 "+level2);

                            if(level2 == 1)
                            {
                                System.out.println("user requested for adding food");
                                //enter the name of the rest
                                //enter the name of the category of the food
                                //enter the name of the food
                                //enter the price of the food(Double)

                                String restName = (String)socketWrapper.read();
                                String categoryName = (String)socketWrapper.read();
                                String foodName = (String)socketWrapper.read();
                                double price = (Double)socketWrapper.read();

                                System.out.println("rest name : "+restName);
                                System.out.println("category name : "+categoryName);
                                System.out.println("Food name : "+foodName);
                                System.out.println("Price : "+price);

                                serverManager.addMenu(restName,categoryName,foodName,price);
                                System.out.println("successfully added to the menu of the restaurant on server");
                            }
                            else if(level2 == 2)
                            {
                                System.out.println("restaurant cho0sed to log out");
                                //log out means connection with the server will be close
                                try
                                {
                                    socketWrapper.closeConnection();
                                    System.out.println("connection closed");
                                }
                                catch (Exception e)
                                {
                                    System.out.println(e);
                                }
                                break;
                            }
                        }
                    }
                    else
                    {
                        System.out.println("incorrect credentials");
                        socketWrapper.write("f");
                    }
                    System.out.println("exiting the opening while true");
                    break;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            try
            {
                socketWrapper.closeConnection();
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
    }
}
