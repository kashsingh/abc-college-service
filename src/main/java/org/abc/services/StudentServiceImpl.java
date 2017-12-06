package org.abc.services;

import org.abc.data.entity.Marks;
import org.abc.data.entity.Student;
import org.abc.data.entity.Subject;
import org.abc.data.repository.MarksRepository;
import org.abc.data.repository.StudentRepository;
import org.abc.exceptions.BadRequestException;
import org.abc.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Nonnull
    private StudentRepository studentRepository;

    @Nonnull
    private MarksRepository marksRepository;

    @Autowired
    public void setStudentRepository(@Nonnull StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Autowired
    public void setMarksRepository(@Nonnull MarksRepository marksRepository) {

        this.marksRepository = marksRepository;
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

    @Override
    public void updateStudent(Student student) throws NotFoundException {
        Student existingStudent = studentRepository.findStudentById(student.getId());
        if (existingStudent == null) {
            throw new NotFoundException("Student not found!!");
        } else {
            existingStudent.setEmail(student.getEmail());
            existingStudent.setName(student.getName());
            existingStudent.setPhone(student.getPhone());
            existingStudent.setGender(student.getGender());
            studentRepository.save(existingStudent);
        }
    }

    @Override
    public void enrollSemester(Student student, List<Subject> subjects) throws BadRequestException{
        List<Marks> subjectMarks = new ArrayList<>();
        student.setCurrentSemester(student.getCurrentSemester() + 1);     //Updates current semester by 1
        studentRepository.save(student);
        int currentSemester = student.getCurrentSemester();
        for (Subject subject : subjects) {
            if (subject.getCourse().equals(student.getCourse())) {
                Marks mark = new Marks(null, student, subject, currentSemester, 0);
                subjectMarks.add(mark);
            } else {
                throw new BadRequestException("Course didn't match for the student and subject");
            }
        }
        marksRepository.save(subjectMarks);

    }

    @Override
    @Nonnull
    public List<Marks> viewSemesterResult(int studentId, int semester) throws NotFoundException {
        return marksRepository.findMarksByStudentIdAndSemester(studentId,semester);
    }

}
