package com.cchong.logistics.service.impl;

import com.cchong.logistics.dao.EmployeeInformationMapper;
import com.cchong.logistics.entity.EmployeeInformation;
import com.cchong.logistics.service.EmployeeInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeInformationServiceImpl implements EmployeeInformationService {

    @Autowired
    private EmployeeInformationMapper employeeInformationMapper;

    /**
     * 删除操作 根据主键删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(int id) {
        return employeeInformationMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加操作
     *
     * @param employeeInformation
     * @return
     */
    @Override
    public int insert(EmployeeInformation employeeInformation) {
        return employeeInformationMapper.insert(employeeInformation);
    }

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    @Override
    public EmployeeInformation selectByPrimaryKey(int id) {
        return employeeInformationMapper.selectByPrimaryKey(id);
    }

    /**
     * 全部查询操作
     *
     * @return
     */
    @Override
    public List<EmployeeInformation> selectAll() {
        return employeeInformationMapper.selectAll();
    }

    /**
     * 修改操作
     *
     * @param employeeInformation
     * @return
     */
    @Override
    public int updateByPrimaryKey(EmployeeInformation employeeInformation) {
        return employeeInformationMapper.updateByPrimaryKey(employeeInformation);
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public int count() {
        return employeeInformationMapper.count();
    }
}
