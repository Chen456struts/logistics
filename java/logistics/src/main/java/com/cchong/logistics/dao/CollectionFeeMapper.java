package com.cchong.logistics.dao;

import com.cchong.logistics.entity.CollectionFee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionFeeMapper {
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
    List<CollectionFee> selectAll(String cType);

    /**
     * 根据主键全部修改操作
     *
     * @param collectionFee
     * @return
     */
    int updateByPrimaryKey(CollectionFee collectionFee);

    /**
     * 查询所有数据
     * @return
     */
    int count(String cType);
}