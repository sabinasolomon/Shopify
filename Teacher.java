import java.util.Scanner;
import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;
import java.lang.*;
import java.util.EventObject;


class Teacher
{
  String tfname;  //teacher first name
  String tlname;  //teacher last name
  String title;   //MR, MS, MRS, PROF or DR
  int numOfClasses;   //holds the NUMBER of courses
  String [] subjects; //hold class/subject NAME
  int []sizes ;       //holds the ACTUAL size of the class
  Student [][] classroom ;  //2d array  --> WILL BE SET LATER -> number of classes and the POTENTIAL class size --> max size is 28
  
  
  
  public Teacher()
  {
    JFrame makingTeacher = new JFrame("Teacher Profile");
    makingTeacher.setSize(600, 500);
    
    JLabel m0 = new JLabel ("Enter your FIRST Name");  //message 0
    JLabel m1 = new JLabel ("Enter your LAST Name"); //message 0.5
    
   JTextField name1 = new JTextField(tfname);
   JTextField name2 = new JTextField(tlname);
   JBox.setSize(name1, 300, 50); //setting sizes of  boxes
   JBox.setSize(name2, 300, 50); //setting sizes of  boxes
   
   
   JLabel m2 = new JLabel ("How many classes do you teach?:");
   JSpinner numOfC = new JSpinner (new SpinnerNumberModel( 1,  1,  10,  1));
   JBox.setSize(numOfC, 50, 30);

   String []answers = {"Mr. ", "Mrs. ", "Ms. ", "Dr. " , "Prof. "};
   JSpinner  titles = new JSpinner (new SpinnerListModel(answers))  ;
   JBox.setSize(titles, 70, 30);

    JButton done = new JButton ("DONE");
    done.setBackground(Color.GRAY);
    done.setOpaque(true);
    JBox.setSize(done, 70, 50);

     JBox getInfo = JBox.vbox(
                             
                                JBox.hbox( JBox.vglue(),JBox.hglue() , m0 ,JBox.hglue() ,JBox.hglue() ),
                                JBox.hbox(   JBox.hglue(), titles, JBox.hglue(), name1 , JBox.hglue()),  //hglue or vglue evenly spaces things out EVENLY IN SPACE ITS GIVEN                                
                                JBox.hbox(JBox.hglue() , m1, JBox.hglue(), JBox.hglue() ),
                                JBox.hbox( JBox.hglue(),JBox.hglue(),JBox.hglue(), JBox.hglue(),JBox.hglue(), JBox.hglue(),name2 , JBox.hglue() , JBox.hglue() ),
                                JBox.hbox( JBox.vglue(), JBox.hglue(),m2,numOfC ,  JBox.hglue()),
                                JBox.hbox( JBox.vglue() , JBox.hglue(), done ,JBox.hglue() )
                               );
                               
    makingTeacher.add(getInfo);
    makingTeacher.setVisible(true);
   
    JEventQueue events = new JEventQueue ();
    events.listenTo(done, "done");    //checking if bottom done has been pressed
    boolean clicked = false;
    while ( clicked == false )
    {
      EventObject event = events.waitEvent();
      String whatHappened = events.getName(event);
     
      if(whatHappened.equals ("done")) //if click done
      {
        tfname = name1.getText ();  //gets 1st name from text box
        tlname = name2.getText ();  //gets last name from text box
        numOfClasses = (Integer) numOfC.getValue();
        subjects = new String [numOfClasses];
        sizes = new int [numOfClasses];
        classroom = new Student [numOfClasses][28]; //max of 28 students per class
        title = (String) titles.getValue();
        clicked = true;
      }  
     
    }
   makingTeacher.setVisible(false);
  }
  
