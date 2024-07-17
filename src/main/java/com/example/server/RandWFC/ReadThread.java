package com.example.server.RandWFC;

import com.example.server.HomePageController;
import com.example.server.ReadAndWriteClass.ReadClass;
import com.example.server.ReadAndWriteClass.WriteClass;
import com.example.server.ServerManager;
import com.example.server.prev_v3.Food;
import com.example.server.prev_v3.Restaurant;
import com.example.server.ReadAndWriteClass.WriteClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReadThread implements Runnable {

    Thread thread;
    util.SocketWrapper socketWrapper;
    ServerManager serverManager;
    String customerUserName;
    HomePageController homePageController;
    int index;
    public ReadThread(util.SocketWrapper socketWrapper, ServerManager serverManager,HomePageController homePageController,int index) {
        this.socketWrapper = socketWrapper;
        this.serverManager = serverManager;
        this.homePageController = homePageController;
        this.index = index;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                //level1

                //enter 1 for login
                //enter 2 for signup

                int level1 = 0;
                String s = (String) socketWrapper.read();
                System.out.println("Received from client at level 1: " + s);
                level1 = Integer.valueOf(s);

                if (level1 == 1)
                {
                    System.out.println("Login Request came from client");
                    homePageController.print("Login Request from client"+index);

                    //Enter username;
                    //Enter Password;

                    String username = (String) socketWrapper.read();
                    String pass = (String) socketWrapper.read();
                    System.out.println("username : " + username);
                    System.out.println("pass : " + pass);

                    if (serverManager.isCorrectCredentialForCustomer(username, pass))
                    {
                        System.out.println("Correct Credential");
                        socketWrapper.write("t");
                        homePageController.print("client"+index+" successfully logged in");

                        customerUserName = username;
                        homePageController.print("client"+index+" username is "+username);


                        //now user has been logged in
                        while (true)
                        {
                            //after login

                            //level2

                            //socketWrapper.write("enter 1 to search rest");
                            //socketWrapper.write("enter 2 to search food");
                            //enter 3 to order food


                            int level2 = 0;
                            String s2 = (String) socketWrapper.read();
                            System.out.println("Received from client at level2: " + s2);


                            level2 = Integer.valueOf(s2);

                            if(level2 == 1)
                            {
                                //user requested for restaurant search

                                //level3

                                //socketWrapper.write("enter 1 to search by name");
                                //socketWrapper.write("enter 2 to search by category");
                                //socketWrapper.write("enter 3 to search by score");
                                //enter 4 to serch by zip code

                                int level3 = 0;
                                String s3 = (String) socketWrapper.read();
                                System.out.println("Received from client at level 3: " + s3);
                                level3 = Integer.valueOf(s3);

                                if(level3 == 1)
                                {

                                    //enter rest name


                                    String restName = (String) socketWrapper.read();
                                    System.out.println("Received from client : " + restName);

                                    List<Restaurant> restaurants = serverManager.searchByNameInRest(restName);

                                    homePageController.print(customerUserName+" requested to search restaurant by name , "+restName);

                                    if(restaurants.size() == 0)
                                    {
                                        System.out.println("no rest found");
                                        socketWrapper.write("no rest found");

                                        homePageController.print("no restaurant found with name "+restName);
                                    }
                                    else
                                    {
                                        homePageController.print("restaurant found");
                                        homePageController.print("found restaurants are:");

                                        System.out.println("rest found");
                                        socketWrapper.write("rest found");

                                        HashMap<Restaurant,List<Food>> hashMap = new HashMap<>();

                                        //now sending the hashmap with restaurants and foods

                                        for(Restaurant i:restaurants)
                                        {
                                            List<Food> temp = serverManager.listOfFoodOfaRestaurant(i.getName());
                                            hashMap.put(i,temp);
                                            homePageController.print(i.getName());

                                        }
                                        WriteClass.writeHashMapOfRestaurantAndFoodList(hashMap,socketWrapper);
                                        System.out.println("sending done");

                                        homePageController.print("results have been sent to "+customerUserName);
                                    }


                                }
                                else if(level3 == 2)
                                {

                                    System.out.println("now i am here ");
                                    //enter restaurant category

                                    String restCategory = (String) socketWrapper.read();
                                    System.out.println("Received restaurant category from client : " + restCategory);
                                    homePageController.print(customerUserName+" requested to search restaurants by category, "+restCategory);

                                    List<Restaurant> restaurants = serverManager.searchByCategoryInRest(restCategory);

                                    if(restaurants.size() == 0)
                                    {
                                        homePageController.print("No restaurant found for the category, "+restCategory);
                                        System.out.println("no rest found");
                                        socketWrapper.write("no rest found");
                                    }
                                    else
                                    {
                                        homePageController.print("restaurants found for the category , "+restCategory);
                                        System.out.println("rest found");
                                        socketWrapper.write("rest found");

                                        HashMap<Restaurant,List<Food>> hashMap = new HashMap<>();


                                        homePageController.print("found restaurants are:");

                                        //now sending the hashmap with restaurants and foods
                                        for(Restaurant i:restaurants)
                                        {
                                            List<Food> temp = serverManager.listOfFoodOfaRestaurant(i.getName());
                                            hashMap.put(i,temp);
                                            homePageController.print(i.getName());

                                        }
                                        WriteClass.writeHashMapOfRestaurantAndFoodList(hashMap,socketWrapper);
                                        System.out.println("sending done");
                                        homePageController.print("results sent to "+customerUserName);
                                    }

                                }
                                else if(level3 == 3)
                                {
                                    //enter upper limit of score
                                    //enter lower limit of score

                                    String LowerScore = (String) socketWrapper.read();
                                    String UpperScore = (String) socketWrapper.read();
                                    System.out.println("Received restaurants upper and lower score form client : " + UpperScore+" and "+LowerScore);

                                    List<Restaurant> restaurants = serverManager.searchByScoreInRest(Double.valueOf(LowerScore),Double.valueOf(UpperScore));

                                    if(restaurants.size() == 0)
                                    {
                                        System.out.println("no rest found");
                                        socketWrapper.write("no rest found");
                                    }
                                    else
                                    {
                                        System.out.println("rest found");
                                        socketWrapper.write("rest found");

                                        HashMap<Restaurant,List<Food>> hashMap = new HashMap<>();

                                        //now sending the hashmap with restaurants and foods
                                        for(Restaurant i:restaurants)
                                        {
                                            List<Food> temp = serverManager.listOfFoodOfaRestaurant(i.getName());
                                            hashMap.put(i,temp);

                                        }
                                        WriteClass.writeHashMapOfRestaurantAndFoodList(hashMap,socketWrapper);
                                        System.out.println("sending done");
                                    }

                                }
                                else if(level3 == 4)
                                {
                                    //enter zip code

                                    String zipCode = (String) socketWrapper.read();
                                    System.out.println("Received zip code from client : " + zipCode);

                                    List<Restaurant> restaurants = serverManager.searchByZipCodeInRest(zipCode);

                                    if(restaurants.size() == 0)
                                    {
                                        System.out.println("no rest found");
                                        socketWrapper.write("no rest found");
                                    }
                                    else
                                    {
                                        System.out.println("rest found");
                                        socketWrapper.write("rest found");

                                        HashMap<Restaurant,List<Food>> hashMap = new HashMap<>();

                                        //now sending the hashmap with restaurants and foods
                                        for(Restaurant i:restaurants)
                                        {
                                            List<Food> temp = serverManager.listOfFoodOfaRestaurant(i.getName());
                                            hashMap.put(i,temp);

                                        }
                                        WriteClass.writeHashMapOfRestaurantAndFoodList(hashMap,socketWrapper);
                                        System.out.println("sending done");
                                    }
                                }

                            }
                            else if(level2 == 2)
                            {
                                //user requested for food search
                                System.out.println("user requested for food search" );

                                //level3

                                //socketWrapper.write("enter 1 to search by name");
                                //socketWrapper.write("enter 2 to search by food and restaurant name");
                                //socketWrapper.write("enter 3 to search by price range");
                                //socketWrapper.write("enter 4 to search by category");

                                int level3 = 0;
                                String s3 = (String) socketWrapper.read();
                                System.out.println("Received from client at level 3: " + s3);
                                level3 = Integer.valueOf(s3);


                                if(level3 == 1)
                                {
                                    System.out.println("user requested for food search by name" );
                                    //enter food name
                                    String foodName = (String) socketWrapper.read();
                                    System.out.println("Received food name from client : " + foodName);
                                    List<Food> foods = serverManager.searchByNameInFood(foodName);


                                    if(foods.size() == 0)
                                    {
                                        System.out.println("no food found");
                                        socketWrapper.write("no food found");
                                    }
                                    else
                                    {
                                        System.out.println("food found");
                                        socketWrapper.write("food found");

                                        WriteClass.writeListOfFood(foods,socketWrapper);
                                        System.out.println("sending done");
                                    }


                                }
                                else if(level3 == 2)
                                {

                                    System.out.println("user requested for food search by food and restaurant name" );
                                    //enter food name
                                    String foodName = (String) socketWrapper.read();
                                    System.out.println("Received food name from client : " + foodName);
                                    //enter restaurant name
                                    String restName = (String) socketWrapper.read();
                                    System.out.println("Received rest name from client : " + restName);


                                    List<Food> foods = serverManager.searchByNameofFoodandRestInFood(foodName,restName);

                                    if(foods.size() == 0)
                                    {
                                        System.out.println("no food found");
                                        socketWrapper.write("no food found");
                                    }
                                    else
                                    {
                                        System.out.println("food found");
                                        socketWrapper.write("food found");

                                        WriteClass.writeListOfFood(foods,socketWrapper);
                                        System.out.println("sending done");
                                    }

                                }
                                else if(level3 == 3)
                                {
                                    System.out.println("user requested for food search by price range" );
                                    //enter lower limit of price
                                    //enter upper limit of price


                                    String lower = (String) socketWrapper.read();
                                    String upper = (String) socketWrapper.read();

                                    List<Food> foods = serverManager.searchByPriceRangeInFood(Double.valueOf(lower),Double.valueOf(upper));

                                    if(foods.size() == 0)
                                    {
                                        System.out.println("no food found");
                                        socketWrapper.write("no food found");
                                    }
                                    else
                                    {
                                        System.out.println("food found");
                                        socketWrapper.write("food found");

                                        WriteClass.writeListOfFood(foods,socketWrapper);
                                        System.out.println("sending done");
                                    }

                                }
                                else if(level3 == 4)
                                {
                                    //enter category
                                    String category = (String) socketWrapper.read();

                                    //enter rest name
                                    String rest = (String) socketWrapper.read();

                                    List<Food> foods = serverManager.searchByCatofFoodInaRest(category,rest);

                                    if(foods.size() == 0)
                                    {
                                        System.out.println("no food found");
                                        socketWrapper.write("no food found");
                                    }
                                    else
                                    {
                                        System.out.println("food found");
                                        socketWrapper.write("food found");

                                        WriteClass.writeListOfFood(foods,socketWrapper);
                                        System.out.println("sending done");
                                    }

                                }
                            }

                            else if(level2 == 3)
                            {
                                System.out.println("customer placed an order");
                                //enter the restid of that food
                                //enter the food name of that food
                                Food food = ReadClass.readFood(socketWrapper);
                                System.out.println("Received restId and foodName from client : " + food.getRestId()+" and "+food.getName());
                                System.out.println("Customer who ordered the food is: "+customerUserName);

                                //now do other stuffs when creating restaurant client
                                System.out.println("now sending this to the restaurant");
                                String orderStatus =  serverManager.FoodOrder(food,username);
                                System.out.println("sending the order status to customer: "+orderStatus);
                                socketWrapper.write(orderStatus);
                            }
                        }
                    }
                    else
                    {
                        System.out.println("Incorrect Credential");
                        socketWrapper.write("f");
                    }
                }
                else if(level1 == 2)
                {
                    System.out.println("Signup Request");

                    //socketWrapper.write("Enter username");
                    //socketWrapper.write("Enter Password");
                    String username = (String) socketWrapper.read();
                    String pass = (String) socketWrapper.read();
                    System.out.println("username : " + username);
                    System.out.println("pass : " + pass);
                    if (serverManager.isAlreadyACustomerWithThisUserName(username))
                    {
                       System.out.println("Username Taken");
                       socketWrapper.write("username taken");
                    }
                    else
                    {
                       System.out.println("Username not Taken");
                       socketWrapper.write("success");
                       serverManager.addCustomer(username, pass);
                    }
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
