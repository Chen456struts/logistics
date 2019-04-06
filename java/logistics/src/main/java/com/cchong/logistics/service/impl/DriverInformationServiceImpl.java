package com.cchong.logistics.service.impl;

import com.cchong.logistics.dao.DriverInformationMapper;
import com.cchong.logistics.entity.DriverInformation;
import com.cchong.logistics.service.DriverInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverInformationServiceImpl implements DriverInformationService {

    @Autowired
    private DriverInformationMapper driverInformationMapper;

    /**
     * 删除操作 根据主键删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(int id) {
        return driverInformationMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加操作
     *
     * @param driverInformation
     * @return
     */
    @Override
    public int insert(DriverInformation driverInformation) {
        return driverInformationMapper.insert(driverInformation);
    }

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    @Override
    public DriverInformation selectByPrimaryKey(int id) {
        return driverInformationMapper.selectByPrimaryKey(id);
    }

    /**
     * 全部查询操作
     *
     * @return
     */
    @Override
    public List<DriverInformation> selectAll() {
        return driverInformationMapper.selectAll();
    }

    /**
     * 修改操作
     *
     * @param driverInformation
     * @return
     */
    @Override
    public int updateByPrimaryKey(DriverInformation driverInformation) {
        return driverInformationMapper.updateByPrimaryKey(driverInformation);
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public int count() {
        return driverInformationMapper.count();
    }
}
