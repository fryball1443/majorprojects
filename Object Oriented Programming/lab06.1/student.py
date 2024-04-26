import json

class Student:
    def __init__(self, name, age):
        self.name = name
        self.age = age

    def get_attendance_rate(self, days_present, total_days):
        try:
            return days_present / total_days
        except ZeroDivisionError:
            return 0.0

class School:
    def __init__(self):
        self.students = []

    def add_student(self, student):
        self.students.append(student)

    def get_student_name(self, index):
        try:
            return self.students[index].name
        except IndexError:
            return None

    def get_student_age(self, index):
        try:
            return self.students[index].age
        except IndexError:
            return None

def main():
    school = School()

    try:
        with open('students.json') as f:
            data = json.load(f)
    except FileNotFoundError:
        print("Error: File 'students.json' not found.")
        return
    except json.JSONDecodeError:
        print("Error: Invalid JSON format in 'students.json'.")
        return

    for x in data:
        school.add_student(Student(x['Name'], x['Age']))

    while True:
        try:
            index = int(input("Enter the index of the student: "))
            name = school.get_student_name(index)
            age = school.get_student_age(index)
            if name is None or age is None:
                print("Error: Invalid student index.")
                continue

            total_days = int(input('How many days has school been in session this year? '))
            days_present = int(input('How many days has the student attended school this year? '))
            print(name, "'s attendance rate is", school.students[index].get_attendance_rate(days_present, total_days))
        except ValueError:
            print("Error: Invalid input. Please enter a valid integer.")
        except KeyboardInterrupt:
            print("\nProgram terminated by user.")
            return

if __name__ == "__main__":
    main()
