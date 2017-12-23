package org.abc.data.repository;

import org.abc.data.entity.Course;
import org.abc.data.entity.Marks;
import org.abc.data.entity.Subject;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MarksRepository extends CrudRepository<Marks, Integer> {

    List<Marks> findMarksByStudentIdAndSemester(Integer studentId, int semester);

    List<Marks> findMarksBySubjectId(Integer subjectId);

    List<Marks> findMarksByStudentId(Integer studentId);


}
