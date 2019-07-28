/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.and.singleton.implementation.faculty;

import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author nk
 */
public interface FacultyDAO {
    public Faculty create(Faculty faculty);

    public Faculty retrieve(String initials);

    public List<Faculty> retrieve();

    public List<Faculty> retrieve(Predicate<Faculty> predicate);

    public Faculty update(String initials, Faculty faculty);

    public boolean delete(String initials);
}
