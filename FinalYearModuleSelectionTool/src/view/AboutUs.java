package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AboutUs {
    public AboutUs() {
        // create a new Stage for the About window
        Stage aboutStage = new Stage();
        aboutStage.setTitle("About Us");

        // create a new VBox to hold the content of the window
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        // create the contents of the window (e.g. labels, images, etc.)
        Label titleLabel = new Label("About Us");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label contentLabel = new Label("This program was developed by Ahmed Nasif Shahriar.\n"
                + "Student ID: P2719390\n\n" 
        		+ "This application allows to create student profile and \n"
        		+ "and then select module also reserved module\n"
        		+ "save the overview in the text file called Student.txt \n"
        		+ "also save the student details or student model as \n"
        		+ "binary form in text file called Studentsaved.txt and load them later \n "
        		);
               
               

        // add the contents to the VBox
        vbox.getChildren().addAll(titleLabel, contentLabel);

        // create a new Scene for the VBox
        Scene aboutScene = new Scene(vbox, 400, 300);

        // set the scene on the aboutStage and show it
        aboutStage.setScene(aboutScene);
        aboutStage.show();
    }
}
