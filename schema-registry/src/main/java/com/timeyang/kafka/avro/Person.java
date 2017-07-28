package com.timeyang.kafka.avro;

/**
 * @author chaokunyang
 */
public class Person {
    private Long id;
    private String name;
    private String gender;
    private Integer age;
    private String description;
    private PersonGroup personGroup;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public String getDescription() {
        return description;
    }

    public PersonGroup getPersonGroup() {
        return personGroup;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPersonGroup(PersonGroup personGroup) {
        this.personGroup = personGroup;
    }
}
