package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.blog.IBlogService;
import com.codegym.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private IBlogService blogService;
	@GetMapping("/categories")
	public ResponseEntity<Iterable<Category>> findAllCategory (){
		List<Category> categories = (List<Category>) categoryService.findAll();
		if (categories.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	@GetMapping("/categories/{id}")
	public ResponseEntity<Iterable<Blog>>findAllBlogByCategoryId (@PathVariable Long id){
		List<Blog> categories = (List<Blog>)blogService.findAllByCategory_Id(id);
		if (categories.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
}
