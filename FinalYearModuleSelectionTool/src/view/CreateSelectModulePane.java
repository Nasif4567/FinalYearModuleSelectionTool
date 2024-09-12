package view;

import javafx.scene.layout.ColumnConstraints;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Course;
import model.Module;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Collection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class CreateSelectModulePane extends GridPane {
	
	private ListView<Module> USTerm1ListView;
	private ListView<Module> USTerm2ListView;
	
	private ListView<Module> STerm1ListView;
	private ListView<Module> STerm2ListView;
	private ListView<Module> YLListView;
	
	
	private Button addTerm1;
	private Button removeTerm1;
	private Button addTerm2;
	private Button removeTerm2;
	private Button reset;
	private Button submit;
	
	final private TextField creditTerm1txt;
	private TextField creditTerm2txt;
	
	
	
	
	
	
	
	public CreateSelectModulePane(){
		
		
		//styling
		this.setVgap(15);
		this.setHgap(10);
		this.setPadding(new Insets(20));
	    this.setAlignment(Pos.TOP_LEFT);
	    
	    
	    ColumnConstraints column0 = new ColumnConstraints();
		column0.setHalignment(HPos.LEFT);

		this.getColumnConstraints().addAll(column0);
		
		//create labels
				Label lblTerm1us = new Label("Unselected Term 1 Modules");
				Label lblTerm2us = new Label("Unselected Term 2 Modules");
				Label lblYearlm = new Label("Selected Year Long Modules");
				Label lblTerm1s = new Label("Selected Term 1 Modules");
				Label lblTerm2s = new Label("Selected Term 2 Modules");
				Label lblTerm1 = new Label("Unselected Term 1 Modules");
				Label lblTerm1btn = new Label("Term 1");
				Label lblTerm2btn = new Label("Term 2");
				Label lblcreditterm1 = new Label("Current Term 1 credits:");
				Label lblcreditterm2 = new Label("Current Term 2 credits:");
				
				
		// Initialise ListViews
				USTerm1ListView =  new ListView<>();
				USTerm1ListView.setPrefHeight(100);
				USTerm1ListView.setPrefWidth(300);
				
				USTerm2ListView =  new ListView<>();
				USTerm2ListView.setPrefHeight(100);
				USTerm2ListView.setPrefWidth(300);
				
				STerm1ListView =  new ListView<>();
				STerm1ListView.setPrefHeight(100);
				STerm1ListView.setPrefWidth(300);
				
				STerm2ListView =  new ListView<>();
				STerm2ListView.setPrefHeight(100);
				STerm2ListView.setPrefWidth(300);
				
				YLListView =  new ListView<>();
				YLListView.setPrefHeight(100);
				YLListView.setPrefWidth(300);
				
		// Initialise Button
				
				addTerm1 = new Button("Add");
				removeTerm1 = new Button("Remove");
				addTerm2 = new Button("Add");
				removeTerm2 = new Button("Remove");
				reset = new Button("Reset");
				submit = new Button("Submit");
				
		// Make text boxes 
				creditTerm1txt = new TextField("0"); 
				creditTerm1txt.setEditable(false);
				creditTerm2txt = new TextField("0");
				creditTerm2txt.setEditable(false);
				
				
		//-----------------------------------------------------------------------------		
				
		// Make a Hboxes for term 1 button
				
				HBox Term1btnBox = new HBox();
				Term1btnBox.getChildren().addAll(lblTerm1btn,addTerm1, removeTerm1 );
				Term1btnBox.setSpacing(10);
				
         // Make a Hboxes for term 2 button
				
				HBox Term2btnBox = new HBox();
				Term2btnBox.getChildren().addAll(lblTerm2btn,addTerm2, removeTerm2 );
				Term2btnBox.setSpacing(10);	
				
			
				
				
				
		//  Add Controls and Labels to the container 
				
        //-----------------------------------------Term 1 unseleted--------------
				this.add(lblTerm1us, 0, 0); 
				this.add(USTerm1ListView, 0, 1);
				this.add(Term1btnBox , 0, 2);
				
				
		//---------------------Term 2 unselected--------------------------------
				
				this.add(lblTerm2us, 0, 4); 
				this.add(USTerm2ListView, 0, 5);
				this.add(Term2btnBox , 0, 6);
				
				
//--------------------Term1 selected----------------------------------
				
				this.add(lblTerm1s,1,0);
				this.add(STerm1ListView, 1, 1);		
				
//--------------------Term2 selected----------------------------------
				
				this.add(lblTerm2s,1,4);
				this.add(STerm2ListView, 1, 5);	
				
				
//--------------------Year long module----------------------------------
				
				this.add(lblYearlm,0,7);
				this.add(YLListView, 0, 8);				
				

				
 // ------------------- Make a Hboxes for credit1------------------------------
				
				HBox CreditBox1 = new HBox();
				CreditBox1.getChildren().addAll(lblcreditterm1,creditTerm1txt);
				CreditBox1.setSpacing(10);
				
// ------------------- Make a Hboxes for credit2------------------------------
				
				HBox CreditBox2 = new HBox();
				CreditBox2.getChildren().addAll(lblcreditterm2,creditTerm2txt);
				CreditBox2.setSpacing(10);					
	
// -------------------------------------------add the credit boxes ------------
				this.add(CreditBox1,0,9);
				this.add(CreditBox2,1,9);

// ------------------- Make a Hboxes for submite and reset ------------------------------
				
				HBox submitreset = new HBox();
				submitreset.getChildren().addAll(submit , reset);
				submitreset.setSpacing(10);				
				
//---------------------------------------------------------------------------------
				this.add(submitreset,0,10);
				
				// Make ListView and TextField resizable
				GridPane.setHgrow(USTerm1ListView, Priority.ALWAYS);
				GridPane.setVgrow(USTerm1ListView, Priority.ALWAYS);
				GridPane.setHgrow(USTerm2ListView, Priority.ALWAYS);
				GridPane.setVgrow(USTerm2ListView, Priority.ALWAYS);
				GridPane.setHgrow(STerm1ListView, Priority.ALWAYS);
				GridPane.setVgrow(STerm1ListView, Priority.ALWAYS);
				GridPane.setHgrow(STerm2ListView, Priority.ALWAYS);
				GridPane.setVgrow(STerm2ListView, Priority.ALWAYS);
				GridPane.setHgrow(YLListView, Priority.ALWAYS);
				GridPane.setVgrow(YLListView, Priority.ALWAYS);
				GridPane.setHgrow(creditTerm1txt, Priority.ALWAYS);
				GridPane.setVgrow(creditTerm1txt, Priority.ALWAYS);
				GridPane.setHgrow(creditTerm2txt, Priority.ALWAYS);
				GridPane.setVgrow(creditTerm2txt, Priority.ALWAYS);
				GridPane.setHgrow(Term1btnBox, Priority.ALWAYS);
				GridPane.setVgrow(Term1btnBox, Priority.ALWAYS);
				GridPane.setHgrow(Term2btnBox, Priority.ALWAYS);
				GridPane.setVgrow(Term2btnBox, Priority.ALWAYS);
				GridPane.setHgrow(CreditBox1, Priority.ALWAYS);
				GridPane.setVgrow(CreditBox1, Priority.ALWAYS);
				GridPane.setHgrow(CreditBox2, Priority.ALWAYS);
				GridPane.setVgrow(CreditBox2, Priority.ALWAYS);
				GridPane.setHgrow(submitreset, Priority.ALWAYS);
				GridPane.setVgrow(submitreset, Priority.ALWAYS);			
				
				
	}
	
	
	//Setter from this view 
	
	public void setLoadSL1(Collection<Module> term1s , Collection<Module> yearlong ) {
		ObservableList<Module> items1sss = FXCollections.observableArrayList(term1s);
		STerm1ListView.setItems(items1sss);
		
		ObservableList<Module> yearsss = FXCollections.observableArrayList(yearlong);
		YLListView.setItems(yearsss);
	}
	
	
	public void setLoadSL2(Collection<Module> term2s) {
		ObservableList<Module> items2sss = FXCollections.observableArrayList(term2s);
		STerm2ListView.setItems(items2sss);
	}
	
	
	
	
	
	
	//List view Load Methods allow controller to load modules
	
	public void LoadTermsModules(Collection<Module> term1us , Collection<Module> term2us , Collection<Module> term1ss , Collection<Module> term2ss, Collection<Module> yearlong
			,int mandcreditterm1 , int ylcredit , int mandcreditterm2
			) {
		
		// Term 1 unselected 
	    ObservableList<Module> items1 = FXCollections.observableArrayList(term1us);
		USTerm1ListView.setItems(items1);
		
		// Term 2 unselected 
		ObservableList<Module> items2 = FXCollections.observableArrayList(term2us);
		USTerm2ListView.setItems(items2);
		
		// Term 1 selected
		ObservableList<Module> items1s = FXCollections.observableArrayList(term1ss);
		STerm1ListView.setItems(items1s);
		
		// Term two selected
		ObservableList<Module> items2s = FXCollections.observableArrayList(term2ss);
		STerm2ListView.setItems(items2s);
		
		
		// Term two selected
		ObservableList<Module> itemsyl = FXCollections.observableArrayList(yearlong);
		YLListView.setItems(itemsyl);	
		
		
		// show credits
		int defalutcreditterm1= mandcreditterm1 +  ylcredit/2;
		creditTerm1txt.setText(String.valueOf( defalutcreditterm1));
		
		int defalutcreditterm2= mandcreditterm2 +  ylcredit/2;
		creditTerm2txt.setText(String.valueOf(defalutcreditterm2 ));
		
		
	}
	
	
	// handler methods for add and remove 
		public void addTerm1ModuleHandler(EventHandler<ActionEvent> handler) {
			addTerm1.setOnAction(handler);
		}
		
		public void addTerm2ModuleHandler(EventHandler<ActionEvent> handler) {
			addTerm2.setOnAction(handler);
		}
		
		public void removeTerm1ModuleHandler(EventHandler<ActionEvent> handler) {
			removeTerm1.setOnAction(handler);
		}
		
		public void removeTerm2ModuleHandler(EventHandler<ActionEvent> handler) {
			removeTerm2.setOnAction(handler);
		}
		
	//handlers for submit 
		public void submitHandler(EventHandler<ActionEvent> handler) {
			submit.setOnAction(handler);
		}
		
		public void ResetHandler(EventHandler<ActionEvent> handler) {
			reset.setOnAction(handler);
		}
		
		
//------------------------------------------Add codes------------------------------------------		
		
	
	// Method allow controller to add modules to selected from unselected
	public void addtoSelectedLVterm1(int addmc) {
		Module selec = USTerm1ListView.getSelectionModel().getSelectedItem();
		
		if (selec != null) {
		USTerm1ListView.getItems().remove(selec);
		STerm1ListView.getItems().add(selec); 
		
		//add module credit 
		int getcurrentmc = Integer.parseInt(creditTerm1txt.getText());
		int changecredit = addmc + getcurrentmc;
		creditTerm1txt.setText(String.valueOf(changecredit));
		
		}	
		 } 
	
	
	// Method allow controller to add modules to selected from unselected
		public void addtoSelectedLVterm2(int addmc) {
			Module selec = USTerm2ListView.getSelectionModel().getSelectedItem();
			
			if (selec != null) {
				USTerm2ListView.getItems().remove(selec);
				STerm2ListView.getItems().add(selec);
				
				//add module credit 
				int getcurrentmc = Integer.parseInt(creditTerm2txt.getText());
				int changecredit = addmc + getcurrentmc;
				creditTerm2txt.setText(String.valueOf(changecredit));
			}
				
		}
  
	
		public Boolean selectedus1True() {
			Module selectedItem = USTerm1ListView.getSelectionModel().getSelectedItem();
			
			if (selectedItem != null) {
				return true;
			}
			return false;
			 }
		
		public Boolean selectedss1True() {
            Module selectedItem = STerm1ListView.getSelectionModel().getSelectedItem();
			
			if (selectedItem != null) {
				return true;
			}
			return false;
			 }
		
		public Boolean selectedus2True() {
            Module selectedItem = USTerm2ListView.getSelectionModel().getSelectedItem();
			
			if (selectedItem != null) {
				return true;
			}
			return false;
			 }
		
		
		public Boolean selectedss2True() {
            Module selectedItem = STerm2ListView.getSelectionModel().getSelectedItem();
			
			if (selectedItem != null) {
				return true;
			}
			return false;
			 }
		
//-------------------------------------Remove codes-----------------------------------------------	
	
		// Method allow controller to remove modules from selected and add to selected
		public void removeSelectedLVterm1(int removemc) {
			Module selectedItem = STerm1ListView.getSelectionModel().getSelectedItem();
			STerm1ListView.getItems().remove(selectedItem);
			USTerm1ListView.getItems().add(selectedItem);
			//remove module credit 
			int getcurrentmc = Integer.parseInt(creditTerm1txt.getText());
			int changecredit =  getcurrentmc - removemc ;
			creditTerm1txt.setText(String.valueOf(changecredit));
			 }
		
		
		public Module getselectedvaluefromselection1() {
			Module selectedItem = STerm1ListView.getSelectionModel().getSelectedItem();	
			return selectedItem;
			 }
		
         
		// Method allow controller to remove modules from selected and add to selected
		public void removeSelectedLVterm2(int removemc) {
					Module selectedItem = STerm2ListView.getSelectionModel().getSelectedItem();
					STerm2ListView.getItems().remove(selectedItem);
					USTerm2ListView.getItems().add(selectedItem);
					//remove module credit 
					int getcurrentmc = Integer.parseInt(creditTerm2txt.getText());
					int changecredit =  getcurrentmc - removemc ;
					creditTerm2txt.setText(String.valueOf(changecredit));
					 
					 }
				
		public Module getselectedvaluefromselection2() {
					Module selectedItem = STerm2ListView.getSelectionModel().getSelectedItem();	
					return selectedItem;
					 }		
				
	//-------------------------------------------------------------------------------
		
		// method to get all the items from List view 
		public Collection<Module> getTheSelectedModules() {
			Collection<Module> allitems = new ArrayList();
			
			ObservableList<Module> allItems1 = STerm1ListView.getItems();
			ObservableList<Module> allItems2 = STerm2ListView.getItems();
			ObservableList<Module> allItems3 = YLListView.getItems();
			
			allitems.addAll(allItems1);
			allitems.addAll(allItems2);
			allitems.addAll(allItems3);
			
			return allitems;
		}
		
		
	   // method to get credit from text box for validation
		public int getcreditterm1() {
			String value = creditTerm1txt.getText();
			int val = Integer.parseInt(value);
			return val;
		}
		
		public int getcreditterm2() {
			String value = creditTerm2txt.getText();
			int val = Integer.parseInt(value);
			return val;
		}
		
		
		
	     
}
