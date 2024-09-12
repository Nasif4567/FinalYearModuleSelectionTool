package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Course;
import model.RunPlan;
import model.Module;
import model.Name;
import model.StudentProfile;
import view.FinalYearOptionsRootPane;
import view.OverviewSelection;
import view.ReserveModulesPane;
import view.AboutUs;
import view.CreateSelectModulePane;
import view.CreateStudentProfilePane;
import view.FinalYearOptionsMenuBar;

public class FinalYearOptionsController {

	//fields to be used throughout class
	private FinalYearOptionsRootPane view;
	
	private StudentProfile Studentmodel;
	
	private CreateStudentProfilePane cspp;
	private CreateSelectModulePane csm;
	private ReserveModulesPane rm;
	private OverviewSelection os;
	
	private FinalYearOptionsMenuBar mstmb;
	
	
	private int rmtotalcredit1;
	private int rmtotalcredit2;
	
	
	private Collection<Module> AllModuleList;
	private Collection<Module> SelectedModuleList;
	
	 

	public FinalYearOptionsController(StudentProfile Studentmodel,  FinalYearOptionsRootPane view) {
		//initialise view and model fields
		this.view = view;
		this.Studentmodel = Studentmodel;
		
		//initialise view subcontainer fields
		cspp = view.getCreateStudentProfilePane();
		csm = view.getCreateSelectModulePane();
		rm = view.getReserveModulesPane();
		os = view.getOverviewSelection();
		
		mstmb = view.getModuleSelectionToolMenuBar();

		//add courses to combobox in create student profile pane using the buildModulesAndCourses helper method below
		cspp.addCourseDataToComboBox(buildModulesAndCourses());
		
		
		
		
		
		// Add modules 

		//attach event handlers to view using private helper method
		this.attachEventHandlers();
	}

	
	//helper method - used to attach event handlers
	private void attachEventHandlers() {
		//attach an event handler to the create student profile pane
		cspp.addCreateStudentProfileHandler(new CreateStudentProfileHandler());
		
		
		csm.addTerm1ModuleHandler(new addTerm1ModuleHandle());
		csm.addTerm2ModuleHandler(new addTerm2ModuleHandle());
		csm.removeTerm1ModuleHandler(new removeTerm1ModuleHandle() );
		csm.removeTerm2ModuleHandler(new removeTerm2ModuleHandle() );
		csm.submitHandler(new submitHandle());
		csm.ResetHandler(new resetHandle());
		
		
		rm.addTerm1RMHandler(new addTerm1RMHandle());
		rm.addTerm2RMHandler(new addTerm2RMHandle());
		rm.removeTerm1RMHandler(new removeTerm1RMHandle());
		rm.removeTerm2RMHandler(new removeTerm2RMHandle());
		rm.confirm1RMHandler(new confirmhandle1());
		rm.confirm2RMHandler(new confirmhandle2());
		
		
		os.SaveOverviewHandler(new SaveOverviewHandle());
		
		//attach an event handler to the menu bar that closes the application
		mstmb.addExitHandler(e -> System.exit(0));
		mstmb.addSaveHandler(new addSaveeHandle());
		mstmb.addLoadHandler(new addLoadHandle());
		mstmb.addAboutHandler(new AboutHandle() );
		
		
		
		
	}
	
	//event handler (currently empty), which can be used for creating a profile
	private class CreateStudentProfileHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			
			// 	Create Profile handler 
			
