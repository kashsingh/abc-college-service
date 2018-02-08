package org.abc.controllers;

import com.google.common.collect.ImmutableMap;
import org.abc.annotations.UserAuthorization;
import org.abc.data.dto.EditUserDetails;
import org.abc.data.entity.Student;
import org.abc.data.entity.Subject;
import org.abc.exceptions.BadRequestException;
import org.abc.exceptions.NotFoundException;
import org.abc.security.models.JwtUser;
import org.abc.services.StudentService;
import org.abc.services.SubjectService;
import org.abc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RequestMapping("/api/student")
@RestController
@UserAuthorization
public class StudentController {

    @Nonnull
    private StudentService studentService;

    @Nonnull
    private SubjectService subjectService;

    @Nonnull
    private UserService userService;

    @Autowired
    public void setSubjectService(@Nonnull SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Autowired
    public void setUserService(@Nonnull UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setStudentService(@Nonnull StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/view-details", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> viewStudent(HttpServletRequest request) {
        try {
            return new ResponseEntity<>(studentService.getStudent(request), HttpStatus.OK);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(ImmutableMap.of("message", e.getMessage()), null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ImmutableMap.of("message", "Internal Server Error"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateStudent(HttpServletRequest request, @RequestBody EditUserDetails editUserDetails) {
        try {
            JwtUser user = userService.getLoggedUser(request);
            userService.updateUser(user.getId(), editUserDetails);
            return new ResponseEntity<>(ImmutableMap.of("message", "User Record Updated"), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ImmutableMap.of("message", "Internal Server Error"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/view-result/semester/{semester}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> viewResult(HttpServletRequest request, @PathVariable("semester") int semester) {
        try {
            JwtUser user = userService.getLoggedUser(request);
            return new ResponseEntity<>(studentService.viewSemesterResult(request, semester), HttpStatus.OK);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(ImmutableMap.of("message", e.getMessage()), null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ImmutableMap.of("message", "Internal Server Error"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/enroll-semester", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> enrollSemester(HttpServletRequest request, @RequestBody ArrayList<Subject> subjects) {
        try {
            studentService.enrollSemester(request, subjects);
            return new ResponseEntity<>(ImmutableMap.of("message", "Enrolled"), HttpStatus.OK);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(ImmutableMap.of("message", e.getMessage()), null, HttpStatus.NOT_FOUND);
        } catch (BadRequestException e) {
            e.printStackTrace();
            return new ResponseEntity<>(ImmutableMap.of("message", e.getMessage()), null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ImmutableMap.of("message", "Internal Server Error"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/enrolled-subjects", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getEnrolledSubjects(HttpServletRequest request) {
        try {
            int studentId = studentService.getStudent(request).getStudentId();
            System.out.print(studentId);
            return new ResponseEntity<>(
                    subjectService.getStudentAllEnrolledSubjects(studentId), HttpStatus.OK);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(ImmutableMap.of("message", e.getMessage()), null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ImmutableMap.of("message", "Internal Server Error"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
