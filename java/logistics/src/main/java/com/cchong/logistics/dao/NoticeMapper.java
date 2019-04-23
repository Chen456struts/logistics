package com.cchong.logistics.dao;

import com.cchong.logistics.entity.Notice;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeMapper {
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
    String selectDirection(String direction);
    /**
     * 根据主键全部修改操作
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
}