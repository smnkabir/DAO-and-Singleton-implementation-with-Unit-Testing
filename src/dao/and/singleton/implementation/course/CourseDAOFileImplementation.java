/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.and.singleton.implementation.course;

import dao.and.singleton.implementation.course.Course;
import dao.and.singleton.implementation.course.CourseDAOFileImplementation;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author nk
 */
public class CourseDAOFileImplementation implements CourseDAO{
     List<Course> courseList;

    @Override
    public Course create(Course course) {
        courseList = read();
        courseList.add(course);
        write();
        return null;
    }

    @Override
    public Course retrieve(String code) {
        List<Course> result = null;
        courseList = read();
        result = courseList.stream().filter(course -> course.getCode().equals(code)).collect(Collectors.toList());
        return result.get(0);
    }

    @Override
    public List<Course> retrieve() {
        return read();
    }

    @Override
    public List<Course> retrieve(Predicate<Course> predicate) {
        return retrieve().stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public Course update(String code, Course course) {
        courseList = read();
        int index = -1;
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCode().equals(code)) {
                index = i;
            }
        }
        courseList.remove(index);
        courseList.add(course);
        write();
        return course;
    }

    @Override
    public boolean delete(String code) {
        courseList = read();
        int index = -1;
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCode().equals(code)) {
                index = i;
            }
        }
        if (index != -1) {
            courseList.remove(index);
            write();
            return true;
        }
        return false;
    }

    private List<Course> read() {
        List<Course> courseList = null;
        try {
            //        RandomAccessFile input = new RandomAccessFile("input.txt", "r");
            courseList = Files.lines(Paths.get("course.txt")).map(line -> {
                String[] s = line.split(",");
                Course c = new Course(s[0], s[1],Double.parseDouble(s[2]));
                return c;
            }).collect(Collectors.toList());
//            courseList.stream().forEach(System.out :: println);
        } catch (IOException ex) {
            Logger.getLogger(CourseDAOFileImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courseList;
    }

    private void write() {
        try {
            RandomAccessFile output = new RandomAccessFile("course.txt", "rw");
            output.setLength(0);
//            courseList.stream().forEach(System.out :: println);
            courseList.stream().map(course -> {
                String line = course.getCode()+ "," + course.getTitle() + "," + course.getCredit() + "\n";
                return line;
            }).forEach(line -> {
                try {
                    output.writeBytes(line);
                } catch (IOException ex) {
                    Logger.getLogger(CourseDAOFileImplementation.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CourseDAOFileImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CourseDAOFileImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
