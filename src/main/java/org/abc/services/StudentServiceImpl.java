package org.abc.services;

import org.abc.data.entity.Student;
import org.abc.data.repository.StudentRepository;
import org.abc.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;

@Service
public class StudentServiceImpl implements StudentService {

    @Nonnull
    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(@Nonnull StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void createStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    @Nonnull
    public Student getStudent(int studentId) throws NotFoundException {
        Student student = studentRepository.findStudentById(studentId);
        if (student == null) {
            throw new NotFoundException(String.format("Student with id %s not found", studentId));
        }
        return student;
    }
}
