package cn.lonesome.sms.service;

import cn.lonesome.sms.constant.ErrorCode;
import cn.lonesome.sms.exception.CustomException;
import cn.lonesome.sms.mapper.ClassMapper;
import cn.lonesome.sms.mapper.CollegeMapper;
import cn.lonesome.sms.mapper.MajorMapper;
import cn.lonesome.sms.mapper.StudentMapper;
import cn.lonesome.sms.model.dto.HometownDto;
import cn.lonesome.sms.model.dto.PaginationDto;
import cn.lonesome.sms.model.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author Yale
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class StudentService {
    final StudentMapper studentMapper;
    final CollegeMapper collegeMapper;
    final MajorMapper majorMapper;
    final ClassMapper classMapper;

    public int countStudentsByHometown(String hometown) {
        return studentMapper.getCountByHometown(hometown);
    }

    public PaginationDto<Student> getStudents(int page, int size, String type, String condition) {
        int offset = (page - 1) * size;
        if ("all".equals(type)) {
            return PaginationDto.<Student>builder()
                    .data(studentMapper.selectAll(offset, size))
                    .total(studentMapper.selectCount())
                    .page(page)
                    .size(size)
                    .build();
        } else if ("college".equals(type)) {
            if (collegeMapper.checkName(condition) != 1) {
                throw new CustomException(ErrorCode.PARAM_ERROR);
            }
            return PaginationDto.<Student>builder()
                    .data(studentMapper.selectByCollege(offset, size, condition))
                    .total(studentMapper.selectCountByCollege(condition))
                    .page(page)
                    .size(size)
                    .build();
        } else if ("major".equals(type)) {
            if (majorMapper.checkName(condition) != 1) {
                throw new CustomException(ErrorCode.PARAM_ERROR);
            }
            return PaginationDto.<Student>builder()
                    .data(studentMapper.selectByMajor(offset, size, condition))
                    .total(studentMapper.selectCountByMajor(condition))
                    .page(page)
                    .size(size)
                    .build();
        } else {
            if (classMapper.checkName(condition) != 1) {
                throw new CustomException(ErrorCode.PARAM_ERROR);
            }
            return PaginationDto.<Student>builder()
                    .data(studentMapper.selectByClass(offset, size, condition))
                    .total(studentMapper.selectCountByClass(condition))
                    .page(page)
                    .size(size)
                    .build();
        }
    }

    public Student login(long id, String password) {
        if (studentMapper.checkId(id) != 1) {
            throw new CustomException(ErrorCode.PARAM_ERROR);
        }
        if (studentMapper.login(id, password) != 1) {
            throw new CustomException(ErrorCode.PARAM_ERROR);
        }
        return studentMapper.selectOne(id);
    }

    public Student get_stu_info(long studentId){
        return studentMapper.selectOne(studentId);
    }
    public PaginationDto<Student> getStudentsFind(int page, int size, String name, String gender, String studentClass, String hometown) {
        int offset = (page - 1) * size;

        ArrayList<Student> students = studentMapper.selectByConditions(offset, size, name, gender, studentClass, hometown);
        int total = studentMapper.selectCountByConditions(name, gender, studentClass, hometown);

        return PaginationDto.<Student>builder()
                .data(students)
                .total(total)
                .size(size)
                .page(page)
                .build();
    }

    public void updateStudent(long id, String name, String gender, int birthYear,
                              String hometown, String className) {
        if (studentMapper.checkId(id) != 1
                || classMapper.checkName(className) != 1) {
            throw new CustomException(ErrorCode.PARAM_ERROR);
        }
        studentMapper.update(id, name, gender, birthYear, className, hometown);
    }

    public void addStudent(Long id, String name, String gender, int birthYear,
                           String hometown, String className) {
        if (studentMapper.checkId(id) == 1
                || classMapper.checkName(className) != 1) {
            throw new CustomException(ErrorCode.PARAM_ERROR);
        }
        studentMapper.insert(id, name, gender, birthYear, className, hometown, "zjut" + id);
    }

    public void deleteStudent(long id) {
        if (studentMapper.checkId(id) != 1) {
            throw new CustomException(ErrorCode.PARAM_ERROR);
        }
        studentMapper.delete(id);
    }

    public void updatePassword(long id, String oldPassword, String newPassword, boolean isAdmin) {
        if (isAdmin) {
            if (studentMapper.checkId(id) != 1) {
                throw new CustomException(ErrorCode.PARAM_ERROR);
            }
            studentMapper.changePassword("zjut" + id, id);
            return;
        }
        if (studentMapper.checkId(id) != 1
                || studentMapper.login(id, oldPassword) != 1) {
            throw new CustomException(ErrorCode.PARAM_ERROR);
        }
        studentMapper.changePassword(newPassword, id);
    }

    public ArrayList<HometownDto> getHometownCounts() {
        ArrayList<String> hometownList = studentMapper.selectHometownList();
        ArrayList<HometownDto> hometownDtoList = new ArrayList<>();
        int i = 0;
        for (String hometown : hometownList) {
            hometownDtoList.add(HometownDto.builder()
                    .id(++i)
                    .hometown(hometown)
                    .count(studentMapper.getCountByHometown(hometown))
                    .build());
        }
        return hometownDtoList;
    }

    public PaginationDto<Student> getStudentsByHometown(int page, int size, String hometown) {
        int offset = (page - 1) * size;
        return PaginationDto.<Student>builder()
                .data(studentMapper.selectByHometown(offset, size, hometown))
                .total(studentMapper.getCountByHometown(hometown))
                .page(page)
                .size(size)
                .build();
    }
}

