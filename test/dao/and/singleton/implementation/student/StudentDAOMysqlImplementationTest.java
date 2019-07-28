/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.and.singleton.implementation.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nk
 */
public class StudentDAOMysqlImplementationTest {
    
    public StudentDAOMysqlImplementationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
       StudentDAOMysqlImplementation instance = new StudentDAOMysqlImplementation();
       instance.delete();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class StudentDAOMysqlImplementation.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Random rand = new Random();
        Student student = new Student(rand.nextInt()+"","A");
        StudentDAOMysqlImplementation instance = new StudentDAOMysqlImplementation();
        Student expResult = student;
        Student result = instance.create(student);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of retrieve method, of class StudentDAOMysqlImplementation.
     */
    @Test
    public void testRetrieve_String() {
        System.out.println("retrieve");
        Random rand = new Random();
        Student student = new Student(rand.nextInt()+"","A");
        String studentId = student.getId();
        StudentDAOMysqlImplementation instance = new StudentDAOMysqlImplementation();
        Student expResult = instance.create(student);
        Student result = instance.retrieve(studentId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of retrieve method, of class StudentDAOMysqlImplementation.
     */
    @Test
    public void testRetrieve_0args() {
        System.out.println("retrieve");
        StudentDAOMysqlImplementation instance = new StudentDAOMysqlImplementation();
        List<Student> studentList = new ArrayList<>();
        for(int i=0;i<5;i++){
            Student student = new Student(i+"","A");
            studentList.add(student);
            instance.create(student);
        }
        List<Student> expResult = studentList;
        List<Student> result = instance.retrieve();
        for(int i=0;i<5;i++)
            assertEquals(expResult.get(i), result.get(i));
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of retrieve method, of class StudentDAOMysqlImplementation.
     */
    @Test
    public void testRetrieve_Predicate() {
        System.out.println("retrieve");
        Predicate<Student> predicate = (student -> student.getId().startsWith("2"));
        StudentDAOMysqlImplementation instance = new StudentDAOMysqlImplementation();
        
        List<Student> studentList = new ArrayList<>();
        for(int i=0;i<5;i++){
            Student student = new Student(i+"","A");
            studentList.add(student);
            instance.create(student);
        }
        
        List<Student> expResult = studentList.stream().filter(predicate).collect(Collectors.toList());
        List<Student> result = instance.retrieve(predicate);
        
        assertEquals(expResult.size(), result.size());
        
        for(int i=0;i<expResult.size();i++)
            assertEquals(expResult.get(i), result.get(i));
        
    }

    /**
     * Test of update method, of class StudentDAOMysqlImplementation.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        String studentId = "123";
        Student student = new Student(studentId,"A");
        StudentDAOMysqlImplementation instance = new StudentDAOMysqlImplementation();
        instance.create(student);
        student = new Student(studentId,"B");
        Student expResult = student ;
        Student result = instance.update(studentId, student);
        assertEquals(expResult, result);
    }

    /**
     * Test of delete method, of class StudentDAOMysqlImplementation.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        String studentId = "123";
        Student student = new Student(studentId,"aa");
        StudentDAOMysqlImplementation instance = new StudentDAOMysqlImplementation();
        instance.create(student);
        boolean expResult = true;
        boolean result = instance.delete(studentId);
        assertEquals(expResult, result);
    }
    
}
