package com.drivingexperience.admin.web;


import com.drivingexperience.admin.dto.DrivingCourseDTO;
import com.drivingexperience.admin.dto.InstructorDTO;
import com.drivingexperience.admin.entity.User;
import com.drivingexperience.admin.service.DrivingCourseService;
import com.drivingexperience.admin.service.InstructorService;
import com.drivingexperience.admin.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructors")
public class InstructorRestController {

    private InstructorService instructorService;

    private UserService userService;

    private DrivingCourseService drivingCourseService;

    public InstructorRestController(InstructorService instructorService, UserService userService, DrivingCourseService drivingCourseService) {
        this.instructorService = instructorService;
        this.userService = userService;
        this.drivingCourseService = drivingCourseService;
    }

    @GetMapping
    public Page<InstructorDTO> searchInstructors(@RequestParam(name = "keyword", defaultValue = "") String keyword,
                                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                                 @RequestParam(name = "size", defaultValue = "5") int size) {
        return instructorService.findInstructorsByName(keyword, page, size);
    }

    @GetMapping("/all")
    public List<InstructorDTO> findAllInstructors() {
        return instructorService.fetchInstructors();
    }

    @DeleteMapping("/{instructorId}")
    public void deleteInstructor(@PathVariable Long instructorId) {
        instructorService.removeInstructor(instructorId);
    }

    @PostMapping
    public InstructorDTO saveInstructor(@RequestBody InstructorDTO instructorDTO) {
        User user = userService.loadUserByEmail(instructorDTO.getUser().getEmail());
        if (user != null) throw new RuntimeException("Email Already Exist");
        return instructorService.createInstructor(instructorDTO);
    }

    @PutMapping("/{instructorId}")
    public InstructorDTO updateInstructor(@RequestBody InstructorDTO instructorDTO, @PathVariable Long instructorId) {
        instructorDTO.setInstructorId(instructorId);
        return instructorService.updateInstructor(instructorDTO);
    }

    @GetMapping("/{instructorId}/courses")
    public Page<DrivingCourseDTO> drivingCoursesByInstructorId(@PathVariable Long instructorId,
                                                                @RequestParam(name = "page", defaultValue = "0") int page,
                                                                @RequestParam(name = "size", defaultValue = "5") int size) {
        return drivingCourseService.fetchDrivingCoursesForInstructor(instructorId, page, size);
    }

    @GetMapping("/find")
    public InstructorDTO loadInstructorByEmail(@RequestParam(name = "email", defaultValue = "") String email) {
        return instructorService.loadInstructorByEmail(email);
    }

}
