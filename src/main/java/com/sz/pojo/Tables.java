package com.sz.pojo;

public class Tables {
    private Integer tableId;
    private String innerTable;
    private Integer categoryId;
    // 0 表示没有客人，1表示有了客人
    private Integer state;

    public Tables() {
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getInnerTable() {
        return innerTable;
    }

    public void setInnerTable(String innerTable) {
        this.innerTable = innerTable;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Tables{" +
                "tableId=" + tableId +
                ", innerTable='" + innerTable + '\'' +
                ", categoryId=" + categoryId +
                ", state=" + state +
                '}';
    }
}
