package com.example.restaurant.prev_v3;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    //private variables
    private List<Restaurant> restaurants;
    private List<Food> foods;


    //constructor
    DatabaseManager() throws Exception {
        //
        restaurants = new ArrayList<>();
        foods = new ArrayList<>();

        //
        readAndCreate();

    }


    //public methods
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
            if ( i.getName().toLowerCase().equals(name.toLowerCase()) ) {
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

    public void exit() throws Exception {
        writeToFile();
    }


    public List<Food> searchByNameofFoodandRestInFood(String foodnamae, String restname) {
        //creating the returning list
        List<Food> l = new ArrayList<>();
        for (Food i : foods) {
            if ( ( i.getName().equalsIgnoreCase(foodnamae) || (i.getName().toLowerCase().contains(foodnamae.toLowerCase())) )&& doesThisIdBelongToThisNamedRest(i.getRestId(), restname)) {
                l.add(i);
                ;
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
           String s = i.getName()+","+String.valueOf(d);
           temp.add(s);
        }
        return temp;
    }

    void addRestaurant(int id,String name,double score,String price,String zipcode,String [] categories)
    {
        if(isAlreadyARestWithThisId(id))
        {
            System.out.println("Restaurant exists with this id");
            return;
        }
        else if(isAlreadyARestWithThisName(name))
        {
            System.out.println("Restaurant exists with this name");
            return;
        }
        else if(categories.length>3 || categories.length<1)
        {
            System.out.println("Invalid number of categories");
            return;
        }

        restaurants.add(new Restaurant(id,name,score,price,zipcode,categories));
    }

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
    private List<Food> listOfFoodOfaRestaurant(String name)
    {
        List<Integer> id = getIdsFromName(name);

        List<Food> temp = new ArrayList<>();
        for(Food i:foods)
        {
            int count = 0;
            for(int j:id)
            {
                if(j == i.getRestId())
                {
                    count = 1;
                }
            }
            if(count == 1)
            {
                temp.add(i);
            }
        }

        return temp;
    }
    private List<Food> listOfFoodOfaRestaurant2(String name)
    {
        int id = getIdFromName(name);
        List<Food> temp = new ArrayList<>();

        for(Food i:foods)
        {
            if(i.getRestId() == id)
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
            if(i.getName().toLowerCase().equals(s.toLowerCase()))
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
    private void readAndCreate() throws Exception
    {
        //reading file
        BufferedReader br_r = new BufferedReader(new FileReader("restaurant.txt"));
        BufferedReader br_f = new BufferedReader(new FileReader("menu.txt"));

        //creating restaurant objects
        String s1;
        while ((s1 = br_r.readLine()) != null) {
            String arr[] = s1.split(",", -1);
            String ss[] = new String[arr.length - 5];
            for (int i = 0; i < (arr.length - 5); i++) {
                ss[i] = arr[i + 5];
            }

            restaurants.add(new Restaurant(Integer.valueOf(arr[0]), arr[1], Double.valueOf(arr[2]), arr[3], arr[4], ss));
        }
        br_r.close();

        //creating food objects
        String s2;
        while ((s2 = br_f.readLine()) != null) {
            String arr[] = s2.split(",", -1);
            String ss[] = new String[arr.length];
            foods.add(new Food(Integer.valueOf(arr[0]), arr[1], arr[2], Double.valueOf(arr[3])));
        }
        br_f.close();
    }


    private void writeToFile() throws Exception
    {

        //writing to file
        BufferedWriter bw_r = new BufferedWriter(new FileWriter("restaurant.txt"));
        BufferedWriter bw_f = new BufferedWriter(new FileWriter("menu.txt"));

        //System.out.println("to be written in file rest");
        for(Restaurant i:restaurants)
        {
            String s3 = i.getId()+","+i.getName()+","+i.getScore()+","+i.getPrice()+","+i.getZipcode();
            //System.out.print(s3);
            bw_r.write(s3);
            String arr[] = i.getCategories();
            //arr.
            for(int j=0;j<arr.length;j++)
            {
                //System.out.print(","+arr[j]);
                bw_r.write(",");
                bw_r.write(arr[j]);
            }
            bw_r.write(System.lineSeparator());
        }
        bw_r.close();


        //System.out.println("to be written in file me");
        for(Food i:foods)
        {
            String s3 = i.getRestId()+","+i.getCategory()+","+i.getName()+","+i.getPrice();
            //System.out.print(s3);
            bw_f.write(s3);
            bw_f.write(System.lineSeparator());
            //System.out.print("\n");
        }
        bw_f.close();

    }

}