package com.ckb.wo.server.service.util;

import java.util.List;

import com.ckb.wo.client.model.User;
import com.ckb.wo.client.model.UserExample;
import com.ckb.wo.server.service.IUserService;
import com.ckb.wo.server.service.factory.BeanFactory;

public class UserLocalServiceUtil extends GenericServiceUtil {

	private static IUserService getUserService(){
		return (IUserService) BeanFactory.getBean("userService");
	}
	
	public static List<User> getUser(){
		return getUserService().getUser();
	}

	public static int countUser(){
		return getUserService().countUser();
	}

	public static List<User> getUserByExample(UserExample example){
		return getUserService().getUserByExample(example);
	}

	public static int countUserByExample(UserExample example){
		return getUserService().countUserByExample(example);	
	}

	public static int deleteUserByPrimaryKey(String userPk){
		return getUserService().deleteUser(userPk);
	}

	public static void insertUser(User user){
		getUserService().insertUser(user);
	}

	public static int updateUser(User user){
		return getUserService().updateUser(user);
	}


	public static User getUserByPrimaryKey(String userPk){
		return getUserService().getUserByPrimaryKey(userPk);
	}
	
	public static List<String> getAllStaffForThisUser(String userPk){
		return null;
	}
	
	public static List<String> getEmployeeIdByExample(UserExample example){
		return getUserService().getEmployeeIdByExample(example);
	}
	
	public static Integer getUserLevelValueByEmployeeId(String employeeId){
		return getUserService().getUserLevelValueByEmployeeId(employeeId);		
	}
	
	public static boolean isProcurementUser(String employeeId){
		return getUserService().isProcurementUser(employeeId);
	}
	
	public static boolean isOperationUser(String employeeId){
		return getUserService().isOperationUser(employeeId);
	}
	
	public static User getFinalUserApprovalWO(Long tworkorderPk){
		return getUserService().getFinalUserApprovalWO(tworkorderPk);
	}

        public static User getFinalUserCancellationWO(Long tworkorderPk){
            return getUserService().getFinalUserCancellationWO(tworkorderPk);
        }


	public static User getUserByPrimaryKeyWithJoin(String employeeId){
		return getUserService().getUserByPrimaryKeyWithJoin(employeeId);
	}
	
	public static List<User> getUserByExampleWithJoin(UserExample example){
		return getUserService().getUserByExampleWithJoin(example);
	}
	
	public static User getUserByPrimaryKeyWithRoles(String employeeId){
		return getUserService().getUserByPrimaryKeyWithRoles(employeeId);
	}
	
	public static List<User> getUserByExampleWithRoles(UserExample example){
		return getUserService().getUserByExampleWithRoles(example);
	}
	
	public static List<User> getUsersOneLevelAbove(String employeeId, String limitClause, String orderByClause){
		return getUserService().getUsersOneLevelAbove(employeeId, limitClause, orderByClause);
	}
	
	public static int countUsersOneLevelAbove(String employeeId){
		return getUserService().countUsersOneLevelAbove(employeeId);
	}

    public static void insertUserToRoles(String employeeId, List<Long> roles){
        getUserService().insertUserToRoles(employeeId, roles);
    }
    
    public static void deleteUserFromRoles(String employeeId, List<Long> roles){
    	getUserService().deleteUserFromRoles(employeeId, roles);
    }
    public static List<User> getUserAlreadyCreateWO(){
        return getUserService().getUserAlreadyCreateWO();
    }
}
