package com.springboard.commons.util;

import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for determine whether image type
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
class MediaUtils {

    private static Map<String, MediaType> mediaTypeMap;

    static {
        mediaTypeMap = new HashMap<>();
        mediaTypeMap.put("JPG", MediaType.IMAGE_JPEG);
        mediaTypeMap.put("GIF", MediaType.IMAGE_GIF);
        mediaTypeMap.put("PNG", MediaType.IMAGE_PNG);
    }

    static MediaType getMediaType(String fileName) {
        String formatName = getFormatName(fileName);
        return mediaTypeMap.get(formatName);
    }

    static String getFormatName(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
    }
}
