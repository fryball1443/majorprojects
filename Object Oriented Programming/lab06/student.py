import json

class Student:
    def __init__(self, name, age):
        self.name = name
        self.age = age

    def get_attendance_rate(self, days_present, total_days):
        return days_present / total_days

class School:
    def __init__(self):
        self.students = []

    def add_student(self, student):
        self.students.append(student)

    def get_student_name(self, index):
        return self.students[index].name
    def get_student_age(self, index):
        return self.students[index].age

def main():
    school = School()

    with open('students.json') as f:
        data = json.load(f)

    for x in data:
        school.add_student(Student(x['Name'], x['Age']))

    while True:
        try:
            index = int(input("Enter the index of the student: "))
        except ValueError:
            print("Please enter a valid integer")
            continue
        except IndexError:
            print("Please enter a valid index")
            continue
        except KeyboardInterrupt:
            print("\n\nCaught KeyboardInterrupt, exiting...")
            break
        name = school.get_student_name(index)
        age = school.get_student_age(index)
        print(name, age, '\n')
        total_days = int(input('How many days has school been in session this year? '))
        days_present = int(input('How many days has the student attended school this year? '))
        try:
            print(name, "'s attendance rate is", school.students[index].get_attendance_rate(int(days_present), int(total_days)))
        except ValueError:
            print("Please enter valid integers for days present and total days")
            continue
        except ZeroDivisionError:
            print("Cannot divide by zero")
            continue
if __name__ == "__main__":
    main()
