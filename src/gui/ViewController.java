package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Constraints;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.entities.Person;

public class ViewController implements Initializable {

	@FXML
	private TextField txtNumber1;
	@FXML
	private TextField txtNumber2;
	@FXML
	private Label labelResult;
	@FXML
	private Button btnSum;
	@FXML
	private ComboBox<Person> comboBoxPerson;
	private ObservableList<Person> obsList;
	@FXML
	private Button btnAll;

	@FXML
	public void onBynAllAction() {
		for (Person person : comboBoxPerson.getItems()) {
			System.out.println(person);
		}
	}
	
	@FXML
	public void onComboBoxPersonAction() {
		Person person = comboBoxPerson.getSelectionModel().getSelectedItem();
		System.out.println(person);
	}
	
	@FXML
	public void onBtnSumAction() {
		try {
			Locale.setDefault(Locale.US);
			Double number1 = Double.parseDouble(txtNumber1.getText());
			Double number2 = Double.parseDouble(txtNumber2.getText());
			Double sum = number1 + number2;
			labelResult.setText(String.format("%.2f", sum));
		} catch (NumberFormatException e) {
			Alerts.showAlert("Alerta de Mensagem", "Opera��o Inv�lida", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Constraints.setTextFieldDouble(txtNumber1);
		Constraints.setTextFieldDouble(txtNumber2);
		Constraints.setTextFieldMaxLength(txtNumber1, 10);
		Constraints.setTextFieldMaxLength(txtNumber2, 10);

		List<Person> list = new ArrayList<>();
		list.add(new Person(1, "Maria", "maria@gmail.com"));
		list.add(new Person(2, "Alex", "alex@gmail.com"));
		list.add(new Person(3, "Bob", "bob@gmail.com"));

		obsList = FXCollections.observableArrayList(list);
		comboBoxPerson.setItems(obsList);

		Callback<ListView<Person>, ListCell<Person>> factory = lv -> new ListCell<Person>() {
			@Override
			protected void updateItem(Person item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getName());
			}
		};

		comboBoxPerson.setCellFactory(factory);
		comboBoxPerson.setButtonCell(factory.call(null));
	}
}
