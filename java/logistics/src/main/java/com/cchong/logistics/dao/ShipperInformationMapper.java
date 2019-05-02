package com.cchong.logistics.dao;

import com.cchong.logistics.entity.ShipperInformation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipperInformationMapper {
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
     * @param shipperInformation
     * @return
     */
    int insert(ShipperInformation shipperInformation);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    ShipperInformation selectByPrimaryKey(int id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<ShipperInformation> selectAll(@Param("sName") String sName, @Param("sPhone") String sPhone, @Param("sSex") String sSex);

    /**
     * 根据主键全部修改操作
     *
     * @param shipperInformation
     * @return
     */
    int updateByPrimaryKey(ShipperInformation shipperInformation);

    //修改密码
    int updatePassword(@Param("password") String password, @Param("sId") int sId);
    /**
     * 查询所有数据
     * @return
     */
    int count(@Param("sName") String sName, @Param("sPhone") String sPhone, @Param("sSex") String sSex);
    /**
     * 根据手机号码获取信息
     * @param phone
     * @return
     */
    ShipperInformation yzm(@Param("phone") String phone);

}