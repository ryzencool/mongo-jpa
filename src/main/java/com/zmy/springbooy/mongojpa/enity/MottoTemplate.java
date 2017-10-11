package com.zmy.springbooy.mongojpa.enity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MottoTemplate {

    @Id
    private String id;


    private String templateName;


    private String templateType;


    private String templatePosition;


    private String createTime;


    private String modifiedTime;

    private String operator;

    public MottoTemplate(String id, String templateName, String templateType, String templatePosition, String createTime, String modifiedTime, String operator) {
        this.id = id;
        this.templateName = templateName;
        this.templateType = templateType;
        this.templatePosition = templatePosition;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
        this.operator = operator;
    }

    public MottoTemplate() {
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getTemplatePosition() {
        return templatePosition;
    }

    public void setTemplatePosition(String templatePosition) {
        this.templatePosition = templatePosition;
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
        return "MottoTemplate{" +
                "id='" + id + '\'' +
                ", templateName='" + templateName + '\'' +
                ", templateType='" + templateType + '\'' +
                ", templatePosition='" + templatePosition + '\'' +
                ", createTime='" + createTime + '\'' +
                ", modifiedTime='" + modifiedTime + '\'' +
                ", operator='" + operator + '\'' +
                '}';
    }
}
