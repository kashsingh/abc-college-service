package org.abc.controllers;

import com.google.common.collect.ImmutableMap;
import org.abc.data.entity.Course;
import org.abc.data.entity.Marks;
import org.abc.data.entity.Student;
import org.abc.data.entity.Subject;
import org.abc.exceptions.NotFoundException;
import org.abc.services.AdminService;
import org.abc.services.SubjectService;
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
import java.util.ArrayList;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Nonnull
    private AdminService adminService;

    @Nonnull
    private SubjectService subjectService;

    @Autowired
    public void setSubjectService(@Nonnull SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Autowired
    public void setAdminService(@Nonnull AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping(value = "/student/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createStudent(@RequestBody Student student) {
        try {
            adminService.createStudent(student);
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

    @RequestMapping(value = "/student/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateStudent(@RequestBody Student student) {
        try {
            adminService.updateStudent(student);
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
            adminService.deleteStudent(adminService.getStudent(studentId));
            return new ResponseEntity<>(ImmutableMap.of("message", "Student Deleted"), HttpStatus.OK);
        } catch (NotFoundException e) {
            System.out.println(e);
            return new ResponseEntity<>(ImmutableMap.of("message", e.getMessage()), null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(ImmutableMap.of("message", "Internal Server Error"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/student/{student_id}/update-marks", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateStudentMarks(@PathVariable("student_id") int studentId, @RequestBody ArrayList<Marks> marks) {
        try {
            adminService.assignStudentMarks(studentId, marks);
            return new ResponseEntity<>(ImmutableMap.of("message", "Marks Updated"), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
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

    @RequestMapping(value = "/{course}/all-subjects", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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

    @RequestMapping(value = "/{course}/{batch}/topper", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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

    @RequestMapping(value = "/{course}/scoring-subjects", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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

    @RequestMapping(value = "/{course}/{batch}/class-result/{threshold}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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
