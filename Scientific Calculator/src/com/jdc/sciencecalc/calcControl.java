package com.jdc.sciencecalc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.util.ResourceBundle;

public class calcControl implements Initializable{

    @FXML
    private Label result;

    @FXML
    private Label temp;

    @FXML
    private Label degRad;

    @FXML
    private GridPane grid;

    private boolean lock = false;
    private boolean degree = false;
    Double [] strarr = new Double[1];

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        temp.setText("");
        makeDegree();
        clearMemory();
        grid.getChildren().stream()
                .filter(node -> node instanceof Button).map(node -> (Button)node)
                .forEach(btn -> {
                    btn.setOnAction(this::pressButton);
                });
    }

    public void pressButton(ActionEvent event) {

        Button btn = (Button) event.getSource();
        String text = btn.getText();
        switch (text) {
            case "AC":
                clear();
                break;
            case "+/-":
                negative();
                break;
            case "%":
                doPercent();
                break;
            case ".":
                makeDecimal();
                break;
            case "=":
                calculate();
                lock=true;
                break;
            case "+":
            case "-":
            case "×":
            case "÷":
                pressOperator(text);
                break;
            case "x²":
                makeDouble();
                break;
            case "x³":
                makeTriple();
                break;
            case "xⁿ":
                makeNple();
                break;
            case "10ⁿ":
                maketenpow();
                break;
            case "x!":
                doFactorial();
                break;
            case "√x":
                doSquareoot();
                break;
            case "∛x":
                doCuberoot();
                break;
            case "ⁿ√x":
                doNthroot();
                break;
            case "Rand":
                printRandom();
                break;
            case "π":
                printPI();
                break;
            case "1/x":
                doDivide();
                break;
            case "ln":
                makeLog();
                break;
            case "log₁₀":
                makeLog10();
                break;
            case "EE":
                makeEE();
                break;
            case "eⁿ":
                getEn();
                break;
            case "sin":
            case "cos":
            case "tan":
            case "sinh":
            case "cosh":
            case "tanh":
                pressTrigo(text);
                break;
            case "m+":
                addMemory();
                break;
            case "m-":
                subtractMemory();
                break;
            case "mr":
                memoryResult();
                break;
            case "mc":
                clearMemory();
                clear();
                break;
            case "Rad":
                makeDegree();
                break;
            case "e":
                getE();
                break;
            default:
                pressNumber(text);
                break;
        }
    }

    private void getE() {
        Double d = Math.exp(1);
        result.setText(d.toString());
    }

    // Memories

    private void clearMemory() {
        strarr[0] = 0d;
        lock = true;
    }

    private void addMemory() {
        String text = result.getText();
        Double d = Double.valueOf(text);
        strarr[0] += d;
        lock = true;
    }

    private void subtractMemory() {
        String text = result.getText();
        Double d = Double.valueOf(text);
        strarr[0] -= d;
        lock = true;
    }

    private void memoryResult() {
        result.setText(strarr[0].toString().toLowerCase());
        lock = true;
    }

    private void pressTrigo(String text) {
        if(result.getText().equals("")) {
            result.setText("Error");
        }
        else {
            String text1 = result.getText();
            Double d1 = Double.valueOf(text1);
            Double d2 = 0d;
            switch(text) {
                case "sin":
                    if(!degree) {
                        d2 = Math.sin(Math.toRadians(d1));
                    }
                    else d2 = Math.sin(d1);
                    break;
                case "cos":
                    if(!degree) {
                        d2 = Math.cos(Math.toRadians(d1));
                    }
                    else d2 = Math.cos(d1);
                    break;
                case "tan":
                    if(!degree) {
                        d2 = Math.tan(Math.toRadians(d1));
                    }
                    else d2 = Math.tan(d1);
                    break;
                case "sinh":
                    if(!degree) {
                        d2 = Math.sinh(Math.toRadians(d1));
                    }
                    else d2 = Math.sinh(d1);
                    break;
                case "cosh":
                    if(!degree) {
                        d2 = Math.cosh(Math.toRadians(d1));
                    }
                    else d2 = Math.cosh(d1);
                    break;
                case "tanh":
                    if(!degree) {
                        d2 = Math.tan(Math.toRadians(d1));
                    }
                    else d2 = Math.tanh(d1);
                    break;
            }
            result.setText(d2.toString().toLowerCase());
        }
    }

    private void makeDegree() {
        degree = !degree;
        if(degree) {
            degRad.setText("Deg");
        }
        else {
            degRad.setText("Rad");
        }
    }

    private void getEn() {
        String text = result.getText();
        Double d = Double.valueOf(text);
        Double d1 = Math.exp(d);
        result.setText(d1.toString().toLowerCase());
    }

    private void printPI() {
        Double d = Math.PI;
        result.setText(d.toString().toLowerCase());
    }

    private void makeEE() {
        if(temp.getText().equals("")) {
            String text = result.getText();
            temp.setText(result.getText()+" EE");
            result.setText("");
        }
    }

    private void makeLog10() {
        String text = result.getText();
        Double d = Double.valueOf(text);
        Double d1 = Math.log10(d);
        result.setText(d1.toString().toLowerCase());
    }

    private void makeLog() {
        String text = result.getText();
        Double d = Double.valueOf(text);
        Double d1 = Math.log(d);
        result.setText(d1.toString().toLowerCase());
    }

    private void doDivide() {
        String text = result.getText();
        Double d = Double.valueOf(text);
        Double d1 = 1/d;
        result.setText(d1.toString().toLowerCase());
    }

    private void printRandom() {
        Double d = Math.random();
        result.setText(d.toString());
    }

    private void maketenpow() {
        String text = result.getText();
        Double d = Double.valueOf(text);
        Double dd = Math.pow(10.0,d);
        result.setText(dd.toString().toLowerCase());
    }

    private void makeNple() {

        if(temp.getText().equals("")) {
            String text = result.getText();
            temp.setText(result.getText()+" ^");
            result.setText("");
        }
    }

    private void doNthroot() {
        if(temp.getText().equals("")) {
            String text = result.getText();
            temp.setText(result.getText()+" √");
            result.setText("");
        }
    }

    private void doCuberoot() {
        String text = result.getText();
        Double d = Double.valueOf(text);
        Double res = Math.cbrt(d);
        result.setText(res.toString().toLowerCase());
    }

    private void doSquareoot() {

        String text = result.getText();
        Double d = Double.valueOf(text);
        Double res = Math.pow(d,0.5);
        result.setText(res.toString().toLowerCase());
    }

    private void doFactorial() {
        String text = result.getText();
        Double d = Double.valueOf(text);
        Double res = 1.0;
        for(Double i=1.0;i<=d;i++) {
            res = res * i;
        }
        result.setText(res.toString().toLowerCase());
    }

    private void makeDouble() {
        String text = result.getText();
        Double d = Double.valueOf(text);
        Double d1 = Math.pow(d,2);

        result.setText(d1.toString().toLowerCase());
    }

    private void makeTriple() {
        String text = result.getText();
        Double d = Double.valueOf(text);
        Double d1 = Math.pow(d,3);

        result.setText(d1.toString().toLowerCase());
    }

    private void doPercent() {
        calculate();

        String text = result.getText();
        Double d = Double.valueOf(text);
        Double dr = d % 100;

        result.setText(dr.toString());
    }

    private void calculate() {
        String text1 = temp.getText();
        String text2 = result.getText();
        if(!text1.isEmpty()) {
            clear();
            String array[] = text1.split(" ");
            String resultText = calculate(array[0],text2,array[1]);
            result.setText(resultText);
        }
    }

    private void pressOperator(String ope) {
        String text1 = temp.getText();
        String text2 = result.getText();
        clear();
        if(text1.isEmpty()) {
            temp.setText(String.format("%s %s", text2, ope));
        }
        else {

            String array [] = text1.split(" ");
            String result  = calculate(array[0], text2, array[1]);
            temp.setText(String.format("%s %s", result, ope));
        }
    }

    private String calculate(String str1, String str2, String ope) {

        Double d1 = Double.valueOf(str1);
        Double d2 = Double.valueOf(str2);
        Double d3 = 0d;
        switch(ope) {
            case "+":
                d3 = d1 + d2;
                break;
            case "-":
                d3 = d1 - d2;
                break;
            case "×":
                d3 = d1 * d2;
                break;
            case "÷":
                if(d2!=0)
                {
                    d3 = d1 / d2;
                } else {
                    return "Error";
                }
                break;
            case "√":
                d3 = Math.pow(d1,Math.abs(1/d2));
                break;
            case "^":
                d3 = Math.pow(d1,d2);
                break;
            case "EE":
                Double zeros = (Math.pow(10,d2));
                String z = zeros.toString();
                String Z = new StringBuilder(z).substring(1,z.length()-2);
                String d1s = d1.toString();
                String d1S = new StringBuilder(d1s).substring(0,d1s.length()-2);
                return d1S.concat(Z).concat(".0");
        }

        return d3.toString().toLowerCase();

    }

    private void pressNumber(String value) {
        String text = result.getText();
        if("0".equals(text)) {
            result.setText(value);
        }
        else if(lock) {
            result.setText(value);
            lock=false;
        }
        else {
            result.setText(text.concat(value));
        }
    }

    private void negative() {
        String text = result.getText();
        if(text.startsWith("-")) {
            result.setText(text.replaceAll("-", ""));
        } else {
            result.setText("-".concat(text));
        }
    }

    private void makeDecimal() {
        String test = result.getText();
        if(!test.contains(".")) {
            result.setText(test.concat(".").toLowerCase());
        }
    }

    public void clear() {
        result.setText("0");
        temp.setText("");
    }
}
