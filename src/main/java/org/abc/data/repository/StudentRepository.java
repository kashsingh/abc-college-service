package org.abc.data.repository;

import org.abc.data.entity.Course;
import org.abc.data.entity.Student;
import org.abc.data.entity.security.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Integer> {

    Student findStudentById(Integer id);

    Student findByUserId(Long userId);

    List<Student> findStudentsByCourseAndBatch(Course course, String batch);
}
