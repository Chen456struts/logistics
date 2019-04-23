package com.cchong.logistics.service;

import com.cchong.logistics.entity.Notice;

import java.util.List;

public interface NoticeService {

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
     * @param notice
     * @return
     */
    int insert(Notice notice);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    Notice selectByPrimaryKey(int id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<Notice> selectAll();

    /**
     * 修改操作
     *
     * @param notice
     * @return
     */
    int updateByPrimaryKey(Notice notice);

    /**
     * 查询所有数据
     * @return
     */
    int count();
    String selectDirection(String direction);
}