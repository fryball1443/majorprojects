public class InfoOut {
  //fields 

	private String name;
	private String address;
	private int age;
	private String phoneNumber;

	// these are the constructors

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
     //output of personal information
	public String toString() {
		return String.format("Person: name=%s, address=%s, age=%d, phoneNumber=%s", 

							this.name, this.address, this.age, this.phoneNumber);

	}
}