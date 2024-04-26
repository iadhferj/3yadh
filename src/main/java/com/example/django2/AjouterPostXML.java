package com.example.django2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Post;
import service.ServicePost;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AjouterPostXML implements Initializable {

    @FXML
    private Label titre;

    @FXML
    private Label cate;

    @FXML
    private Label des;
    @FXML
    private Label all;
    @FXML
    private ImageView imageP;

    @FXML
    private AnchorPane mainForm;
    @FXML
    private Button btAjouter;

    @FXML
    private TextField tfCategories;

    @FXML
    private TextArea tfDescription;

    @FXML
    private TextField tfTitre;

    @FXML
    private ImageView brandingImageView;

    private Image image;

    private final ServicePost sp = new ServicePost();
    @FXML
    private Button cancelButton;

    @FXML
    void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void AjouterPost(ActionEvent event) {
        if (tfTitre.getText().isEmpty() && tfDescription.getText().isEmpty() && tfCategories.getText().isEmpty()) {
            all.setText("You should fill all labels");
        } else {
            all.setText("");

            if (tfTitre.getText().isEmpty()) {
                titre.setText("You should fill the label");
            } else if (tfDescription.getText().isEmpty()) {
                des.setText("You should fill the label");
            } else if (tfCategories.getText().isEmpty()) {
                cate.setText("You should fill the label");

            } else if (data.path == null || data.path.isEmpty()) {
                showAlert("Erreur", "Veuillez sélectionner une image.");
                return;
            } else {
                String titre = tfTitre.getText();
                String description = tfDescription.getText();
                String categorie = tfCategories.getText();

                Post post = new Post(titre, description, data.path, categorie);
                try {
                    sp.insertOne(post);
                } catch (SQLException e) {
                    System.err.println("Error inserting post: " + e.getMessage());
                }
            }
        }
    }
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    public void imageIm(ActionEvent actionEvent) {

        Post p = new Post();
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File","*png","*jpg"));
        File file = openFile.showOpenDialog(mainForm.getScene().getWindow());
        if(file != null)
        {
            data.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 125,130,false , true);
            imageP.setImage(image);
        }
    }

    @FXML
    private void retourToAfficher(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherPost.fxml"));
            Parent root = loader.load();

            // Create a new scene
            Scene scene = new Scene(root);

            // Get the stage from the event
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Image brandingImage = new Image(getClass().getResource("/image/logo-removebg-preview.png").toString());
            brandingImageView.setImage(brandingImage);
        } catch (Exception e) {
            e.printStackTrace();
        }}
}
