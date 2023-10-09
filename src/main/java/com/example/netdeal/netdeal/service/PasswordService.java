package com.example.netdeal.netdeal.service;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PasswordService {

    String regexUpperCase ="[A-Z]{1,1}" ;
    String regexLowerCase ="[a-z]{1,1}" ;
    String regexNumberCase ="[0-9]{1,1}" ;
    String regexSimbolCase = "[!@#\\$%\\^&\\*]{1,1}" ;

    public void validaPassword(String password){
        validarCarachetr(password);
    }

    public void validarCarachetr(String password){

        //String input = "RTabBd1-T%304**";
        String input = "123877";

        int numberofCharacteres = input.length() ;
        int qtdUpper =  matchCaseGenerico(regexUpperCase,input) ;
        int qtdLower =  matchCaseGenerico(regexLowerCase,input) ;
        int qtdNumber =  matchCaseGenerico(regexNumberCase,input) ;
        int qtdSimbol =  matchCaseGenerico(regexSimbolCase,input) ;
        int qtdMiddleNumberSimbol= somarMiddleNumberAndSimbol(input);

        //boolean requirementsValido = validaRequirementsAtingido(numberofCharacteres,qtdUpper, qtdLower, qtdNumber,qtdSimbol) ;
        //System.out.println(requirementsValido);

        //int qtdDeducaoLetterOnly = deducaoLetterOnly( numberofCharacteres ,  qtdNumber ,  qtdSimbol ) ;
        //System.out.println(qtdDeducaoLetterOnly);

        int qtdDeducaoNumberOnly = deducaoNumberOnly( numberofCharacteres ,  qtdUpper ,  qtdLower,  qtdSimbol );
        System.out.println(qtdDeducaoNumberOnly);
    }


    public int somaBonusCaracteres(int numberofCharacteres){
        return numberofCharacteres * 4;
    }

    public int somaBonusUppercase(int numberofCharacteres, int qtdUpper){
        return (numberofCharacteres - qtdUpper)*2 ;
    }

    public int somaBonusLowercase(int numberofCharacteres, int qtdLower){
        return (numberofCharacteres - qtdLower)*2 ;
    }

    public int somarBonusNumber(int qtdNumber){
        return qtdNumber * 4 ;
    }

    public int somarBonusSimbolo(int qtdSimbol){
        return qtdSimbol * 6 ;
    }

    public int somarBonusMiddleNumberSymbol(int qtdMiddleNumberSimbol){
        return qtdMiddleNumberSimbol * 2 ;
    }


    public int somarBonusRequirements(int numberofCharacteres,
                                      int qtdUpper, int qtdLower, int qtdNumber ,
                                      int qtdSimbol){

        boolean numerCaracteresValido =  ehvalidoNumeroCaracteresSenha( numberofCharacteres) ;
        if (!numerCaracteresValido) {//volta bonus 0 se nao atingir numero de caracteres da senha 8
            return 0 ;
        }

        //fazer logica restandte para calculo do bonus
        int qtdeRequirementAtingido = validaqtdeRequirementsAtingido(numberofCharacteres,
                                                            qtdUpper,qtdLower,qtdNumber,qtdSimbol);

        if ( qtdeRequirementAtingido < 3 ){
            return 0 ;
        }

        qtdeRequirementAtingido++ ; //soma 1 do tamanho da senha

        return qtdeRequirementAtingido * 2 ;
    }


    public boolean ehvalidoNumeroCaracteresSenha(int numberofCharacteres){
        if ( numberofCharacteres <8 ){
            return false;
        }
        return true ;
    }

    public int validaqtdeRequirementsAtingido(int numberofCharacteres,
                                      int qtdUpper, int qtdLower, int qtdNumber ,
                                      int qtdSimbol ){
        int countRequirements = 0 ;

        if (qtdUpper>0 ){
            countRequirements++;
        }
        if (qtdLower>0 ){
            countRequirements++;
        }
        if (qtdNumber>0 ){
            countRequirements++;
        }
        if (qtdSimbol>0 ){
            countRequirements++;
        }

        return countRequirements ;
    }


    public int somarMiddleNumberAndSimbol(String input){
        String middleNumberSymbol = input.substring(1,input.length()-1) ;
        int qtdNumber =  matchCaseGenerico(regexNumberCase,middleNumberSymbol) ;
        int qtdSimbol =  matchCaseGenerico(regexSimbolCase,middleNumberSymbol) ;
        return qtdNumber + qtdSimbol ;
    }


    public int matchCaseGenerico(String regex, String input){
        int countCase = 0 ;
        Matcher matterCase =  Pattern.compile(regex).matcher(input) ;
        while(matterCase.find()){
            //System.out.println(matterCase.group(0));
            countCase++;
        }
        return countCase;
    }


    public int deducaoLetterOnly(int numberofCharacteres , int qtdNumber , int qtdSimbol ){

        if ( (qtdNumber> 0) || (qtdSimbol>0) ){
            return 0 ;
        }
        return numberofCharacteres * (-1);
    }

    public int deducaoNumberOnly(int numberofCharacteres , int qtdUpper , int qtdLower, int qtdSimbol ){

        if ( (qtdUpper> 0) || (qtdLower>0) || (qtdSimbol>0) ){
            return 0 ;
        }
        return numberofCharacteres * (-1);
    }





}
