package com.ckb.wo.client.model;

import java.io.Serializable;


public class OriContainer implements Serializable {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column oricontainer.containerdesc
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	private String containerdesc;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column oricontainer.servicemode
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	private String servicemode;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column oricontainer.containercode
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	private String containercode;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table oricontainer
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column oricontainer.containerdesc
	 * @return  the value of oricontainer.containerdesc
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public String getContainerdesc() {
		return containerdesc;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column oricontainer.containerdesc
	 * @param containerdesc  the value for oricontainer.containerdesc
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public void setContainerdesc(String containerdesc) {
		this.containerdesc = containerdesc == null ? null : containerdesc
				.trim();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column oricontainer.servicemode
	 * @return  the value of oricontainer.servicemode
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public String getServicemode() {
		return servicemode;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column oricontainer.servicemode
	 * @param servicemode  the value for oricontainer.servicemode
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public void setServicemode(String servicemode) {
		this.servicemode = servicemode == null ? null : servicemode.trim();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column oricontainer.containercode
	 * @return  the value of oricontainer.containercode
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public String getContainercode() {
		return containercode;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column oricontainer.containercode
	 * @param containercode  the value for oricontainer.containercode
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	public void setContainercode(String containercode) {
		this.containercode = containercode == null ? null : containercode
				.trim();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table oricontainer
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (!(that instanceof OriContainer)) {
			return false;
		}
		OriContainer other = (OriContainer) that;
		return this.getContainerdesc() == null ? other == null : this
				.getContainerdesc().equals(other.getContainerdesc())
				&& this.getServicemode() == null ? other == null : this
				.getServicemode().equals(other.getServicemode())
				&& this.getContainercode() == null ? other == null : this
				.getContainercode().equals(other.getContainercode());
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table oricontainer
	 * @ibatorgenerated  Tue Jun 29 17:37:52 SGT 2010
	 */
	@Override
	public int hashCode() {
		int hash = 23;
		if (getContainerdesc() != null) {
			hash *= getContainerdesc().hashCode();
		}
		if (getServicemode() != null) {
			hash *= getServicemode().hashCode();
		}
		if (getContainercode() != null) {
			hash *= getContainercode().hashCode();
		}
		return hash;
	}
}