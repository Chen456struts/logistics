package com.cchong.logistics.service.impl;

import com.cchong.logistics.dao.FinanceInfoMapper;
import com.cchong.logistics.entity.FinanceInfo;
import com.cchong.logistics.service.FinanceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinanceInfoServiceImpl implements FinanceInfoService {

    @Autowired
    private FinanceInfoMapper financeInfoMapper;

    /**
     * 删除操作 根据主键删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(int id) {
        return financeInfoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加操作
     *
     * @param financeInfo
     * @return
     */
    @Override
    public int insert(FinanceInfo financeInfo) {
        return financeInfoMapper.insert(financeInfo);
    }

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    @Override
    public FinanceInfo selectByPrimaryKey(int id) {
        return financeInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 全部查询操作
     *
     * @return
     */
    @Override
    public List<FinanceInfo> selectAll() {
        return financeInfoMapper.selectAll();
    }

    /**
     * 修改操作
     *
     * @param financeInfo
     * @return
     */
    @Override
    public int updateByPrimaryKey(FinanceInfo financeInfo) {
        return financeInfoMapper.updateByPrimaryKey(financeInfo);
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public int count() {
        return financeInfoMapper.count();
    }
}
