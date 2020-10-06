package main.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class FormGrid extends GridPane {
    private final Form form;

    public FormGrid(Form form) {
        this.form = form;

        setAlignment(Pos.CENTER);
        setHgap(10);
        setVgap(10);
//        setGridLinesVisible(true);
        setPadding(new Insets(25));

        // set light background color to the border pane
//        setStyle("-fx-background-color: #ECEFF1;");

        setUpLayout();
    }

    private void setUpLayout() {
        //node, col idx, row idx, col span, row span
        Label idLbl = new Label("ID");
        TextField idTextField = form.getId();
        idTextField.setMaxWidth(50);
        VBox vBox1 = new VBox(idLbl, idTextField);
        vBox1.setAlignment(Pos.TOP_LEFT);
        add(vBox1, 0, 0);

        Label fNameLbl = new Label("First Name");
        TextField fNameTextField = form.getFirstName();
        fNameTextField.setMinWidth(250);
        VBox vBox2 = new VBox(fNameLbl, fNameTextField);
        vBox2.setAlignment(Pos.TOP_LEFT);
        add(vBox2, 0, 1, 1, 1);

        Label mNameLbl = new Label("Middle Name");
        TextField mNameTextField = form.getMiddleName();
        mNameTextField.setMinWidth(150);
        VBox vBox3 = new VBox(mNameLbl, mNameTextField);
        vBox3.setAlignment(Pos.TOP_LEFT);
        add(vBox3, 1, 1);

        Label lNameLbl = new Label("Last Name");
        TextField lNameTextField = form.getLastName();
        lNameTextField.setMinWidth(250);
        VBox vBox4 = new VBox(lNameLbl, lNameTextField);
        vBox4.setAlignment(Pos.TOP_LEFT);
        add(vBox4, 2, 1);

//        HBox hBox = new HBox(vBox2, vBox3, vBox4);
//        hBox.setSpacing(4);
//        TitledPane nameTitledPane = new TitledPane("Full Name", hBox);
//        nameTitledPane.setCollapsible(false);
//        add(hBox, 0, 2, 3, 1);

        Label addressLbl = new Label("Address");
        TextField addressTextField = form.getAddress();
        VBox vBox5 = new VBox(addressLbl, addressTextField);
        vBox5.setAlignment(Pos.TOP_LEFT);
        add(vBox5, 0, 2, 3, 1);

        Label cityLbl = new Label("City");
        TextField cityTextField = form.getCity();
        VBox vBox6 = new VBox(cityLbl, cityTextField);
        vBox6.setAlignment(Pos.TOP_LEFT);
        add(vBox6, 0, 3, 2, 1);

        Label stateLbl = new Label("State");
        TextField stateTextField = form.getState();
        VBox vBox7 = new VBox(stateLbl, stateTextField);
        vBox7.setAlignment(Pos.TOP_LEFT);
        add(vBox7, 2, 3);

        Label phoneLbl = new Label("Telephone");
        TextField phoneTextField = form.getTelephone();
        VBox vBox8 = new VBox(phoneLbl, phoneTextField);
        vBox8.setAlignment(Pos.TOP_LEFT);
        add(vBox8, 0, 4);
    }
}
