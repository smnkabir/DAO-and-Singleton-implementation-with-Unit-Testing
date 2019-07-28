/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.and.singleton.implementation.student;

import java.util.ArrayList;
import java.util.List;
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
public class StudentDAOFileImplementationTest {

    public StudentDAOFileImplementationTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        StudentDAOFileImplementation instance = new StudentDAOFileImplementation();
        instance.delete();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class StudentDAOFileImplementation.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Student student = new Student("123", "Nk");
        StudentDAOFileImplementation instance = new StudentDAOFileImplementation();
        Student expResult = student;
        Student result = instance.create(student);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of retrieve method, of class StudentDAOFileImplementation.
     */
    @Test
    public void testRetrieve_String() {
        System.out.println("retrieve");
        String studentId = "123";
        Student student = new Student(studentId, "aa");
        StudentDAOFileImplementation instance = new StudentDAOFileImplementation();
        instance.create(student);
        Student expResult = student;
        Student result = instance.retrieve(studentId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of retrieve method, of class StudentDAOFileImplementation.
     */
    @Test
    public void testRetrieve_0args() {
        System.out.println("retrieve");
        StudentDAOFileImplementation instance = new StudentDAOFileImplementation();
        List<Student> expResult = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Student student = new Student(i + "", "Aa");
            expResult.add(student);
            instance.create(student);
        }
        List<Student> result = instance.retrieve();
        assertEquals(expResult.size(), result.size());

        for (int i = 0; i < result.size(); i++) {
            assertEquals(expResult.get(i), result.get(i));
        }
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of retrieve method, of class StudentDAOFileImplementation.
     */
    @Test
    public void testRetrieve_Predicate() {
        System.out.println("retrieve");
        Predicate<Student> predicate = student -> student.getName().startsWith("a");
        StudentDAOFileImplementation instance = new StudentDAOFileImplementation();
        List<Student> studentList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Student student = new Student(i + "", ('a' + i) + "a");
            studentList.add(student);
            instance.create(student);
        }
        List<Student> expResult = studentList.stream().filter(predicate).collect(Collectors.toList());
        List<Student> result = instance.retrieve(predicate);
        assertEquals(expResult.size(), result.size());

        for (int i = 0; i < result.size(); i++) {
            assertEquals(expResult.get(i), result.get(i));
        }
    // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of update method, of class StudentDAOFileImplementation.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        String studentId = "123";
        Student student = new Student(studentId, "Aa");
        StudentDAOFileImplementation instance = new StudentDAOFileImplementation();
        instance.create(student);
        student = new Student(studentId, "Bb");
        Student expResult = student;
        Student result = instance.update(studentId, student);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of delete method, of class StudentDAOFileImplementation.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        String studentId = "123";
        Student student = new Student(studentId, "Aa");

        StudentDAOFileImplementation instance = new StudentDAOFileImplementation();
        instance.create(student);
        
        boolean expResult = true;
        boolean result = instance.delete(studentId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

}
