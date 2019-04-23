package com.cchong.logistics.service.impl;

import com.cchong.logistics.dao.ShipperInformationMapper;
import com.cchong.logistics.entity.ShipperInformation;
import com.cchong.logistics.service.ShipperInformationService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipperInformationServiceImpl implements ShipperInformationService {

    @Autowired
    private ShipperInformationMapper shipperInformationMapper;

    /**
     * 删除操作 根据主键删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(int id) {
        return shipperInformationMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加操作
     *
     * @param shipperInformation
     * @return
     */
    @Override
    public int insert(ShipperInformation shipperInformation) {
        return shipperInformationMapper.insert(shipperInformation);
    }

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    @Override
    public ShipperInformation selectByPrimaryKey(int id) {
        return shipperInformationMapper.selectByPrimaryKey(id);
    }

    /**
     * 全部查询操作
     *
     * @return
     */
    @Override
    public List<ShipperInformation> selectAll(@Param("sName") String sName, @Param("dPhone") String sPhone, @Param("sSex") String sSex ) {
        return shipperInformationMapper.selectAll(sName,sPhone,sSex);
    }

    /**
     * 修改操作
     *
     * @param shipperInformation
     * @return
     */
    @Override
    public int updateByPrimaryKey(ShipperInformation shipperInformation) {
        return shipperInformationMapper.updateByPrimaryKey(shipperInformation);
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public int count(@Param("sName") String sName, @Param("dPhone") String sPhone, @Param("sSex") String sSex ){
        return shipperInformationMapper.count(sName,sPhone,sSex);
    }
}
