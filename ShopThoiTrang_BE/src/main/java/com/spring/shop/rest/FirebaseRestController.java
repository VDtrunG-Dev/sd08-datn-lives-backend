package com.spring.shop.rest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.spring.shop.service.FirebaseFileService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/upload")
public class FirebaseRestController {
    @Autowired
    private FirebaseFileService firebaseFileService;


    @PostMapping(value = "/sanpham", consumes = "multipart/form-data")
    public ResponseEntity<Object> uploadFileSP(@RequestParam(value = "files",required = false) MultipartFile[] files) {
        List<String> listURL = new ArrayList<>();

        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                String urlImage = "";
                try {
                    urlImage = firebaseFileService.saveTest(file);

                    listURL.add(urlImage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            listURL.add("https://firebasestorage.googleapis.com/v0/b/datn-dd682.appspot.com/o/z4943271513376_0ce9c57c5777f0a5033a197d309b62ba.jpg?alt=media&token=5fff8ac7-7688-48f0-9fbf-31a2eba1ec09");
        }
        return new ResponseEntity<>(listURL, HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = "multipart/form-data")
    public ResponseEntity<Object> uploadFile(@RequestParam(value = "files",required = false) MultipartFile[] files) {
        List<String> listURL = new ArrayList<>();

        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                String urlImage = "";
                try {
                    urlImage = firebaseFileService.saveTest(file);

                    listURL.add(urlImage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            listURL.add("https://firebasestorage.googleapis.com/v0/b/datn-dd682.appspot.com/o/044d165d-36ab-47c3-94d5-60b1ef73c8a2?alt=media&token=044d165d-36ab-47c3-94d5-60b1ef73c8a2");
        }
        return new ResponseEntity<>(listURL, HttpStatus.OK);
    }

    @GetMapping(value = "detete")
    public ResponseEntity<Object> getMethodName(@RequestParam String url) throws IOException {
        return ResponseEntity.ok(firebaseFileService.deleteFile(url));
    }

    @GetMapping(value = "getFile")
    public ResponseEntity<byte[]> getFile(@RequestParam String url) throws IOException {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            String contentType = entity.getContentType().getValue();
            InputStream inputStream = entity.getContent();

            // Đọc dữ liệu từ InputStream và ghi vào ByteArrayOutputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            byte[] fileData = outputStream.toByteArray();
            // Xác định MediaType dựa trên ContentType
            MediaType mediaType = MediaType.parseMediaType(contentType);
            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .body(fileData);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
