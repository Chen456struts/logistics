package com.cchong.logistics.service.impl;

import com.cchong.logistics.dao.VerificationCodeMapper;
import com.cchong.logistics.entity.VerificationCode;
import com.cchong.logistics.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
    @Autowired
    private VerificationCodeMapper verificationCodeMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return verificationCodeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(VerificationCode record) {
        return verificationCodeMapper.insert(record);
    }

    @Override
    public VerificationCode selectByPrimaryKey(Integer id) {
        return verificationCodeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<VerificationCode> selectAll() {
        return verificationCodeMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(VerificationCode record) {
        return verificationCodeMapper.updateByPrimaryKey(record);
    }
}
