public class Instructor
{
  private String lastName;
  private String firstName;
  private String officeNumber;
  
  public Instructor(String lname,String fname, String office)
  {
   this.lastName=lname;
   this.firstName=fname;
   this.officeNumber=office;
  }
  
  public Instructor(Instructor object2)
  {
    this.lastName=object2.lastName;
    this.firstName=object2.firstName;
    this.officeNumber=object2.officeNumber;
  }
  
  public void set(String lname, String fname, String office)
  {
     lastName=lname;
     firstName=fname;
     officeNumber=office;
  }
  
  public String toString()
  {
     String output;
     output="\n\nLastName "+lastName+
            "\nFirstName "+firstName+
            "\nOfficeNumber "+officeNumber;
     return output;
  }
}