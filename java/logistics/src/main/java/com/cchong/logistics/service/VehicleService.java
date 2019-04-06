package com.cchong.logistics.service;

import com.cchong.logistics.entity.Vehicle;

import java.util.List;

public interface VehicleService {

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
     * @param vehicle
     * @return
     */
    int insert(Vehicle vehicle);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    Vehicle selectByPrimaryKey(int id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<Vehicle> selectAll();

    /**
     * 修改操作
     *
     * @param vehicle
     * @return
     */
    int updateByPrimaryKey(Vehicle vehicle);

    /**
     * 查询所有数据
     * @return
     */
    int count();
}