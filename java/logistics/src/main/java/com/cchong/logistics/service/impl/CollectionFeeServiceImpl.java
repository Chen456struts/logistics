package com.cchong.logistics.service.impl;

import com.cchong.logistics.dao.CollectionFeeMapper;
import com.cchong.logistics.entity.CollectionFee;
import com.cchong.logistics.service.CollectionFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionFeeServiceImpl implements CollectionFeeService {

    @Autowired
    private CollectionFeeMapper collectionFeeMapper;

    /**
     * 删除操作 根据主键删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(int id) {
        return collectionFeeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加操作
     *
     * @param collectionFee
     * @return
     */
    @Override
    public int insert(CollectionFee collectionFee) {
        return collectionFeeMapper.insert(collectionFee);
    }

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    @Override
    public CollectionFee selectByPrimaryKey(int id) {
        return collectionFeeMapper.selectByPrimaryKey(id);
    }

    /**
     * 全部查询操作
     *
     * @return
     */
    @Override
    public List<CollectionFee> selectAll() {
        return collectionFeeMapper.selectAll();
    }

    /**
     * 修改操作
     *
     * @param collectionFee
     * @return
     */
    @Override
    public int updateByPrimaryKey(CollectionFee collectionFee) {
        return collectionFeeMapper.updateByPrimaryKey(collectionFee);
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public int count() {
        return collectionFeeMapper.count();
    }
}
