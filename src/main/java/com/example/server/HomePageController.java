package com.example.server;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class HomePageController {
    public Button buttonExit;
    public Button buttonStartRestaurantServer;
    public Button buttonStartCustomerServer;
    public Button buttonAddRest;
    public AnchorPane statusShowingAnchorPane;
    public TextArea showText;
    public Button showStatusButtonId;
    private ServerApplication serverApplication;

    int countStatusButtonId = 0;
    //static TextArea textArea;

    public void setMain(ServerApplication serverApplication)
    {
        this.serverApplication = serverApplication;
    }
    public void init()
    {
        initialize(statusShowingAnchorPane,showText);
        statusShowingAnchorPane.setVisible(false);
    }

    //
    public void OnActionExitButton(ActionEvent actionEvent)
    {
        try
        {
            serverApplication.getServerManager().exit();
            System.exit(0);
        }
        catch (Exception e)
        {
            System.out.println("could not exit successfully");
        }
    }

    public void OnActionRestaurantButton(ActionEvent actionEvent)
    {
        serverApplication.StartRestaurantServer();
    }

    public void OnActionStartCustomerButton(ActionEvent actionEvent)
    {
        serverApplication.StartCustomerServer();
    }

    public void OnActionAddRest(ActionEvent actionEvent)
    {
        serverApplication.showAddRestPage();
    }

    public void OnActionShowStatus(ActionEvent actionEvent)
    {
        if(countStatusButtonId == 0)
        {
            System.out.println("showing status");
            statusShowingAnchorPane.setVisible(true);
            countStatusButtonId = 1;
            showStatusButtonId.setText("Hide status");
        }
        else if(countStatusButtonId == 1)
        {
            System.out.println("hiding status");
            statusShowingAnchorPane.setVisible(false);
            countStatusButtonId = 0;
            showStatusButtonId.setText("Show status");
        }
    }

    public void initialize(AnchorPane anchorPane,TextArea textArea) {
        textArea.setEditable(false);
        textArea.setStyle("-fx-control-inner-background: black; -fx-font-family: Monospace; -fx-text-fill: white;");

        // Create a ScrollPane and add the TextArea to it
        ScrollPane scrollPane = new ScrollPane(textArea);
        scrollPane.setStyle("-fx-background-color: black;");

        // Ensure the ScrollPane grows to fill the AnchorPane
        AnchorPane.setTopAnchor(scrollPane, 0.0);
        AnchorPane.setBottomAnchor(scrollPane, 0.0);
        AnchorPane.setLeftAnchor(scrollPane, 0.0);
        AnchorPane.setRightAnchor(scrollPane, 0.0);

        anchorPane.getChildren().add(scrollPane);
    }
    public void print(String text)
    {
        showText.appendText(text + "\n");
    }
}
