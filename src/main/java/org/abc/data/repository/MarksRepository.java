package org.abc.data.repository;

import org.abc.data.entity.Marks;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MarksRepository extends CrudRepository<Marks, Integer> {

    Marks findMarksByStudentIdAndSemester(Integer studentId, int semester);

    List<Marks> findMarksBySubjectId(Integer subjectId);
}
