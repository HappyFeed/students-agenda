package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private TextField barraBusqueda;
    
    ObservableList<String> list = FXCollections.observableArrayList("Modelado","Discretas");

    @FXML
    void buscarContacto(ActionEvent event) {
    	String parametroBusqueda = comboBoxBusqueda.getValue();
    	int value;
    	switch(parametroBusqueda) {
    		case "Nombre":
    			value = binarySearchName(friends, 0, friends.size()-1, barraBusqueda.getText());
    			break;
    		case "Apellido":
    			value = binarySearchLastName(friends, 0, friends.size()-1, barraBusqueda.getText());
    			break;
    		case "Edad":
    			value = binarySearchAge(friends, 0, friends.size()-1, Integer.parseInt(barraBusqueda.getText()));
    			break;
    		case "Codigo":
    			value = binarySearchCode(friends, 0, friends.size()-1, barraBusqueda.getText());
    			break;
    		case "Semestre":
    			value = binarySearchSemester(friends, 0, friends.size()-1, barraBusqueda.getText());
    			break;
    		default:
    			value = binarySearchName(friends, 0, friends.size()-1, barraBusqueda.getText());
    			break;
    	}
    	if(value==-1) {
    		System.out.println("No Existe");
    	}else {
    		friends.get(value).getName();
    	}
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
    
    public int binarySearchName(ArrayList<Contact> friends, int l, int r, String x){ 
        if (r >= l) { 
            int mid = l + (r - l) / 2; 

            if (friends.get(mid).getName().equalsIgnoreCase(x)) {
                return mid; 
            }
            if (friends.get(mid).getName().compareTo(x) > 0) { 
                return binarySearchName(friends, l, mid - 1, x); 
            }
            return binarySearchName(friends, mid + 1, r, x); 
        } 
        return -1; 
    }
    
    public int binarySearchLastName(ArrayList<Contact> friends, int l, int r, String x){ 
        if (r >= l) { 
            int mid = l + (r - l) / 2; 

            if (friends.get(mid).getLastName().equalsIgnoreCase(x)) {
                return mid; 
            }
            if (friends.get(mid).getLastName().compareTo(x) > 0) { 
                return binarySearchLastName(friends, l, mid - 1, x); 
            }
            return binarySearchLastName(friends, mid + 1, r, x); 
        }  
        return -1; 
    }
    
    public int binarySearchAge(ArrayList<Contact> friends, int l, int r, int x){ 
        if (r >= l) { 
            int mid = l + (r - l) / 2; 

            if (friends.get(mid).getAge() == x) {
                return mid; 
            }
            if (friends.get(mid).getAge() > x) { 
                return binarySearchAge(friends, l, mid - 1, x); 
            }
            return binarySearchAge(friends, mid + 1, r, x); 
        } 
        return -1; 
    }
    
    public int binarySearchCode(ArrayList<Contact> friends, int l, int r, String x){ 
        if (r >= l) { 
            int mid = l + (r - l) / 2; 

            if (friends.get(mid).getCode().equalsIgnoreCase(x)) {
                return mid; 
            }
            if (friends.get(mid).getCode().compareTo(x) > 0) { 
                return binarySearchCode(friends, l, mid - 1, x); 
            }
            return binarySearchCode(friends, mid + 1, r, x); 
        }  
        return -1; 
    }
    
    public int binarySearchSemester(ArrayList<Contact> friends, int l, int r, String x){ 
        if (r >= l) { 
            int mid = l + (r - l) / 2; 

            if (friends.get(mid).getSemester().equalsIgnoreCase(x)) {
                return mid; 
            }
            if (friends.get(mid).getSemester().compareTo(x) > 0) { 
                return binarySearchSemester(friends, l, mid - 1, x); 
            }
            return binarySearchSemester(friends, mid + 1, r, x); 
        }  
        return -1; 
    }
    
    
    
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
    	
    	//ComboBox Information: the paramater which is going to be searching
    	comboBoxBusqueda.getItems().add("Nombre");
    	comboBoxBusqueda.getItems().add("Apellido");
    	comboBoxBusqueda.getItems().add("Edad");
    	comboBoxBusqueda.getItems().add("Codigo");
    	comboBoxBusqueda.getItems().add("Semestre");
    	
    	//The information for the listView where the contacts are going to be shown
    	textAreaMaterias.setItems(list);
    	
    }
}