			// Get the data
			try {
				
			
			String Pnumber = cspp.getStudentPnumber();
			Name name = cspp.getStudentName();
			Course course = cspp.getSelectedCourse();
			String email = cspp.getStudentEmail();
			LocalDate datee = cspp.getStudentDate();
			
			
			
			
			// Validation
			
			if(Pnumber.equals("") || name.getFirstName().equals("") || name.getFamilyName().equals("") || course.equals("") || email.equals("") || datee.equals("")) {
				//output error
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Please input all the details");
				
			}
			
			else {
			// Set the data to data model 
			Studentmodel.setStudentPnumber(Pnumber);
			Studentmodel.setStudentName(name);
			Studentmodel.setStudentEmail(email);
			Studentmodel.setSubmissionDate(datee);
			Studentmodel.setStudentCourse(course);
			
			alertDialogBuilder(AlertType.INFORMATION, "Done", null, "Student profile created");
			}
			
//----------------------------------------------------------------------------------------			
			
		AllModuleList = Studentmodel.getStudentCourse().getAllModulesOnCourse();	
		loadModulesinCSM(AllModuleList);
			
			// next page
			view.changeTab(1);
			
			}catch(Exception ee) {
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Can't create student profile please check on the input details ");
			}
			
			
//---------------------------------------------------------------------------------------------			
			
			
		}
	}
	
	
	
	// ---------------------------------------Add and remove handlers--------------------------
	
		private class addTerm1ModuleHandle implements EventHandler<ActionEvent> {
			public void handle(ActionEvent e) {	
				try {
				LoadAddbuttnterm1(AllModuleList);
				}catch (Exception ee) {
					alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Can't add");
				}
			
			} }	
		
		
	//---------------------------------------------------------------------------------	
		private class addTerm2ModuleHandle implements EventHandler<ActionEvent> {
			public void handle(ActionEvent e) {
				try {
				LoadAddbuttnterm2(AllModuleList);
				}catch (Exception ee) {
					alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Can't add");
				}
			}
			}
	//--------------------------------------------------------------------------------------	
		private class removeTerm1ModuleHandle implements EventHandler<ActionEvent> {
			public void handle(ActionEvent e) {	
				try {
					Removebuttnterm1(AllModuleList);
				} catch (Exception ee) {
					alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Can't remove");
				}
				
			} }	
		
	//-----------------------------------------------------------------------------	
		private class removeTerm2ModuleHandle implements EventHandler<ActionEvent> {
			public void handle(ActionEvent e) {
				try {
				Removebuttnterm2(AllModuleList);
				} catch (Exception ee) {
					alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Can't remove");
				}
				
	          }	} 

		
		//---------------------------------------submit handler------------------------------------------------------		
		
				private class submitHandle implements EventHandler<ActionEvent> {
					public void handle(ActionEvent e) {	
						SelectedModuleList = Studentmodel.getAllSelectedModules();
						LoadSubmitButton(AllModuleList,SelectedModuleList);
						 // next page
			 			view.changeTab(2);
						
					}}
		//-------------------------------------Reset handler---------------------------------
				private class resetHandle implements EventHandler<ActionEvent> {
					public void handle(ActionEvent e) {
						try {
						loadModulesinCSM(AllModuleList);
						}catch(Exception ee) {
							alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Nothing to to reset");
						}
						
					}}
		//----------------
		
		
	//------------------------------------------------reserve module handler---------------------	
		
				private class addTerm1RMHandle implements EventHandler<ActionEvent> {
					public void handle(ActionEvent e) {	
						try {
							
						
						if (rmtotalcredit1 < 30) {
							rmtotalcredit1 += rm.addtoSelectedRMterm1();
						}	
						else {
							alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "You cannot reserve module more than 30 per term");
						}
					
						}catch(Exception ee) {
							alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Nothing to add");
						}
						
					}}
				
				private class addTerm2RMHandle implements EventHandler<ActionEvent> {
					public void handle(ActionEvent e) {	
						try {
						if (rmtotalcredit2 < 30) {
							rmtotalcredit2 += rm.addtoSelectedRMterm2();	;
						}	
						else {
							alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "You cannot reserve module more than 30 credit per term");
						}
						
						}catch(Exception ee) {
							alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Nothing to add");
						}
						
						
					}}
				
				
				private class removeTerm1RMHandle implements EventHandler<ActionEvent> {
					public void handle(ActionEvent e) {	
						
						if (rmtotalcredit1 > 0) {
							rmtotalcredit1 -= rm.removeSelectedRMterm1();
						}	
						else {
							alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "There is nothing to remove");
						}
							
					}}
				
				
				private class removeTerm2RMHandle implements EventHandler<ActionEvent> {
					public void handle(ActionEvent e) {	
						
						if (rmtotalcredit2 > 0) {
							rmtotalcredit2 -= rm.removeSelectedRMterm2();
						}	
						else {
							alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "There is nothing to remove");
						}
						
						
					}}
				
				
				private class confirmhandle1 implements EventHandler<ActionEvent> {
					public void handle(ActionEvent e) {
						for (Module m :rm.getTheReservedM1() ) {
							 Studentmodel.addReservedModule(m);
						}
						rm.openTerm2();
					}}		
				
				
				private class confirmhandle2 implements EventHandler<ActionEvent> {
					public void handle(ActionEvent e) {
						if (rm.getTheReservedM1().size() ==0 || rm.getTheReservedM2().size() ==0) {
							alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Add the modules first please");
						}
						else {
						for (Module m :rm.getTheReservedM2() ) {
							 Studentmodel.addReservedModule(m);
						}
						
						
						// Load data in overview profile
						os.LoadProfile(Studentmodel.getStudentName().getFullName(), Studentmodel.getStudentPnumber(), 
								Studentmodel.getStudentEmail(), Studentmodel.getSubmissionDate(), Studentmodel.getStudentCourse());
						
						// Load data in overview selected modules 
						for (Module m : Studentmodel.getAllSelectedModules()) {
							
							String mandatory = null;
							String delivery = null;
							
							if (m.isMandatory() == true) {
								mandatory = "Yes";
							}
							else {
								mandatory = "No";
							}
							
							if (m.getDelivery() == RunPlan.TERM_1) {
								delivery = "Term 1";
							}
							else {
								delivery = "Term 2";
							}
							
						os.LoadSelectedModules(m.getModuleCode(), m.getModuleName(), m.getModuleCredits(), mandatory, delivery);
						}
						
						
						// Load data in overview reserved modules 
						for (Module m : Studentmodel.getAllReservedModules()) {
							
							String mandatory = null;
							String delivery = null;
							
							if (m.isMandatory() == true) {
								mandatory = "Yes";
							}
							else {
								mandatory = "No";
							}
							
							if (m.getDelivery() == RunPlan.TERM_1) {
								delivery = "Term 1";
							}
							else {
								delivery = "Term 2";
							}
							
						os.LoadReservedModules(m.getModuleCode(), m.getModuleName(), m.getModuleCredits(), mandatory, delivery);
						}
						
						
						// next page
						view.changeTab(3);
						
						}
						
					}}	
				
				
	//-----------------------------------------------------save over view handle--------
				
				private class SaveOverviewHandle implements EventHandler<ActionEvent> {
					public void handle(ActionEvent e) {
						
						String SPnumber = Studentmodel.getStudentPnumber();
						String Sfn = Studentmodel.getStudentName().getFirstName();
						String Sln = Studentmodel.getStudentName().getFamilyName();
						String Semail = Studentmodel.getStudentEmail();
						LocalDate Sdate = Studentmodel.getSubmissionDate();
						Course Scourse = Studentmodel.getStudentCourse();
						
						
						PrintWriter out = null;
					
						
						try {
							out = new PrintWriter(new FileWriter("Student.txt", true));
				
						} catch (IOException e2) {
							
							e2.printStackTrace();
						}
					
						
						
						
						//Validation for check is the student
						
						if(SPnumber.equals("") || Sfn.equals("") || Sln.equals("") || Semail.equals("") || Sdate.toString().equals("") || Scourse.toString().equals("")) {
							//output error
							alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "No student data to enter , please register student");
							
						}
						
						else {
							

							// Write the files in the text file for profile
								out.println("Name: "+Studentmodel.getStudentName().getFullName()+ " " + "Pnumber: " + Studentmodel.getStudentPnumber() + " " + "Email: "+ Studentmodel.getStudentEmail() + " "+ "Date: " + Studentmodel.getSubmissionDate() +" " +"Course: "+ Studentmodel.getStudentCourse());
								
							//------------------------------------------------------------------------------------------------
								
								for (Module m : Studentmodel.getAllSelectedModules()) {	
									
								// Write selected modules in text files
									out.println("Selected_Module_code: "+m.getModuleCode()+ " " + "Selected_Module_name: " + m.getModuleName() + " " + "Selected_module_credit: "+ m.getModuleCredits() + " "+ "Selected_Module_Mandatory: " + m.isMandatory() +" " +"Selected_Module_Delivery: "+ m.getDelivery());
								
								}
	
								for (Module m : Studentmodel.getAllReservedModules()) {
								
									//Write reserved modules in text files
									out.println("Reserved_Module_code: "+m.getModuleCode()+ " " + "Reserved_Module_name: " + m.getModuleName() + " " + "Reserved_module_credit: "+ m.getModuleCredits() + " "+ "Reserved_Module_Mandatory: " + m.isMandatory() +" " +"Reserved_Module_Delivery: "+ m.getDelivery());
									
										
								}
								
								
								
								out.close();
								
											
								
								//-------------------------------------------------------------------------------
								
								// Alert box to show it is successfull
								alertDialogBuilder(AlertType.INFORMATION, "Done", null, "Student data saved");
								
								
						}	
							
				
					}
				
				}//------------------------------------end of code
				
				
					
				//------------------------------------------------Save handler and load  -------------------------------	
					
					
					private class addSaveeHandle implements EventHandler<ActionEvent> {
						public void handle(ActionEvent e) {
							
							if ( !Studentmodel.getStudentName().getFullName().equals("") || !Studentmodel.getStudentPnumber().equals("") || Studentmodel.getAllSelectedModules().size() !=0 || Studentmodel.getAllReservedModules().size() !=0) {
							    try {
							        FileOutputStream fileOut = new FileOutputStream("StudentSaved.txt");
							        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
							        objectOut.writeObject(Studentmodel);
							        objectOut.close();
							        fileOut.close();
							        alertDialogBuilder(AlertType.INFORMATION, "Done", null, "The student profile is saved");
							    } catch (Exception ex) {
							        ex.printStackTrace();
							    }
							    
							
							} else {
								alertDialogBuilder(AlertType.ERROR, "Done", null, "Nothing to save because student details or modules are not inputted properly");
							}
								
					
						}
							
					}
