package com.example.customer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginPageController {
    public TextField textFielUsernameId;
    public PasswordField passwordFieldId;
    public Button signupbuttonid;
    public Button exitButtonId;
    public Label labelIncorrectCredential;
    public Button LoginButton;
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

    public void init()
    {
        labelIncorrectCredential.setVisible(false);
        ButtonStyleExit(exitButtonId);
        ButtonStyle(signupbuttonid);
        ButtonStyle(LoginButton);
    }

    public void OnActionLoginButton(ActionEvent actionEvent)
    {
        String username = textFielUsernameId.getText();
        String password = passwordFieldId.getText();
        try
        {
            socketWrapper.write("1");
            socketWrapper.write(username);
            socketWrapper.write(password);

            String received = (String) socketWrapper.read();
            if(received.toLowerCase().contains("t"))
            {
                System.out.println("got true");
                customerClientApplication.showHomePage();
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

    public void OnActionSignUpButton(ActionEvent actionEvent)
    {
        System.out.println("going for signup");
        customerClientApplication.showSignUpPage();
    }

    public void OnActionExitButton(ActionEvent actionEvent)
    {
        Platform.exit();
        System.out.println("after exiting");
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
