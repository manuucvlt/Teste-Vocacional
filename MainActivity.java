package com.example.testevocacional;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView txtPergunta;
    private TextView txtNumeroPergunta;

    private TextView opcao1;
    private TextView opcao2;
    private TextView opcao3;
    private TextView opcao4;
    private TextView opcao5;

    private TextView btnAnterior;
    private TextView btnProximo;

    private ProgressBar progresso;

    private List<Pergunta> perguntas;

    /*
     * Guarda a resposta de cada pergunta.
     *
     * 0 = não respondeu
     * 1 = Discordo totalmente
     * 2 = Discordo
     * 3 = Neutro
     * 4 = Concordo
     * 5 = Concordo totalmente
     */
    private int[] respostas;

    private int perguntaAtual = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        criarPerguntas();

        respostas = new int[perguntas.size()];

        inicializarComponentes();

        configurarCliques();

        atualizarTela();
    }


    // =========================================================
    // PERGUNTAS
    // =========================================================

    private void criarPerguntas() {

        perguntas = new ArrayList<>();

        // -----------------------------------------------------
        // DESIGN UI/UX
        // -----------------------------------------------------

        perguntas.add(
                new Pergunta(
                        "Gosto de criar interfaces visualmente organizadas e agradáveis.",
                        Pergunta.UI_UX
                )
        );

        perguntas.add(
                new Pergunta(
                        "Tenho interesse em entender como as pessoas interagem com aplicativos e sites.",
                        Pergunta.UI_UX
                )
        );

        perguntas.add(
                new Pergunta(
                        "Gosto de pensar em maneiras de tornar uma experiência digital mais simples e intuitiva.",
                        Pergunta.UI_UX
                )
        );


        // -----------------------------------------------------
        // PROGRAMAÇÃO
        // -----------------------------------------------------

        perguntas.add(
                new Pergunta(
                        "Gosto de resolver problemas utilizando lógica e raciocínio.",
                        Pergunta.PROGRAMACAO
                )
        );

        perguntas.add(
                new Pergunta(
                        "Tenho interesse em criar aplicativos, sistemas ou programas.",
                        Pergunta.PROGRAMACAO
                )
        );

        perguntas.add(
                new Pergunta(
                        "Tenho curiosidade em entender como os códigos funcionam por trás de uma aplicação.",
                        Pergunta.PROGRAMACAO
                )
        );


        // -----------------------------------------------------
        // CIÊNCIA DE DADOS
        // -----------------------------------------------------

        perguntas.add(
                new Pergunta(
                        "Gosto de analisar informações para encontrar padrões.",
                        Pergunta.CIENCIA_DADOS
                )
        );

        perguntas.add(
                new Pergunta(
                        "Tenho interesse em trabalhar com números, estatísticas e dados.",
                        Pergunta.CIENCIA_DADOS
                )
        );

        perguntas.add(
                new Pergunta(
                        "Gosto de utilizar informações para chegar a conclusões e tomar decisões.",
                        Pergunta.CIENCIA_DADOS
                )
        );


        // -----------------------------------------------------
        // SEGURANÇA DA INFORMAÇÃO
        // -----------------------------------------------------

        perguntas.add(
                new Pergunta(
                        "Tenho interesse em descobrir vulnerabilidades e proteger sistemas.",
                        Pergunta.SEGURANCA
                )
        );

        perguntas.add(
                new Pergunta(
                        "Gosto de pensar em maneiras de evitar acessos não autorizados.",
                        Pergunta.SEGURANCA
                )
        );

        perguntas.add(
                new Pergunta(
                        "Tenho interesse em privacidade, proteção de dados e segurança digital.",
                        Pergunta.SEGURANCA
                )
        );


        // -----------------------------------------------------
        // SISTEMAS EMBARCADOS
        // -----------------------------------------------------

        perguntas.add(
                new Pergunta(
                        "Tenho interesse em entender como dispositivos eletrônicos funcionam.",
                        Pergunta.SISTEMAS_EMBARCADOS
                )
        );

        perguntas.add(
                new Pergunta(
                        "Gosto da ideia de programar equipamentos, sensores ou dispositivos inteligentes.",
                        Pergunta.SISTEMAS_EMBARCADOS
                )
        );

        perguntas.add(
                new Pergunta(
                        "Tenho curiosidade sobre tecnologias que unem hardware e software.",
                        Pergunta.SISTEMAS_EMBARCADOS
                )
        );


        // -----------------------------------------------------
        // BANCO DE DADOS
        // -----------------------------------------------------

        perguntas.add(
                new Pergunta(
                        "Gosto de organizar grandes quantidades de informações.",
                        Pergunta.BANCO_DADOS
                )
        );

        perguntas.add(
                new Pergunta(
                        "Tenho interesse em entender como os dados são armazenados e consultados.",
                        Pergunta.BANCO_DADOS
                )
        );

        perguntas.add(
                new Pergunta(
                        "Gosto de trabalhar com estruturas organizadas para facilitar o acesso às informações.",
                        Pergunta.BANCO_DADOS
                )
        );
    }


    // =========================================================
    // INICIALIZAÇÃO DOS COMPONENTES
    // =========================================================

    private void inicializarComponentes() {

        txtPergunta = findViewById(R.id.txtPergunta);

        txtNumeroPergunta =
                findViewById(R.id.txtNumeroPergunta);

        opcao1 = findViewById(R.id.opcao1);
        opcao2 = findViewById(R.id.opcao2);
        opcao3 = findViewById(R.id.opcao3);
        opcao4 = findViewById(R.id.opcao4);
        opcao5 = findViewById(R.id.opcao5);

        btnAnterior =
                findViewById(R.id.btnAnterior);

        btnProximo =
                findViewById(R.id.btnProximo);

        progresso =
                findViewById(R.id.progresso);
    }


    // =========================================================
    // CLIQUES
    // =========================================================

    private void configurarCliques() {

        opcao1.setOnClickListener(
                v -> selecionarResposta(1)
        );

        opcao2.setOnClickListener(
                v -> selecionarResposta(2)
        );

        opcao3.setOnClickListener(
                v -> selecionarResposta(3)
        );

        opcao4.setOnClickListener(
                v -> selecionarResposta(4)
        );

        opcao5.setOnClickListener(
                v -> selecionarResposta(5)
        );


        btnAnterior.setOnClickListener(
                v -> voltarPergunta()
        );


        btnProximo.setOnClickListener(
                v -> proximaPergunta()
        );
    }


    // =========================================================
    // SELECIONAR RESPOSTA
    // =========================================================

    private void selecionarResposta(int resposta) {

        respostas[perguntaAtual] = resposta;

        atualizarOpcoes();
    }


    // =========================================================
    // ATUALIZAR VISUAL DAS OPÇÕES
    // =========================================================

    private void atualizarOpcoes() {

        opcao1.setBackgroundResource(
                R.drawable.bg_option
        );

        opcao2.setBackgroundResource(
                R.drawable.bg_option
        );

        opcao3.setBackgroundResource(
                R.drawable.bg_option
        );

        opcao4.setBackgroundResource(
                R.drawable.bg_option
        );

        opcao5.setBackgroundResource(
                R.drawable.bg_option
        );


        int resposta =
                respostas[perguntaAtual];


        if (resposta == 1) {

            opcao1.setBackgroundResource(
                    R.drawable.bg_option_selected
            );
        }

        if (resposta == 2) {

            opcao2.setBackgroundResource(
                    R.drawable.bg_option_selected
            );
        }

        if (resposta == 3) {

            opcao3.setBackgroundResource(
                    R.drawable.bg_option_selected
            );
        }

        if (resposta == 4) {

            opcao4.setBackgroundResource(
                    R.drawable.bg_option_selected
            );
        }

        if (resposta == 5) {

            opcao5.setBackgroundResource(
                    R.drawable.bg_option_selected
            );
        }
    }


    // =========================================================
    // ATUALIZAR A PERGUNTA
    // =========================================================

    private void atualizarTela() {

        Pergunta pergunta =
                perguntas.get(perguntaAtual);


        txtPergunta.setText(
                pergunta.getTexto()
        );


        txtNumeroPergunta.setText(
                (perguntaAtual + 1)
                        + " de "
                        + perguntas.size()
        );


        progresso.setProgress(
                perguntaAtual + 1
        );


        atualizarOpcoes();
    }


    // =========================================================
    // PRÓXIMA PERGUNTA
    // =========================================================

    private void proximaPergunta() {

        /*
         * Não permite avançar sem responder.
         */

        if (respostas[perguntaAtual] == 0) {
            return;
        }


        if (
                perguntaAtual <
                        perguntas.size() - 1
        ) {

            perguntaAtual++;

            atualizarTela();

        } else {

            abrirResultado();
        }
    }


    // =========================================================
    // VOLTAR
    // =========================================================

    private void voltarPergunta() {

        if (perguntaAtual > 0) {

            perguntaAtual--;

            atualizarTela();

        } else {

            finish();
        }
    }


    // =========================================================
    // ABRIR RESULTADO
    // =========================================================

    private void abrirResultado() {

        Intent intent =
                new Intent(
                        MainActivity.this,
                        ResultadoTesteActivity.class
                );


        intent.putExtra(
                "respostas",
                respostas
        );


        startActivity(intent);
    }
}