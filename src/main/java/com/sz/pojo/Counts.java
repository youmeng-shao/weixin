package com.sz.pojo;

import java.io.Serializable;

public class Counts implements Serializable{
    private Integer num;
    private Integer countId;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getCountId() {
        return countId;
    }

    public void setCountId(Integer countId) {
        this.countId = countId;
    }

    @Override
    public String toString() {
        return "Counts{" +
                "num=" + num +
                ", countId=" + countId +
                '}';
    }
}
