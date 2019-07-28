/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.and.singleton.implementation.faculty;

import dao.and.singleton.implementation.DBConnection;
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
public class FacultyDAOMysqlImplementation implements FacultyDAO{

    private Statement statement = DBConnection.getStatement();
    private String query;
    private Faculty faculty = null;

    @Override
    public Faculty create(Faculty faculty) {
        try {
            query = "INSERT INTO faculty VALUES('" + faculty.getInitials()+ "','" + faculty.getName() + "','" + faculty.getRank()+ "');";
            statement.executeUpdate(query);
            faculty = retrieve(faculty.getInitials());
        } catch (SQLException ex) {
            Logger.getLogger(FacultyDAOMysqlImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return faculty;
    }

    @Override
    public Faculty retrieve(String initials) {
        Faculty faculty = null;
        try {
            query = "SELECT * FROM faculty WHERE initials = '" + initials + "';";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                faculty = new Faculty(result.getString("initials"), result.getString("name"),result.getString("rank"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(FacultyDAOMysqlImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return faculty;
    }

    @Override
    public List<Faculty> retrieve() {
        List<Faculty> facultyList = new ArrayList();
        try {
            query = "SELECT * FROM faculty;";
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                faculty = new Faculty(result.getString("initials"), result.getString("name"),result.getString("rank"));
                facultyList.add(faculty);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FacultyDAOMysqlImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return facultyList;
    }

    @Override
    public List<Faculty> retrieve(Predicate<Faculty> predicate) {
        return retrieve().stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public Faculty update(String initials, Faculty faculty) {
        if (initials.equals(faculty.getInitials())) {
            try {
                query = "UPDATE faculty SET name = '" + faculty.getName() + "' where initials = '" + initials + "';";
                statement.executeUpdate(query);
                faculty = retrieve(initials);
            } catch (SQLException ex) {
                Logger.getLogger(FacultyDAOMysqlImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return faculty;
    }

    @Override
    public boolean delete(String initials) {
       int executeUpdate = 0;
        try {
            query = "DELETE FROM faculty WHERE id = '" + initials + "'";
            executeUpdate = statement.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(FacultyDAOMysqlImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (executeUpdate > 0) {
            return true;
        }
        return false;

    }
    public void delete(){
        try {
            query = "DELETE FROM faculty;";
            statement.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(FacultyDAOMysqlImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
