package org.pixelformater.comicServer.Services;

import org.pixelformater.comicServer.Model.ComicModel;
import org.pixelformater.comicServer.Repositories.ComicRepository;
import org.springframework.stereotype.Service;
import org.pixelformater.comicServer.Model.ProviderModel;

import java.util.List;

@Service
public class ComicModelService extends GenericService<ComicRepository> {

    public void createOrUpdate(ComicModel comicModel){
        ComicModel tempModel = getComicModel(comicModel);
        if(tempModel!=null){
            comicModel.setId(tempModel.getId());
        }
        iRepository.save(comicModel);
    }

    public ComicModel getComicModel(ComicModel comicModel){
        ComicModel objectToReturn = null;
        if(comicModel.getId()!=null){
            objectToReturn=iRepository.findById(comicModel.getId()).get();
        }else if(comicModel.getComicName()!=null && comicModel.getComicProviderId()!=null){
            objectToReturn=iRepository.findByComicNameAndComicProviderId(comicModel.getComicName(),comicModel.getComicProviderId());

        }
        return objectToReturn;
    }

    public List<ComicModel> getAllByComicProviderId(ProviderModel providerModel){
        return iRepository.findAllByComicProviderId(providerModel.getId());
    }

    public ComicModel findComicByName(String comicName){
        return iRepository.findByComicName(comicName);
    }

}
