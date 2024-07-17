package com.example.customer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class CustomerClientApplication extends Application {

    static Client client;
    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            this.stage = primaryStage;
            //stage.initStyle(StageStyle.UNDECORATED);
            showLoginPage();
            //showHomePage();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //setting connection
        String serverAddress = "127.0.0.1";
        int port = 44444;
        client = new Client(serverAddress, port);

        launch();
    }

    void showLoginPage() {
        try {
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

            // XML Loading using FXMLLoader
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("home.fxml"));
            Parent root = loader.load();


            // Loading and preparing the controller
            HomeController controller = loader.getController();
            controller.setMain(this);
            controller.setSocketWrapper(client.getSocketWrapper());
            System.out.println("trying to show home");
            controller.init();


            // Set the primary stage
            stage.setResizable(false);
            stage.setScene(new Scene(root, 975, 700));
            stage.show();

        } catch (Exception e) {

        }
    }

    void showSignUpPage()
    {
        try {
            // XML Loading using FXMLLoader
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("signupPagee.fxml"));
            Parent root = loader.load();

            // Loading and preparing the controller
            SignupPageeController controller = loader.getController();
            controller.setMain(this);
            controller.setSocketWrapper(client.getSocketWrapper());
            controller.init();

            // Set the primary stage
            stage.setResizable(false);
            stage.setScene(new Scene(root, 875, 625));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
