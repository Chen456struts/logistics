package com.cchong.logistics.dao;

import com.cchong.logistics.entity.FinanceInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinanceInfoMapper {
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
     * @param financeInfo
     * @return
     */
    int insert(FinanceInfo financeInfo);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    FinanceInfo selectByPrimaryKey(int id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<FinanceInfo> selectAll();

    /**
     * 根据主键全部修改操作
     *
     * @param financeInfo
     * @return
     */
    int updateByPrimaryKey(FinanceInfo financeInfo);

    /**
     * 查询所有数据
     * @return
     */
    int count();


    /**
     * 根据交易类型查询操作
     *
     * @return
     */
    List<FinanceInfo> selectType(@Param("fName") String fName,@Param("fNameType") String fNameType,@Param("fType") String fType);

    /**
     * 根据交易类型查询操作
     * @return
     */
    int countType(@Param("fName") String fName,@Param("fNameType") String fNameType,@Param("fType") String fType);
}