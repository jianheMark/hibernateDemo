package edu.mum.cs.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Person {
    @Id //In this case, the First name is primary Key.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long id;

    @Column(name = "first", length = 255)
    private String firstName;
    private String lastName;

    @Column(unique = true, length = 250)
    private String email;

    @Column(columnDefinition = "DATE")
    private LocalDate birthDate;

    @Transient //Won't map int temp to the table. When one element don't want into database table.
    private int temp;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public Person(String firstName, String lastName, String email, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;

    }


}
