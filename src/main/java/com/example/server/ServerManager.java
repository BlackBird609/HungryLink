package com.example.server;

import com.example.server.ReadAndWriteClass.WriteClass;
import com.example.server.prev_v3.DatabaseManager;
import com.example.server.prev_v3.Food;
import com.example.server.prev_v3.Restaurant;
import com.example.server.serverManagerIO.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ServerManager {

    private List<Restaurant> restaurants;
    private List<Food> foods;

    private HashMap<String,String> customerIdPass;
    private HashMap<Integer,String> restaurantIdPass;
    private HashMap<Integer, util.SocketWrapper> restaurantSockets;

    ServerManager()
    {
        restaurants = new ArrayList<>();
        foods = new ArrayList<>();
        customerIdPass = new HashMap<>();
        restaurantIdPass = new HashMap<>();
        this.restaurantSockets = new HashMap<>();

        readFromFile();
    }

    public void exit() throws Exception
    {
        writeToFile();
    }

    //methods for searching rest
    public List<Restaurant> searchByNameInRest(String name) {
        //creating the returning list
        List<Restaurant> l = new ArrayList<>();
        for (Restaurant i : restaurants) {
            if ( i.getName().toLowerCase().contains(name.toLowerCase()) ) {
                l.add(i);
            }
        }
        return l;
    }
    public List<Restaurant> searchByAccurateNameInRest(String name) {
        //creating the returning list
        List<Restaurant> l = new ArrayList<>();
        for (Restaurant i : restaurants) {
            if ( i.getName().equalsIgnoreCase(name) ) {
                l.add(i);
            }
        }
        return l;
    }

    public List<Restaurant> searchByScoreInRest(double range1, double range2) {
        List<Restaurant> l = new ArrayList<>();
        for (Restaurant i : restaurants) {
            if ((range1 <= i.getScore()) && (i.getScore() <= range2)) {
                l.add(i);
            }
        }
        return l;
    }

    public List<Restaurant> searchByCategoryInRest(String cat) {
        List<Restaurant> l = new ArrayList<>();
        for (Restaurant i : restaurants) {
            for (String j : i.getCategories()) {
                if ( cat.equalsIgnoreCase(j) || j.toLowerCase().contains(cat.toLowerCase()) ){
                    l.add(i);
                }
            }
        }
        return l;
    }

    public List<Restaurant> searchByPriceInRest(String price) {
        List<Restaurant> temp = new ArrayList<>();
        for (Restaurant i : restaurants) {
            if (i.getPrice().equals(price)) {
                temp.add(i);
            }
        }
        return temp;
    }

    public List<Restaurant> searchByZipCodeInRest(String zip) {
        List<Restaurant> temp = new ArrayList<>();
        for (Restaurant i : restaurants) {
            if (i.getZipcode().equals(zip)) {
                temp.add(i);
            }
        }

        return temp;
    }


    //print category wise list function returns list of Lines where
    //each line is comma seperated strings
    //first string is the name of category
    //and the other strings are the name of the restaurants with that category
    public List<String> printCategoryWiseList()
    {
        //creating the list of string that will be returned
        List<String> l = new ArrayList<>();

        //creating list of all unique categories
        List<String> listOfCats = new ArrayList<>();
        for (Restaurant i : restaurants) {
            for (String j : i.getCategories()) {
                //j is a category here
                //need to check if this category is already added or not
                int added = 0;
                for (String k : listOfCats) {
                    if (k.equals(j)) {
                        added = 1;
                        break;
                    }
                }
                if (added == 0) {
                    listOfCats.add(j);
                    //System.out.println("entering in list of cats "+j);
                }
            }
        }


        //
        for (String i : listOfCats) {
            String TempString = i + ",";
            //System.out.print(i+":");

            //creating a list of the restaurants that contains that cat
            List<String> namesOfRest = new ArrayList<>();
            int membersOfRest = 0;

            for (Restaurant j : restaurants) {
                int present = 0;
                for (String k : j.getCategories()) {
                    if (k.equals(i)) {
                        present = 1;
                        break;
                    }
                }
                if (present == 1) {
                    namesOfRest.add(j.getName());
                    membersOfRest++;
                }
            }

            //now printing the members of the array
            for (int hh = 0; hh < membersOfRest; hh++) {
                //System.out.print(namesOfRest.get(hh));
                TempString = TempString + namesOfRest.get(hh);
                if (hh != membersOfRest - 1) {
                    //System.out.print(",");
                    TempString = TempString + ",";
                }
            }
            l.add(TempString);
        }

        return l;
    }

    //methods for searching food
    public List<Food> searchByNameInFood(String name) {
        //creating the returning list
        List<Food> l = new ArrayList<>();
        for (Food i : foods) {
            if (i.getName().equalsIgnoreCase(name) || i.getName().toLowerCase().contains(name.toLowerCase())) {
                l.add(i);
            }
        }
        return l;
    }

    public List<Food> searchByNameofFoodandRestInFood(String foodnamae, String restname) {
        //creating the returning list
        List<Food> l = new ArrayList<>();
        for (Food i : foods) {
            if ( ( i.getName().equalsIgnoreCase(foodnamae) || (i.getName().toLowerCase().contains(foodnamae.toLowerCase())) )&& doesThisIdBelongToThisNamedRest(i.getRestId(), restname)) {
                l.add(i);
            }
        }
        return l;
    }

    List<Food> searchByCatInFood(String cat) {
        List<Food> templist = new ArrayList<>();
        for (Food i : foods) {
            if (i.getCategory().equalsIgnoreCase(cat) || i.getCategory().toLowerCase().contains(cat.toLowerCase())) {
                templist.add(i);
            }
        }
        return templist;
    }

    public List<Food> searchByCatofFoodInaRest(String cat, String rest) {
        List<Food> temp2 = new ArrayList<>();

        for (Food i : foods) {
            if (i.getCategory().toLowerCase().contains(cat.toLowerCase()) && doesThisIdBelongToThisNamedRest(i.getRestId(), rest)) {
                temp2.add(i);
            }
        }

        return temp2;
    }

    public List<Food> searchByPriceRangeInFood(double d1, double d2) {
        List<Food> temp = new ArrayList<>();
        for (Food i : foods) {
            if ((d1 <= i.getPrice()) && (i.getPrice() <= d2)) {
                temp.add(i);
            }
        }
        return temp;
    }

    public List<Food> searchFoodByPriceRangeInaRest(double d1, double d2, String name) {
        List<Food> f = new ArrayList<>();
        f = listOfFoodOfaRestaurant(name);

        List<Food> ff = new ArrayList<>();

        for (Food i : f) {
            if ((d1 <= i.getPrice()) && (i.getPrice() <= d2)) {
                ff.add(i);
            }
        }
        return ff;
    }

    public List<Food> searchForCostliestFoodinaRest(String name)
    {
        List<Food> temp2 = new ArrayList<>();

        List<Restaurant> restlist = new ArrayList<>();
        restlist = searchByNameInRest(name);

        for(Restaurant r:restlist)
        {
            List<Food> temp = new ArrayList<>();
            temp = listOfFoodOfaRestaurant(r.getName());
            double d = getThePriceOfTheCostliestFoodInaRest(r.getName());

            for(Food i:temp)
            {
                if(i.getPrice() == d)
                {
                    temp2.add(i);
                }
            }


        }

        return temp2;

    }
    public List<List<Food>> newSearchForCostliestFoodinaRest(String name)
    {
        //List<Food> temp2 = new ArrayList<>();
        List<List<Food>> temp2 = new ArrayList<>();
        List<Restaurant> restlist = new ArrayList<>();
        restlist = searchByNameInRest(name);

        int resti = 0;
        for(Restaurant r:restlist)
        {
            List<Food> temp = new ArrayList<>();
            temp2.add(new ArrayList<>());
            temp = listOfFoodOfaRestaurant(r.getName());
            double d = getThePriceOfTheCostliestFoodInaRest(r.getName());

            for(Food i:temp)
            {
                if(i.getPrice() == d)
                {
                    temp2.get(resti).add(i);
                }

            }
            resti++;
        }

        return temp2;

    }
    public List<String> totalNumbersofFood()
    {
        List<String> temp = new ArrayList<>();
        for(Restaurant i:restaurants)
        {
            double d = totalNumbersofFoodInaRest(i.getName());
            String s = i.getName()+","+ d;
            temp.add(s);
        }
        return temp;
    }

    //ordering food

    public HashMap<Integer, util.SocketWrapper> getRestaurantSockets()
    {
        return restaurantSockets;
    }
    public String FoodOrder(Food oreredFood, String username)
    {
        //finding the socketWrapper of the restaurant
        util.SocketWrapper socketWrapper = restaurantSockets.get(oreredFood.getRestId());
        if(socketWrapper == null)
        {
            System.out.println("This restaurant is not open now, cannot order food from here at this moment");
            return("failed");
        }
        else
        {
            try
            {
                System.out.println("the restaurant is open");
                System.out.println("ordered food is :");
                oreredFood.printFood();
                System.out.println("ordering customer is: "+username);
                System.out.println("ordered to restaurant "+getRestNameFromId(oreredFood.getRestId()));

                socketWrapper.write("food order");
                WriteClass.writeFood(oreredFood,socketWrapper);
                socketWrapper.write(username);

                return ("success");
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return ("failed");
            }
        }
    }

    //adding new restaurant to server
    String addRestaurant(int id,String name,double score,String price,String zipcode,String [] categories)
    {
        if(isAlreadyARestWithThisId(id))
        {
            return("Restaurant exists with this id");

        }
        else if(isAlreadyARestWithThisName(name))
        {
            return("Restaurant exists with this name");

        }
        else if(categories.length>3 || categories.length<1)
        {
            return("Invalid number of categories");
        }

        restaurants.add(new Restaurant(Integer.valueOf(id),name,score,price,zipcode,categories));
        WriteToRest writeToRest = new WriteToRest(restaurants);
        try
        {
            writeToRest.getThread1().join();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ("successful");
    }

    //adding new food to a restaurant
    public void addMenu(String nameofrest,String category,String name,double price)
    {
        List<Restaurant> restList = new ArrayList<>();
        restList = searchByAccurateNameInRest(nameofrest);
        if(restList.size() == 0)
        {
            System.out.println("No restaurant with this name");
            return;
        }
        else if(isAlreadyInTheSameRestAndSameCat(nameofrest,category,name))
        {
            System.out.println("This food is already in the same category of the same restaurant with same name");
            return;
        }
        foods.add(new Food(getIdFromName(nameofrest),category,name,price));
        new WriteToMenu(foods);
    }


    //for customer signup and login
    public boolean isAlreadyACustomerWithThisUserName(String s)
    {
        //checking if already s1 exists
        int exists = 0;
        for(Map.Entry<String,String> entry: customerIdPass.entrySet())
        {
            if(entry.getKey().equals(s))
            {
                exists = 1;
                break;
            }
        }
        if(exists == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public void addCustomer(String s1,String s2) throws InterruptedException {
        if(!isAlreadyACustomerWithThisUserName(s1))
        {
            customerIdPass.put(s1,s2);
            WriteToCustomerIdPass writeToCustomerIdPass = new WriteToCustomerIdPass(customerIdPass);
            writeToCustomerIdPass.getThread1().join();
        }
    }
    public boolean isCorrectCredentialForCustomer(String name,String pass)
    {
        if(!isAlreadyACustomerWithThisUserName(name))
        {
            return false;
        }
        if(customerIdPass.get(name).equals(pass))
        {
            return true;
        }
        return false;
    }

    //for restaurant login
    public boolean isAlreadyARestaurantWithThisRestId(int restId)
    {
        //checking if already s1 exists
        int exists = 0;
        for(Map.Entry<Integer,String> entry: restaurantIdPass.entrySet())
        {
            if(entry.getKey() == restId)
            {
                exists = 1;
                break;
            }
        }
        if(exists == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean isCorrectCredentialForRestaurant(int restId,String pass)
    {
        if(!isAlreadyARestaurantWithThisRestId(restId))
        {
            return false;
        }
        if(restaurantIdPass.get(restId).equals(pass))
        {
            return true;
        }
        return false;
    }

    //public only for the purpose of printing
    public String getRestNameFromId(int id)
    {
        for(Restaurant i:restaurants)
        {
            if(i.getId() == id)
            {
                return i.getName();
            }
        }
        return("Not-Found");
    }


    //private helper function

    private boolean isAlreadyInTheSameRestAndSameCat(String nameofrest,String category,String name)
    {
        List<Food> listoffood = new ArrayList<>();
        listoffood = listOfFoodOfaRestaurant(nameofrest);
        for(Food i:listoffood)
        {
            if(i.getCategory().equalsIgnoreCase(category) && i.getName().equalsIgnoreCase(name))
            {
                return true;
            }
        }
        return false;
    }
    private boolean isAlreadyARestWithThisId(int id)
    {
        for(Restaurant i:restaurants)
        {
            if(i.getId() == id)
            {
                return true;
            }
        }
        return false;
    }
    private boolean isAlreadyARestWithThisName(String id)
    {
        for(Restaurant i:restaurants)
        {
            if(i.getName().equalsIgnoreCase(id))
            {
                return true;
            }
        }
        return false;
    }

    private int totalNumbersofFoodInaRest(String name)
    {
        List<Food> foodlist = new ArrayList<>();
        foodlist = listOfFoodOfaRestaurant(name);
        return foodlist.size();
    }

    private double getThePriceOfTheCostliestFoodInaRest(String name)
    {
        List<Food> temp = listOfFoodOfaRestaurant(name);
        double max = -99;
        for(Food i:temp)
        {
            if(i.getPrice()>max)
            {
                max = i.getPrice();
            }
        }

        return max;
    }
    public List<Food> listOfFoodOfaRestaurant(String name)
    {
        List<Integer> id = getIdsFromName(name);

        List<Food> temp = new ArrayList<>();
        for(Food i:foods)
        {
            int count = 0;
            for(int j:id)
            {
                if (j == i.getRestId()) {
                    count = 1;
                    break;
                }
            }
            if(count == 1)
            {
                temp.add(i);
            }
        }

        return temp;
    }
    private int getIdFromName(String s)
    {

        int f = 0;
        //List<Integer> f = new ArrayList<>();
        for(Restaurant i:restaurants)
        {
            if(i.getName().equalsIgnoreCase(s))
            {
                //f.add(i.getId());
                f = i.getId();
                break;
            }
        }
        return f;
    }
    private List<Integer> getIdsFromName(String s)
    {

        //int f = 0;
        List<Integer> f = new ArrayList<>();
        for(Restaurant i:restaurants)
        {
            if(i.getName().toLowerCase().contains(s.toLowerCase()))
            {
                f.add(i.getId());
                break;
            }
        }
        return f;
    }

    private boolean doesThisIdBelongToThisNamedRest(int id,String nm)
    {
        boolean temp = false;
        for(Restaurant i:restaurants)
        {
            if(i.getId() == id)
            {
                if(nm.equalsIgnoreCase(i.getName()) || i.getName().toLowerCase().contains(nm.toLowerCase()))
                {
                    temp =  true;
                }
            }
        }
        return temp;
    }

    public List<Food> listOfFoodOfARest(int restId)
    {
        List<Food> temp = new ArrayList<>();
        for(Food food:foods)
        {
            if(food.getRestId() == restId)
            {
                temp.add(food);
            }
        }
        return temp;
    }

    //read and write
    public void readFromFile()
    {
        ReadFromRest readFromRest = new ReadFromRest(restaurants);
        ReadFromMenu readFromMenu = new ReadFromMenu(foods);
        ReadFromCustomerIdPass readFromCustomerIdPass = new ReadFromCustomerIdPass(customerIdPass);
        ReadFromRestaurantIdPass readFromRestaurantIdPass = new ReadFromRestaurantIdPass(restaurantIdPass);

        try
        {
            readFromMenu.getThread1().join();
            readFromRest.getThread1().join();
            readFromCustomerIdPass.getThread1().join();
            readFromRestaurantIdPass.getThread1().join();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void writeToFile()
    {
        WriteToMenu writeToMenu = new WriteToMenu(foods);
        WriteToRest writeToRest = new WriteToRest(restaurants);
        WriteToCustomerIdPass writeToCustomerIdPass = new WriteToCustomerIdPass(customerIdPass);
        WriteToRestaurantIdPass writeToRestaurantIdPass = new WriteToRestaurantIdPass(restaurantIdPass);

        try
        {
            writeToMenu.getThread1().join();
            writeToRest.getThread1().join();
            writeToCustomerIdPass.getThread1().join();
            writeToRestaurantIdPass.getThread1().join();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

}
