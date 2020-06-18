package com.chtti.springboot.demo.Demo7JPADocker;

import com.chtti.springboot.demo.Demo7JPADocker.bean.Beverage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BeverageRunner1  implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeverageRunner1.class.getSimpleName());

    @Autowired
    MybeverageCRUDRepositry repositry;

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("start to test beverage reposity");
        insertSomeData();
        loadAllData();
        deleteAndCheckCount();
    }
    private  void loadAllData(){
        repositry.findAll().forEach(beverage -> LOGGER.info("get a record ==> {}",beverage));
    }
    private  void deleteAndCheckCount(){
        LOGGER.info("before delete count={}",repositry.count());
        repositry.deleteAll();
        LOGGER.info("after delete count={}",repositry.count());
    }
    private void insertSomeData(){
        repositry.save(new Beverage("americano","black coffee with no milk"));
        repositry.save(new Beverage("latte","espresso with 60% milk"));
        repositry.save(new Beverage("Assam black tea","normal black tea"));
   }
}
