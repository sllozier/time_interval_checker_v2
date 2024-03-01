package main;

import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Name: Sarah L. Lozier
 * Class: CMSC 215 - 6380
 * Project: Project 4
 * Date: March 5th, 2024
 * Description: Project4 class implements a GUI interface for comparing time
 * intervals and checking if a given time is within the intervals.
 * It contains two buttons: "Compare Intervals" and "Check Time" which perform
 * the respective operations and display the results.
 * The GUI layout includes text fields for inputting start and end times of two
 * intervals, a text field for entering a time to check,
 * and a label to display the output messages.
 */

// scene.getStylesheets().add("path/to/your/stylesheet.css");

public class Project4 extends Application {

    private ComboBox<String> startHour1, startMinute1, endHour1, endMinute1,
            startHour2, startMinute2, endHour2, endMinute2,
            startMeridian1, endMeridian1, startMeridian2, endMeridian2,
            timeToCheckHour, timeToCheckMinute, timeToCheckMeridian;

    private TextField overlapOutputField;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        GridPane gridPane = createGridPane();
        root.setCenter(gridPane);
        Scene scene = new Scene(root, 650, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Time Interval Checker");
        primaryStage.show();
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Define the gradient
        LinearGradient gradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#ee7752")),
                new Stop(0.3, Color.web("#e73c7e")),
                new Stop(0.6, Color.web("#23a6d5")),
                new Stop(1, Color.web("#23d5ab")));

