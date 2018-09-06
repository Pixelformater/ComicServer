package org.pixelformater.comicServer.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IRepository<E> extends CrudRepository<E,Long> {


}
