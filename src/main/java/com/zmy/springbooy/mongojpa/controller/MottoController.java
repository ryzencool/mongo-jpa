package com.zmy.springbooy.mongojpa.controller;

import com.zmy.springbooy.mongojpa.enity.MottoTemplate;
import com.zmy.springbooy.mongojpa.repository.MottoTemplateRepository;
import com.zmy.springbooy.mongojpa.util.DateUtil;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@RestController
public class MottoController {
    private final MottoTemplateRepository mottoTemplateRepository;

    public MottoController(MottoTemplateRepository mottoTemplateRepository) {
        this.mottoTemplateRepository = mottoTemplateRepository;
    }

    @GetMapping("/test")
    public Mono<String> test() {
        return Mono.just("java");
    }

    @GetMapping("/insert/{id}/{templateName}/{templateType}/{templatePosition}")
    public Mono<MottoTemplate> insert(@PathVariable("id") String id
            , @PathVariable("templateName") String templateName
            , @PathVariable("templateType") String templateType
            , @PathVariable("templatePosition") String templatePosition) {
        String timeFormat = "yyMMdd HH:mm:ss";
        MottoTemplate mottoTemplate = new MottoTemplate(id, templateName, templateType, templatePosition
                , DateUtil.formatDateTime(LocalDateTime.now(), timeFormat)
                , DateUtil.formatDateTime(LocalDateTime.now(), timeFormat)
                , "zmy");
        return mottoTemplateRepository.save(mottoTemplate);
    }

    @GetMapping("/queryAll")
    public Flux<MottoTemplate> queryAll() {
        return mottoTemplateRepository.findAll();
    }

    @GetMapping("/queryByType/{templateType}")
    public Flux<MottoTemplate> queryByType(@PathVariable("templateType") String templateType) {
        return mottoTemplateRepository.findByTemplateType(templateType, PageRequest.of(0, 2));
    }

    @GetMapping("/queryByTime/{createTime}/{modifiedTime}")
    public Flux<MottoTemplate> queryByTime(@PathVariable("createTime") String createTime, @PathVariable("modifiedTime") String modifiedTime) {
        String fromTimeFormat = "yyMMddHHmmss";
        String toTimeFormat = "yyMMdd HH:mm:ss";
        LocalDateTime localDateTimeTempStart = DateUtil.parseDateTime(createTime, fromTimeFormat);
        LocalDateTime localDateTimeTempEnd = DateUtil.parseDateTime(modifiedTime, fromTimeFormat);
        createTime = DateUtil.formatDateTime(localDateTimeTempStart, toTimeFormat);
        modifiedTime = DateUtil.formatDateTime(localDateTimeTempEnd, toTimeFormat);
        return mottoTemplateRepository.findByCreateTimeGreaterThanEqualAndModifiedTimeLessThanEqual(createTime, modifiedTime);
    }

    @GetMapping("/countByType/{templateType}")
    public Mono<Integer> countByType(@PathVariable("templateType") String templateType) {
        return mottoTemplateRepository.countByTemplateType(templateType);
    }

    @GetMapping("/queryByTemplate")
    public Flux<MottoTemplate> queryByTemplate() {
        MottoTemplate mottoTemplate = new MottoTemplate();
        mottoTemplate.setTemplateType("士");
//        return mottoTemplateRepository.findOne(Example.of(mottoTemplate));
        return mottoTemplateRepository.findAll(Example.of(mottoTemplate, ExampleMatcher.matching().withMatcher("createTime", i -> i.contains())) );
//        return mottoTemplateRepository.findByTemplateNameContaining("张");
//         mottoTemplateRepository.findMottoTemplate(mottoTemplate);
    }


}
