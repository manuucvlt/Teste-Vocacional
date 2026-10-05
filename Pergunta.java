package com.example.testevocacional;

public class Pergunta {

    public static final int UI_UX = 0;

    public static final int PROGRAMACAO = 1;

    public static final int CIENCIA_DADOS = 2;

    public static final int SEGURANCA = 3;

    public static final int SISTEMAS_EMBARCADOS = 4;

    public static final int BANCO_DADOS = 5;


    private final String texto;

    private final int area;


    public Pergunta(
            String texto,
            int area
    ) {

        this.texto = texto;

        this.area = area;
    }


    public String getTexto() {

        return texto;
    }


    public int getArea() {

        return area;
    }
}