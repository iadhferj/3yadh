package com.example.django2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import models.Post;
import service.ServicePost;

import java.sql.SQLException;
import java.util.List;

public class AfficherPostFront {

    @FXML
    private VBox postContainer;

    private final ServicePost servicePost = new ServicePost(); // Instantiate ServicePost

    public void initialize() {
        // Initialize the postContainer
        postContainer = new VBox();

        // Load posts when the controller is initialized
        loadPosts();
    }

    private void loadPosts() {
        try {
            // Retrieve posts from your database
            List<Post> posts = servicePost.selectAll();

            // Iterate through each post and create UI elements
            for (Post post : posts) {
                ImageView profilePic = new ImageView(new Image("file:///C:/Users/garal/IdeaProjects/Django2/src/main/resources/image/profile-removebg-preview.png"));
                profilePic.setFitWidth(50);
                profilePic.setFitHeight(50);
               String id= String.valueOf(post.getId());
                Label idlabel = new Label(id);
                Label titleLabel = new Label(post.getTitre());
                Label descriptionLabel = new Label(post.getDescription());

                // Add post elements to a new VBox
                VBox postNode = new VBox(profilePic, titleLabel, descriptionLabel);
                postNode.setStyle("-fx-background-color: #ffffff; -fx-padding: 10px;");
                postNode.setSpacing(5);

                // Add the post to the postContainer VBox
                postContainer.getChildren().add(postNode);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception appropriately
        }
    }
}
