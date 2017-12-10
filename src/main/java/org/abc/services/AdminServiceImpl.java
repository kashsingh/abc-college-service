package org.abc.services;


import org.abc.data.DTO.StudentUser;
import org.abc.data.entity.*;
import org.abc.data.repository.MarksRepository;
import org.abc.data.repository.StudentRepository;
import org.abc.data.repository.SubjectRepository;
import org.abc.data.repository.UserRepository;
import org.abc.exceptions.NotFoundException;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Nonnull
    private UserRepository userRepository;

    @Nonnull
    private StudentRepository studentRepository;

    @Nonnull
    private SubjectRepository subjectRepository;

    @Nonnull
    private MarksRepository marksRepository;

    @Autowired
    public void setUserRepository(@Nonnull UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setMarksRepository(@Nonnull MarksRepository marksRepository) {
        this.marksRepository = marksRepository;
    }

    @Autowired
    public void setStudentRepository(@Nonnull StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Autowired
    public void setSubjectRepository(@Nonnull SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void createStudentUser(StudentUser studentUser) {
        User user = studentUser.getUser();
        userRepository.save(user);


        user = userRepository.findUserByEmail(user.getEmail());
        System.out.print(user);
        Student student = new Student();    //null, user, studentUser.getCourse(), studentUser.getBatch(), 0
        student.setId(null);
        student.setUser(user);
        student.setCourse(studentUser.getCourse());
        student.setBatch(studentUser.getBatch());
        student.setCurrentSemester(0);
        studentRepository.save(student);
    }

    @Nonnull
    @Override
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
            student.setBatch(existingStudent.getBatch());
            student.setCurrentSemester(existingStudent.getCurrentSemester());
            studentRepository.save(student);
        }
    }

    @Override
    public void deleteStudent(Student student) throws NotFoundException {
        Student existingStudent = studentRepository.findStudentById(student.getId());
        if (existingStudent == null) {
            throw new NotFoundException("Student not found!!");
        } else {
            List<Marks> studentMarks = marksRepository.findMarksByStudentId(student.getId());
            marksRepository.delete(studentMarks);
            studentRepository.delete(student);
        }
    }

    @Override
    public void assignStudentMarks(int studentId, List<Marks> marks) throws NotFoundException {
        Student existingStudent = studentRepository.findStudentById(studentId);
        if (existingStudent == null) {
            throw new NotFoundException("Student not found!!");
        } else {
            marksRepository.save(marks);
        }

    }

    @Nonnull
    @Override
    public List<Pair> getTopperStudentForBatch(Course course, String batch) throws NotFoundException {
        List<Student> allClassStudents = studentRepository.findStudentsByCourseAndBatch(course, batch);
        if (allClassStudents == null) {
            throw new NotFoundException("No student found for the class.");
        } else {
            Student classTopper = new Student();
            double highestPercentage = 0.0;
            for (Student student : allClassStudents) {
                double studentPercentage = 0.0;
                int noOfSubjects = 0;
                List<Marks> studentSubjectsMarks = marksRepository.findMarksByStudentId(student.getId());

                for (Marks mark : studentSubjectsMarks) {
                    studentPercentage += mark.getMarks();
                    noOfSubjects++;
                }

                studentPercentage /= noOfSubjects;
                if (studentPercentage > highestPercentage) {
                    highestPercentage = studentPercentage;
                    classTopper = student;
                }
            }

            List<Pair> topperResult = new ArrayList<>();
            topperResult.add(Pair.of("topper", classTopper));
            topperResult.add(Pair.of("percentage", highestPercentage));
            return topperResult;
        }
    }

    @Nonnull
    @Override
    public List<Pair> getHighestAndLowestScoreSubjects(Course course) throws NotFoundException {
        List<Subject> allSubjectsOfCourse = subjectRepository.findSubjectsByCourse(course);

        if (allSubjectsOfCourse == null) {
            throw new NotFoundException("No subjects found for the course.");

        } else {
            Subject highScoreSubject = allSubjectsOfCourse.get(0);
            Subject lowScoreSubject = allSubjectsOfCourse.get(0);
            double highestScore = 0.0;
            double lowestScore = 0.0;

            for (Subject subject : allSubjectsOfCourse) {
                List<Marks> allMarksForSubject = marksRepository.findMarksBySubjectId(subject.getId());
                double subjectTotalMarks = 0.0;
                int totalStudents = 0;

                for (Marks marks : allMarksForSubject) {
                    subjectTotalMarks += marks.getMarks();
                    totalStudents++;
                }

                double subjectScore = subjectTotalMarks / totalStudents;

                if (highestScore == 0.0 && lowestScore == 0.0) {
                    highestScore = subjectScore;
                    lowestScore = subjectScore;
                } else if (subjectScore > highestScore) {
                    highestScore = subjectScore;
                    highScoreSubject = subject;
                } else if (subjectScore < lowestScore) {
                    lowestScore = subjectScore;
                    lowScoreSubject = subject;
                }
            }
            List<Pair> highLowScoreSubjects = new ArrayList<>();
            highLowScoreSubjects.add(Pair.of("high", highScoreSubject));
            highLowScoreSubjects.add(Pair.of("low", lowScoreSubject));
            return highLowScoreSubjects;
        }
    }

    @Nonnull
    @Override
    public List<List<Pair>> getClassResult(Course course, String batch, double threshold) throws NotFoundException {
        List<Student> allClassStudents = studentRepository.findStudentsByCourseAndBatch(course, batch);

        if (allClassStudents == null) {
            throw new NotFoundException("No Student found for the Batch!");

        } else {
            List<List<Pair>> classResult = new ArrayList<>();
            for (Student student : allClassStudents) {
                double studentPercentage = 0.0;
                int noOfSubjects = 0;
                List<Marks> studentSubjectsMarks = marksRepository.findMarksByStudentId(student.getId());

                for (Marks mark : studentSubjectsMarks) {
                    studentPercentage += mark.getMarks();
                    noOfSubjects++;
                }

                studentPercentage = studentPercentage / noOfSubjects;
                if (studentPercentage >= threshold) {
                    List<Pair> studentResult = new ArrayList<>();
                    studentResult.add(Pair.of("student",student));
                    studentResult.add(Pair.of("percentage", studentPercentage));
                    classResult.add(studentResult);
                }
            }

            if (classResult.isEmpty()) {
                throw new NotFoundException("No student found above this threshold");
            }
            return classResult;
        }

    }
}
