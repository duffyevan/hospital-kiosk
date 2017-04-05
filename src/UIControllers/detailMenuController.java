package UIControllers;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by Leon Zhang on 2017/4/1.
 */

public class detailMenuController extends centralUIController implements Initializable {
  /* define all ui elements */
  @FXML
  private Pane DetailMenu; // Value injected by FXMLLoader


  @Override
  public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
  }

  public void quit () {
    Stage primaryStage = (Stage) DetailMenu.getScene().getWindow();
    try {
      restartUI(primaryStage);
    } catch (Exception e) {
      System.out.println("Cannot load main menu");
      e.printStackTrace();
    }
  }

  public void back () {
    Stage primaryStage = (Stage) DetailMenu.getScene().getWindow();
    try {
      loadScene(primaryStage, "/SearchMenu.fxml");
    } catch (Exception e) {
      System.out.println("Cannot load search menu");
      e.printStackTrace();
    }
  }

  public void gotoMap () {
    Stage primaryStage = (Stage) DetailMenu.getScene().getWindow();
    try {
      loadScene(primaryStage, "/MapScene.fxml");
    } catch (Exception e) {
      System.out.println("Cannot load map view");
      e.printStackTrace();
    }
  }

}

