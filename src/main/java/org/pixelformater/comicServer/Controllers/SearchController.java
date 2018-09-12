package org.pixelformater.comicServer.Controllers;

import org.pixelformater.comicServer.Model.ComicModel;
import org.pixelformater.comicServer.Model.ProviderModel;
import org.pixelformater.comicServer.Services.ComicModelService;
import org.pixelformater.comicServer.Services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    ComicModelService comicModelService;

    @Autowired
    ProviderService providerService;

    @RequestMapping("/search")
    public ComicModel findSeries(@RequestParam(value = "name")String seriesName){
        return comicModelService.findComicByName(seriesName);
    }

    @RequestMapping("/getAllComicProviders")
    public List<ProviderModel> getAllProviders(){
      return providerService.findAllProviders();
    }
}