  public void createClass ()
  {
    //--------------------------------SETTING UP CLASSROOM--------------------------
    for (int i=0; i<numOfClasses; i++)
    {
    JFrame setup = new JFrame ("");
    setup.setSize(400,300);
    JLabel classNum = new JLabel ("Setting up class #: " + (i+1));
    JLabel i1 = new JLabel ("Enter course name:");  //instructions 1
    JTextField getsub = new JTextField ("");
    JSpinner getsize = new JSpinner  (new SpinnerNumberModel( 1,  1,  28,  1)); //in loop so it reserts. Spinner allows user to pick quatities. Automatically set to 1. User can pick number between 1 and 28. Each 'step' increases by 1.
    JLabel i2 = new JLabel ("How many students are there in your class:");  //instructions 1    
    JButton done = new JButton ("CLICK WHEN DONE");
    done.setBackground(Color.GREEN);        //making button green
    done.setOpaque(true);  
    JBox.setSize(done, 150, 50);             //setting up the size of the butto
    JBox.setSize (getsize, 50,30);
    JBox.setSize (getsub, 300, 30);
    JBox buttonGrid = JBox.vbox(
                                JBox.hbox( JBox.vglue(), JBox.hglue(), classNum, JBox.hglue() ),
                                JBox.hbox(JBox.hglue (), i1, getsub, JBox.hglue ()),  //hglue or vglue evenly spaces things out EVENLY IN SPACE ITS GIVEN                                
                                JBox.hbox(  JBox.vglue ()),
                                JBox.hbox(JBox.hglue (), i2, getsize, JBox.hglue ()),
                                JBox.hbox( JBox.hglue(), JBox.hglue () ,JBox.hglue (), done),
                                JBox.hbox(  JBox.vglue ())
                               );
    setup.add(buttonGrid);  
    setup.setVisible(true);
  
      JEventQueue makingList = new JEventQueue ();
      makingList.listenTo (done, "done");  //checking if bottom enter has been pressed
      
      //   boolean clicked = false;
      // while (clicked == false)--> not nessacary since we re only listening to 1 thing
      
        EventObject event = makingList.waitEvent();
        String whatHappened = makingList.getName(event);
        if(whatHappened.equals ("done")) //if click enter
        {        
          sizes[i] = (Integer) getsize.getValue();   //get an int value from the spinner  
          subjects[i] = getsub.getText ();  //gets 1st name from text box
          getsub.setText(""); 
          setup.repaint();        //refreshes frame
          setup.setVisible(false);
          //      clicked = true;
        }
        // }//end of while
        
        for(int j = 0 ; j < (classroom[i].length) ; j++)//goes through each student
        {
          this.classroom[i][j] = new Student ();      
        }
        for(int j = 0 ; j<sizes[i] ; j++)//goes through each student
        {
          this.classroom[i][j].modifyInfo();    
        }  
      
    } //end of for that increasing i to go through each class/////////////////////////////
     
    sortClasses();
  }
  
  
  public void sortClasses()
  {    
    for (int classnum = 0; classnum < numOfClasses; classnum++)
    {                                               //goes through the 3 classes
      for (int i = 0; i < sizes[classnum]; i++)
      {
        for (int j = i+1; j < sizes[classnum] ; j++)
        {
          if (classroom[classnum][i].lname.compareToIgnoreCase(classroom[classnum][j].lname)>0)
          {
            Student temp = classroom[classnum][i];
            classroom[classnum][i] = classroom[classnum][j];
            classroom[classnum][j] = temp;
          }
        }
      }
    }
  }
  

  
  
