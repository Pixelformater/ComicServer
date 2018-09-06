package org.pixelformater.comicServer.Repositories;

import org.pixelformater.comicServer.Model.ChapterModel;

public interface ChapterRepository extends IRepository<ChapterModel> {

    ChapterModel findByComicIdAndChapterNumber(Long comicId,Integer chapterNumber);
}
