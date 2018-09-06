package org.pixelformater.comicServer.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="COMIC")
public class ComicModel extends GenericModel{

    private Long comicProviderId;

    private String comicName;
    private String comicAlternateName;
    private Integer yearOfRelease;
    private String status;
    private String comicAuthor;
    private String comicUrl;
    private Integer numberOfChapters;
    private Integer numberOfChaptersDownloaded;
    private String comicGenres;

    public ComicModel() {
    }

    public ComicModel(Long comicProviderId, String comicName, String comicAlternateName, Integer yearOfRelease, String status, String comicAuthor, String comicUrl, Integer numberOfChapters, Integer numberOfChaptersDownloaded) {
        this.comicProviderId = comicProviderId;
        this.comicName = comicName;
        this.comicAlternateName = comicAlternateName;
        this.yearOfRelease = yearOfRelease;
        this.status = status;
        this.comicAuthor = comicAuthor;
        this.comicUrl = comicUrl;
        this.numberOfChapters = numberOfChapters;
        this.numberOfChaptersDownloaded = numberOfChaptersDownloaded;
    }

    public Long getComicProviderId() {
        return comicProviderId;
    }

    public void setComicProviderId(Long comicProviderId) {
        this.comicProviderId = comicProviderId;
    }

    public String getComicName() {
        return comicName;
    }

    public void setComicName(String comicName) {
        this.comicName = comicName;
    }

    public String getComicAlternateName() {
        return comicAlternateName;
    }

    public void setComicAlternateName(String comicAlternateName) {
        this.comicAlternateName = comicAlternateName;
    }

    public Integer getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(Integer yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComicAuthor() {
        return comicAuthor;
    }

    public void setComicAuthor(String comicAuthor) {
        this.comicAuthor = comicAuthor;
    }

    public String getComicUrl() {
        return comicUrl;
    }

    public void setComicUrl(String comicUrl) {
        this.comicUrl = comicUrl;
    }

    public Integer getNumberOfChapters() {
        return numberOfChapters;
    }

    public void setNumberOfChapters(Integer numberOfChapters) {
        this.numberOfChapters = numberOfChapters;
    }

    public Integer getNumberOfChaptersDownloaded() {
        return numberOfChaptersDownloaded;
    }

    public void setNumberOfChaptersDownloaded(Integer numberOfChaptersDownloaded) {
        this.numberOfChaptersDownloaded = numberOfChaptersDownloaded;
    }

    public String getComicGenres() {
        return comicGenres;
    }

    public void setComicGenres(String comicGenres) {
        this.comicGenres = comicGenres;
    }
}
