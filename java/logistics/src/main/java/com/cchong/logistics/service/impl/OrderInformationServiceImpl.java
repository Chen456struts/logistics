package com.cchong.logistics.service.impl;

import com.cchong.logistics.dao.OrderInformationMapper;
import com.cchong.logistics.entity.OrderInformation;
import com.cchong.logistics.service.OrderInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderInformationServiceImpl implements OrderInformationService {

    @Autowired
    private OrderInformationMapper orderInformationMapper;

    /**
     * 删除操作 根据主键删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(String id) {
        return orderInformationMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加操作
     *
     * @param orderInformation
     * @return
     */
    @Override
    public int insert(OrderInformation orderInformation) {
        return orderInformationMapper.insert(orderInformation);
    }

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    @Override
    public OrderInformation selectByPrimaryKey(int id) {
        return orderInformationMapper.selectByPrimaryKey(id);
    }

    @Override
    public int countType(String type,int sId, int dId) {
        return orderInformationMapper.countType(type,sId,dId);
    }

    /**
     * 全部查询操作
     *
     * @return
     */
    @Override
    public List<OrderInformation> selectAll() {
        return orderInformationMapper.selectAll();
    }

    /**
     * 修改操作
     *
     * @param orderInformation
     * @return
     */
    @Override
    public int updateByPrimaryKey(OrderInformation orderInformation) {
        return orderInformationMapper.updateByPrimaryKey(orderInformation);
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public int count() {
        return orderInformationMapper.count();
    }

    @Override
    public List<OrderInformation> selectStart(String oState, String oId, String contacts,int sId,int dId) {
        return orderInformationMapper.selectStart(oState, oId, contacts,sId,dId);
    }

    @Override
    public int countStart(String oState, String oId, String contacts,int sId,int dId) {
        return orderInformationMapper.countStart(oState, oId, contacts,sId,dId);
    }

    @Override
    public int updateSet(String oId, int dId, String oState, int endDate) {
        return orderInformationMapper.updateSet(oId,dId,oState,endDate);
    }

}
