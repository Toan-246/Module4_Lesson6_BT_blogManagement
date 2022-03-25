package com.codegym.repository;

import java.util.List;

public interface IGeneralRepository<T> {
    List<T> findAll();

    T findById(Long id);

    T save (T t);

    void remove (Long id);

}
