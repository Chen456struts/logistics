package com.cchong.logistics.service;

import com.cchong.logistics.entity.ShipperInformation;

import java.util.List;

public interface ShipperInformationService {

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
     * @param shipperInformation
     * @return
     */
    int insert(ShipperInformation shipperInformation);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    ShipperInformation selectByPrimaryKey(int id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<ShipperInformation> selectAll();

    /**
     * 修改操作
     *
     * @param shipperInformation
     * @return
     */
    int updateByPrimaryKey(ShipperInformation shipperInformation);

    /**
     * 查询所有数据
     * @return
     */
    int count();
}