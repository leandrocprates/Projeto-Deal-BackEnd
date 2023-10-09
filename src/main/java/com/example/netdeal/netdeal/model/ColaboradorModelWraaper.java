package com.example.netdeal.netdeal.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
public class ColaboradorModelWraaper {
    List<ColaboradorModel> colaboradorModel ;
}
