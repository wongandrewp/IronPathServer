package wong.andrew.webandprocessbuilder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import wong.andrew.webandprocessbuilder.exception.StorageFileNotFoundException;
import wong.andrew.webandprocessbuilder.service.StorageService;


import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.stream.Collectors;

/**
 * Created by andrewwong on 7/3/17.
 */
@RequestMapping("/files")
@RestController
@CrossOrigin
public class FileUploadController {

    private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Value("${multipart.location}")
    private String location;

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    //    @GetMapping("/{filename}")
//    @ResponseBody
//    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
//
//        Resource file = storageService.loadAsResource(filename);
//        return ResponseEntity
//                .ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
//                .body(file);
//    }

    @RequestMapping(value = "/{filename}", method = RequestMethod.GET, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        System.out.println(file.getFilename());
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity upload(@RequestPart("file") MultipartFile file) throws IOException {
        File videoFile = new File(location + "/" + System.currentTimeMillis() + ".mp4");
        file.transferTo(videoFile);

        log.info("Saved file to {}", videoFile.getAbsolutePath());
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }


    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
