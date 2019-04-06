package com.cchong.logistics.service;

import com.cchong.logistics.entity.CollectionFee;

import java.util.List;

public interface CollectionFeeService {

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
     * @param collectionFee
     * @return
     */
    int insert(CollectionFee collectionFee);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    CollectionFee selectByPrimaryKey(int id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<CollectionFee> selectAll();

    /**
     * 修改操作
     *
     * @param collectionFee
     * @return
     */
    int updateByPrimaryKey(CollectionFee collectionFee);

    /**
     * 查询所有数据
     * @return
     */
    int count();
}