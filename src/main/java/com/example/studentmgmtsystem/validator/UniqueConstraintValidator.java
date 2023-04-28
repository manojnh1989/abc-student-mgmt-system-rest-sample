package com.example.studentmgmtsystem.validator;

import com.example.studentmgmtsystem.repository.RepositoryWrapperFactory;
import com.example.studentmgmtsystem.validator.annotation.UniqueConstraint;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.text.CaseUtils;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.hibernate.validator.internal.metadata.descriptor.ConstraintDescriptorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.Optional;

import static com.example.studentmgmtsystem.constants.StudentMgmtSystemConstants.REPOSITORY_METHOD_NAME_PREFIX;

@Log4j2
public class UniqueConstraintValidator implements ConstraintValidator<UniqueConstraint, Object> {

    @Autowired
    private RepositoryWrapperFactory repositoryWrapperFactory;

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        // Check if value is non-null
        if (Objects.isNull(value)) {
            log.debug("Value to check validation is null. Applicable for update requests");
            return true;
        }
        final var annotation = retrieveAnnotationFromContext(context);
        final var repository = repositoryWrapperFactory.getRepository(annotation.entityType());
        final var repositoryMethod = ReflectionUtils.findRequiredMethod(repository.getClass().getInterfaces()[0],
                REPOSITORY_METHOD_NAME_PREFIX + CaseUtils.toCamelCase(annotation.entityFieldName(), true), value.getClass());
        final Optional<?> optional;
        try {
            optional = (Optional<?>) repositoryMethod.invoke(repository, value);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        final var isPresent = Objects.nonNull(optional) && optional.isPresent();
        if (isPresent) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(MessageFormat.format("Record already exists for {0}:{1}",
                    annotation.entityFieldName(), value)).addConstraintViolation();
        }
        return !isPresent;
    }

    private static UniqueConstraint retrieveAnnotationFromContext(final ConstraintValidatorContext context) {
        var ctx = (ConstraintValidatorContextImpl) context;
        var descriptor = (ConstraintDescriptorImpl<UniqueConstraint>) ctx.getConstraintDescriptor();
        return descriptor.getAnnotationDescriptor().getAnnotation();
    }
}
