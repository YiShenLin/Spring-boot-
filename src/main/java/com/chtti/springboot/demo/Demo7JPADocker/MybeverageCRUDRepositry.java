package com.chtti.springboot.demo.Demo7JPADocker;

import com.chtti.springboot.demo.Demo7JPADocker.bean.Beverage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MybeverageCRUDRepositry extends CrudRepository<Beverage,Long> {
    List<Beverage> findMatchByTitle(String title);
    Page<Beverage> findAll(Pageable pageable);
    List<Beverage> findByTitleLike(String title);
    List<Beverage> findByDetailContaining(String detail);
}
