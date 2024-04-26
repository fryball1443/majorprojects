public class CourseDemo
{
   public static void main(String[] args)
   {
     Instructor myInstructor=new Instructor("Ben","Sunflower","Warf108");
     TextBook myTextBook=new TextBook("Strating out with Java","Tony Gaddis","Pearson");
     Course myCourse=new Course(myInstructor,myTextBook);
     System.out.println(myCourse);
     
     myInstructor.set("dfaf","afasdf","afafddas");
     System.out.println(myCourse.getName());
     
     
     
     //Instructor i1=new Instructor("Faith","Lee","WCS102");
     //System.out.println(i1);
     //i1.set("Lee","Faith","Warf109");
     //System.out.println(i1);
   }
}