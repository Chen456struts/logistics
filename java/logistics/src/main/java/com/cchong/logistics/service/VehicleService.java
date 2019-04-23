package com.cchong.logistics.service;

import com.cchong.logistics.entity.Vehicle;
import org.apache.ibatis.annotations.Param;

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
    List<Vehicle> selectAll(String license,String iId);

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
    int count(@Param("license") String license, @Param("iId") String iId);
}