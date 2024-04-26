package com.example.django2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.Post;
import models.Reaction;
import service.ServicePost;

import java.sql.SQLException;

public class itemPost {
    @FXML
    private Label idlabel;


    @FXML
    private HBox LikeContainer;

    @FXML
    private Label categorieLable;

    @FXML
    private HBox commentContainer;

    @FXML
    private Label desriptionLable;

    @FXML
    private ImageView img;

    @FXML
    private ImageView imgHate;

    @FXML
    private ImageView imgLike;

    @FXML
    private ImageView imgLove;

    @FXML
    private Label nbrcomment;

    @FXML
    private Label nbrreaction;

    @FXML
    private Label nbrshare;

    @FXML
    private ImageView profilepic;

    @FXML
    private HBox reactionContainer;

    @FXML
    private ImageView reactionimg;

    @FXML
    private Label reactionname;

    @FXML
    private HBox shareContainer;

    @FXML
    private Label titreLabel;
    private long startTime=0;
    private Reaction currentReaction;
    //private Post post=new Post();
    private long postId;

   // private MyListener myListener;


     ServicePost servicePost=new ServicePost();

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(post);
    }

    private Post post;
    private MyListener myListener;

    public void setData(Post post, MyListener myListener) {
        this.post = post;
        this.postId = post.getId();
        this.myListener = myListener;
        titreLabel.setText(post.getTitre());
        desriptionLable.setText(post.getDescription());
        categorieLable.setText(post.getCategorie());
        nbrreaction.setText(String.valueOf(post.getLike_count()));

        Image image = new Image(getClass().getResource("/image/logo-removebg-preview.png").toString());
        img.setImage(image);
    }


    @FXML
    public void clickreactionContainer(MouseEvent event) {
        startTime=System.currentTimeMillis();
    }



    @FXML
    public void clickreaction(MouseEvent event) {
        if(System.currentTimeMillis()- startTime > 500){
            reactionContainer.setVisible(true);
        }else {
            if (reactionContainer.isVisible()){reactionContainer.setVisible(false);}
            if (currentReaction==Reaction.NON){
                setReaction(Reaction.Like);
            }else { setReaction(Reaction.NON);}
        }
    }
    @FXML
    public void imgclickreaction(MouseEvent event) {
        switch (((ImageView) event.getSource()).getId()){
            case "imgLove" :
                setReaction(Reaction.LOVE);
                break;
            case "imgLike" :
                setReaction(Reaction.Like);
                break;
            case "imgHate" :
                setReaction(Reaction.HATE);
                break;
        }
        reactionContainer.setVisible(false);

    }




    public void setReaction(Reaction reaction) {
        // Update the currentReaction field
        currentReaction = reaction;

        Image image = new Image(getClass().getResourceAsStream(reaction.getImgSrc()));
        reactionimg.setImage(image);
        reactionname.setText(reaction.getName());

        // Update the like and dislike counts for the currentPost
        if (reaction == Reaction.Like || reaction == Reaction.LOVE) {
            post.setLike_count(post.getLike_count() + 1);
        } else if (reaction == Reaction.HATE) {
            post.setDislike_count(post.getDislike_count() + 1);
        }

        try {
            servicePost.updateLikeCount(postId, post); // Update the database
            System.out.println("Post updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to update post: " + e.getMessage());
        }

        // Update the number of reactions displayed
        nbrreaction.setText(String.valueOf(post.getLike_count()));
    }

}
