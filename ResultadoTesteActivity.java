package com.example.testevocacional;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultadoTesteActivity extends AppCompatActivity {

    private ResultadoGraficoView grafico;

    private TextView txtAreaPrincipal;

    private LinearLayout btnRefazer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_resultado_teste);


        // ==============================
        // COMPONENTES DA TELA
        // ==============================

        grafico =
                findViewById(R.id.graficoResultado);

        txtAreaPrincipal =
                findViewById(R.id.txtAreaPrincipal);

        btnRefazer =
                findViewById(R.id.btnRefazer);


        // ==============================
        // RECEBER RESPOSTAS
        // ==============================

        int[] respostas =
                getIntent().getIntArrayExtra("respostas");


        // Caso não tenha recebido respostas
        if (respostas == null) {

            respostas = new int[18];
        }


        // ==============================
        // CALCULAR RESULTADO
        // ==============================

        calcularResultado(respostas);


        // ==============================
        // BOTÃO REFAZER
        // ==============================

        btnRefazer.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            ResultadoTesteActivity.this,
                            ResultadoTesteActivity.class
                    );

            startActivity(intent);

            finish();
        });
    }


    private void calcularResultado(int[] respostas) {

        /*
         * Cada posição representa uma área:
         *
         * 0 = Design UI/UX
         * 1 = Programação
         * 2 = Ciência de Dados
         * 3 = Segurança
         * 4 = Sistemas Embarcados
         * 5 = Banco de Dados
         */

        float[] pontos = {
                0,
                0,
                0,
                0,
                0,
                0
        };


        int[] quantidade = {
                0,
                0,
                0,
                0,
                0,
                0
        };


        /*
         * As 18 perguntas são divididas
         * em 6 áreas, 3 perguntas para cada.
         */

        int[] areas = {

                // UI/UX
                Pergunta.UI_UX,
                Pergunta.UI_UX,
                Pergunta.UI_UX,

                // Programação
                Pergunta.PROGRAMACAO,
                Pergunta.PROGRAMACAO,
                Pergunta.PROGRAMACAO,

                // Ciência de Dados
                Pergunta.CIENCIA_DADOS,
                Pergunta.CIENCIA_DADOS,
                Pergunta.CIENCIA_DADOS,

                // Segurança
                Pergunta.SEGURANCA,
                Pergunta.SEGURANCA,
                Pergunta.SEGURANCA,

                // Sistemas Embarcados
                Pergunta.SISTEMAS_EMBARCADOS,
                Pergunta.SISTEMAS_EMBARCADOS,
                Pergunta.SISTEMAS_EMBARCADOS,

                // Banco de Dados
                Pergunta.BANCO_DADOS,
                Pergunta.BANCO_DADOS,
                Pergunta.BANCO_DADOS
        };


        // ==============================
        // SOMAR RESPOSTAS
        // ==============================

        for (
                int i = 0;
                i < respostas.length && i < areas.length;
                i++
        ) {

            // 0 significa que não respondeu
            if (respostas[i] == 0) {
                continue;
            }


            int area = areas[i];


            pontos[area] += respostas[i];

            quantidade[area]++;
        }


        // ==============================
        // CALCULAR PORCENTAGENS
        // ==============================

        float[] porcentagens = {
                0,
                0,
                0,
                0,
                0,
                0
        };


        for (int i = 0; i < 6; i++) {

            if (quantidade[i] > 0) {

                float media =
                        pontos[i] /
                                quantidade[i];


                /*
                 * Converte a escala:
                 *
                 * 1 = 0%
                 * 2 = 25%
                 * 3 = 50%
                 * 4 = 75%
                 * 5 = 100%
                 */

                porcentagens[i] =
                        ((media - 1) / 4f) * 100f;
            }
        }


        // ==============================
        // ENVIAR PARA O GRÁFICO
        // ==============================

        grafico.definirValores(
                porcentagens
        );


        // ==============================
        // MOSTRAR MAIOR AFINIDADE
        // ==============================

        mostrarMaiorArea(porcentagens);
    }


    private void mostrarMaiorArea(
            float[] porcentagens
    ) {

        int maior = 0;


        for (int i = 1; i < 6; i++) {

            if (
                    porcentagens[i] >
                            porcentagens[maior]
            ) {

                maior = i;
            }
        }


        String[] nomes = {

                "Design UI/UX",

                "Programação",

                "Ciência de Dados",

                "Segurança da Informação",

                "Sistemas Embarcados",

                "Banco de Dados"
        };


        txtAreaPrincipal.setText(

                "Sua maior afinidade é com\n"
                        + nomes[maior]
                        + "."
        );
    }
}