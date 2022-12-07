package com.ckb.wo.server.dao.fast;

import com.ckb.wo.client.model.FastDivision;
import com.ckb.wo.client.model.FastDivisionExample;
import java.util.List;

public interface FastDivisionDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_division
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    int countFastDivisionByExample(FastDivisionExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_division
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    int deleteFastDivisionByExample(FastDivisionExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_division
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    int deleteFastDivisionByPrimaryKey(String divisionId);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_division
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    void insertFastDivision(FastDivision record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_division
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    void insertFastDivisionSelective(FastDivision record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_division
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    List<FastDivision> selectFastDivisionByExample(FastDivisionExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_division
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    FastDivision selectFastDivisionByPrimaryKey(String divisionId);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_division
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    int updateFastDivisionByExampleSelective(FastDivision record, FastDivisionExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_division
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    int updateFastDivisionByExample(FastDivision record, FastDivisionExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_division
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    int updateFastDivisionByPrimaryKeySelective(FastDivision record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_division
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    int updateFastDivisionByPrimaryKey(FastDivision record);
}