//---------------------------------------------------------------------
					private class addLoadHandle implements EventHandler<ActionEvent> {
						public void handle(ActionEvent e) {
							
							File file = new File("StudentSaved.txt");

							if(file.exists()) {
							
							try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("StudentSaved.txt"))) {
							    StudentProfile studentProfile = (StudentProfile) in.readObject();
							    
							    cspp.setStudentPnumbertxt(studentProfile.getStudentPnumber());
							    cspp.setStudentfNametxt(studentProfile.getStudentName().getFirstName());
							    cspp.setStudentlNametxt(studentProfile.getStudentName().getFamilyName());
							    cspp.setStudentlEmail(studentProfile.getStudentEmail());
							    cspp.setStudentDate(studentProfile.getSubmissionDate());
							    
							    
							    int index;
							    if (studentProfile.getStudentCourse().equals("Computer Science"))
							    	
							    {
							    	index = 1;
							    }
							    
							    else {
							    	index = 0;
							    }
							    cspp.setCoursetxt(index);
							    
							    //----------------------------load selected modules page------------
							    loadModulesinCSM(studentProfile.getStudentCourse().getAllModulesOnCourse());
							    AllModuleList = studentProfile.getStudentCourse().getAllModulesOnCourse();
							    SelectedModuleList = studentProfile.getAllSelectedModules();
							    
							    
							    Collection<Module> loadms1 = new ArrayList();;
							    Collection<Module> loadms2 = new ArrayList();;
							    Collection<Module> yearlong = new ArrayList();;
							    for (Module m : studentProfile.getAllSelectedModules()) {
							    	if (m.getDelivery() == RunPlan.TERM_1) {
							    		loadms1.add(m);
							    	}
							    	else {
							    		
							    		if (m.getDelivery() == RunPlan.YEAR_LONG) {
							    			yearlong.add(m);
							    		}
							    		else {
							    			loadms2.add(m);
							    		}
							    		
							    	}
							    }
							    
							    // the modules in view 
							    csm.setLoadSL1(loadms1,yearlong);
							    csm.setLoadSL2(loadms2);
							//-----------------------------------------Reserved module load----------------------------------------------    
							    
							  // calling the load reserved page unselect and selected module of the student
							    LoadSubmitButton(AllModuleList,SelectedModuleList);
							    rm.LoadRMSmodules(loadms1,loadms2);
							    
							//Load selected reserved and and profile deatils in Overview 
							    os.LoadProfile(studentProfile.getStudentName().getFullName(), studentProfile.getStudentPnumber(), studentProfile.getStudentEmail(), studentProfile.getSubmissionDate(), studentProfile.getStudentCourse());
							    
							    for (Module m : studentProfile.getAllSelectedModules()) {
							    	os.LoadSelectedModules(m.getModuleCode(), m.getModuleName(), m.getModuleCredits(), String.valueOf(m.isMandatory()), String.valueOf(m.getDelivery()));
							    }
							    
							    for (Module m : studentProfile.getAllReservedModules()) {
							    	os.LoadReservedModules(m.getModuleCode(), m.getModuleName(), m.getModuleCredits(), String.valueOf(m.isMandatory()), String.valueOf(m.getDelivery()));
							    }
							    
							    
							    
							    in.close();
					
							       
							    
							} catch (IOException | ClassNotFoundException e1) {
							    e1.printStackTrace();
							}
							
							}//------------------------------------end of if
							
							
							else {
								alertDialogBuilder(AlertType.ERROR, "Error", null, "No data is saved so can't load anything");
							}
									
							
						}	
								
					
						}
					
					
					
					
			//-----------------------------------------------about handle ----------------
					
					private class AboutHandle implements EventHandler<ActionEvent> {
						public void handle(ActionEvent e) {	
						AboutUs au=	new AboutUs();		
						}	
								
					
						}
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
				
				
		
		
		

