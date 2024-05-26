package application;

import java.util.function.UnaryOperator;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }
    private Password pass = new Password();
    @Override
    public void start(Stage primaryStage) throws Exception {

        Button genrateButton = new Button("Generate!");
        genrateButton.setDisable(true);
        Button copyButton = new Button("Copy!");
        copyButton.setDisable(true);

        copyButton.setOnAction(event->{
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(pass.getPassword());
            clipboard.setContent(content);
            copyButton.setText("Copied!");
            copyButton.setDisable(true);
        });

        Label label1 = new Label("Length");
        Label label2 = new Label("Generated: ");
        VBox labelBox = new VBox(label1, label2);
        labelBox.setAlignment(Pos.CENTER);
        labelBox.setSpacing(15);

        TextField lengthField = new TextField();
        TextFormatter<String> formatter = new TextFormatter<>((UnaryOperator<TextFormatter.Change>) change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")&& newText.length()<4) {
                return change;
            }
            return null;
        });
        lengthField.setOnKeyReleased(event->{
                genrateButton.setDisable(lengthField.getText().length() == 0);
        });
        lengthField.setTextFormatter(formatter);
        
        lengthField.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
            	genrateButton.requestFocus();
            	genrateButton.fireEvent(event);
            }
        });
        
        TextField genratedField = new TextField();
        genratedField.setEditable(false);

        VBox TextFieldbox = new VBox(lengthField, genratedField);
        TextFieldbox.setAlignment(Pos.CENTER);
        TextFieldbox.setSpacing(10);

        HBox hbox = new HBox(labelBox, TextFieldbox);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(5);

        CheckBox UppercaseCheckBox = new CheckBox("Contains Uppercase");
        UppercaseCheckBox.setSelected(true);

        CheckBox SymbolsCheckBox = new CheckBox("Contains Symbols");
        SymbolsCheckBox.setSelected(true); 

        CheckBox NumbersCheckBox = new CheckBox("Contains Numbers");
        NumbersCheckBox.setSelected(true);

        HBox CheckBoxbox = new HBox(UppercaseCheckBox,SymbolsCheckBox,NumbersCheckBox);
        CheckBoxbox.setAlignment(Pos.CENTER);
        CheckBoxbox.setSpacing(10);

        genrateButton.setOnAction(event->{
            genratedField.setText(pass.Genrate(UppercaseCheckBox.isSelected(), SymbolsCheckBox.isSelected(), NumbersCheckBox.isSelected(), Integer.parseInt(lengthField.getText())));
            copyButton.setDisable(genratedField.getText().length() == 0);
            copyButton.setText("Copy!");
        });


        VBox root = new VBox(hbox,genrateButton,copyButton,CheckBoxbox);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(15);
        primaryStage.setTitle("Random Password Genrator");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }
}
