package com.control.situation.utils.db;

import java.io.Serializable;

/**
 * 表字段信息
 *
 * Created by yhe.abcft on 2017/7/9 0009.
 */
public class TableFieldInfo  implements Serializable {

    private static final long serialVersionUID = 1L;

    // 字段名
    private String fieldName;
    // 字段注释
    private String fieldComment;
    // 字段类型
    private String fieldType;
    // 是否为主键
    private Boolean isPrimary;
    // 是否为空
    private Boolean isNull;

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldComment() {
        return fieldComment;
    }

    public String getFieldType() {
        return fieldType;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public Boolean getNull() {

        return isNull;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setFieldComment(String fieldComment) {
        this.fieldComment = fieldComment;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }

    public void setNull(Boolean aNull) {
        isNull = aNull;
    }
}
