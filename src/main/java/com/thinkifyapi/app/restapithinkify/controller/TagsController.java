package com.thinkifyapi.app.restapithinkify.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.thinkifyapi.app.restapithinkify.models.Tags;
import com.thinkifyapi.app.restapithinkify.repository.TagsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TagsController {

    @Autowired(required=true)
    TagsRepository tagsRepository;

    @GetMapping("/tags")
    public ResponseEntity<List<Tags>> getAllTags(@RequestParam(required = false) String user){
        try {
            List<Tags> tags = new ArrayList<Tags>();

            if (user == null)
               tagsRepository.findAll().forEach(tags::add);
            else
               tagsRepository.findByUserContaining(user).forEach(tags::add);
            if (tags.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tags, HttpStatus.OK);
            } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/tags/{id}")
    public ResponseEntity<Tags> getTagsById(@PathVariable("id") String id){
        Optional<Tags> tagsData = tagsRepository.findById(id);

        if (tagsData.isPresent()) {
          return new ResponseEntity<>(tagsData.get(), HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tags")
    public ResponseEntity<Tags> createTags(@RequestBody Tags tag){
        try {
            Tags _tags = tagsRepository.save(new Tags(tag.getEpcColorLED(), tag.getBaudrate(),
                tag.getSerialPort(), tag.getPasscode(), tag.getEpc(), tag.getTid(), tag.getUser(), false));
            return new ResponseEntity<>(_tags, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }

    @PutMapping("/tags/{id}")
    public ResponseEntity<Tags> updateTags(@PathVariable("id") String id, @RequestBody Tags tag){
        Optional<Tags> tagData = tagsRepository.findById(id);

        if (tagData.isPresent()) {
          Tags _tag = tagData.get();
          _tag.setEpcColorLED(tag.getEpc());
          _tag.setBaudrate(tag.getBaudrate());
          _tag.setSerialPort(tag.getSerialPort());
          _tag.setPasscode(tag.getPasscode());
          _tag.setEpc(tag.getEpc());
          _tag.setTid(tag.getTid());
          _tag.setUser(tag.getUser());
          _tag.setActive(tag.getActive());
          return new ResponseEntity<>(tagsRepository.save(_tag), HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/tags/{id}")
    public ResponseEntity<HttpStatus> deleteTag(@PathVariable("id") String id){
        try {
            tagsRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }

    @DeleteMapping("/tags")
    public ResponseEntity<HttpStatus> deleteAllTags() {
        try {
            tagsRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }

    @GetMapping("/tags/active")
  public ResponseEntity<List<Tags>> findByActive() {
    try {
        List<Tags> tags = tagsRepository.findByActive(true);
    
        if (tags.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tags, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

    
}
