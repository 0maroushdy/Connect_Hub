/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contentmanagment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author moustafa
 */
public abstract class AContent {
    private final int contentId;
    private final IAuthor authorId;

    private String content;

    private LocalDateTime timeOfUpload;

    private static final DateTimeFormatter timeStampFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public String getTimestamp() {
        return this.timeOfUpload.format(AContent.timeStampFormat);
    }

    public String getContent() {
        return content;
    }

    public int getContentId() {
        return contentId;
    }

    public IAuthor getAuthorId() {
        return authorId;
    }

    public LocalDateTime getTimeOfUpload() {
        return timeOfUpload;
    }
    
    public void setTimeOfUpload(){
        this.timeOfUpload = LocalDateTime.now();
    }

    public AContent(IAuthor authorId) {
        this.authorId = authorId;
        this.contentId = ContentDataBase.getInstance().getUniqueId();
    }
    
    public abstract void uplode();
}
