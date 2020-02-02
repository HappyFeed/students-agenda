package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

import model.Contact;

public class AgendaController {

	ArrayList<Contact> friends;
	
    @FXML
    private ImageView fotoContact;

    @FXML
    private TextField LabelCarrera;

    @FXML
    private TextField labelSemestre;

    @FXML
    private TextField labelCodigo;

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
    private TextField labelNombreContacto;

    @FXML
    private TextField labelApellidoContacto;

    @FXML
    private TextField labelEdadContacto;

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
    	contactNumber.setText("1");
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
    	buttonGuardar.setText("guardar");
    	buttonGuardar.setVisible(true);
    	labelApellidoContacto.setDisable(false);
    	labelCodigo.setDisable(false);
    	labelEdadContacto.setDisable(false);
    	labelNombreContacto.setDisable(false);
    	labelSemestre.setDisable(false);
    	LabelCarrera.setDisable(false);
    	fotoContact.setDisable(false);
    }

    @FXML
    void nextPage(ActionEvent event) {

    }

    @FXML
    void previewContact(ActionEvent event) {

    }
    
    @FXML
    void guardarData(ActionEvent event) {
    	if(buttonGuardar.getText().equals("actualizar")) {
    		actualizarInfo();
    	}else {
    		guardarContacto();
    	}
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
    void updateData(ActionEvent event) {
    	buttonGuardar.setText("actualizar");
    	buttonGuardar.setVisible(true);
    	labelApellidoContacto.setDisable(false);
    	labelCodigo.setDisable(false);
    	labelEdadContacto.setDisable(false);
    	labelNombreContacto.setDisable(false);
    	labelSemestre.setDisable(false);
    	LabelCarrera.setDisable(false);
    	fotoContact.setDisable(false);
    }

    public void actualizarInfo() {
    	int nContact=Integer.parseInt(contactNumber.getText());
    	Contact m= friends.get(nContact-1);
    	m.setAge(Integer.parseInt(labelEdadContacto.getText()));
    	m.setCode(labelCodigo.getText());
    	m.setName(labelNombreContacto.getText());
    	m.setLastName(labelApellidoContacto.getText());
    	m.setSemester(labelSemestre.getText());
    }
    
    public void guardarContacto() {
    	contactNumber.setText(""+friends.size());
    	Contact newContact= new Contact(labelCodigo.getText(),labelSemestre.getText(),labelNombreContacto.getText(),labelNombreContacto.getText(),Integer.parseInt(labelEdadContacto.getText()));
    	friends.add(friends.size(),newContact);
    	contactNumber.setText(""+(friends.size()+1));
    	if(friends !=null) {
    		System.out.println("guardo");
    	}
    }
    
}

