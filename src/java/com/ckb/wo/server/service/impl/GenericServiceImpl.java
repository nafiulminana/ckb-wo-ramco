package com.ckb.wo.server.service.impl;

public class GenericServiceImpl {

	protected String setOffset(int pageNo, int offset){
		if(pageNo<1){
			pageNo=1;
		}else{
			pageNo=pageNo-1;
		}
		if(offset<0){
			offset=10;
		}
		return (pageNo+","+offset);
	}

}
