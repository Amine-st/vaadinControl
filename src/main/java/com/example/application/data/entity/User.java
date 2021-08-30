package com.example.application.data.entity;

import org.dom4j.tree.AbstractEntity;


public class User extends AbstractEntity {


    private int id ;
    private int uid ;
    private String name;
    private int privilege;
    private String password;
    private String group_id;
    private String user_id;
    private int card;



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPrivilege() {
        return privilege;
    }
    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getGroup_id() {
        return group_id;
    }
    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public int getCard() {
        return card;
    }
    public void setCard(int card) {
        this.card = card;
    }


}
