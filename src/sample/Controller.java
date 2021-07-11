package sample;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;


public class Controller {

    @FXML
    private Circle circle1;
    @FXML
    private Circle circle2;
    @FXML
    private Circle circle3;
    @FXML
    private Circle circle4;
    @FXML
    private Circle circle5;
    @FXML
    private StackPane row1;
    @FXML
    private StackPane row2;
    @FXML
    private StackPane row3;
    @FXML
    private Text winStatus;
    @FXML
    private int level=1;
    @FXML
    private Button levelButton;

    private Circle currentCircle;
    private int res;

    public void initialize(){
        circle1.setRadius(130);
        circle2.setRadius(100);
        circle3.setRadius(70);
        circle4.setRadius(40);
        circle5.setRadius(10);
        currentCircle=null;
        row1.getChildren().removeAll(circle5, circle4);
        if(level==2) row1.getChildren().add(circle4);
        if(level==3){
            row1.getChildren().add(circle4);
            row1.getChildren().add(circle5);
        }
        res = row1.getChildren().size();
        levelButton.setDisable(true);
    }

    // selects the smallest circle that is, the top ring, and makes it glow to show it is selected
    public void selectCircle(MouseEvent event){
        if(currentCircle==null){
            currentCircle = getCircle((Circle) event.getPickResult().getIntersectedNode());
            Glow glow = new Glow();
            glow.setLevel(0.5);
            currentCircle.setEffect(glow);
        }else{

            if(currentCircle.getId().compareTo(getCircle((Circle) event.getPickResult().getIntersectedNode()).getId())>0){
                String row = event.getPickResult().getIntersectedNode().getParent().getId();
                if(row.equals("row1")){
                    row1.getChildren().add(currentCircle);
                }else if(row.equals("row2")){
                    row2.getChildren().add(currentCircle);
                }else if(row.equals("row3")){
                    row3.getChildren().add(currentCircle);
                }
            }
            currentCircle.setEffect(null);
            currentCircle=null;
        }

        if(row3.getChildren().size()==res){
            winStatus.setVisible(true);
            if(level==3){
                levelButton.setDisable(true);
            }else {
                levelButton.setDisable(false);
            }
        }

    }

    // drops circle on both blank as well as a non blank row appropriately
    public void dropCircle(MouseEvent event){

        String row = event.getPickResult().getIntersectedNode().getId();

        if(row.equals("row1")){
            if(currentCircle == null ) return;
            else if(row1.getChildren().size()==0) {
                row1.getChildren().add(currentCircle);
                currentCircle.setEffect(null);
                currentCircle = null;
            }else{
                if(currentCircle.getId().compareTo(getCircle((Circle) row1.getChildren().get(0)).getId())>0){
                    row1.getChildren().add(currentCircle);
                    currentCircle.setEffect(null);
                    currentCircle = null;
                }else{
                    currentCircle.setEffect(null);
                    currentCircle=null;
                }
            }
        }else if(row.equals("row2")){
            if(currentCircle == null ) return;
            else if(row2.getChildren().size()==0) {
                row2.getChildren().add(currentCircle);
                currentCircle.setEffect(null);
                currentCircle = null;
            }else{
                if(currentCircle.getId().compareTo(getCircle((Circle) row2.getChildren().get(0)).getId())>0){
                    row2.getChildren().add(currentCircle);
                    currentCircle.setEffect(null);
                    currentCircle = null;
                }else{
                    currentCircle.setEffect(null);
                    currentCircle=null;
                }
            }
        }else if(row.equals("row3")){
            if(currentCircle == null ) return;
            else if(row3.getChildren().size()==0) {
                row3.getChildren().add(currentCircle);
                currentCircle.setEffect(null);
                currentCircle = null;
            }else{
                if(currentCircle.getId().compareTo(getCircle((Circle) row3.getChildren().get(0)).getId())>0){
                    row3.getChildren().add(currentCircle);
                    currentCircle.setEffect(null);
                    currentCircle = null;
                }else{
                    currentCircle.setEffect(null);
                    currentCircle=null;
                }
            }
        }

    }

    //gets the smallest circle of the row of whichever circle you press
    //that is you don't want to select a bigger circle when a smaller circle is already present
    public Circle getCircle(Circle circle){
        Circle toChoseCircle = circle;
        StackPane row = (StackPane) circle.getParent();
        for(int i=0; i<row.getChildren().size(); i++){
            if(row.getChildren().get(i).getId().compareTo(toChoseCircle.getId())>0) toChoseCircle= (Circle) row.getChildren().get(i);
        }
        return toChoseCircle;
    }

    // restarts the current level
    public void restart(){
        winStatus.setVisible(false);
        row1.getChildren().removeAll(circle1, circle2, circle3, circle4, circle5);
        row2.getChildren().removeAll(circle1, circle2, circle3, circle4, circle5);
        row3.getChildren().removeAll(circle1, circle2, circle3, circle4, circle5);
        row1.getChildren().addAll(circle1, circle2, circle3);
        initialize();
    }

    public void changeLevel(){
        level++;
        restart();
    }

}
