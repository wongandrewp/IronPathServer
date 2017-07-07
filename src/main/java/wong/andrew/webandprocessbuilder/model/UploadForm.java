package wong.andrew.webandprocessbuilder.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by andrewwong on 7/6/17.
 */
public class UploadForm {
    MultipartFile[] files;

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }
}
