package com.example.restaurant;

import com.example.restaurant.prev_v3.Food;
import com.example.restaurant.util.SocketWrapper;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;

public class FoodSectionController {

    public AnchorPane FoodListShowingAnchorPane;
    public RadioButton buttonShowList;
    public Button buttonAddFood;
    public Button buttonHome;
    public AnchorPane anchorPaneAddFood;
    public StackPane foodListShowingStackPane;
    //managing attributes
    private SocketWrapper socketWrapper;
    private RestaurantClientApplication restaurantClientApplication;
    private List<Food> foods;

    //ui components id
    int showFood = 0;


    //initial methods to get values for the fields
    void setSocketWrapper(SocketWrapper s)
    {
        socketWrapper = s;
    }

    void setMain(RestaurantClientApplication restaurantClientApplication)
    {
        this.restaurantClientApplication = restaurantClientApplication;
    }

    void setFoods(List<Food> foods)
    {
        this.foods = foods;
    }

    void init() throws IOException, ClassNotFoundException
    {
        //showListViewForFoods(foods);
        System.out.println("printing the foods of the rest");
        for(Food i:foods)
        {
            i.printFood();
        }
        //showListViewForFoods(foods);
        OnACtionShowList1();
        foodListShowingStackPane.setVisible(false);
    }

    void showFoodViewPage()
    {
        try
        {

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    void showTableViewForFoods(List<Food> foods)
    {

    }

    void showFoodDetails(Food food)
    {

    }

    public void OnActionShowList(ActionEvent actionEvent)
    {
        if(showFood == 0)
        {
            System.out.println("this click will make it show");
            foodListShowingStackPane.setVisible(true);
            //OnACtionShowList1();
            showFood = 1;
        }
        else if(showFood == 1)
        {
            System.out.println("this click will make it disappear");
            //OnActionShowList2();
            foodListShowingStackPane.setVisible(false);
            showFood = 0;
        }
    }

    public void OnACtionShowList1()
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
                            //Image image = new Image("C:\\Users\\mdsha\\OneDrive - BUET\\Documents\\KonohaFoodService\\server\\src\\main\\resources\\com\\example\\server\\pictures\\"+"food.jpeg");

                            String imagePath = "/com/example/restaurant/pictures/food.jpeg";
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
                            //Button OrdeFood = new Button("Order");
                            //subCell2.getChildren().add(OrdeFood);

//                            OrdeFood.setOnAction((ActionEvent event) ->
//                            {
//                                try
//                                {
//                                    System.out.println("Ordering food: ");
//                                    String foodName = getItem().getName();
//                                    String foodId = String.valueOf(getItem().getRestId());
//                                    System.out.println(foodId);
//                                    System.out.println(foodName);
//                                }
//                                catch (Exception e)
//                                {
//
//                                }
//                            });

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
        foodListShowingStackPane.getChildren().add(listView);

    }
    public void OnActionShowList2()
    {
        FoodListShowingAnchorPane.getChildren().clear();
    }

    public void OnActionAddFood(ActionEvent actionEvent)
    {
        //

        VBox vBox = new VBox();


        Label errorLabel = new Label("Cannot add,a food already exists with same in the same category!");

        Label doneLabel = new Label("Food added successfully!");

        vBox.getChildren().add(errorLabel);

        Label nameLabel = new Label("Food Name:");
        TextField nameTextField = new TextField();

        nameTextField.setOnMouseClicked(event -> {
            errorLabel.setVisible(false);
        });

        Label categoryLabel = new Label("Category:");
        TextField categoryTextField = new TextField();

        categoryTextField.setOnMouseClicked(event -> {
            errorLabel.setVisible(false);
        });


        Label priceLabel = new Label("Price:");
        TextField priceTextField = new TextField();

        priceTextField.setOnMouseClicked(event -> {
            errorLabel.setVisible(false);
        });

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            // Retrieve input values
            String foodName = nameTextField.getText();
            String category = categoryTextField.getText();
            double price = Double.parseDouble(priceTextField.getText());

            String back = restaurantClientApplication.addMenu(category,foodName,price);
            if(back.contains("no"))
            {
                errorLabel.setVisible(true);
            }
            else
            {
                doneLabel.setVisible(true);
                anchorPaneAddFood.getChildren().clear();
            }

        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> anchorPaneAddFood.getChildren().clear());

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(addButton, cancelButton);
        vBox.getChildren().addAll(nameLabel,nameTextField,categoryLabel,categoryTextField,priceLabel,priceTextField,buttonBox);

        errorLabel.setVisible(false);
        doneLabel.setVisible(false);
        //vBox.getChildren().addAll(doneLabel,errorLabel);
        anchorPaneAddFood.getChildren().add(vBox);
    }

    public void OnACtionHomeButton(ActionEvent actionEvent)
    {
        //main.ReturnToHomePage();
        restaurantClientApplication.showHomePage();
    }
}
