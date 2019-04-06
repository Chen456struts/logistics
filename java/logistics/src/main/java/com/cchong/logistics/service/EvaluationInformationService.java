package com.cchong.logistics.service;

import com.cchong.logistics.entity.EvaluationInformation;

import java.util.List;

public interface EvaluationInformationService {

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
     * @param evaluationInformation
     * @return
     */
    int insert(EvaluationInformation evaluationInformation);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    EvaluationInformation selectByPrimaryKey(int id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<EvaluationInformation> selectAll();

    /**
     * 修改操作
     *
     * @param evaluationInformation
     * @return
     */
    int updateByPrimaryKey(EvaluationInformation evaluationInformation);

    /**
     * 查询所有数据
     * @return
     */
    int count();
}