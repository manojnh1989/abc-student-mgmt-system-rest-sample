package com.example.studentmgmtsystem.dto;

import com.example.studentmgmtsystem.domain.Student;
import com.example.studentmgmtsystem.validator.annotation.UniqueConstraint;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.example.studentmgmtsystem.constants.StudentMgmtSystemConstants.StudentFieldValidation.NAME_REGEX_PATTERN;
import static com.example.studentmgmtsystem.constants.StudentMgmtSystemConstants.EntityFields.NAME;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreateRequest {

    @UniqueConstraint(entityType = Student.class, entityFieldName = NAME)
    @ApiModelProperty(
            value = "Name",
            example = "Tom",
            dataType = "String"
    )
    @Pattern(regexp = NAME_REGEX_PATTERN, message = "Name should contain (alphanumeric+spaces) with camelcase")
    @Size(min = 3, message = "Name is too short")
    @Size(max = 100, message = "Name is too long")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @ApiModelProperty(
            value = "Name",
            example = "33/34 Park Avenue, NY",
            dataType = "String"
    )
    @Nullable
    @Size(max = 1000, message = "Address is too long")
    private String address;
}
