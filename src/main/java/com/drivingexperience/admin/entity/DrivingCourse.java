package com.drivingexperience.admin.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity //defines this class as a database table
@Table(name = "DrivingCourses")
public class DrivingCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", nullable = false)
    private Long courseId;
    @Basic
    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Basic
    @Column(name = "course_difficulty", nullable = false, length = 50)
    private String courseDifficulty;

    @Basic
    @Column(name = "course_description", nullable = false)
    private String courseDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", referencedColumnName = "instructor_id", nullable = false)
    private Instructor instructor;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "enrolled_in",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private Set<Student> students = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrivingCourse that = (DrivingCourse) o;
        return Objects.equals(courseId, that.courseId) && Objects.equals(courseName, that.courseName) && Objects.equals(courseDifficulty, that.courseDifficulty) && Objects.equals(courseDescription, that.courseDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName, courseDifficulty, courseDescription);
    }

    public void assingStudentToCourse(Student student)
    {
        this.students.add(student);
        student.getDrivingCourses().add(this);
    }

    private void removeStudentFromCourse(Student student)
    {
        this.students.remove(student);
        student.getDrivingCourses().remove(this);
    }

    public DrivingCourse() {
    }

    public DrivingCourse(String courseName, String courseDifficulty, String courseDescription, Instructor instructor) {
        this.courseName = courseName;
        this.courseDifficulty = courseDifficulty;
        this.courseDescription = courseDescription;
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "DrivingCourse{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseDifficulty='" + courseDifficulty + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                '}';
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDifficulty() {
        return courseDifficulty;
    }

    public void setCourseDifficulty(String courseDifficulty) {
        this.courseDifficulty = courseDifficulty;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
