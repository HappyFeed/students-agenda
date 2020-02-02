package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

import model.Contact;

public class AgendaController {

	ArrayList<Contact> friends;
	
    @FXML
    private ImageView fotoContact;

    @FXML
    private Label labelNombreContacto;

    @FXML
    private Label labelApellidoContacto;

    @FXML
    private Label labelEdadContacto;

    @FXML
    private MenuItem editarContact;

    @FXML
    private Menu salirOption;
    
    @FXML
    private MenuItem borrarContact;

    @FXML
    private MenuItem AgregarContact;

    @FXML
    private ComboBox<String> comboBoxBusqueda;

    @FXML
    private Button buttonBuscar;

    @FXML
    private Label LabelCarrera;

    @FXML
    private Label labelSemestre;

    @FXML
    private Label labelCodigo;

    @FXML
    private ListView<String> textAreaMaterias;

    @FXML
    private Button buttonGuardar;
    
    @FXML
    private Button next;

    @FXML
    private Button preview;

    @FXML
    private Label contactNumber;

    @FXML
    void initialize() {
    	friends= new ArrayList<Contact>();
    	buttonGuardar.setVisible(false);
    	labelApellidoContacto.setDisable(true);
    	labelCodigo.setDisable(true);
    	labelEdadContacto.setDisable(true);
    	labelNombreContacto.setDisable(true);
    	labelSemestre.setDisable(true);
    	LabelCarrera.setDisable(true);
    	fotoContact.setDisable(true);
    }
    
    
    @FXML
    void buscarContacto(ActionEvent event) {

    }

    @FXML
    void cerrarAgenda(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void delatedData(ActionEvent event) {

    }

    @FXML
    void fillData(ActionEvent event) {
    	
    }

    @FXML
    void nextPage(ActionEvent event) {

    }

    @FXML
    void previewContact(ActionEvent event) {

    }
    
    @FXML
    void guardarData(ActionEvent event) {
    	int nContact=Integer.parseInt(contactNumber.getText());
    	Contact m= friends.get(nContact-1);
    	m.setAge(Integer.parseInt(labelEdadContacto.getText()));
    	m.setCode(labelCodigo.getText());
    	m.setName(labelNombreContacto.getText());
    	m.setLastName(labelApellidoContacto.getText());
    	m.setSemester(labelSemestre.getText());
    	
    }
    
    @FXML
    void updateData(ActionEvent event) {
    	buttonGuardar.setVisible(true);
    	labelApellidoContacto.setDisable(true);
    	labelCodigo.setDisable(true);
    	labelEdadContacto.setDisable(true);
    	labelNombreContacto.setDisable(true);
    	labelSemestre.setDisable(true);
    	LabelCarrera.setDisable(true);
    	fotoContact.setDisable(true);
    }

}

