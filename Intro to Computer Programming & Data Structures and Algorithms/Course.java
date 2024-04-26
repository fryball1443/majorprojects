public class Course
{
   private String courseName;
   private Instructor instructor;
   private TextBook textbook;
   
   public Course(Instructor instr,TextBook text)
   {
     // courseName=name;
    //creating instructor and textbook object using copy constructor
      instructor = new Instructor(instr);//Deep copy
      textbook = new TextBook(text);//Deep copy
     
   //  instructor=instr;//shallow copy - copying a refernce
    // textbook=text;//shallow copy security issue
   }
   public String getName()
   {
      return courseName;
   }
   public Instructor getInstructor()
   {
      return new Instructor(instructor);   
     //return instructor;causes a security issue
   }
   public TextBook getTextBook()
   {
      return new TextBook(textbook);
   }
   public String toString()
   {
      String output="Course name "+courseName+
                    "\n\nInstructor info \n"+ instructor+
                    "\n\nTextBook info \n"+ textbook;
                    
       return output;
   }
 }
   
   