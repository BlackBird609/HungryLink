package com.example.restaurant;

import com.example.restaurant.ReadandWrite.ReadThread;
import com.example.restaurant.prev_v3.Food;
import com.example.restaurant.prev_v3.Restaurant;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;

public class RestaurantClientApplication extends Application {

    //connection fields
    static String serverAddress = "127.0.0.1";
    static int port = 55555;
    static Client client;

    //fxml fields
    Stage stage;
    HomePageController homePageController;
    FoodSectionController foodSectionController;


    //working fields
    List<Food> foods;
    int RestId;
    String restName;



    //basic methods
    public Stage getStage() {
        return stage;
    }

    static void connectToServer()
    {
        client = new Client(serverAddress,port);
    }

    void disconnectFromServer()
    {
        try
        {
            client.getSocketWrapper().closeConnection();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        try
        {
            this.stage = stage;
            //stage.initStyle(StageStyle.UNDECORATED);
            showLoginPage();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


    //methods to startFxml
    void showLoginPage()
    {
        try {
            connectToServer();
            System.out.println("showing login page");
            // XML Loading using FXMLLoader
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("loginPage.fxml"));
            Parent root = loader.load();

            // Loading and preparing the controller
            LoginPageController controller = loader.getController();
            controller.setMain(this);
            controller.setSocketWrapper(client.getSocketWrapper());
            controller.init();

            // Set the primary stage
            stage.setResizable(false);
            stage.setScene(new Scene(root, 875, 625));
            stage.show();
        } catch (Exception e) {

        }
    }

    void showHomePage()
    {
        try {

            System.out.println("starting the home page");
            System.out.println("starting the continuous reading thread to accept order any time");
            //client.getReadThread().getThread().start();

            // XML Loading using FXMLLoader
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("homePage.fxml"));
            Parent root = loader.load();


            // Loading and preparing the controller
            HomePageController controller = loader.getController();
            controller.setMain(this);
            controller.setSocketWrapper(client.getSocketWrapper());
            controller.setFoods(foods);

            client.getReadThread().setHomePageController(controller);
            homePageController = controller;

            System.out.println("trying to show home");
            controller.init();

            // Set the primary stage
            stage.setResizable(false);
            stage.setScene(new Scene(root, 875, 625));
            stage.show();

            System.out.println("here");

        } catch (Exception e) {

        }
    }
    void ReturnToHomePage()
    {
        try {

            System.out.println("starting the home page");
            //System.out.println("starting the continuous reading thread to accept order any time");
            //client.getReadThread().getThread().start();

            // XML Loading using FXMLLoader
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("homePage.fxml"));
            Parent root = loader.load();

            // Loading and preparing the controller
            HomePageController controller = loader.getController();
            controller.setMain(this);
            controller.setSocketWrapper(client.getSocketWrapper());
            controller.setFoods(foods);

            client.getReadThread().setHomePageController(controller);
            homePageController = controller;

            System.out.println("trying to show home");
            controller.init();

            // Set the primary stage
            stage.setResizable(false);
            stage.setScene(new Scene(root, 875, 625));
            stage.show();

            System.out.println("here");

        } catch (Exception e) {

        }
    }
    void showFoodSectionPage()
    {
        try {

            System.out.println("starting food section page");

            // XML Loading using FXMLLoader
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FoodSection.fxml"));
            Parent root = loader.load();

            System.out.println("loaders loaded");

            // Loading and preparing the controller
            FoodSectionController controller = loader.getController();
            controller.setMain(this);
            controller.setSocketWrapper(client.getSocketWrapper());
            controller.setFoods(foods);
            controller.init();

            foodSectionController = controller;

            System.out.println("preparing done");
            // Set the primary stage
            stage.setResizable(false);
            stage.setScene(new Scene(root, 875, 625));
            stage.show();

        } catch (Exception e) {

        }
    }


    //method to add food to restaurant food list
    public String addMenu(String category,String name,double price)
    {
        if(isAlreadyInTheSameCat(category,name))
        {
            System.out.println("This food is already in the same category of the same restaurant with same name");
            return("cannot add,food already exists with same name in same category!");
        }
        else
        {
            System.out.println("food added");
            Food food = new Food(RestId,category,name,price);
            System.out.println("new food");
            food.printFood();
            foods.add(food);

            //foodSectionController.setFoods(foods);
            foodSectionController.OnACtionShowList1();

            System.out.println("sending to the server");
            //
            try
            {
                client.getSocketWrapper().write("1");
                client.getSocketWrapper().write(restName);
                client.getSocketWrapper().write(category);
                client.getSocketWrapper().write(name);
                client.getSocketWrapper().write(price);
            }
            catch (Exception e)
            {
                System.out.println("got error while adding food to server");
                e.printStackTrace();
            }
            //
            return("successfully added food");
        }
    }
    private boolean isAlreadyInTheSameCat(String category,String name)
    {
        for(Food i:foods)
        {
            if(i.getCategory().equalsIgnoreCase(category) && i.getName().equalsIgnoreCase(name))
            {
                return true;
            }
        }
        return false;
    }

    //other methods

    public void setRestName(String name)
    {
        restName = name;
    }
    public void setRestId(int restId)
    {
        this.RestId = restId;
    }

    public void updateOrders(List<String> oders)
    {
        new UpdateOrder(oders,homePageController);
    }
    public void setFoods(List<Food> foods)
    {
        this.foods = foods;
    }

    public List<Food> getFoods()
    {
        return foods;
    }

    public Client getClient ()
    {
        return client;
    }

    public void keepThreadWaiting()
    {
        try
        {
            client.keepThreadWaiting();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}


class UpdateOrder implements Runnable
{
    Thread thread;
    List<String> orders;
    HomePageController homePageController;

    UpdateOrder(List<String> orders,HomePageController homePageController)
    {
        thread = new Thread(this);
        this.orders = orders;
        this.homePageController = homePageController;
        thread.start();
    }
    @Override
    public void run() {
        homePageController.showOrders(orders);
    }
}