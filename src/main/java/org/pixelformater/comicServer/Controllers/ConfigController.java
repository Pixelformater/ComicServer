package org.pixelformater.comicServer.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.pixelformater.comicServer.Model.ConfigModel;
import org.pixelformater.comicServer.Services.ConfigService;


@RestController
public class ConfigController {

    @Autowired
    ConfigService configService;

    @RequestMapping("/setConfig")
    public String setConfig(@RequestParam(value = "configKey") String configKey,@RequestParam(value = "configValue") String configValue) {
        configService.createOrUpdate(new ConfigModel(configKey, configValue));
        return "done";
    }

    @RequestMapping("/getConfig")
    public String getDownloadDirectory(@RequestParam(value = "configKey") String configKey) {
        return configService.findByPropertyKey(configKey).getPropertyValue();
    }




}
