package ru.asuslov.springcourse.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table (name = "person")
public class Person {
    @Transient
    private final String EMPTY_MES_ERROR = "Значение не может быть пустым";
    @Transient
    private final String NEGATIVE_MEANING_MES_ERROR = "Значение не может быть отрицательным";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "age")
    private int age;

    @NotEmpty(message = EMPTY_MES_ERROR)
    @Size(min=2,message = "Значение должно состоять минимум из 2-х символов")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = EMPTY_MES_ERROR)
    @Email(message = "Указанное значение не соответствует формату Email адреса")
    @Column(name = "email")
    private String email;

    public Person(){}

    public Person(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
