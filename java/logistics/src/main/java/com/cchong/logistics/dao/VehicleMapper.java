package com.cchong.logistics.dao;

import com.cchong.logistics.entity.Vehicle;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleMapper {
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
    List<Vehicle> selectAll(@Param("license") String license,@Param("iId") String iId);

    /**
     * 根据主键全部修改操作
     *
     * @param vehicle
     * @return
     */
    int updateByPrimaryKey(Vehicle vehicle);

    /**
     * 查询所有数据
     * @return
     */
    int count(@Param("license") String license,@Param("iId") String iId);
}