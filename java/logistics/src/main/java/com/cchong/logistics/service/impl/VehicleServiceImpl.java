package com.cchong.logistics.service.impl;

import com.cchong.logistics.dao.VehicleMapper;
import com.cchong.logistics.entity.Vehicle;
import com.cchong.logistics.service.VehicleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleMapper vehicleMapper;

    /**
     * 删除操作 根据主键删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(int id) {
        return vehicleMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加操作
     *
     * @param vehicle
     * @return
     */
    @Override
    public int insert(Vehicle vehicle) {
        return vehicleMapper.insert(vehicle);
    }

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    @Override
    public Vehicle selectByPrimaryKey(int id) {
        return vehicleMapper.selectByPrimaryKey(id);
    }

    /**
     * 全部查询操作
     *
     * @return
     */
    @Override
    public List<Vehicle> selectAll(String license, String iId) {
        return vehicleMapper.selectAll(license,iId);
    }

    /**
     * 修改操作
     *
     * @param vehicle
     * @return
     */
    @Override
    public int updateByPrimaryKey(Vehicle vehicle) {
        return vehicleMapper.updateByPrimaryKey(vehicle);
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public int count(String license,String iId) {
        return vehicleMapper.count(license,iId);
    }
}
