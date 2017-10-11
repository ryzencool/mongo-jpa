package com.zmy.springbooy.mongojpa.controller;

import com.zmy.springbooy.mongojpa.enity.HeroTemplate;
import com.zmy.springbooy.mongojpa.repository.HeroTemplateRepository;
import com.zmy.springbooy.mongojpa.util.DateUtil;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
public class MottoController {
    private final HeroTemplateRepository heroTemplateRepository;

    public MottoController(HeroTemplateRepository heroTemplateRepository) {
        this.heroTemplateRepository = heroTemplateRepository;
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
     * @param templateType
     * @return
     */
    @GetMapping("/countByType/{templateType}")
    public Mono<Integer> countByType(@PathVariable("templateType") String templateType) {
        return heroTemplateRepository.countByHeroType(templateType);
    }

    /**
     * 模糊查询
     * @return
     */
    @GetMapping("/queryByTemplate")
    public Flux<HeroTemplate> queryByTemplate() {
        HeroTemplate heroTemplate = new HeroTemplate();
        heroTemplate.setHeroType("士");
        return heroTemplateRepository.findAll(Example.of(heroTemplate, ExampleMatcher.matching().withMatcher("createTime", i -> i.contains())) );
    }
}
