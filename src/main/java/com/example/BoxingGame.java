package com.example;

// --- IMPORTS ---
// These bring in JavaFX tools (window, buttons, text, layout)
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BoxingGame extends Application {

    // --- GAME STATE VARIABLES ---
    // These are class variables → usable anywhere in this class
    int playerHP = 100;
    int cpuHP = 100;
    String message = "Fight!";

    @Override
    public void start(Stage stage) {

        // --- UI ELEMENTS ---
        // Label = displays text
        Label titleLabel = new Label("🥊 Player vs CPU Boxing Game");

        Label statusLabel = new Label(
            "Player HP: " + playerHP +
            "\nCPU HP: " + cpuHP +
            "\nPress Hit to start!"
        );

        // Buttons
        Button hitButton = new Button("Hit");
        Button resetButton = new Button("Reset");

        // --- LAYOUT ---
        // VBox stacks items vertically
        VBox layout = new VBox(15);

        // Add everything to layout (top → bottom)
        layout.getChildren().addAll(titleLabel, statusLabel, hitButton, resetButton);

        // --- HIT BUTTON LOGIC ---
        // setOnAction = runs when button is clicked
        hitButton.setOnAction(e -> {


          for (int i = 0; i < 1; i++) {
              //  loop req.
          }
            // Stop game if someone already lost
            if (playerHP <= 0 || cpuHP <= 0) {
                return; // exits method early
            }

            // --- PLAYER ATTACK ---
            if (Math.random() < 0.7) { // 70% chance to hit
                int damage = (int)(Math.random() * 11) + 10; // 10–20 damage
                cpuHP -= damage;
                message = "You hit CPU for " + damage;
            } else {
                message = "CPU dodged!";
            }

            // --- CPU ATTACK BACK ---
            if (cpuHP > 0) {
                if (Math.random() < 0.6) { // 60% chance to hit
                    int damage = (int)(Math.random() * 11) + 5; // 5–15 damage
                    playerHP -= damage;
                    message += "\nCPU hits you for " + damage;
                } else {
                    message += "\nYou dodged!";
                }
            }

            // --- PREVENT NEGATIVE HP ---
            if (playerHP < 0) playerHP = 0;
            if (cpuHP < 0) cpuHP = 0;

            // --- KO CHECK ---
            if (cpuHP <= 0) {
                message = "💥 CPU KO! You win!";
            } else if (playerHP <= 0) {
                message = "💀 You got knocked out!";
            }

            // --- UPDATE SCREEN ---
            statusLabel.setText(
                "Player HP: " + playerHP +
                "\nCPU HP: " + cpuHP +
                "\n\n" + message
            );
        });

        // --- RESET BUTTON ---
        resetButton.setOnAction(event -> {
            playerHP = 100;
            cpuHP = 100;
            message = "New Fight!";

            statusLabel.setText(
                "Player HP: " + playerHP +
                "\nCPU HP: " + cpuHP +
                "\n\n" + message
            );
        });

        // --- SCENE + STAGE ---
        // Scene = everything inside the window
        Scene scene = new Scene(layout, 350, 250);

        // Stage = the window itself
        stage.setTitle("Boxing Game");
        stage.setScene(scene);
        stage.show();
    }
}
