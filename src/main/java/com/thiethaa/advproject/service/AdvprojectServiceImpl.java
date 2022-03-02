package com.thiethaa.advproject.service;

import com.thiethaa.advproject.entity.Advproject;
import com.thiethaa.advproject.exception.MyException;
import com.thiethaa.advproject.repository.AdvprojectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdvprojectServiceImpl implements AdvprojectService {

    @Autowired
    AdvprojectRepository repository;

    @Transactional
    public Advproject storeFile(MultipartFile file, String name, String ingredient) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            Advproject advproject = new Advproject();
            advproject.setId(UUID.randomUUID().toString());
            advproject.setName(name);
            advproject.setIngredient(ingredient);
            advproject.setFileName(fileName);
            advproject.setImage(file.getBytes());
            advproject.setFileType(file.getContentType());

            String displayFileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/advprojevt/displayImage/")
                    .path(advproject.getId())
                    .toUriString();

            advproject.setThumbnailUrl(displayFileUrl);
            return repository.save(advproject);

        } catch (Exception e) {
            e.printStackTrace(System.err);
            throw new MyException("Could not Store file " + name + " please try again!");
        }
    }

    public Advproject updateFile(MultipartFile file, String name, String ingredient, String id) {
        Advproject newAdv = null;
        if (id != null) {
            Optional<Advproject> advprojectOptional = repository.findById(id);
            if (advprojectOptional.isPresent()) {
                newAdv = advprojectOptional.get();
                try {
                    newAdv.setName(name);
                    newAdv.setIngredient(ingredient);
                    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                    newAdv.setFileName(fileName);
                    newAdv.setImage(file.getBytes());
                    newAdv.setFileType(file.getContentType());

                    String displayFileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/advproject/displayImage/")
                            .path(newAdv.getId())
                            .toUriString();

                    newAdv.setThumbnailUrl(displayFileUrl);
                    repository.save(newAdv);
                    return repository.save(newAdv);
                } catch (Exception ex) {
                    System.err.println("Error updating service with id: " + id);
                    ex.printStackTrace(System.err);
                }
            }
        }
        return newAdv;
    }

    @Override
    public List<Advproject> getAdvprojectList() {
        List<Advproject> advprojectsList = repository.findAll();
        return advprojectsList;
    }

    @Override
    public Advproject getAdvprojectById(String id) throws MyException {
        Optional<Advproject> advprojectOptional = repository.findById(id);
        if (advprojectOptional.isPresent()) {
            return advprojectOptional.get();
        } else {
            throw new MyException("No Record Found!");
        }
    }

    @Override
    public void deleteAdvprojectById(String id) throws MyException {
        Optional<Advproject> advprojectOptional = repository.findById(id);
        if (advprojectOptional.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new MyException("No Record Found!");
        }
    }


}
