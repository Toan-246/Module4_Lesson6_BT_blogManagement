package com.codegym.repository.blog;

import com.codegym.model.Blog;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class BlogRepository implements IBlogRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Blog> findAll() {
        TypedQuery<Blog> query = entityManager.createQuery("SELECT b FROM Blog as b", Blog.class);
        return query.getResultList();
    }

    @Override
    public Blog findById(Long id) {
        TypedQuery<Blog> query = entityManager.createQuery("SELECT b FROM Blog as b WHERE b.id = :id", Blog.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Blog save(Blog blog) {
        if (blog.getId() == null) {
            entityManager.persist(blog);
        } else {
            entityManager.merge(blog);
        }
        return blog;
    }

    @Override
    public void remove(Long id) {
        Blog blog = findById(id);
        if (blog != null) {
            entityManager.remove(blog);
        }
    }

    @Override
    public List<Blog> findByName(String name) {
        TypedQuery<Blog>query = entityManager.createQuery("SELECT b FROM Blog as b WHERE b.tittle like ?1", Blog.class);
        query.setParameter(1, name);
        return query.getResultList();
    }
}
