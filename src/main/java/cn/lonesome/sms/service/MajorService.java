package cn.lonesome.sms.service;

import cn.lonesome.sms.constant.ErrorCode;
import cn.lonesome.sms.exception.CustomException;
import cn.lonesome.sms.mapper.CollegeMapper;
import cn.lonesome.sms.mapper.MajorMapper;
import cn.lonesome.sms.model.dto.PaginationDto;
import cn.lonesome.sms.model.entity.Major;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Yale
 * @version 1.0
 */
@Service
@AllArgsConstructor
public class MajorService {
    final MajorMapper majorMapper;
    final CollegeMapper collegeMapper;

    public void addMajor(int id, String name, String collegeName) {
        if (id == 0 || name == null || collegeName == null) {
            throw new CustomException(ErrorCode.PARAM_ERROR);
        }
        if (majorMapper.checkId(id) != 0
                || majorMapper.checkName(name) != 0
                || collegeMapper.checkName(collegeName) != 1) {
            throw new CustomException(ErrorCode.PARAM_ERROR);
        }
        majorMapper.insert(id, name, collegeName);
    }

    public void deleteMajor(int id) {
        if (id == 0) {
            throw new CustomException(ErrorCode.PARAM_ERROR);
        }
        if (majorMapper.checkId(id) != 1) {
            throw new CustomException(ErrorCode.PARAM_ERROR);
        }
        majorMapper.delete(id);
    }

    public void updateMajor(int id, String name, String collegeName) {
        if (id == 0 || name == null || collegeName == null) {
            throw new CustomException(ErrorCode.PARAM_ERROR);
        }
        if (majorMapper.checkId(id) != 1
                || collegeMapper.checkName(collegeName) != 1) {
            throw new CustomException(ErrorCode.PARAM_ERROR);
        }
        majorMapper.update(id, name, collegeName);
    }

    public PaginationDto<Major> getMajors(int page, int size, String type, String condition) {
        int offset = (page - 1) * size;
        if ("all".equals(type)) {
            return PaginationDto.<Major>builder()
                    .data(majorMapper.selectAll(offset, size))
                    .total(majorMapper.selectCount())
                    .page(page)
                    .size(size)
                    .build();
        } else {
            if (collegeMapper.checkName(condition) != 1) {
                throw new CustomException(ErrorCode.PARAM_ERROR);
            }
            return PaginationDto.<Major>builder()
                    .data(majorMapper.selectAllByCollegeName(offset, size, condition))
                    .total(majorMapper.selectCount())
                    .page(page)
                    .size(size)
                    .build();
        }
    }
}
