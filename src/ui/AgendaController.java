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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.Contact;

public class AgendaController {

	private static final String SAVE_PLAYERS="dataSave/users.ps";
	private static final String FIRST_USERS="dataSave/data_users.csv";
	public static final String SER_FILE="dataSave/users.ps";
	
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
    	m.setCarrera(LabelCarrera.getText());
    	save();
    }
    
    public void guardarContacto() {
    	contactNumber.setText(""+friends.size());
    	Contact newContact= new Contact(labelCodigo.getText(),labelSemestre.getText(),labelNombreContacto.getText(),labelApellidoContacto.getText(),Integer.parseInt(labelEdadContacto.getText()),LabelCarrera.getText());
    	friends.add(friends.size(),newContact);
    	contactNumber.setText(""+(friends.size()+1));
    	if(friends !=null) {
    		System.out.println("guardo");
    	}
    	save();
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
    
    public void sortByNames(ArrayList<Contact> friends) { 
        int n = friends.size(); 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if (friends.get(j).getName().compareTo(friends.get(j+1).getName()) > 0) 
                { 
                    // swap arr[j+1] and arr[i] 
                    Contact temp = friends.get(j); 
                    friends.set(j, friends.get(j+1)); 
                    friends.set(j+1, temp); 
                } 
    }
    
    public void sortByLastnames(ArrayList<Contact> friends) { 
        int n = friends.size(); 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if (friends.get(j).getLastName().compareTo(friends.get(j+1).getLastName()) > 0) 
                { 
                    // swap arr[j+1] and arr[i] 
                    Contact temp = friends.get(j); 
                    friends.set(j, friends.get(j+1)); 
                    friends.set(j+1, temp); 
                } 
    }
    
    public void sortByAge(ArrayList<Contact> friends) { 
        int n = friends.size(); 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if (friends.get(j).getAge() > friends.get(j+1).getAge()) 
                { 
                    // swap arr[j+1] and arr[i] 
                    Contact temp = friends.get(j); 
                    friends.set(j, friends.get(j+1)); 
                    friends.set(j+1, temp); 
                } 
    }
    
    public void sortByCode(ArrayList<Contact> friends) { 
        int n = friends.size(); 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if (friends.get(j).getCode().compareTo(friends.get(j+1).getCode()) > 0) 
                { 
                    // swap arr[j+1] and arr[i] 
                    Contact temp = friends.get(j); 
                    friends.set(j, friends.get(j+1)); 
                    friends.set(j+1, temp); 
                } 
    }
    
    public void sortBySemester(ArrayList<Contact> friends) { 
        int n = friends.size(); 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if (friends.get(j).getSemester().compareTo(friends.get(j+1).getSemester()) > 0) 
                { 
                    // swap arr[j+1] and arr[i] 
                    Contact temp = friends.get(j); 
                    friends.set(j, friends.get(j+1)); 
                    friends.set(j+1, temp); 
                } 
    }
    
	@SuppressWarnings("unchecked")
	public ArrayList<Contact> load(){
    	ArrayList<Contact> savedUsers= new ArrayList<Contact>();
    	try {    		
    		File archivo= new File(SAVE_PLAYERS);
    		if(archivo.exists()) {
			  ObjectInputStream ois= new ObjectInputStream(new FileInputStream(archivo));
			  savedUsers=(ArrayList<Contact>) ois.readObject();
			  ois.close();
    		}else {
    			savedUsers= new ArrayList<Contact>();
    		}        		         	
		} catch (IOException e ) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
    	return savedUsers;
    }
    
	public void save() {
		  ObjectOutputStream oos;
			try {
				oos = new ObjectOutputStream(new FileOutputStream(SER_FILE));
				oos.writeObject(friends);
				oos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void readFirst() throws IOException {
		File file = new File(FIRST_USERS);
		FileReader fileReader = new FileReader(file);
		BufferedReader br = new BufferedReader(fileReader);
		String line = br.readLine();
		line = br.readLine();
		while(line != null){
			String[] parts = line.split(",");
			Contact m= new Contact(parts[0],parts[4],parts[1],parts[2],Integer.parseInt(parts[3]),parts[5]);
			friends.add(m);
			line = br.readLine();
		}
		fileReader.close();
		br.close();
		
	}
	
    @FXML
    void initialize() {
    	friends= load();
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

