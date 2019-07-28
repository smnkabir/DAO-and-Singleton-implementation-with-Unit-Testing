/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.and.singleton.implementation.faculty;

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
public class FacultyDAOFileImplementationTest {
    
    public FacultyDAOFileImplementationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        FacultyDAOFileImplementation instance = new FacultyDAOFileImplementation();
        instance.delete();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class FacultyDAOFileImplementation.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Faculty faculty = new Faculty("nk","Nk","lecturer");
        FacultyDAOFileImplementation instance = new FacultyDAOFileImplementation();
        Faculty expResult = faculty;
        Faculty result = instance.create(faculty);
        assertEquals(expResult, result);
    }

    /**
     * Test of retrieve method, of class FacultyDAOFileImplementation.
     */
    @Test
    public void testRetrieve_String() {
        System.out.println("retrieve");
        String initials = "nk";
        FacultyDAOFileImplementation instance = new FacultyDAOFileImplementation();
        Faculty expResult = new Faculty("nk","Nk","lecturer");
        instance.create(expResult);
        Faculty result = instance.retrieve(initials);
        assertEquals(expResult, result);
    }

    /**
     * Test of retrieve method, of class FacultyDAOFileImplementation.
     */
    @Test
    public void testRetrieve_0args() {
        System.out.println("retrieve");
        FacultyDAOFileImplementation instance = new FacultyDAOFileImplementation();
        List<Faculty> expResult = new ArrayList<>();
        for(int i=0 ; i<5 ; i++)
        {
            Faculty faculty = new Faculty(i+"a","Nk","lecturer");
            expResult.add(faculty);
            instance.create(faculty);
        }
        
        List<Faculty> result = instance.retrieve();
        assertEquals(expResult.size(), result.size());
        for(int i=0 ; i<result.size() ; i++)
            assertEquals(expResult.get(i), result.get(i));
        
    }

    /**
     * Test of retrieve method, of class FacultyDAOFileImplementation.
     */
    @Test
    public void testRetrieve_Predicate() {
        System.out.println("retrieve");
        Predicate<Faculty> predicate = faculty -> faculty.getName().startsWith("N");
        List<Faculty> facultyList = new ArrayList<>();
        FacultyDAOFileImplementation instance = new FacultyDAOFileImplementation();
        for(int i=0 ; i<5 ; i++){
            Faculty faculty = new Faculty("nk",('N'+i)+"ka","lecturer");
            facultyList.add(faculty);
            instance.create(faculty);
        }
        List<Faculty> expResult = facultyList.stream().filter(predicate).collect(Collectors.toList());
        List<Faculty> result = instance.retrieve(predicate);
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class FacultyDAOFileImplementation.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        String initials = "nk";
        Faculty faculty = new Faculty("nk","Nk","lecturer");
        FacultyDAOFileImplementation instance = new FacultyDAOFileImplementation();
        instance.create(faculty);
        faculty = new Faculty("nk","Jk","lecturer");
        Faculty expResult = faculty;
        Faculty result = instance.update(initials, faculty);
        assertEquals(expResult, result);
    }

    /**
     * Test of delete method, of class FacultyDAOFileImplementation.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        String initials = "nk";
        Faculty faculty = new Faculty("nk","Nk","lecturer");
       
        FacultyDAOFileImplementation instance = new FacultyDAOFileImplementation();
//        instance.create(faculty);
        boolean expResult = false;
        boolean result = instance.delete(initials);
        assertEquals(expResult, result);
    }
    
}
