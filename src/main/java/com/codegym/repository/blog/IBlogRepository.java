package com.codegym.repository.blog;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBlogRepository extends PagingAndSortingRepository<Blog, Long> {
	public Iterable<Blog> findAllByTittleContaining(String tittle);
	Iterable<Blog>findAllByCategory_Id(Long id);
}
