package com.cchong.logistics.dao;

import com.cchong.logistics.entity.DriverInformation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverInformationMapper {
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
     * @param driverInformation
     * @return
     */
    int insert(DriverInformation driverInformation);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    DriverInformation selectByPrimaryKey(int id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<DriverInformation> selectAll();

    /**
     * 根据主键全部修改操作
     *
     * @param driverInformation
     * @return
     */
    int updateByPrimaryKey(DriverInformation driverInformation);

    /**
     * 查询所有数据
     * @return
     */
    int count();
}