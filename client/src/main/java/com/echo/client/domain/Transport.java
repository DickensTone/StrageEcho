package com.echo.client.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class Transport {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String Id;

    private StringBuffer content;
    private Instant createTime;
    private Instant modifyTime;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public StringBuffer getContent() {
        return content;
    }

    public void setContent(StringBuffer content) {
        this.content = content;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Instant modifyTime) {
        this.modifyTime = modifyTime;
    }
}
