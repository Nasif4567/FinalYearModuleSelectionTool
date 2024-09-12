package view;

import java.util.ArrayList;
import java.util.Collection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Module;

public class ReserveModulesPane extends GridPane {
     
	private Accordion acord;
	
	private TitledPane pane1;
	private TitledPane pane2;
	
	private ListView<Module> Term1Us;
	private ListView<Module> Term2Us;
	private ListView<Module> Term1s;
	private ListView<Module> Term2s;
	
	private Button AddTerm1;
	private Button RemoveTerm1;
	private Button ConfirmTerm1;
	private Button AddTerm2;
	private Button RemoveTerm2;
	private Button ConfirmTerm2;
	
	
	private HBox HboxTerm1;
	private HBox HboxTerm2;
	private HBox HboxTerm1btn;
	private HBox HboxTerm2btn;
	private VBox VBoxAll1;
	private VBox VBoxAll2;
	
	
	
	
	
public ReserveModulesPane(){
		
		
		//styling
		this.setVgap(15);
		this.setHgap(10);
		this.setPadding(new Insets(20));
	    this.setAlignment(Pos.CENTER);
	    
	    
	    ColumnConstraints column0 = new ColumnConstraints();
		column0.setHalignment(HPos.CENTER);
	    
		// Accord
	    acord = new Accordion();
	    
	    // The list view 
	    Term1Us = new ListView();
	    Term2Us = new ListView();
	    Term1s = new ListView();
	    Term2s = new ListView();
	    
	    // The button 
	    AddTerm1 = new Button("Add");
	    RemoveTerm1 = new Button("Remove");
	    ConfirmTerm1 = new Button("Confirm");
	    AddTerm2 = new Button("Add");
	    RemoveTerm2 = new Button("Remove");
	    ConfirmTerm2 = new Button("Confirm");
	    
	    

	    HboxTerm1 = new HBox(new VBox(new Label("Unselected Term 1 modules"),Term1Us )  ,   new VBox(new Label("Selected Term 1 modules"),Term1s ) );
	    HboxTerm1.setSpacing(10);
	    
	    
	    HboxTerm2 = new HBox(new VBox(new Label("Unselected Term 2 modules"),Term2Us )  ,   new VBox(new Label("Selected Term 2 modules"),Term2s ) );
	    HboxTerm2.setSpacing(10);
	    
	    
	    HboxTerm1btn = new HBox(new Label("Reserve 30 credit worth of Term 1 module"),AddTerm1,RemoveTerm1,ConfirmTerm1);
	    HboxTerm1btn.setSpacing(10);
	    HboxTerm2btn = new HBox(new Label("Reserve 30 credit worth of Term 2 module"),AddTerm2,RemoveTerm2,ConfirmTerm2);
	    HboxTerm2btn.setSpacing(10);
	    
	    
	    VBoxAll1 = new VBox(HboxTerm1, HboxTerm1btn);
	    VBoxAll1.setSpacing(10);
	    VBoxAll2 = new VBox(HboxTerm2,HboxTerm2btn);
	    VBoxAll2.setSpacing(10);
	    
	    pane1 = new TitledPane("Term 1", VBoxAll1);
	    pane2 = new TitledPane("Term 2",VBoxAll2);
	    
	    acord.getPanes().addAll(pane1,pane2);
	    
	    this.add(acord,0,1);
	    
	
}


// method

public void LoadRMUSmodules(Collection<Module> uSTerm1 ,Collection<Module> uSterm2) {
	ObservableList<Module> item1 = FXCollections.observableArrayList(uSTerm1);
	ObservableList<Module> item2 = FXCollections.observableArrayList(uSterm2);
	
	Term1Us.setItems(item1);
	Term2Us.setItems(item2);
	
}



// Handler methods 
public void addTerm1RMHandler(EventHandler<ActionEvent> handler) {
	AddTerm1.setOnAction(handler);
}
 
public void addTerm2RMHandler(EventHandler<ActionEvent> handler) {
	AddTerm2.setOnAction(handler);
}

public void removeTerm1RMHandler(EventHandler<ActionEvent> handler) {
	RemoveTerm1.setOnAction(handler);
}

public void removeTerm2RMHandler(EventHandler<ActionEvent> handler) {
	RemoveTerm2.setOnAction(handler);
}

public void confirm1RMHandler(EventHandler<ActionEvent> handler) {
	ConfirmTerm1.setOnAction(handler);
}
public void confirm2RMHandler(EventHandler<ActionEvent> handler) {
	ConfirmTerm2.setOnAction(handler);
}



//Method allow controller to add modules to selected from unselected


	public int addtoSelectedRMterm1() {
		Module selec = Term1Us.getSelectionModel().getSelectedItem();
		if (selec != null) {
		Term1Us.getItems().remove(selec);
		Term1s.getItems().add(selec); 
	    
		}
		return selec.getModuleCredits();	
		 } 
	
	

	public int addtoSelectedRMterm2() {
		Module selec = Term2Us.getSelectionModel().getSelectedItem();
		if (selec != null) {
		Term2Us.getItems().remove(selec);
		Term2s.getItems().add(selec); 
	
		}	
		
		return selec.getModuleCredits();
		 } 
	
	// Method allow controller to remove modules from selected and add to selected
	public int removeSelectedRMterm1() {
		Module selectedItem = Term1s.getSelectionModel().getSelectedItem();
		Term1s.getItems().remove(selectedItem);
		Term1Us.getItems().add(selectedItem);
		
		return selectedItem.getModuleCredits();
				 }
			
	public int removeSelectedRMterm2() {
		Module selectedItem = Term2s.getSelectionModel().getSelectedItem();
	    Term2s.getItems().remove(selectedItem);
		Term2Us.getItems().add(selectedItem);
		
		return selectedItem.getModuleCredits();
		
				 }
	
	public Collection<Module> getTheReservedM1() {
		ObservableList<Module> allItems1 = Term1s.getItems();	
		return allItems1;
	}
	
	public Collection<Module> getTheReservedM2() {
		ObservableList<Module> allItems2 = Term2s.getItems();
		return allItems2;
	}
	
	//expand 2 term accodion
	public void openTerm2() {
		pane2.setExpanded(true);
	}
	
	
	// Load methods used in the load 
	public void LoadRMSmodules(Collection<Module> STerm1 ,Collection<Module> Sterm2) {
		ObservableList<Module> item1 = FXCollections.observableArrayList(STerm1);
		ObservableList<Module> item2 = FXCollections.observableArrayList(Sterm2);
		
		Term1s.setItems(item1);
		Term2s.setItems(item2);
		
	}
	
}