        // Set the gradient as background
        gridPane.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));

        initializeComboBoxes();
        createLabels(gridPane);
        createFields(gridPane);
        createButtons(gridPane);

        return gridPane;
    }

    private void initializeComboBoxes() {
        // Initialize ComboBoxes for hours, minutes, and meridians
        String[] hours = new String[12];
        for (int i = 0; i < 12; i++) {
            hours[i] = String.format("%02d", i + 1);
        }
        String[] minutes = new String[60];
        for (int i = 0; i < 60; i++) {
            minutes[i] = String.format("%02d", i);
        }
        String[] meridians = { "AM", "PM" };

        startHour1 = new ComboBox<>();
        startMinute1 = new ComboBox<>();
        endHour1 = new ComboBox<>();
        endMinute1 = new ComboBox<>();
        startHour2 = new ComboBox<>();
        startMinute2 = new ComboBox<>();
        endHour2 = new ComboBox<>();
        endMinute2 = new ComboBox<>();
        startMeridian1 = new ComboBox<>();
        endMeridian1 = new ComboBox<>();
        startMeridian2 = new ComboBox<>();
        endMeridian2 = new ComboBox<>();
        timeToCheckHour = new ComboBox<>();
        timeToCheckMinute = new ComboBox<>();
        timeToCheckMeridian = new ComboBox<>();

        List<ComboBox<String>> allHours = Arrays.asList(startHour1, startMinute1, endHour1, endMinute1, startHour2,
                startMinute2, endHour2, endMinute2, timeToCheckHour);
        List<ComboBox<String>> allMinutes = Arrays.asList(startMinute1, endMinute1, startMinute2, endMinute2,
                timeToCheckMinute);
        List<ComboBox<String>> allMeridians = Arrays.asList(startMeridian1, endMeridian1, startMeridian2, endMeridian2,
                timeToCheckMeridian);

        // Populate hours ComboBoxes
        for (ComboBox<String> cb : allHours) {
            cb.getItems().clear(); // Clear existing items to prevent duplication
            cb.getItems().addAll(hours);
            cb.setValue("01"); // Set default value
            cb.setBorder(new Border(new BorderStroke(Color.DARKGREY, BorderStrokeStyle.SOLID,
                    CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            addHoverEffects(cb);
        }

        // Populate minutes ComboBoxes
        for (ComboBox<String> cb : allMinutes) {
            cb.getItems().clear(); // Clear existing items to prevent duplication
            cb.getItems().addAll(minutes);
            cb.setValue("00"); // Set default value
            cb.setBorder(new Border(new BorderStroke(Color.DARKGREY, BorderStrokeStyle.SOLID,
                    CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            addHoverEffects(cb);
        }

        // Populate meridians ComboBoxes
        for (ComboBox<String> cb : allMeridians) {
            cb.getItems().clear(); // Clear existing items to prevent duplication
            cb.getItems().addAll(meridians);
            cb.setValue("AM"); // Set default value
            cb.setBorder(new Border(new BorderStroke(Color.DARKGREY, BorderStrokeStyle.SOLID,
                    CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            addHoverEffects(cb);
        }

    }

    private void createLabels(GridPane gridPane) {
        String[] labels = { "Start Time", "End Time", "Time Interval 1", "Time Interval 2", };
        int[] rows = { 0, 0, 1, 2 };
        int[] cols = { 1, 2, 0, 0 };
        for (int i = 0; i < labels.length; i++) {
            Label label = new Label(labels[i]);
            label.setFont(Font.font("Arial", FontWeight.BOLD, 12)); // Set font size if needed
            StackPane stackPane = new StackPane();
            stackPane.getChildren().add(label);
            StackPane.setAlignment(label, Pos.CENTER);
            GridPane.setConstraints(stackPane, cols[i], rows[i]);
            gridPane.getChildren().add(stackPane);
        }
    }

    private void createFields(GridPane gridPane) {
        overlapOutputField = new TextField();
        overlapOutputField.setEditable(false);

        HBox interval1Start = new HBox(5, startHour1, startMinute1, startMeridian1);
        HBox interval1End = new HBox(5, endHour1, endMinute1, endMeridian1);
        HBox interval2Start = new HBox(5, startHour2, startMinute2, startMeridian2);
        HBox interval2End = new HBox(5, endHour2, endMinute2, endMeridian2);

        interval1Start.setPadding(new Insets(0, 15, 0, 15)); // top, right, bottom, left
        interval1End.setPadding(new Insets(0, 15, 0, 15));
        interval2Start.setPadding(new Insets(0, 15, 0, 15));
        interval2End.setPadding(new Insets(0, 15, 0, 15));

        gridPane.add(interval1Start, 1, 1);
        gridPane.add(interval1End, 2, 1);
        gridPane.add(interval2Start, 1, 2);
        gridPane.add(interval2End, 2, 2);
        gridPane.add(overlapOutputField, 1, 7, 2, 1);

        // "Time to Check" setup with label
        Label timeToCheckLabel = new Label("Time to Check");
        timeToCheckLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        HBox timeToCheck = new HBox(5, timeToCheckHour, timeToCheckMinute, timeToCheckMeridian);
        timeToCheck.setPadding(new Insets(0, 15, 0, 15));

        // Wrap the label and timeToCheck HBox in a container HBox
        HBox timeToCheckContainer = new HBox(10, timeToCheckLabel, timeToCheck);
        timeToCheckContainer.setAlignment(Pos.CENTER); // Center the contents

        // Set the container HBox to span across the desired number of columns for
        // centering
        GridPane.setConstraints(timeToCheckContainer, 0, 4); // Adjust the starting column index as needed
        GridPane.setColumnSpan(timeToCheckContainer, GridPane.REMAINING); // Span across all remaining columns
        gridPane.getChildren().add(timeToCheckContainer);
    }

    private void createButtons(GridPane gridPane) {
        Button compareIntervals = new Button("Compare Intervals");
        Button checkTime = new Button("Check Time");
        Button clearFields = new Button("Clear Fields");

        compareIntervals.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        compareIntervals.setBackground(
                new Background(new BackgroundFill(Color.web("#bcf1ed"), CornerRadii.EMPTY, Insets.EMPTY)));
        compareIntervals.setMaxWidth(Double.MAX_VALUE);
        addHoverEffects(compareIntervals);

        checkTime.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        checkTime.setBackground(
                new Background(new BackgroundFill(Color.web("#ffcf3d"), CornerRadii.EMPTY, Insets.EMPTY)));
        checkTime.setMaxWidth(Double.MAX_VALUE);
        addHoverEffects(checkTime);

        clearFields.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        clearFields.setBackground(
                new Background(new BackgroundFill(Color.web("#fc9d88"), CornerRadii.EMPTY, Insets.EMPTY)));
        clearFields.setMaxWidth(Double.MAX_VALUE);
        addHoverEffects(clearFields);

        compareIntervals.setOnAction(e -> handleCompareIntervals());
        checkTime.setOnAction(e -> handleCheckTime());
        clearFields.setOnAction(e -> handleClearButtonAction());

        gridPane.add(compareIntervals, 1, 3);
        gridPane.add(checkTime, 2, 3);
        gridPane.add(clearFields, 2, 8, 2, 1);
    }

    private void handleCompareIntervals() {
        try {
            // Parse the values directly from ComboBoxes without needing to convert from
            // String
            int startHour1Value = Integer.parseInt(startHour1.getValue());
            int startMinute1Value = Integer.parseInt(startMinute1.getValue());
            String startMeridian1Value = startMeridian1.getValue();

            int endHour1Value = Integer.parseInt(endHour1.getValue());
            int endMinute1Value = Integer.parseInt(endMinute1.getValue());
            String endMeridian1Value = endMeridian1.getValue();

            int startHour2Value = Integer.parseInt(startHour2.getValue());
            int startMinute2Value = Integer.parseInt(startMinute2.getValue());
            String startMeridian2Value = startMeridian2.getValue();

            int endHour2Value = Integer.parseInt(endHour2.getValue());
            int endMinute2Value = Integer.parseInt(endMinute2.getValue());
            String endMeridian2Value = endMeridian2.getValue();

            // Construct Time objects for the start and end of both intervals
            Time interval1Start = new Time(startHour1Value, startMinute1Value, startMeridian1Value);
            Time interval1End = new Time(endHour1Value, endMinute1Value, endMeridian1Value);
            Time interval2Start = new Time(startHour2Value, startMinute2Value, startMeridian2Value);
            Time interval2End = new Time(endHour2Value, endMinute2Value, endMeridian2Value);

            // Now proceed with creating Interval objects and comparing them as before
            Interval<Time> interval1 = new Interval<>(interval1Start, interval1End);
            Interval<Time> interval2 = new Interval<>(interval2Start, interval2End);

            boolean overlap = interval1.overlaps(interval2) || interval2.overlaps(interval1);
            boolean subinterval1 = interval1.subinterval(interval2);
            boolean subinterval2 = interval2.subinterval(interval1);

            if (subinterval1) {
                overlapOutputField.setText("Interval 2 is a sub-interval of interval 1");
            } else if (subinterval2) {
                overlapOutputField.setText("Interval 1 is a sub-interval of interval 2");
            } else if (overlap) {
                overlapOutputField.setText("The intervals overlap");
            } else {
                overlapOutputField.setText("The intervals are disjoint");
            }
        } catch (NumberFormatException e) {
            overlapOutputField.setText("Invalid time format or values. Please check your input.");
        }
    }

    private void handleCheckTime() {
        // Retrieve the selected values from the ComboBoxes
        String hourStr = timeToCheckHour.getValue();
        String minuteStr = timeToCheckMinute.getValue();
        String meridian = timeToCheckMeridian.getValue();

        // Validate the selections are not null (in case ComboBoxes are not properly
        // initialized)
        if (hourStr == null || minuteStr == null || meridian == null) {
            overlapOutputField.setText("Please select a valid time to check.");
            return;
        }
        try {
            // Convert the selected hour and minute to integers
            int hour = Integer.parseInt(hourStr);
            int minute = Integer.parseInt(minuteStr);

            // Construct the Time object for the time to check
            Time timeToCheck = new Time(hour, minute, meridian);

            // Construct Time objects for interval start and end times as above
            Time interval1Start = new Time(Integer.parseInt(startHour1.getValue()),
                    Integer.parseInt(startMinute1.getValue()), startMeridian1.getValue());
            Time interval1End = new Time(Integer.parseInt(endHour1.getValue()), Integer.parseInt(endMinute1.getValue()),
                    endMeridian1.getValue());
            Time interval2Start = new Time(Integer.parseInt(startHour2.getValue()),
                    Integer.parseInt(startMinute2.getValue()), startMeridian2.getValue());
            Time interval2End = new Time(Integer.parseInt(endHour2.getValue()), Integer.parseInt(endMinute2.getValue()),
                    endMeridian2.getValue());

            Interval<Time> interval1 = new Interval<>(interval1Start, interval1End);
            Interval<Time> interval2 = new Interval<>(interval2Start, interval2End);

            boolean inInterval1 = interval1.within(timeToCheck);
            boolean inInterval2 = interval2.within(timeToCheck);

            if (inInterval1 && inInterval2) {
                overlapOutputField.setText("Both intervals contain the time " + timeToCheck);
            } else if (inInterval1) {
                overlapOutputField.setText("Only interval 1 contains the time " + timeToCheck);
            } else if (inInterval2) {
                overlapOutputField.setText("Only interval 2 contains the time " + timeToCheck);
            } else {
                overlapOutputField.setText("Neither interval contains the time " + timeToCheck);
            }
        } catch (NumberFormatException e) {
            overlapOutputField.setText("Invalid time format or values. Please check your input.");
        }
    }

    // Method to clear all fields and reset ComboBoxes to their default values
    private void clearFieldsAndComboBoxes(List<TextField> textFields, List<ComboBox<String>> comboBoxes) {
        // Clear all text fields
        for (TextField textField : textFields) {
            textField.clear();
        }
        // Reset combo boxes to their default values
        for (ComboBox<String> comboBox : comboBoxes) {
            comboBox.getSelectionModel().selectFirst(); // Assumes the first item is a suitable default
        }
    }

    private void handleClearButtonAction() {
        List<TextField> textFields = Arrays.asList(overlapOutputField);

        List<ComboBox<String>> comboBoxes = Arrays.asList(startHour1, startMinute1, endHour1, endMinute1,
                startHour2, startMinute2, endHour2, endMinute2,
                startMeridian1, endMeridian1, startMeridian2, endMeridian2, timeToCheckHour, timeToCheckMinute,
                timeToCheckMeridian);

        clearFieldsAndComboBoxes(textFields, comboBoxes);
    }

    // Method to add hover effects to buttons and combo boxes
    private void addHoverEffects(Control control) {
        // Add hover effect to change border color
        control.setOnMouseEntered(e -> {
            if (control instanceof ComboBox) {
                control.setBorder(new Border(new BorderStroke(Color.DARKGREY.brighter(), BorderStrokeStyle.SOLID, null,
                        new BorderWidths(1))));
            } else if (control instanceof Button) {
                Button button = (Button) control; // Cast to Button
                if (button.getText().equals("Clear Fields")) {
                    control.setBackground(
                            new Background(new BackgroundFill(Color.web("#ff634d"), CornerRadii.EMPTY, Insets.EMPTY)));
                } else if (button.getText().equals("Check Time")) {
                    control.setBackground(
                            new Background(new BackgroundFill(Color.web("#ffb85f"), CornerRadii.EMPTY, Insets.EMPTY)));
                } else if (button.getText().equals("Compare Intervals")) {
                    control.setBackground(
                            new Background(new BackgroundFill(Color.web("#01b6ad"), CornerRadii.EMPTY, Insets.EMPTY)));
                }
            }
        });
        // Reset border color on mouse exit
        control.setOnMouseExited(e -> {
            if (control instanceof ComboBox) {
                control.setBorder(new Border(new BorderStroke(Color.DARKGREY, BorderStrokeStyle.SOLID, null,
                        new BorderWidths(1))));
            } else if (control instanceof Button) {
                Button button = (Button) control; // Cast to Button
                if (button.getText().equals("Clear Fields")) {
                    control.setBackground(
                            new Background(new BackgroundFill(Color.web("#fc9d88"), CornerRadii.EMPTY, Insets.EMPTY)));
                } else if (button.getText().equals("Check Time")) {
                    control.setBackground(
                            new Background(new BackgroundFill(Color.web("#ffcf3d"), CornerRadii.EMPTY, Insets.EMPTY)));
                } else if (button.getText().equals("Compare Intervals")) {
                    control.setBackground(
                            new Background(new BackgroundFill(Color.web("#bcf1ed"), CornerRadii.EMPTY, Insets.EMPTY)));
                }
            }

        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}