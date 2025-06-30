package cn.lonesome.sms.service;

import cn.lonesome.sms.constant.ErrorCode;
import cn.lonesome.sms.exception.CustomException;
import cn.lonesome.sms.mapper.CollegeMapper;
import cn.lonesome.sms.mapper.TeacherMapper;
import cn.lonesome.sms.model.dto.PaginationDto;
import cn.lonesome.sms.model.entity.Teacher;
import cn.lonesome.sms.model.entity.Student;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author Yale
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private CollegeMapper collegeMapper;
//    final TeacherMapper teacherMapper;
//    final CollegeMapper collegeMapper;

    public int getTeacherCount() {
        return teacherMapper.selectCount();
    }

    public void addTeacher(int id, String name, String gender, int birthYear,
                           String college, String phone, String title) {
        if (teacherMapper.checkId(id) != 0
                || collegeMapper.checkName(college) != 1) {
            throw new CustomException(ErrorCode.PARAM_ERROR);
        }
        teacherMapper.insert(id, name, gender, birthYear, college, phone, title, "zjut" + id);
    }

    public void deleteTeacher(int id) {
        if (teacherMapper.checkId(id) != 1) {
            throw new CustomException(ErrorCode.PARAM_ERROR);
        }
        teacherMapper.delete(id);
    }

    public void updateTeacher(int id, String name, String gender, int birthYear,
                              String college, String phone, String title) {
        if (teacherMapper.checkId(id) != 1
                || collegeMapper.checkName(college) != 1) {
            throw new CustomException(ErrorCode.PARAM_ERROR);
        }
        teacherMapper.update(id, name, gender, birthYear, college, phone, title);
    }

    public PaginationDto<Teacher> getTeachers(int page, int size, String type, String condition) {
        int offset = (page - 1) * size;
        if ("all".equals(type)) {
            return PaginationDto.<Teacher>builder()
                    .data(teacherMapper.selectAll(offset, size))
                    .total(teacherMapper.selectCount())
                    .size(size)
                    .page(page)
                    .build();
        } else {
            if (collegeMapper.checkName(condition) != 1) {
                throw new CustomException(ErrorCode.PARAM_ERROR);
            }
            return PaginationDto.<Teacher>builder()
                    .data(teacherMapper.selectByCollegeName(offset, size, condition))
                    .total(teacherMapper.selectCount())
                    .size(size)
                    .page(page)
                    .build();
        }
    }

    public PaginationDto<Teacher> getTeachersfind(int page, int size, String name, String gender, String college, String jobTitle) {
        int offset = (page - 1) * size;

        ArrayList<Teacher> teachers = teacherMapper.selectByConditions(offset, size, name, gender, college, jobTitle);
        int total = teacherMapper.selectCountByConditions(name, gender, college, jobTitle);

        return PaginationDto.<Teacher>builder()
                .data(teachers)
                .total(total)
                .size(size)
                .page(page)
                .build();
    }




    public void changePassword(long id, String oldPassword, String newPassword, boolean isAdmin) {
        if (isAdmin) {
            teacherMapper.changePassword("zjut" + id, id);
            return;
        }
        if (teacherMapper.login(id, oldPassword) != 1) {
            throw new CustomException(ErrorCode.PARAM_ERROR);
        }
        teacherMapper.changePassword(newPassword, id);
    }
    public void changePasswordWithoutLogin(long id, String newPassword) {

        teacherMapper.changePassword(newPassword, id);
    }

    public Teacher login(int id, String password) {
        if (teacherMapper.login(id, password) != 1) {
            throw new CustomException(ErrorCode.PARAM_ERROR);
        }
        return teacherMapper.selectOne(id);
    }

    public Teacher get_teacher_info(long teacherId){
        return teacherMapper.selectOne(teacherId);
    }
}
