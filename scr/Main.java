package eg.edu.alexu.csd.datastructure;

import javax.print.DocFlavor;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import java.net.URL;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    public static Stage stage ;
    public static Stage error ;
    public static Mail m = new Mail() ;

    @Override
    public void start(Stage Pstage) throws Exception {
        this.stage = Pstage ;
        homePage();
    }

    public static void errorPopupWindow() throws Exception {
        error = new Stage() ;
        error.initModality(Modality.APPLICATION_MODAL);
        error.setTitle("Error");
        error.getIcons().add(new Image("image/error.png"));
        Parent root = FXMLLoader.load(Main.class.getResource("error.fxml"));
        error.setScene(new Scene(root));
        error.showAndWait();
    }

    public static void homePage() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("GUI.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.setTitle("Mail server");
        stage.getIcons().add(new Image("image/icon.png"));
        stage.show();
    }

    public static void passwordPage() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("passwordpage.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    public static void SignupPage() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("signup.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    public static void Emailview() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("emialview.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { launch(args); }

}
