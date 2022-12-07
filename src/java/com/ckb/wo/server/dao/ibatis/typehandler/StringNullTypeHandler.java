package com.ckb.wo.server.dao.ibatis.typehandler;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

public class StringNullTypeHandler implements TypeHandlerCallback{

	private static final String EMPTY_STRING="";
	public Object getResult(ResultGetter arg0) throws SQLException {
		if(arg0.getString()==null){
			return EMPTY_STRING;
		}
		return arg0.getString();
	}

	public void setParameter(ParameterSetter arg0, Object arg1)
			throws SQLException {
		if(arg1==null){
			arg0.setString(EMPTY_STRING);
		}else{
			arg0.setString((String)arg1);
		}
		
	}

	public Object valueOf(String arg0) {
		if(arg0==null){
			return EMPTY_STRING;
		}
		return arg0;
	}

}
