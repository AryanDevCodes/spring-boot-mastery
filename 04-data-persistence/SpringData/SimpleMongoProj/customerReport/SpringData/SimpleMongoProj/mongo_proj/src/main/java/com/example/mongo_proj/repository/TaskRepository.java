package com.example.mongo_proj.repository;

import com.example.mongo_proj.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task,String> {

}
