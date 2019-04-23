package com.cchong.logistics.dao;

import com.cchong.logistics.entity.EvaluationInformation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationInformationMapper {
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
     * @param evaluationInformation
     * @return
     */
    int insert(EvaluationInformation evaluationInformation);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    EvaluationInformation selectByPrimaryKey(int id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<EvaluationInformation> selectAll(@Param("fsid") int fsid,@Param("sName")  String sName,@Param("dName")  String dName);

    /**
     * 根据主键全部修改操作
     *
     * @param evaluationInformation
     * @return
     */
    int updateByPrimaryKey(EvaluationInformation evaluationInformation);

    /**
     * 查询所有数据
     * @return
     */
    int count(@Param("fsid") int fsid,@Param("sName")  String sName,@Param("dName")  String dName);
}