package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;



public class BlogForm {
    private Long id;
    private String tittle;
    private String content;
    private MultipartFile image;
	private Category category;

    public BlogForm() {
    }

	public BlogForm(Long id, String tittle, String content, MultipartFile image, Category category) {
		this.id = id;
		this.tittle = tittle;
		this.content = content;
		this.image = image;
		this.category = category;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}