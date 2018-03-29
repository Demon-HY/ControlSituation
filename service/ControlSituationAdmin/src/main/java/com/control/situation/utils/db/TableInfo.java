package com.control.situation.utils.db;

import com.control.situation.utils.db.TableFieldInfo;

import java.io.Serializable;
import java.util.List;

/**
 * 数据库表信息
 *
 * Created by yhe.abcft on 2017/7/9 0009.
 */
public class TableInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tableName;
    private String tableComment;
    private List<TableFieldInfo> listFieldInfos;

    public String getTableName() {
        return tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public List<TableFieldInfo> getListFieldInfos() {
        return listFieldInfos;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public void setListFieldInfos(List<TableFieldInfo> listFieldInfos) {
        this.listFieldInfos = listFieldInfos;
    }
}
