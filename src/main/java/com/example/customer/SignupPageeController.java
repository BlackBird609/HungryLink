package com.example.customer;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SignupPageeController {
    public TextField usernamelabel;
    public PasswordField passwordlabel;
    public PasswordField reenterpasslabel;
    public Label passworddontmatch;
    public Label usernametaken;
    public Button signupbuttonid;
    public Label finaltextfield;
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

    void init()
    {
        passworddontmatch.setVisible(false);
        usernametaken.setVisible(false);
        finaltextfield.setVisible(false);

    }

    public void OnActionUserNameTextField(MouseEvent event)
    {
        passworddontmatch.setVisible(false);
        usernametaken.setVisible(false);
    }

    public void OnActionPasswordTextField(MouseEvent event)
    {
        passworddontmatch.setVisible(false);
        usernametaken.setVisible(false);
    }

    public void OnActionReenterPass(MouseEvent event)
    {
        passworddontmatch.setVisible(false);
        usernametaken.setVisible(false);
    }

    public void OnActionSignupButton(ActionEvent actionEvent)
    {
        String username = usernamelabel.getText();
        String password = passwordlabel.getText();
        String reenterpass = reenterpasslabel.getText();
        if( (!username.equals(""))&&(!(password.equals("")))&& password.equals(reenterpass))
        {
            System.out.println("passwords match");
            try
            {
                socketWrapper.write("2");
                socketWrapper.write(username);
                socketWrapper.write(password);

                String received = (String) socketWrapper.read();
                if(received.toLowerCase().contains("taken"))
                {
                    System.out.println("got taken");
                    usernametaken.setVisible(true);
                }
                else if(received.toLowerCase().contains("success"))
                {
                    System.out.println("got true");

//                    usernametaken.setVisible(false);
//                    usernamelabel.setVisible(false);
//                    passwordlabel.setVisible(false);
//                    reenterpasslabel.setVisible(false);
//                    passworddontmatch.setVisible(false);
//                    signupbuttonid.setVisible(false);

                    finaltextfield.setVisible(true);

                }
            }
            catch (Exception e)
            {
                System.out.println(e);
            }

        }
        else
        {
            System.out.println("passwords do not match");
            passworddontmatch.setVisible(true);
        }

    }

    public void onActionBackButton(ActionEvent actionEvent)
    {
        System.out.println("inside onActionBackButton");
        customerClientApplication.showLoginPage();
    }
}
