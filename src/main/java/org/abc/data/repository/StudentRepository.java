package org.abc.data.repository;

import org.abc.data.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Integer> {

    Student findStudentById(int id);

    List<Student> findStudentByName(String name);
}
