/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contentmanagment;

import java.time.LocalDateTime;

/**
 *
 * @author moustafa
 */
public class Post extends AContent {

    public Post(IAuthor authorId) {
        super(authorId);
    }

    @Override
    public void uplode() {
        this.setTimeOfUpload();
        ContentDataBase.getInstance().addContent(this);
    }

}
