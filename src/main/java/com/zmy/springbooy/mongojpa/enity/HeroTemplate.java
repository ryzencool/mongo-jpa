package com.zmy.springbooy.mongojpa.enity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

    public HeroTemplate(String id, String heroName, String heroType, String heroPosition, String createTime, String modifiedTime, String operator) {
        this.id = id;
        this.heroName = heroName;
        this.heroType = heroType;
        this.heroPosition = heroPosition;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
        this.operator = operator;
    }

    public HeroTemplate() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getHeroType() {
        return heroType;
    }

    public void setHeroType(String heroType) {
        this.heroType = heroType;
    }

    public String getHeroPosition() {
        return heroPosition;
    }

    public void setHeroPosition(String heroPosition) {
        this.heroPosition = heroPosition;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "HeroTemplate{" +
                "id='" + id + '\'' +
                ", heroName='" + heroName + '\'' +
                ", heroType='" + heroType + '\'' +
                ", heroPosition='" + heroPosition + '\'' +
                ", createTime='" + createTime + '\'' +
                ", modifiedTime='" + modifiedTime + '\'' +
                ", operator='" + operator + '\'' +
                '}';
    }
}
