package com.cchong.logistics.dao;

import com.cchong.logistics.entity.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminMapper {
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
     * @param admin
     * @return
     */
    int insert(Admin admin);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    Admin selectByPrimaryKey(int id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<Admin> selectAll();

    /**
     * 根据主键全部修改操作
     *
     * @param admin
     * @return
     */
    int updateByPrimaryKey(Admin admin);

    /**
     * 查询所有数据
     * @return
     */
    int count();
}