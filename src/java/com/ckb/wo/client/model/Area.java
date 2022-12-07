package com.ckb.wo.client.model;

import java.io.Serializable;

public class Area implements Serializable {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column tarea.area_id
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    private String areaId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column tarea.areaname
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    private String areaname;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table tarea
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column tarea.area_id
     *
     * @return the value of tarea.area_id
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public String getAreaId() {
        return areaId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column tarea.area_id
     *
     * @param areaId the value for tarea.area_id
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column tarea.areaname
     *
     * @return the value of tarea.areaname
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public String getAreaname() {
        return areaname;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column tarea.areaname
     *
     * @param areaname the value for tarea.areaname
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    public void setAreaname(String areaname) {
        this.areaname = areaname == null ? null : areaname.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tarea
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (!(that instanceof Area)) {
            return false;
        }
        Area other = (Area) that;
        return this.getAreaId() == null ? other == null : this.getAreaId().equals(other.getAreaId())
            && this.getAreaname() == null ? other == null : this.getAreaname().equals(other.getAreaname());
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table tarea
     *
     * @ibatorgenerated Thu Jul 01 20:08:58 SGT 2010
     */
    @Override
    public int hashCode() {
        int hash = 23;
        if (getAreaId() != null) {
            hash *= getAreaId().hashCode();
        }
        if (getAreaname() != null) {
            hash *= getAreaname().hashCode();
        }
        return hash;
    }
}