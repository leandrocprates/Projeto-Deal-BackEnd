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

    @Autowired
    private PasswordService passwordService ;

    public void salvarMongoDb(List<ColaboradorModel> colaboradorModel ){

        colaboradorRepository.deleteAll();
        transformarSenha(colaboradorModel);

        ColaboradorModelWraaper colaboradorModelWraaper = new ColaboradorModelWraaper();
        colaboradorModelWraaper.setColaboradorModel(colaboradorModel);
        colaboradorRepository.save(colaboradorModelWraaper);
    }


    public void transformarSenha(List<ColaboradorModel> colaboradorModel){

        for (ColaboradorModel colNivel1 : colaboradorModel ){
            buscarScore(colNivel1);
            for ( ColaboradorModel colNivel2 : colNivel1.getRefColaborador()){
                buscarScore(colNivel2);
                for(ColaboradorModel colNivel3 : colNivel2.getRefColaborador()){
                    buscarScore(colNivel3);
                }
            }
        }
        return ;
    }



    public void buscarScore(ColaboradorModel colNivel){
        int score = passwordService.retornaScoreSenha(colNivel.getSenhaColaborador());
        String textoScore = passwordService.retornaTextoScore(score);
        //System.out.println("score: " + score );
        //System.out.println("textoScore: " + textoScore );

        colNivel.setPorcentagem(score+"%");
        colNivel.setTexto(textoScore);

    }



    public List<ColaboradorModel> buscarColaborador(){
        ColaboradorModelWraaper colaboradorModelWraaper = colaboradorRepository.findAll().get(0);
        return colaboradorModelWraaper.getColaboradorModel();
    }

}
