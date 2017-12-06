package org.abc.data.repository;

import org.abc.data.entity.Course;
import org.abc.data.entity.Subject;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubjectRepository extends CrudRepository<Subject, Integer> {

    Subject findSubjectById(Integer id);

    List<Subject> findSubjectsByCourse(Course course);


}
