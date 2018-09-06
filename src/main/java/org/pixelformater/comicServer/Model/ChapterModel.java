package org.pixelformater.comicServer.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="CHAPTER")
public class ChapterModel extends GenericModel{

    private Long comicId;
    private Integer chapterNumber;
    private String chapterName;
    private String chapterUrl;

    private String downloadedPath;

    private boolean downloaded;
    public ChapterModel() {
    }

    public ChapterModel(Long comicId, Integer chapterNumber, String chapterName, String chapterUrl, boolean downloaded) {
        this.comicId = comicId;
        this.chapterNumber = chapterNumber;
        this.chapterName = chapterName;
        this.chapterUrl = chapterUrl;
        this.downloaded = downloaded;
    }

    public Long getComicId() {
        return comicId;
    }

    public void setComicId(Long comicId) {
        this.comicId = comicId;
    }

    public Integer getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(Integer chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterUrl() {
        return chapterUrl;
    }

    public void setChapterUrl(String chapterUrl) {
        this.chapterUrl = chapterUrl;
    }

    public boolean isDownloaded() {
        return downloaded;
    }

    public void setDownloaded(boolean downloaded) {
        this.downloaded = downloaded;
    }

    public String getDownloadedPath() { return downloadedPath; }

    public void setDownloadedPath(String downloadedPath) { this.downloadedPath = downloadedPath; }
}
