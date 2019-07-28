/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.and.singleton.implementation.faculty;

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
public class FacultyDAOFileImplementation implements FacultyDAO {
    List<Faculty> facultyList;

    @Override
    public Faculty create(Faculty faculty) {
        facultyList = read();
        facultyList.add(faculty);
        write();
        return retrieve(faculty.getInitials());
    }

    @Override
    public Faculty retrieve(String initials) {
        List<Faculty> result = null;
        facultyList = read();
        result = facultyList.stream().filter(faculty -> faculty.getInitials().equals(initials)).collect(Collectors.toList());
        return result.get(0);
    }

    @Override
    public List<Faculty> retrieve() {
        return read();
    }

    @Override
    public List<Faculty> retrieve(Predicate<Faculty> predicate) {
        return retrieve().stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public Faculty update(String initials, Faculty faculty) {
        facultyList = read();
        int index = -1;
        for (int i = 0; i < facultyList.size(); i++) {
            if (facultyList.get(i).getInitials().equals(initials)) {
                index = i;
            }
        }
        facultyList.remove(index);
        facultyList.add(faculty);
        write();
        return faculty;
    }

    @Override
    public boolean delete(String initials) {
        facultyList = read();
        int index = -1;
        for (int i = 0; i < facultyList.size(); i++) {
            if (facultyList.get(i).getInitials().equals(initials)) {
                index = i;
            }
        }
        if (index != -1) {
            facultyList.remove(index);
            write();
            return true;
        }
        return false;
    }

    private List<Faculty> read() {
        List<Faculty> facultyList = null;
        try {
            //        RandomAccessFile input = new RandomAccessFile("input.txt", "r");
            facultyList = Files.lines(Paths.get("faculty.txt")).map(line -> {
                String[] s = line.split(",");
                Faculty fl = new Faculty(s[0], s[1],s[2]);
                return fl;
            }).collect(Collectors.toList());
//            facultyList.stream().forEach(System.out :: println);
        } catch (IOException ex) {
            Logger.getLogger(FacultyDAOFileImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return facultyList;
    }

    private void write() {
        try {
            RandomAccessFile output = new RandomAccessFile("faculty.txt", "rw");
            output.setLength(0);
//            facultyList.stream().forEach(System.out :: println);
            facultyList.stream().map(faculty -> {
                String line = faculty.getInitials()+ "," + faculty.getName() + "," + faculty.getRank() + "\n";
                return line;
            }).forEach(line -> {
                try {
                    output.writeBytes(line);
                } catch (IOException ex) {
                    Logger.getLogger(FacultyDAOFileImplementation.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FacultyDAOFileImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FacultyDAOFileImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void delete(){
        try {
            RandomAccessFile output = new RandomAccessFile("faculty.txt", "rw");
            output.setLength(0);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FacultyDAOFileImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FacultyDAOFileImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
