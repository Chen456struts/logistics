package com.cchong.logistics.service;

import com.cchong.logistics.entity.OrderInformation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderInformationService {

    /**
     * 删除操作 根据主键
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 添加操作
     *
     * @param orderInformation
     * @return
     */
    int insert(OrderInformation orderInformation);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    OrderInformation selectByPrimaryKey(int id);
    int countType(String type,int sId, int dId);
    /**
     * 全部查询操作
     *
     * @return
     */
    List<OrderInformation> selectAll();

    /**
     * 修改操作
     *
     * @param orderInformation
     * @return
     */
    int updateByPrimaryKey(OrderInformation orderInformation);

    /**
     * 查询所有数据
     * @return
     */
    int count();

    //根据订单状态查询
    List<OrderInformation> selectStart(String oState, String oId, String contacts,int sId,int dId);

    int countStart(String oState, String oId, String contacts,int sId,int dId);

    //订单号、司机id、订单状态、完成时间
    int updateSet(@Param("oId") String oId,@Param("dId") int dId,@Param("oState") String oState,@Param("endDate") int endDate);
}