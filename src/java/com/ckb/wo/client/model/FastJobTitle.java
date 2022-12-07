package com.ckb.wo.client.model;

import java.io.Serializable;

public class FastJobTitle implements Serializable {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_job_title.job_title_id
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    private String jobTitleId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column t_job_title.description
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    private String description;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table t_job_title
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_job_title.job_title_id
     *
     * @return the value of t_job_title.job_title_id
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    public String getJobTitleId() {
        return jobTitleId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_job_title.job_title_id
     *
     * @param jobTitleId the value for t_job_title.job_title_id
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    public void setJobTitleId(String jobTitleId) {
        this.jobTitleId = jobTitleId == null ? null : jobTitleId.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column t_job_title.description
     *
     * @return the value of t_job_title.description
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column t_job_title.description
     *
     * @param description the value for t_job_title.description
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_job_title
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (!(that instanceof FastJobTitle)) {
            return false;
        }
        FastJobTitle other = (FastJobTitle) that;
        return this.getJobTitleId() == null ? other == null : this.getJobTitleId().equals(other.getJobTitleId())
            && this.getDescription() == null ? other == null : this.getDescription().equals(other.getDescription());
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table t_job_title
     *
     * @ibatorgenerated Thu Jul 01 20:09:00 SGT 2010
     */
    @Override
    public int hashCode() {
        int hash = 23;
        if (getJobTitleId() != null) {
            hash *= getJobTitleId().hashCode();
        }
        if (getDescription() != null) {
            hash *= getDescription().hashCode();
        }
        return hash;
    }
}