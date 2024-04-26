public class Month
{
 // fields
  private String month;
  private int days;
  
 // constructors
  public Month(String month) {
    this.month = month;
    //this.days = days;
  }  
 
 // getters
  public String getMonth()
  {
    return this.month;
  }
  
  public int getdays()
  {
    return this.days;
  }

 // setters  
  public void setDays(int days)
  {
    this.days = days;
  }
 //method
  
  public int numberDays()
  {
    if ((month == "January") || (month == "March") || (month == "May") || (month == "July") || (month == "August") || (month == "October") || (month == "December")) {
      days = 31;
    }
    this.days = days;
    return days;
  }
  /*/
  numberDays(){
    month[] = {"January", "February"};
  }*/
}