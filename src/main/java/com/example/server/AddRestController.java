package com.example.server;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class AddRestController {

    public TextField id;
    public TextField name;
    public TextField price;
    public TextField zipCode;
    public TextField score;
    public TextField category1;
    public TextField category2;
    public TextField category3;
    public AnchorPane statusShowingAnchor;
    private ServerApplication serverApplication;


    public void setMain(ServerApplication serverApplication)
    {
        this.serverApplication = serverApplication;
    }

    public void init()
    {

    }

    public void OnACtionBackButton(ActionEvent actionEvent)
    {
        serverApplication.showHomePage();
    }

    public void OnACtionSubmitButton(ActionEvent actionEvent)
    {
        try
        {
            int Id =  Integer.valueOf(id.getText());

            String Name = name.getText();

            Pattern pattern1 = Pattern.compile("[a-zA-Z]");
            Matcher matcher = pattern1.matcher(Name);
            if(!matcher.find())
            {
                System.out.println("Enter a valid name");
                Label label = new Label("Enter a valid name");
                label.setStyle("-fx-font-size: 14px; -fx-text-fill: red; -fx-font-weight: bold;");

                statusShowingAnchor.getChildren().add(label);

                return;
            }



            String Price = price.getText();
            System.out.println("restaurant price is "+Price);

            if(!(Price.equals("$") || Price.equals("$$") || Price.equals("$$$")))
            {
                System.out.println("Restaurant price can only be $/$$/$$");
                Label label = new Label("Restaurant price can only be $/$$/$$");
                label.setStyle("-fx-font-size: 14px; -fx-text-fill: red; -fx-font-weight: bold;");

                statusShowingAnchor.getChildren().add(label);

                return;
            }
            String Zipcode = zipCode.getText();
            try
            {
                double Score = Double.valueOf(score.getText());

                String cat1 = category1.getText();
                String cat2 = category2.getText();
                String cat3 = category3.getText();

                Pattern pattern = Pattern.compile("[a-zA-Z]");

                Matcher matcher1 = pattern.matcher(cat1);
                Matcher matcher2 = pattern.matcher(cat2);
                Matcher matcher3 = pattern.matcher(cat3);

                String s;

                if (matcher1.find())
                {
                    System.out.println("category 1 is valid");
                    if(matcher2.find())
                    {
                        System.out.println("cat 2 is valid");
                        if(matcher3.find())
                        {
                            System.out.println("cat3 is valid");
                            s = cat1 + "," + cat2 + "," + cat3;
                        }
                        else
                        {
                            s = cat1 + "," + cat2;
                        }
                    }
                    else
                    {
                        s = cat1;
                    }

                    String arr[] = s.split(",",-1);
                    System.out.println("categories are:");
                    for(String s1:arr)
                    {
                        System.out.println(s1);
                    }

                    String receive = serverApplication.getServerManager().addRestaurant(Id,Name,Score,Price,Zipcode,arr);
                    if(receive.contains("id"))
                    {
                        System.out.println("Restaurant exists with this id");
                        Label label = new Label("Restaurant exists with this id");
                        label.setStyle("-fx-font-size: 14px; -fx-text-fill: red; -fx-font-weight: bold;");

                        statusShowingAnchor.getChildren().add(label);
                    }
                    else if(receive.contains("name"))
                    {
                        System.out.println("Restaurant exists with this name");
                        Label label = new Label("Restaurant exists with this name");
                        label.setStyle("-fx-font-size: 14px; -fx-text-fill: red; -fx-font-weight: bold;");

                        statusShowingAnchor.getChildren().add(label);
                    }
                    else if(receive.contains("success"))
                    {
                        System.out.println("successful registration");
                        Label label = new Label("Successful registration");
                        label.setStyle("-fx-font-size: 14px; -fx-text-fill: green; -fx-font-weight: bold;");

                        statusShowingAnchor.getChildren().add(label);


                        id.clear();
                        name.clear();
                        price.clear();
                        score.clear();
                        category1.clear();
                        category2.clear();
                        category3.clear();
                        zipCode.clear();
                    }
                }
                else
                {
                    System.out.println("no valid cat");
                }
            }
            catch(Exception e)
            {
                System.out.println("Enter an double number in the score field");

                Label label = new Label("Enter an double number in the score field");
                label.setStyle("-fx-font-size: 14px; -fx-text-fill: red; -fx-font-weight: bold;");

                statusShowingAnchor.getChildren().add(label);
            }
        }
        catch (Exception e)
        {
            System.out.println("Enter an Integer in rest id field");

            Label label = new Label("Enter an Integer in rest id field");
            label.setStyle("-fx-font-size: 14px; -fx-text-fill: red; -fx-font-weight: bold;");

            statusShowingAnchor.getChildren().add(label);
        }

    }


    public void OnACtionID(MouseEvent mouseEvent)
    {
        statusShowingAnchor.getChildren().clear();
    }

    public void OnACtionCancelButton(ActionEvent actionEvent)
    {
        id.clear();
        name.clear();
        price.clear();
        score.clear();
        category1.clear();
        category2.clear();
        category3.clear();
        zipCode.clear();

        statusShowingAnchor.getChildren().clear();
    }
}
