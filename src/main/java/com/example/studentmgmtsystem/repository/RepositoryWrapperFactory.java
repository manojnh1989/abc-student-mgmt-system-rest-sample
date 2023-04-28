package com.example.studentmgmtsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

@FunctionalInterface
public interface RepositoryWrapperFactory {

    JpaRepository<?, ?> getRepository(final Class<?> entityType);
}
