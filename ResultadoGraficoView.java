package com.example.testevocacional;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ResultadoGraficoView extends View {

    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private float[] valores = {
            0, 0, 0, 0, 0, 0
    };

    private final String[] nomes = {
            "UI/UX",
            "Prog.",
            "Dados",
            "Seg.",
            "Emb.",
            "BD"
    };

    public ResultadoGraficoView(Context context) {
        super(context);
    }

    public ResultadoGraficoView(
            Context context,
            AttributeSet attrs
    ) {
        super(context, attrs);
    }

    public void definirValores(float[] valores) {

        if (valores == null || valores.length < 6) {
            return;
        }

        this.valores = valores;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        float largura = getWidth();
        float altura = getHeight();

        float margemEsquerda = 30;
        float margemDireita = 5;
        float topo = 10;
        float base = altura - 35;

        float alturaGrafico = base - topo;

        // ==========================
        // LINHAS DO GRÁFICO
        // ==========================

        paint.setStrokeWidth(1);
        paint.setColor(0xFFD0C9DD);

        for (int i = 0; i <= 4; i++) {

            float y =
                    base -
                            alturaGrafico * i / 4f;

            canvas.drawLine(
                    margemEsquerda,
                    y,
                    largura - margemDireita,
                    y,
                    paint
            );
        }

        // ==========================
        // PORCENTAGENS
        // ==========================

        paint.setTextSize(7);
        paint.setColor(0xFF5D5A69);

        String[] porcentagens = {
                "100%",
                "75%",
                "50%",
                "25%",
                "0%"
        };

        for (int i = 0; i < 5; i++) {

            float y =
                    base -
                            alturaGrafico * i / 4f;

            canvas.drawText(
                    porcentagens[i],
                    0,
                    y + 3,
                    paint
            );
        }

        // ==========================
        // BARRAS
        // ==========================

        float areaUtil =
                largura -
                        margemEsquerda -
                        margemDireita;

        float larguraBarra = 22;

        float espaco =
                (areaUtil - (6 * larguraBarra)) / 7;

        int maior = maiorIndice();

        for (int i = 0; i < 6; i++) {

            float x =
                    margemEsquerda +
                            espaco +
                            i * (larguraBarra + espaco);

            float valor = valores[i];

            if (valor < 0) {
                valor = 0;
            }

            if (valor > 100) {
                valor = 100;
            }

            float alturaBarra =
                    alturaGrafico *
                            valor /
                            100f;

            float topoBarra =
                    base -
                            alturaBarra;

            // Barra principal
            if (i == maior) {
                paint.setColor(0xFF5C35C7);
            } else {
                paint.setColor(0xFF8873C9);
            }

            canvas.drawRoundRect(
                    x,
                    topoBarra,
                    x + larguraBarra,
                    base,
                    4,
                    4,
                    paint
            );

            // Nome da área
            paint.setColor(0xFF25253A);
            paint.setTextSize(7);

            float tamanhoTexto =
                    paint.measureText(nomes[i]);

            canvas.drawText(
                    nomes[i],
                    x + (larguraBarra - tamanhoTexto) / 2,
                    base + 14,
                    paint
            );
        }
    }

    private int maiorIndice() {

        int indice = 0;

        for (int i = 1; i < valores.length; i++) {

            if (valores[i] > valores[indice]) {
                indice = i;
            }
        }

        return indice;
    }
}