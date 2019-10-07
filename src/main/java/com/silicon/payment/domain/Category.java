package com.silicon.payment.domain;

import javax.persistence.*;

@Entity
@Table(name = "department")
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private Long name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getName() {
        return name;
    }

    public void setName(Long name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
