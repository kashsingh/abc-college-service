package org.abc.services;

import org.abc.data.entity.Marks;
import org.abc.data.entity.Student;
import org.abc.data.entity.Subject;
import org.abc.data.repository.MarksRepository;
import org.abc.data.repository.StudentRepository;
import org.abc.data.repository.SubjectRepository;
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

    @Nonnull
    private SubjectRepository subjectRepository;

    @Autowired
    public void setStudentRepository(@Nonnull StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Autowired
    public void setMarksRepository(@Nonnull MarksRepository marksRepository) {

        this.marksRepository = marksRepository;
    }

    @Autowired
    public void setSubjectRepository(@Nonnull SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
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
            existingStudent.setBatch(student.getBatch());
            studentRepository.save(existingStudent);
        }
    }

    @Override
    public void enrollSemester(Student student, List<Subject> subjects) throws BadRequestException, NotFoundException {
        if (studentRepository.findStudentById(student.getId()) == null) {
            throw new NotFoundException("Student not found!");

        } else {
            List<Marks> subjectMarks = new ArrayList<>();
            student.setCurrentSemester(student.getCurrentSemester() + 1);     //Updates current semester by 1
            studentRepository.save(student);
            int currentSemester = student.getCurrentSemester();

            for (Subject subject : subjects) {
                if (subjectRepository.findSubjectById(subject.getId()) == null) {
                    throw new NotFoundException(String.format("Non-existing subject %s passed", subject.getSubjectName()));
                }

                if (subject.getCourse().equals(student.getCourse())) {
                    Marks mark = new Marks(null, student, subject, currentSemester, 0);
                    subjectMarks.add(mark);
                } else {
                    student.setCurrentSemester(currentSemester - 1);
                    studentRepository.save(student);
                    throw new BadRequestException("Course didn't match for the student and subject");
                }

            }
            marksRepository.save(subjectMarks);
        }
    }

    @Override
    @Nonnull
    public List<Marks> viewSemesterResult(int studentId, int semester) throws NotFoundException {
        if (studentRepository.findStudentById(studentId) == null) {
            throw new NotFoundException("Student not found!");
        } else {
            List<Marks> studentSemesterResult = marksRepository.findMarksByStudentIdAndSemester(studentId, semester);
            if (studentSemesterResult == null) {
                throw new NotFoundException("No results found for the semester");
            }
            return studentSemesterResult;
        }
    }

}
