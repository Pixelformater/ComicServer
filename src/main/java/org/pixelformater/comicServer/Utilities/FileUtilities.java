package org.pixelformater.comicServer.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtilities {

    public static void compressComicFiles(String zipFile, String sourceFolder, List<String> fileList) {

        byte[] buffer = new byte[1024];
        String source = new File(sourceFolder).getName();
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(sourceFolder+File.separator+zipFile);
            zos = new ZipOutputStream(fos);

            System.out.println("Output to Zip : " + zipFile);
            FileInputStream in = null;

            for (String file: fileList) {
                System.out.println("File Added : " + file);
                ZipEntry ze = new ZipEntry(source + File.separator + file);
                zos.putNextEntry(ze);
                try {
                    in = new FileInputStream(sourceFolder + File.separator + file);
                    int len;
                    while ((len = in .read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }
                } finally {
                    in.close();
                }
            }

            zos.closeEntry();
            System.out.println("Folder successfully compressed");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                zos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteFileList(String sourceFolder, List<String> fileList){
        for(String file : fileList){
            File toDelete = new File(sourceFolder+file);
            if(toDelete.exists()){
                toDelete.delete();
            }
        }
    }

    public static String removeInvalidCharactersFromFilename(String filename){
        String invalidCharactersRemoved = filename.replaceAll("[\\\\/:*?\"<>|]", "");
        return invalidCharactersRemoved;
    }
}
