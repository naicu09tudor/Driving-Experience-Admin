package com.drivingexperience.admin.web;

import com.drivingexperience.admin.dto.DrivingCourseDTO;
import com.drivingexperience.admin.service.DrivingCourseService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drivingcourses")
@CrossOrigin("*")
public class DrivingCourseController {

    private DrivingCourseService drivingCourseService;

    public DrivingCourseController(DrivingCourseService drivingCourseService) {
        this.drivingCourseService = drivingCourseService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('Admin')")
    public Page<DrivingCourseDTO> searchDrivingCourses(@RequestParam(name = "keyword", defaultValue = "") String keyword,
                                                       @RequestParam(name = "page", defaultValue = "0") int page,
                                                       @RequestParam(name = "size", defaultValue = "5") int size) {

        return drivingCourseService.findDrivingCoursesByDrivingCoursesName(keyword, page, size);

    }

    @DeleteMapping("/{courseId}")
    @PreAuthorize("hasAuthority('Admin')")
    public void deleteDrivingCourse(@PathVariable Long courseId){
        drivingCourseService.removeDrivingCourse(courseId);
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('Admin', 'Instructor')")
    public DrivingCourseDTO saveDrivingCourse(@RequestBody DrivingCourseDTO drivingCourseDTO)
    {
        return drivingCourseService.createDrivingCourse(drivingCourseDTO);
    }

    @PutMapping("/{courseId}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Instructor')")
    public DrivingCourseDTO updateCourse(@RequestBody DrivingCourseDTO drivingCourseDTO, @PathVariable Long courseId) {
        drivingCourseDTO.setCourseId(courseId);
        return drivingCourseService.updateDrivingCourse(drivingCourseDTO);
    }

    @PostMapping("/{courseId}/enroll/students/{studentId}")
    @PreAuthorize("hasAuthority('Student')")
    public void enrollStudentInCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        drivingCourseService.assignStudentToDrivingCourse(courseId, studentId);
    }


}
