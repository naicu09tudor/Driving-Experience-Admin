package com.drivingexperience.admin.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Student {

    private Long studentId;
    private String firstName;
    private String lastName;
    private String level;

    private Set<DrivingCourse> drivingCourses = new HashSet<>();

    private User user;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId) && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(level, student.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, firstName, lastName, level);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", level='" + level + '\'' +
                '}';
    }

    public Student() {
    }

    public Student(String firstName, String lastName, String level, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.level = level;
        this.user = user;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Set<DrivingCourse> getDrivingCourses() {
        return drivingCourses;
    }

    public void setDrivingCourses(Set<DrivingCourse> drivingCourses) {
        this.drivingCourses = drivingCourses;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
