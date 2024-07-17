package com.example.customer;

import com.example.customer.ReadAndWriteClass.ReadClass;
import com.example.customer.ReadAndWriteClass.WriteClass;
import com.example.customer.prev_v3.Food;
import com.example.customer.prev_v3.Restaurant;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.util.Callback;

import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;


public class HomeController {
    public Button buttonShowingResults;
    public Button searchingButton1;
    public Button searchingButton2;
    public Button searchinButton3;
    public Button searchingButton4;
    public Button searchingButton5;
    public Button searchingButton6;
    public Button searchingButton7;
    public Button searchingButton8;
    public AnchorPane OrderStatusShowingAnchorPane;
    public AnchorPane SearchResultShowingAnchorPane;
    int countShowSearchFilters = 0;
    int countShowResultButton = 0;
    public HBox UpperHboxId;
    public StackPane showreslutOnstackPaneid;
    public AnchorPane searchFilterShowingAnchorPane;
    public RadioButton buttonSearchFilter;
    private util.SocketWrapper socketWrapper;
    private CustomerClientApplication customerClientApplication;

    void setSocketWrapper(util.SocketWrapper s)
    {
        socketWrapper = s;
    }

    void setMain(CustomerClientApplication customerClientApplication)
    {
        this.customerClientApplication = customerClientApplication;
    }

    void init() throws IOException, ClassNotFoundException
    {

        showInputForSearchFoodByName(UpperHboxId);

        OrderStatusShowingAnchorPane.setVisible(false);



        searchFilterShowingAnchorPane.setVisible(false);
        ButtonStyle2(buttonShowingResults);
        ButtonStyle3(searchingButton1);
        ButtonStyle3(searchingButton2);
        ButtonStyle3(searchinButton3);
        ButtonStyle3(searchingButton4);
        ButtonStyle3(searchingButton5);
        ButtonStyle3(searchingButton6);
        ButtonStyle3(searchingButton7);
        ButtonStyle3(searchingButton8);


        showSearchFilters();
        countShowSearchFilters = 1;
        buttonSearchFilter.setText("Hide Filters");

        SearchResultShowingAnchorPane.setVisible(false);

    }


    //for search in food by name
    public void OnActionSearchFoodByName(ActionEvent actionEvent)
    {
        ButtonStyle3(searchingButton1);
        ButtonStyle3(searchingButton2);
        ButtonStyle3(searchinButton3);
        ButtonStyle3(searchingButton4);
        ButtonStyle3(searchingButton5);
        ButtonStyle3(searchingButton6);
        ButtonStyle3(searchingButton7);
        ButtonStyle3(searchingButton8);

        ButtonStyleSearchButtonClicked(searchingButton1);


        showInputForSearchFoodByName(UpperHboxId);
    }

