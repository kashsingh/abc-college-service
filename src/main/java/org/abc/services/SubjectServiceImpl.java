package org.abc.services;

import org.abc.data.entity.Course;
import org.abc.data.entity.Marks;
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
public class SubjectServiceImpl implements SubjectService {

    @Nonnull
    private StudentRepository studentRepository;

    @Nonnull
    private SubjectRepository subjectRepository;

    @Nonnull
    private MarksRepository marksRepository;

    @Nonnull
    private UserService userService;

    @Autowired
    public void setStudentRepository(@Nonnull StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Autowired
    public void setUserService(@Nonnull UserService userService) {
        this.userService = userService;
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
    public void createSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    public void updateSubject(Subject subject) throws NotFoundException {
        Subject existingSubject = subjectRepository.findSubjectById(subject.getId());
        if (existingSubject == null) {
            throw new NotFoundException("Subject not found!!");
        } else {
            subjectRepository.save(subject);
        }
    }

    @Override
    public void deleteSubject(Subject subject) throws NotFoundException {
        if (subjectRepository.findSubjectById(subject.getId()) == null) {
            throw new NotFoundException("Subject not found!!");
        } else {
            List<Marks> subjectMarks = marksRepository.findMarksBySubjectId(subject.getId());
            marksRepository.delete(subjectMarks);
            subjectRepository.delete(subject);
        }
    }

    @Override
    @Nonnull
    public Subject getAllSubjects(int subjectId) throws NotFoundException {
        Subject subject = subjectRepository.findSubjectById(subjectId);
        if (subject == null) {
            throw new NotFoundException(String.format("Subject with id %s not found", subjectId));
        }
        return subject;
    }

    @Nonnull
    @Override
    public List<Subject> viewCourseSubjects(Course course) throws NotFoundException {

        //Gets a list of all the subjects of a particular course.
        List<Subject> allCourseSubject = subjectRepository.findSubjectsByCourse(course);
        if (allCourseSubject.isEmpty()) {
            throw new NotFoundException("No subjects found for the course");
        }
        return allCourseSubject;
    }

    @Nonnull
    @Override
    public List<Subject> getStudentEnrolledSubjectsForSemester(Integer studentId, Integer semester) throws BadRequestException {

        List<Marks> studentMarks = marksRepository.findMarksByStudentIdAndSemester(studentId, semester);
        List<Subject> studentEnrolledSubjects = new ArrayList<>();

        if (studentMarks.isEmpty()) {
            throw new BadRequestException("Student hasn't enrolled yet for the semester.");
        }

        for (Marks mark : studentMarks) {
            studentEnrolledSubjects.add(mark.getSubject());
        }
        return studentEnrolledSubjects;
    }

    @Nonnull
    @Override
    public List<Subject> getStudentAllEnrolledSubjects(Integer studentId) throws BadRequestException {

        List<Marks> studentMarks = marksRepository.findMarksByStudentId(studentId);
        List<Subject> studentEnrolledSubjects = new ArrayList<>();

        if (studentMarks.isEmpty()) {
            throw new BadRequestException("Student hasn't enrolled yet for the semester.");
        }

        for (Marks mark : studentMarks) {
            studentEnrolledSubjects.add(mark.getSubject());
        }
        return studentEnrolledSubjects;
    }
}
