/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.and.singleton.implementation.course;

import dao.and.singleton.implementation.DBConnection;
import dao.and.singleton.implementation.course.Course;
import dao.and.singleton.implementation.course.CourseDAOMysqlImplementation;
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
public class CourseDAOMysqlImplementation implements CourseDAO {
    private Statement statement = DBConnection.getStatement();
    private String query;
    private Course course = null;

    @Override
    public Course create(Course course) {
        try {
            query = "INSERT INTO course VALUES('" + course.getCode()+ "','" + course.getTitle() + "','" + course.getCredit()+ "');";
            statement.executeUpdate(query);
            course = retrieve(course.getCode());
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAOMysqlImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return course;
    }

    @Override
    public Course retrieve(String code) {
        Course course = null;
        try {
            query = "SELECT * FROM course WHERE code = '" + code + "';";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                course = new Course(result.getString("code"), result.getString("title"),Double.parseDouble(result.getString("credit")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CourseDAOMysqlImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return course;
    }

    @Override
    public List<Course> retrieve() {
        List<Course> courseList = new ArrayList();
        try {
            query = "SELECT * FROM course;";
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                course = new Course(result.getString("code"), result.getString("title"),Double.parseDouble(result.getString("credit")));
                courseList.add(course);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CourseDAOMysqlImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courseList;
    }

    @Override
    public List<Course> retrieve(Predicate<Course> predicate) {
        return retrieve().stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public Course update(String code, Course course) {
        if (code.equals(course.getCode())) {
            try {
                query = "UPDATE course SET title = '" + course.getTitle() + ", credit = '" + course.getCredit() +  "' where code = '" + code + "';";
                statement.executeUpdate(query);
                course = retrieve(code);
            } catch (SQLException ex) {
                Logger.getLogger(CourseDAOMysqlImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return course;
    }

    @Override
    public boolean delete(String code) {
        int executeUpdate = 0;
        try {
            query = "DELETE FROM course WHERE code = '" + code + "'";
            executeUpdate = statement.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(CourseDAOMysqlImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (executeUpdate > 0) {
            return true;
        }
        return false;

    }
}
