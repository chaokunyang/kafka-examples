package com.timeyang.avro;

/**
 * @author yangck
 */
public class User {
    private Long id;
    private String username;
    private String gender;
    private Integer age;
    private String desc;

    public User(String username, String gender, Integer age, String desc) {
        this.username = username;
        this.gender = gender;
        this.age = age;
        this.desc = desc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
