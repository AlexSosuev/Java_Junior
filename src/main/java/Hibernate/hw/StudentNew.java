package Hibernate.hw;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Setter
@Getter
@Entity(name = "StudentNew")
@Table(name="StudentNew")
public class StudentNew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private int age;

    public StudentNew() {
    }
}
