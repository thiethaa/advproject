package com.thiethaa.advproject.controller;

import com.thiethaa.advproject.entity.Advproject;
import com.thiethaa.advproject.service.AdvprojectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/advproject")
public class AdvprojectController {

    @Autowired
    AdvprojectService service;

    @GetMapping("/advList")
    public ResponseEntity<List<Advproject>> getAdvprojectList() {
        List<Advproject> advprojectList = service.getAdvprojectList();
        return new ResponseEntity<List<Advproject>>(advprojectList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("adv/{id}")
    public ResponseEntity<Advproject> getAdvprojectById(@PathVariable("id") String id) {
        Advproject advproject = service.getAdvprojectById(id);
        return new ResponseEntity<Advproject>(advproject, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping ("displayFile/{id}")
    public ResponseEntity<byte[]> displayImage(@PathVariable("id") String id) {
        // Load file from database and show it in the browswer
        Advproject advproject = service.getAdvprojectById(id);

        byte[] imageBytes = advproject.getImage();

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> storeFile(@RequestParam("file")MultipartFile file,
                                             @RequestParam("name") String name,
                                             @RequestParam("ingredient") String ingredient){
        service.storeFile(file,name, ingredient);
        String msg = "New Employee :: "+name+" has been added";
        return new ResponseEntity<>("Message send: " + msg, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Advproject> updateAdvImage(@RequestParam("file") MultipartFile file,
                                                     @PathVariable("id") String id,
                                                     @RequestParam("name") String name,
                                                     @RequestParam("ingredient") String ingredient){
        Advproject newAdv = service.updateFile(file,name,ingredient,id);
        return new ResponseEntity<Advproject>(newAdv, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteAdvprojectById(@PathVariable("id") String id){
        service.deleteAdvprojectById(id);
        return HttpStatus.FORBIDDEN;
    }
}
