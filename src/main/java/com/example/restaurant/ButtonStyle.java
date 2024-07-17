package com.example.restaurant;

import javafx.scene.control.Button;

public class ButtonStyle {
    public static void ButtonStyle(Button button)
    {
        button.setStyle("-fx-background-radius: 10; -fx-background-color: #3498db; -fx-text-fill: white;");
    }
    public static void ButtonStyleExit(Button button)
    {
        button.setStyle(
                "-fx-background-color: #e74c3c; " + // Background color (red)
                        "-fx-text-fill: white; " + // Text color (white)
                        "-fx-font-size: 14px; " + // Font size
                        "-fx-padding: 7px 20px; " + // Padding (top/bottom 10px, left/right 20px)
                        "-fx-background-radius: 5px;" // Rounded corners
        );
    }
}
