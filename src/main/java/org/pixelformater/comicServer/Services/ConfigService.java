package org.pixelformater.comicServer.Services;

import org.pixelformater.comicServer.Model.ConfigModel;
import org.pixelformater.comicServer.Repositories.ConfigRespository;
import org.springframework.stereotype.Service;

@Service
public class ConfigService extends GenericService<ConfigRespository> {

    public ConfigModel findByPropertyKey(String propertyKey){
        return iRepository.findByPropertyKey(propertyKey);
    }

    public void createOrUpdate(ConfigModel configModel){
        ConfigModel tempModel = getConfigModel(configModel);
        if(tempModel!=null){
            configModel.setId(tempModel.getId());
        }
        iRepository.save(configModel);
    }

    public ConfigModel getConfigModel(ConfigModel configModel){
        ConfigModel objectToReturn = null;
        if(configModel.getId()!=null){
            objectToReturn=iRepository.findById(configModel.getId()).get();
        }else if(configModel.getPropertyKey()!=null){
            objectToReturn=iRepository.findByPropertyKey(configModel.getPropertyKey());

        }
        return objectToReturn;
    }

}
