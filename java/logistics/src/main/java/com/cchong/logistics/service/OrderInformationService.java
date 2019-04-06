package com.cchong.logistics.service;

import com.cchong.logistics.entity.OrderInformation;

import java.util.List;

public interface OrderInformationService {

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
     * @param orderInformation
     * @return
     */
    int insert(OrderInformation orderInformation);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    OrderInformation selectByPrimaryKey(int id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<OrderInformation> selectAll();

    /**
     * 修改操作
     *
     * @param orderInformation
     * @return
     */
    int updateByPrimaryKey(OrderInformation orderInformation);

    /**
     * 查询所有数据
     * @return
     */
    int count();
}