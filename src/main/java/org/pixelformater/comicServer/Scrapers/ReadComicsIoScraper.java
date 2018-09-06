package org.pixelformater.comicServer.Scrapers;

import org.pixelformater.comicServer.Model.ChapterModel;
import org.pixelformater.comicServer.Model.ComicModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pixelformater.comicServer.Model.ProviderModel;
import org.pixelformater.comicServer.Utilities.WebPageUtilities;

import java.util.ArrayList;
import java.util.List;

public class ReadComicsIoScraper implements IScraper<ComicModel> {

    private WebPageUtilities webPageUtilities = new WebPageUtilities();

    @Override
    public List<ComicModel> getAllTheComics(ProviderModel providerModel) {
        ArrayList<ComicModel> listOfComics = new ArrayList<>();
        String domAsXml = webPageUtilities.getWebPageAsXml(providerModel.getAllComicsUrl());
        Document doc = Jsoup.parse(domAsXml);
        Elements ele = doc.getElementsByClass("big-link");
        for (Element element : ele) {
            String url = element.attr("href");
            String comicName = element.text();
            ComicModel comic = new ComicModel();
            comic.setComicName(comicName);
            comic.setComicUrl(url);
            comic.setComicProviderId(providerModel.getId());
            listOfComics.add(comic);
        }
        return listOfComics;
    }

    @Override
    public List<String> getAllPagesByUrl(String chapterUrl) {
        String domAsXml = webPageUtilities.getWebPageAsXml(chapterUrl+"/full");
        List<String> pages = getAllPagesByDom(domAsXml);
        return pages;
    }

    @Override
    public List<String> getAllPagesByDom(String comicDom) {
        Document doc = Jsoup.parse(comicDom);
        Elements elements = doc.getElementsByClass("chapter_img");
        List<String> listOfPages = new ArrayList<>();
        for (Element element: elements) {
            listOfPages.add(element.attr("src"));
        }

        return listOfPages;
    }

    @Override
    public List<ChapterModel> getAllChaptersByUrl(String comicUrl) {
        String domAsXml = webPageUtilities.getWebPageAsXml(comicUrl);
        List<ChapterModel> listOfChapters = getAllChapterByDom(domAsXml);
        return listOfChapters;
    }

    @Override
    public List<ChapterModel> getAllChapterByDom(String comicDom) {
        ArrayList<ChapterModel> listOfChapters = new ArrayList<>();
        Document doc = Jsoup.parse(comicDom);
        Elements elements = doc.getElementsByClass("ch-name");
        for (Element element: elements) {
            ChapterModel chapterModel = new ChapterModel();
            chapterModel.setChapterName(element.text());
            chapterModel.setChapterUrl(element.attr("href"));
            chapterModel.setDownloaded(false);
            chapterModel.setChapterNumber(elements.size() - listOfChapters.size());
            listOfChapters.add(chapterModel);
        }
        return listOfChapters;
    }

    @Override
    public String getPageByUrl(Integer pageNumber, String chapterUrl) {
        return null;
    }

    @Override
    public ChapterModel getChapterByUrl(Integer chapterNumber, String comicUrl) {
        return null;
    }

    @Override
    public Integer getNumberOfPagesByUrl(String chapterUrl) {
        return null;
    }

    @Override
    public ChapterModel getChapterByDom(Integer chapterNumber, String comicDom) {
        return null;
    }

    @Override
    public String getPageByDom(Integer pageNumber, String chapterDom) {
        return null;
    }

    @Override
    public Integer getNumberOfPagesByDom(String chapterDom) {
        return null;
    }

    @Override
    public ComicModel getComicModelByUrl(String comicUrl) {
        String domAsXml = webPageUtilities.getWebPageAsXml(comicUrl);
        ComicModel comicModel = getComicModelByDom(domAsXml);
        return comicModel;
    }

    @Override
    public ComicModel getComicModelByDom(String comicDom) {

        ComicModel comicModel = new ComicModel();
        Document doc = Jsoup.parse(comicDom);
        Elements elements = doc.getElementsByClass("title");
        String title = elements.get(0).text().replace("Comic","");
        comicModel.setComicName(title);
        comicModel=getComicDetailsByDom(comicModel,doc);
        return comicModel;
    }

    @Override
    public ComicModel getComicDetailsByDom(ComicModel comicModel, Document comicDom) {
        ArrayList<String> tableAsString = new ArrayList<>();

        Element table = comicDom.getElementsByClass("full-table").first();
        Elements rows = table.select("tr");
        for (Element element: rows) {
            Elements col = element.select("td");
            if((col.get(0).text().trim()).contains("Name")){
                comicModel.setComicName(col.get(1).text().trim());
            }else if((col.get(0).text().trim()).contains("Alternate Name")){
                comicModel.setComicAlternateName(col.get(1).text().trim());
            }else if((col.get(0).text().trim()).contains("Year of Release")){
                comicModel.setYearOfRelease(Integer.valueOf(col.get(1).text().trim()));
            } else if ((col.get(0).text().trim()).contains("Status")) {
                comicModel.setStatus(col.get(1).text().trim());
            }else if ((col.get(0).text().trim()).contains("Author")){
                comicModel.setComicAuthor(col.get(1).text().trim());
            }else if((col.get(0).text().trim()).contains("Genre")){
                comicModel.setComicGenres(col.get(1).text().trim());
            }
        }

        return comicModel;
    }

}
