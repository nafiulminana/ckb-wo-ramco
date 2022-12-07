package com.ckb.wo.server.service;

import com.ckb.wo.client.model.UserAccess;

public interface ILoginService {

	public UserAccess login(String userId, String password);

}