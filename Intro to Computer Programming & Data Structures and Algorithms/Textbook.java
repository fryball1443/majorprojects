public class Textbook {
   private String title;
   private String author;
   private String publisher;
   
   
   
   public Instructor(String title,String author, String publisher) {
      this.title = title;
      this.author = author;
      this.publisher = publisher;
      
   }
   
   public Instructor(Instructor object2) {
      this.title = object2.title;
      this.author = object2.author;
      this.publisher = object2.publisher;
      
   }

   public void set(String title, String author, String publisher) {
      this.title = title;
      this.author = author;
      this.publisher = publisher;
   }
   
   public String toString() {
      