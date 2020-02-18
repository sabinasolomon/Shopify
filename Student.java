
import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;
import java.lang.*;
import java.util.EventObject;

class Student{
 
  String fname;  //student first name
  String lname;  //student last name
 
 
  int [] grades = {0,0,0,0,0,0,0,0};    //last years grades? OR this class's grades?
  int fgrade  ;
  boolean [] onTime = {true, true,true,true,true,true,true,true};  //array for wheter or not assigment was on time

  int absences =0;      //how many times student has been absent
  int lates=0;        //how many times student has been late
  int yearGrade =9;    //9-12
  boolean pass;      //did they pass the course?
 
  public Student (String fn, String ln, int g1, int g2, int g3, int g4, int g5, int g6, int g7 , int g8, boolean ot1,boolean ot2,boolean ot3,boolean ot4,boolean ot5,boolean ot6,boolean ot7,boolean ot8, int abs, int l, int yg, int a)
  {
   fname = fn;
   lname = ln;
   grades [0]= g1;
   grades [1]= g2;
   grades [2]= g3;
   grades [3]= g4;
   grades [4]= g5;
   grades [5]= g6;
   grades [6]= g7;
   grades [7]= g8;
   
   onTime [0] = ot1;
   onTime [1] = ot2;
   onTime [2] = ot3;
   onTime [3] = ot4;
   onTime [4] = ot5;
   onTime [5] = ot6;
   onTime [6] = ot7;
   onTime [7] = ot8;

   absences = abs;
   lates = l;
   yearGrade = yg;  //1-12

  }
 
  public Student ()
  {  }
 
