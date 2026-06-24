package com.example.tareaguiajavafx;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class crudController {


    // TextField
    @FXML
    private TextField txtCedula;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtEdad;
    @FXML
    private TextArea txtObservaciones;

    //Combo Box
    @FXML
    private ComboBox cbEstadoCivil;
    @FXML
    private ComboBox cbCategoria;

    @FXML
    private void llenarComboBox(){
        cbCategoria.getItems().addAll(
                "Soltero",
                "Casado",
                "Divorciado",
                "Viudo"
        );

        cbEstadoCivil.getItems().addAll(
                "Infantil",
                "Juvenil",
                "Senior"
        );
    }

    //RadioButton
    @FXML
    private RadioButton rbMatutina;
    @FXML
    private RadioButton rbVespertina;
    @FXML
    private RadioButton rbNocturna;

    private ToggleGroup grupoJornada;

    @FXML
    public void configurarToggleGroup(){
        grupoJornada = new ToggleGroup();
        rbMatutina.setToggleGroup(grupoJornada);
        rbVespertina.setToggleGroup(grupoJornada);
        rbNocturna.setToggleGroup(grupoJornada);
    }

    //TableView
    @FXML
    private TableView<Participante> tblParticipantes;
    //Columnas del table
    @FXML
    private TableColumn<Participante, Integer> colId;
    @FXML
    private TableColumn<Participante,String> colCedula;
    @FXML
    private TableColumn<Participante,String> colNombre;
    @FXML
    private TableColumn<Participante,String> colApellido;
    @FXML
    private TableColumn<Participante,Integer> colEdad;
    @FXML
    private TableColumn<Participante,String> colCorreo;
    @FXML
    private TableColumn<Participante,String> colEstadoCivil;
    @FXML
    private TableColumn<Participante,String> colJornada;
    @FXML
    private TableColumn<Participante,String> colCategoria;

    @FXML
    public void inicializarColumnas(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));

        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));

        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));

        colEstadoCivil.setCellValueFactory(new PropertyValueFactory<>("estadoCivil"));

        colJornada.setCellValueFactory(new PropertyValueFactory<>("jornada"));

        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
    }

    //Metodo initialize
    public void initialize(){

        llenarComboBox();
        configurarToggleGroup();
        inicializarColumnas();

    }

    //METODOS DEL CRUD




}
