package application.addProduct;

import application.main.Main;
import application.main.Alerts;
import application.products.Product;
import application.products.ProductDataProcessor;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * The AddProductWindow class creates a new window for adding a product.
 *
 * @author David Spencer
 * @version 1.0
 */
public class AddProductWindow extends Application
{
    private Product newProduct;
    private Stage stage;

    /**
     * Constructor for the AddProductWindow class
     */
    public AddProductWindow()
    {
        newProduct = new Product();
    }

    /**
     * Method used to add the new product to the application.
     * @param event The event that caused this call
     */
    private void addProduct(ActionEvent event) {
        try {
            if (ProductDataProcessor.validProductData(newProduct)) {
                // Product contains valid data and can be added
                Main.addProduct(newProduct);
            }
        }
        catch (NullPointerException exception) {
            System.err.println("Something went wrong!");
        }
        catch (InvalidFieldException exception) {
            Alerts.showAlert(Alert.AlertType.ERROR,
                    exception.getType().toString().replace(":", " error").toUpperCase(),
                    exception.getType().getInvalidFieldDescription().replace(": ", ""));
        }
        catch (StringLengthExceededException exception) {
            Alerts.showAlert(Alert.AlertType.ERROR, "Error!",
                    exception.getType().getStringLengthExceededDescription().replace(": ", ""));
        }
    }

    /**
     * Closes the stage for this window.
     */
    public void closeWindow() {
        stage.close();
    }

    @Override
    public void start(Stage window)
    {
        stage = window;
        Label title = new Label("Add product:");
        title.setId("title");

        // Add text fields
        VBox centerBox = new VBox();
        for (AddProductEnum value : AddProductEnum.values()) {
            centerBox.getChildren().add(new AddProductTextBox(value.toString(), newProduct, value));
        }

        Button addButton = new Button("Add Product");
        addButton.setOnAction(this::addProduct);
        addButton.setMinHeight(50);
        addButton.setMaxHeight(50);
        addButton.setMaxWidth(300);
        addButton.setMinWidth(300);
        addButton.setId("add-button");

        HBox buttonHBox = new HBox(addButton);
        buttonHBox.setId("button-hbox");
        buttonHBox.setAlignment(Pos.CENTER);

        BorderPane contents = new BorderPane(centerBox, title, null, buttonHBox, null);

        Scene scene = new Scene(contents, 700, 800);
//        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

}
