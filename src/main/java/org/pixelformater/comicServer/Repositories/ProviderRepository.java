package org.pixelformater.comicServer.Repositories;

import org.springframework.stereotype.Repository;
import org.pixelformater.comicServer.Model.ProviderModel;
@Repository
public interface ProviderRepository extends IRepository<ProviderModel>{

    ProviderModel findByProviderName(String providerName);
}
