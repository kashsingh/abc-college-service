package org.abc.data.repository;

import org.abc.data.dto.ScoringSubjectReport;
import org.abc.data.entity.Course;
import org.abc.data.entity.Subject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRepository extends CrudRepository<Subject, Integer> {

    Subject findSubjectById(Integer id);

    List<Subject> findSubjectsByCourse(Course course);

//    @Query(value = "SELECT subject_id, subject_name, abc_portal.student.course_id as course_id, AVG(marks) as average_marks " +
//                   "FROM ((abc_portal.student " +
//                   "INNER JOIN abc_portal.marks ON abc_portal.student.id = abc_portal.marks.student_id) " +
//                   "INNER JOIN abc_portal.subject ON abc_portal.marks.subject_id = abc_portal.subject.id) " +
//                   "WHERE batch = ?1 AND abc_portal.student.course_id = ?2 " +
//                   "GROUP BY subject_id "+
//                   "ORDER BY average_marks DESC",
//                    nativeQuery = true)
    public List<ScoringSubjectReport> getScoringSubjectReport(@Param("batch") String batch,@Param("courseId") int courseId);


}
