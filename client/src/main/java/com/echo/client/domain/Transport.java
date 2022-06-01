package com.echo.client.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class Transport {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "uuid2"
    )
    private String Id;

    @Column(columnDefinition="varchar(2048)")
    private String content;
    private Instant createTime;
    private Instant modifyTime;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }


    public String getContent() {
        return content;
    }

    public void setContent(StringBuffer content) {
        this.content = content.toString();
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

    @Override
    public String toString() {
        return "Transport{" +
                "Id='" + Id + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
