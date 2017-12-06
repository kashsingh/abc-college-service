package org.abc.services;

import org.abc.data.entity.Course;
import org.abc.data.entity.Marks;
import org.abc.data.entity.Subject;
import org.abc.data.repository.MarksRepository;
import org.abc.data.repository.SubjectRepository;
import org.abc.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Nonnull
    private SubjectRepository subjectRepository;

    @Nonnull
    private MarksRepository marksRepository;

    @Autowired
    public void setMarksRepository(@Nonnull MarksRepository marksRepository) {
        this.marksRepository = marksRepository;
    }

    @Autowired
    public void setSubjectRepository(@Nonnull SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void createSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    public void updateSubject(Subject subject) throws NotFoundException {
        Subject existingSubject = subjectRepository.findSubjectById(subject.getId());
        if (existingSubject == null) {
            throw new NotFoundException("Subject not found!!");
        } else {
            subjectRepository.save(subject);
        }
    }

    @Override
    public void deleteSubject(Subject subject) throws NotFoundException {
        if (subjectRepository.findSubjectById(subject.getId()) == null) {
            throw new NotFoundException("Subject not found!!");
        } else {
            List<Marks> subjectMarks = marksRepository.findMarksBySubjectId(subject.getId());
            marksRepository.delete(subjectMarks);
            subjectRepository.delete(subject);
        }
    }

    @Override
    @Nonnull
    public Subject getSubject(int subjectId) throws NotFoundException {
        Subject subject = subjectRepository.findSubjectById(subjectId);
        if (subject == null) {
            throw new NotFoundException(String.format("Student with id %s not found", subjectId));
        }
        return subject;
    }

    @Nonnull
    @Override
    public List<Subject> viewCourseSubjects(Course course) throws NotFoundException {
        return subjectRepository.findSubjectsByCourse(course);
    }
}