    private void showInputForSearchFoodByName(HBox hBox)
    {
        hBox.getChildren().clear();
        hBox.setSpacing(30);

        TextField textField = new TextField();
        textField.setPromptText("Enter food name");
        Button button = new Button("Search");

        hBox.getChildren().addAll(textField, button);

        button.setOnAction((ActionEvent event) ->
        {
            try
            {
                socketWrapper.write("2");
                socketWrapper.write("1");
                socketWrapper.write(textField.getText());

                String s = (String) socketWrapper.read();
                if (s.contains("no"))
                {
                    System.out.println("no rest with this name");
                    //then handle the event
                }
                else
                {
                    System.out.println("foods found\n\n");
                    List<Food> foods = ReadClass.readListOfFood(socketWrapper);

                    System.out.println("the foods are");
                    for (Food food:foods)
                    {
                        food.printFood();
                    }

                    //

                    //creating list view

                    showListViewForFoods(foods);
                }
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        });
    }

    //for search food by name of food and restaurant
    public void OnActionSearchFoodByFoodNameandRestName(ActionEvent actionEvent)
    {
        ButtonStyle3(searchingButton1);
        ButtonStyle3(searchingButton2);
        ButtonStyle3(searchinButton3);
        ButtonStyle3(searchingButton4);
        ButtonStyle3(searchingButton5);
        ButtonStyle3(searchingButton6);
        ButtonStyle3(searchingButton7);
        ButtonStyle3(searchingButton8);

        ButtonStyleSearchButtonClicked(searchingButton2);
        showInputForSearchFoodByFoodNameandRestName(UpperHboxId);
    }

    private void showInputForSearchFoodByFoodNameandRestName(HBox hBox)
    {
        hBox.getChildren().clear();

        hBox.setSpacing(30);

        VBox vbox1 = new VBox();
        vbox1.setSpacing(30);

        VBox vbox2 = new VBox();
        vbox2.setSpacing(30);

        TextField textField = new TextField();
        textField.setPromptText("Enter name of food");

        TextField textField2 = new TextField();
        textField2.setPromptText("Enter name of restaurant");

        vbox1.getChildren().addAll(textField,textField2);

        Button button = new Button("Search");

        vbox2.getChildren().addAll(button);

        hBox.getChildren().addAll(vbox1,vbox2);

        button.setOnAction((ActionEvent event) ->
        {
            try
            {
                socketWrapper.write("2");
                socketWrapper.write("2");
                socketWrapper.write(textField.getText());
                socketWrapper.write(textField2.getText());

                String s = (String) socketWrapper.read();
                if (s.contains("no"))
                {
                    System.out.println("no rest with this name");
                    //then handle the event
                }
                else
                {
                    System.out.println("foods found\n\n");
                    List<Food> foods = ReadClass.readListOfFood(socketWrapper);

                    System.out.println("the foods are");
                    for (Food food:foods)
                    {
                        food.printFood();
                    }


                    showListViewForFoods(foods);
                }
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        });
    }


    // for search food by Price range
    public void OnActionSearchFoodByPriceRange(ActionEvent actionEvent)
    {
        ButtonStyle3(searchingButton1);
        ButtonStyle3(searchingButton2);
        ButtonStyle3(searchinButton3);
        ButtonStyle3(searchingButton4);
        ButtonStyle3(searchingButton5);
        ButtonStyle3(searchingButton6);
        ButtonStyle3(searchingButton7);
        ButtonStyle3(searchingButton8);

        ButtonStyleSearchButtonClicked(searchinButton3);
        ShowInputForSearchFoodByPriceRange(UpperHboxId);
    }

    private void ShowInputForSearchFoodByPriceRange(HBox hBox)
    {
        hBox.getChildren().clear();

        hBox.setSpacing(30);

        VBox vbox1 = new VBox();
        vbox1.setSpacing(30);

        VBox vbox2 = new VBox();
        vbox2.setSpacing(30);

        TextField textField = new TextField();
        textField.setPromptText("Enter Lower Limit of price");

        TextField textField2 = new TextField();
        textField2.setPromptText("Enter Upper Limit of price");

        vbox1.getChildren().addAll(textField,textField2);

        Button button = new Button("Search");

        vbox2.getChildren().addAll(button);

        hBox.getChildren().addAll(vbox1,vbox2);
        button.setOnAction((ActionEvent event) ->
        {
            try
            {
                socketWrapper.write("2");
                socketWrapper.write("3");
                socketWrapper.write(textField.getText());
                socketWrapper.write(textField2.getText());

                String s = (String) socketWrapper.read();
                if (s.contains("no"))
                {
                    System.out.println("no food with in this price range");
                    //then handle the event
                }
                else
                {
                    System.out.println("foods found\n\n");
                    List<Food> foods = ReadClass.readListOfFood(socketWrapper);

                    System.out.println("the foods are");
                    for (Food food:foods)
                    {
                        food.printFood();
                    }


                    showListViewForFoods(foods);
                }
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        });

    }


    //for search food by category in a given restaurant
    public void OnActionSearchFoodByCategory(ActionEvent actionEvent)
    {
        ButtonStyle3(searchingButton1);
        ButtonStyle3(searchingButton2);
        ButtonStyle3(searchinButton3);
        ButtonStyle3(searchingButton4);
        ButtonStyle3(searchingButton5);
        ButtonStyle3(searchingButton6);
        ButtonStyle3(searchingButton7);
        ButtonStyle3(searchingButton8);

        ButtonStyleSearchButtonClicked(searchingButton4);
        showInputForSearchFoodByCategory(UpperHboxId);
    }

    private void showInputForSearchFoodByCategory(HBox hBox)
    {
        hBox.getChildren().clear();
        hBox.setSpacing(30);


        TextField textField = new TextField();
        textField.setPromptText("Enter category");
        Button button = new Button("Search");

        hBox.getChildren().addAll(textField, button);

        button.setOnAction((ActionEvent event) ->
        {
            try
            {
                socketWrapper.write("2");
                socketWrapper.write("4");
                socketWrapper.write(textField.getText());

                String s = (String) socketWrapper.read();
                if (s.contains("no"))
                {
                    System.out.println("no food with this category");
                    //then handle the event
                }
                else
                {
                    System.out.println("foods found\n\n");
                    List<Food> foods = ReadClass.readListOfFood(socketWrapper);

                    System.out.println("the foods are");
                    for (Food food:foods)
                    {
                        food.printFood();
                    }

                    //

                    //creating list view

                    showListViewForFoods(foods);
                }
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        });
    }

    //for search in rest , search by name
    public void showInputForSearchRestByName(ActionEvent actionEvent) throws IOException, ClassNotFoundException
    {
        ButtonStyle3(searchingButton1);
        ButtonStyle3(searchingButton2);
        ButtonStyle3(searchinButton3);
        ButtonStyle3(searchingButton4);
        ButtonStyle3(searchingButton5);
        ButtonStyle3(searchingButton6);
        ButtonStyle3(searchingButton7);
        ButtonStyle3(searchingButton8);

        ButtonStyleSearchButtonClicked(searchingButton5);
        showInputForSearchRestByName(UpperHboxId);
    }
    private void showInputForSearchRestByName(HBox hBox) throws IOException, ClassNotFoundException
    {
        hBox.getChildren().clear();
        hBox.setSpacing(30);


        TextField textField = new TextField();
        textField.setPromptText("Enter restaurant name");
        Button button = new Button("Search");
        hBox.getChildren().addAll(textField, button);


        button.setOnAction((ActionEvent event) ->
        {
            try {
                socketWrapper.write("1");
                socketWrapper.write("1");
                socketWrapper.write(textField.getText());

                String s = (String) socketWrapper.read();
                if (s.contains("no")) {
                    System.out.println("no rest with this name");
                    //then handle the event
                } else {
                    System.out.println("rest found\n\n");
                    HashMap<Restaurant, List<Food>> hashMap = ReadClass.readHashMapOfRestaurantAndFoodList(socketWrapper);
                    for (Map.Entry<Restaurant, List<Food>> entry : hashMap.entrySet()) {
                        Restaurant restaurant = entry.getKey();
                        restaurant.printRestaurant();
                        List<Food> foodList = entry.getValue();
                        System.out.println("Food List : ");
                        for (Food food : foodList) {
                            food.printFood();
                        }
                        System.out.println("\n\n");
                    }

                    //

                    //creating list view

                    List<Restaurant> restaurants = new ArrayList<>();
                    // Create a ListView and set its cell factory
                    for (Map.Entry<Restaurant, List<Food>> entry : hashMap.entrySet()) {
                        Restaurant restaurant = entry.getKey();
                        restaurants.add(restaurant);
                    }

                    //
                    showListViewForRestaurant(restaurants,hashMap);
                }
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        });
    }

    //for search in rest , search by category
    public void OnACtionSeearchRestByCat(ActionEvent actionEvent)
    {
        ButtonStyle3(searchingButton1);
        ButtonStyle3(searchingButton2);
        ButtonStyle3(searchinButton3);
        ButtonStyle3(searchingButton4);
        ButtonStyle3(searchingButton5);
        ButtonStyle3(searchingButton6);
        ButtonStyle3(searchingButton7);
        ButtonStyle3(searchingButton8);

        ButtonStyleSearchButtonClicked(searchingButton6);
        try {
            showInputForSearchRestByCategory(UpperHboxId);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    private void showInputForSearchRestByCategory(HBox hBox) throws IOException, ClassNotFoundException {
        System.out.println("now try to retrive data from server for search in rest by category");
        hBox.getChildren().clear();
        hBox.setSpacing(30);


        TextField textField = new TextField();
        textField.setPromptText("Enter restaurant category");
        Button button = new Button("Search");
        hBox.getChildren().addAll(textField, button);

        button.setOnAction((ActionEvent event) ->
        {
            try {
                socketWrapper.write("1");
                socketWrapper.write("2");
                socketWrapper.write(textField.getText());

                String s = (String) socketWrapper.read();
                if (s.contains("no")) {
                    System.out.println("no rest with this category");

                } else {
                    System.out.println("rest found for this category\n\n");

                    HashMap<Restaurant, List<Food>> hashMap = ReadClass.readHashMapOfRestaurantAndFoodList(socketWrapper);
                    System.out.println("Printing the received hashmap for searching in rest by category");
                    for (Map.Entry<Restaurant, List<Food>> entry : hashMap.entrySet()) {
                        Restaurant restaurant = entry.getKey();
                        restaurant.printRestaurant();
                        List<Food> foodList = entry.getValue();
                        System.out.println("Food List : ");
                        for (Food food : foodList) {
                            food.printFood();
                        }
                        System.out.println("\n\n");
                    }



                    //creating list of restaurants

                    List<Restaurant> restaurants = new ArrayList<>();
                    // Create a ListView and set its cell factory
                    for (Map.Entry<Restaurant, List<Food>> entry : hashMap.entrySet()) {
                        Restaurant restaurant = entry.getKey();
                        restaurants.add(restaurant);
                    }
                    //
                    showListViewForRestaurant(restaurants,hashMap);
                }
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        });

    }

    //for search in rest , search by score
    public void OnActionSearchRestByScore(ActionEvent actionEvent)
    {
        ButtonStyle3(searchingButton1);
        ButtonStyle3(searchingButton2);
        ButtonStyle3(searchinButton3);
        ButtonStyle3(searchingButton4);
        ButtonStyle3(searchingButton5);
        ButtonStyle3(searchingButton6);
        ButtonStyle3(searchingButton7);
        ButtonStyle3(searchingButton8);

        ButtonStyleSearchButtonClicked(searchingButton7);
        showInputForSearchRestByScore(UpperHboxId);
    }
    private void showInputForSearchRestByScore(HBox hBox)
    {
        hBox.getChildren().clear();

        hBox.setSpacing(30);

        VBox vbox1 = new VBox();
        vbox1.setSpacing(30);

        VBox vbox2 = new VBox();
        vbox2.setSpacing(30);

        TextField textField = new TextField();
        textField.setPromptText("Enter Lower Limit of Score");

        TextField textField2 = new TextField();
        textField2.setPromptText("Enter Upper Limit of Score");

        vbox1.getChildren().addAll(textField,textField2);

        Button button = new Button("Search");

        vbox2.getChildren().addAll(button);

        hBox.getChildren().addAll(vbox1,vbox2);

        button.setOnAction((ActionEvent event) ->
        {
            try {
                socketWrapper.write("1");
                socketWrapper.write("3");
                socketWrapper.write(textField.getText());
                socketWrapper.write(textField2.getText());

                String s = (String) socketWrapper.read();
                if (s.contains("no")) {
                    System.out.println("no rest within this range of score");

                } else {
                    System.out.println("rest found for this range of score\n\n");

                    HashMap<Restaurant, List<Food>> hashMap = ReadClass.readHashMapOfRestaurantAndFoodList(socketWrapper);

                    System.out.println("Printing the received hashmap for searching in rest by score range");
                    for(Map.Entry<Restaurant, List<Food>> entry : hashMap.entrySet())
                    {
                        Restaurant restaurant = entry.getKey();
                        restaurant.printRestaurant();
                        List<Food> foodList = entry.getValue();
                        System.out.println("Food List : ");
                        for (Food food : foodList) {
                            food.printFood();
                        }
                        System.out.println("\n\n");
                    }


                    //creating list of restaurants
                    List<Restaurant> restaurants = new ArrayList<>();

                    for (Map.Entry<Restaurant, List<Food>> entry : hashMap.entrySet()) {
                        Restaurant restaurant = entry.getKey();
                        restaurants.add(restaurant);
                    }

                    showListViewForRestaurant(restaurants,hashMap);
                }
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        });
    }

    //for search in rest , search by zip code
    public void OnActionSearchRestByZipCode(ActionEvent actionEvent)
    {
        ButtonStyle3(searchingButton1);
        ButtonStyle3(searchingButton2);
        ButtonStyle3(searchinButton3);
        ButtonStyle3(searchingButton4);
        ButtonStyle3(searchingButton5);
        ButtonStyle3(searchingButton6);
        ButtonStyle3(searchingButton7);
        ButtonStyle3(searchingButton8);

        ButtonStyleSearchButtonClicked(searchingButton8);
        showInputForSeachRestByZipCode(UpperHboxId);
    }

    private void showInputForSeachRestByZipCode(HBox hBox)
    {
        hBox.getChildren().clear();
        hBox.setSpacing(30);
        TextField textField = new TextField();
        textField.setPromptText("Enter Zip Code");
        Button button = new Button("Search");
        hBox.getChildren().addAll(textField, button);

        button.setOnAction((ActionEvent event) ->
        {
            try {
                socketWrapper.write("1");
                socketWrapper.write("4");
                socketWrapper.write(textField.getText());

                String s = (String) socketWrapper.read();
                if (s.contains("no")) {
                    System.out.println("no rest within this zipcode");

                } else {
                    System.out.println("rest found for this zipcode\n\n");

                    HashMap<Restaurant, List<Food>> hashMap = ReadClass.readHashMapOfRestaurantAndFoodList(socketWrapper);

                    System.out.println("Printing the received hashmap for searching in rest by zipcode");
                    for(Map.Entry<Restaurant, List<Food>> entry : hashMap.entrySet())
                    {
                        Restaurant restaurant = entry.getKey();
                        restaurant.printRestaurant();
                        List<Food> foodList = entry.getValue();
                        System.out.println("Food List : ");
                        for (Food food : foodList) {
                            food.printFood();
                        }
                        System.out.println("\n\n");
                    }


                    //creating list of restaurants
                    List<Restaurant> restaurants = new ArrayList<>();

                    for (Map.Entry<Restaurant, List<Food>> entry : hashMap.entrySet()) {
                        Restaurant restaurant = entry.getKey();
                        restaurants.add(restaurant);
                    }

                    showListViewForRestaurant(restaurants,hashMap);
                }
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        });
    }

    //showing list of restaurants in a list view
    private void showListViewForRestaurant(List<Restaurant> restaurants,HashMap<Restaurant,List<Food>> hashMap)
    {
        ListView<Restaurant> listView = new ListView<>();
        listView.setCellFactory(new Callback<>()
        {

            @Override
            public ListCell<Restaurant> call(ListView<Restaurant> param) {
                return new ListCell<Restaurant>() {
                    @Override
                    protected void updateItem(Restaurant item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null)
                        {
                            String imagePath = "/com/example/customer/pictures/rest1.png";
                            Image image = new Image(getClass().getResourceAsStream(imagePath));

                            BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, true));
                            setBackground(new Background(backgroundImage));

                            // Create a VBox for each cell
                            VBox cellContent = new VBox();
                            cellContent.setAlignment(Pos.CENTER_LEFT);

                            // Create the first sub-cell (with a background image) and an HBox
                            HBox subCell1 = new HBox();

                            subCell1.setAlignment(Pos.CENTER);
                            subCell1.setBackground(new Background(new BackgroundFill(Color.rgb(173, 216, 230), null, null)));
                            subCell1.setMinHeight(30); // Set the height as needed

                            //creating the second sub cell
                            HBox subCell2 = new HBox();
                            //subCell2.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
                            subCell2.setMinHeight(150);

                            // Create an HBox in the first sub-cell
                            HBox hboxInSubCell1 = new HBox();
                            hboxInSubCell1.setSpacing(175.2);

                            //add texts in the hbox of the first subcell
                            Font boldFont = Font.font("Arial", FontWeight.BOLD, 14);
                            Label text1 = new Label(item.getName());
                            Label text2 = new Label("Score: " + String.valueOf(item.getScore()));
                            Label text3 = new Label("Price: " + item.getPrice());
                            text3.setFont(boldFont);
                            text2.setFont(boldFont);
                            text1.setFont(boldFont);
                            text1.setAlignment(Pos.CENTER_LEFT);
                            text2.setAlignment(Pos.CENTER_LEFT);
                            text3.setAlignment(Pos.CENTER_LEFT);

                            HBox.setHgrow(text1,Priority.ALWAYS);

                            hboxInSubCell1.getChildren().addAll(text1,text2,text3);

                            //now add the hbox inside the subcell1
                            subCell1.getChildren().add(hboxInSubCell1);

                            // Add the sub-cells to the VBox
                            cellContent.getChildren().addAll(subCell1, subCell2);

                            // Set the cell's content
                            setGraphic(cellContent);

                            setOnMouseClicked((MouseEvent event) -> {
                                try
                                {

                                    Restaurant clickedRestaurant = getItem();
                                    System.out.println("so the clicked restaurant is : ");
                                    System.out.println(clickedRestaurant.getName());

                                    //now showing the list of foods of the clicked restaurant
                                    List<Food> foods = hashMap.get(clickedRestaurant);
                                    showListViewForFoods(foods);

                                }
                                catch (Exception e)
                                {
                                    System.out.println(e);
                                }
                            });

                        }
                        else
                        {
                            setText(null);
                            setGraphic(null);
                        }
                    }
                };
            }



        });

        listView.getItems().addAll(restaurants);
        StackPane root = showreslutOnstackPaneid;
        root.getChildren().add(listView);

        SearchResultShowingAnchorPane.setVisible(true);
    }



    //showing list of Foods in a list view
    private void showListViewForFoods2(List<Food> foods)
    {
        ListView<Food> listView = new ListView<>();
        listView.setCellFactory(new Callback<>()
        {

            @Override
            public ListCell<Food> call(ListView<Food> param) {
                return new ListCell<Food>() {
                    @Override
                    protected void updateItem(Food item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null)
                        {
                            String imagePath = "/com/example/customer/pictures/food.jpeg";
                            Image image = new Image(getClass().getResourceAsStream(imagePath));

                            BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, true));
                            setBackground(new Background(backgroundImage));

                            // Create a VBox for each cell
                            VBox cellContent = new VBox();
                            cellContent.setAlignment(Pos.CENTER_LEFT);

                            // Create the first sub-cell (with a background image) and an HBox
                            HBox subCell1 = new HBox();

                            //subCell1.setAlignment(Pos.CENTER);
                            subCell1.setBackground(new Background(new BackgroundFill(Color.rgb(173, 216, 230), null, null)));
                            subCell1.setMinHeight(30); // Set the height as needed

                            //creating the second sub cell
                            HBox subCell2 = new HBox();
                            subCell2.setAlignment(Pos.CENTER_RIGHT);
                            Button OrdeFood = new Button("Order");
                            subCell2.getChildren().add(OrdeFood);

                            OrdeFood.setOnAction((ActionEvent event) ->
                            {
                                try
                                {
                                    System.out.println("Ordering food: ");
                                    Food food = getItem();
                                    food.printFood();
                                    socketWrapper.write("3");
                                    WriteClass.writeFood(food,socketWrapper);
                                    //socketWrapper.write();
                                    String orderStatus = (String) socketWrapper.read();
                                    if(orderStatus.contains("f"))
                                    {
                                        System.out.println("food ordering failed");
                                        System.out.println("the restaurant is currently closed");

                                        Label failed = new Label("            Cannot order,the restaurant is currently closed!");

                                        //OrderStatusShowingAnchorPane.getChildren().clear();
                                        OrderStatusShowingAnchorPane.getChildren().add(failed);
                                        OrderStatusShowingAnchorPane.setVisible(true);

                                        Thread delayedThread = new Thread(() -> {
                                            try {
                                                Thread.sleep(5000);

                                                System.out.println("Task executed after 5 seconds");
                                                OrderStatusShowingAnchorPane.setVisible(false);
                                                failed.setVisible(false);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        });

                                        delayedThread.start();


                                    }
                                    else if(orderStatus.contains("s"))
                                    {
                                        System.out.println("Food ordering successful");


                                        //OrderStatusShowingAnchorPane.getChildren().clear();
                                        Label success = new Label("          Order has been placed");
                                        OrderStatusShowingAnchorPane.getChildren().add(success);
                                        OrderStatusShowingAnchorPane.setVisible(true);


                                        Thread delayedThread = new Thread(() -> {
                                            try {
                                                Thread.sleep(5000);

                                                System.out.println("Task executed after 5 seconds");
                                                OrderStatusShowingAnchorPane.setVisible(false);
                                                success.setVisible(false);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        });

                                        delayedThread.start();
                                    }
                                }
                                catch (Exception e)
                                {

                                }
                            });

                            //subCell2.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
                            subCell2.setMinHeight(100);

                            // Create an HBox in the first sub-cell
                            HBox hboxInSubCell1 = new HBox();
                            hboxInSubCell1.setSpacing(10);

                            //add texts in the hbox of the first subcell
                            //Font boldFont = Font.font("Arial", FontWeight.BOLD, 14);
                            Label text1 = new Label(item.getName());
                            Label text2 = new Label("Price: " + String.valueOf(item.getPrice()));
                            Label text3 = new Label("Category: " + item.getCategory());

                            hboxInSubCell1.getChildren().addAll(text1,text2,text3);

                            //now add the hbox inside the subcell1
                            subCell1.getChildren().add(hboxInSubCell1);

                            // Add the sub-cells to the VBox
                            cellContent.getChildren().addAll(subCell1, subCell2);

                            // Set the cell's content
                            setGraphic(cellContent);

                        } else {
                            setText(null);
                            setGraphic(null);
                        }
                    }
                };
            }
        });

        listView.getItems().addAll(foods);
        StackPane root = showreslutOnstackPaneid;
        root.getChildren().add(listView);

        SearchResultShowingAnchorPane.setVisible(true);
    }

    void showListViewForFoods(List<Food> foods)
    {
        // Create a FlowPane to display foods
        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(10));
        flowPane.setVgap(10);
        flowPane.setHgap(10);

        // Create a ScrollPane to enable scrolling
        ScrollPane scrollPane = new ScrollPane(flowPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // Add the ScrollPane to the scene
        //Scene scene = new Scene(scrollPane, 800, 600);


        // Iterate through the list of foods and create a cell for each food
        for (Food food : foods) {
            Pane cell = createFoodCell(food);
            flowPane.getChildren().add(cell);
        }



        showreslutOnstackPaneid.getChildren().add(scrollPane);
        showreslutOnstackPaneid.getChildren().addAll(flowPane);


        SearchResultShowingAnchorPane.setVisible(true);

    }

    //private helper methods for showing foods

    private Pane createFoodCell(Food food) {
        Pane cell = new Pane();
        cell.setMinWidth(200);
        cell.setMaxWidth(200);
        cell.setStyle("-fx-background-color: #FFFFFF;"); // White background


        String imagePath = "/com/example/customer/pictures/food.jpeg";
        Image image = new Image(getClass().getResourceAsStream(imagePath));

        ImageView imageView = new ImageView(image);


        imageView.setFitWidth(200);
        imageView.setFitHeight(150);

        // Create Text elements for name, price, and category
        Text nameText = createText(food.getName(), 10, 180);
        Text priceText = createText("Price: " + food.getPrice(), 10, 200);
        Text categoryText = createText("Category: " + food.getCategory(), 10, 220);

        Button button = new Button("Order");
        button.setOnAction(event -> {
            System.out.println("Ordering food: " + food.getName());
            try
            {
                System.out.println("Ordering food: ");
                food.printFood();
                socketWrapper.write("3");
                WriteClass.writeFood(food,socketWrapper);
                //socketWrapper.write();
                String orderStatus = (String) socketWrapper.read();
                if(orderStatus.contains("f"))
                {
                    System.out.println("food ordering failed");
                    System.out.println("the restaurant is currently closed");

                    Label failed = new Label("            Cannot order,the restaurant is currently closed!");

                    //OrderStatusShowingAnchorPane.getChildren().clear();
                    OrderStatusShowingAnchorPane.getChildren().add(failed);
                    OrderStatusShowingAnchorPane.setVisible(true);

                    Thread delayedThread = new Thread(() -> {
                        try {
                            Thread.sleep(5000);

                            System.out.println("Task executed after 5 seconds");
                            OrderStatusShowingAnchorPane.setVisible(false);
                            failed.setVisible(false);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });

                    delayedThread.start();


                }
                else if(orderStatus.contains("s"))
                {
                    System.out.println("Food ordering successful");


                    //OrderStatusShowingAnchorPane.getChildren().clear();
                    Label success = new Label("          Order has been placed");
                    OrderStatusShowingAnchorPane.getChildren().add(success);
                    OrderStatusShowingAnchorPane.setVisible(true);


                    Thread delayedThread = new Thread(() -> {
                        try {
                            Thread.sleep(5000);

                            System.out.println("Task executed after 5 seconds");
                            OrderStatusShowingAnchorPane.setVisible(false);
                            success.setVisible(false);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });

                    delayedThread.start();
                }
            }
            catch (Exception e)
            {

            }

        });
        ButtonStyleSearchButtonClicked(button);

        // Add all elements to the cell
        cell.getChildren().addAll(imageView, nameText, priceText, categoryText,button);

        return cell;
    }
    private Text createText(String text, double x, double y) {
        Text t = new Text(text);
        t.setFont(Font.font("Arial", 12)); // Set font and size as needed
        t.setX(x);
        t.setY(y);
        return t;
    }

    //

    public void OnACtionSearchFilter(ActionEvent actionEvent)
    {
        if(countShowSearchFilters == 0)
        {
            System.out.println("showing filters");
            showSearchFilters();
            countShowSearchFilters = 1;
            //buttonSearchFilter.setText("Hide Filters");
        }
        else if(countShowSearchFilters == 1)
        {
            System.out.println("hiding filters");
            hideSearchFilters();
            countShowSearchFilters = 0;
            //buttonSearchFilter.setText("Show Filters");
        }
    }

    private void showSearchFilters()
    {
        searchFilterShowingAnchorPane.setVisible(true);
    }

    private void hideSearchFilters()
    {
        searchFilterShowingAnchorPane.setVisible(false);
    }

    public void OnActionButtonShowingResult(ActionEvent actionEvent)
    {
        if(countShowResultButton == 0)
        {
            System.out.println("Hiding results");
            showreslutOnstackPaneid.setVisible(false);
            SearchResultShowingAnchorPane.setVisible(false);
            buttonShowingResults.setText("Show Result");
            countShowResultButton = 1;
        }
        else if(countShowResultButton == 1)
        {
            System.out.println("Showing Results");
            showreslutOnstackPaneid.setVisible(true);
            SearchResultShowingAnchorPane.setVisible(true);
            buttonShowingResults.setText("Hide Results");
            countShowResultButton = 0;
        }
    }

    void ButtonStyle(Button button)
    {
        button.setStyle("-fx-background-radius: 10; -fx-background-color: #3498db; -fx-text-fill: white;");
    }


    void ButtonStyle2(Button button)
    {
        button.setStyle("-fx-background-radius: 10; -fx-background-color: #E8B8CA; -fx-text-fill: white;");
    }

    void ButtonStyle3(Button button)
    {
        button.setStyle("-fx-background-radius: 10; -fx-background-color: #900C3F; -fx-text-fill: white;");
    }

    void ButtonStyleSearchButtonClicked(Button button)
    {
        button.setStyle(
                "-fx-background-color: #2980b9;" + /* Change background color when clicked */
                        "-fx-text-fill: #fff;" +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 5, 0, 0, 0);"
        );
    }


}