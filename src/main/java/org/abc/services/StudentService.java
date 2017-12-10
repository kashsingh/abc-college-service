package org.abc.services;

import org.abc.data.entity.Marks;
import org.abc.data.entity.Student;
import org.abc.data.entity.Subject;
import org.abc.exceptions.BadRequestException;
import org.abc.exceptions.NotFoundException;

import javax.annotation.Nonnull;
import java.util.List;

public interface StudentService {

    @Nonnull
    Student getStudent(int studentId) throws NotFoundException;

    void enrollSemester(Student student, List<Subject> subjects) throws BadRequestException, NotFoundException;

    @Nonnull
    List<Marks> viewSemesterResult(int studentId, int semester) throws NotFoundException;

}
