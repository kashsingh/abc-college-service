package org.abc.services;

import org.abc.data.dto.MarksDetails;
import org.abc.data.dto.StudentUser;
import org.abc.data.entity.Marks;
import org.abc.data.entity.Student;
import org.abc.data.entity.Subject;
import org.abc.data.repository.MarksRepository;
import org.abc.data.repository.StudentRepository;
import org.abc.data.repository.SubjectRepository;
import org.abc.exceptions.BadRequestException;
import org.abc.exceptions.NotFoundException;
import org.abc.security.models.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
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

    @Nonnull
    private UserService userService;

    @Nonnull
    private SubjectService subjectService;

    @Autowired
    public void setSubjectService(@Nonnull SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Autowired
    public void setUserService(@Nonnull UserService userService) {
        this.userService = userService;
    }


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
    public StudentUser getStudent(HttpServletRequest request) throws NotFoundException {

        // Get the logged user and find the student by its userId.
        JwtUser user = userService.getLoggedUser(request);
        Student student = studentRepository.findByUserId(user.getId());

        // If not found throw the exception.
        if (student == null) {
            throw new NotFoundException(String.format("Student with username %s not found", user.getUsername()));
        }

        // Return the student user details.
        StudentUser studentUser = new StudentUser(
                student.getId(),
                                                    user.getUsername(),
                                                    user.getFirstname(),
                                                    user.getLastname(),
                                                    user.getEmail(),
                                                    student.getCourse(),
                                                    student.getBatch(),
                                                    student.getCurrentSemester());
        return studentUser;
    }


    @Override
    public void enrollSemester(HttpServletRequest request, List<Subject> subjects) throws BadRequestException, NotFoundException {
        JwtUser user = userService.getLoggedUser(request);
        Student student = studentRepository.findByUserId(user.getId());

        if (student == null) {
            throw new NotFoundException("Student not found!");
        }
        // If the student is currently in eighth semester don't let him/her enroll.
        else if (student.getCurrentSemester() == 8) {
            throw new BadRequestException("Student already enrolled 8 semesters");
        }

        List<Marks> subjectMarks = new ArrayList<>();
        int enrollingSemester = student.getCurrentSemester() + 1;

        List<Subject> studentEnrolledSubjects = subjectService.getStudentEnrolledSubjects(student.getId());

        // Validating the request
        for (Subject subject : subjects) {
            // Checking if subject exists and the student and subject have the same course.
            if ((subjectRepository.findSubjectById(subject.getId()) == null) || !subject.getCourse().equals(student.getCourse())) {
                throw new NotFoundException(String.format("Non-existing/wrong course subject %s passed", subject.getSubjectName()));
            }
            // Checking if the student has already enrolled for the subject or not.
            else if (studentEnrolledSubjects != null && studentEnrolledSubjects.contains(subject)) {
                throw new BadRequestException("Student already enrolled for subject");
            }
        }

        //Adding default marks to save the enrolled subjects for student.
        for (Subject subject : subjects) {
            Marks mark = new Marks(null, student, subject, enrollingSemester, 0);
            subjectMarks.add(mark);
        }

        //Saving student and marks, for storing enrolled subjects.
        student.setCurrentSemester(enrollingSemester);
        studentRepository.save(student);
        marksRepository.save(subjectMarks);
    }

    @Override
    @Nonnull
    public List<MarksDetails> viewSemesterResult(HttpServletRequest request, int semester) throws NotFoundException {

        // Get the requester details from the token.
        JwtUser user = userService.getLoggedUser(request);
        Student student = studentRepository.findByUserId(user.getId());

        if (student == null) {
            throw new NotFoundException("Student not found!");
        } else {

            // Find all marks details for the student for a semester.
            List<Marks> studentSemesterResult = marksRepository.findMarksByStudentIdAndSemester(student.getId(), semester);

            if (studentSemesterResult == null) {
                throw new NotFoundException("No results found for the semester");
            }

            List<MarksDetails> semesterResultDetails = new ArrayList<>();

            // Create a MarksDetails object and send the details.
            for (Marks mark : studentSemesterResult) {
                semesterResultDetails.add(new MarksDetails(mark.getStudent().getId(),
                        mark.getSubject().getId(),
                        mark.getSemester(),
                        mark.getSubject().getSubjectName(),
                        mark.getMarks()));
            }
            return semesterResultDetails;
        }
    }

}
