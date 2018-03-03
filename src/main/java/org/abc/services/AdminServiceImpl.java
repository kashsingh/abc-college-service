package org.abc.services;

import org.abc.data.dto.EditUserDetails;
import org.abc.data.dto.ScoringSubjectReport;
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
            updateSubjectMark.put(Integer.parseInt((String) subjectMark.get("subjectId")),
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
    public Map<String, Object> getTopperStudentForBatch(Course course, String batch) throws NotFoundException {

        //Get a list of students for the course and batch.
        List<Student> allClassStudents = studentRepository.findStudentsByCourseAndBatch(course, batch);

        //If the class is null, then throw the exception
        if (allClassStudents.isEmpty()) {
            throw new NotFoundException("No student found for the class.");
        }

        Student classTopper = new Student();
        double highestPercentage = 0.0;
        // Comparator to compare percentages for students.
        final Comparator<Student> comp = (s1, s2) -> Double.compare( getStudentPercentage(s1.getId()), getStudentPercentage(s2.getId()));

        // Stream to get the class topper with maximum percentage.
        classTopper = allClassStudents.stream().max(comp).get();
        // Gets the percentage for class topper.
        highestPercentage = getStudentPercentage(classTopper.getId());

        Map<String, Object> topperResult = new HashMap<>();

        if (highestPercentage != 0.0) {

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

            topperResult.put("student", studentUser);
            topperResult.put("percentage", Math.round(highestPercentage*100.0)/100.0);

        } else {
            throw new NotFoundException("Class topper doesn't exists.");
        }
        return topperResult;

    }

    @Nonnull
    @Override
    public Map<String, Object> getHighestAndLowestScoreSubjects(Course course, String batch) throws NotFoundException {

        // Gets the ordinal value stored in db for course.
        int courseId = course.ordinal();
        List<ScoringSubjectReport> subjectsReport = subjectRepository.getScoringSubjectReport(batch, courseId);

        if (subjectsReport.isEmpty()){
            throw new NotFoundException("No marks record found for the batch.");
        }

    /*  Since the query returns list of all subjects enrolled by students of the batch
        along with the the average marks scored by them in those subjects and the list is sorted in descending
        order of their score, so the first subject is the highest scoring subject and the last one should be least scoring.
        Although if the highest and lowest score is 0 then we can say that highest and lowest scoring subjects don't exists.*/

        ScoringSubjectReport highScoreSubject = subjectsReport.get(0);
        ScoringSubjectReport lowScoreSubject = subjectsReport.get(subjectsReport.size() - 1);

//      If the highest and lowest score is still zero then throw the exception.
        if (highScoreSubject.getAverageMarks() == 0.0 && lowScoreSubject.getAverageMarks() == 0.0) {
            throw new NotFoundException("Marks haven't been updated yet.");
        }

        Map<String, Object> highLowScoreSubjects = new HashMap<>();

        highLowScoreSubjects.put("high", highScoreSubject);
        highLowScoreSubjects.put("low", lowScoreSubject);

        return highLowScoreSubjects;
    }

    @Nonnull
    @Override
    public List<Map<String, Object>> getClassResult(Course course, String batch, double threshold) throws NotFoundException {

        // Get all the students of the class
        List<Student> allClassStudents = studentRepository.findStudentsByCourseAndBatch(course, batch);

        // Throw the exception if no student record found for the class.
        if (allClassStudents.isEmpty()) {
            throw new NotFoundException("No Student found for the Batch.");
        } else {

            List<Map<String, Object>> classResult = new ArrayList<>();

            for (Student student : allClassStudents) {

                Double studentPercentage = getStudentPercentage(student.getId());

                // If the student percentage is greater than the threshold then add it to classResult.
                if (studentPercentage >= threshold) {
                    Map<String, Object> studentResult = new HashMap<>();

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

                    studentResult.put("student", studentUser);
                    studentResult.put("percentage", Math.round(studentPercentage*100.0)/100.0);
                    classResult.add(studentResult);
                }
            }

            if (classResult.isEmpty()) {
                throw new NotFoundException("No student found above this threshold");
            }
            return classResult;
        }
    }

    private Double getStudentPercentage(int studentId){
        Double studentPercentage = 0.0;
        studentPercentage = marksRepository.findPercentageByStudentId(studentId);
        if (studentPercentage == null){
            studentPercentage = 0.0;
        }
        return studentPercentage;
    }
}
