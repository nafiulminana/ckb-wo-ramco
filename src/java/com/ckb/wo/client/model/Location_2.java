//package com.ckb.wo.client.model;
//
//import java.io.Serializable;
//
//
//public class Location implements Serializable {
//
//	/**
//	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column tlocation.tlocation_pk
//	 * @ibatorgenerated  Mon Jul 26 17:59:51 SGT 2010
//	 */
//	private Long tlocationPk;
//	/**
//	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column tlocation.locationcode
//	 * @ibatorgenerated  Mon Jul 26 17:59:51 SGT 2010
//	 */
//	private String locationcode;
//	/**
//	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column tlocation.locationname
//	 * @ibatorgenerated  Mon Jul 26 17:59:51 SGT 2010
//	 */
//	private String locationname;
//	/**
//	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column tlocation.detailaddress
//	 * @ibatorgenerated  Mon Jul 26 17:59:51 SGT 2010
//	 */
//	private String detailaddress;
//	/**
//	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column tlocation.cityname
//	 * @ibatorgenerated  Mon Jul 26 17:59:51 SGT 2010
//	 */
//	private String cityname;
//	/**
//	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table tlocation
//	 * @ibatorgenerated  Mon Jul 26 17:59:51 SGT 2010
//	 */
//	private static final long serialVersionUID = 1L;
//
//	/**
//	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column tlocation.tlocation_pk
//	 * @return  the value of tlocation.tlocation_pk
//	 * @ibatorgenerated  Mon Jul 26 17:59:51 SGT 2010
//	 */
//	public Long getTlocationPk() {
//		return tlocationPk;
//	}
//
//	/**
//	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column tlocation.tlocation_pk
//	 * @param tlocationPk  the value for tlocation.tlocation_pk
//	 * @ibatorgenerated  Mon Jul 26 17:59:51 SGT 2010
//	 */
//	public void setTlocationPk(Long tlocationPk) {
//		this.tlocationPk = tlocationPk;
//	}
//
//	/**
//	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column tlocation.locationcode
//	 * @return  the value of tlocation.locationcode
//	 * @ibatorgenerated  Mon Jul 26 17:59:51 SGT 2010
//	 */
//	public String getLocationcode() {
//		return locationcode;
//	}
//
//	/**
//	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column tlocation.locationcode
//	 * @param locationcode  the value for tlocation.locationcode
//	 * @ibatorgenerated  Mon Jul 26 17:59:51 SGT 2010
//	 */
//	public void setLocationcode(String locationcode) {
//		this.locationcode = locationcode == null ? null : locationcode.trim();
//	}
//
//	/**
//	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column tlocation.locationname
//	 * @return  the value of tlocation.locationname
//	 * @ibatorgenerated  Mon Jul 26 17:59:51 SGT 2010
//	 */
//	public String getLocationname() {
//		return locationname;
//	}
//
//	/**
//	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column tlocation.locationname
//	 * @param locationname  the value for tlocation.locationname
//	 * @ibatorgenerated  Mon Jul 26 17:59:51 SGT 2010
//	 */
//	public void setLocationname(String locationname) {
//		this.locationname = locationname == null ? null : locationname.trim();
//	}
//
//	/**
//	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column tlocation.detailaddress
//	 * @return  the value of tlocation.detailaddress
//	 * @ibatorgenerated  Mon Jul 26 17:59:51 SGT 2010
//	 */
//	public String getDetailaddress() {
//		return detailaddress;
//	}
//
//	/**
//	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column tlocation.detailaddress
//	 * @param detailaddress  the value for tlocation.detailaddress
//	 * @ibatorgenerated  Mon Jul 26 17:59:51 SGT 2010
//	 */
//	public void setDetailaddress(String detailaddress) {
//		this.detailaddress = detailaddress == null ? null : detailaddress
//				.trim();
//	}
//
//	/**
//	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column tlocation.cityname
//	 * @return  the value of tlocation.cityname
//	 * @ibatorgenerated  Mon Jul 26 17:59:51 SGT 2010
//	 */
//	public String getCityname() {
//		return cityname;
//	}
//
//	/**
//	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column tlocation.cityname
//	 * @param cityname  the value for tlocation.cityname
//	 * @ibatorgenerated  Mon Jul 26 17:59:51 SGT 2010
//	 */
//	public void setCityname(String cityname) {
//		this.cityname = cityname == null ? null : cityname.trim();
//	}
//
//	/**
//	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tlocation
//	 * @ibatorgenerated  Mon Jul 26 17:59:51 SGT 2010
//	 */
//	@Override
//	public boolean equals(Object that) {
//		if (this == that) {
//			return true;
//		}
//		if (!(that instanceof Location)) {
//			return false;
//		}
//		Location other = (Location) that;
//		return this.getTlocationPk() == null ? other == null : this
//				.getTlocationPk().equals(other.getTlocationPk())
//				&& this.getLocationcode() == null ? other == null : this
//				.getLocationcode().equals(other.getLocationcode())
//				&& this.getLocationname() == null ? other == null : this
//				.getLocationname().equals(other.getLocationname())
//				&& this.getDetailaddress() == null ? other == null : this
//				.getDetailaddress().equals(other.getDetailaddress())
//				&& this.getCityname() == null ? other == null : this
//				.getCityname().equals(other.getCityname());
//	}
//
//	/**
//	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table tlocation
//	 * @ibatorgenerated  Mon Jul 26 17:59:51 SGT 2010
//	 */
//	@Override
//	public int hashCode() {
//		int hash = 23;
//		if (getTlocationPk() != null) {
//			hash *= getTlocationPk().hashCode();
//		}
//		if (getLocationcode() != null) {
//			hash *= getLocationcode().hashCode();
//		}
//		if (getLocationname() != null) {
//			hash *= getLocationname().hashCode();
//		}
//		if (getDetailaddress() != null) {
//			hash *= getDetailaddress().hashCode();
//		}
//		if (getCityname() != null) {
//			hash *= getCityname().hashCode();
//		}
//		return hash;
//	}
//
//	@Override
//	public String toString() {
//		return "Location [locationcode=" + locationcode + ", locationname="
//				+ locationname + ", tlocationPk=" + tlocationPk + "]";
//	}
//}