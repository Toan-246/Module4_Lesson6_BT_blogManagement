package com.codegym.service.blog;

import com.codegym.model.Blog;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface IBlogService extends IGeneralService<Blog> {
    List<Blog> findByName (String name);
}
