package com.cchong.logistics.dao;

import com.cchong.logistics.entity.ShipperInformation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipperInformationMapper {
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
     * 根据主键全部修改操作
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