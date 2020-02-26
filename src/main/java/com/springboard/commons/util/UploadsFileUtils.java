package com.springboard.commons.util;

import org.imgscalr.Scalr;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Class for file upload feature processing
 * @author MyHyem
 * @since 2020.02.18
 * @version 1.0
 * @see
 *
 * <pre>
 * << Modification Information >>
 *
 *   Modified date       Modifier              Modifications
 *  --------------      ----------       ---------------------------
 *  2020.02.18          MyHyem              Initial Creation
 *  </pre>
 */
public class UploadsFileUtils {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(UploadsFileUtils.class);

    /** process file upload */
    public String uploadFile(MultipartFile file, HttpServletRequest request) throws Exception {

        String originalFileName = file.getOriginalFilename();           // file name
        byte[] fileData = file.getBytes();                              // file data

        String uuidFileName = getUuidFileName(originalFileName);        // file name duplication prevention processing
        String rootPath = getRootPath(originalFileName, request);       // config default path
        String datePath = getDateOath(rootPath);                        // config date path

        File target = new File(rootPath + datePath, uuidFileName);      // create file object
        FileCopyUtils.copy(fileData, target);                                  // copy to file object from file data

        if (MediaUtils.getMediaType(originalFileName) != null) {        // create thumbnail
            uuidFileName = makeThmbnail(rootPath, datePath, uuidFileName);
        }

        return replaceSavedFilePath(datePath, uuidFileName);            // replace file storage path
    }

    /** process file delete */
    public static void deleteFile(String fileName, HttpServletRequest request) {

        String rootPath = getRootPath(fileName, request);               // file default path

        MediaType mediaType = MediaUtils.getMediaType(fileName);
        if (mediaType != null) {        // delete original image file
            String originalImg = fileName.substring(0, 12) + fileName.substring(14);
            new File(rootPath + originalImg.replace('/', File.separatorChar)).delete();
        }

        new File(rootPath + fileName.replace('/', File.separatorChar)).delete();    // delete file data
    }

    /** config HttpHeader */
    public static HttpHeaders getHttpHeaders(String fileName) throws Exception {

        MediaType mediaType = MediaUtils.getMediaType(fileName);        // check file type
        HttpHeaders httpHeaders = new HttpHeaders();

        if (mediaType != null) {        // case image file
            httpHeaders.setContentType(mediaType);
        }

        fileName = fileName.substring(fileName.indexOf("_") + 1);           // delete uuid
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);     // config download MIME type
        httpHeaders.add("Content-Disposition",
                "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");

        return httpHeaders;
    }

    /** default path extraction */
    public static String getRootPath(String fileName, HttpServletRequest request) {

        String rootPath = "/resources/upload";
        MediaType mediaType = MediaUtils.getMediaType(fileName);
        if (mediaType != null) {
            return request.getSession().getServletContext().getRealPath(rootPath + "/images");
        }

        return request.getSession().getServletContext().getRealPath(rootPath + "/files");
    }

    /** date path extraction */
    public static String getDateOath(String uploadPath) {

        Calendar calendar = Calendar.getInstance();
        String yearPath = File.separator + calendar.get(Calendar.YEAR);
        String monthPath = yearPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.MONTH) + 1);
        String datePath = monthPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.DATE));

        makeDateDir(uploadPath, yearPath, monthPath, datePath);

        return datePath;
    }

    /** create date directory */
    public static void makeDateDir(String uploadPath, String... paths) {

        if (new File(uploadPath + paths[paths.length + 1]).exists()) {
            return;
        }

        for (String path : paths) {
            File dirPath = new File(uploadPath + path);
            if (!dirPath.exists()) {
                dirPath.mkdir();
            }
        }
    }

    /** replace file storage path */
    private static String replaceSavedFilePath(String datePath, String fileName) {
        String savedFilePath = datePath + File.separator + fileName;

        return savedFilePath.replace(File.separatorChar, '/');
    }

    /** file name duplication prevention processing */
    private static String getUuidFileName(String originalFileName) {
        return UUID.randomUUID().toString() + "_" + originalFileName;
    }

    /** create thumbnail */
    private static String makeThmbnail(String uploadRootPath, String datePath, String fileName) throws Exception {

        BufferedImage originalImg = ImageIO.read(new File(uploadRootPath + datePath, fileName));
        BufferedImage thumbnailImg = Scalr.resize(originalImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
        String thumbnailImgName = "s_" + fileName;
        String fullPath = uploadRootPath + datePath + File.separator + thumbnailImgName;
        File newFile = new File(fullPath);
        String formatName = MediaUtils.getFormatName(fileName);
        ImageIO.write(thumbnailImg, formatName, newFile);

        return thumbnailImgName;
    }
}
