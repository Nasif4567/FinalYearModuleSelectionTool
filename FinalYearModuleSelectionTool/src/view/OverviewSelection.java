package view;



import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Course;
import model.Name;
import model.RunPlan;

public class OverviewSelection extends GridPane {
	
	private TextArea profile;
	private TextArea smodule;
	private TextArea rmodule;
	private Button save;
	
	public OverviewSelection() {
		
		//styling
				this.setVgap(15);
				this.setHgap(10);
				this.setPadding(new Insets(20));
			    this.setAlignment(Pos.TOP_LEFT);
			    
			    
			    ColumnConstraints column0 = new ColumnConstraints();
				column0.setHalignment(HPos.LEFT);
		
		// Text Area 
		profile = new TextArea("Student Profile details will appear here");
		profile.setWrapText(true);
		profile.setEditable(false);
		profile.setPrefHeight(200);
		profile.setPrefWidth(300);
		
		smodule = new TextArea("Selected Modules : ");
		smodule.setEditable(false);
		smodule.setPrefHeight(200);
		smodule.setPrefWidth(300);
		
		rmodule = new TextArea("Reserved modules : ");
		rmodule.setEditable(false);
		rmodule.setPrefHeight(200);
		rmodule.setPrefWidth(300);
		
		save = new Button("Save Overview");
		
		
		VBox whole = new VBox();
		whole.setSpacing(10);;
		HBox nextcontent = new HBox();
		nextcontent.setSpacing(10);
		
		
		nextcontent.getChildren().addAll(smodule,rmodule);
		whole.getChildren().addAll(profile , nextcontent , save);
		
		this.add(whole, 0 , 1);
		
	}
	
	
	// methods , load data in the profile 
	public void LoadProfile(String name , String pn , String email , LocalDate date , Course course) {
		profile.clear();
		profile.appendText("Name : "+name + "\n" + "Pnumber : " + pn + "\n" + "Email : "+ email + "\n"+ "Date : " + date +"\n" +"Course : "+ course); 
	}
	
	// methods , load data in the Selected  
		public void LoadSelectedModules(String mcode , String mname , int credit , String mandatory , String delivery ) {
	
			smodule.appendText(
			"\n" 
			+ "=============== " 
			+"\n" 
			+ "Module Code : "+ mcode + ", "+ "Module Name : " + mname 
			+"\n" +"Credits : "+ credit +", " + "Mandatory on course ? " + mandatory + ", "+ "Delivery :" + delivery 
			+ "\n"
			+ "\n"
					); 
		}
		
		// methods , load data in the Reserved  
				public void LoadReservedModules(String mcode , String mname , int credit , String mandatory , String delivery ) {
					rmodule.appendText(
					"\n" 
					+ "=============== " 
					+"\n" 
					+ "Module Code : "+ mcode + ", "+ "Module Name : " + mname 
					+"\n" +"Credits : "+ credit +", " + "Mandatory on course ? " + mandatory + ", "+ "Delivery :" + delivery 
					+ "\n"
					+ "\n"
							); 
				}
	
	// Event Handler
		public void SaveOverviewHandler(EventHandler<ActionEvent> handler) {
					save.setOnAction(handler);
				}			
	

}
