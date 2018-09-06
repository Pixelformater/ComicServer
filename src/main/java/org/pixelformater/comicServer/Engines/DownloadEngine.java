package org.pixelformater.comicServer.Engines;

import org.pixelformater.comicServer.Model.ComicModel;
import org.pixelformater.comicServer.Model.ConfigModel;
import org.pixelformater.comicServer.Services.ComicChapterService;
import org.pixelformater.comicServer.Scrapers.IScraper;
import org.pixelformater.comicServer.Services.ComicModelService;
import org.pixelformater.comicServer.Services.ConfigService;
import org.pixelformater.comicServer.Services.ProviderService;
import org.pixelformater.comicServer.Utilities.DownloadUtilities;
import org.pixelformater.comicServer.Utilities.FileUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.pixelformater.comicServer.Model.ChapterModel;
import org.pixelformater.comicServer.Model.ProviderModel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class DownloadEngine {

    @Autowired
    private ConfigService configService;

    @Autowired
    private ComicModelService comicModelService;

    @Autowired
    private ComicChapterService comicChapterService;

    @Autowired
    private ProviderService providerService;

    public String downloadComic(String link, String providerName) {
        ProviderModel providerModel = new ProviderModel();
        providerModel.setProviderName(providerName);
        providerModel = providerService.getProvider(providerModel);
        IScraper scraper = providerService.getProviderScraper(providerModel.getScraperClassName());
        List<ChapterModel> listOfChapter = scraper.getAllChaptersByUrl(link);
        String downloadBasePath = getDownDir();
        ComicModel comicModel = scraper.getComicModelByUrl(link);
        HashMap<ChapterModel,List<String>> chapterPages= new HashMap<>();
        for (ChapterModel chapterModel : listOfChapter) {
            comicChapterService.createOrUpdate(chapterModel);
            List<String> listOfPages = scraper.getAllPagesByUrl(chapterModel.getChapterUrl());
            List<String> listOfPagesDownloadPath = new ArrayList<>();
            String scrubbedDirectoryName= FileUtilities.removeInvalidCharactersFromFilename(comicModel.getComicName()).trim();
            String scrubbedChapterName = FileUtilities.removeInvalidCharactersFromFilename(chapterModel.getChapterName()).trim();
            String downloadChapterDirectory=downloadBasePath.trim() + File.separator + scrubbedDirectoryName + File.separator + scrubbedChapterName + File.separator ;
            for (int i = 0; i < listOfPages.size(); i++) {
                String fileName=String.valueOf(i) + listOfPages.get(i).substring(listOfPages.get(i).lastIndexOf("."));
                String downloadPath = downloadChapterDirectory + fileName;
                DownloadUtilities.imageDownload(listOfPages.get(i), downloadPath);
                listOfPagesDownloadPath.add(fileName);
            }
            chapterModel.setDownloadedPath(downloadChapterDirectory);
            chapterModel.setDownloaded(true);
            chapterPages.put(chapterModel,listOfPagesDownloadPath);
        }
        if (isChapterToBeZipped()){
            for(HashMap.Entry<ChapterModel,List<String>> entry: chapterPages.entrySet()){
                String zipFile = entry.getKey().getChapterName().trim()+".cbz";
                String sourceFolder = entry.getKey().getDownloadedPath();
                FileUtilities.compressComicFiles(zipFile,sourceFolder,entry.getValue());
            }
        }
        if(areFilesToBeDeletedAfterCompressing()){
            for(HashMap.Entry<ChapterModel,List<String>> entry: chapterPages.entrySet()){
                String sourceFolder = entry.getKey().getDownloadedPath();
                FileUtilities.deleteFileList(sourceFolder,entry.getValue());
            }
        }
            return "DONE";
    }


    public String getDownDir() {
        String returnString = "";
        ConfigModel configfDownCommand = configService.findByPropertyKey("downloadDirectory");
        if (configfDownCommand != null) {
            returnString = configfDownCommand.getPropertyValue();
        }
        return returnString;
    }

    public List<ComicModel> getAllComicsByScraping(String providerName) {
        ProviderModel providerModel = new ProviderModel();
        providerModel.setProviderName(providerName);
        providerModel = getProvider(providerModel);
        IScraper scraper = providerService.getProviderScraper(providerModel.getScraperClassName());
        ArrayList<ComicModel> allComics = (ArrayList<ComicModel>) scraper.getAllTheComics(providerModel);
        for (ComicModel comic : allComics) {
            if (comicModelService.getComicModel(comic) == null) {
                comicModelService.createOrUpdate(comic);
            }
        }
        return allComics;
    }

    public List<ComicModel> getAllComicsByProviderName(String providerName) {
        ProviderModel providerModel = new ProviderModel();
        providerModel.setProviderName(providerName);
        providerModel = providerService.getProvider(providerModel);
        return comicModelService.getAllByComicProviderId(providerModel);
    }

    public ProviderModel getProvider(ProviderModel providerModel) {
        return providerService.getProvider(providerModel);
    }

    public boolean isChapterToBeZipped() {
        Boolean returnBool =false;
        ConfigModel config = configService.findByPropertyKey("compressChapter");
        if (config != null) {
            returnBool = config.getPropertyValue().equals("true");
        }
        return returnBool;
    }

    public boolean areFilesToBeDeletedAfterCompressing() {
        Boolean returnBool = false;
        ConfigModel config = configService.findByPropertyKey("deleteAfterCompressing");
        if (config != null) {
           returnBool =  config.getPropertyValue().equals("true");
        }
        return returnBool;
    }

}