//-------------------------------------------------------------------------------------
	//helper method - builds modules and course data and returns courses within an array
	private Course[] buildModulesAndCourses() {
		Module imat3423 = new Module("IMAT3423", "Systems Building: Methods", 15, true, RunPlan.TERM_1);
		Module ctec3451 = new Module("CTEC3451", "Development Project", 30, true, RunPlan.YEAR_LONG);
		Module ctec3902_SoftEng = new Module("CTEC3902", "Rigorous Systems", 15, true, RunPlan.TERM_2);	
		Module ctec3902_CompSci = new Module("CTEC3902", "Rigorous Systems", 15, false, RunPlan.TERM_2);
		Module ctec3110 = new Module("CTEC3110", "Secure Web Application Development", 15, false, RunPlan.TERM_1);
		Module ctec3605 = new Module("CTEC3605", "Multi-service Networks 1", 15, false, RunPlan.TERM_1);	
		Module ctec3606 = new Module("CTEC3606", "Multi-service Networks 2", 15, false, RunPlan.TERM_2);	
		Module ctec3410 = new Module("CTEC3410", "Web Application Penetration Testing", 15, false, RunPlan.TERM_2);
		Module ctec3904 = new Module("CTEC3904", "Functional Software Development", 15, false, RunPlan.TERM_2);
		Module ctec3905 = new Module("CTEC3905", "Front-End Web Development", 15, false, RunPlan.TERM_2);
		Module ctec3906 = new Module("CTEC3906", "Interaction Design", 15, false, RunPlan.TERM_1);
		Module ctec3911 = new Module("CTEC3911", "Mobile Application Development", 15, false, RunPlan.TERM_1);
		Module imat3410 = new Module("IMAT3104", "Database Management and Programming", 15, false, RunPlan.TERM_2);
		Module imat3406 = new Module("IMAT3406", "Fuzzy Logic and Knowledge Based Systems", 15, false, RunPlan.TERM_1);
		Module imat3611 = new Module("IMAT3611", "Computer Ethics and Privacy", 15, false, RunPlan.TERM_1);
		Module imat3613 = new Module("IMAT3613", "Data Mining", 15, false, RunPlan.TERM_1);
		Module imat3614 = new Module("IMAT3614", "Big Data and Business Models", 15, false, RunPlan.TERM_2);
		Module imat3428_CompSci = new Module("IMAT3428", "Information Technology Services Practice", 15, false, RunPlan.TERM_2);


		Course compSci = new Course("Computer Science");
		compSci.addModuleToCourse(imat3423);
		compSci.addModuleToCourse(ctec3451);
		compSci.addModuleToCourse(ctec3902_CompSci);
		compSci.addModuleToCourse(ctec3110);
		compSci.addModuleToCourse(ctec3605);
		compSci.addModuleToCourse(ctec3606);
		compSci.addModuleToCourse(ctec3410);
		compSci.addModuleToCourse(ctec3904);
		compSci.addModuleToCourse(ctec3905);
		compSci.addModuleToCourse(ctec3906);
		compSci.addModuleToCourse(ctec3911);
		compSci.addModuleToCourse(imat3410);
		compSci.addModuleToCourse(imat3406);
		compSci.addModuleToCourse(imat3611);
		compSci.addModuleToCourse(imat3613);
		compSci.addModuleToCourse(imat3614);
		compSci.addModuleToCourse(imat3428_CompSci);

		Course softEng = new Course("Software Engineering");
		softEng.addModuleToCourse(imat3423);
		softEng.addModuleToCourse(ctec3451);
		softEng.addModuleToCourse(ctec3902_SoftEng);
		softEng.addModuleToCourse(ctec3110);
		softEng.addModuleToCourse(ctec3605);
		softEng.addModuleToCourse(ctec3606);
		softEng.addModuleToCourse(ctec3410);
		softEng.addModuleToCourse(ctec3904);
		softEng.addModuleToCourse(ctec3905);
		softEng.addModuleToCourse(ctec3906);
		softEng.addModuleToCourse(ctec3911);
		softEng.addModuleToCourse(imat3410);
		softEng.addModuleToCourse(imat3406);
		softEng.addModuleToCourse(imat3611);
		softEng.addModuleToCourse(imat3613);
		softEng.addModuleToCourse(imat3614);

		Course[] courses = new Course[2];
		courses[0] = compSci;
		courses[1] = softEng;

		return courses;
	}
	
	
	
	//helper method to build dialogs
		private void alertDialogBuilder(AlertType type, String title, String header, String content) {
			Alert alert = new Alert(type);
			alert.setTitle(title);
			alert.setHeaderText(header);
			alert.setContentText(content);
			alert.showAndWait();
		}
		
		
		
		
	//Important Methods used here	----------------------------------------------------------
		
	// Unselected and selected module load methods for Select modules-----------------------------------
		public void loadModulesinCSM(Collection<Module> mc) {
			// add modules to list view unselected term 1 
						Collection<Module> term1us = new ArrayList<>();		
						for (Module m : mc) {
							if (m.getDelivery() == RunPlan.TERM_1 && m.isMandatory() == false ) {
								term1us.add(m);			
							}
						}
						
						
						// add modules to list view unselected term 2
						Collection<Module> term2us = new ArrayList<>();		
						for (Module m : mc) {
							if (m.getDelivery() == RunPlan.TERM_2 && m.isMandatory() == false ) {
								term2us.add(m);		
							}
						}
						
						
						// add manadatory modules to list view selected term 1
						Collection<Module> term1s = new ArrayList<>();
						
						int modulecreditmandterm1 = 0;
						for (Module m : mc) {
							if (m.getDelivery() == RunPlan.TERM_1 && m.isMandatory() == true ) {
								term1s.add(m);
								modulecreditmandterm1 = m.getModuleCredits();
								System.out.println(modulecreditmandterm1);
							
							}
						}			
						
						int modulecreditmandterm2 = 0;
						// add manadatory modules to list view selected term 2
						Collection<Module> term2s = new ArrayList<>();		
						for (Module m : mc) {
							if (m.getDelivery() == RunPlan.TERM_2 && m.isMandatory() == true ) {
								term2s.add(m);	
								modulecreditmandterm2 = m.getModuleCredits();
								
								
							}
						}				
						
						
						// add year long modules 
									Collection<Module> termyl = new ArrayList<>();	
									int yearlongcredit = 0;
									for (Module m : mc) {
										if (m.getDelivery() == RunPlan.YEAR_LONG && m.isMandatory() == true ) {
											termyl.add(m);	
											yearlongcredit = m.getModuleCredits();
											
											
										}
									}	
									
									
						// Method to load 
						csm.LoadTermsModules(term1us,term2us, term1s, term2s,termyl,modulecreditmandterm1,yearlongcredit,modulecreditmandterm2);
		}
		
		
		
		
		
		//add methods load term 1-----------------------------------------------------------------
		public void LoadAddbuttnterm1(Collection<Module> mc) {
			
			int modulecreditterm1add = 0;	
			
			// get all the 15 credit module credits
			for (Module m : mc) {
				if ( m.getDelivery() == RunPlan.TERM_1 && m.isMandatory() == false ) {
					modulecreditterm1add = m.getModuleCredits();			 
				}
			}
		
	    
		
		if (csm.getcreditterm1() <60 && csm.selectedus1True() == true )	{
			// to selected with module increment		
			csm.addtoSelectedLVterm1(modulecreditterm1add);	
			
			
			
		} 
		else {
			
			if (csm.selectedus1True() == false) {
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Please select a module");
			}
			
			if (csm.getcreditterm1() == 60) {
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Highest credit per term is 60");
			}
				
		}
		}
		
		
		// add methods term 2 ----------------------------------------------------------------------------------------------
		public void LoadAddbuttnterm2(Collection<Module> mc) {
			
			int modulecreditterm2add = 0;	
			
			// get all the 15 credit module credits
			for (Module m : mc) {
				if ( m.getDelivery() == RunPlan.TERM_2 && m.isMandatory() == false ) {
					modulecreditterm2add = m.getModuleCredits();			 
				}
			}
		

		
			if (csm.getcreditterm2() <60 && csm.selectedus2True() == true )	{
				// to selected with module increment		
				csm.addtoSelectedLVterm2(modulecreditterm2add);	
				
				
				
			} 
			else {
				
				if (csm.selectedus2True() == false) {
					alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Please select a module");
				}
				
				if (csm.getcreditterm2() == 60) {
					alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Highest credit per term is 60");
				}
					
			}
		}
		
		
		//remove button ---------------------------------------------------------------------------------
		public void Removebuttnterm1(Collection<Module> mc) {

			Module mod= null;
			int modulecreditremove1 =0;
			// get all the 15 credit module credits also get the madatory module to not be removed
			for (Module m : mc) {
				if ( m.getDelivery() == RunPlan.TERM_1 && m.isMandatory() == true ) {
					mod = m	;
					//get the madatory module name 
				}
				
				else {
					modulecreditremove1 = m.getModuleCredits();
				}
			}
			
			// remove module credit
			
			//need to be stuck at 0
			
			
			if (csm.getselectedvaluefromselection1().equals(mod)) {
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "You cannot remove manadatory modules");
			} 
			
			//------------------------------------------------
			else {
				
					csm.removeSelectedLVterm1(modulecreditremove1);
				
				
			} 
		}
		
		
		
		
		public void Removebuttnterm2(Collection<Module> mc) {
			
			Module mod= null;
			int modulecreditremove2 =0;
			// get all the 15 credit module credits
			for (Module m : mc) {
				if ( m.getDelivery() == RunPlan.TERM_2 && m.isMandatory() == true ) {
					mod = m	;
					//get the madatory module name 
				}
				else {
					modulecreditremove2 = m.getModuleCredits();
				}
			}
			
			
			
			if (csm.getselectedvaluefromselection2().equals(mod)) {
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "You cannot remove manadatory modules");
			} 
			
			//------------------------------------------------
			else {
				csm.removeSelectedLVterm2(modulecreditremove2);
			} 
		}
		//------------------------------------------------------------------------------
		// submit 
		
		public void LoadSubmitButton(Collection<Module> mc,Collection<Module> mc1 ) {
			try {
				
				
				for (Module m : csm.getTheSelectedModules()) {
					Studentmodel.addSelectedModule(m);	
				}
				
				
				Collection<Module> unselectedm = mc;
				Collection<Module> USTerm1rm = new ArrayList();
				Collection<Module> USTerm2rm = new ArrayList();
				
				unselectedm.removeAll(mc1);
				
             for (Module m : unselectedm ) {
            	 if(m.getDelivery() == RunPlan.TERM_1 && m.isMandatory()==false) {
            		 USTerm1rm.add(m);
            	 }
             }
             
             
             for (Module m1 : unselectedm ) {
            	 if(m1.getDelivery() == RunPlan.TERM_2 && m1.isMandatory()==false) {
            		 USTerm2rm.add(m1);
            	 }
	
             }
             
             // Load data in the list view 
             rm.LoadRMUSmodules(USTerm1rm, USTerm2rm);
             
           
				}
				
				
				
				catch (Exception e1 ) {
					alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, e1.getMessage());
				}
		}
		
		

}
