package cn.lonesome.sms.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.lonesome.sms.model.dto.AjaxResp;
import cn.lonesome.sms.model.dto.ChangePasswordDto;
import cn.lonesome.sms.model.dto.ChangePasswordWithoutLoginDto;
import cn.lonesome.sms.model.entity.Course;
import cn.lonesome.sms.model.entity.Student;
import cn.lonesome.sms.service.CourseScheduleServer;
import cn.lonesome.sms.service.CourseService;
import cn.lonesome.sms.service.ScoreService;
import cn.lonesome.sms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Yale
 * @version 1.0
 */
@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {
    final StudentService studentService;
    final CourseService courseService;
    final ScoreService scoreService;
    final CourseScheduleServer courseScheduleServer;

    @PostMapping("/login")
    public Object login(Long id, String password) {
        Student student = studentService.login(id, password);
        StpUtil.logout(student.getId());
        StpUtil.login(student.getId());
        return AjaxResp.success();
    }


    @PutMapping("/password")
    public Object changePassword(@RequestBody ChangePasswordDto dto) {
        studentService.updatePassword(StpUtil.getLoginIdAsLong(), dto.getOldPassword(), dto.getNewPassword(), false);
        return AjaxResp.success();
    }
    @PutMapping("/passwordWithoutLogin")
    public Object changePassword_without_login(@RequestBody ChangePasswordWithoutLoginDto dto) {
        int userName=dto.getAccount();

        // 修改密码
        studentService.updatePasswordWithoutLogin(userName, dto.getNewPassword());

        return AjaxResp.success("密码修改成功");
    }




    @GetMapping("/course")
    public Object getCourse(int page, int size, String term) {
        return AjaxResp.success(courseService.getStudentCourseList(page, size, StpUtil.getLoginIdAsLong(), term));
    }

    @GetMapping("/score")
    public Object getScore(int page, int size, String term) {
        return AjaxResp.success(scoreService.getScore(page, size, term, "student", String.valueOf(StpUtil.getLoginIdAsLong())));
    }

    @GetMapping("/class_table")
    public Object getClassTable(String term, int page, int size) {
        return AjaxResp.success(courseScheduleServer.getCourseScheduleByClass(StpUtil.getLoginIdAsLong(), term, page, size));
    }

    @GetMapping("/all_score")
    public Object getAllScore() {
        return AjaxResp.success(scoreService.getAvgScoreByStudentId(StpUtil.getLoginIdAsLong()));
    }

    //获取学生自己的考试信息
    @GetMapping("/exams_stu")
    public Object getStudentExamCourses() {
        List<Course> examCourses = courseService.getStudentExamList(StpUtil.getLoginIdAsLong());
        return AjaxResp.success(examCourses);
    }
    @GetMapping("/stu_info")
    public Object get_stu_info(){
        return AjaxResp.success(studentService.get_stu_info(StpUtil.getLoginIdAsLong()));
    }


}
