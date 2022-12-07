package com.ckb.wo.server.service.impl;

import java.util.Iterator;
import java.util.List;

import com.ckb.wo.client.model.Area;
import com.ckb.wo.client.model.AreaExample;
import com.ckb.wo.client.model.Department;
import com.ckb.wo.client.model.Division;
import com.ckb.wo.client.model.FastDepartment;
import com.ckb.wo.client.model.FastDivision;
import com.ckb.wo.client.model.FastJobTitle;
import com.ckb.wo.client.model.FastLevel;
import com.ckb.wo.client.model.FastUser;
import com.ckb.wo.client.model.FastUserExample;
import com.ckb.wo.client.model.JobTitle;
import com.ckb.wo.client.model.Level;
import com.ckb.wo.client.model.User;
import com.ckb.wo.server.dao.AreaDAO;
import com.ckb.wo.server.dao.DepartmentDAO;
import com.ckb.wo.server.dao.DivisionDAO;
import com.ckb.wo.server.dao.JobTitleDAO;
import com.ckb.wo.server.dao.LevelDAO;
import com.ckb.wo.server.dao.UserDAO;
import com.ckb.wo.server.dao.fast.FastDepartmentDAO;
import com.ckb.wo.server.dao.fast.FastDivisionDAO;
import com.ckb.wo.server.dao.fast.FastJobTitleDAO;
import com.ckb.wo.server.dao.fast.FastLevelDAO;
import com.ckb.wo.server.dao.fast.FastUserDAO;
import com.ckb.wo.server.service.IFastService;
import com.ckb.wo.server.service.factory.BeanFactory;

public class FastServiceImpl extends GenericServiceImpl implements IFastService {

	private FastUserDAO fastuserDao;
	private FastLevelDAO fastlevelDao;
	private FastJobTitleDAO fastjobtitleDao;
	private FastDivisionDAO fastdivisionDao;
	private FastDepartmentDAO fastDepartmentDao;

	private UserDAO userDao;
	private AreaDAO areaDao;
	private LevelDAO levelDao;
	private JobTitleDAO jobtitleDao;
	private DivisionDAO divisionDao;
	private DepartmentDAO departmentDao;

