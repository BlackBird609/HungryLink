package com.example.customer.prev_v3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RestaurantDatabase {
    public static void main(String[] args) throws Exception{
        DatabaseManager manager = new DatabaseManager();
        Scanner scanner = new Scanner(System.in);

        int option = 0;
        while(option != 5)
        {
            System.out.println("Main Menu:");
            System.out.println("1) Search Restaurants");
            System.out.println("2) Search Food Items");
            System.out.println("3) Add Restaurant");
            System.out.println("4) Add Food Item to the Menu");
            System.out.println("5) Exit System");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option)
            {
                case 1:
                {
                    int option1 = 0;
                    while(option1 != 7)
                    {

                        System.out.println("Restaurant Searching Options:");
                        System.out.println("1) By Name");
                        System.out.println("2) By Score");
                        System.out.println("3) By Category");
                        System.out.println("4) By Price");
                        System.out.println("5) By Zip Code");
                        System.out.println("6) Different Category Wise List of Restaurants");
                        System.out.println("7) Back to Main Menu");

                        option1 = scanner.nextInt();
                        scanner.nextLine();

                        switch (option1)
                        {
                            case 1:
                            {
                                System.out.println("Enter the name of Restaurant");
                                String temp1 = scanner.nextLine();
                                List<Restaurant> tempListOfRestaurant = new ArrayList<>();

                                //calling the required method
                                tempListOfRestaurant = manager.searchByNameInRest(temp1);

                                for(Restaurant i:tempListOfRestaurant)
                                {
                                    i.printRestaurant();
                                }
                                if(tempListOfRestaurant.isEmpty())
                                {
                                    System.out.println("No such restaurant with this name");
                                }
                                break;
                            }
                            case 2:
                            {
                                System.out.println("Enter the lower limit of score");
                                double d1 = scanner.nextDouble();
                                scanner.nextLine();
                                System.out.println("Enter the upper limit of score");
                                double d2 = scanner.nextDouble();
                                scanner.nextLine();

                                List<Restaurant> tempListOfRestaurant2 = new ArrayList<>();
                                //calling the required method
                                tempListOfRestaurant2 = manager.searchByScoreInRest(d1,d2);

                                for(Restaurant i:tempListOfRestaurant2)
                                {
                                    i.printRestaurant();
                                }
                                if(tempListOfRestaurant2.isEmpty())
                                {
                                    System.out.println("No such restaurant with this score range");
                                }
                                break;
                            }
                            case 3:
                            {
                                System.out.println("Enter the category of restaurant");
                                String temp2 = scanner.nextLine();

                                List<Restaurant> tempListofRest = new ArrayList<>();
                                tempListofRest = manager.searchByCategoryInRest(temp2);

                                for(Restaurant i:tempListofRest)
                                {
                                    i.printRestaurant();
                                }

                                if(tempListofRest.isEmpty())
                                {
                                    System.out.println("No such restaurant with this category");
                                }
                                break;
                            }
                            case 4:
                            {
                                System.out.println("Enter the price of restaurant($$$/$$/$)");
                                String temp3 = scanner.nextLine();

                                List<Restaurant> RestList = new ArrayList<>();
                                RestList = manager.searchByPriceInRest(temp3);


                                if(RestList.isEmpty())
                                {
                                    System.out.println("No such restaurant with this price");
                                }
                                else
                                {
                                    for(Restaurant i:RestList)
                                    {
                                        i.printRestaurant();
                                    }
                                }
                                break;
                            }
                            case 5:
                            {
                                System.out.println("Enter the zip code of restaurant");
                                String tempe4 = scanner.nextLine();

                                List<Restaurant> RestList = new ArrayList<>();

                                RestList = manager.searchByZipCodeInRest(tempe4);
                                if(RestList.isEmpty())
                                {
                                    System.out.println("No such restaurant with this zip code");
                                }
                                else
                                {
                                    for(Restaurant i:RestList)
                                    {
                                        i.printRestaurant();
                                    }
                                }
                                break;
                            }
                            case 6:
                            {
                                List<String> StringtList = new ArrayList<>();
                                StringtList =  manager.printCategoryWiseList();

                                //now stringlist contains list of lines
                                //each line has some strings coma seperated
                                //the first string is the name of the category
                                //the later strings are the name of the restaurant with the category


                                //for now printing the list
                                for(String i:StringtList)
                                {
                                    String ttttString [] = i.split(",",-1);
                                    for(int j=0;j<ttttString.length;j++)
                                    {
                                        System.out.print(ttttString[j]);
                                        if(j==0)
                                        {
                                            System.out.print(":");
                                        }
                                        else if(j>0 && j!=(ttttString.length-1))
                                        {
                                            System.out.print(",");
                                        }
                                    }

                                    System.out.println();
                                }

                                System.out.println("\n");
                                break;
                            }
                            case 7:
                            {
                                break;
                            }
                            default:
                            {
                                System.out.println("You can only enter between 1 to 7");
                                break;
                            }
                        }

                    }

                    break;
                }
                case 2:
                {
                    int option2 = 0;

                    while(option2 != 9)
                    {
                        System.out.println("Food Item Searching Options:");
                        System.out.println("1) By Name");
                        System.out.println("2) By Name in a Given Restaurant");
                        System.out.println("3) By Category");
                        System.out.println("4) By Category in a Given Restaurant");
                        System.out.println("5) By Price Range");
                        System.out.println("6) By Price Range in a Given Restaurant");
                        System.out.println("7) Costliest Food Item(s) on the Menu in a Given Restaurant");
                        System.out.println("8) List of Restaurants and Total Food Item on the Menu");
                        System.out.println("9) Back to Main Menu");

                        option2 = scanner.nextInt();
                        scanner.nextLine();

                        switch(option2)
                        {
                            case 1:
                            {
                                System.out.println("Enter the name of Food item");
                                String temp1 = scanner.nextLine();
                                List<Food> tempListOfFood = new ArrayList<>();

                                //calling the required method
                                tempListOfFood = manager.searchByNameInFood(temp1);

                                for(Food i:tempListOfFood)
                                {
                                    i.printFood();
                                }
                                if(tempListOfFood.isEmpty())
                                {
                                    System.out.println("No such Food item with this name");
                                }
                                break;
                            }
                            case 2:
                            {
                                System.out.println("Enter the name of the food item");
                                String temp1 = scanner.nextLine();
                                System.out.println("Enter the name of the restaurant");
                                String temp2 = scanner.nextLine();

                                List<Food> templist = new ArrayList<>();
                                templist = manager.searchByNameofFoodandRestInFood(temp1,temp2);

                                if(templist.isEmpty())
                                {
                                    System.out.println("No such food item with this name on the menu of this restaurant");
                                }
                                else
                                {
                                    for(Food i:templist)
                                    {
                                        i.printFood();
                                    }
                                }
                                break;
                            }
                            case 3:
                            {
                                System.out.println("Enter the name of food item category");
                                String temp1 = scanner.nextLine();
                                List<Food> templist = new ArrayList<>();
                                templist = manager.searchByCatInFood(temp1);

                                if(templist.isEmpty())
                                {
                                    System.out.println("No such food item with this category");
                                }
                                else
                                {
                                    for(Food i:templist)
                                    {
                                        i.printFood();
                                    }
                                }
                                break;
                            }
                            case 4:
                            {
                                System.out.println("Enter a food item category");
                                String temp1 = scanner.nextLine();
                                System.out.println("Enter a restaurant name");
                                String temp2 = scanner.nextLine();

                                List<Food> templist = new ArrayList<>();
                                templist = manager.searchByCatofFoodInaRest(temp1,temp2);
                                if(templist.isEmpty())
                                {
                                    System.out.println("No such food item with this category on the menu of this restaurant");
                                }
                                else
                                {
                                    for(Food i:templist)
                                    {
                                        i.printFood();
                                    }
                                }
                                break;
                            }
                            case 5:
                            {
                                System.out.println("Enter the lower limit of price of food item");
                                double d1 = scanner.nextDouble();
                                scanner.nextLine();
                                System.out.println("Enter the upper limit of price of food item");
                                double d2 = scanner.nextDouble();
                                scanner.nextLine();
                                List<Food> temp = new ArrayList<>();
                                temp = manager.searchByPriceRangeInFood(d1,d2);
                                if(temp.isEmpty())
                                {
                                    System.out.println("No such food item with this price range");
                                }
                                else
                                {
                                    for(Food i: temp)
                                    {
                                        i.printFood();
                                    }
                                }
                                break;
                            }
                            case 6:
                            {
                                System.out.println("Enter the lower limit of price");
                                double d1 = scanner.nextDouble();
                                scanner.nextLine();
                                System.out.println("Enter the upper limit of price");
                                double d2 = scanner.nextDouble();
                                scanner.nextLine();
                                System.out.println("Enter the name of the restaurant");
                                String name = scanner.nextLine();


                                List<Food> temp = new ArrayList<>();
                                temp = manager.searchFoodByPriceRangeInaRest(d1,d2,name);
                                if(temp.isEmpty())
                                {
                                    System.out.println("No such food item with this price range on the menu of this restaurant");
                                }
                                else
                                {
                                    for(Food i:temp)
                                    {
                                        i.printFood();
                                    }
                                }
                                break;
                            }
                            case 7:
                            {
                                System.out.println("Enter the name of the restaurant");
                                String name = scanner.nextLine();

                                List<List<Food>> tempList = new ArrayList<>();
                                tempList = manager.newSearchForCostliestFoodinaRest(name);

                                for(List<Food> i:tempList)
                                {
                                    System.out.println("\n\nPrinting costliest foods in "+manager.getRestNameFromId(i.get(0).getRestId())+":\n");
                                    for(Food j: i)
                                    {
                                        j.printFood();
                                    }
                                }
                                break;
                            }
                            case 8:
                            {
                                List<String> temp = new ArrayList<>();
                                temp = manager.totalNumbersofFood();
                                for(String i:temp)
                                {
                                    String arr[] = i.split(",",-1);
                                    for(int j=0;j<arr.length;j++)
                                    {
                                        System.out.print(arr[j]);
                                        if(j == 0)
                                        {
                                            System.out.print(":");
                                        }
                                    }
                                    System.out.println();
                                }
                                break;
                            }
                            case 9:
                            {
                                break;
                            }
                            default:
                            {
                                System.out.println("Please Enter a number between 1 to 9");
                                break;
                            }
                        }

                    }


                    break;
                }
                case 3:
                {
                    System.out.println("Enter restaurant name");
                    String name = scanner.nextLine();
                    System.out.println("Enter restaurant id");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter restaurant score");
                    double score = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Enter restaurant price");
                    String price = scanner.nextLine();
                    System.out.println("Enter restaurant zipcode");
                    String zipcode = scanner.nextLine();
                    System.out.println("Enter the number of categories of the restaurant");
                    int cats = scanner.nextInt();
                    scanner.nextLine();
                    String [] catsarr = new String[cats];
                    for(int i=0;i<cats;i++)
                    {
                        System.out.println("Enter category");
                        String temp = scanner.nextLine();
                        catsarr[i] = temp;
                    }
                    manager.addRestaurant(id,name,score,price,zipcode,catsarr);


                    break;
                }
                case 4:
                {
                    System.out.println("Enter restaurant name");
                    String restname = scanner.nextLine();
                    System.out.println("Enter food name");
                    String name = scanner.nextLine();
                    System.out.println("Enter category of the food");
                    String cat = scanner.nextLine();
                    System.out.println("Enter price of the food");
                    double price = scanner.nextDouble();
                    scanner.nextLine();

                    manager.addMenu(restname,cat,name,price);
                    break;
                }
                case 5:
                {
                    manager.exit();
                    break;
                }
                default:
                {
                    System.out.println("Enter a valid number");
                    break;
                }
            }
        }

        //System.out.println("Everything okay");
    }
}