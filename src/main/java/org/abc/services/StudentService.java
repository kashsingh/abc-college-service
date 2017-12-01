package org.abc.services;

import org.abc.data.entity.Student;
import org.abc.exceptions.NotFoundException;

import javax.annotation.Nonnull;

public interface StudentService {

    void createStudent(Student student);

    @Nonnull
    Student getStudent(int studentId) throws NotFoundException;
}
