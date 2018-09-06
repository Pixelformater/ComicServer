package org.pixelformater.comicServer.Services;

import org.pixelformater.comicServer.Repositories.ChapterRepository;
import org.springframework.stereotype.Service;
import org.pixelformater.comicServer.Model.ChapterModel;

@Service
public class ComicChapterService extends GenericService<ChapterRepository> {

    public boolean checkIfComicChapterAlreadyExists(ChapterModel chapterModel){
        boolean valueToReturn=false;
        if(iRepository.findByComicIdAndChapterNumber(chapterModel.getComicId(),chapterModel.getChapterNumber())!=null){
            valueToReturn=true;
        }
        return valueToReturn;
    }

    public void createOrUpdate(ChapterModel comicChapter){
        ChapterModel tempModel = getComicModel(comicChapter);
        if(tempModel!=null){
            comicChapter.setId(tempModel.getId());
        }
        iRepository.save(comicChapter);
    }

    public ChapterModel getComicModel(ChapterModel comicChapter){
        return iRepository.findByComicIdAndChapterNumber(comicChapter.getComicId(),comicChapter.getChapterNumber());
    }

}
