package com.example.studentmgmtsystem.controller;

import com.example.studentmgmtsystem.dto.StudentCreateRequest;
import com.example.studentmgmtsystem.dto.StudentInformation;
import com.example.studentmgmtsystem.dto.StudentUpdateRequest;
import com.example.studentmgmtsystem.service.StudentService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

import java.util.UUID;

import static com.example.studentmgmtsystem.constants.StudentMgmtSystemConstants.Paths.ID_PATH_PARAMETER_NAME;
import static com.example.studentmgmtsystem.constants.StudentMgmtSystemConstants.Paths.NAME_REQ_PARAMETER_NAME;
import static com.example.studentmgmtsystem.constants.StudentMgmtSystemConstants.Paths.ID_PATH_PARAMETER;
import static com.example.studentmgmtsystem.constants.StudentMgmtSystemConstants.Paths.PAGES;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/v1/student")
@Api(value = "Student Endpoints", tags = "Student", description = "Student Endpoints")
public class StudentController {

    private StudentService studentService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public StudentInformation addStudent(@Valid @RequestBody StudentCreateRequest studentCreateRequest) {
        return studentService.addStudentEntity(studentCreateRequest);
    }

    @GetMapping(path = ID_PATH_PARAMETER, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public StudentInformation findStudentById(@PathVariable(ID_PATH_PARAMETER_NAME) UUID id) {
        return studentService.findStudentById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public StudentInformation findStudentByName(@RequestParam(value = NAME_REQ_PARAMETER_NAME) String name) {
        return studentService.findStudentByName(name);
    }

    @PatchMapping(path = ID_PATH_PARAMETER, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public StudentInformation updateStudent(@PathVariable(ID_PATH_PARAMETER_NAME) UUID id,
                                            @Valid @RequestBody StudentUpdateRequest studentUpdateRequest) {
        return studentService.updateStudentEntity(id, studentUpdateRequest);
    }

    @DeleteMapping(value = ID_PATH_PARAMETER)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable(ID_PATH_PARAMETER_NAME) UUID id) {
        studentService.deleteStudentEntity(id);
    }

    @GetMapping(value = PAGES, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<StudentInformation> findStudentPages(final Pageable pageable) {
        return studentService.findAllStudentEntities(pageable);
    }
}
