package com.pedroacbg.blog.services;

import com.pedroacbg.blog.domain.model.Tag;
import com.pedroacbg.blog.repositories.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    Logger logger = LoggerFactory.getLogger(TagService.class);

    public List<Tag> getTags(){
        logger.info("Listando todas as tags...");
        return tagRepository.findAllWithPostCount();
    }

    public Tag getTagById(Long id){
        logger.info("Buscando tag por id...");
        return tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nenhuma tag encontrada com este id "+ id));
    }

    public List<Tag> getTagByIds(Set<Long> ids){
        logger.info("Buscando Tags por IDs...");
        List<Tag> foundTags = tagRepository.findAllById(ids);
        if(foundTags.size() != ids.size()){
            throw new EntityNotFoundException("Nem todos os IDs de tag especificados existem");
        }
        return foundTags;
    }

    @Transactional
    public List<Tag> createTags(Set<String> tagNames){
        logger.info("Criando uma ou mais tags...");

       List<Tag> existingTags = tagRepository.findByNameIn(tagNames);
       Set<String> existingTagNames = existingTags.stream().map(tag -> tag.getName()).collect(Collectors.toSet());

       List<Tag> newTags = tagNames.stream().filter(name -> !existingTagNames.contains(name))
               .map(name -> {
                   Tag newTag = new Tag();
                   newTag.setName(name);
                   newTag.setPosts(new HashSet<>());
                   return newTag;
               }).toList();

       List<Tag> savedTags = new ArrayList<>();
       if(!newTags.isEmpty()){
           savedTags = tagRepository.saveAll(newTags);
       }

       savedTags.addAll(existingTags);
       return savedTags;
    }

    @Transactional
    public void deleteTag(Long id){
        logger.info("Verificando se a tag contém posts associados...");
        tagRepository.findById(id).ifPresent(tag -> {
            if(!tag.getPosts().isEmpty()){
                throw new IllegalStateException("Não é possível deletar uma tag que contém posts associados.");
            }
            logger.info("Deletando a tag...");
            tagRepository.deleteById(id);
        });
    }

}
