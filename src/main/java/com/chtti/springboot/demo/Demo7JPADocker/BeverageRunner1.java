package com.chtti.springboot.demo.Demo7JPADocker;

import com.chtti.springboot.demo.Demo7JPADocker.bean.Beverage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class BeverageRunner1  implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeverageRunner1.class.getSimpleName());

    @Autowired
    MybeverageCRUDRepositry repository;

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("start to test beverage reposity");
        insertSomeData();
        loadSomeData();
        loadAllData();
        loadDataByPage();
        loadDataByLike();
        loadDataByContaining();
        deleteAndCheckCount();
    }

    private void loadDataByContaining() {
        LOGGER.info("find detail with milk");
        repository.findByDetailContaining("milk")
                .forEach(b -> LOGGER.info("find detail with milk:{}", b));
        LOGGER.info("find detail with milk");
        repository.findByDetailContaining("hot")
                .forEach(b -> LOGGER.info("find detail with hot:{}", b));
    }

    private void loadDataByLike() {
        LOGGER.info("find hot");
        repository.findByTitleLike("hot%")
                .forEach(beverage -> LOGGER.info("'hot prefix:'{}", beverage));
        repository.findByTitleLike("%latte")
                .forEach(beverage -> LOGGER.info("'end with latte:{}'", beverage));
    }

    private void loadDataByPage() {
        LOGGER.info("load data by page ");
        PageRequest pr1 =  PageRequest.of(0,4);
        repository.findAll(pr1).forEach(beverage -> {LOGGER.info("#{} page:{}",pr1.getPageNumber(),beverage);});
        Pageable pageable = pr1.next();
        repository.findAll(pageable).forEach(beverage -> {LOGGER.info("#{} page:{}",pageable.getPageNumber(),beverage);});
    }

    private  void loadSomeData(){
        LOGGER.info("find all americano only ");
        repository.findMatchByTitle("americano").forEach(beverage -> LOGGER.info("get a record ==> {}",beverage));
    }
    private  void loadAllData(){
        LOGGER.info("find all data");
        repository.findAll().forEach(beverage -> LOGGER.info("get a record ==> {}",beverage));
    }
    private  void deleteAndCheckCount(){
        LOGGER.info("before delete count={}",repository.count());
        repository.deleteAll();
        LOGGER.info("after delete count={}",repository.count());
    }
    private void insertSomeData(){
        repository.save(new Beverage("americano","ice black coffee with no milk"));
        repository.save(new Beverage("americano","hot black coffee with no milk"));
        repository.save(new Beverage("ice latte","espresso with 60% milk"));
        repository.save(new Beverage("hot latte","espresso with 60% milk"));
        repository.save(new Beverage("ice Assam black tea","normal black tea"));
        repository.save(new Beverage("hot Assam black tea","normal black tea"));
        repository.save(new Beverage("hot milk tea","black tea witch hot milk"));
        repository.save(new Beverage("ice milk tea","black tea witch ice milk"));
   }
}
