package com.ckb.wo.server.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ckb.wo.client.model.ExtendedUserExample;
import com.ckb.wo.client.model.RolesUsersExample;
import com.ckb.wo.client.model.User;
import com.ckb.wo.client.model.UserExample;
import com.ckb.wo.client.model.WorkOrder;
import com.ckb.wo.server.service.util.UserLocalServiceUtil;

public interface IUserService {

    public List<User> getUser();

    public int countUser();

    public List<User> getUserByExample(UserExample example);

    public int countUserByExample(UserExample example);

    public int deleteUser(String userPk);

    public void insertUser(User user);

    public int updateUser(User user);

    public List<User> getUserByExample(UserExample example, int pageNo,
            int rowPerPage);

    public User getUserByPrimaryKey(String userPk);

    public List<String> getEmployeeIdByExample(UserExample example);

    public Integer getUserLevelValueByEmployeeId(String employeeId);

    public boolean isProcurementUser(String employeeId);

    public boolean isOperationUser(String employeeId);

    public List<String> getAllStaffForThisUser(String employeeId);

    public User getFinalUserApprovalWO(Long tworkorderPk);

    public User getNextUserForThisWorkOrder(Long tworkorderPk);

    public User getUserByPrimaryKeyWithJoin(String employeeId);

    public List<User> getUserByExampleWithJoin(UserExample example);

    public User getUserByPrimaryKeyWithRoles(String employeeId);

    public List<User> getUserByExampleWithRoles(UserExample example);

    public List<String> getOnLeaveUsersOneLevelBelowWhoDelegatingToThisUser(
            String employeeId);

    public List<String> getOnLeaveUsersTwoLevelBelowWhoDelegatingToThisUser(
            String employeeId);

    public List<String> getCreatorUserForOnLeaveUserBelowOneLevel(String employeeId);

    public List<String> getCreatorUserForOnLeaveUserBelowTwoLevel(String employeeId);

    public List<User> getUsersOneLevelAbove(String employeeId, String limitClause, String orderByClause);

    public int countUsersOneLevelAbove(String employeeId);

    public abstract void insertUserToRoles(String employeeId, List<Long> roles);

    public void deleteUserFromRoles(String employeeId, List<Long> roles);

    public List<User> getUserAlreadyCreateWO();

    User getFinalUserCancellationWO(Long tworkorderPk);

    List<String> getAllLeaveUser(String employeeId);
}
