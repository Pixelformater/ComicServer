package org.pixelformater.comicServer.Controllers;


import org.pixelformater.comicServer.Engines.DownloadEngine;
import org.pixelformater.comicServer.Model.ComicModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class DownloadController {

    @Autowired
    private DownloadEngine comicDlService;


    @RequestMapping("/downloadChapter")
    public String message(@RequestParam(value="link") String link, @RequestParam(value="providerName") String providerName){
        return comicDlService.downloadComic(link,providerName);
    }

    @RequestMapping("/getAllComicForProvider")
    public List<ComicModel> getAllComicsByDatabase(@RequestParam(value="providerName") String providerName){
        return comicDlService.getAllComicsByProviderName(providerName);
    }

    @RequestMapping("/scrapeSite")
    public List<ComicModel> getAllComicsByScraping(@RequestParam(value="providerName") String providerName){
        return comicDlService.getAllComicsByScraping(providerName);
    }

}
