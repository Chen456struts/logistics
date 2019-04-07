package com.cchong.logistics.dao;

import com.cchong.logistics.entity.VehicleInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleInfoMapper {
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
     * @param vehicleInfo
     * @return
     */
    int insert(VehicleInfo vehicleInfo);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    VehicleInfo selectByPrimaryKey(int id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<VehicleInfo> selectAll();

    /**
     * 根据主键全部修改操作
     *
     * @param vehicleInfo
     * @return
     */
    int updateByPrimaryKey(VehicleInfo vehicleInfo);

    /**
     * 查询所有数据
     * @return
     */
    int count();
}