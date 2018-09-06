package org.pixelformater.comicServer.Services;

import com.google.gson.*;
import org.pixelformater.comicServer.Model.ConfigModel;
import org.pixelformater.comicServer.Repositories.ConfigRespository;
import org.pixelformater.comicServer.Repositories.ProviderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.pixelformater.comicServer.Model.ProviderModel;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

@Component
public class InitializatorService {

    private static Logger logger = LoggerFactory.getLogger(InitializatorService.class);

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ConfigRespository configRespository;

    @PostConstruct
    public void initializeDatabase() {
        JsonArray jsonArrayConfig = new JsonArray();
        JsonArray jsonArrayProvider= new JsonArray();
        Gson gson = new Gson();
        ArrayList<ProviderModel> providerModels = new ArrayList<>();
        ArrayList<ConfigModel> configModels = new ArrayList<>();
        try{
            FileReader fileReader = new FileReader("src/provider.json");
            JsonObject jsonObjectProvider= (JsonObject) new JsonParser().parse(fileReader);
            jsonArrayProvider = jsonObjectProvider.getAsJsonArray("providerModel");
            for(JsonElement element:jsonArrayProvider){
                ProviderModel providerModel = gson.fromJson(element,ProviderModel.class);
                providerModels.add(providerModel);
            }
            FileReader fileReaderConfiguration = new FileReader("src/configuration.json");
            JsonObject jsonObjectConfiguration= (JsonObject) new JsonParser().parse(fileReaderConfiguration);
            jsonArrayConfig = jsonObjectConfiguration.getAsJsonArray("configModel");
            for(JsonElement element:jsonArrayConfig){
                ConfigModel configModel = gson.fromJson(element,ConfigModel.class);
                configModels.add(configModel);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for(ConfigModel configModel:configModels){
            if(configRespository.findByPropertyKey(configModel.getPropertyKey())==null){
                configRespository.save(configModel);
            }
        }
        for (ProviderModel providerModel:providerModels) {
            if (providerRepository.findByProviderName(providerModel.getProviderName()) == null) {
                providerRepository.save(providerModel);
            }
        }
        logger.info("Initialization Complete");
    }

}
