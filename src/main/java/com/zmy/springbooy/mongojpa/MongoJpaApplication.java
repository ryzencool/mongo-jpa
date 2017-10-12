package com.zmy.springbooy.mongojpa;

import com.zmy.springbooy.mongojpa.enity.HeroTemplate;
import com.zmy.springbooy.mongojpa.repository.HeroTemplateRepository;
import com.zmy.springbooy.mongojpa.util.DateUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

import java.time.LocalDateTime;
import java.util.stream.Stream;

@SpringBootApplication
@EnableWebFlux
@EnableReactiveMongoRepositories
@EnableConfigurationProperties
public class MongoJpaApplication {

    @Bean
    CommandLineRunner startRun(HeroTemplateRepository heroTemplateRepository) {
        return (args) -> {
            String timeFormat = "yyMMdd HH:mm:ss";
            heroTemplateRepository.deleteAll().subscribe(null
                    , null
                    , () -> {
                        Stream.of(
                                new HeroTemplate("1", "陈宫", "谋士", "汉", DateUtil.formatDateTime(LocalDateTime.now(), timeFormat), DateUtil.formatDateTime(LocalDateTime.now(), timeFormat), "zmy"),
                                new HeroTemplate("2", "吕布", "武将", "汉", DateUtil.formatDateTime(LocalDateTime.now(), timeFormat), DateUtil.formatDateTime(LocalDateTime.now(), timeFormat), "zmy"),
                                new HeroTemplate("3", "关羽", "武将", "蜀", DateUtil.formatDateTime(LocalDateTime.now(), timeFormat), DateUtil.formatDateTime(LocalDateTime.now(), timeFormat), "zmy"),
                                new HeroTemplate("4", "黄忠", "武将", "蜀", DateUtil.formatDateTime(LocalDateTime.now(), timeFormat), DateUtil.formatDateTime(LocalDateTime.now(), timeFormat), "zmy"),
                                new HeroTemplate("5", "孙权", "君王", "吴", DateUtil.formatDateTime(LocalDateTime.now(), timeFormat), DateUtil.formatDateTime(LocalDateTime.now(), timeFormat), "zmy"),
                                new HeroTemplate("6", "丁奉", "武将", "吴", DateUtil.formatDateTime(LocalDateTime.now(), timeFormat), DateUtil.formatDateTime(LocalDateTime.now(), timeFormat), "zmy"),
                                new HeroTemplate("7", "陆逊", "谋士", "吴", DateUtil.formatDateTime(LocalDateTime.now(), timeFormat), DateUtil.formatDateTime(LocalDateTime.now(), timeFormat), "zmy"),
                                new HeroTemplate("8", "程昱", "谋士", "魏", DateUtil.formatDateTime(LocalDateTime.now(), timeFormat), DateUtil.formatDateTime(LocalDateTime.now(), timeFormat), "zmy"),
                                new HeroTemplate("9", "张辽", "武将", "魏", DateUtil.formatDateTime(LocalDateTime.now(), timeFormat), DateUtil.formatDateTime(LocalDateTime.now(), timeFormat), "zmy")
                        ).forEach(i -> {
                            heroTemplateRepository.save(i).subscribe(System.out::println);
                        });
                    });
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(MongoJpaApplication.class, args);
    }
}
