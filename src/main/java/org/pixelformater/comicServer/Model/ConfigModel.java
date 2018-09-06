package org.pixelformater.comicServer.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CONFIG")
public class ConfigModel extends GenericModel {

    private String propertyKey;
    private String propertyValue;

    public ConfigModel(){}

    public ConfigModel(String propertyKey, String propertyValue) {
        this.propertyKey = propertyKey;
        this.propertyValue = propertyValue;
    }

    public ConfigModel(String propertyKey){
        this.propertyKey = propertyKey;
    }

    public String getPropertyKey() {
        return propertyKey;
    }

    public void setPropertyKey(String propertyKey) {
        this.propertyKey = propertyKey;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

}
