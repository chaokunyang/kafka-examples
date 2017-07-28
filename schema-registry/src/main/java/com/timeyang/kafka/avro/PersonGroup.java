package com.timeyang.kafka.avro;

import java.util.List;

/**
 * @author chaokunyang
 */
public class PersonGroup {
    private Long id;
    private String name;
    private String interests;
    private List<Person> persons;
    private String description;
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInterests() {
        return interests;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
