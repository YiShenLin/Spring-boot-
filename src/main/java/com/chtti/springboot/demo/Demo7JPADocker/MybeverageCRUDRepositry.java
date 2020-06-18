package com.chtti.springboot.demo.Demo7JPADocker;

import com.chtti.springboot.demo.Demo7JPADocker.bean.Beverage;
import org.springframework.data.repository.CrudRepository;

public interface MybeverageCRUDRepositry extends CrudRepository<Beverage,Long> {
}
