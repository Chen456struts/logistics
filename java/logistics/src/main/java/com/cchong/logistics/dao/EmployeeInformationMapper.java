package com.cchong.logistics.dao;

import com.cchong.logistics.entity.EmployeeInformation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeInformationMapper {
    /**
     * 根据主键删除操作
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
     * 根据主键全部修改操作
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