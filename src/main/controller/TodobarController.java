package controller;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import model.Task;
import ui.EditTask;
import ui.ListView;
import ui.PomoTodoApp;
import utility.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static ui.PomoTodoApp.setScene;

// Controller class for Todobar UI
public class TodobarController implements Initializable {
    private static final String todoOptionsPopUpFXML = "resources/fxml/TodoOptionsPopUp.fxml";
    private static final String todoActionsPopUpFXML = "resources/fxml/TodoActionsPopUp.fxml";
    private File todoActionPopUpFxmlFile = new File(todoActionsPopUpFXML);
    private File todoOptionPopUpFxmlFile = new File(todoOptionsPopUpFXML);
    
    @FXML
    private Label descriptionLabel;
    @FXML
    private JFXHamburger todoActionsPopUpBurger;
    @FXML
    private StackPane todoActionsPopUpContainer;
    @FXML
    private JFXRippler todoOptionsPopUpRippler;
    @FXML
    private StackPane todoOptionsPopUpBurger;
    
    private Task task;

    private JFXPopup actionsPopUp;
    private JFXPopup optionsPopUp;


    
    // REQUIRES: task != null
    // MODIFIES: this
    // EFFECTS: sets the task in this Todobar
    //          updates the Todobar UI label to task's description
    public void setTask(Task task) {
        this.task = task;
        descriptionLabel.setText(task.getDescription());
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: complete this method
        loadActionsPopUp();
        loadActionListener();
        loadOptionsPopUp();
        loadOptionListener();
    }

    private void loadActionsPopUp() {    //toolbarPopUpFxmlFile.toURI().toURL()
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(todoActionPopUpFxmlFile.toURI().toURL());
            fxmlLoader.setController(new ActionPopUpController());
            actionsPopUp = new JFXPopup(fxmlLoader.load());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


    private void loadOptionsPopUp() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(todoOptionPopUpFxmlFile.toURI().toURL());
            fxmlLoader.setController(new OptionPopUpController());
            optionsPopUp = new JFXPopup(fxmlLoader.load());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void loadActionListener() {
        todoActionsPopUpBurger.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                actionsPopUp.show(todoActionsPopUpBurger,
                        JFXPopup.PopupVPosition.TOP,
                        JFXPopup.PopupHPosition.LEFT,
                        12,
                        15);
            }
        });
    }

    private void loadOptionListener() {
        todoOptionsPopUpBurger.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                optionsPopUp.show(todoOptionsPopUpBurger,
                        JFXPopup.PopupVPosition.TOP,
                        JFXPopup.PopupHPosition.RIGHT,
                        -12,
                        15);
            }
        });
    }

    class ActionPopUpController {
        @FXML
        private JFXListView<?> actionPopUpList;

        @FXML
        private void submit() {
            int selectedIndex = actionPopUpList.getSelectionModel().getSelectedIndex();
            switch (selectedIndex) {
                case 0:
                    Logger.log("TodobarActionsPopUpController", "TO DO is not supported in this version!");
                    break;
                case 1:
                    Logger.log("TodobarActionsPopUpController", "UP NEXT is not supported in this version!");
                    break;
                case 2:
                    Logger.log("TodobarActionsPopUpController", "IN PROGRESS is not supported in this version!");
                    break;
                case 3:
                    Logger.log("TodobarActionsPopUpController", "DONE is not supported in this version!");
                    break;
                default:
                    Logger.log("TodobarActionsPopUpController", "POMODORO is not supported in this version!");
            }
            actionsPopUp.hide();
        }
    }

    class OptionPopUpController {
        @FXML
        private JFXListView<?> optionPopUpList;

        @FXML
        private void submit() {
            int selectedIndex = optionPopUpList.getSelectionModel().getSelectedIndex();
            switch (selectedIndex) {
                case 0:
                    Logger.log("TodobarOptionsPopUpController", "Editing the selected task");
                    setScene(new EditTask(task));
                    break;
                case 1:
                    Logger.log("TodobarOptionsPopUpController", "Selected task has deleted!");
                    PomoTodoApp.getTasks().remove(task);
                    setScene(new ListView(PomoTodoApp.getTasks()));
                    break;
                default:
                    Logger.log("TodobarOptionsPopUpController", "No action is implemented for the selected option");
            }
            optionsPopUp.hide();
        }
    }
}



