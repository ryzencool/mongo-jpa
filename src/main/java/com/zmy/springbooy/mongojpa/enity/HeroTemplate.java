package com.zmy.springbooy.mongojpa.enity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document
public class HeroTemplate {

    @Id
    private String id;
    /**
     * 英雄名称
     */
    private String heroName;

    /**
     * 英雄类型
     */
    private String heroType;

    /**
     * 英雄属国
     */
    private String heroPosition;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifiedTime;

    /**
     * 操作人
     */
    private String operator;


}
