package org.pixelformater.comicServer.Controllers;

import org.pixelformater.comicServer.Model.ComicModel;
import org.pixelformater.comicServer.Services.ComicModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @Autowired
    ComicModelService comicModelService;

    @RequestMapping("search/")
    public ComicModel findSeries(@RequestParam(value = "name")String seriesName){
        return comicModelService.findComicByName(seriesName);
    }
}
