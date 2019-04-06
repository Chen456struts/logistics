package com.cchong.logistics.service.impl;

import com.cchong.logistics.dao.EvaluationInformationMapper;
import com.cchong.logistics.entity.EvaluationInformation;
import com.cchong.logistics.service.EvaluationInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationInformationServiceImpl implements EvaluationInformationService {

    @Autowired
    private EvaluationInformationMapper evaluationInformationMapper;

    /**
     * 删除操作 根据主键删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(int id) {
        return evaluationInformationMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加操作
     *
     * @param evaluationInformation
     * @return
     */
    @Override
    public int insert(EvaluationInformation evaluationInformation) {
        return evaluationInformationMapper.insert(evaluationInformation);
    }

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    @Override
    public EvaluationInformation selectByPrimaryKey(int id) {
        return evaluationInformationMapper.selectByPrimaryKey(id);
    }

    /**
     * 全部查询操作
     *
     * @return
     */
    @Override
    public List<EvaluationInformation> selectAll() {
        return evaluationInformationMapper.selectAll();
    }

    /**
     * 修改操作
     *
     * @param evaluationInformation
     * @return
     */
    @Override
    public int updateByPrimaryKey(EvaluationInformation evaluationInformation) {
        return evaluationInformationMapper.updateByPrimaryKey(evaluationInformation);
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public int count() {
        return evaluationInformationMapper.count();
    }
}
