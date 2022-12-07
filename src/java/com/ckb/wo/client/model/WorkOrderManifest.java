package com.ckb.wo.client.model;

import java.io.Serializable;

import com.ckb.wo.server.service.util.ManifestDALocalServiceUtil;

public class WorkOrderManifest implements Serializable {

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds
     * to the database column tworkorder_manifest.tworkorder_manifest_pk
     *
     * @ibatorgenerated Mon Jul 05 17:58:15 SGT 2010
     */
    private Long tworkorderManifestPk;

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds
     * to the database column tworkorder_manifest.tworkorder_fk
     *
     * @ibatorgenerated Mon Jul 05 17:58:15 SGT 2010
     */
    private Long tworkorderFk;

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds
     * to the database column tworkorder_manifest.manifest_no
     *
     * @ibatorgenerated Mon Jul 05 17:58:15 SGT 2010
     */
    private Long manifestNo;

    /**
     * This field was generated by Apache iBATIS ibator. This field corresponds
     * to the database table tworkorder_manifest
     *
     * @ibatorgenerated Mon Jul 05 17:58:15 SGT 2010
     */
    private static final long serialVersionUID = 1L;

    private MdaManifest manifest;
    private ManifestWeightVolumeDetail manifestweightvolumedetail;

    /**
     * This method was generated by Apache iBATIS ibator. This method returns
     * the value of the database column
     * tworkorder_manifest.tworkorder_manifest_pk
     *
     * @return the value of tworkorder_manifest.tworkorder_manifest_pk
     *
     * @ibatorgenerated Mon Jul 05 17:58:15 SGT 2010
     */
    public Long getTworkorderManifestPk() {
        return tworkorderManifestPk;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the
     * value of the database column tworkorder_manifest.tworkorder_manifest_pk
     *
     * @param tworkorderManifestPk the value for
     * tworkorder_manifest.tworkorder_manifest_pk
     *
     * @ibatorgenerated Mon Jul 05 17:58:15 SGT 2010
     */
    public void setTworkorderManifestPk(Long tworkorderManifestPk) {
        this.tworkorderManifestPk = tworkorderManifestPk;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns
     * the value of the database column tworkorder_manifest.tworkorder_fk
     *
     * @return the value of tworkorder_manifest.tworkorder_fk
     *
     * @ibatorgenerated Mon Jul 05 17:58:15 SGT 2010
     */
    public Long getTworkorderFk() {
        return tworkorderFk;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the
     * value of the database column tworkorder_manifest.tworkorder_fk
     *
     * @param tworkorderFk the value for tworkorder_manifest.tworkorder_fk
     *
     * @ibatorgenerated Mon Jul 05 17:58:15 SGT 2010
     */
    public void setTworkorderFk(Long tworkorderFk) {
        this.tworkorderFk = tworkorderFk;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method returns
     * the value of the database column tworkorder_manifest.manifest_no
     *
     * @return the value of tworkorder_manifest.manifest_no
     *
     * @ibatorgenerated Mon Jul 05 17:58:15 SGT 2010
     */
    public Long getManifestNo() {
        return manifestNo;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method sets the
     * value of the database column tworkorder_manifest.manifest_no
     *
     * @param manifestNo the value for tworkorder_manifest.manifest_no
     *
     * @ibatorgenerated Mon Jul 05 17:58:15 SGT 2010
     */
    public void setManifestNo(Long manifestNo) {
        this.manifestNo = manifestNo;
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorder_manifest
     *
     * @param that
     * @ibatorgenerated Mon Jul 05 17:58:15 SGT 2010
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (!(that instanceof WorkOrderManifest)) {
            return false;
        }
        WorkOrderManifest other = (WorkOrderManifest) that;
        return this.getTworkorderManifestPk() == null ? other == null : this.getTworkorderManifestPk().equals(other.getTworkorderManifestPk())
                && this.getTworkorderFk() == null ? other == null : this.getTworkorderFk().equals(other.getTworkorderFk())
                && this.getManifestNo() == null ? other == null : this.getManifestNo().equals(other.getManifestNo());
    }

    /**
     * This method was generated by Apache iBATIS ibator. This method
     * corresponds to the database table tworkorder_manifest
     *
     * @ibatorgenerated Mon Jul 05 17:58:15 SGT 2010
     */
    @Override
    public int hashCode() {
        int hash = 23;
        if (getTworkorderManifestPk() != null) {
            hash *= getTworkorderManifestPk().hashCode();
        }
        if (getTworkorderFk() != null) {
            hash *= getTworkorderFk().hashCode();
        }
        if (getManifestNo() != null) {
            hash *= getManifestNo().hashCode();
        }
        return hash;
    }

    public MdaManifest getManifest() {
        if (manifest == null && manifestNo != null) {
            manifest = ManifestDALocalServiceUtil.getManifestByPrimaryKey(manifestNo);
        }
        return manifest;
    }

    public void setManifest(MdaManifest manifest) {
        this.manifest = manifest;
    }

    public ManifestWeightVolumeDetail getManifestweightvolumedetail() {
        return manifestweightvolumedetail;
    }

    public void setManifestweightvolumedetail(
            ManifestWeightVolumeDetail manifestweightvolumedetail) {
        this.manifestweightvolumedetail = manifestweightvolumedetail;
    }

    @Override
    public String toString() {
        return "WorkOrderManifest{" + "tworkorderManifestPk=" + tworkorderManifestPk + ", tworkorderFk=" + tworkorderFk + ", manifestNo=" + manifestNo + ", manifest=" + manifest + ", manifestweightvolumedetail=" + manifestweightvolumedetail + '}';
    }
}
