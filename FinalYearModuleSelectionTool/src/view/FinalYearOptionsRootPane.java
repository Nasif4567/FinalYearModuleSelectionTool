package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;


public class FinalYearOptionsRootPane extends BorderPane {

	private CreateStudentProfilePane cspp;
	private CreateSelectModulePane csmp;
	private ReserveModulesPane rm;
	private OverviewSelection os;
	private FinalYearOptionsMenuBar mstmb;
	private TabPane tp;
	
	public FinalYearOptionsRootPane() {
		//create tab pane and disable tabs from being closed
		tp = new TabPane();
		tp.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		//create panes
		cspp = new CreateStudentProfilePane();
		csmp = new CreateSelectModulePane();
		rm = new ReserveModulesPane();
		os = new OverviewSelection();
		
		
		//create tabs with panes added
		Tab t1 = new Tab("Create Profile", cspp);
		Tab t2 = new Tab("Select Modules", csmp);
		Tab t3 = new Tab("Reserve Modules", rm);
		Tab t4 = new Tab("Overview Selection", os);
		
		
		//add tabs to tab pane
		tp.getTabs().addAll(t1,t2,t3, t4);
		
		//create menu bar
		mstmb = new FinalYearOptionsMenuBar();
		
		//add menu bar and tab pane to this root pane
		this.setTop(mstmb);
		this.setCenter(tp);
		
	}

	//methods allowing sub-containers to be accessed by the controller.
	public CreateStudentProfilePane getCreateStudentProfilePane() {
		return cspp;
	}
	
	public CreateSelectModulePane getCreateSelectModulePane() {
		return csmp;
	}
	
	public ReserveModulesPane getReserveModulesPane() {
		return rm;
	}
	
	public OverviewSelection getOverviewSelection() {
		return os;
	}
	
	public FinalYearOptionsMenuBar getModuleSelectionToolMenuBar() {
		return mstmb;
	}
	
	//method to allow the controller to change tabs
	public void changeTab(int index) {
		tp.getSelectionModel().select(index);
	}
}
