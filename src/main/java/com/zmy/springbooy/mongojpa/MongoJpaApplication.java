package com.zmy.springbooy.mongojpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
@EnableReactiveMongoRepositories
public class MongoJpaApplication {

//    private MottoTemplateRepository mottoTemplateRepository;
//
//    public MongoJpaApplication(MottoTemplateRepository mottoTemplateRepository) {
//        this.mottoTemplateRepository = mottoTemplateRepository;
//    }

//    @Bean
//    CommandLineRunner startRun() {
//        return (args) -> {
//            String timeFormat = "yyMMdd HH:mm:ss";
//            mottoTemplateRepository.deleteAll();
//            MottoTemplate mottoTemplate = new MottoTemplate("1", "陈宫", "谋士", "汉", DateUtil.formatDateTime(LocalDateTime.now(), timeFormat), DateUtil.formatDateTime(LocalDateTime.now(), timeFormat), "zmy");
//            mottoTemplateRepository.save(mottoTemplate);
//        };
//    }

    public static void main(String[] args) {
        SpringApplication.run(MongoJpaApplication.class, args);
    }
}
