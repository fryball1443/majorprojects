public class InfoOut {
  //fields 

	private String name;
	private String address;
	private int age;
	private String phoneNumber;

	//constructors

	public InfoOut() {

	}

	public InfoOut(String name, String address, int age, String phoneNumber) {

	  this.name = name;
     this.address = address;
     
	  this.age = age;
	  this.phoneNumber = phoneNumber;

	}
	//getters and setters

	public String getName() {

		return name;

	   }
	   public void setName(String name)
       {
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
   public String getPhoneNumber() {
      return phoneNumber;

	}
   
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
   
	@Override
     //output
	public String toString() {
		return String.format("Person:\n Name:\n  %s\n Age:\n  %d\n Location:\n  %s\n Phone Number:\n  %s\n", 

							this.name, this.age, this.address, this.phoneNumber);

	}
}