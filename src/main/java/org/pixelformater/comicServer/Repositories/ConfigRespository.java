package org.pixelformater.comicServer.Repositories;
import org.pixelformater.comicServer.Model.ConfigModel;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRespository extends IRepository<ConfigModel>{

    ConfigModel findByPropertyKey(String propertyKey);



}
