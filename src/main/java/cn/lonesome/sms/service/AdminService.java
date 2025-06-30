package cn.lonesome.sms.service;

import cn.lonesome.sms.constant.ErrorCode;
import cn.lonesome.sms.exception.CustomException;
import cn.lonesome.sms.mapper.AdminMapper;
import cn.lonesome.sms.model.entity.Admin;
import cn.lonesome.sms.model.entity.Schedule;
import lombok.RequiredArgsConstructor;
import cn.lonesome.sms.model.entity.SchoolStatistics;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Service;

/**
 * @author Yale
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class AdminService {
    final AdminMapper adminMapper;

    public Admin login(Integer username, String password) {
        if (username == null || password == null) {
            throw new CustomException(ErrorCode.PARAM_ERROR);
        }
        if (adminMapper.checkUsername(username) != 1) {
            throw new CustomException(ErrorCode.USER_NOT_EXISTED);
        }
        return adminMapper.login(username, password);
    }

    public Admin.Role getRole(Integer username) {
        if (username == null) {
            throw new CustomException(ErrorCode.PARAM_ERROR);
        }
        if (adminMapper.checkUsername(username) != 1) {
            throw new CustomException(ErrorCode.USER_NOT_EXISTED);
        }
        return adminMapper.selectByUsername(username).getRole();
    }

    public void changePassword(Integer username, String oldPassword, String newPassword) {
        if (username == null || oldPassword == null || newPassword == null) {
            throw new CustomException(ErrorCode.PARAM_ERROR);
        }
        if (adminMapper.checkUsername(username) != 1) {
            throw new CustomException(ErrorCode.USER_NOT_EXISTED);
        }
        if (adminMapper.login(username, oldPassword) == null) {
            throw new CustomException(ErrorCode.PARAM_ERROR);
        }
        adminMapper.changePassword(username, newPassword);
    }


    public void changePasswordWithoutLogin(Integer username, String newPassword) {

        adminMapper.changePassword(username, newPassword);
    }




    public void insert(Integer id, String password, String role) {
        if (id == null || password == null || role == null || !EnumUtils.isValidEnum(Admin.Role.class, role)) {
            throw new CustomException(ErrorCode.PARAM_ERROR);
        }
        if (adminMapper.checkUsername(id) != 0) {
            throw new CustomException(ErrorCode.USER_EXISTED);
        }
        adminMapper.insert(id, password, role);
    }

    public void updateRole(Integer username, String role) {
        if (username == null || role == null || !EnumUtils.isValidEnum(Admin.Role.class, role)) {
            throw new CustomException(ErrorCode.PARAM_ERROR);
        }
        if (adminMapper.checkUsername(username) != 1) {
            throw new CustomException(ErrorCode.USER_NOT_EXISTED);
        }
        adminMapper.updateRole(username, role);
    }

    public void delete(Integer username) {
        if (username == null) {
            throw new CustomException(ErrorCode.PARAM_ERROR);
        }
        if (adminMapper.checkUsername(username) != 1) {
            throw new CustomException(ErrorCode.USER_NOT_EXISTED);
        }
        adminMapper.delete(username);
    }

    public SchoolStatistics getSchoolInfo(){
        return adminMapper.getSchoolStatistics();
    }


}
