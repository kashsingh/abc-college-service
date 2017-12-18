package org.abc.services;

import org.abc.data.dto.StudentUser;
import org.abc.data.entity.Marks;
import org.abc.data.entity.Student;
import org.abc.data.entity.Subject;
import org.abc.exceptions.BadRequestException;
import org.abc.exceptions.NotFoundException;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface StudentService {

    @Nonnull
    StudentUser getStudent(HttpServletRequest request) throws NotFoundException;

    void enrollSemester(HttpServletRequest request, List<Subject> subjects) throws BadRequestException, NotFoundException;

    @Nonnull
    List<Marks> viewSemesterResult(int studentId, int semester) throws NotFoundException;

}
