
"""

class Employee:
  def __init__(self, first_name:str, last_name:str, salary:int):
    self.first_name = first_name
    self.last_name = last_name
    self.salary = salary

  def get_salary(self):
    return self.salary
  
emp_1 = Employee("clark", "kent", 1)
print(emp_1.get_salary())

"""

my_list = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
my_list_1 = my_list[-1:0:-1]

print(my_list_1)

def tripleVal(num: int):
  return num * 3

