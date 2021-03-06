package com.zmy.springbooy.mongojpa.controller;

import com.zmy.springbooy.mongojpa.enity.HeroResult;
import com.zmy.springbooy.mongojpa.enity.HeroTemplate;
import com.zmy.springbooy.mongojpa.repository.HeroTemplateRepository;
import com.zmy.springbooy.mongojpa.util.DateUtil;
import com.zmy.springbooy.mongojpa.util.TestConfigurationProperties;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@RestController
public class MottoController {

    private final HeroTemplateRepository heroTemplateRepository;
    private final TestConfigurationProperties testConfigurationProperties;

    private ReactiveMongoTemplate reactiveMongoTemplate;

    public MottoController(HeroTemplateRepository heroTemplateRepository, ReactiveMongoTemplate reactiveMongoTemplate, TestConfigurationProperties testConfigurationProperties) {
        this.heroTemplateRepository = heroTemplateRepository;
        this.reactiveMongoTemplate = reactiveMongoTemplate;
        this.testConfigurationProperties = testConfigurationProperties;
    }

    @GetMapping("/test")
    public Mono<String> test() {
        return Mono.just("java");
    }

    @GetMapping("/insert/{id}/{templateName}/{templateType}/{templatePosition}")
    public Mono<HeroTemplate> insert(@PathVariable("id") String id
            , @PathVariable("templateName") String templateName
            , @PathVariable("templateType") String templateType
            , @PathVariable("templatePosition") String templatePosition) {
        String timeFormat = "yyMMdd HH:mm:ss";
        HeroTemplate mottoTemplate = new HeroTemplate(id, templateName, templateType, templatePosition
                , DateUtil.formatDateTime(LocalDateTime.now(), timeFormat)
                , DateUtil.formatDateTime(LocalDateTime.now(), timeFormat)
                , "zmy");
        return heroTemplateRepository.save(mottoTemplate);
    }

    /**
     * 聚合操作,查询某个模板的
     */
    @GetMapping("/testAggregation")
    public Flux<Map<String, Object>> testAggregation() {
        Aggregation aggregation = newAggregation(
                group("heroType").count().as("count").first("heroType").as("name"),
                project("name", "count"),
                sort(Sort.Direction.DESC, "count"),
                match(Criteria.where("count").gt(0))
        );
        return reactiveMongoTemplate.aggregate(aggregation, "heroTemplate", HeroResult.class)
                .map(i -> {
                    Map<String, Object> tempMap = new HashMap<>();
                    tempMap.put("name", i.getName());
                    tempMap.put("count", i.getCount());
                    return tempMap;
                });
    }


    /**
     * 查所有属性种类
     *
     * @return
     */
    @GetMapping("/getType")
    public Flux<Map<String, Object>> getType() {
        Aggregation aggregation = newAggregation(
                group("heroType").first("heroType").as("type"),
                project("type")
        );


        return reactiveMongoTemplate.aggregate(aggregation, "heroTemplate", HeroResult.class)
                .map(i -> {
                    Map<String, Object> tempMap = new HashMap<>();
                    tempMap.put("type", i.getType());
                    return tempMap;
                });
    }

    @GetMapping("/queryAll")
    public Flux<HeroTemplate> queryAll() {
        return heroTemplateRepository.findAll();
    }

    @GetMapping("/queryByType/{templateType}")
    public Flux<HeroTemplate> queryByType(@PathVariable("templateType") String templateType) {
        return heroTemplateRepository.findByHeroType(templateType, PageRequest.of(0, 2));
    }

    @GetMapping("/queryByTime/{createTime}/{modifiedTime}")
    public Flux<HeroTemplate> queryByTime(@PathVariable("createTime") String createTime, @PathVariable("modifiedTime") String modifiedTime) {
        String fromTimeFormat = "yyMMddHHmmss";
        String toTimeFormat = "yyMMdd HH:mm:ss";
        LocalDateTime localDateTimeTempStart = DateUtil.parseDateTime(createTime, fromTimeFormat);
        LocalDateTime localDateTimeTempEnd = DateUtil.parseDateTime(modifiedTime, fromTimeFormat);
        createTime = DateUtil.formatDateTime(localDateTimeTempStart, toTimeFormat);
        modifiedTime = DateUtil.formatDateTime(localDateTimeTempEnd, toTimeFormat);
        return heroTemplateRepository.findByCreateTimeGreaterThanEqualAndModifiedTimeLessThanEqual(createTime, modifiedTime);
    }

    /**
     * 统计数量
     *
     * @param templateType
     * @return
     */
    @GetMapping("/countByType/{templateType}")
    public Mono<Integer> countByType(@PathVariable("templateType") String templateType) {
        return heroTemplateRepository.countByHeroType(templateType);
    }


    /**
     * 查找对象
     *
     * @param heroTemplate
     * @return
     */
    @GetMapping("/queryByTemplate")
    public Flux<HeroTemplate> queryTemplate(@RequestBody HeroTemplate heroTemplate) {

        return heroTemplateRepository.findAll(Example.of(heroTemplate));

    }

    /**
     * 传递对象的模糊查询
     *
     * @param heroTemplate
     * @return
     */
    @GetMapping("/queryByTemplateBlur")
    public Flux<HeroTemplate> queryTemplateBlur(@RequestBody HeroTemplate heroTemplate) {
        return heroTemplateRepository.findAll(
                Example.of(heroTemplate
                        , ExampleMatcher.matching()
                                .withMatcher("heroType", i -> i.contains())
                                .withMatcher("heroName", i -> i.startsWith())));

    }

    @PostMapping("/upload")
    public Mono<Void> upload(@RequestPart("file") FilePart filePart) {
        File file = new File("D:\\temp\\picture\\a.png");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        filePart.transferTo(file);
        return Mono.empty();
    }

//    @GetMapping(value = "/getHeroWithInterval", produces = "application/stream+json")
//    public Flux<Tuple2<HeroTemplate, Long>> getHeroTemplateInterval() {
//        Flux<HeroTemplateheroTemplateRepository.findAll();
//    }


}
