package org.abc.services;


import org.abc.data.dto.EditUserDetails;
import org.abc.data.dto.StudentUser;
import org.abc.data.dto.UpdateMarks;
import org.abc.data.entity.*;
import org.abc.data.entity.security.Authority;
import org.abc.data.entity.security.AuthorityName;
import org.abc.data.entity.security.User;
import org.abc.data.repository.*;
import org.abc.exceptions.BadRequestException;
import org.abc.exceptions.NotFoundException;
import org.abc.utils.TimeProvider;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.annotation.Nonnull;
import java.util.*;

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

    @Nonnull
    private AuthorityRepository authorityRepository;

    @Nonnull
    private UserService userService;

    @Autowired
    public void setUserService(@Nonnull UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAuthorityRepository(@Nonnull AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

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
        // Get the list of authorities for the user
        Authority authority = authorityRepository.findByName(AuthorityName.ROLE_USER);
        List<Authority> authorities = Arrays.asList(authority);

        // Get a new password encoder for the user
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User newUser = new User();

        //Create a new user from the details and save it.
        newUser.setId(null);
        newUser.setUsername(studentUser.getUsername());
        newUser.setPassword(passwordEncoder.encode(studentUser.getPassword()));
        newUser.setFirstname(studentUser.getFirstname());
        newUser.setLastname(studentUser.getLastname());
        newUser.setEmail(studentUser.getEmail());
        newUser.setEnabled(true);
        newUser.setLastPasswordResetDate(new TimeProvider().now());
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);

        newUser = userRepository.findByUsername(studentUser.getUsername());

        //Create new student using the user created above and save details.
        Student student = new Student();
        student.setId(null);
        student.setUser(newUser);
        student.setCourse(studentUser.getCourse());
        student.setBatch(studentUser.getBatch());
        student.setCurrentSemester(0);

        studentRepository.save(student);
    }

    @Override
    public void updateStudentDetails(int studentId, EditUserDetails editUserDetails) throws NotFoundException {

        Student student = studentRepository.findStudentById(studentId);
        if (student == null) {
            throw new NotFoundException("Student not found");
        }
        userService.updateUser(student.getUser().getId(), editUserDetails);
    }

    @Nonnull
    @Override
    public StudentUser getStudent(int studentId) throws NotFoundException {

        Student student = studentRepository.findStudentById(studentId);
        if (student == null) {
            throw new NotFoundException(String.format("Student with id %s not found", studentId));
        }

        // Get the user and send response with student user.
        User user = student.getUser();
        StudentUser studentUser = new StudentUser(studentId,
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
    public void deleteStudent(int studentId) throws NotFoundException {

        //Checking if the student exists or not.
        Student existingStudent = studentRepository.findStudentById(studentId);
        if (existingStudent == null) {
            throw new NotFoundException("Student not found!!");
        } else {
            User user = existingStudent.getUser();

            //This disables the user.
            user.setEnabled(false);
            userRepository.save(user);

            List<Marks> studentMarks = marksRepository.findMarksByStudentId(studentId);

            //After disabling the user delete its marks and student records.
            marksRepository.delete(studentMarks);
            studentRepository.delete(existingStudent);
        }
    }

    @Override
    public void assignStudentMarks(int studentId, int semester, UpdateMarks newMarks) throws NotFoundException, BadRequestException {

        Student existingStudent = studentRepository.findStudentById(studentId);
        if (existingStudent == null) {
            throw new NotFoundException("Student not found!!");
        }

        List<Marks> studentEnrolledSubjectMarks = marksRepository.findMarksByStudentIdAndSemester(studentId, semester);
        Set<Integer> subjectSet = new HashSet<>();

        //Create a set of enrolled subjects ids for the semester for a student
        for (Marks mark : studentEnrolledSubjectMarks) {
            subjectSet.add(mark.getSubject().getId());
        }

        //Creating a hash map of subject id and their marks
        Map<Integer, Float> updateSubjectMark = new HashMap();

        for (Map subjectMark : newMarks.getSubjectMarks()) {
            updateSubjectMark.put(Integer.parseInt((String) subjectMark.get("subject_id")),
                    Float.parseFloat((String) subjectMark.get("marks")));
        }

        //Checking if the all subjects in the request are enrolled before enrolling
        if (!subjectSet.containsAll(updateSubjectMark.keySet())) {
            throw new BadRequestException("Subject not enrolled for the semester by the student");
        }

        //Iterating over all enrolled subjects and checking if it is present in updateSubjectMark
        //If yes then update the marks details and finally saving the list of marks
        for (Marks mark : studentEnrolledSubjectMarks) {
            int subjectId = mark.getSubject().getId();
            if (updateSubjectMark.containsKey(subjectId)) {
                mark.setMarks(updateSubjectMark.get(subjectId));
            }
        }
        marksRepository.save(studentEnrolledSubjectMarks);
    }

    @Nonnull
    @Override
    public List<Pair> getTopperStudentForBatch(Course course, String batch) throws NotFoundException {

        //Get a list of students for the course and batch.
        List<Student> allClassStudents = studentRepository.findStudentsByCourseAndBatch(course, batch);

        //If the class is null, then throw the exception
        if (allClassStudents == null) {
            throw new NotFoundException("No student found for the class.");
        }

        Student classTopper = new Student();
        double highestPercentage = 0.0;

        for (Student student : allClassStudents) {

            double studentPercentage = 0.0;

            int noOfSubjects = 0;

            // Get all the marks for the student.
            List<Marks> studentSubjectsMarks = marksRepository.findMarksByStudentId(student.getId());

            // Calculate the student percentage.
            for (Marks mark : studentSubjectsMarks) {
                studentPercentage += mark.getMarks();
                noOfSubjects++;
            }
            studentPercentage /= noOfSubjects;

            // If the student percentage is higher than the current percentage then set it as highest
            // and set the topper as current student.
            if (studentPercentage > highestPercentage) {
                highestPercentage = studentPercentage;
                classTopper = student;
            }
        }

        List<Pair> topperResult = new ArrayList<>();
        if (classTopper.getId() != null) {

            User user = classTopper.getUser();
            StudentUser studentUser = new StudentUser(
                    classTopper.getId(),
                    user.getUsername(),
                    user.getFirstname(),
                    user.getLastname(),
                    user.getEmail(),
                    classTopper.getCourse(),
                    classTopper.getBatch(),
                    classTopper.getCurrentSemester());

            topperResult.add(Pair.of("topper_student", studentUser));
            topperResult.add(Pair.of("percentage", highestPercentage));

        } else {
            throw new NotFoundException("Class topper doesn't exists.");
        }
        return topperResult;

    }

    @Nonnull
    @Override
    public List<Pair> getHighestAndLowestScoreSubjects(Course course) throws NotFoundException {

        // Gets all subjects for the course
        List<Subject> allSubjectsOfCourse = subjectRepository.findSubjectsByCourse(course);

        // If no subjects are found throw the exception.
        if (allSubjectsOfCourse == null) {

            throw new NotFoundException("No subjects found for the course.");

        } else {
            // Assuming that the highest and lowest scoring subjects is the first subject in allSubjectsOfScore
            Subject highScoreSubject = allSubjectsOfCourse.get(0);
            Subject lowScoreSubject = allSubjectsOfCourse.get(0);

            // Assuming that the high score and low score for the subjects is 0.0
            double highestScore = 0.0;
            double lowestScore = 0.0;

            // Iterating over all the subjects.
            for (Subject subject : allSubjectsOfCourse) {

                // Get all the marks details for the particular subject
                List<Marks> allMarksForSubject = marksRepository.findMarksBySubjectId(subject.getId());

                // Assuming the subject's total marks for all the students and the students are zero.
                double subjectTotalMarks = 0.0;
                int totalStudents = 0;

                // Iterate over every marks detail and update subject's total marks and number of students.
                for (Marks marks : allMarksForSubject) {
                    subjectTotalMarks += marks.getMarks();
                    totalStudents++;
                }

                // Finding the percentage of the subject.
                double subjectScore = 0.0;
                if (subjectTotalMarks > 0) {
                    subjectScore = subjectTotalMarks / totalStudents;
                }

                // If the highest and lowest scores are zero then update them with the score of first
                // subject's score which is not zero.
                if (highestScore == 0.0 && lowestScore == 0.0) {
                    highestScore = subjectScore;
                    lowestScore = subjectScore;
                }
                // Else if the subject score is greater than highestScore than update the highestScore
                // and highScoreSubject flags.
                else if (subjectScore > highestScore) {
                    highestScore = subjectScore;
                    highScoreSubject = subject;
                }
                // Else if subject score is lower than the lowestScore than update the lowestScore and
                // lowScoreSubject flag
                else if (subjectScore < lowestScore) {
                    lowestScore = subjectScore;
                    lowScoreSubject = subject;
                }
            }

            // If the highest and lowest score is still zero then throw the exception.
            if (highestScore == 0.0 && lowestScore == 0.0) {
                throw new NotFoundException("No marks record found for the course.");
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

        // Get all the students of the class
        List<Student> allClassStudents = studentRepository.findStudentsByCourseAndBatch(course, batch);

        // Throw the exception if no student record found for the class.
        if (allClassStudents == null) {
            throw new NotFoundException("No Student found for the Batch!");

        } else {

            List<List<Pair>> classResult = new ArrayList<>();

            for (Student student : allClassStudents) {

                double studentTotal = 0.0;
                double studentPercentage = 0.0;
                int noOfSubjects = 0;
                List<Marks> studentSubjectsMarks = marksRepository.findMarksByStudentId(student.getId());

                // Calculate noOfSubjects and studentTotal.
                for (Marks mark : studentSubjectsMarks) {
                    studentTotal += mark.getMarks();
                    noOfSubjects++;
                }

                // If the student total is greater then zero then find percentage for the student.
                if (studentTotal > 0) {
                    studentPercentage = studentTotal / noOfSubjects;
                }

                // If the student percentage is greater than the threshold then add it to classResult.
                if (studentPercentage >= threshold) {
                    List<Pair> studentResult = new ArrayList<>();

                    //Get the student detail
                    User user = student.getUser();
                    StudentUser studentUser = new StudentUser(
                            student.getId(),
                            user.getUsername(),
                            user.getFirstname(),
                            user.getLastname(),
                            user.getEmail(),
                            student.getCourse(),
                            student.getBatch(),
                            student.getCurrentSemester());

                    studentResult.add(Pair.of("student", studentUser));
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
