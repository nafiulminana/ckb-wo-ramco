package com.ckb.wo.server.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ckb.wo.client.model.Department;
import com.ckb.wo.client.model.Division;
import com.ckb.wo.client.model.ExtendedUserExample;
import com.ckb.wo.client.model.RolesUsers;
import com.ckb.wo.client.model.RolesUsersExample;
import com.ckb.wo.client.model.User;
import com.ckb.wo.client.model.UserExample;
import com.ckb.wo.client.model.WorkOrder;
import com.ckb.wo.server.dao.RolesUsersDAO;
import com.ckb.wo.server.dao.UserDAO;
import com.ckb.wo.server.dao.WorkOrderDAO;
import com.ckb.wo.server.service.IUserService;
import com.ckb.wo.server.service.factory.BeanFactory;
import com.ckb.wo.server.service.util.UserLocalServiceUtil;

public class UserServiceImpl extends GenericServiceImpl implements IUserService {

    final static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UserServiceImpl.class);

    private UserDAO userDao;
    private WorkOrderDAO workorderDao;
    private RolesUsersDAO rolesusersDao;

    public UserServiceImpl(UserDAO userDao) {
        super();
        this.userDao = userDao;
    }

    private WorkOrderDAO getWorkorderDao() {
        if (workorderDao == null) {
            workorderDao = (WorkOrderDAO) BeanFactory.getBean("workorderDao");
        }
        return workorderDao;
    }

    /**
     * @return the rolesusersDao
     */
    public RolesUsersDAO getRolesusersDao() {
        if (rolesusersDao == null) {
            rolesusersDao = (RolesUsersDAO) BeanFactory.getBean("rolesusersDao");
        }
        return rolesusersDao;
    }

    /**
     * @param rolesusersDao the rolesusersDao to set
     */
    public void setRolesusersDao(RolesUsersDAO rolesusersDao) {
        this.rolesusersDao = rolesusersDao;
    }

    @Override
    public List<User> getUser() {
        UserExample example = new UserExample();
        return userDao.selectUserByExample(example);
    }

    @Override
    public int countUser() {
        UserExample example = new UserExample();
        return userDao.countUserByExample(example);
    }

    @Override
    public List<User> getUserByExample(UserExample example) {
        return userDao.selectUserByExample(example);
    }

    @Override
    public int countUserByExample(UserExample example) {
        return userDao.countUserByExample(example);
    }

    @Override
    public int deleteUser(String userPk) {
        return userDao.deleteUserByPrimaryKey(userPk);
    }

    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUserByPrimaryKey(user);
    }

    @Override
    public List<User> getUserByExample(UserExample example, int pageNo, int rowPerPage) {
        example.setLimitClause(setOffset(pageNo, rowPerPage));
        return userDao.selectUserByExample(example);
    }

    @Override
    public User getUserByPrimaryKey(String userPk) {
        return userDao.selectUserByPrimaryKey(userPk);
    }

    @Override
    public List<String> getAllStaffForThisUser(String employeeId) {
        //int level_value = getUserLevelValueByEmployeeId(employeeId);
        List<String> bossIds = new ArrayList<>();
        bossIds.add(employeeId);
        UserExample userExample = new UserExample();

        List<String> allEmployeeBelow = new ArrayList<>();

//		for(int i=level_value;i>-1;i--){
        while (!bossIds.isEmpty()) {
//                        if(bossIds.isEmpty()) continue;
            userExample.createCriteria().andBossemployeeidIn(bossIds);
            List<String> empIds = UserLocalServiceUtil.getEmployeeIdByExample(userExample);
            allEmployeeBelow.addAll(empIds);
            // update boss id
            bossIds.clear();
            bossIds.addAll(empIds);
            // clear criteria
            userExample.clear();
        }
        return allEmployeeBelow;
    }

    public User getBossUser(String slaveUserPk, Integer expectedLevelValue) {
        User slaveUser = getUserByPrimaryKey(slaveUserPk);
        int slaveLevel = getUserLevelValueByEmployeeId(slaveUserPk);
        User aboveUser = null;
//        String bossEmployeeId = slaveUser.getBossemployeeid();
        for (int i = slaveLevel + 1; i <= expectedLevelValue; i++) {
            aboveUser = getUserByPrimaryKey(slaveUser.getBossemployeeid());
//            int currentLevelValue = getUserLevelValueByEmployeeId(aboveUser.getEmployeeId());
        }
        return aboveUser;
    }

    public User getBossUser(Long slaveUserPk, String expectedLevelId) {
//		User slaveUser = getUserByPrimaryKey(slaveUserPk);
//		User aboveUser = 
        return null;
    }

    @Override
    public List<String> getEmployeeIdByExample(UserExample example) {
        return userDao.selectselectEmployeeIdByExample(example);
    }

    @Override
    public Integer getUserLevelValueByEmployeeId(String employeeId) {
        return getUserDao().getUserLevelValueByEmployeeId(employeeId);
    }

    public UserDAO getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean isProcurementUser(String employeeId) {
        return isUserBelongsToThisDepartment(Department.PROCUREMENT_CODE, employeeId) && isUserBelongsToThisDivision(Division.SERVICE_CODE, employeeId);
    }

    @Override
    public boolean isOperationUser(String employeeId) {
//		return isUserBelongsToThisDepartment(Department.OPERATION_CODE, employeeId);
        return isUserBelongsToThisDivision(Division.OPERATION_CODE, employeeId);
    }

    public boolean isFinance(String employeeId) {
//		return isUserBelongsToThisDepartment(Department.FINANCE_CODE, employeeId);
        return isUserBelongsToThisDivision(Division.FINANCE_CODE, employeeId);
    }

    private boolean isUserBelongsToThisDepartment(String deptId, String employeeId) {
        User user = getUserDao().selectUserByPrimaryKey(employeeId);
        if (user == null) {
            return false;
        }
        return deptId.equalsIgnoreCase(user.getDepartmentId());
    }

    private boolean isUserBelongsToThisDivision(String division, String employeeId) {
        User user = getUserDao().selectUserByPrimaryKey(employeeId);
        if (user == null) {
            return false;
        }
//		if(deptId.equalsIgnoreCase(user.getDepartmentId())){
        return division.equalsIgnoreCase(user.getDivisionId());
    }

    @Override
    public User getFinalUserApprovalWO(Long tworkorderPk) {
        return getUserDao().getFinalUserApprovalWO(tworkorderPk);
    }

    @Override
    public User getFinalUserCancellationWO(Long tworkorderPk) {
        return getUserDao().getFinalUserCancellationWO(tworkorderPk);
    }

    @Override
    public User getNextUserForThisWorkOrder(Long tworkorderPk) {
        User user = getUserDao().getNextUserForThisWorkOrder(tworkorderPk);
        if (user == null) {
            WorkOrder workOrder = getWorkorderDao().selectWorkOrderByPrimaryKey(tworkorderPk);
            User nextUser = null;
            String employeeId = workOrder.getCreatedbyemployeeid();
            String bossemployeeId = null;
            while (true) {
                nextUser = getUserByPrimaryKey(employeeId);
                bossemployeeId = nextUser.getBossemployeeid();
                nextUser = getUserByPrimaryKey(bossemployeeId);
                Integer userLevel = getUserLevelValueByEmployeeId(bossemployeeId);
                if (workOrder.getMaxlevel() > (userLevel)) {
                    nextUser = null;
                    break;
                }
                if (workOrder.getNexttlevel().equals(userLevel)) {
                    break;
                } else {
                    employeeId = bossemployeeId;
                }
            }
            user = nextUser;
        }
        log.info("Next User For WO " + tworkorderPk + " is " + user.getEmployeeId());
        return user;
    }

    @Override
    public User getUserByPrimaryKeyWithJoin(String employeeId) {
        return getUserDao().selectUserByPrimaryKeyWithJoin(employeeId);
    }

    @Override
    public List<User> getUserByExampleWithJoin(UserExample example) {
        return getUserDao().selectUserByExampleWithJoin(example);
    }

    @Override
    public User getUserByPrimaryKeyWithRoles(String employeeId) {
        return getUserDao().selectUserByPrimaryKeyWithRoles(employeeId);
    }

    @Override
    public List<User> getUserByExampleWithRoles(UserExample example) {
        return getUserDao().selectUserByExampleWithRoles(example);
    }

    /*
     * get all user below this employeeId, who are currently on leave,
     * and delegating to this user
     */
    @Override
    public List<String> getOnLeaveUsersOneLevelBelowWhoDelegatingToThisUser(String employeeId) {
        Date today = new Date();

        UserExample example = new UserExample();
        UserExample.Criteria crit = example.createCriteria();
        crit.andBossemployeeidEqualTo(employeeId);
        crit.andGrantfromLessThanOrEqualTo(today);
        crit.andGranttoGreaterThanOrEqualTo(today);
        List<String> usersOneLevelBelow = getUserDao().selectselectEmployeeIdByExample(example);

        return usersOneLevelBelow;
    }

    @Override
    public List<String> getOnLeaveUsersTwoLevelBelowWhoDelegatingToThisUser(String employeeId) {
        Date today = new Date();
        List<String> usersOneLevelBelow = getOnLeaveUsersOneLevelBelowWhoDelegatingToThisUser(employeeId);
        if (usersOneLevelBelow != null && usersOneLevelBelow.size() > 0) {
            // we assume that it's not possible 3 users on the hierarcy takes leave at one time.
            // at most only 2 users at the same time take leave.
            UserExample example2 = new UserExample();
            UserExample.Criteria crit2 = example2.createCriteria();
            crit2.andBossemployeeidIn(usersOneLevelBelow);
            crit2.andGrantfromLessThanOrEqualTo(today);
            crit2.andGranttoGreaterThanOrEqualTo(today);
            List<String> usersTwoLevelBelow = getUserDao().selectselectEmployeeIdByExample(example2);
            return usersTwoLevelBelow;
        }
        return null;
    }

    /*
     * For every staff who is two level below user with employeeId,
     * find all the staff
     */
    @Override
    public List<String> getCreatorUserForOnLeaveUserBelowOneLevel(String employeeId) {
        List<String> oneLevelBelowOnLeave = getOnLeaveUsersOneLevelBelowWhoDelegatingToThisUser(employeeId);
        List<String> creatorUserForBelowOneLevel = getCreatorUserForThisUsers(oneLevelBelowOnLeave);
        return creatorUserForBelowOneLevel;
    }

    @Override
    public List<String> getCreatorUserForOnLeaveUserBelowTwoLevel(String employeeId) {
        List<String> twoLevelBelowOnLeave = getOnLeaveUsersTwoLevelBelowWhoDelegatingToThisUser(employeeId);
        List<String> creatorUserForBelowTwoLevel = getCreatorUserForThisUsers(twoLevelBelowOnLeave);
        return creatorUserForBelowTwoLevel;
    }

    private List<String> getCreatorUserForThisUsers(List<String> userList) {
        List<String> creatorUser = new ArrayList<>();
        if (userList != null) {
            for (String empId : userList) {
                List<String> allStaff = getAllStaffForThisUser(empId);
                if (allStaff != null) {
                    creatorUser.addAll(allStaff);
                }
            }
        }
        return creatorUser;
    }

    @Override
    public List<User> getUsersOneLevelAbove(String employeeId, String limitClause, String orderByClause) {
        ExtendedUserExample example = getExampleUsersOneLevelAbove(employeeId);
        example.setLimitClause(limitClause);
        example.setOrderByClause(orderByClause);
        return getUserDao().selectUsersOneLevelAbove(example);
        //return getUserDao().selectUserByExampleWithJoin(example);
    }

    @Override
    public int countUsersOneLevelAbove(String employeeId) {
        ExtendedUserExample example = getExampleUsersOneLevelAbove(employeeId);
        return getUserDao().countUsersOneLevelAbove(example);
    }

    private ExtendedUserExample getExampleUsersOneLevelAbove(String employeeId) {
        int thisuserlevel = getUserDao().getUserLevelValueByEmployeeId(employeeId);
        ExtendedUserExample example = new ExtendedUserExample();
        ExtendedUserExample.ExtendedCriteria crit = (ExtendedUserExample.ExtendedCriteria) example.createCriteria();
//		crit.andLevelValueEqualTo(thisuserlevel+1);
        crit.andLevelValueGreaterThan(thisuserlevel);

        return example;
    }

    @Override
    public void insertUserToRoles(String employeeId, List<Long> roles) {
        if (roles != null) {
            RolesUsersExample example = new RolesUsersExample();
            for (Long trole_fk : roles) {
                // check whether the value already exists in the table
                example.clear();
                example.createCriteria().andEmployeeIdEqualTo(employeeId).andTroleFkEqualTo(trole_fk);
                int count = getRolesusersDao().countRolesUsersByExample(example);
                if (count == 0) {
                    RolesUsers rusers = new RolesUsers();
                    rusers.setEmployeeId(employeeId);
                    rusers.setTroleFk(trole_fk);
                    getRolesusersDao().insertRolesUsers(rusers);
                }
            }
        }
    }

    @Override
    public void deleteUserFromRoles(String employeeId, List<Long> roles) {
        if (roles != null) {
            RolesUsersExample example = new RolesUsersExample();
            for (Long trole_fk : roles) {
                example.createCriteria().andEmployeeIdEqualTo(employeeId).andTroleFkEqualTo(trole_fk);
                getRolesusersDao().deleteRolesUsersByExample(example);
                example.clear();
            }
        }
    }

    @Override
    public List<User> getUserAlreadyCreateWO() {
        List<User> result = userDao.getUserAlreadyCreateWO();
        return result;
    }

    @Override
    public List<String> getAllLeaveUser(String employeeId) {
        List<String> bossIds = new ArrayList<>();
        bossIds.add(employeeId);
        UserExample userExample = new UserExample();

        List<String> allEmployeeBelow = new ArrayList<>();
        Date today = new Date();
//		for(int i=level_value;i>-1;i--){
        while (!bossIds.isEmpty()) {
//                        if(bossIds.isEmpty()) continue;
            userExample.createCriteria().andBossemployeeidIn(bossIds)
                    .andBossemployeeidEqualTo(employeeId)
                    .andGrantfromLessThanOrEqualTo(today)
                    .andGranttoGreaterThanOrEqualTo(today);
            List<String> empIds = UserLocalServiceUtil.getEmployeeIdByExample(userExample);
            allEmployeeBelow.addAll(empIds);
            // update boss id
            bossIds.clear();
            bossIds.addAll(empIds);
            // clear criteria
            userExample.clear();
        }
        return allEmployeeBelow;
    }

    public boolean isFinanceTreasury(String employeeId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
