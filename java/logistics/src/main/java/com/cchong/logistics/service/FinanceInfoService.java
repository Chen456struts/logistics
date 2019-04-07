package com.cchong.logistics.service;

import com.cchong.logistics.entity.FinanceInfo;

import java.util.List;

public interface FinanceInfoService {

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
     * @param financeInfo
     * @return
     */
    int insert(FinanceInfo financeInfo);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    FinanceInfo selectByPrimaryKey(int id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<FinanceInfo> selectAll();

    /**
     * 修改操作
     *
     * @param financeInfo
     * @return
     */
    int updateByPrimaryKey(FinanceInfo financeInfo);

    /**
     * 查询所有数据
     * @return
     */
    int count();
}