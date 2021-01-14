package ru.asuslov.springcourse.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.Period;
import java.util.Date;
import java.time.LocalDate;
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
    private Integer id;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "birthdate")
    private Date birthDate;

    @NotEmpty(message = EMPTY_MES_ERROR)
    @Size(min=2,message = "Значение должно состоять минимум из 2-х символов")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = EMPTY_MES_ERROR)
    @Email(message = "Указанное значение не соответствует формату Email адреса")
    @Column(name = "email")
    private String email;

    @Column(name = "gender", nullable = true)
    private Integer gender;

    @Transient
    private String genderName;

    @Transient
    private Integer age;

    public Person(){}

    public Person(Integer id, String name, Integer age, String email, Integer gender, String genderName) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.gender = gender;
        this.genderName = genderName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthdate) {
        this.birthDate = birthdate;
    }

    public Integer getAge() {
        //return this.age;

        long difference_In_Time = new Date().getTime() - birthDate.getTime();
        return (int)(difference_In_Time / (1000l * 60 * 60 * 24 * 365));
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getGenderName() {
        switch (this.gender) {
            case 0: return "Male";
            case 1: return "Female";
            default: return "";
        }
    }

    public void setGenderName(String genderName) { }
}
