package main.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FormGrid
 * This class is responsible for generating the layout of the form fields
 * and their labels
 *
 * @author Roshan Kharel
 */
public class FormGrid extends GridPane {
    private final Form form;

    /**
     * FormGrid constructor
     *
     * @param form Form object that has all the fields as per Staff attributes
     */
    public FormGrid(Form form) {
        this.form = form;

        // centrally align the grid
        setAlignment(Pos.CENTER);
        // set horizontal spacing/gap between its children nodes to 10px
        setHgap(10);
        // set vertical spacing/gap between its children nodes to 10px
        setVgap(10);

        // set a padding of 25px from all side
        setPadding(new Insets(25));

        // set up the layout
        setUpLayout();
    }

    /**
     * An internal method for creation, insertion and alignment of all the visual elements (nodes)
     * in the GridPan
     */
    private void setUpLayout() {
        //node, col idx, row idx, col span, row span

        // form field and its respective Label initialization, visual alignment, and
        // grouping of all the form fields
        Label idLbl = new Label("ID");
        TextField idTextField = form.getIdTextField();
        idTextField.setMaxWidth(50);
        VBox vBox1 = new VBox(idLbl, idTextField);
        vBox1.setAlignment(Pos.TOP_LEFT);
        add(vBox1, 0, 0);

        Label fNameLbl = new Label("First Name");
        TextField fNameTextField = form.getFirstNameTextField();
        fNameTextField.setMinWidth(250);
        VBox vBox2 = new VBox(fNameLbl, fNameTextField);
        vBox2.setAlignment(Pos.TOP_LEFT);
        add(vBox2, 0, 1, 1, 1);

        Label mNameLbl = new Label("Middle Name");
        TextField mNameTextField = form.getMiddleNameTextField();
        mNameTextField.setMinWidth(150);
        VBox vBox3 = new VBox(mNameLbl, mNameTextField);
        vBox3.setAlignment(Pos.TOP_LEFT);
        add(vBox3, 1, 1);

        Label lNameLbl = new Label("Last Name");
        TextField lNameTextField = form.getLastNameTextField();
        lNameTextField.setMinWidth(250);
        VBox vBox4 = new VBox(lNameLbl, lNameTextField);
        vBox4.setAlignment(Pos.TOP_LEFT);
        add(vBox4, 2, 1);

        Label addressLbl = new Label("Address");
        TextField addressTextField = form.getAddressTextField();
        VBox vBox5 = new VBox(addressLbl, addressTextField);
        vBox5.setAlignment(Pos.TOP_LEFT);
        add(vBox5, 0, 2, 3, 1);

        Label cityLbl = new Label("City");
        TextField cityTextField = form.getCityTextField();
        VBox vBox6 = new VBox(cityLbl, cityTextField);
        vBox6.setAlignment(Pos.TOP_LEFT);
        add(vBox6, 0, 3, 2, 1);

        Label stateLbl = new Label("State");
        TextField stateTextField = form.getStateTextField();
        VBox vBox7 = new VBox(stateLbl, stateTextField);
        vBox7.setAlignment(Pos.TOP_LEFT);
        add(vBox7, 2, 3);

        Label phoneLbl = new Label("Telephone");
        TextField phoneTextField = form.getTelephoneTextField();
        VBox vBox8 = new VBox(phoneLbl, phoneTextField);
        vBox8.setAlignment(Pos.TOP_LEFT);
        add(vBox8, 0, 4);
    }
}
