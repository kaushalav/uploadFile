package com.uploadFile.uploadFile.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component  
public class FileUploadHelper {
    // public final String UPLOAD_DIR = "D:\\Avanish_kaushal\\uploadFile\\uploadFile\\src\\main\\resources\\static\\images";
    public final String UPLOAD_DIR = new ClassPathResource("static/images/").getFile().getAbsolutePath();
    FileUploadHelper() throws IOException {
        
    }
    public boolean uploadFile(MultipartFile multiPartFile) {
        boolean isFileUploaded = false;
        try {
            // 1 using input stream
            // InputStream iS = multiPartFile.getInputStream();
            // byte data[] = new byte[iS.available()];
            // iS.read(data);
            // FileOutputStream fos = new FileOutputStream(
            //         UPLOAD_DIR + File.separator + multiPartFile.getOriginalFilename());
            // fos.write(data);
            // fos.flush();
            // fos.close();
            Files.copy(multiPartFile
                    .getInputStream(), Paths.get(UPLOAD_DIR+"\\"+multiPartFile.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
            isFileUploaded = true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return isFileUploaded;
    }
}
