package org.abc.data.repository;

import org.abc.data.entity.Marks;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MarksRepository extends CrudRepository<Marks, Integer> {

    List<Marks> findMarksByStudentIdAndSemester(Integer studentId, Integer semester);

    List<Marks> findMarksBySubjectId(Integer subjectId);

    List<Marks> findMarksByStudentId(Integer studentId);

    @Query(value = "SELECT AVG(marks) FROM abc_portal.marks WHERE student_id = ?1", nativeQuery = true)
    Double findPercentageByStudentId(Integer studentId);


}
