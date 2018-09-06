package org.pixelformater.comicServer.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="PROVIDER")
public class ProviderModel extends GenericModel {

    private String providerName;
    private String scraperClassName;
    private String baseUrl;
    private String allComicsUrl;

    public ProviderModel() {
    }

    public ProviderModel(String providerName, String scraperClassName, String baseUrl, String allComicsUrl) {
        this.providerName = providerName;
        this.scraperClassName = scraperClassName;
        this.baseUrl = baseUrl;
        this.allComicsUrl = allComicsUrl;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getScraperClassName() {
        return scraperClassName;
    }

    public void setScraperClassName(String scraperClassName) {
        this.scraperClassName = scraperClassName;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getAllComicsUrl() {
        return allComicsUrl;
    }

    public void setAllComicsUrl(String allComicsUrl) {
        this.allComicsUrl = allComicsUrl;
    }
}
