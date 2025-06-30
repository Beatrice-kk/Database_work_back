package cn.lonesome.sms.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.lonesome.sms.constant.ErrorCode;
import cn.lonesome.sms.exception.CustomException;
import cn.lonesome.sms.model.dto.*;
import cn.lonesome.sms.model.entity.Admin;
import cn.lonesome.sms.model.entity.College;
import cn.lonesome.sms.model.entity.Course;
import cn.lonesome.sms.model.entity.Student;
import cn.lonesome.sms.service.*;
import cn.lonesome.sms.utils.ExcelUtil;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Yale
 * @version 1.0
 */
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    final AdminService adminService;
    final CollegeService collegeService;
    final MajorService majorService;
    final TeacherService teacherService;
    final ClassService classService;
    final StudentService studentService;
    final CourseService courseService;
    final CourseScheduleServer courseScheduleService;
    final ScoreService scoreService;

    @PostMapping("/login")
    public Object login(@RequestParam("id") Integer id, @RequestParam("password") String password) {
        Admin admin = adminService.login(id, password);
        StpUtil.logout(admin.getId());
        StpUtil.login(admin.getId());
        String token = StpUtil.getTokenValue(); // 获取 token
        return AjaxResp.success(token); // 返回 token
    }

    @PutMapping("/password")
    public Object changePassword(@RequestBody ChangePasswordDto dto) {
        int userName = StpUtil.getLoginIdAsInt();
        System.out.println(dto.getOldPassword());
        adminService.changePassword(userName, dto.getOldPassword(), dto.getNewPassword());
        return AjaxResp.success();
    }


    @PutMapping("/passwordWithoutLogin")
    public Object changePassword_without_login(@RequestBody ChangePasswordWithoutLoginDto dto) {
        int userName=dto.getAccount();

        adminService.changePasswordWithoutLogin(userName, dto.getNewPassword());

        return AjaxResp.success("密码修改成功");
    }




    @PutMapping
    public Object updateAdmin(@RequestBody ChangeRoleDto dto) {
        int userName = StpUtil.getLoginIdAsInt();
        if (!adminService.getRole(userName).equals(Admin.Role.SUPER_ADMIN)) {
            throw new CustomException(ErrorCode.PERMISSION_DENIED);
        }
        adminService.updateRole(dto.getId(), dto.getRole());
        return AjaxResp.success();
    }

    @PostMapping
    public Object addAdmin(@RequestBody AdminDto admin) {
        adminService.insert(admin.getId(), admin.getPassword(), admin.getRole());
        return AjaxResp.success();
    }

    @DeleteMapping
    public Object deleteAdmin(@Param("id") int id) {
        adminService.delete(id);
        return AjaxResp.success();
    }

    @GetMapping("/college")
    public Object getCollege(@Param("page") int page, @Param("size") int size) {
        return AjaxResp.success(collegeService.getColleges(page, size));
    }

    @PostMapping("/college")
    public Object addCollege(@RequestBody College college) {
        int userName = StpUtil.getLoginIdAsInt();
        if (adminService.getRole(userName).equals(Admin.Role.COLLEGE_ADMIN)) {
            throw new CustomException(ErrorCode.PERMISSION_DENIED);
        }
        collegeService.addCollege(college.getId(), college.getName());
        return AjaxResp.success();
    }

    @DeleteMapping("/college")
    public Object deleteCollege(@Param("id") int id) {
        int userName = StpUtil.getLoginIdAsInt();
        if (adminService.getRole(userName).equals(Admin.Role.COLLEGE_ADMIN)) {
            throw new CustomException(ErrorCode.PERMISSION_DENIED);
        }
        collegeService.deleteCollege(id);
        return AjaxResp.success();
    }

    @PutMapping("/college")
    public Object updateCollege(@RequestBody College college) {
        System.out.println(StpUtil.isLogin());
        int userName = StpUtil.getLoginIdAsInt();
        if (adminService.getRole(userName).equals(Admin.Role.COLLEGE_ADMIN)) {
            throw new CustomException(ErrorCode.PERMISSION_DENIED);
        }
        collegeService.updateCollege(college.getId(), college.getName());
        return AjaxResp.success();
    }

    @PutMapping("/major")
    public Object updateMajor(@RequestBody MajorDto major) {
        majorService.updateMajor(major.getId(), major.getName(), major.getCollege());
        return AjaxResp.success();
    }

    @GetMapping("/major")
    public Object getMajor(@Param("page") int page, @Param("size") int size, @Param("type") String type, @Param("condition") String condition) {
        int userName = StpUtil.getLoginIdAsInt();
        if (adminService.getRole(userName).equals(Admin.Role.COLLEGE_ADMIN) && "all".equals(type)) {
            throw new CustomException(ErrorCode.PERMISSION_DENIED);
        }
        return AjaxResp.success(majorService.getMajors(page, size, type, condition));
    }

    @PostMapping("/major")
    public Object addMajor(@RequestBody MajorDto major) {
        majorService.addMajor(major.getId(), major.getName(), major.getCollege());
        return AjaxResp.success();
    }

    @DeleteMapping("/major")
    public Object deleteMajor(@Param("id") int id) {
        majorService.deleteMajor(id);
        return AjaxResp.success();
    }

    @GetMapping("/teacher")
    public Object getTeacher(@RequestParam(value = "page", required = false) Integer page,
                             @RequestParam(value = "size", required = false) Integer size,
                             @RequestParam(value = "type", required = false) String type,
                             @RequestParam(value = "condition", required = false) String condition) {
        int userName = StpUtil.getLoginIdAsInt();
        if (adminService.getRole(userName).equals(Admin.Role.COLLEGE_ADMIN) && "all".equals(type)) {
            throw new CustomException(ErrorCode.PERMISSION_DENIED);
        }
        return AjaxResp.success(teacherService.getTeachers(page, size, type, condition));
    }

    @GetMapping("/teacher/find")
    public Object getTeacherFind(@RequestParam(value = "page", required = false) Integer page,
                             @RequestParam(value = "size", required = false) Integer size,
                             @RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "gender", required = false) String gender,
                             @RequestParam(value = "college", required = false) String college,
                             @RequestParam(value = "jobTitle", required = false) String jobTitle) {

        return AjaxResp.success(teacherService.getTeachersfind(page, size, name, gender, college, jobTitle));
    }

    @PostMapping("/teacher")
    public Object addTeacher(@RequestBody TeacherDto teacherDto) {
        System.out.println(teacherDto);
        teacherService.addTeacher(teacherDto.getId(), teacherDto.getName(), teacherDto.getGender(),
                teacherDto.getBirthYear(), teacherDto.getCollege(), teacherDto.getPhoneNum(), teacherDto.getJobTitle());
        return AjaxResp.success();
    }

    @PostMapping("/teacher/file")
    public Object addTeacherByFile(@RequestParam("file") MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        if (!name.substring(name.length() - 5).equals(".xlsx")) {
            throw new CustomException(ErrorCode.FILE_ERROR);
        }
        List<TeacherDto> list = ExcelUtil.excelToTeacherList(file.getInputStream());
        for (TeacherDto teacherDto : list) {
            teacherService.addTeacher(teacherDto.getId(), teacherDto.getName(), teacherDto.getGender(),
                    teacherDto.getBirthYear(), teacherDto.getCollege(), teacherDto.getPhoneNum(), teacherDto.getJobTitle());
        }
        return AjaxResp.success();
    }

    @DeleteMapping("/teacher")
    public Object deleteTeacher(@Param("id") int id) {
        teacherService.deleteTeacher(id);
        return AjaxResp.success();
    }

    @PutMapping("/teacher")
    public Object updateTeacher(@RequestBody TeacherDto teacherDto) {
        teacherService.updateTeacher(teacherDto.getId(), teacherDto.getName(), teacherDto.getGender(),
                teacherDto.getBirthYear(), teacherDto.getCollege(), teacherDto.getPhoneNum(), teacherDto.getJobTitle());
        return AjaxResp.success();
    }

    @PutMapping("/teacher/password")
    public Object changeTeacherPassword(@RequestBody ResetPasswordDto dto) {
        teacherService.changePassword(dto.getId(), null, null, true);
        return AjaxResp.success();
    }

    @GetMapping("/class")
    public Object getClass(@Param("page") int page, @Param("size") int size, @Param("type") String type, @Param("condition") String condition) {
        int userName = StpUtil.getLoginIdAsInt();
        if (adminService.getRole(userName).equals(Admin.Role.COLLEGE_ADMIN) && "all".equals(type)) {
            throw new CustomException(ErrorCode.PERMISSION_DENIED);
        }
        return AjaxResp.success(classService.getClasses(page, size, type, condition));
    }

    @PostMapping("/class")
    public Object addClass(@RequestBody ClassDto classDto) {
        classService.addClass(classDto.getId(), classDto.getName(), classDto.getMajor(), classDto.getTeacherId());
        return AjaxResp.success();
    }

    @DeleteMapping("/class")
    public Object deleteClass(@Param("id") int id) {
        classService.deleteClass(id);
        return AjaxResp.success();
    }

    @PutMapping("/class")
    public Object updateClass(@RequestBody ClassDto classDto) {
        classService.updateClass(classDto.getId(), classDto.getName(), classDto.getMajor(), classDto.getTeacherId());
        return AjaxResp.success();
    }

    @GetMapping("/student")
    public Object getStudent(@Param("page") int page, @Param("size") int size, @Param("type") String type, @Param("condition") String condition) {
        int userName = StpUtil.getLoginIdAsInt();
        if (adminService.getRole(userName).equals(Admin.Role.COLLEGE_ADMIN) && "all".equals(type)) {
            throw new CustomException(ErrorCode.PERMISSION_DENIED);
        }
        return AjaxResp.success(studentService.getStudents(page, size, type, condition));
    }

    @GetMapping("/student/find")
    public Object getStudentFind(@RequestParam(value = "page", required = false) Integer page,
                                 @RequestParam(value = "size", required = false) Integer size,
                                 @RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "gender", required = false) String gender,
                                 @RequestParam(value = "className", required = false) String studentClass,
                                 @RequestParam(value = "hometown", required = false) String hometown) {
        return AjaxResp.success(studentService.getStudentsFind(page, size, name, gender, studentClass, hometown));
    }
    @GetMapping("/student/findById")
    public Object getStudentById(@RequestParam("id") Long studentId) {
        Student student = studentService.getStudentById(studentId);
        if (student != null) {
            return AjaxResp.success(student);
        } else {
            return 0;
//                    AjaxResp.error("未找到对应学号的学生信息");
        }
    }


    @PostMapping("/student")
    public Object addStudent(@RequestBody StudentDto studentDto) {
        studentService.addStudent(studentDto.getId(), studentDto.getName(), studentDto.getGender(),
                studentDto.getBirthYear(), studentDto.getHometown(), studentDto.getClassName());
        return AjaxResp.success();
    }

    @PostMapping("/student/file")
    public Object addStudentByFile(@RequestParam("file") MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        if (!name.substring(name.length() - 5).equals(".xlsx")) {
            throw new CustomException(ErrorCode.FILE_ERROR);
        }
        List<StudentDto> list = ExcelUtil.excelToStudentList(file.getInputStream());
        for (StudentDto studentDto : list) {
            studentService.addStudent(studentDto.getId(), studentDto.getName(), studentDto.getGender(),
                    studentDto.getBirthYear(), studentDto.getHometown(), studentDto.getClassName());
        }
        return AjaxResp.success();
    }

    @DeleteMapping("/student")
    public Object deleteStudent(@Param("id") long id) {
        studentService.deleteStudent(id);
        return AjaxResp.success();
    }

    @PutMapping("/student")
    public Object updateStudent(@RequestBody StudentDto studentDto) {
        studentService.updateStudent(studentDto.getId(), studentDto.getName(), studentDto.getGender(),
                studentDto.getBirthYear(), studentDto.getHometown(), studentDto.getClassName());
        return AjaxResp.success();
    }

    @PutMapping("/student/password")
    public Object changeStudentPassword(@RequestBody ResetPasswordDto dto) {
        studentService.updatePassword(dto.getId(), null, null, true);
        return AjaxResp.success();
    }

    @GetMapping("/student/hometown_count")
    public Object getStudentHometownCount() {
        return AjaxResp.success(studentService.getHometownCounts());
    }

    @GetMapping("/student/hometown")
    public Object getStudentByHometown(@Param("page") int page, @Param("size") int size, @Param("hometown") String hometown) {
        return AjaxResp.success(studentService.getStudentsByHometown(page, size, hometown));
    }

    @GetMapping("/course")
    public Object getCourse(@Param("page") int page, @Param("size") int size,
                            @Param("type") String type, @Param("condition") String condition) {
        int userName = StpUtil.getLoginIdAsInt();
        if (adminService.getRole(userName).equals(Admin.Role.COLLEGE_ADMIN) && "all".equals(type)) {
            throw new CustomException(ErrorCode.PERMISSION_DENIED);
        }
        return AjaxResp.success(courseService.getCourse(page, size, type, condition));
    }

    @PostMapping("/course")
    public Object addCourse(@RequestBody CourseDto courseDto) {
        courseService.addCourse(courseDto.getId(), courseDto.getName(), courseDto.getCollegeName(), courseDto.getTerm(),
                courseDto.getExaminationMethod(), courseDto.getCredit(), courseDto.getHours(), courseDto.getAttribute(), courseDto.getYear());
        return AjaxResp.success();
    }

    @PutMapping("/course")
    public Object updateCourse(@RequestBody CourseDto courseDto) {
        courseService.updateCourse(courseDto.getId(), courseDto.getName(), courseDto.getCollegeName(), courseDto.getTerm(),
                courseDto.getExaminationMethod(), courseDto.getCredit(), courseDto.getHours(), courseDto.getAttribute(),
                courseDto.getYear());
        return AjaxResp.success();
    }

    @DeleteMapping("/course")
    public Object deleteCourse(@Param("id") int id) {
        courseService.deleteCourse(id);
        return AjaxResp.success();
    }

    @GetMapping("/course/term")
    public Object getTermList() {
        return AjaxResp.success(courseService.getTermList());
    }

    @GetMapping("/course/attribute")
    public Object getAttributeList() {
        return AjaxResp.success(courseService.getAttributeList());
    }

    @GetMapping("/course/examination_method")
    public Object getExaminationMethodList() {
        return AjaxResp.success(courseService.getExaminationMethodList());
    }

    @GetMapping("/course_schedule")
    public Object getCourseSchedule(@Param("page") int page, @Param("size") int size,
                                    @Param("type") String type, @Param("condition") String condition) {
        int userName = StpUtil.getLoginIdAsInt();
        if (adminService.getRole(userName).equals(Admin.Role.COLLEGE_ADMIN) && "all".equals(type)) {
            throw new CustomException(ErrorCode.PERMISSION_DENIED);
        }
        return AjaxResp.success(courseScheduleService.getCourseSchedule(page, size, type, condition));
    }

    @PostMapping("/course_schedule")
    public Object addCourseSchedule(@RequestBody CourseScheduleDto courseScheduleDto) {
        courseScheduleService.addCourseSchedule(courseScheduleDto);
        return AjaxResp.success();
    }

    @PutMapping("/course_schedule")
    public Object updateCourseSchedule(@RequestBody CourseScheduleDto courseScheduleDto) {
        courseScheduleService.updateCourseSchedule(courseScheduleDto);
        return AjaxResp.success();
    }

    @PostMapping("/course_schedule/delete")
    public Object deleteCourseSchedule(@RequestBody CourseScheduleDto courseScheduleDto) {
        courseScheduleService.deleteCourseSchedule(courseScheduleDto.getCourseId(), courseScheduleDto.getClassName());
        return AjaxResp.success();
    }

    @GetMapping("/score")
    public Object getScore(@Param("page") int page, @Param("size") int size,
                           @Param("term") String term, @Param("type") String type, @Param("condition") String condition) {
        return AjaxResp.success(scoreService.getScore(page, size, term, type, condition));
    }

    @GetMapping("/score/all_year")
    public Object getAllYear(@Param("year") int year, @Param("type") String type, @Param("condition") String condition) {
        return AjaxResp.success(scoreService.getAllYearScore(year, type, condition));
    }

    @GetMapping("/score/ave")
    public Object getAveScore(@Param("page") int page, @Param("size") int size,
                              @Param("type") String type, @Param("condition") String condition) {
        return AjaxResp.success(scoreService.getAvgScore(page, size, type, condition));
    }

    @GetMapping("/exams")
    public Object getExamCourses() {
        List<Course> examCourses = courseService.getExamList();
        return AjaxResp.success(examCourses);
    }


    @GetMapping("/schoolInfo")
    public Object getStudentInfo() {
        return AjaxResp.success(adminService.getSchoolInfo());
    }


}
