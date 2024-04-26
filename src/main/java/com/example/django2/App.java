package com.example.django2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class App extends Application {
    @Override
    public  void start(Stage primaryStage) throws  Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("ajouterPost.fxml"));
       //Parent root = FXMLLoader.load(getClass().getResource("updatePost.fxml"));
     //Parent root = FXMLLoader.load(getClass().getResource("Front.fxml"));
     //  Parent root = FXMLLoader.load(getClass().getResource("ajouterCommnentaire.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("AfficherCommentaire.fxml"));
       // Parent root = FXMLLoader.load(getClass().getResource("updateCommentaire.fxml"));
      Parent root = FXMLLoader.load(getClass().getResource("afficherPost.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, 1000,600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}


