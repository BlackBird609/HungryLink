package com.example.server;

import com.example.server.prev_v3.Restaurant;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.SocketWrapper;

import java.net.ServerSocket;
import java.net.Socket;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;


public class ServerApplication extends Application
{
    Stage stage;
    static ServerManager serverManager;
    HomePageController homePageController;

    public static void main(String[] args)
    {
        serverManager = new ServerManager();
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        this.stage = stage;
        showHomePage();

    }


    public void showLoginPage() {
        try {
            // XML Loading using FXMLLoader
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("loginPage.fxml"));
            Parent root = loader.load();

            // Loading and preparing the controller
            LoginPageController controller = loader.getController();
            controller.setMain(this);
            controller.init();

            // Set the primary stage
            stage.setResizable(false);
            stage.setScene(new Scene(root, 875, 625));
            stage.show();
        } catch (Exception e) {

        }
    }

    public void showHomePage()
    {
        try {
            // XML Loading using FXMLLoader
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("homePage.fxml"));
            Parent root = loader.load();

            // Loading and preparing the controller
            HomePageController controller = loader.getController();
            controller.setMain(this);
            controller.init();

            homePageController = controller;

            // Set the primary stage
            stage.setResizable(false);
            stage.setScene(new Scene(root, 875, 625));
            stage.show();
        } catch (Exception e) {

        }
    }
    public void showAddRestPage()
    {
        try {
            // XML Loading using FXMLLoader
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("addRest.fxml"));
            Parent root = loader.load();

            // Loading and preparing the controller
            AddRestController controller = loader.getController();
            controller.setMain(this);
            controller.init();

            // Set the primary stage
            stage.setResizable(false);
            stage.setScene(new Scene(root, 875, 625));
            stage.show();
        } catch (Exception e) {

        }
    }

    void StartCustomerServer()
    {
        System.out.println("Starting customer server");

        homePageController.print("starting customer server");
        new ServerForCustomer(44444,serverManager,homePageController);
    }

    void StartRestaurantServer()
    {
        System.out.println("starting restaurant server");

        homePageController.print("starting restaurant server");

        new ServerForRest(55555,serverManager,homePageController);
    }

    ServerManager getServerManager()
    {
        return serverManager;
    }
}
