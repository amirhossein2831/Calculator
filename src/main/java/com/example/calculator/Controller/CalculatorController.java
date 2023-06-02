package com.example.calculator.Controller;

import com.example.calculator.Error.Error;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculatorController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button delete;

    @FXML
    private Button divide;

    @FXML
    private Button dot;

    @FXML
    private Button eight;

    @FXML
    private Button equal;

    @FXML
    private Button five;

    @FXML
    private Button four;

    @FXML
    private Button minus;

    @FXML
    private Button multi;

    @FXML
    private Button nine;

    @FXML
    private Button one;

    @FXML
    private Button reset;

    @FXML
    private Button seven;

    @FXML
    private Button six;

    @FXML
    private Button sum;

    @FXML
    private TextField text;

    @FXML
    private Button three;

    @FXML
    private Button two;

    @FXML
    private Button zero;
    private String value;
    private String  operand;
    boolean isShiftPressed;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        value = "";
        operand = "";
        setActionNumber(zero,one,two,three,four,five,six,seven,eight,nine,dot);
        setActionOperand(sum, minus, multi, divide);
        equal.setOnAction(event -> setActionEqual(0));
        reset.setOnAction(event -> setReset());
        delete.setOnAction(event -> setDelete());
    }
    private void setActionNumber(Button... buttons) {
        for (Button button : buttons) {
            button.setOnAction(event -> setNumber(button));
        }
    }
    private void setNumber(Button button) {
        value += button.getText();
        text.setText(value);
    }
    private void setActionOperand(Button... buttons) {
        for (Button button : buttons) {
            button.setOnAction(event -> setOperand(button));
        }
    }
    private void setOperand(Button button) {
        if (value.equals("")) {
            Error.ErrorNoNumber();
            return;
        }
        if (operand.length() != 0) {
            setActionEqual(1);
        }
        if (value.substring(value.length() - 1).equals("-") || value.substring(value.length() - 1).equals("+") ||
                value.substring(value.length() - 1).equals("*") || value.substring(value.length() - 1).equals("/")) {
            Error.ErrorNoOperand();
            return;
        }
        operand = button.getText();
        value += button.getText();
        text.setText(value);
    }
    @FXML
    public  void setKeyPress() {
        Scene scene = anchorPane.getScene();
        scene.setOnKeyPressed(keyEvent -> {
            KeyCode keyCode = keyEvent.getCode();
            isShiftPressed = keyEvent.isShiftDown();
            if (keyCode == KeyCode.DIGIT0) {
                zero.fire();
            } else if (keyCode == KeyCode.DIGIT1) {
                one.fire();
            } else if (keyCode == KeyCode.DIGIT2) {
                two.fire();
            } else if (keyCode == KeyCode.DIGIT3) {
                three.fire();
            } else if (keyCode == KeyCode.DIGIT4) {
                four.fire();
            } else if (keyCode == KeyCode.DIGIT5) {
                five.fire();
            } else if (keyCode == KeyCode.DIGIT6) {
                six.fire();
            } else if (keyCode == KeyCode.DIGIT7) {
                seven.fire();
            } else if (keyCode == KeyCode.DIGIT8 && isShiftPressed) {
                multi.fire();
            } else if (keyCode == KeyCode.DIGIT8) {
                eight.fire();
            } else if (keyCode == KeyCode.DIGIT9) {
                nine.fire();
            } else if (keyCode == KeyCode.PERIOD) {
                dot.fire();
            } else if (keyCode == KeyCode.BACK_SPACE) {
                delete.fire();
            } else if (keyCode == KeyCode.EQUALS && isShiftPressed) {
                sum.fire();
            } else if (keyCode == KeyCode.ASTERISK) {
                multi.fire();
            } else if (keyCode == KeyCode.SLASH) {
                divide.fire();
            } else if (keyCode == KeyCode.MINUS) {
                minus.fire();
            } else if (keyCode == KeyCode.EQUALS) {
                equal.fire();
            }
        });
    }
    private void setActionEqual(int x) {
        if (operand.length() == 0) {
            Error.ErrorNoOperand();
            return;
        } else if (value.substring(value.length() - 1).equals(operand)) {
            Error.ErrorNoOperand();
            return;
        }
        String operand = String.format("\\%s", this.operand);
        String[] parts = value.split(operand);
        calculate( Double.parseDouble(parts[0]),Double.parseDouble(parts[1]),operand,x);
    }
    private void setReset() {
        value = "";
        text.setText(value);
    }
    private void setDelete() {
        String str = value;
        if (str == null || str.isEmpty()) {
            return;
        }
        String toDelete = str.substring(str.length() - 1);
        if (operand.equals(toDelete)) {
            operand = "";
        }
        value = str.substring(0, str.length() - 1);
        text.setText(value);
    }
    private void calculate(double firstPart, double secondPart, String operand,int x) {
        double result;
        switch (operand) {
            case "\\+":
                result = firstPart + secondPart;
                break;
            case "\\-":
                result = firstPart - secondPart;
                break;
            case "\\*":
                result = firstPart * secondPart;
                break;
            case "\\/":
                result = firstPart / secondPart;
                break;
            default:
                result = 0;
        }
        text.setText(String.valueOf(result));
        this.operand = "";
        if (x == 0) {
            value = "";
        } else if (x == 1) {
            value = String.valueOf(result);
        }
    }
}
