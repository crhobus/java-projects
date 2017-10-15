package org.calc.client;

import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.calc.client.service.GWTService;
import org.calc.client.service.GWTServiceAsync;

public class Calculadora extends FormPanel {

    private Button btZero;
    private Button btUm;
    private Button btDois;
    private Button btTres;
    private Button btQuatro;
    private Button btCinco;
    private Button btSeis;
    private Button btSete;
    private Button btOito;
    private Button btNove;
    private Button btVirgula;
    private Button btSoma;
    private Button btSubtracao;
    private Button btMultiplicacao;
    private Button btDivisao;
    private Button btIgual;
    private Button btLimpar;
    private TextBox tbDisplay;
    private AsyncCallback<String> callback;

    public Calculadora() {
        setForm(this);
    }

    private void setForm(FormPanel form) {
        btZero = new Button("0");
        btUm = new Button("1");
        btDois = new Button("2");
        btTres = new Button("3");
        btQuatro = new Button("4");
        btCinco = new Button("5");
        btSeis = new Button("6");
        btSete = new Button("7");
        btOito = new Button("8");
        btNove = new Button("9");
        btVirgula = new Button(",");
        btSoma = new Button("+");
        btSubtracao = new Button("-");
        btMultiplicacao = new Button("*");
        btDivisao = new Button("/");
        btIgual = new Button("=");
        btLimpar = new Button("Clear");
        tbDisplay = new TextBox();

        btZero.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent evt) {
                tbDisplay.setText(tbDisplay.getText() + "0");
            }
        });

        btUm.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent evt) {
                tbDisplay.setText(tbDisplay.getText() + "1");
            }
        });

        btDois.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent evt) {
                tbDisplay.setText(tbDisplay.getText() + "2");
            }
        });

        btTres.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent evt) {
                tbDisplay.setText(tbDisplay.getText() + "3");
            }
        });

        btQuatro.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent evt) {
                tbDisplay.setText(tbDisplay.getText() + "4");
            }
        });

        btCinco.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent evt) {
                tbDisplay.setText(tbDisplay.getText() + "5");
            }
        });

        btSeis.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent evt) {
                tbDisplay.setText(tbDisplay.getText() + "6");
            }
        });

        btSete.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent evt) {
                tbDisplay.setText(tbDisplay.getText() + "7");
            }
        });

        btOito.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent evt) {
                tbDisplay.setText(tbDisplay.getText() + "8");
            }
        });

        btNove.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent evt) {
                tbDisplay.setText(tbDisplay.getText() + "9");
            }
        });

        btVirgula.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent evt) {
                if (!"".equals(tbDisplay.getText())
                        && !tbDisplay.getText().endsWith(" + ")
                        && !tbDisplay.getText().endsWith(" - ")
                        && !tbDisplay.getText().endsWith(" * ")
                        && !tbDisplay.getText().endsWith(" / ")
                        && !tbDisplay.getText().endsWith(",")) {
                    tbDisplay.setText(tbDisplay.getText() + ",");
                }
            }
        });

        btSoma.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent evt) {
                if (!"".equals(tbDisplay.getText())
                        && !tbDisplay.getText().endsWith(" + ")
                        && !tbDisplay.getText().endsWith(" - ")
                        && !tbDisplay.getText().endsWith(" * ")
                        && !tbDisplay.getText().endsWith(" / ")
                        && !tbDisplay.getText().endsWith(",")) {
                    tbDisplay.setText(tbDisplay.getText() + " + ");
                }
            }
        });

        btSubtracao.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent evt) {
                if (!"".equals(tbDisplay.getText())
                        && !tbDisplay.getText().endsWith(" + ")
                        && !tbDisplay.getText().endsWith(" - ")
                        && !tbDisplay.getText().endsWith(" * ")
                        && !tbDisplay.getText().endsWith(" / ")
                        && !tbDisplay.getText().endsWith(",")) {
                    tbDisplay.setText(tbDisplay.getText() + " - ");
                }
            }
        });

        btMultiplicacao.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent evt) {
                if (!"".equals(tbDisplay.getText())
                        && !tbDisplay.getText().endsWith(" + ")
                        && !tbDisplay.getText().endsWith(" - ")
                        && !tbDisplay.getText().endsWith(" * ")
                        && !tbDisplay.getText().endsWith(" / ")
                        && !tbDisplay.getText().endsWith(",")) {
                    tbDisplay.setText(tbDisplay.getText() + " * ");
                }
            }
        });

        btDivisao.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent evt) {
                if (!"".equals(tbDisplay.getText())
                        && !tbDisplay.getText().endsWith(" + ")
                        && !tbDisplay.getText().endsWith(" - ")
                        && !tbDisplay.getText().endsWith(" * ")
                        && !tbDisplay.getText().endsWith(" / ")
                        && !tbDisplay.getText().endsWith(",")) {
                    tbDisplay.setText(tbDisplay.getText() + " / ");
                }
            }
        });

        callback = new AsyncCallback<String>() {

            @Override
            public void onSuccess(String result) {
                tbDisplay.setText(result.replace(".", ","));
            }

            @Override
            public void onFailure(Throwable caught) {
                tbDisplay.setText("Falha de comunicação");
            }
        };

        btIgual.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent evt) {
                getService().resultado(tbDisplay.getText(), callback);
            }
        });

        btLimpar.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent evt) {
                tbDisplay.setText("");
            }
        });

        tbDisplay.setReadOnly(true);
        tbDisplay.setPixelSize(205, 25);
        Grid gDisplay = new Grid(1, 1);
        gDisplay.setWidget(0, 0, tbDisplay);

        btSete.setPixelSize(50, 30);
        btOito.setPixelSize(50, 30);
        btNove.setPixelSize(50, 30);
        btDivisao.setPixelSize(50, 30);
        btQuatro.setPixelSize(50, 30);
        btCinco.setPixelSize(50, 30);
        btSeis.setPixelSize(50, 30);
        btMultiplicacao.setPixelSize(50, 30);
        btUm.setPixelSize(50, 30);
        btDois.setPixelSize(50, 30);
        btTres.setPixelSize(50, 30);
        btSubtracao.setPixelSize(50, 30);
        btZero.setPixelSize(50, 30);
        btVirgula.setPixelSize(50, 30);
        btLimpar.setPixelSize(50, 30);
        btSoma.setPixelSize(50, 30);
        btIgual.setPixelSize(50, 30);

        Grid grid = new Grid(5, 4);
        grid.setWidget(0, 0, btSete);
        grid.setWidget(0, 1, btOito);
        grid.setWidget(0, 2, btNove);
        grid.setWidget(0, 3, btDivisao);
        grid.setWidget(1, 0, btQuatro);
        grid.setWidget(1, 1, btCinco);
        grid.setWidget(1, 2, btSeis);
        grid.setWidget(1, 3, btMultiplicacao);
        grid.setWidget(2, 0, btUm);
        grid.setWidget(2, 1, btDois);
        grid.setWidget(2, 2, btTres);
        grid.setWidget(2, 3, btSubtracao);
        grid.setWidget(3, 0, btZero);
        grid.setWidget(3, 1, btVirgula);
        grid.setWidget(3, 2, btLimpar);
        grid.setWidget(3, 3, btSoma);
        grid.setWidget(4, 0, btIgual);

        VerticalPanel vp = new VerticalPanel();
        vp.add(new HTML("<h2>GWT Web Application Calculadora</h2>"));
        vp.add(gDisplay);
        vp.add(grid);

        form.add(vp);

    }

    public static GWTServiceAsync getService() {
        return GWT.create(GWTService.class);
    }
}
