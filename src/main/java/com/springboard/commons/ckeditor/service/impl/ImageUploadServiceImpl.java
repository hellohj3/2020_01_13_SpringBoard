package com.springboard.commons.ckeditor.service.impl;

import com.springboard.commons.ckeditor.service.ImageUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Class to Image upload handling Process
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

@Service("imageUploadService")
public class ImageUploadServiceImpl implements ImageUploadService {

    /**
     * Do upload image in server
     * @param HttpServletRequest - HttpServletRequest.
     * @param HttpServletResponse - HttpServletResponse.
     * @param MultipartFile - MultipartFile.
     * @return void
     * @exception Exception
     */
    public void imageUpload(HttpServletRequest request, HttpServletResponse response, MultipartFile file) throws Exception {
        OutputStream out = null;
        PrintWriter printWriter = null;
        String fileName = file.getOriginalFilename();
        byte[] bytes = file.getBytes();
        String rootPath = "/resources/upload";
        String uploadPath = rootPath + "/" + fileName;

        System.out.println(uploadPath);

        out = new FileOutputStream(new File(uploadPath));
        out.write(bytes);
        String callback = request.getParameter("CKEditorFuncNum");

        printWriter = response.getWriter();
        String fileUrl = request.getContextPath()+ rootPath + "/" +fileName; //url 경로
        printWriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
                + callback
                + ",'"
                + fileUrl
                + "','이미지를 업로드 하였습니다.'"
                + ")</script>");
        printWriter.flush();
    }
}
