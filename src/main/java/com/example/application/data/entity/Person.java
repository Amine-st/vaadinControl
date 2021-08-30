package com.example.application.data.entity;

import org.dom4j.tree.AbstractEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"uid"})})
public class Person extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;


    @NotNull
    @Column(name = "uid")
    private int uid ;

    @NotNull
    private String name;

    @NotNull
    private int privilege;

    @NotNull
    private String password;

    @NotNull
    private String group_id;

    @NotNull
    private String user_id;

    @NotNull
    private int card;

    @ManyToOne
    @JoinColumn(name="timezone1", referencedColumnName="id")
    private TimeZone timezone1 ;
    @ManyToOne
    @JoinColumn(name="timezone2", referencedColumnName="id")
    private TimeZone timezone2 ;

    @ManyToOne
    @JoinColumn(name="timezone3", referencedColumnName="id")
    private TimeZone timezone3 ;

    //@ManyToOne(fetch = FetchType.EAGER)
    //private Authority authorities;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public TimeZone getTimezone1() {
        return timezone1;
    }

    public void setTimezone1(TimeZone timezone1) {
        this.timezone1 = timezone1;
    }

    public TimeZone getTimezone2() {
        return timezone2;
    }

    public void setTimezone2(TimeZone timezone2) {
        this.timezone2 = timezone2;
    }

    public TimeZone getTimezone3() {
        return timezone3;
    }

    public void setTimezone3(TimeZone timezone3) {
        this.timezone3 = timezone3;
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
        this.password = password ;
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
