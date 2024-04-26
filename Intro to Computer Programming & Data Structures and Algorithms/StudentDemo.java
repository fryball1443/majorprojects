class Student
{
   int sid;
   String sname;
   //default constructor
   public Student()
   {
      sid=2;
      sname="Jacob";
   }
   //parameterized constructor
   public Student(int sid,String sname)
   {
      this.sid=sid;
      this.sname=sname;
    }
   //copy constructor - Create an object using a copy constructor
   public Student(Student obj)
   {
      this.sid=obj.sid;
      this.sname=obj.sname;  
   }
   public Student copy()
   {
     Student copyObject=new Student();
     return copyObject;
   }
   
   //@override
   public String toString()
   {
     String output;
     output="student id "+sid+"\nstudent name "+sname;
     return output;
   }
   //@override
   public boolean equals(Student obj)
   {
       boolean output;
       if(sid==obj.sid && sname.equals(obj.sname))
        output=true;
       else
        output=false;
       
       return output;
   }
      
}  
public class StudentDemo
{
   public static void main(String[] args)
   {
      Student s1=new Student();
      //  Student s2=s1;//Reference copy
      Student s2=s1.copy();
      System.out.println("S1 object details----- \n "+s1);
      System.out.println("S2 object details----- \n "+s2);
      //The below if is comparing the refernces of s1 and s2
      if(s1==s2)
         System.out.println("s1 and s2 are pointing to a same reference");
      else 
         System.out.println("s1 and s2 are not pointing a same reference");
      //This below if is comparing field values in s1 and s2   
      if(s1.equals(s2))
        System.out.println("S1 and S2 have same data");
      else
        System.out.println("S1 and s2 got different data");
      
      Student s3=new Student(23,"Rosy");//It calls parameterized constructor
      Student s4=new Student(s3);//It call copy constructor
      
      System.out.println("S3 values----- \n "+s3);
      System.out.println("S4 values----- \n "+s4);

       if(s3==s4)
         System.out.println("s3 and s4 are pointing to a same reference");
      else 
         System.out.println("s3 and s4 are not pointing a same reference");
      //This below if is comparing field values in s1 and s2   
      if(s3.equals(s4))
        System.out.println("s3 and s4 have same data");
      else
        System.out.println("s3 and s4 got different data");
      
    
   }
} 