  public void show()
  {
    JFrame studentProfile = new JFrame("Student Profile");
    studentProfile.setSize(500, 400);
    JLabel lfullname =  new  JLabel (fname + "  " +lname);
    JLabel [] lgrades = new JLabel [8];
    JLabel [] lonTime = new JLabel [8];
     
    for (int i =0; i< grades.length; i++)
    {
      lgrades[i]= new JLabel(" " +grades[i]);
 
      if (onTime[i]) //if true
      {
         lonTime[i] =new JLabel("ON TIME");
      }
      if (onTime[i] == false) //if false
      {
         lonTime[i]= new JLabel("   LATE");
      }

    }

    JLabel labsences =  new  JLabel ("Number of absences: " +absences);
    JLabel llates =  new  JLabel ("Number of lates: " +lates);
    JLabel lyearGrade =  new  JLabel ("Year Grade: " +yearGrade);
    JLabel lpass =  new  JLabel (" " +pass);
    JLabel lfgrade =  new  JLabel ("Final Grade: " +fgrade + "%");
    JLabel info =  new  JLabel ("Grades (%):                  Handed in on Time or Late?");
   
    if (pass == true)
    {
      lpass.setText("PASS");
    }
     if (pass != true)
    {
       lpass.setText("FAIL");
    }
     
     JLabel done =  new  JLabel ("To exit click X in corner");
  
      JBox showStudent =  JBox.vbox(
                                JBox.hbox( JBox.vglue() ),
                                JBox.hbox(JBox.hglue(), lfullname ,JBox.hglue() ),  //hglue or vglue evenly spaces things out EVENLY IN SPACE ITS GIVEN                                
                                JBox.hbox(JBox.hglue(), llates,  JBox.hglue(), labsences,  JBox.hglue(), lyearGrade,  JBox.hglue()),            
                                JBox.hbox(JBox.hglue(), info, JBox.hglue(),JBox.hglue()),
                                JBox.hbox(JBox.hglue(), lgrades[0], JBox.hglue(), lonTime[0],JBox.hglue()),
                                JBox.hbox(JBox.hglue(), lgrades[1], JBox.hglue(), lonTime[1],JBox.hglue()),
                                JBox.hbox(JBox.hglue(), lgrades[2], JBox.hglue(), lonTime[2],JBox.hglue()),
                                JBox.hbox(JBox.hglue(), lgrades[3], JBox.hglue(), lonTime[3],JBox.hglue()),
                                JBox.hbox(JBox.hglue(), lgrades[4], JBox.hglue(), lonTime[4],JBox.hglue()),
                                JBox.hbox(JBox.hglue(), lgrades[5], JBox.hglue(), lonTime[5],JBox.hglue()),
                                JBox.hbox(JBox.hglue(), lgrades[6], JBox.hglue(), lonTime[6],JBox.hglue()),
                                JBox.hbox(JBox.hglue(), lgrades[7], JBox.hglue(), lonTime[7],JBox.hglue()),
                                JBox.hbox(JBox.hglue(), lfgrade,JBox.hglue(), lpass, JBox.hglue()),
                                JBox.hbox( JBox.vglue() ,JBox.hglue() ,  done , JBox.hglue())
                               );
    studentProfile.add(showStudent);
    studentProfile.setVisible(true);

  }
 
 
  public void modifyInfo()
  {
   JFrame modifyStu = new JFrame("Student Profile");
   modifyStu.setSize(600, 500);

   JLabel m0 = new JLabel ("Enter Student's FIRST Name");  //message 0
   JLabel m05 = new JLabel ("Enter Student's LAST Name"); //message 0.5
   
   JTextField name1 = new JTextField(fname);
   JTextField name2 = new JTextField(lname);
   
   JLabel m1 = new JLabel ("          Number of Lates:");  //spaces are there for formating purposes
   JSpinner numOfLates = new JSpinner (new SpinnerNumberModel( lates,  0,  100,  1));
   
   JLabel m2 = new JLabel ("Number of Absences:");
   JSpinner numOfAbs = new JSpinner (new SpinnerNumberModel( absences,  0,  100,  1));
   
   JLabel m3 = new JLabel ("Year grade: ");
   JSpinner year  = new JSpinner (new SpinnerNumberModel( yearGrade,  9,  12,  1));
       
   JLabel m4 = new JLabel ("Enter Student's  grades:");
   JSpinner [] gr = new JSpinner[8]; //(new SpinnerNumberModel( 1,  0,  100,  1));

   String []answers = {"on time", "late"};
   JSpinner [] assi = new JSpinner [8] ;//(new SpinnerListModel(answers))  ;

   for (int i =0; i<grades.length; i++)
   {
    assi [i] = new JSpinner (new SpinnerListModel(answers));
    gr [i] = new JSpinner (new SpinnerNumberModel( grades[i],  0,  100,  1));
    JBox.setSize(gr[i], 50, 30);
    JBox.setSize(assi[i], 70, 30);
   }
   
    JButton done = new JButton ("DONE");
    done.setBackground(Color.GRAY);
    done.setOpaque(true);
     
     JBox.setSize(numOfLates, 50, 30);
     JBox.setSize(numOfAbs, 50, 30);
     JBox.setSize(year, 50, 30);
     JBox.setSize(done, 70, 50);
     JBox.setSize(name1, 300, 50); //setting sizes of  boxes
     JBox.setSize(name2, 300, 50); //setting sizes of  boxes
   
     JBox getInfo = JBox.vbox(
                             
                                JBox.hbox( JBox.vglue(), m0 ),
                                JBox.hbox(  JBox.hglue(), name1 , JBox.hglue(), m1,numOfLates ,  JBox.hglue()),  //hglue or vglue evenly spaces things out EVENLY IN SPACE ITS GIVEN                                
                                JBox.hbox( m05),
                                JBox.hbox(  JBox.hglue(),name2 , JBox.hglue() ,m2, numOfAbs, JBox.hglue() ),
                                JBox.hbox(  JBox.vglue() , JBox.hglue(), JBox.hglue() ,m3, year, JBox.hglue() ),
                                JBox.hbox( JBox.vglue() ),
                                JBox.hbox( m4),
                                JBox.hbox( gr[0] , assi[0] ,JBox.hglue(), gr[1],assi[1],JBox.hglue(), gr[2],assi[2],JBox.hglue(), gr[3],assi[3] ),
                                JBox.hbox( gr[4] , assi[4] ,JBox.hglue(), gr[5],assi[5],JBox.hglue(), gr[6],assi[6],JBox.hglue(), gr[7],assi[7] ),
                                JBox.hbox( JBox.vglue() , JBox.hglue(), done )
                               );
                               
    modifyStu.add(getInfo);
    modifyStu.setVisible(true);
    
    JEventQueue events = new JEventQueue ();
    events.listenTo(done, "done");    //checking if bottom done has been pressed
    boolean clicked = false;
    while ( clicked == false )
    {
      EventObject event = events.waitEvent();
      String whatHappened = events.getName(event);
      if(whatHappened.equals ("done")) //if click done
      {
         lates= (Integer) numOfLates.getValue();
         absences= (Integer) numOfAbs.getValue();
         yearGrade = (Integer)year.getValue();
         fname = name1.getText ();  //gets 1st name from text box
         lname = name2.getText ();  //gets last name from text box
         for (int i =0 ; i < grades.length; i++)
         {
           grades[i]= (Integer) gr[i].getValue();
           String temp = (String) assi[i].getValue();
           if (temp.equals ("late")) {  onTime[i]= false ;   }
         }  
         clicked = true;
      } //end of if done
    modifyGrade();
    }
   modifyStu.setVisible(false);
  } //end modifyInfo
 
 
  public void finalGrade ()
  {
    fgrade =0; //resets the final grade
    for (int i=0; i < grades.length ;i++ )   //adds all grades together
    {      
      fgrade = fgrade + grades[i];  
    }
    fgrade = fgrade/grades.length;          //finds average grade
    if (fgrade >= 50)    //determines if passed of failed --> if they get 50 or higher, they pass
    {
      pass = true;
    }
    if (fgrade < 50)  //if lower than 50, they fail
    {
      pass = false;
    }
  }
 
  public void modifyGrade() //if late, will take away 5%
  {
    for (int i=1; i < grades.length ;i++ )  
    {
      if (onTime[i]==false)           //checks if the assignment is  late
      {
        grades[i]= (int)((double)grades[i]* 0.95) ;    //takes away 5% of the grade  --> grades is an int variable but temporarly convert to double, then go back
      }
    }
    finalGrade();
  }
}