package com.cchong.logistics.service;

import com.cchong.logistics.entity.EmployeeInformation;

import java.util.List;

public interface EmployeeInformationService {

    /**
     * 删除操作 根据主键
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(int id);

    /**
     * 添加操作
     *
     * @param employeeInformation
     * @return
     */
    int insert(EmployeeInformation employeeInformation);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    EmployeeInformation selectByPrimaryKey(int id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<EmployeeInformation> selectAll();

    /**
     * 修改操作
     *
     * @param employeeInformation
     * @return
     */
    int updateByPrimaryKey(EmployeeInformation employeeInformation);

    /**
     * 查询所有数据
     * @return
     */
    int count();
}