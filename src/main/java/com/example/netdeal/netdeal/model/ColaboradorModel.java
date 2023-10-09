package com.example.netdeal.netdeal.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@Document
public class ColaboradorModel {
    @Id
    private String id ;
    private String nomeColaborador;
    private String senhaColaborador;
    private String porcentagem;
    private String texto;
    private List<ColaboradorModel> refColaborador ;
}
