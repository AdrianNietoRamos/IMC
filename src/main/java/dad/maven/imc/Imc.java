package dad.maven.imc;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class Imc extends Application {

	private Label pesoLabel;
	private TextField pesoText;
	private Label kgLabel;
	private Label alturaLabel;
	private TextField alturaText;
	private Label cmLabel;
	private Label imcLabel;
	private Label formulaLabel;
	private Label resulLabel;
	private DoubleProperty alturaProperty = new SimpleDoubleProperty();
	private DoubleProperty pesoProperty = new SimpleDoubleProperty();
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		pesoLabel =new Label("Peso: ");
		pesoText=new TextField();
		pesoText.setMaxWidth(75);
		kgLabel =new Label(" kg");
		alturaLabel =new Label("Altura: ");
		alturaText=new TextField();
		alturaText.setMaxWidth(75);
		cmLabel =new Label(" cm");
		imcLabel =new Label("IMC: ");
		/*pes=Integer.parseInt(pesoText.getText());
		alt=Integer.parseInt(alturaText.getText());
		metros=alt/100;
		formula=pes/(metros*metros);*/
		formulaLabel =new Label();
		resulLabel =new Label();
		HBox pesoBox = new HBox(5, pesoLabel, pesoText, kgLabel);
		pesoBox.setAlignment(Pos.CENTER);
		HBox alturaBox = new HBox(5, alturaLabel, alturaText, cmLabel);
		alturaBox.setAlignment(Pos.CENTER);
		HBox imcBox = new HBox(5, imcLabel, formulaLabel);
		imcBox.setAlignment(Pos.CENTER);
		
		VBox root = new VBox(5);
		root.setAlignment(Pos.CENTER);
		root.setFillWidth(false);
		root.getChildren().addAll(pesoBox, alturaBox, imcBox, resulLabel);
		
		
		
		Scene scene = new Scene(root, 320, 200);

		primaryStage.setTitle("AdivinApp");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		Bindings.bindBidirectional(pesoText.textProperty(), pesoProperty, new NumberStringConverter());
		Bindings.bindBidirectional(alturaText.textProperty(), alturaProperty, new NumberStringConverter());
		
		DoubleBinding metrosBinding = alturaProperty.divide(100);
		
		DoubleBinding mcuadrado =metrosBinding.multiply(metrosBinding); 
		
		DoubleBinding resultadoFinal=pesoProperty.divide(mcuadrado);
		
		formulaLabel.textProperty().bind(Bindings.concat(resultadoFinal.asString()));
		
		formulaLabel.textProperty().addListener((o, ov, nv) -> {
		
		double num= resultadoFinal.doubleValue();
		
		if(num < 18.5) {
			resulLabel.setText("Peso inferior al normal");
		}
		if(num >= 18.5 && num < 25) {
			resulLabel.setText("Normal");
			}
		if(num >= 25 && num < 30) {
			resulLabel.setText("Peso superior al normal");
			}
		if(num >= 30) {
			resulLabel.setText("Obesidad");
			}

	});}

	public static void main(String[] args) {

		launch(args);

	}

}
