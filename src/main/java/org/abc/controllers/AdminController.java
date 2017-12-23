package org.abc.controllers;

import com.google.common.collect.ImmutableMap;
import org.abc.annotations.AdminAuthorization;
import org.abc.data.dto.EditUserDetails;
import org.abc.data.dto.StudentUser;
import org.abc.data.dto.UpdateMarks;
import org.abc.data.entity.*;
import org.abc.exceptions.BadRequestException;
import org.abc.exceptions.NotFoundException;
import org.abc.services.AdminService;
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

@RestController
@RequestMapping("/api/admin")
@AdminAuthorization
public class AdminController {

    @Nonnull
    private AdminService adminService;

    @Nonnull
    private SubjectService subjectService;

    @Nonnull
    private UserService userService;

    @Nonnull
    public void setUserService(@Nonnull UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setSubjectService(@Nonnull SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Autowired
    public void setAdminService(@Nonnull AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping(value = "/student/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createStudent(@RequestBody StudentUser studentUser) {
        try {
            adminService.createStudentUser(studentUser);
            return new ResponseEntity<>(ImmutableMap.of("message", "Created"), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ImmutableMap.of("message", "Internal Server Error"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/student/{student_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> viewStudent(@PathVariable("student_id") int studentId) {
        try {
            return new ResponseEntity<>(adminService.getStudent(studentId), HttpStatus.OK);
        } catch (NotFoundException e) {
            System.out.println(e);
            return new ResponseEntity<>(ImmutableMap.of("message", e.getMessage()), null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(ImmutableMap.of("message", "Internal Server Error"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/student/{student_id}/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateStudent(@PathVariable("student_id") int studentId, @RequestBody EditUserDetails editUserDetails) {
        try {
            adminService.updateStudentDetails(studentId, editUserDetails);
            return new ResponseEntity<>(ImmutableMap.of("message", "Student Record Updated"), HttpStatus.OK);
        } catch (NotFoundException e) {
            System.out.println(e);
            return new ResponseEntity<>(ImmutableMap.of("message", e.getMessage()), null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ImmutableMap.of("message", "Internal Server Error"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "student/{student_id}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteStudent(@PathVariable("student_id") int studentId) {
        try {
            adminService.deleteStudent(studentId);
            return new ResponseEntity<>(ImmutableMap.of("message", "Student Deleted"), HttpStatus.OK);
        } catch (NotFoundException e) {
            System.out.println(e);
            return new ResponseEntity<>(ImmutableMap.of("message", e.getMessage()), null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(ImmutableMap.of("message", "Internal Server Error"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/student/{student_id}/semester/{semester}/update-marks", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateStudentMarks(@PathVariable("student_id") int studentId,
                                                     @PathVariable("semester") int semester,
                                                     @RequestBody UpdateMarks updateMarks) {
        try {
            adminService.assignStudentMarks(studentId, semester, updateMarks);
            return new ResponseEntity<>(ImmutableMap.of("message", "Marks Updated"), HttpStatus.OK);
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

    @RequestMapping(value = "/subject/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createSubject(@RequestBody Subject subject) {
        try {
            subjectService.createSubject(subject);
            return new ResponseEntity<>(ImmutableMap.of("message", "Created"), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(ImmutableMap.of("message", "Internal Server Error"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/subject/all-subjects/course/{course}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getSubjects(@PathVariable("course") Course course) {
        try {
            return new ResponseEntity<>(subjectService.viewCourseSubjects(course), HttpStatus.OK);
        } catch (NotFoundException e) {
            System.out.println(e);
            return new ResponseEntity<>(ImmutableMap.of("message", e.getMessage()), null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(ImmutableMap.of("message", "Internal Server Error"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/subject/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateSubject(@RequestBody Subject subject) {
        try {
            subjectService.updateSubject(subject);
            return new ResponseEntity<>(ImmutableMap.of("message", "Subject Record Updated"), HttpStatus.OK);
        } catch (NotFoundException e) {
            System.out.println(e);
            return new ResponseEntity<>(ImmutableMap.of("message", e.getMessage()), null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ImmutableMap.of("message", "Internal Server Error"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "subject/{subject_id}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteSubject(@PathVariable("subject_id") int subjectId) {
        try {
            subjectService.deleteSubject(subjectService.getSubject(subjectId));
            return new ResponseEntity<>(ImmutableMap.of("message", "Subject Deleted"), HttpStatus.OK);
        } catch (NotFoundException e) {
            System.out.println(e);
            return new ResponseEntity<>(ImmutableMap.of("message", e.getMessage()), null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(ImmutableMap.of("message", "Internal Server Error"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/report/course/{course}/batch/{batch}/topper", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getClassTopper(@PathVariable("course") Course course, @PathVariable("batch") String batch) {
        try {
            return new ResponseEntity<>(adminService.getTopperStudentForBatch(course, batch), HttpStatus.OK);
        } catch (NotFoundException e) {
            System.out.println(e);
            return new ResponseEntity<>(ImmutableMap.of("message", e.getMessage()), null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(ImmutableMap.of("message", "Internal Server Error"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/report/course/{course}/scoring-subjects", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getHighAndLowScoringSubjects(@PathVariable("course") Course course) {
        try {
            return new ResponseEntity<>(adminService.getHighestAndLowestScoreSubjects(course), HttpStatus.OK);
        } catch (NotFoundException e) {
            System.out.println(e);
            return new ResponseEntity<>(ImmutableMap.of("message", e.getMessage()), null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(ImmutableMap.of("message", "Internal Server Error"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "report/course/{course}/batch/{batch}/class-result/th/{threshold}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getClassResult(@PathVariable("course") Course course, @PathVariable("batch") String batch, @PathVariable("threshold") double threshold) {
        try {
            return new ResponseEntity<>(adminService.getClassResult(course, batch, threshold), HttpStatus.OK);
        } catch (NotFoundException e) {
            System.out.println(e);
            return new ResponseEntity<>(ImmutableMap.of("message", e.getMessage()), null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(ImmutableMap.of("message", "Internal Server Error"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
