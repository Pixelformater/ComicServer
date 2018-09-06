package org.pixelformater.comicServer.Utilities;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class DownloadUtilities {

    private static Logger logger = LoggerFactory.getLogger(DownloadUtilities.class);
    public static boolean imageDownload(String url, String pathToDownload) {
        boolean isSuccessful = false;

        try {
            Connection.Response resultImageResponse = Jsoup.connect(url).ignoreContentType(true).execute();
            File file = new File(pathToDownload.trim());

            file.getParentFile().mkdirs();
            FileOutputStream out = (new FileOutputStream(file));
            out.write(resultImageResponse.bodyAsBytes());
            out.close();
            isSuccessful = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isSuccessful;
    }


}
