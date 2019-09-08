package com.sz.pojo;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private String openId;
    private Integer tableId;
    private Double totalPrice;
    // 多对多映射，一个用户可以点多道菜
    private List<Menu> menus;
    private List<Counts> count;

    public List<Counts> getCount() {
        return count;
    }

    public void setCount(List<Counts> count) {
        this.count = count;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "User{" +
                "openId='" + openId + '\'' +
                ", tableId=" + tableId +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
