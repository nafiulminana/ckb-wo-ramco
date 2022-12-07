package com.ckb.wo.server.service.impl;

import java.util.List;

import com.ckb.wo.client.model.Level;
import com.ckb.wo.client.model.LevelExample;
import com.ckb.wo.server.dao.LevelDAO;
import com.ckb.wo.server.service.ILevelService;

public class LevelServiceImpl extends GenericServiceImpl implements ILevelService{
	private LevelDAO levelDao;
	
	
	public LevelServiceImpl(LevelDAO levelDao) {
		super();
		this.levelDao = levelDao;
	}

	public List<Level> getLevel() {
		LevelExample example = new LevelExample();
		return getLevelDao().selectLevelByExample(example);
	}
	
	public int countLevel(){
		LevelExample example = new LevelExample();
		return getLevelDao().countLevelByExample(example);
	}
	
	
	public List<Level> getLevelByExample(LevelExample example){
		return getLevelDao().selectLevelByExample(example);
	}
	
	public int countLevelByExample(LevelExample example){		
		return getLevelDao().countLevelByExample(example);
	}

	public int deleteLevel(String levelPk){		
		return getLevelDao().deleteLevelByPrimaryKey(levelPk);
	}
	
	public void insertLevel(Level level){
		 getLevelDao().insertLevel(level);
	}
	
	public int updateLevel(Level level){
		return getLevelDao().updateLevelByPrimaryKey(level);		
	}

	public List<Level> getLevelByExample(LevelExample example, int pageNo,
			int rowPerPage) {
		example.setLimitClause(setOffset(pageNo, rowPerPage));
		return getLevelDao().selectLevelByExample(example);
	}
	
	public Level getLevelyByPrimaryKey(String levelPk){
		return getLevelDao().selectLevelByPrimaryKey(levelPk);		
	}
	
	public List<Level> getApprovalTypeWithContent(int maxLevel){
		return getLevelDao().selectApprovalTypeWithContent(maxLevel);
	}

	public LevelDAO getLevelDao() {
		return levelDao;
	}

	public void setLevelDao(LevelDAO levelDao) {
		this.levelDao = levelDao;
	}
}
