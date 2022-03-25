package com.codegym.repository.blog;

import com.codegym.model.Blog;
import com.codegym.repository.IGeneralRepository;

import java.util.List;

public interface IBlogRepository extends IGeneralRepository <Blog> {
    List <Blog> findByName (String name);
}
