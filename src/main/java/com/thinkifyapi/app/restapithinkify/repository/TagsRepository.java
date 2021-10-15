package com.thinkifyapi.app.restapithinkify.repository;
import java.util.List;

import com.thinkifyapi.app.restapithinkify.models.Tags;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRepository extends MongoRepository<Tags, String>{
    List<Tags> findByUserContaining(String user);
    List<Tags> findByEpcContaining(String epc);
    List<Tags> findByTidContaining(String tid);
    List<Tags> findByActive(Boolean active);
    List<Tags> findByTagId(long tagId);
    Tags findTopByOrderByTimestampDesc();
}
