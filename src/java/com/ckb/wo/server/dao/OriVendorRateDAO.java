package com.ckb.wo.server.dao;

import com.ckb.wo.client.model.OriVendorRate;
import com.ckb.wo.client.model.OriVendorRateExample;
import java.util.List;

public interface OriVendorRateDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table orivendorrate
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	int countOriVendorRateByExample(OriVendorRateExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table orivendorrate
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	int deleteOriVendorRateByExample(OriVendorRateExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table orivendorrate
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	void insertOriVendorRate(OriVendorRate record);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table orivendorrate
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	void insertOriVendorRateSelective(OriVendorRate record);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table orivendorrate
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	List<OriVendorRate> selectOriVendorRateByExample(
			OriVendorRateExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table orivendorrate
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	int updateOriVendorRateByExampleSelective(OriVendorRate record,
			OriVendorRateExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table orivendorrate
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	int updateOriVendorRateByExample(OriVendorRate record,
			OriVendorRateExample example);
}