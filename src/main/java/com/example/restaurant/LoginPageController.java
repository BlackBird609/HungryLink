package com.example.restaurant;

import com.example.restaurant.ReadAndWriteClass.ReadClass;
import com.example.restaurant.ReadAndWriteClass.WriteClass;
import com.example.restaurant.prev_v3.Food;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import com.example.restaurant.util.SocketWrapper;

public class LoginPageController {

    String restName;
    int restID;
    public TextField textFielUsernameId;
    public PasswordField passwordFieldId;
    public Button exitButtonId;
    public Label labelIncorrectCredential;
    public Button LoginButton;
    private SocketWrapper socketWrapper;
    private RestaurantClientApplication restaurantClientApplication;

    void setSocketWrapper(SocketWrapper s)
    {
        socketWrapper = s;
    }

    void setMain(RestaurantClientApplication restaurantClientApplication)
    {
        this.restaurantClientApplication = restaurantClientApplication;
    }

    public void init()
    {
        labelIncorrectCredential.setVisible(false);
        ButtonStyle(LoginButton);
        ButtonStyleExit(exitButtonId);
    }

    public void OnActionLoginButton(ActionEvent actionEvent)
    {
        String restId = textFielUsernameId.getText();
        String password = passwordFieldId.getText();

        try
        {
            System.out.println("restaurant id now will be converted to int form string");
            int id = Integer.valueOf(restId);

            try
            {
                socketWrapper.write("1");
                socketWrapper.write(id);
                socketWrapper.write(password);

                String received = (String) socketWrapper.read();
                if(received.toLowerCase().contains("t"))
                {
                    restID = id;
                    restaurantClientApplication.setRestId(restID);
                    System.out.println("got true");

                    restaurantClientApplication.setFoods(ReadClass.readListOfFood(socketWrapper));

                    restName = (String)socketWrapper.read();
                    System.out.println("my name is "+restName);
                    restaurantClientApplication.setRestName(restName);

                    System.out.println("printing the list of foods of this rest");

                    for(Food i:restaurantClientApplication.getFoods())
                    {
                        i.printFood();
                    }

                    restaurantClientApplication.getClient().getReadThread().getThread().start();
                    restaurantClientApplication.showHomePage();
                }
                else if(received.toLowerCase().contains("f"))
                {
                    System.out.println("got false");
                    labelIncorrectCredential.setVisible(true);
                }
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
        catch (Exception e)
        {
            System.out.println("Please enter an integer number");
        }

    }

    public void OnActionExitButton(ActionEvent actionEvent)
    {
        System.exit(0);
    }

    public void OnActionTextFieldUsername(MouseEvent mouseEvent)
    {
        //System.out.println("OnActionTextFieldUsername");
        labelIncorrectCredential.setVisible(false);
    }

    void ButtonStyle(Button button)
    {
        button.setStyle("-fx-background-radius: 10; -fx-background-color: #3498db; -fx-text-fill: white;");
    }
    void ButtonStyleExit(Button button)
    {
        button.setStyle(
                "-fx-background-color: #e74c3c; " + // Background color (red)
                        "-fx-text-fill: white; " + // Text color (white)
                        "-fx-font-size: 14px; " + // Font size
                        "-fx-padding: 5px 20px; " + // Padding (top/bottom 10px, left/right 20px)
                        "-fx-background-radius: 5px;" // Rounded corners
        );
    }
}
