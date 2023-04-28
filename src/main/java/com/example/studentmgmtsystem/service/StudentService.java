package com.example.studentmgmtsystem.service;

import com.example.studentmgmtsystem.domain.Student;
import com.example.studentmgmtsystem.dto.StudentCreateRequest;
import com.example.studentmgmtsystem.dto.StudentInformation;
import com.example.studentmgmtsystem.dto.StudentUpdateRequest;
import com.example.studentmgmtsystem.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.text.MessageFormat;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
@AllArgsConstructor
public class StudentService {

    private StudentRepository studentRepository;

    @Transactional
    public StudentInformation addStudentEntity(final StudentCreateRequest studentCreateRequest) {
        return Student.to(studentRepository.save(Student.from(studentCreateRequest)));
    }

    public StudentInformation findStudentById(final UUID id) {
        final var student = studentRepository.findById(id).orElseThrow(() -> new NoSuchElementException(
                MessageFormat.format("No record found for id:{0}", id)));
        return Student.to(student);
    }

    public StudentInformation findStudentByName(final String name) {
        final var student = studentRepository.findByName(name).orElseThrow(() -> new NoSuchElementException(
                MessageFormat.format("No record found for name:{0}", name)));
        return Student.to(student);
    }

    @Transactional
    public StudentInformation updateStudentEntity(final UUID id, final StudentUpdateRequest studentUpdateRequest) {
        final var student = studentRepository.findById(id).orElseThrow(() -> new NoSuchElementException(
                MessageFormat.format("No record found for id:{0}", id)));
        // Update Name
        if (!ObjectUtils.nullSafeEquals(studentUpdateRequest.getName(), student.getName())) {
            student.setName(studentUpdateRequest.getName());
        }

        if (!ObjectUtils.nullSafeEquals(studentUpdateRequest.getAddress(), student.getAddress())) {
            student.setAddress(studentUpdateRequest.getAddress());
        }
        return Student.to(studentRepository.save(student));
    }

    @Transactional
    public void deleteStudentEntity(final UUID id) {
        studentRepository.deleteById(id);
    }

    public Page<StudentInformation> findAllStudentEntities(final Pageable pageable) {
        final var studentPages = studentRepository.findAll(pageable);
        return new PageImpl<>(studentPages.getContent().stream().map(Student::to).collect(Collectors.toList()),
                pageable, studentPages.getTotalElements());
    }
 }
