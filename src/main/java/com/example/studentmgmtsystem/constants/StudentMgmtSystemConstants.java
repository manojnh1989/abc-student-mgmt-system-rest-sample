package com.example.studentmgmtsystem.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StudentMgmtSystemConstants {

    public static final String LOCAL_PROFILE = "local";
    public static final String REPOSITORY_METHOD_NAME_PREFIX = "findBy";

    @UtilityClass
    public static class Paths {

        public static final String ID_PATH_PARAMETER_NAME = "id";
        public static final String NAME_REQ_PARAMETER_NAME = "name";
        public static final String ID_PATH_PARAMETER = "/{" + ID_PATH_PARAMETER_NAME + "}";

        public static final String PAGES = "/pages";
    }

    @UtilityClass
    public static class EntityFields {
        public static final String NAME = "name";
    }

    @UtilityClass
    public static class StudentFieldValidation {

        public static final String NAME_REGEX_PATTERN = "^[A-Z][A-Za-z0-9 ]+[A-Za-z0-9]$";
    }
}
