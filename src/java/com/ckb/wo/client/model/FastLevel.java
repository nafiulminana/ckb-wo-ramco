package com.ckb.wo.client.model;

import java.io.Serializable;

public class FastLevel implements Serializable {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_level.level_id
     *
     * @ibatorgenerated Thu Jul 01 20:09:01 SGT 2010
     */
    private String levelId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_level.description
     *
     * @ibatorgenerated Thu Jul 01 20:09:01 SGT 2010
     */
    private String description;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table t_level
     *
     * @ibatorgenerated Thu Jul 01 20:09:01 SGT 2010
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_level.level_id
     *
     * @return the value of t_level.level_id
     *
     * @ibatorgenerated Thu Jul 01 20:09:01 SGT 2010
     */
    public String getLevelId() {
        return levelId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_level.level_id
     *
     * @param levelId the value for t_level.level_id
     *
     * @ibatorgenerated Thu Jul 01 20:09:01 SGT 2010
     */
    public void setLevelId(String levelId) {
        this.levelId = levelId == null ? null : levelId.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_level.description
     *
     * @return the value of t_level.description
     *
     * @ibatorgenerated Thu Jul 01 20:09:01 SGT 2010
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_level.description
     *
     * @param description the value for t_level.description
     *
     * @ibatorgenerated Thu Jul 01 20:09:01 SGT 2010
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_level
     *
     * @ibatorgenerated Thu Jul 01 20:09:01 SGT 2010
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (!(that instanceof FastLevel)) {
            return false;
        }
        FastLevel other = (FastLevel) that;
        return this.getLevelId() == null ? other == null : this.getLevelId().equals(other.getLevelId())
            && this.getDescription() == null ? other == null : this.getDescription().equals(other.getDescription());
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_level
     *
     * @ibatorgenerated Thu Jul 01 20:09:01 SGT 2010
     */
    @Override
    public int hashCode() {
        int hash = 23;
        if (getLevelId() != null) {
            hash *= getLevelId().hashCode();
        }
        if (getDescription() != null) {
            hash *= getDescription().hashCode();
        }
        return hash;
    }
}