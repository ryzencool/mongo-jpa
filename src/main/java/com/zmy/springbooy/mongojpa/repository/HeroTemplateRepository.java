package com.zmy.springbooy.mongojpa.repository;

import com.zmy.springbooy.mongojpa.enity.HeroTemplate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by ztex on 2017/10/9.
 */
@Repository
public interface HeroTemplateRepository extends ReactiveMongoRepository<HeroTemplate, String> {

    Flux<HeroTemplate> findByHeroName(String templateName);


    Flux<HeroTemplate> findByHeroType(String templateType, Pageable pageable);

    /**
     * 查询两段时间之间的记录
     * @param createTime
     * @param modifiedTime
     * @return
     */
    Flux<HeroTemplate> findByCreateTimeGreaterThanEqualAndModifiedTimeLessThanEqual(String createTime, String modifiedTime);

    Mono<Integer> countByHeroType(String templateType);

    /**
     * 模糊查询
     * @param templateName
     * @return
     */
    Flux<HeroTemplate> findByHeroNameContaining(String templateName);

}
