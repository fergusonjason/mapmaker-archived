package org.jason.mapmaker.tigertools.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author Jason
 */
public class GeoProperty implements Serializable {

    @JsonProperty("REGION") private String region;
    @JsonProperty("DIVISION") private String division;
    @JsonProperty("STATEFP") private String stateFP;
    @JsonProperty("STATENS") private String stateNS;
    @JsonProperty("GEOID") private String geoId;
    @JsonProperty("STUSPS") private String stusPs;
    @JsonProperty("NAME") private String name;
    @JsonProperty("LSAD") private String lsad;
    @JsonProperty("MTFCC") private String mtfcc;
    @JsonProperty("FUNCSTAT") private String funcStat;
    @JsonProperty("ALAND") private Long landArea;
    @JsonProperty("AWATER") private Long waterArea;
    @JsonProperty("INTPTLAT") private String intPtLat;
    @JsonProperty("INTPTLON") private String intPtLng;

    @JsonProperty("COUNTYFP") private String countyFP;
    @JsonProperty("COUNTYNS") private String countyNs;
    @JsonProperty("NAMELSAD") private String nameLsad;
    @JsonProperty("CLASSMP") private String classMp;
    @JsonProperty("CSAFP") private String csafp;
    @JsonProperty("CBSAFP") private String cbsafp;
    @JsonProperty("METDIVFP") private String metdivfp;

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
}
