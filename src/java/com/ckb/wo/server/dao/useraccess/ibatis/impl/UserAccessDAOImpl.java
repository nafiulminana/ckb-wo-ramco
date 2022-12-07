package com.ckb.wo.server.dao.useraccess.ibatis.impl;

import com.ckb.wo.client.model.UserAccess;
import com.ckb.wo.client.model.UserAccessExample;
import com.ckb.wo.server.dao.useraccess.UserAccessDAO;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class UserAccessDAOImpl extends SqlMapClientDaoSupport implements UserAccessDAO {

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table t_user
     *
     * @ibatorgenerated Mon Jul 19 20:03:21 SGT 2010
     */
    public UserAccessDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table t_user
     *
     * @param example
     * @return
     * @ibatorgenerated Mon Jul 19 20:03:21 SGT 2010
     */
    @Override
    public int countUserAccessByExample(UserAccessExample example) {
        Integer count = (Integer) getSqlMapClientTemplate().queryForObject("t_user.ibatorgenerated_countByExample", example);
        return count;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table t_user
     *
     * @param example
     * @return
     * @ibatorgenerated Mon Jul 19 20:03:21 SGT 2010
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<UserAccess> selectUserAccessByExample(UserAccessExample example) {
        List<UserAccess> list = getSqlMapClientTemplate().queryForList("t_user.ibatorgenerated_selectByExample", example);
        return list;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table t_user
     *
     * @param userId
     * @return
     * @ibatorgenerated Mon Jul 19 20:03:21 SGT 2010
     */
    @Override
    public UserAccess selectUserAccessByPrimaryKey(String userId) {
        UserAccess key = new UserAccess();
        key.setUserId(userId);
        UserAccess record = (UserAccess) getSqlMapClientTemplate().queryForObject("t_user.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }
}