package com.example.django2;

import models.Post;

import java.sql.SQLException;

public interface MyListener {
    public void onClickListener(Post post) ;
}
