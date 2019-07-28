/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.and.singleton.implementation.student;

import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author nk
 */
public interface StudentDAO {

    public Student create(Student student);

    public Student retrieve(String studentId);

    public List<Student> retrieve();

    public List<Student> retrieve(Predicate<Student> predicate);

    public Student update(String studentId, Student student);

    public boolean delete(String studentId);

}