	public FastServiceImpl(FastUserDAO fastuserDao, UserDAO userDao) {
		super();
		this.fastuserDao = fastuserDao;
		this.userDao = userDao;
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public AreaDAO getAreaDao() {
		if (areaDao == null) {
			areaDao = (AreaDAO) BeanFactory.getBean("areaDao");
		}
		return areaDao;
	}

	public void setAreaDao(AreaDAO areaDao) {
		this.areaDao = areaDao;
	}

	public LevelDAO getLevelDao() {
		if (levelDao == null) {
			levelDao = (LevelDAO) BeanFactory.getBean("levelDao");
		}
		return levelDao;
	}

	public void setLevelDao(LevelDAO levelDao) {
		this.levelDao = levelDao;
	}

	public JobTitleDAO getJobtitleDao() {
		if (jobtitleDao == null) {
			jobtitleDao = (JobTitleDAO) BeanFactory.getBean("jobtitleDao");
		}
		return jobtitleDao;
	}

	public void setJobtitleDao(JobTitleDAO jobtitleDao) {
		this.jobtitleDao = jobtitleDao;
	}

	public DivisionDAO getDivisionDao() {
		if (divisionDao == null) {
			divisionDao = (DivisionDAO) BeanFactory.getBean("divisionDao");
		}
		return divisionDao;
	}

	public void setDivisionDao(DivisionDAO divisionDao) {
		this.divisionDao = divisionDao;
	}

	public DepartmentDAO getDepartmentDao() {
		if (departmentDao == null) {
			departmentDao = (DepartmentDAO) BeanFactory
					.getBean("departmentDao");
		}
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}

	public FastUserDAO getFastuserDao() {
		return fastuserDao;
	}

	public void setFastuserDao(FastUserDAO fastuserDao) {
		this.fastuserDao = fastuserDao;
	}

	public FastLevelDAO getFastlevelDao() {
		if (fastlevelDao == null) {
			fastlevelDao = (FastLevelDAO) BeanFactory.getBean("fastlevelDao");
		}
		return fastlevelDao;
	}

	public void setFastlevelDao(FastLevelDAO fastlevelDao) {
		this.fastlevelDao = fastlevelDao;
	}

	public FastJobTitleDAO getFastjobtitleDao() {
		if (fastjobtitleDao == null) {
			fastjobtitleDao = (FastJobTitleDAO) BeanFactory
					.getBean("fastjobtitleDao");
		}
		return fastjobtitleDao;
	}

	public void setFastjobtitleDao(FastJobTitleDAO fastjobtitleDao) {
		this.fastjobtitleDao = fastjobtitleDao;
	}

	public FastDivisionDAO getFastdivisionDao() {
		if (fastdivisionDao == null) {
			fastdivisionDao = (FastDivisionDAO) BeanFactory
					.getBean("fastdivisionDao");
		}
		return fastdivisionDao;
	}

	public void setFastdivisionDao(FastDivisionDAO fastdivisionDao) {
		this.fastdivisionDao = fastdivisionDao;
	}

	public FastDepartmentDAO getFastDepartmentDao() {
		if (fastDepartmentDao == null) {
			fastDepartmentDao = (FastDepartmentDAO) BeanFactory
					.getBean("fastdepartmentDao");
		}
		return fastDepartmentDao;
	}

	public void setFastDepartmentDao(FastDepartmentDAO fastDepartmentDao) {
		this.fastDepartmentDao = fastDepartmentDao;
	}

	/* (non-Javadoc)
	 * @see com.ckb.wo.server.service.impl.IFastService#getFastUserByExample(com.ckb.wo.client.model.FastUserExample)
	 */
	public List<FastUser> getFastUserByExample(FastUserExample example) {
		return getFastuserDao().selectFastUserByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.ckb.wo.server.service.impl.IFastService#countFastUserByExample(com.ckb.wo.client.model.FastUserExample)
	 */
	public int countFastUserByExample(FastUserExample example) {
		return getFastuserDao().countFastUserByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.ckb.wo.server.service.impl.IFastService#getFastUserByIds(java.util.List)
	 */
	public List<FastUser> getFastUserByIds(List<String> employee_Ids) {
		FastUserExample example = new FastUserExample();
		example.createCriteria().andEmployeeIdIn(employee_Ids);
		return getFastuserDao().selectFastUserByExample(example);
	}

	/* (non-Javadoc)
	 * @see com.ckb.wo.server.service.impl.IFastService#importFastUserByIds(java.util.List)
	 */
	public void importFastUserByIds(List<String> employee_Ids) {
		// select fast user
		if (employee_Ids == null || employee_Ids.size() == 0) {
			return;
		}
		List<FastUser> lfuser = getFastUserByIds(employee_Ids);
		for (Iterator iterator = lfuser.iterator(); iterator.hasNext();) {
			FastUser fastUser = (FastUser) iterator.next();
			// check if user already exist in WO
			User user = getUserDao().selectUserByPrimaryKey(
					fastUser.getEmployeeId());
			if (user == null) {
				user = new User();
				user.setAreaId(fastUser.getAreaId());
				user.setDepartmentId(fastUser.getDepartmentId());
				user.setDivisionId(fastUser.getDivisionId());
				user.setEmail(fastUser.getEmail());
				user.setEmployeeId(fastUser.getEmployeeId());
				user.setFirstName(fastUser.getFirstName());
				user.setMiddleName(fastUser.getMiddleName());
				user.setLastName(fastUser.getLastName());
				user.setJobTitleId(fastUser.getJobTitleId());
				user.setLevelId(fastUser.getLevelId());
				user.setStationId(fastUser.getStationId());
				user.setIsactiveuser(true);
				getUserDao().insertUser(user);
				insertAreaIfNotExist(fastUser.getAreaId());
				insertDivisionIfNotExist(fastUser.getDivisionId());
				insertDepartmentIfNotExist(fastUser.getDepartmentId());
				insertJobTitleIfNotExist(fastUser.getJobTitleId());
				insertLevelIfNotExist(fastUser.getLevelId());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.ckb.wo.server.service.impl.IFastService#updateBoss(java.lang.String, java.util.List)
	 */
	public void updateBoss(String bossEmployeeId, List<String> slaveEmployeeIds) {

		if (bossEmployeeId == null || slaveEmployeeIds == null
				|| bossEmployeeId.length() == 0 || slaveEmployeeIds.size() == 0) {
			return;
		}

	}

	/* (non-Javadoc)
	 * @see com.ckb.wo.server.service.impl.IFastService#updateBoss(java.lang.String, java.lang.String)
	 */
	public void updateBoss(String bossEmployeeId, String slaveEmployeeId) {
		if (bossEmployeeId == null || bossEmployeeId.length() == 0
				|| slaveEmployeeId == null || slaveEmployeeId.length() == 0) {
			return;
		}
		User bossUser = getUserDao().selectUserByPrimaryKey(bossEmployeeId);
		User slaveUser = getUserDao().selectUserByPrimaryKey(slaveEmployeeId);
		if(bossUser!=null && slaveUser!=null){
			slaveUser.setBossemployeeid(bossUser.getBossemployeeid());
			getUserDao().updateUserByPrimaryKey(slaveUser);
		}
	}

	public void insertLevelIfNotExist(String levelId) {
		if (levelId == null || levelId.length() == 0) {
			return;
		}
		Level level = getLevelDao().selectLevelByPrimaryKey(levelId);
		if (level == null) {
			FastLevel fastlvl = getFastlevelDao().selectFastLevelByPrimaryKey(
					levelId);
			level = new Level();
			level.setLevelId(levelId);
			level.setDescription(fastlvl.getDescription());
			level.setLevelValue(new Integer(-1));
			getLevelDao().insertLevel(level);
		}
	}

	public void insertJobTitleIfNotExist(String jobtitleId) {
		if (jobtitleId == null || jobtitleId.length() == 0) {
			return;
		}
		JobTitle jobtitle = getJobtitleDao().selectJobTitleByPrimaryKey(
				jobtitleId);
		if (jobtitle == null) {
			FastJobTitle fastjt = getFastjobtitleDao()
					.selectFastJobTitleByPrimaryKey(jobtitleId);
			jobtitle = new JobTitle();
			jobtitle.setJobTitleId(jobtitleId);
			jobtitle.setDescription(fastjt.getDescription());
			getJobtitleDao().insertJobTitle(jobtitle);
		}
	}

	public void insertDepartmentIfNotExist(String deptId) {
		if (deptId == null || deptId.length() == 0) {
			return;
		}
		Department department = getDepartmentDao()
				.selectDepartmentByPrimaryKey(deptId);
		if (department == null) {
			FastDepartment fastdept = getFastDepartmentDao()
					.selectFastDepartmentByPrimaryKey(deptId);
			department = new Department();
			department.setDepartmentId(fastdept.getDepartmentId());
			department.setDepartmentname(fastdept.getDescription());
			getDepartmentDao().insertDepartment(department);
		}
	}

	public void insertDivisionIfNotExist(String divisionId) {
		if (divisionId == null || divisionId.length() == 0) {
			return;
		}
		Division division = getDivisionDao().selectDivisionByPrimaryKey(
				divisionId);
		if (division == null) {
			FastDivision fastdiv = getFastdivisionDao()
					.selectFastDivisionByPrimaryKey(divisionId);
			division = new Division();
			division.setDivisionId(fastdiv.getDivisionId());
			division.setDescription(fastdiv.getDescription());
			getDivisionDao().insertDivision(division);
		}
	}

	public void insertAreaIfNotExist(String areaCode) {
		if (areaCode == null || areaCode.length() == 0) {
			return;
		}
		AreaExample example = new AreaExample();
		example.createCriteria().andAreaIdEqualTo(areaCode);
		int count = getAreaDao().countAreaByExample(example);
		if (count == 0) {
			Area area = new Area();
			area.setAreaId(areaCode);
			area.setAreaname(areaCode);
			getAreaDao().insertArea(area);
		}
	}
}
