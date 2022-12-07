package com.ckb.wo.server.service.util;

import java.util.List;

import com.ckb.wo.client.model.Level;
import com.ckb.wo.client.model.LevelExample;
import com.ckb.wo.server.service.ILevelService;

public class LevelLocalServiceUtil extends GenericServiceUtil {

	public static int countLevel(){
		return getLevelService().countLevel();
	}
	
	public static List<Level> getLevel(){
		return getLevelService().getLevel();
	}
	
	public static List<Level> getLevelByExample(LevelExample example){
		return getLevelService().getLevelByExample(example);
	}
	
	public static int countLevelByExample(LevelExample example){
		return getLevelService().countLevelByExample(example);
	}
	
	public static int deleteLevelByPrimaryKey(String levelPk){
		return getLevelService().deleteLevel(levelPk);
	}
	
	public static int updateLevel(Level level){
		return getLevelService().updateLevel(level);
	}
	
	public static void insertLevel(Level level){
		getLevelService().insertLevel(level);	
	}
	
	public static Level getLevelByPrimaryKey(String levelPk){
		return getLevelService().getLevelyByPrimaryKey(levelPk);
	}
	
	private static ILevelService getLevelService(){
		return (ILevelService) getBean("levelService");
	}
}
