package com.victorxu.muses.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;



import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class HistoryKey {

    @Id(autoincrement = true)
    private long id;
    private String name;
    @NotNull
    private Date updateTime;
    @NotNull
    private long userId;
    @Generated(hash = 341664257)
    public HistoryKey(long id, String name, @NotNull Date updateTime, long userId) {
        this.id = id;
        this.name = name;
        this.updateTime = updateTime;
        this.userId = userId;
    }
    @Generated(hash = 668397952)
    public HistoryKey() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getUpdateTime() {
        return this.updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public long getUserId() {
        return this.userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }

}
