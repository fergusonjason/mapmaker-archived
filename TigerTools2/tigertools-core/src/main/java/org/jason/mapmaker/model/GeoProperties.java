package org.jason.mapmaker.model;

import java.io.Serializable;

/**
 * @author Jason
 */
public class GeoProperties implements Serializable {

     private String region;
     private String division;
     private String stateFP;
     private String stateNS;
     private String geoId;
     private String stusPs;
     private String name;
     private String lsad;
     private String mtfcc;
     private String funcStat;
     private Long landArea;
     private Long waterArea;
     private String intPtLat;
     private String intPtLng;

     private String countyFP;
     private String countyNs;
     private String nameLsad;
     private String classMp;
     private String csafp;
     private String cbsafp;
     private String metdivfp;

    private String cousubFp;
    private String cousubNs;
    private String cnectaFp;
    private String nectaFp;
    private String nctadvFp;

    private String urbanRural;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getStateFP() {
        return stateFP;
    }

    public void setStateFP(String stateFP) {
        this.stateFP = stateFP;
    }

    public String getStateNS() {
        return stateNS;
    }

    public void setStateNS(String stateNS) {
        this.stateNS = stateNS;
    }

    public String getGeoId() {
        return geoId;
    }

    public void setGeoId(String geoId) {
        this.geoId = geoId;
    }

    public String getStusPs() {
        return stusPs;
    }

    public String getCountyFP() {
        return countyFP;
    }

    public void setCountyFP(String countyFP) {
        this.countyFP = countyFP;
    }

    public void setStusPs(String stusPs) {
        this.stusPs = stusPs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLsad() {
        return lsad;
    }

    public void setLsad(String lsad) {
        this.lsad = lsad;
    }

    public String getMtfcc() {
        return mtfcc;
    }

    public void setMtfcc(String mtfcc) {
        this.mtfcc = mtfcc;
    }

    public String getFuncStat() {
        return funcStat;
    }

    public void setFuncStat(String funcStat) {
        this.funcStat = funcStat;
    }

    public Long getLandArea() {
        return landArea;
    }

    public void setLandArea(Long landArea) {
        this.landArea = landArea;
    }

    public Long getWaterArea() {
        return waterArea;
    }

    public void setWaterArea(Long waterArea) {
        this.waterArea = waterArea;
    }

    public String getIntPtLat() {
        return intPtLat;
    }

    public void setIntPtLat(String intPtLat) {
        this.intPtLat = intPtLat;
    }

    public String getIntPtLng() {
        return intPtLng;
    }

    public void setIntPtLng(String intPtLng) {
        this.intPtLng = intPtLng;
    }

    public String getCountyNs() {
        return countyNs;
    }

    public void setCountyNs(String countyNs) {
        this.countyNs = countyNs;
    }

    public String getNameLsad() {
        return nameLsad;
    }

    public void setNameLsad(String nameLsad) {
        this.nameLsad = nameLsad;
    }

    public String getClassMp() {
        return classMp;
    }

    public void setClassMp(String classMp) {
        this.classMp = classMp;
    }

    public String getCsafp() {
        return csafp;
    }

    public void setCsafp(String csafp) {
        this.csafp = csafp;
    }

    public String getCbsafp() {
        return cbsafp;
    }

    public void setCbsafp(String cbsafp) {
        this.cbsafp = cbsafp;
    }

    public String getMetdivfp() {
        return metdivfp;
    }

    public void setMetdivfp(String metdivfp) {
        this.metdivfp = metdivfp;
    }

    public String getUrbanRural() {
        return urbanRural;
    }

    public void setUrbanRural(String urbanRural) {
        this.urbanRural = urbanRural;
    }

    public String getCousubFp() {
        return cousubFp;
    }

    public void setCousubFp(String cousubFp) {
        this.cousubFp = cousubFp;
    }

    public String getCousubNs() {
        return cousubNs;
    }

    public void setCousubNs(String cousubNs) {
        this.cousubNs = cousubNs;
    }

    public String getCnectaFp() {
        return cnectaFp;
    }

    public void setCnectaFp(String cnectaFp) {
        this.cnectaFp = cnectaFp;
    }

    public String getNectaFp() {
        return nectaFp;
    }

    public void setNectaFp(String nectaFp) {
        this.nectaFp = nectaFp;
    }

    public String getNctadvFp() {
        return nctadvFp;
    }

    public void setNctadvFp(String nctadvFp) {
        this.nctadvFp = nctadvFp;
    }

    @Override
    public String toString() {
        return "GeoProperties{" +
                "region='" + region + '\'' +
                ", division='" + division + '\'' +
                ", stateFP='" + stateFP + '\'' +
                ", stateNS='" + stateNS + '\'' +
                ", geoId='" + geoId + '\'' +
                ", stusPs='" + stusPs + '\'' +
                ", name='" + name + '\'' +
                ", lsad='" + lsad + '\'' +
                ", mtfcc='" + mtfcc + '\'' +
                ", funcStat='" + funcStat + '\'' +
                ", landArea=" + landArea +
                ", waterArea=" + waterArea +
                ", intPtLat='" + intPtLat + '\'' +
                ", intPtLng='" + intPtLng + '\'' +
                ", countyFP='" + countyFP + '\'' +
                ", countyNs='" + countyNs + '\'' +
                ", nameLsad='" + nameLsad + '\'' +
                ", classMp='" + classMp + '\'' +
                ", csafp='" + csafp + '\'' +
                ", cbsafp='" + cbsafp + '\'' +
                ", metdivfp='" + metdivfp + '\'' +
                ", urbanRural='" + urbanRural + '\'' +
                '}';
    }
}
