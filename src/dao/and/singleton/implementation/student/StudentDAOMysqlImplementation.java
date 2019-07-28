/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.and.singleton.implementation.student;

import dao.and.singleton.implementation.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author nk
 */
public class StudentDAOMysqlImplementation implements StudentDAO {

    // private Connection connection = DBConnection.getConnection();

    private Statement statement = DBConnection.getStatement();
    private String query;
    private Student student = null;

    @Override
    public Student create(Student student) {
        try {
            query = "INSERT INTO student VALUES('" + student.getId() + "','" + student.getName() + "');";
            statement.executeUpdate(query);
            student = retrieve(student.getId());
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOMysqlImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return student;
    }

    @Override
    public Student retrieve(String studentId) {
        Student student = null;
        try {
            query = "SELECT * FROM student WHERE id = '" + studentId + "';";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                student = new Student(result.getString("id"), result.getString("name"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOMysqlImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return student;
    }

    @Override
    public List<Student> retrieve() {
        List<Student> studentList = new ArrayList();
        try {
            query = "SELECT * FROM student;";
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                student = new Student(result.getString("id"), result.getString("name"));
                studentList.add(student);
            }

        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOMysqlImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return studentList;
    }

    @Override
    public List<Student> retrieve(Predicate<Student> predicate) {
        return retrieve().stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public Student update(String studentId, Student student) {
        if (studentId.equals(student.getId())) {
            try {
                query = "UPDATE student SET name = '" + student.getName() + "' where id = '" + studentId + "';";
                statement.executeUpdate(query);
                student = retrieve(studentId);
            } catch (SQLException ex) {
                Logger.getLogger(StudentDAOMysqlImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return student;
    }

    @Override
    public boolean delete(String studentId) {
        int executeUpdate = 0;
        try {
            query = "DELETE FROM student WHERE id = '" + studentId + "'";
            executeUpdate = statement.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOMysqlImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (executeUpdate > 0) {
            return true;
        }
        return false;

    }
    public boolean delete() {
        int executeUpdate = 0;
        try {
            query = "DELETE FROM student;";
            executeUpdate = statement.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOMysqlImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (executeUpdate > 0) {
            return true;
        }
        return false;

    }
}
