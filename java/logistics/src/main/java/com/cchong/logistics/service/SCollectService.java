package com.cchong.logistics.service;

import com.cchong.logistics.entity.SCollect;

import java.util.List;

public interface SCollectService {

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
     * 修改操作
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