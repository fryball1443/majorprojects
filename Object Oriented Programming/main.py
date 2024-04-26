#declare variable
var_1=1
var_2=1.0
var_3="one"

print(var_1)
print(var_1)
print(var_3)

print (var_1, var_2, var_3, end = 'Hello\n')

list_1 = [1, 1.0, "one"]
list_2 = [var_1, var_2, var_3]

"""



i = 10
while i >= 0:
    print(i)
    i -= 1

"""

user_input = int(input("Enter a number: "))
if user_input < 10:
    print("The number is less than 10")
elif user_input > 10:
    print("The number is greater than 10")
elif user_input == 10:
    print("The number is equal to 10")
