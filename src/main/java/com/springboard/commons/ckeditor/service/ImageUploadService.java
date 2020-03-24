package com.springboard.commons.ckeditor.service;

import com.springboard.blog.PostVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface to Image upload handling Process
 * @author MyHyem
 * @since 2020.03.11
 * @version 1.0
 * @see
 *
 * <pre>
 * << Modification Information >>
 *
 *   Modified date       Modifier              Modifications
 *  --------------      ----------       ---------------------------
 *  2020.03.11          MyHyem              Initial Creation
 *  </pre>
 */

public interface ImageUploadService {
    /**
     * Do upload image in server
     * @param HttpServletRequest - HttpServletRequest.
     * @param HttpServletResponse - HttpServletResponse.
     * @param MultipartFile - MultipartFile.
     * @return void
     * @exception Exception
     */
    void imageUpload(HttpServletRequest request, HttpServletResponse response, MultipartFile file) throws Exception;
}
