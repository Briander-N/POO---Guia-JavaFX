package com.example.tareaguiajavafx;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class crudController {


    private int idSeleccionado = -1;

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
    private TextField txtCorreo;
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

        leer();


        tblParticipantes.setOnMouseClicked(event -> seleccionarParticipante());
    }

    //METODOS DEL CRUD

    @FXML
    private void guardar() {
        String cedula = txtCedula.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String edadTexto = txtEdad.getText();
        String correo = txtCorreo.getText();

        String estadoCivil = cbEstadoCivil.getValue().toString();
        String jornada = "";
        if(rbMatutina.isSelected()){
            jornada="Matutina";
        }
        if(rbVespertina.isSelected()) {
            jornada = "Vespertina";
        }
        if(rbNocturna.isSelected()) {
            jornada = "Nocturna";
        }
        String categoria = cbCategoria.getValue().toString();

        //Validar campos vacios
        if(cedula.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || edadTexto.isEmpty() || correo.isEmpty()) {

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("Debe completar todos los campos");
            alerta.showAndWait();
            return;
        }
        //Validar Edad
        int edad;

        try {
            edad = Integer.parseInt(edadTexto);
        } catch (Exception e){

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("La edad debe ser numérica");
            alerta.showAndWait();
            return;
        }
        if(edad <= 5){

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("La edad debe ser mayor a 5");
            alerta.showAndWait();
            return;
        }
        //Validar Correo
        if(!correo.contains("@")){

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("Correo inválido");
            alerta.showAndWait();
            return;
        }
        //Validar Cedula
        if(!cedula.matches("\\d+")){

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("La cédula solo debe contener números");
            alerta.showAndWait();
            return;
        }
        //Crear Participante factory
        Participante participante = ParticipanteFactory.crearParticipante(
                0,
                cedula,
                nombre,
                apellido,
                edad,
                correo,
                estadoCivil,
                jornada,
                categoria
        );
        //INSERT

        try{
            Connection con = Conexion.getConexion();
            String sql = """
                INSERT INTO participante(
                    cedula,
                    nombre,
                    apellido,
                    edad,
                    correo,
                    estado_civil,
                    jornada,
                    categoria,
                    observaciones
                    )
                   VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, participante.getCedula());
            ps.setString(2, participante.getNombre());
            ps.setString(3, participante.getApellido());
            ps.setInt(4, participante.getEdad());
            ps.setString(5, participante.getCorreo());
            ps.setString(6, participante.getEstadoCivil());
            ps.setString(7, participante.getJornada());
            ps.setString(8, participante.getCategoria());
            ps.setString(9, txtObservaciones.getText());

            ps.executeUpdate();
            leer();

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Registro");
            alerta.setHeaderText(null);
            alerta.setContentText("Participante guardado correctamente");
            alerta.showAndWait();


        } catch (Exception e){
            e.printStackTrace();
        }
    }


    private void leer(){
        try{
            Connection con = Conexion.getConexion();

            String sql = "SELECT * from participante";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            tblParticipantes.getItems().clear();

            while (rs.next()){
                int id = rs.getInt("id");
                String cedula = rs.getString("cedula");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int edad = rs.getInt("edad");
                String correo = rs.getString("correo");
                String estadoCivil =
                        rs.getString("estado_civil");
                String jornada =
                        rs.getString("jornada");
                String categoria =
                        rs.getString("categoria");

                Participante participante =
                        ParticipanteFactory.crearParticipante(
                                id,
                                cedula,
                                nombre,
                                apellido,
                                edad,
                                correo,
                                estadoCivil,
                                jornada,
                                categoria
                        );

                tblParticipantes.getItems().add(participante);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Metodo para seleccionar participante
    private void seleccionarParticipante() {

        Participante participante = tblParticipantes.getSelectionModel().getSelectedItem();
        if(participante != null){
            idSeleccionado = participante.getId();
            txtCedula.setText(
                    participante.getCedula());
            txtNombre.setText(
                    participante.getNombre());
            txtApellido.setText(
                    participante.getApellido());
            txtEdad.setText(
                    String.valueOf(
                            participante.getEdad()));
            txtCorreo.setText(
                    participante.getCorreo());
            cbEstadoCivil.setValue(
                    participante.getEstadoCivil());
            cbCategoria.setValue(
                    participante.getCategoria());
        }
    }

    @FXML
    private void actualizar(){
        String cedula = txtCedula.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String edadTexto = txtEdad.getText();
        String correo = txtCorreo.getText();

        String estadoCivil = cbEstadoCivil.getValue().toString();
        String jornada = "";
        if(rbMatutina.isSelected()){
            jornada="Matutina";
        }
        if(rbVespertina.isSelected()) {
            jornada = "Vespertina";
        }
        if(rbNocturna.isSelected()) {
            jornada = "Nocturna";
        }
        String categoria = cbCategoria.getValue().toString();
        int edad;
        try{
            Connection con = Conexion.getConexion();
            String sql = """
                    UPDATE participante
                            SET cedula=?,
                                nombre=?,
                                apellido=?,
                                edad=?,
                                correo=?,
                                estado_civil=?,
                                jornada=?,
                                categoria=?
                            WHERE id=?
                    """;

            edad = Integer.parseInt(edadTexto);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cedula);
            ps.setString(2, nombre);
            ps.setString(3, apellido);
            ps.setInt(4, edad);
            ps.setString(5, correo);
            ps.setString(6, estadoCivil);
            ps.setString(7, jornada);
            ps.setString(8, categoria);
            ps.setInt(9, idSeleccionado);

            ps.executeUpdate();
            leer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void eliminar(){

    }



}
