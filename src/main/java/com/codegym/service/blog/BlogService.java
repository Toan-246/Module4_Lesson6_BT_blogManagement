package com.codegym.service.blog;

import com.codegym.model.Blog;
import com.codegym.repository.blog.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService implements IBlogService{
    @Autowired
	IBlogRepository blogRepository;
    @Override
    public Iterable<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return blogRepository.findById(id);
    }

    @Override
    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public void remove(Long id) {
        blogRepository.deleteById(id);
    }

	@Override
	public Iterable<Blog> findAllByTittleContaining(String tittle) {
		tittle = "%" + tittle + "%";
		return blogRepository.findAllByTittleContaining(tittle);
	}

	@Override
	public Iterable<Blog> findAllByCategory_Id(Long id) {
		return blogRepository.findAllByCategory_Id(id);
	}
}
