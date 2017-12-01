package org.abc.controllers;

import com.google.common.collect.ImmutableMap;
import org.abc.data.entity.Student;
import org.abc.exceptions.NotFoundException;
import org.abc.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nonnull;

@RequestMapping("/api/student")
@RestController
public class StudentController {

    @Nonnull
    private StudentService studentService;

    @Autowired
    public void setStudentService(@Nonnull StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createStudent(@RequestBody Student student) {
        try {
            studentService.createStudent(student);
            return new ResponseEntity<>(ImmutableMap.of("message", "Created"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(ImmutableMap.of("message", "Internal Server Error"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{studentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getStudent(@PathVariable("studentId") int studentId) {
        try {
            return new ResponseEntity<>(studentService.getStudent(studentId), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(ImmutableMap.of("message", e.getMessage()), null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(ImmutableMap.of("message", "Internal Server Error"), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
