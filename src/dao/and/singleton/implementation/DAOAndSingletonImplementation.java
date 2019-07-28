/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.and.singleton.implementation;

import dao.and.singleton.implementation.course.Course;
import dao.and.singleton.implementation.course.CourseDAO;
import dao.and.singleton.implementation.course.CourseDAOMysqlImplementation;
import dao.and.singleton.implementation.student.Student;
import dao.and.singleton.implementation.student.StudentDAO;
import dao.and.singleton.implementation.student.StudentDAOFileImplementation;
import dao.and.singleton.implementation.student.StudentDAOMysqlImplementation;
import dao.and.singleton.implementation.faculty.FacultyDAOFileImplementation;
import dao.and.singleton.implementation.faculty.FacultyDAO;
import dao.and.singleton.implementation.faculty.FacultyDAOMysqlImplementation;
import java.sql.Connection;
import java.util.List;
import java.util.Random;

/**
 *
 * @author nk
 */
public class DAOAndSingletonImplementation {

    public DAOAndSingletonImplementation() {
//        Student student = null;
        /**
         * * Database Part **
         */
//        StudentDAO daoObj = new StudentDAOMysqlImplementation();

//        student = daoObj.create(new Student("2016000000096","Nazmul Kabir"));
//        System.out.println(student);
//        student = daoObj.retrieve("2016000000096");
//        System.out.println(student);
//        
//        List<Student> facultyList = daoObj.retrieve();
//        facultyList.stream().forEach(System.out :: println);
//        
//        student = daoObj.update("2016000000096",new Student("2016000000096","Nazmul Kabir"));
//        System.out.println(student);
//        System.out.println(daoObj.delete("2016000000091"));
        
//        Random rand = new Random();
//        for(int i=0;i<100000;i++){
//            daoObj.create(new Student(rand.nextInt(1000000000)+ "" ,"a"));
//        }
        
        /**
         * * End **
         */
        /**
         * * File IO **
         */
//        daoObj = new StudentDAOFileImplementation();

//        daoObj.create(new Student("2016000000096","Nazmul Kabir"));
//        student = daoObj.retrieve("2016000000096");
//        System.out.println(student);
//        daoObj.retrieve().stream().forEach(System.out::println);
        
//        System.out.println(daoObj.delete("2016000000096"));
        
//        System.out.println(System.getProperty("user.dir"));
        
        
        //** Faculty Mysql implementation **//
        
//        FacultyDAO obj = new FacultyDAOMysqlImplementation();
//        obj.create(new Faculty ("nk","Nazmul Kabir","Lecturer"));
        
//        System.out.println(obj.retrieve("nk"));
        
        //** Faculty File Implementation **//
        
//        obj = new FacultyDAOFileImplementation();
//        obj.create(new Faculty ("nk","Nazmul Kabir","Lecturer"));
//        obj.delete("nk");
        
        //** Course mysql implementaiton **//
        
//        CourseDAO courseDAO = new CourseDAOMysqlImplementation();
//        courseDAO.create(new Course("CSE4047","Advance Java",3));

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new DAOAndSingletonImplementation();
    }

}
