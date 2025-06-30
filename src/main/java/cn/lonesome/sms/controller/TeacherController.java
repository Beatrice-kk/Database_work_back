package cn.lonesome.sms.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.lonesome.sms.model.dto.AjaxResp;
import cn.lonesome.sms.model.dto.ChangePasswordDto;
import cn.lonesome.sms.model.dto.ChangePasswordWithoutLoginDto;

import cn.lonesome.sms.model.entity.Course;
import cn.lonesome.sms.model.entity.Teacher;
import cn.lonesome.sms.service.CourseScheduleServer;
import cn.lonesome.sms.service.CourseService;
import cn.lonesome.sms.service.ScoreService;
import cn.lonesome.sms.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Yale
 * @version 1.0
 */
@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherController {
    final TeacherService teacherService;
    final ScoreService scoreService;
    final CourseScheduleServer courseScheduleServer;
    final CourseService courseService;

    @PostMapping("/login")
    public Object login(@Param("id") int id, @Param("password") String password) {
        Teacher teacher = teacherService.login(id, password);
        StpUtil.logout(teacher.getId());
        StpUtil.login(teacher.getId());
        return AjaxResp.success();
    }

    @PutMapping("/password")
    public Object changePassword(@RequestBody ChangePasswordDto dto) {
        int userName = StpUtil.getLoginIdAsInt();
        System.out.println("oldPassword = " + dto.getOldPassword());
        System.out.println("newPassword = " + dto.getNewPassword());

        teacherService.changePassword(userName, dto.getOldPassword(), dto.getNewPassword(), false);
        return AjaxResp.success();
    }
//
    @PutMapping("/passwordWithoutLogin")
    public Object changePassword_without_login(@RequestBody ChangePasswordWithoutLoginDto dto) {
        int userName=dto.getAccount();

//        // 判断是否已登录
//        if (StpUtil.isLogin()) {
//            // 登录状态下使用当前登录用户 ID
//            userId = StpUtil.getLoginIdAsInt();
//        } else {
//            // 未登录，必须传账号才能处理
//            if (dto.getAccount() == null) {
//                return AjaxResp.error("未登录状态下必须提供账号");
//            }
//            userId = userService.getIdByAccount(dto.getAccount());
//            if (userId == null) {
//                return AjaxResp.error("用户不存在");
//            }
//        }

        // 修改密码
        teacherService.changePasswordWithoutLogin(userName, dto.getNewPassword());

        return AjaxResp.success("密码修改成功");
    }


    @GetMapping("/score")
    public Object getScore(@RequestParam int page,
                           @RequestParam int size,
                           @RequestParam(name = "course_id") String courseId,
                           @RequestParam(name = "class_name") String className) {
        return AjaxResp.success(scoreService.getScoreByTeacherId(StpUtil.getLoginIdAsLong(), className, courseId, page, size));
    }

    @PostMapping("/score")
    public Object addScore(@RequestParam(name = "student_id") long studentId,
                           @RequestParam(name = "course_id") String courseId,
                           @RequestParam(name = "score") double score,
                           @RequestParam(name = "term") String term) {
        scoreService.updateScore(StpUtil.getLoginIdAsLong(), studentId, courseId, term, score);
        return AjaxResp.success();
    }

    @GetMapping("/ave_score")
    public Object getAveScore(@RequestParam(name = "page") int page,
                              @RequestParam(name = "size") int size) {
        return AjaxResp.success(scoreService.getAvgScore(page, size, "teacher", String.valueOf(StpUtil.getLoginIdAsLong())));
    }

    @GetMapping("/class_table")
    public Object getClassTable(@RequestParam(name = "page") int page,
                                @RequestParam(name = "size") int size,
                                @RequestParam(name = "term") String term) {
        return AjaxResp.success(courseScheduleServer.getCourseScheduleByTeacher(StpUtil.getLoginIdAsLong(), term, page, size));
    }


    @GetMapping("/exams_teacher")
    public Object getTeacherExamCourses() {
        List<Course> examCourses = courseService.getTeacherExamList(StpUtil.getLoginIdAsLong());
        return AjaxResp.success(examCourses);
    }

    @GetMapping("/teacher_info")
    public Object get_stu_info(){
        return AjaxResp.success(teacherService.get_teacher_info(StpUtil.getLoginIdAsLong()));
    }
}
