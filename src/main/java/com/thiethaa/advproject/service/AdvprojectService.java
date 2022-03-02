package com.thiethaa.advproject.service;

import com.thiethaa.advproject.entity.Advproject;
import com.thiethaa.advproject.exception.MyException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdvprojectService {

    Advproject storeFile(MultipartFile file, String name, String ingredient);

    Advproject updateFile(MultipartFile file, String name, String ingredient, String id);

    List<Advproject> getAdvprojectList();

    Advproject getAdvprojectById(String id) throws MyException;

    void deleteAdvprojectById(String id) throws MyException;
}
