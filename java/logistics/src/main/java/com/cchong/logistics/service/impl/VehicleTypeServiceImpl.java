package com.cchong.logistics.service.impl;

import com.cchong.logistics.dao.VehicleTypeMapper;
import com.cchong.logistics.entity.VehicleType;
import com.cchong.logistics.service.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleTypeServiceImpl implements VehicleTypeService {

    @Autowired
    private VehicleTypeMapper vehicleTypeMapper;

    /**
     * 删除操作 根据主键删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(int id) {
        return vehicleTypeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加操作
     *
     * @param vehicleType
     * @return
     */
    @Override
    public int insert(VehicleType vehicleType) {
        return vehicleTypeMapper.insert(vehicleType);
    }

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    @Override
    public VehicleType selectByPrimaryKey(int id) {
        return vehicleTypeMapper.selectByPrimaryKey(id);
    }

    /**
     * 全部查询操作
     *
     * @return
     */
    @Override
    public List<VehicleType> selectAll(String vName) {
        return vehicleTypeMapper.selectAll(vName);
    }

    /**
     * 修改操作
     *
     * @param vehicleType
     * @return
     */
    @Override
    public int updateByPrimaryKey(VehicleType vehicleType) {
        return vehicleTypeMapper.updateByPrimaryKey(vehicleType);
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public int count(String vName) {
        return vehicleTypeMapper.count(vName);
    }

    @Override
    public List<VehicleType> selectGroup() {
        return vehicleTypeMapper.selectGroup();
    }
}
