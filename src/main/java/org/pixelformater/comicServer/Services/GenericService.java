package org.pixelformater.comicServer.Services;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericService<IRepository> {

    @Autowired
    protected IRepository iRepository;




}
