package com.example.netdeal.netdeal.repository;


import com.example.netdeal.netdeal.model.ColaboradorModelWraaper;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ColaboradorRepository extends MongoRepository<ColaboradorModelWraaper,String> {
}
