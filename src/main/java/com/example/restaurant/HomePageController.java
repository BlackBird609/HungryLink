package com.example.restaurant;

import com.example.restaurant.prev_v3.Food;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.skin.TableHeaderRow;
import javafx.scene.control.skin.TableViewSkin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.restaurant.util.SocketWrapper;

public class HomePageController {

    public HBox UpperHboxId;
    public StackPane showreslutOnstackPaneid;
    public Button buttonFoodSection;
    public Button ButtonSettings;
    public Button Quit;
    public ImageView exitButton;
    private SocketWrapper socketWrapper;
    private RestaurantClientApplication restaurantClientApplication;

    private List<Food> foods;

    private List<String> foodOrders;

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

    void setFoodOrders(List<String> foodOrders)
    {
        this.foodOrders = foodOrders;
    }

    void init() throws IOException, ClassNotFoundException
    {
        //showListViewForFoods(foods);
        System.out.println("printing the foods of the rest");

        for(Food i:foods)
        {
            i.printFood();
        }

        ButtonStyle1(Quit);
        ButtonStyle2(buttonFoodSection);
        ButtonStyle2(ButtonSettings);
    }

    //showing orders
    public void showOrders(List<String> orders)
    {

        List<Order> orderList = getListOfOrders(orders);
        System.out.println("now we will show");
        for(Order o:orderList)
        {
            System.out.println(o.getFoodName()+"  "+o.getCustomerName());
        }

        TableView<Order> tableView = new TableView<>();

        TableColumn<Order, String> foodColumn = new TableColumn<>("Food");
        TableColumn<Order, String> customerColumn = new TableColumn<>("Customer Name");

        foodColumn.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        foodColumn.setSortable(false);
        customerColumn.setSortable(false);

        tableView.setRowFactory(tv -> {
            return new TableRow<Order>() {
                @Override
                protected void updateItem(Order item, boolean empty) {
                    super.updateItem(item, empty);
                    if (getIndex() % 2 == 0) {
                        setStyle("-fx-background-color: white; -fx-text-fill: black;");
                    } else {
                        setStyle("-fx-background-color: #900C3F; -fx-text-fill: white;");
                    }
                }
            };
        });


        tableView.getColumns().addAll(foodColumn, customerColumn);
        tableView.getItems().addAll(orderList);

        // Make columns editable (optional)
        foodColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        customerColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        showreslutOnstackPaneid.getChildren().add(tableView);

    }

    public void onActionSwitchButton(ActionEvent actionEvent)
    {
        restaurantClientApplication.showFoodSectionPage();
    }

    private List<Order> getListOfOrders(List<String> strings)
    {
        List<Order> orders = new ArrayList<>();
        for(String i:strings)
        {
            orders.add(StringToOrder(i));
        }
        return orders;
    }
    private Order StringToOrder(String s)
    {
        String arr[] = s.split(",",-1);
        return new Order(arr[0],arr[1]);
    }

    public static void ButtonStyle1(Button button)
    {
        button.setStyle("-fx-background-radius: 10; -fx-background-color: #AB3ACC; -fx-text-fill: white;");
    }


    public static void ButtonStyle2(Button button)
    {
        button.setStyle("-fx-background-radius: 10; -fx-background-color: #900C3F; -fx-text-fill: white;");
    }

    public void OnActionExitButton(ActionEvent actionEvent)
    {
        System.exit(0);
    }

    public void OnACtionLogOut(ActionEvent actionEvent)
    {
        try
        {
            socketWrapper.write("2");
            restaurantClientApplication.disconnectFromServer();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        restaurantClientApplication.showLoginPage();
    }
}
