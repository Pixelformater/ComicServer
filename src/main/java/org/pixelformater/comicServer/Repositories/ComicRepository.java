package org.pixelformater.comicServer.Repositories;

import org.pixelformater.comicServer.Model.ComicModel;

import java.util.List;

public interface ComicRepository extends IRepository<ComicModel> {

    ComicModel findByComicName(String comicName);

    ComicModel findByComicUrl(String comicUrl);

    List<ComicModel> findAllByComicProviderId(Long providerId);

    ComicModel findByComicNameAndComicProviderId(String comicName,Long providerId);


}
