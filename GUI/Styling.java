
package GUI;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Styling {
	
	public void setFont(Label lbl) {
		lbl.setFont(Font.font("Verdana", FontWeight.NORMAL, 17));
	}
	
	public void setFontMainTitle(Label lbl) {
		lbl.setFont(Font.font("Verdana", FontWeight.BOLD , 17));
	}
	
	
    public  void MainBtnstyling(Button btn){
        btn.setStyle("-fx-padding: 15;" 
                + "-fx-border-style: solid inside;" 
                + "-fx-border-width: 2;" 
                + "-fx-border-insets: 0;"        
                + "-fx-border-radius: 9;"
                +" -fx-background-radius: 9;" 
                + "-fx-border-color: black;"
                + " -fx-font-weight: bold;"
		+ " -fx-font-size: 19;");
    }
    
    public  void MainBtnstylingReset(Button btn){
        btn.setStyle("-fx-padding: 30;" 
                + "-fx-border-style: solid inside;" 
                + "-fx-border-width: 2;" 
                + "-fx-border-insets: 0;"        
                + "-fx-border-radius: 9;"
                +" -fx-background-radius: 9;" 
                + "-fx-border-color: #8B0000;"
                + " -fx-font-weight: bold;"
		+ " -fx-font-size: 19;");
    }
    
    public  void Btnstyling(Button btn){
        btn.setStyle("-fx-padding: 13;" 
                + "-fx-border-style: solid inside;" 
                + "-fx-border-width: 2;" 
                + "-fx-border-insets: 0;"        
                + "-fx-border-radius: 9;"
                +" -fx-background-radius: 9;" 
                + "-fx-border-color: black;"
                + " -fx-font-weight: bold;"
		+ " -fx-font-size: 12;");
    }
    public  void AnchorStyling(AnchorPane anchor){
        anchor.setStyle("-fx-padding: 10;"
                + "-fx-border-style: solid inside;" 
                + "-fx-border-width: 0;" 
                + "-fx-border-insets: 0;"        
                + "-fx-border-radius: 0;");
              
    }
    public  void lblTitleStyling(Label lbl){
        lbl.setFont(Font.font("Verdana"
                , FontWeight.BOLD
                , 40));
    }
    public  void lblStyling(Label lbl){
        lbl.setFont(Font.font("Verdana"
                , FontWeight.BOLD
                , 20));
    }
    public  void txtStyling(TextField txt){
        txt.setStyle("-fx-padding: 3;" 
                + "-fx-border-style: solid inside;"  
                + "-fx-border-width: 2;" 
                + "-fx-border-insets: 0;"       
                + "-fx-border-radius: 2;" 
                +" -fx-background-radius: 2;"
                + "-fx-border-color: grey;"
                +" -fx-background-color: null;");
    }
    
    public  void btnStylingForInput(Button btn){
        btn.setStyle("-fx-padding: 3;" 
                + "-fx-border-style: solid inside;"  
                + "-fx-border-width: 2;" 
                + "-fx-border-insets: 0;"       
                + "-fx-border-radius: 2;" 
                +" -fx-background-radius: 2;"
                + "-fx-border-color: grey;"
                + " -fx-font-weight: bold;"
                );
    }
    public  void PaneStyling(Pane pane){
        pane.setStyle("-fx-padding: 18;"
                + "-fx-border-style: solid inside;" 
                + "-fx-border-width: 0;" 
                + "-fx-border-insets: 0;"        
                + "-fx-border-radius: 0;");
           
    }
    public  void gridStyling(GridPane grid){
        grid.setStyle(" -fx-grid-lines-visible: true;"
        		+ " -fx-padding: 0; "
        		+ "-fx-border-style: solid inside; "
        		+ "-fx-border-width: 0;"
        		+ " -fx-border-insets: 0;"
        		+ " -fx-border-radius: 4; "
        		+ "-fx-border-color: black;");
    }
    public  void vboxStyling(VBox vbox){
        vbox.setStyle("-fx-padding: 18;"
                + "-fx-border-style: solid inside;" 
                + "-fx-border-width: 0;" 
                + "-fx-border-insets: 0;"        
                + "-fx-border-radius: 0;");
             
    }
    
    public  void vboxStylingWithBorder(VBox vbox){
        vbox.setStyle("-fx-padding: 18;"
                + "-fx-border-style: solid inside;" 
                + "-fx-border-width: 2;" 
                + "-fx-border-insets: 0;"        
                + "-fx-border-radius: 4;"
                + "-fx-border-color: black;");
    }
    
    public  void borderStyling(BorderPane border){
        border.setStyle("-fx-padding: 2;"
                + "-fx-border-style: solid inside;" 
                + "-fx-border-width: 0;" 
                + "-fx-border-insets: 0;"        
                + "-fx-border-radius: 0;"
                + "-fx-border-color: black;");
    }
    
    public  void borderStylingWithBorder(BorderPane border){
        border.setStyle("-fx-padding: 18;"
                + "-fx-border-style: solid inside;" 
                + "-fx-border-width: 2;" 
                + "-fx-border-insets: 0;"        
                + "-fx-border-radius: 2;"
                + "-fx-border-color: black;");
    }
    public  void comboStyling(ComboBox<?> combo){
        combo.setStyle("-fx-padding: 10;" 
                + "-fx-border-style: solid inside;"  
                + "-fx-border-width: 2;" 
                + "-fx-border-insets: 0;"
                + "-fx-border-radius: 6;" 
                +" -fx-background-radius: 6;"
                + "-fx-border-color: black;");
      
        
    }
}
