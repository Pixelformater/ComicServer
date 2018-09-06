package org.pixelformater.comicServer.Services;

import org.pixelformater.comicServer.Repositories.ProviderRepository;
import org.pixelformater.comicServer.Scrapers.IScraper;
import org.springframework.stereotype.Service;
import org.pixelformater.comicServer.Model.ProviderModel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Service
public class ProviderService extends GenericService<ProviderRepository>{


    public IScraper getProviderScraper(String providerClassName) {
        IScraper scraper=null;
        try {
            Class<IScraper> scraperClass = (Class<IScraper>) Class.forName("org.pixelformater.comicServer.Scrapers"+"."+providerClassName);
            Constructor<IScraper> scraperConstructor = scraperClass.getConstructor();
            scraper = (IScraper) scraperConstructor.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return scraper;
    }

    public ProviderModel getProvider (ProviderModel providerModel){
        ProviderModel objectToReturn = null;
        if(providerModel.getId()!=null){
            objectToReturn= iRepository.findById(providerModel.getId()).get();
        }else if (providerModel.getProviderName()!=null){
            objectToReturn= iRepository.findByProviderName(providerModel.getProviderName());
        }
        return objectToReturn;
    }
}
