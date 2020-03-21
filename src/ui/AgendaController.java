package ui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import model.Contact;
import model.Subject;

public class AgendaController {

	private static final String SAVE_PLAYERS="dataSave/users.ps";
	private static final String FIRST_USERS="dataSave/data_users.csv";
	public static final String SER_FILE="dataSave/users.ps";
	
	ArrayList<Contact> friends;
	ArrayList<Subject> subjects;
	
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
    private MenuItem borrarContact;

    @FXML
    private Button elimanarMateria;
    
    @FXML
    private MenuItem AgregarContact;

    @FXML
    private ComboBox<String> comboBoxBusqueda;

    @FXML
    private TextArea informations;
    
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
    
    @FXML
    private Menu sortBy;
    
    @FXML
    private Button buttonReport;
    
    
    ObservableList<String> list = FXCollections.observableArrayList();

    @FXML
    void buscarContacto(ActionEvent event) {
    	String parametroBusqueda = comboBoxBusqueda.getValue();
    	int value;
    	if(parametroBusqueda!=null) {
        	switch(parametroBusqueda) {
    		case "Nombre":
    			sortByNames();
    			value = binarySearchName(friends, 0, friends.size()-1, barraBusqueda.getText());
    			break;
    		case "Apellido":
    			sortByLastnames();
    			value = binarySearchLastName(friends, 0, friends.size()-1, barraBusqueda.getText());
    			break;
    		case "Edad":
    			sortByAge();
    			value = binarySearchAge(friends, 0, friends.size()-1, Integer.parseInt(barraBusqueda.getText()));
    			break;
    		case "Codigo":
    			sortByCode();
    			value = binarySearchCode(friends, 0, friends.size()-1, barraBusqueda.getText());
    			break;
    		case "Semestre":
    			sortBySemester();
    			value = binarySearchSemester(friends, 0, friends.size()-1, barraBusqueda.getText());
    			break;
    		default:
    			value = binarySearchName(friends, 0, friends.size()-1, barraBusqueda.getText());
    			break;
    	}
    	if(value==-1) {
    		barraBusqueda.setText("No existe");
    	}else {
    		labelApellidoContacto.setText(friends.get(value).getLastName());
        	labelCodigo.setText(friends.get(value).getCode());
        	labelEdadContacto.setText(""+friends.get(value).getAge());
        	labelNombreContacto.setText(friends.get(value).getName());
        	labelSemestre.setText(friends.get(value).getSemester());
        	LabelCarrera.setText(friends.get(value).getCarrera());
        	Image n=whichIs(friends.get(value).getName());
        	if(n!=null) {
        		fotoContact.setImage(n);
        	}
        	barraBusqueda.setText("");
    	}
    	}else {
    		
    	}

    	
    }
 
