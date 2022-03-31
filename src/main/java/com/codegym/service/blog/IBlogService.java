package com.codegym.service.blog;

import com.codegym.model.Blog;
import com.codegym.service.IGeneralService;


public interface IBlogService extends IGeneralService<Blog> {
	public Iterable<Blog> findAllByTittleContaining(String tittle);
	Iterable<Blog>findAllByCategory_Id(Long id);
}
