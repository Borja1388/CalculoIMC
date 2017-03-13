/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculoimc;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Borja
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label altura;
    @FXML
    private Label peso;
    private Button calculo;
    @FXML
    private Label resultado;
    @FXML
    private RadioButton obesidad;
    @FXML
    private RadioButton sobrepeso;
    @FXML
    private RadioButton normal;
    @FXML
    private RadioButton extra;
    private String altura1;
    private String peso1;
    private String resultado1;
    private double peso2;
    private double altura2;
    private double resultadoFinal;
    ObservableList<String> pesos = FXCollections.observableArrayList("Obesidad","Sobrepeso","Normal","Extra-delgadez");
    @FXML
    private ToggleGroup tipoPeso;
    @FXML
    private AnchorPane panel;
    @FXML
    private Slider slider;
    @FXML
    private Slider scroll;
    @FXML
    private ListView<?> list1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        panel.setStyle("-fx-background-color: orange;");
        peso.setStyle("-fx-border-color: black");
        altura.setStyle("-fx-border-color: black");
        resultado.setStyle("-fx-border-color: black");
        
        
    }
    public void calcularIMC(){
        Toggle seleccionado = tipoPeso.getSelectedToggle();
        DecimalFormat df = new DecimalFormat("#.##");
        altura1 = altura.getText();
        peso1 = peso.getText();
        peso2 = Double.parseDouble(peso1);
        altura2 = Double.parseDouble(altura1);
        altura2 = altura2 / 100;
        if (peso2 < 20 || peso2 > 180 && altura2 < 40 || altura2 > 220) {
            resultado.setText("Error al introducir los datos");
            peso.setStyle("-fx-text-inner-color: red;");
            altura.setStyle("-fx-text-inner-color: red;");

        } else {
            resultadoFinal = peso2 / (altura2 * altura2);
            resultado1 = String.valueOf(df.format(resultadoFinal));
            resultado.setText(resultado1);

        }

        if (resultadoFinal > 30) {
            obesidad.setSelected(true);
            resultado.setStyle("-fx-background-color: yellow;");
            

        }
        if (resultadoFinal > 25 && resultadoFinal < 29.9) {
            sobrepeso.setSelected(true);
            resultado.setStyle("-fx-background-color: white;");

        }
        if (resultadoFinal < 18.5) {
            extra.setSelected(true);
            resultado.setStyle("-fx-background-color: yellow;");
            

        }
        if (resultadoFinal > 18.5 && resultadoFinal < 24.9) {
            normal.setSelected(true);
            resultado.setStyle("-fx-background-color: white;");

        }
    }

    @FXML
    private void seleccionarAltura(MouseEvent event) {
        double valor=(double) slider.getValue();
        altura.setText(valor + "");
        calcularIMC();
    }

    @FXML
    private void seleccionarPeso(MouseEvent event) {
        double valor2=(double) scroll.getValue();
        peso.setText(valor2 + "");
        calcularIMC();
    }

}
