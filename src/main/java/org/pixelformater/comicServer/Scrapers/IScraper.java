package org.pixelformater.comicServer.Scrapers;

import org.pixelformater.comicServer.Model.ChapterModel;
import org.pixelformater.comicServer.Model.ComicModel;
import org.jsoup.nodes.Document;
import org.pixelformater.comicServer.Model.ProviderModel;

import java.util.List;

public interface IScraper<E> {

    List<E> getAllTheComics(ProviderModel providerModel);

    List<String> getAllPagesByUrl(String chapterUrl);

    List<ChapterModel> getAllChaptersByUrl(String comicUrl);

    String getPageByUrl(Integer pageNumber, String chapterUrl);

    ChapterModel getChapterByUrl(Integer chapterNumber, String comicUrl);

    Integer getNumberOfPagesByUrl(String chapterUrl);

    ComicModel getComicModelByUrl(String comicUrl);

    List<String> getAllPagesByDom(String comicDom);

    List<ChapterModel> getAllChapterByDom(String comicDom);

    ChapterModel getChapterByDom(Integer chapterNumber, String comicDom);

    String getPageByDom(Integer pageNumber,String chapterDom);

    Integer getNumberOfPagesByDom(String chapterDom);

    ComicModel getComicModelByDom(String comicDom);

    ComicModel getComicDetailsByDom(ComicModel comicModel, Document dom);


}
