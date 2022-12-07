package com.ckb.wo.server.service;

import java.util.List;

import com.ckb.wo.client.model.Level;
import com.ckb.wo.client.model.LevelExample;

public interface ILevelService {

	public List<Level> getLevel();

	public int countLevel();

	public List<Level> getLevelByExample(LevelExample example);

	public int countLevelByExample(LevelExample example);

	public int deleteLevel(String levelPk);

	public void insertLevel(Level level);

	public int updateLevel(Level level);

	public List<Level> getLevelByExample(LevelExample example, int pageNo,
			int rowPerPage);

	public Level getLevelyByPrimaryKey(String levelPk);

	public List<Level> getApprovalTypeWithContent(int maxLevel);

}