package com.ckb.wo.client.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ManifestWeightVolumeDetail implements Serializable {

    private Long manifest_no;
    private String ori;
    private String dest;
    private Date ship_date;
    private Date actual_ship_date;
    private Integer jmlh_da;
    private BigDecimal weight;
    private BigDecimal volume;
    private String carrier_ownership_id;
    private String moda;
    private String vendor_id;

    public Long getManifest_no() {
        return manifest_no;
    }

    public void setManifest_no(Long manifestNo) {
        manifest_no = manifestNo;
    }

    public String getOri() {
        return ori;
    }

    public void setOri(String ori) {
        this.ori = ori;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public Date getShip_date() {
        return ship_date;
    }

    public void setShip_date(Date shipDate) {
        ship_date = shipDate;
    }

    public Date getActual_ship_date() {
        return actual_ship_date;
    }

    public void setActual_ship_date(Date actualShipDate) {
        actual_ship_date = actualShipDate;
    }

    public Integer getJmlh_da() {
        return jmlh_da;
    }

    public void setJmlh_da(Integer jmlhDa) {
        jmlh_da = jmlhDa;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public String getCarrier_ownership_id() {
        return carrier_ownership_id;
    }

    public void setCarrier_ownership_id(String carrier_ownership_id) {
        this.carrier_ownership_id = carrier_ownership_id;
    }

    public String getModa() {
        return moda;
    }

    public void setModa(String moda) {
        this.moda = moda;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime
                * result
                + ((actual_ship_date == null) ? 0 : actual_ship_date.hashCode());
        result = prime * result + ((dest == null) ? 0 : dest.hashCode());
        result = prime * result + ((jmlh_da == null) ? 0 : jmlh_da.hashCode());
        result = prime * result
                + ((manifest_no == null) ? 0 : manifest_no.hashCode());
        result = prime * result + ((ori == null) ? 0 : ori.hashCode());
        result = prime * result
                + ((ship_date == null) ? 0 : ship_date.hashCode());
        result = prime * result + ((volume == null) ? 0 : volume.hashCode());
        result = prime * result + ((weight == null) ? 0 : weight.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ManifestWeightVolumeDetail other = (ManifestWeightVolumeDetail) obj;
        if (actual_ship_date == null) {
            if (other.actual_ship_date != null) {
                return false;
            }
        } else if (!actual_ship_date.equals(other.actual_ship_date)) {
            return false;
        }
        if (dest == null) {
            if (other.dest != null) {
                return false;
            }
        } else if (!dest.equals(other.dest)) {
            return false;
        }
        if (jmlh_da == null) {
            if (other.jmlh_da != null) {
                return false;
            }
        } else if (!jmlh_da.equals(other.jmlh_da)) {
            return false;
        }
        if (manifest_no == null) {
            if (other.manifest_no != null) {
                return false;
            }
        } else if (!manifest_no.equals(other.manifest_no)) {
            return false;
        }
        if (ori == null) {
            if (other.ori != null) {
                return false;
            }
        } else if (!ori.equals(other.ori)) {
            return false;
        }
        if (ship_date == null) {
            if (other.ship_date != null) {
                return false;
            }
        } else if (!ship_date.equals(other.ship_date)) {
            return false;
        }
        if (volume == null) {
            if (other.volume != null) {
                return false;
            }
        } else if (!volume.equals(other.volume)) {
            return false;
        }
        if (weight == null) {
            if (other.weight != null) {
                return false;
            }
        } else if (!weight.equals(other.weight)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ManifestWeightVolumeDetail{" + "manifest_no=" + manifest_no + ", ori=" + ori + ", dest=" + dest + ", ship_date=" + ship_date + ", actual_ship_date=" + actual_ship_date + ", jmlh_da=" + jmlh_da + ", weight=" + weight + ", volume=" + volume + ", carrier_ownership_id=" + carrier_ownership_id + ", moda=" + moda + ", vendor_id=" + vendor_id + '}';
    }

}