  public static void main (String args [])
  {
    Teacher user  = new Teacher(); 
    Scanner input = new Scanner (System.in);    
    
    user.createClass();  //creates the teachers 3 courses
    boolean exit =false;
    while(exit ==false)////will loop util user asks to exit
    {
      ///________________________CHOSING WHICH CLASS________________-
      int choice = 0;      //this is  used for the teacher to choose which class they want to look at (classroom[THIS][])
      String decision ="";
      boolean valid = false;
      while (!valid )   //while not valid (while its false)  --> for error detection
      {
        System.out.println ("Which class would you like to access?");
        for (int i =0; i<user.numOfClasses; i++)
        {
           System.out.println ("Enter " + (i+1) + " for " + user.subjects[i]);
        }
        System.out.println ("Or 'exit' to leave program");

        decision=input.next();                       //tacken in as String (to prevent program from breaking if user enters a character
        try { choice = Integer.parseInt(decision); } //trying to convert to int
        catch (NumberFormatException e){}            //if cant be int      
      
        if(choice >=1 && choice<=user.numOfClasses)
        {
          valid =true;
          choice --; //because array starts at 0
        }      
        if (decision.equals("exit"))
        {
          System.out.println("You have been loged out");
          exit = true;
          valid = true;
        }
        
      }   //end of while ! valid
      //________________________END of CHOSING WHICH CLASS________________-
      
      //////////////////////This is the "MAIN MENU"//////////////////////////////////////////  
      
      int findStu = 0;   //used to determine which student we are looking for specically within the different classes (classroom[][THIS])
      boolean back = false;
      while (!back && !exit)   //while back and  exit false  --> so theres a back and exit feuture
      {
        System.out.println("\n\nStudents in " + user.title  +user.tlname +" " + user.tlname + "'s " + user.subjects[choice]+ " class \n" );
        
        for (int i = 0 ; i <user.sizes[choice] ; i++)  //printing out all studens
        {
          System.out.println( (i+1) + ".    " +user.classroom[choice][i].lname + ",  " + user.classroom[choice][i].fname);
        }
                
        System.out.println ("Enter 1 - " + user.sizes[choice]+ " to view student profile, enter 'add' to add a new student to your class, enter 'search' to search for a student, enter 'remove' to remove a student from your class, enter 'modify' to edit any student info, or enter 'back' to go back to go back.");
        decision=input.next();  //first tacken in as String 
        try { findStu = Integer.parseInt(decision); } //trying to convert to int
        catch (NumberFormatException e){}            //if cant be int this prevents the program from breacking  
        
        if(findStu >=1 && findStu<=user.sizes[choice])
        {
          findStu --; //because array starts at 0
          user.classroom[choice][findStu].show(); //calls on method to show student info
          findStu = 0 ;  //have to reset to prevent from opening when code re-loops
        }    
        
        if (decision.equalsIgnoreCase("add"))
        {
          user.classroom[choice][(user.sizes[choice])].modifyInfo();
          user.sizes[choice] ++; 
          user.sortClasses();
        }  //end of if add
        
        if (decision.equalsIgnoreCase("search"))
        {
          System.out.println("Which student would you like to search for?");
          decision = input.next();
          System.out.println("Results:");
          boolean studentFound = false ; //determines if the student can be found in the system
          for (int i = 0; i < user.sizes[choice]; i++)//goes through all the students
          {
            if(decision.equalsIgnoreCase(user.classroom[choice][i].lname) || decision.equalsIgnoreCase(user.classroom[choice][i].fname) )//checks both first and last name
            {
              System.out.println(user.classroom[choice][i].fname +" " + user.classroom[choice][i].lname + "   Their corresponding #: " + (i+1));
              studentFound = true;
            }      
          }
          if (studentFound == false)
          {
            System.out.println("Sorry, no students were found");
          }
        }//end of if search
        
        
        if (decision.equalsIgnoreCase("remove"))
        {
          System.out.println("Which student would you like to remove? Enter corresponding number 1 - " + user.sizes[choice] );
          decision=input.next();
          try { findStu = Integer.parseInt(decision); } //trying to convert to int
          catch (NumberFormatException e){}             //if cant be int       
          findStu--;                                   //array starts at 0, but the numbering starts at 1     
          for (int i = findStu; i < user.sizes[choice]; i++)   ///bumps all the students after the removed student up 1 spot in tne array
          {
            user.classroom[choice][i] = user.classroom[choice][i+1];
          }
          user.sizes[choice]--;                         //decrease size of class
        } //end of if remove 
        
        if (decision.equalsIgnoreCase("modify"))
        {
          System.out.println("Which student would you like to modify? Enter corresponding number 1 - " + user.sizes[choice] );
          decision=input.next();
          try { findStu = Integer.parseInt(decision); }//trying to convert to int
          catch (NumberFormatException e){}            //if cant be int      
          findStu--;                                   //array starts at 0 but asking for # from 1
          user.classroom[choice][findStu].modifyInfo();
        } //end of if modify
        
        if (decision.equalsIgnoreCase("back"))
        {
          back = true;
        }
        
      }   //end of while ! back
    }   //end of while exit == false
    input.close();
  }//end of main
}//end of class

