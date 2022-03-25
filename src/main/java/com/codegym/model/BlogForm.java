package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

public class BlogForm {
    private Long id;
    private String tittle;
    private String content;
    private MultipartFile image;

    public BlogForm() {
    }

    public BlogForm(String tittle, String content, MultipartFile image) {
        this.tittle = tittle;
        this.content = content;
        this.image = image;
    }

    public BlogForm(Long id, String tittle, String content, MultipartFile image) {
        this.id = id;
        this.tittle = tittle;
        this.content = content;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}