package com.example.studentmgmtsystem.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

import java.util.UUID;

@Value
public class StudentInformation {

    @ApiModelProperty(
            value = "Unique Identifier",
            example = "f13bec53-c8a3-43f6-a1d7-7faa62296530",
            dataType = "UUID"
    )
    UUID id;

    @ApiModelProperty(
            value = "Name",
            example = "Tom",
            dataType = "String"
    )
    String name;

    @ApiModelProperty(
            value = "Address",
            example = "33/34 Park Avenue, NY",
            dataType = "String"
    )
    String address;
}
