package com.cchong.logistics.service;

import com.cchong.logistics.entity.DriverInformation;

import java.util.List;

public interface DriverInformationService {

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
     * @param driverInformation
     * @return
     */
    int insert(DriverInformation driverInformation);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    DriverInformation selectByPrimaryKey(int id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<DriverInformation> selectAll();

    /**
     * 修改操作
     *
     * @param driverInformation
     * @return
     */
    int updateByPrimaryKey(DriverInformation driverInformation);

    /**
     * 查询所有数据
     * @return
     */
    int count();
}