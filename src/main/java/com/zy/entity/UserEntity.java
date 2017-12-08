package com.zy.entity;



import com.sun.istack.internal.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;


@Entity(name="user")
public class UserEntity {

    @Id
    @GeneratedValue  //为一个实体生成一个唯一标识的主键
    private Long id;
    private String username;
    private String password;
    private Integer failnum;
    private Date logindate;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Integer getFailnum() {
        return this.failnum;
    }

    public void setFailnum(Integer failnum) {
        this.failnum = failnum;
    }

    public Date getLogindate() {
        return logindate;
    }

    public void setLogindate(Date logindate) {
        this.logindate = logindate;
    }
}
