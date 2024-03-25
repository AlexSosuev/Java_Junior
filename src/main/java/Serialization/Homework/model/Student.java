package Serialization.Homework.model;

import Serialization.Homework.annotations.Column;
import Serialization.Homework.annotations.Id;
import Serialization.Homework.annotations.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(table = "Student")
public class Student {
    @Id
    private int id;
    @Column(column = "age")
    private int age;
    @Column(column = "firstName")
    private String firstName;
    @Column(column = "lastName")
    private String lastName;
}