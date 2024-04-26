public class CourseDemo {
   public static void main(String[] args) {
      Instructor myInstructor = new Instructor("Ben", "Sunflower", "Warf108");
      TextBook myTextBook = new TextBook("Starting out with java", "Tony Gaddis", "Pearson");
      Course myCourse = new Course("CISP1020", myInstructor, myTextBook);
      System.out.println(myCourse);
      
      myInstructor.set("asd", "asdfasd", "asdfasdfasdf");
      System.out.println(myInstructor);
      
      
     /*/
      Instructor ins1 = new Instructor(myInstructor);
      System.out.println(ins1);
     //*/
      
     /*/ 
      Instructor i1 = new Instructor("Faith", "Lee", "WCS102");
      System.out.println(i1);
      i1.set("Lee", "Faith", "Warf109");
      System.out.println(i1);
     //*/
      
      
   }
}