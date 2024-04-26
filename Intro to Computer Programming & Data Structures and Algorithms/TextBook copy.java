public class TextBook
{
  private String title;
  private String author;
  private String publisher;  
  public TextBook(String title,String author, String publisher)
  {
    this.title=title;
    this.author=author;
    this.publisher=publisher;   
  }
  public TextBook(TextBook object2)
  {
    this.title=object2.title;
    this.author=object2.author;
    this.publisher=object2.publisher;    
  }  
  public void set(String title,String author, String publisher)
  {
     this.title=title;
     this.author=author;
     this.publisher=publisher;
  }  
  public String toString()
  {
     String output;
     output="title  "+title+
            "\nauthor  "+author+
            "\npublisher  "+publisher; 
     return output;
  }
}