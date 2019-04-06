package com.cchong.logistics.dao;

import com.cchong.logistics.entity.SCollect;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SCollectMapper {
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
     * @param sCollect
     * @return
     */
    int insert(SCollect sCollect);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    SCollect selectByPrimaryKey(int id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<SCollect> selectAll();

    /**
     * 根据主键全部修改操作
     *
     * @param sCollect
     * @return
     */
    int updateByPrimaryKey(SCollect sCollect);

    /**
     * 查询所有数据
     * @return
     */
    int count();
}