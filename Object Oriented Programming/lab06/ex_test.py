try:
    my_file = open('input.txt')
except FileNotFoundError:
    print('Sorry. This file could not be found.')
    exit()
else:
    my_var = my_file.readline()
    print(my_var)
finally:
    print('Program terminated.')

