package com.cchong.logistics.dao;

import com.cchong.logistics.entity.OrderInformation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderInformationMapper {
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
     * 根据主键全部修改操作
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