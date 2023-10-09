package com.example.netdeal.netdeal.service;

import com.example.netdeal.netdeal.model.ColaboradorModel;
import com.example.netdeal.netdeal.model.ColaboradorModelWraaper;
import com.example.netdeal.netdeal.repository.ColaboradorRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository ;

    public void salvarMongoDb(List<ColaboradorModel> colaboradorModel ){
        ColaboradorModelWraaper colaboradorModelWraaper = new ColaboradorModelWraaper();
        colaboradorModelWraaper.setColaboradorModel(colaboradorModel);
        colaboradorRepository.save(colaboradorModelWraaper);
    }

    public List<ColaboradorModel> buscarColaborador(){
        ColaboradorModelWraaper colaboradorModelWraaper = colaboradorRepository.findAll().get(0);
        return colaboradorModelWraaper.getColaboradorModel();
    }

}
