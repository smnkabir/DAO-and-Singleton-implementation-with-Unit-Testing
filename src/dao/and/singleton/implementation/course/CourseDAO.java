/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.and.singleton.implementation.course;

import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author nk
 */
public interface CourseDAO {
    public Course create(Course course);

    public Course retrieve(String code);

    public List<Course> retrieve();

    public List<Course> retrieve(Predicate<Course> predicate);

    public Course update(String code, Course course);

    public boolean delete(String code);
}