    @FXML
    void delatedData(ActionEvent event) {
    	int nDelated= Integer.parseInt(contactNumber.getText())-1;
    	friends.remove(nDelated);
    	clear();
    	save();
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
    void nextPage(ActionEvent event) throws IOException {
    	int newPage= Integer.parseInt(contactNumber.getText())+1;
        if(newPage<(friends.size()+2)) {
        	contactNumber.setText(newPage+"");
        	clear();
        	showTable();
        }
        elimanarMateria.setVisible(false);
    }

    @FXML
    void previewContact(ActionEvent event) throws IOException {
        int newPage= Integer.parseInt(contactNumber.getText())-1;
        if(newPage>0) {
        	contactNumber.setText(newPage+"");
        	clear();
        	showTable();
        }
        elimanarMateria.setVisible(false);
    }
    
    public void showTable()  {
    	try {
       	int pages=(friends.size());
    	for(int j=0;j<pages;j++){
    		if(j+1==Integer.parseInt(contactNumber.getText())){
    	    	for (int i = (1*j); i <1+(1*j) && i<friends.size(); i++) {
    	    		labelApellidoContacto.setText(friends.get(i).getLastName());
    	        	labelCodigo.setText(friends.get(i).getCode());
    	        	labelEdadContacto.setText(""+friends.get(i).getAge());
    	        	labelNombreContacto.setText(friends.get(i).getName());
    	        	labelSemestre.setText(friends.get(i).getSemester());
    	        	LabelCarrera.setText(friends.get(i).getCarrera()); 
    	        	if(!friends.get(i).getAvatar().isEmpty()) {  	        		
    	        		URL url = new URL(friends.get(i).getAvatar());
        				URLConnection conn = url.openConnection();
        				InputStream in = conn.getInputStream();
        	        	Image n=new Image(in);
        	        	fotoContact.setImage(n);
    	        	}  	        	
    	        	if(friends.get(i).getSubject()!=null) {
        	        	for (int k = 0; k < friends.get(i).getSubject().size(); k++) {
        	        		textAreaMaterias.getItems().add(friends.get(i).getSubject().get(k).getName());	
    					}
    	        	}
    	        	
    		    }
    	    }
		}
    	} catch (IOException e) {

			e.printStackTrace();
		}
    }
    
    public void clear() {
    	labelApellidoContacto.setText("");
    	labelCodigo.setText("");
    	labelEdadContacto.setText("");
    	labelNombreContacto.setText("");
    	labelSemestre.setText("");
    	LabelCarrera.setText("");
    	Image n=new Image(new File("profilePic/pic6.png").toURI().toString());
    	fotoContact.setImage(n);
    	textAreaMaterias.getItems().clear();
    	informations.setText("");
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
    	Contact newContact= new Contact(labelCodigo.getText(),labelSemestre.getText(),labelNombreContacto.getText(),labelApellidoContacto.getText(),Integer.parseInt(labelEdadContacto.getText()),LabelCarrera.getText(),null);
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
    
    public void sortByNames() { 
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
    
    public void sortByLastnames() { 
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
    
    public void sortByAge() { 
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
    
    public void sortByCode() { 
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
    
    public void sortBySemester() { 
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
    			savedUsers= readFirst();
    		}
    		for (int i = 0; i < savedUsers.size(); i++) {
    			for (int j = 0; j < savedUsers.get(i).getSubject().size(); j++) {
    				if(!subjects.contains(savedUsers.get(i).getSubject().get(j))) {
    		    		subjects.add(savedUsers.get(i).getSubject().get(j));
    		    	}
				}
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
	
	public ArrayList<Contact> readFirst() throws IOException {
		ArrayList<Contact> n= new ArrayList<Contact>();
		File file = new File(FIRST_USERS);
		FileReader fileReader = new FileReader(file);
		BufferedReader br = new BufferedReader(fileReader);
		String line = br.readLine();
		line = br.readLine();
		while(line != null){
			String[] parts = line.split(";");
			Contact m= new Contact(parts[4],parts[9],parts[0],parts[1],Integer.parseInt(parts[8]),parts[5],parts[6]);
			n.add(m);
			line = br.readLine();
		}
		fileReader.close();
		br.close();
		return n;
	}
	public Image whichIs(String name) {
		Image n= null;
		switch(name) {
		case "Alejandro":
			n=new Image(new File("profilePic/pic 1.jpg").toURI().toString());
			break;
		case "Jose":
			n=new Image(new File("profilePic/pic5.png").toURI().toString());
			break;
		case "Jairo":
			n=new Image(new File("profilePic/pic4.png").toURI().toString());
			break;
		case "Cristian":
			n=new Image(new File("profilePic/pic3.jpg").toURI().toString());
			break;	
		case "Juanito":
			n=new Image(new File("profilePic/pic2.jpg").toURI().toString());
			break;	
		}
		return n;
	}
    @FXML
    void initialize() {
    	subjects = new ArrayList<Subject>();
    	friends= load();  	
    	buttonGuardar.setVisible(false);
    	labelApellidoContacto.setDisable(true);
    	labelApellidoContacto.setOpacity(1);
    	labelCodigo.setDisable(true);
    	labelCodigo.setOpacity(1);
    	labelEdadContacto.setDisable(true);
    	labelEdadContacto.setOpacity(1);
    	labelNombreContacto.setDisable(true);
    	labelNombreContacto.setOpacity(1);
    	labelSemestre.setDisable(true);
    	labelSemestre.setOpacity(1);
    	LabelCarrera.setDisable(true);
    	LabelCarrera.setOpacity(1);
    	fotoContact.setDisable(true);
    	contactNumber.setText("1");
    	elimanarMateria.setVisible(false);
    	//ComboBox Information: the paramater which is going to be searching
    	comboBoxBusqueda.getItems().add("Nombre");
    	comboBoxBusqueda.getItems().add("Apellido");
    	comboBoxBusqueda.getItems().add("Edad");
    	comboBoxBusqueda.getItems().add("Codigo");
    	comboBoxBusqueda.getItems().add("Semestre");
    	
    	//The information for the listView where the contacts are going to be shown
    	
    	
    	save();
    }
    

    @FXML
    void sortAge(ActionEvent event) {
    	sort("Edad");
    }

    @FXML
    void sortByCode(ActionEvent event) {
    	sort("Codigo");
    }

    @FXML
    void sortLastName(ActionEvent event) {
    	sort("Apellido");
    }

    @FXML
    void sortName(ActionEvent event) {
    	sort("Nombre");
    }

    @FXML
    void sortSemestre(ActionEvent event) {
    	sort("Semestre");
    }
    
    void sort(String kind) {
    	String parametroBusqueda = kind;
    	switch(parametroBusqueda) {
    		case "Nombre":
    			sortByNames();
    			break;
    		case "Apellido":
    			sortByLastnames();
    			break;
    		case "Edad":
    			sortByAge();
    			break;
    		case "Codigo":
    			sortByCode();
    			break;
    		case "Semestre":
    			sortBySemester();
    			break;
    		default:
    			break;
    	}
    }
    
    @FXML
    void agregarMateria(ActionEvent event) {
    	// Create the custom dialog.
    	Dialog<Pair<String, String>> dialog = new Dialog<>();
    	dialog.setTitle("Login Dialog");
    	dialog.setHeaderText("Look, a Custom Login Dialog");

    	// Set the icon (must be included in the project).
    	//dialog.setGraphic(new ImageView(this.getClass().getResource("/profilePic/pic 1.jpg").toString()));

    	// Set the button types.
    	ButtonType loginButtonType = new ButtonType("Save", ButtonData.OK_DONE);
    	dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

    	// Create the username and password labels and fields.
    	GridPane grid = new GridPane();
    	grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(20, 150, 10, 10));

    	TextField nombre = new TextField();
    	nombre.setPromptText("Nombre");
    	TextField nrc = new TextField();
    	nrc.setPromptText("NRC");
    	TextField info = new TextField();
    	info.setPromptText("Info");
    	TextField creditos = new TextField();
    	creditos.setPromptText("Creditos");
    	

    	grid.add(new Label("Nombre:"), 0, 0);
    	grid.add(nombre, 1, 0);
    	grid.add(new Label("NRC:"), 0, 1);
    	grid.add(nrc, 1, 1);
    	grid.add(new Label("Info:"), 2, 0);
    	grid.add(info, 3, 0);
    	grid.add(new Label("Creditos:"), 2, 1);
    	grid.add(creditos, 3, 1);
    	

    	// Enable/Disable login button depending on whether a username was entered.
    	Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
    	loginButton.setDisable(true);

    	// Do some validation (using the Java 8 lambda syntax).
    	nombre.textProperty().addListener((observable, oldValue, newValue) -> {
    	    loginButton.setDisable(newValue.trim().isEmpty());
    	});

    	dialog.getDialogPane().setContent(grid);

    	// Request focus on the username field by default.
    	Platform.runLater(() -> nombre.requestFocus());

    	dialog.showAndWait();
    	
    	String name = nombre.getText();
    	String NRC = nrc.getText();
    	String Info = info.getText();
    	int credits = Integer.parseInt(creditos.getText());
    	
    	guardarMateria(name, NRC, Info, credits);
    	save();

    }
    
    void guardarMateria(String name, String NRC, String Info, int credits) {
    	Subject sub = new Subject(name, credits, NRC, Info);
    	friends.get(Integer.parseInt(contactNumber.getText())-1).setSubject(sub);
    	if(!subjects.contains(sub)) {
    		subjects.add(sub);
    	}
    	save();
    }
    
    long promedioMaterias() {
    	int suma = 0;
    	for(int i=0; i<friends.size(); i++) {
    		suma += friends.get(i).getSubject().size();
    	}
    	System.out.println(suma+" /"+friends.size());
    	long result = suma/friends.size();
    	return result;
    }
    
    long primedioCreditos() {
    	int suma = 0;
    	for(int i=0; i<friends.size(); i++) {
    		for(int j=0; j<friends.get(i).getSubject().size(); j++) {
    			suma += friends.get(i).getSubject().get(j).getCredits();    			
    		}
    	}
    	long result = suma/friends.size();
    	return result;
    }
    
    int indexMateriaTop() {
    	int indexMateria = 0;
    	int mayor = 0;
    	int count = 0;
    	for(int i=0; i<subjects.size(); i++) {
    		for(int j=0; j<friends.size(); j++) {
    			for(int k=0; k<friends.get(j).getSubject().size(); k++) {
    				if(friends.get(j).getSubject().get(k).getName().equals(subjects.get(i).getName())) {
    					count++;
    				}
    			}
    		}
    		if(count>mayor) {
    			mayor = count;
    			indexMateria = i;
    		}
    	}
    	return indexMateria;
    }
    
    int indexMateriaLess() {
    	int indexMateria = 0;
    	int menor = Integer.MAX_VALUE;
    	int count = 0;
    	for(int i=0; i<subjects.size(); i++) {
    		for(int j=0; j<friends.size(); j++) {
    			for(int k=0; k<friends.get(j).getSubject().size(); k++) {
    				if(friends.get(j).getSubject().get(k).getName().equals(subjects.get(i).getName())) {
    					count++;
    				}
    			}
    		}
    		if(count<menor) {
    			menor = count;
    			indexMateria = i;
    		}
    	}
    	return indexMateria;
    }
    
    @FXML
    void mostrarReporte(ActionEvent event) {
    	if(subjects.size()!=0) {
    		Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Reporte de Materias");
        	alert.setHeaderText("Este es el resumen de la informacion referente a las materias");
        	String informacion = "Materia menos Matriculada: "+subjects.get(indexMateriaLess()).getName()+"\n"
        			 			+"Materia mas Matriculada: "+subjects.get(indexMateriaTop()).getName()+"\n"
        			 			+"Promedio Materias: "+promedioMaterias()+"\n"
        						+"Promedio de Creditos: "+primedioCreditos()+"\n";
        	alert.setContentText(informacion);

        	alert.showAndWait();
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Reporte de Materias");
        	String informacion = "No hay materias matriculadas";
        	alert.setContentText(informacion);
        	alert.showAndWait();
    	}
    }

    
    @FXML
    void showSubjectInformation(MouseEvent event) {
    	friends.get(Integer.parseInt(contactNumber.getText())-1).sortByNames();
    	int pos = textAreaMaterias.getEditingIndex();
    	String nameSubject = textAreaMaterias.getItems().get(pos+1);
    	int n=friends.get(Integer.parseInt(contactNumber.getText())-1).binarySearchSubject(friends.get(Integer.parseInt(contactNumber.getText())-1).getSubject(), 0, friends.get(Integer.parseInt(contactNumber.getText())-1).getSubject().size()-1,nameSubject );
    	informations.setText(friends.get(Integer.parseInt(contactNumber.getText())-1).getSubject().get(n).getInfo());
    	elimanarMateria.setVisible(true);
    }
    

    @FXML
    void delated(ActionEvent event) {
    	int pos = textAreaMaterias.getEditingIndex()+1;
    	friends.get(Integer.parseInt(contactNumber.getText())-1).getSubject().remove(pos);
    	save();
    }
    
    
}

