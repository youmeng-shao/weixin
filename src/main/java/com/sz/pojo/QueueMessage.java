package com.sz.pojo;

public class QueueMessage {

    private String description;
    private String queueInnerTale;
    private Integer tableId;

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQueueInnerTale() {
        return queueInnerTale;
    }

    public void setQueueInnerTale(String queueInnerTale) {
        this.queueInnerTale = queueInnerTale;
    }

    @Override
    public String toString() {
        return "QueueMessage{" +
                "description='" + description + '\'' +
                ", queueInnerTale='" + queueInnerTale + '\'' +
                ", tableId=" + tableId +
                '}';
    }
}
