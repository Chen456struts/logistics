package com.cchong.logistics.dao;

import com.cchong.logistics.entity.OrderInformation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.ws.rs.PathParam;
import java.util.List;

@Repository
public interface OrderInformationMapper {
    /**
     * 根据主键删除操作
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

    /**
     * 全部查询操作
     *
     * @return
     */
    List<OrderInformation> selectAll();

    /**
     * 根据主键全部修改操作
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
    //根据订单状态查询、订单编号、联系人号码查询
    List<OrderInformation> selectStart(@Param("oState") String oState,@Param("oId") String oId,@Param("contacts") String contacts);
    //根据订单状态查询、订单编号、联系人号码查询
    int countStart(@Param("oState") String oState,@Param("oId") String oId,@Param("contacts") String contacts);

    int countType(@Param("type") String type,@Param("sId") int sId,@Param("dId")  int dId);
}