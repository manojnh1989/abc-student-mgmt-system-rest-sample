package com.example.studentmgmtsystem.domain;

import com.example.studentmgmtsystem.domain.base.AbstractBaseEntity;
import com.example.studentmgmtsystem.dto.StudentCreateRequest;
import com.example.studentmgmtsystem.dto.StudentInformation;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Student extends AbstractBaseEntity {

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "address")
    private String address;

    public static Student from(final StudentCreateRequest studentCreateRequest) {
        return new Student(studentCreateRequest.getName(), studentCreateRequest.getAddress());
    }

    public static StudentInformation to(final Student student) {
        return new StudentInformation(student.getId(), student.getName(), student.getAddress());
    }
}
