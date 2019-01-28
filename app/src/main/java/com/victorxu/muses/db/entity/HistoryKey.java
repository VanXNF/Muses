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
    private Long id;
    @Unique
    private String name;
    @NotNull
    private Date updateTime;
    @Generated(hash = 1876917312)
    public HistoryKey(Long id, String name, @NotNull Date updateTime) {
        this.id = id;
        this.name = name;
        this.updateTime = updateTime;
    }
    @Generated(hash = 668397952)
    public HistoryKey() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
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
   
}
