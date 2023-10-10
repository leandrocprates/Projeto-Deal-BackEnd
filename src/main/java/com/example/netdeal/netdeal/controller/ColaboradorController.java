package com.example.netdeal.netdeal.controller;

import com.example.netdeal.netdeal.model.ColaboradorModel;
import com.example.netdeal.netdeal.service.ColaboradorService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ColaboradorController {

    @Autowired
    private ColaboradorService colaboradorService ;

    @PostMapping(value = "/salvarcolaborador")
    public ResponseEntity salvarColaborador(@RequestBody List<ColaboradorModel> colaboradorModel  ){

        System.out.println(new Gson().toJson(colaboradorModel));
        colaboradorService.salvarMongoDb(colaboradorModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/buscarcolaborador")
    public ResponseEntity buscarColaboradores(){
        return new ResponseEntity<>(colaboradorService.buscarColaborador(),HttpStatus.OK);
    }

}
