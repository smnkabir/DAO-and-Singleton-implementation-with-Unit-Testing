/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.and.singleton.implementation.student;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.RandomAccess;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author nk
 */
public class StudentDAOFileImplementation implements StudentDAO {

    List<Student> studentList;

    @Override
    public Student create(Student student) {
        studentList = read();
        studentList.add(student);
        write();
        return retrieve(student.getId());
    }

    @Override
    public Student retrieve(String studentId) {
        List<Student> result = null;
        studentList = read();
        result = studentList.stream().filter(student -> student.getId().equals(studentId)).collect(Collectors.toList());
        return result.get(0);
    }

    @Override
    public List<Student> retrieve() {
        studentList = read();
        return studentList;
    }

    @Override
    public List<Student> retrieve(Predicate<Student> predicate) {
        return retrieve().stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public Student update(String studentId, Student student) {
        studentList = read();
        int index = -1;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId().equals(studentId)) {
                index = i;
            }
        }
        studentList.remove(index);
        studentList.add(student);
        write();
        return student;
    }

    @Override
    public boolean delete(String studentId) {
        studentList = read();
        int index = -1;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId().equals(studentId)) {
                index = i;
            }
        }
        if (index != -1) {
            studentList.remove(index);
            write();
            return true;
        }
        return false;
    }

    private List<Student> read() {
        List<Student> studentList = null;
        try {
            //        RandomAccessFile input = new RandomAccessFile("input.txt", "r");
            studentList = Files.lines(Paths.get("student.txt")).map(line -> {
                String[] s = line.split(",");
                Student st = new Student(s[0], s[1]);
                return st;
            }).collect(Collectors.toList());
//            studentList.stream().forEach(System.out :: println);
        } catch (IOException ex) {
            Logger.getLogger(StudentDAOFileImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return studentList;
    }

    private void write() {
        try {
            RandomAccessFile output = new RandomAccessFile("student.txt", "rw");
            output.setLength(0);
            studentList.stream().forEach(System.out :: println);
            studentList.stream().map(student -> {
                String line = student.getId() + "," + student.getName() + "\n";
                return line;
            }).forEach(line -> {
                try {
                    output.writeBytes(line);
                } catch (IOException ex) {
                    Logger.getLogger(StudentDAOFileImplementation.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentDAOFileImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StudentDAOFileImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void delete(){
        try {
            RandomAccessFile output = new RandomAccessFile("student.txt", "rw");
            output.setLength(0);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentDAOFileImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StudentDAOFileImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
