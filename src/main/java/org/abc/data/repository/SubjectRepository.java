package org.abc.data.repository;

import org.abc.data.entity.Course;
import org.abc.data.entity.Subject;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRepository extends CrudRepository<Subject, Integer> {

    Subject findSubjectById(Integer id);

    Subject findSubjectBySubjectName(String subjectName);

    Subject findSubjectByCourse(Course course);       //Don't weather this is correct or not


}
