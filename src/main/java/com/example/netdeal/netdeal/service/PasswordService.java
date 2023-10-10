package com.example.netdeal.netdeal.service;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PasswordService {

    String regexUpperCase ="[A-Z]{1,1}" ;
    String regexLowerCase ="[a-z]{1,1}" ;
    String regexNumberCase ="[0-9]{1,1}" ;
    String regexSimbolCase = "[-!@#\\$%\\^&\\*]{1,1}" ;

    String regexConsecutiveUpperCase = "[A-Z]{1,}" ;
    String regexConsecutiveLowerCase = "[a-z]{1,}" ;
    String regexConsecutiveNumberCase = "[0-9]{1,}" ;


    public void validaPassword(String password){
        int score  = retornaScoreSenha(password);
        System.out.println("score: " + score );
        String textoScore = retornaTextoScore( score) ;
        System.out.println("texto: " + textoScore );
    }


    public String retornaTextoScore(int score){
        String textScore = "Ruim" ;
        if (score<=25 ){
            textScore = "Ruim" ;
        }
        if (score>25 && score<=50 ){
            textScore = "Fraco" ;
        }
        if (score>50 && score<=75 ){
            textScore = "Bom" ;
        }
        if (score>75 ){
            textScore = "Forte" ;
        }

        return textScore ;
    }


    public int retornaScoreSenha(String password){

        String input = password ;
        //String input = "RTabBd1-T%304**";
        //String input = "123877";
        //String input = "AabTT6b93";


        int numberofCharacteres = input.length() ;
        int qtdUpper =  matchCaseGenerico(regexUpperCase,input) ;
        int qtdLower =  matchCaseGenerico(regexLowerCase,input) ;
        int qtdNumber =  matchCaseGenerico(regexNumberCase,input) ;
        int qtdSimbol =  matchCaseGenerico(regexSimbolCase,input) ;
        int qtdMiddleNumberSimbol= somarMiddleNumberAndSimbol(input);


        int resultSomaAdition = somarAdditions( numberofCharacteres,
                                            qtdUpper,  qtdLower,  qtdNumber ,
                                            qtdSimbol,  qtdMiddleNumberSimbol);


        int resultSomaDeducao = somardeducoes( numberofCharacteres,
                                            qtdUpper,  qtdLower,  qtdNumber ,
                                            qtdSimbol,  qtdMiddleNumberSimbol,  input ) ;


        int score = resultSomaAdition - resultSomaDeducao ;

        return score ;

    }

    public int somarAdditions(int numberofCharacteres,
                              int qtdUpper, int qtdLower, int qtdNumber ,
                              int qtdSimbol, int qtdMiddleNumberSimbol){

        int somaBonusCaracteres =  somaBonusCaracteres(numberofCharacteres) ;
        int somaBonusUppercase = somaBonusUppercase( numberofCharacteres,  qtdUpper);
        int somaBonusLowercase = somaBonusLowercase( numberofCharacteres, qtdLower) ;
        int somarBonusNumber = somarBonusNumber(qtdNumber)  ;
        int somarBonusSimbolo = somarBonusSimbolo( qtdSimbol) ;
        int somarBonusMiddleNumberSymbol = somarBonusMiddleNumberSymbol(qtdMiddleNumberSimbol) ;
        int somarBonusRequirements = somarBonusRequirements( numberofCharacteres,
                                            qtdUpper,  qtdLower,  qtdNumber , qtdSimbol);


        //System.out.println("somaBonusCaracteres: " + somaBonusCaracteres );
        //System.out.println("somaBonusUppercase: " + somaBonusUppercase );
        //System.out.println("somaBonusLowercase: " + somaBonusLowercase );
        //System.out.println("somarBonusNumber; " + somarBonusNumber );
        //System.out.println("somarBonusSimbolo: " + somarBonusSimbolo );
        //System.out.println("somarBonusMiddleNumberSymbol: " + somarBonusMiddleNumberSymbol );
        //System.out.println("somarBonusRequirements: " + somarBonusRequirements );

        return (somaBonusCaracteres + somaBonusUppercase + somaBonusLowercase+ somarBonusNumber +
                somarBonusSimbolo+ somarBonusMiddleNumberSymbol + somarBonusRequirements  ) ;

    }

    public int somardeducoes(int numberofCharacteres,
                              int qtdUpper, int qtdLower, int qtdNumber ,
                              int qtdSimbol, int qtdMiddleNumberSimbol, String input ) {

        int qtdDeducaoLetterOnly = deducaoLetterOnly( numberofCharacteres ,  qtdNumber ,  qtdSimbol ) ;
        int qtdDeducaoNumberOnly = deducaoNumberOnly( numberofCharacteres ,  qtdUpper ,  qtdLower,  qtdSimbol );
        int qtdDeducaoConsecutiveUpperCase =  deducaoConsecutiveCase( input , regexConsecutiveUpperCase );
        int qtdDeducaoConsecutiveLowerCase =  deducaoConsecutiveCase( input , regexConsecutiveLowerCase );
        int qtdDeducaoConsecutiveNumberCase =  deducaoConsecutiveCase( input , regexConsecutiveNumberCase );

        //System.out.println(qtdDeducaoLetterOnly);
        //System.out.println(qtdDeducaoNumberOnly);
        //System.out.println(qtdDeducaoConsecutiveUpperCase);
        //System.out.println(qtdDeducaoConsecutiveLowerCase);
        //System.out.println(qtdDeducaoConsecutiveNumberCase);

        return (qtdDeducaoLetterOnly + qtdDeducaoNumberOnly + qtdDeducaoConsecutiveUpperCase +
                qtdDeducaoConsecutiveLowerCase + qtdDeducaoConsecutiveNumberCase ) ;

    }

    public int somaBonusCaracteres(int numberofCharacteres){
        return numberofCharacteres * 4;
    }

    public int somaBonusUppercase(int numberofCharacteres, int qtdUpper){
        if (qtdUpper == 0 ){
            return 0;
        }
        return (numberofCharacteres - qtdUpper)*2 ;
    }

    public int somaBonusLowercase(int numberofCharacteres, int qtdLower){
        if ( qtdLower == 0  ){
            return 0;
        }
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


    public int deducaoConsecutiveCase(String input , String regex ){
        Matcher matterCase = Pattern.compile(regex).matcher(input);
        int countSequencia =0;
        while(matterCase.find()){
            String sequencia = matterCase.group(0);
            if ( sequencia.length()>0 ){
                countSequencia = countSequencia+ (sequencia.length()-1) ;
            }
            //System.out.println(matterCase.group(0));
        }
        return countSequencia ;
    }



}


/*

        boolean valor = Pattern.compile("[A-Z]{1,1}").matcher("AaB").matches();
        System.out.println(valor);

        Matcher matterCase = Pattern.compile("[A-Z]{1,}").matcher("ABCDEFaB") ;
        while(matterCase.find()){
            System.out.println(matterCase.group(0));
        }

 */