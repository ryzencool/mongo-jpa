package com.zmy.springbooy.mongojpa.repository;

import com.zmy.springbooy.mongojpa.enity.MottoTemplate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by ztex on 2017/10/9.
 */
@Repository
public interface MottoTemplateRepository extends ReactiveMongoRepository<MottoTemplate, String> {

    Flux<MottoTemplate> findByTemplateName(String templateName);

    Flux<MottoTemplate> findByTemplateType(String templateType, Pageable pageable);

    Flux<MottoTemplate> findByCreateTimeGreaterThanEqualAndModifiedTimeLessThanEqual(String createTime, String modifiedTime);

    Mono<Integer> countByTemplateType(String templateType);

    Flux<MottoTemplate> findByTemplateNameContaining(String templateName);


}
