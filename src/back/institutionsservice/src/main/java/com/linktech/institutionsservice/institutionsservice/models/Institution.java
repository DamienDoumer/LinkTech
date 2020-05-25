package com.linktech.institutionsservice.institutionsservice.models;

public class Institution extends BaseModel{

    String name;
    String location;
    InstitutionType type;
    String countryName;
    String sector;

    public String getCountryName() {
        return this.countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getSector() {
        return this.sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public InstitutionType getType() {
        return this.type;
    }

    public void setType(InstitutionType type) {
        this.type = type;
    }
}