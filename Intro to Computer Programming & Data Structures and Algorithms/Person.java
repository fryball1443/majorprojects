
public class Person {
   private String name;
   private String address;
   private int age;
   private long phno;
   
   // Default constructor
   public Person() {
      
   }
   
   // Parameterized constructor
   public Person(String name, String address, int age, long phno) {
      super();
      this.name = name;
      this.address = address;
      this.age = age;
      this.phno = phno;
   }
   
   // Setter and getter methods
   public String getName() {
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
   }
   
   public String getAddress() {
      return address;
   }
   
   public void setAddress(String address) {
      this.address = address;
   }
   
   public int getAge() {
      return age;
   }
   
   public void setAge(int age) {
      this.age = age;
   }
   
   public long getPhno() {
      return phno;
   }
   
   public void setPhno(long phno) {
      this.phno = phno;
   }
   
}