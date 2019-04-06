package com.cchong.logistics.service.impl;

import com.cchong.logistics.dao.NoticeMapper;
import com.cchong.logistics.entity.Notice;
import com.cchong.logistics.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * 删除操作 根据主键删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(int id) {
        return noticeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加操作
     *
     * @param notice
     * @return
     */
    @Override
    public int insert(Notice notice) {
        return noticeMapper.insert(notice);
    }

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    @Override
    public Notice selectByPrimaryKey(int id) {
        return noticeMapper.selectByPrimaryKey(id);
    }

    /**
     * 全部查询操作
     *
     * @return
     */
    @Override
    public List<Notice> selectAll() {
        return noticeMapper.selectAll();
    }

    /**
     * 修改操作
     *
     * @param notice
     * @return
     */
    @Override
    public int updateByPrimaryKey(Notice notice) {
        return noticeMapper.updateByPrimaryKey(notice);
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public int count() {
        return noticeMapper.count();
    }
}
