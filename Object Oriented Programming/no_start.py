"""
def non_start(str):
  return str[2:] + str[:2]

non_start("hello")
print(non_start("hello"))

def first_last6(nums):
  if ((nums[0] == 6) or (nums[-1] == 6)):
    return True
  else:
    return False



print(first_last6([1, 2, 6])) #should be true
print(first_last6([6, 1, 2, 3])) #should be true
print(first_last6([13, 6, 1, 2, 3])) #should be false


def same_first_last(nums):
  if len(nums) >= 1 and nums[0] == nums[-1]:
    return True
  else:
    return False
  
print(same_first_last([1, 2, 3]))
print(same_first_last([1, 2, 3, 1]))
print(same_first_last([1, 2, 1]))


def make_pi():
  return [3, 1, 4]

def common_end(a, b):
  if ((len(a) >= 1) and (len(b) >= 1)) and (a[0] == b[0] or a[-1] == b[-1]):
    return True
  else:
    return False
  
print(common_end([1, 2, 3], [7, 3]))
print(common_end([1, 2, 3], [7, 3, 2]))
print(common_end([1, 2, 3], [1, 3]))


for i=0, i<=list.length, i++:
for i in range(len(lst)):
  # do something with lst[i]

def sum3(nums):
  lastnum = 0
  for i in range(len(nums)):
    lastnum += nums[i]
  return lastnum
  
    

print(sum3([1, 2, 3])) # → 6
print(sum3([5, 11, 2])) #→ 18
print(sum3([7, 0, 0])) #→ 7


def rotate_left3(nums):
  lastnum = 0
  for i in range(len(nums)):
    if i == 0:
      lastnum = nums[i]
    elif i == len(nums)-1:
      nums[i-1] = nums[i]
      nums[i] = lastnum
    else:
      nums[i-1] = nums[i]
  return nums

print(rotate_left3([1, 2, 3]) + rotate_left3([5, 11, 9]) +  rotate_left3([7, 0, 0]))



def reverse3(nums):
  nums.reverse()
  return nums

print(reverse3([1, 2, 3]))
print(reverse3([5, 11, 9]))
print(reverse3([7, 0, 0]))


def sum13(nums):
  lastnum = 0
  for i in range(len(nums)):
    lastnum += nums[i]
  return lastnum


def without_end(str):
  print(str[1:])
  print(str[:1])
  print(str[-1:])
  print(str[:-1])
  

without_end("jerry")

Given an array of ints length 3, 
figure out which is larger, the first or last element in the array, 
and set all the other elements to be that value. Return the changed array.


max_end3([1, 2, 3]) → [3, 3, 3]
max_end3([11, 5, 9]) → [11, 11, 11]
max_end3([2, 11, 3]) → [3, 3, 3]

def max_end3(nums):
  largest = 0
  if nums[0] >= nums[-1]:
    largest = nums[0]
  elif nums[0] <= nums[-1]:
    largest = nums[-1]
  for i in range(len(nums)):
    nums[i] = largest
  return nums

print(max_end3([1, 2, 3]))
print(max_end3([11, 5, 9]))
print(max_end3([2, 11, 3]))

Given an array of ints, return the sum of the first 2 elements in the array. 
If the array length is less than 2, just sum up the elements that exist, 
returning 0 if the array is length 0.


sum2([1, 2, 3]) → 3
sum2([1, 1]) → 2
sum2([1, 1, 1, 1]) → 2

def sum2(nums):
  total = 0
  if len(nums) >= 2:
    for i in range(2):
      total += nums[i]
  elif len(nums) < 2:
    for i in range(len(nums)):
      total += nums[i]
  return total

print(sum2([1, 2, 3]))
print(sum2([1, 1]))
print(sum2([1, 1, 1, 1]))

Given 2 int arrays, a and b, each length 3, 
return a new array length 2 containing their middle elements.


middle_way([1, 2, 3], [4, 5, 6]) → [2, 5]
middle_way([7, 7, 7], [3, 8, 0]) → [7, 8]
middle_way([5, 2, 9], [1, 4, 5]) → [2, 4]

def middle_way(a, b):
  newarray = [0,0]
  newarray[0] = a[len(a) // 2]
  newarray[1] = b[len(b) // 2]
  return newarray

  
print(middle_way([1, 2, 3], [4, 5, 6]))
print(middle_way([7, 7, 7], [3, 8, 0]))
print(middle_way([5, 2, 9], [1, 4, 5]))

Given an array of ints, 
return a new array length 2 containing the first and last elements from the original array. 
The original array will be length 1 or more.


make_ends([1, 2, 3]) → [1, 3]
make_ends([1, 2, 3, 4]) → [1, 4]
make_ends([7, 4, 6, 2]) → [7, 2]

def make_ends(nums):
  newarray = []
  if len(nums) >= 1:
    newarray = [nums[0], nums[-1]]
  return newarray

print(make_ends([1, 2, 3]))
print(make_ends([1, 2, 3, 4]))
print(make_ends([7, 4, 6, 2]))

Given an int array length 2, return True if it contains a 2 or a 3.


has23([2, 5]) → True
has23([4, 3]) → True
has23([4, 5]) → False

def has23(nums):
  if 2 in nums or 3 in nums:
    return True
  else:
    return False
  
print(has23([2, 5]))
print(has23([4, 3]))
print(has23([4, 5]))


Given a string, return a string where for every char in the original, there are two chars.


double_char('The') → 'TThhee'
double_char('AAbb') → 'AAAAbbbb'
double_char('Hi-There') → 'HHii--TThheerree'

def double_char(str):
  newstr = ""
  for i in range(len(str)):
    newstr += str[i] + str[i]
  return newstr

print(double_char('The'))

Return the number of times that the string "hi" appears anywhere in the given string.


count_hi('abc hi ho') → 1
count_hi('ABChi hi') → 2
count_hi('hihi') → 2

def count_hi(str):
  total = 0
  for i in range(len(str) - 1):
    if (str[i] == "h") and (str[i + 1] == "i"):
      total += 1
  return total

print(count_hi('hihihihihi hi'))


Return True if the string "cat" and "dog" appear the same number of times in the given string.


cat_dog('catdog') → True
cat_dog('catcat') → False
cat_dog('1cat1cadodog') → True

def cat_dog(str):
  catcount = 0
  dogcount = 0
  for i in range(len(str)-1):
    if str[i:i+3] == 'cat':
      catcount += 1
    elif str[i:i+3] == 'dog':
      dogcount += 1
  if dogcount == catcount:
    return True
  else:
    return False
  
print(cat_dog('catdog'))


def count_code(str):
  total = 0
  for i in range(len(str) - 3):
    if (str[i] == 'c') and (str[i + 1] == 'o') and (str[i + 3] =='e'):
      total += 1
  return total

def end_other(a, b):
  a = a.lower()
  b = b.lower()
  return a.endswith(b) or b.endswith(a)

print(end_other('Hiabc', 'abc'))
print(end_other('AbC', 'HiaBc'))
print(end_other('abc', 'abXabc'))

def xyz_there(str):
  for i in range(len(str)):
    if str[i:i+3] == 'xyz':
      if i == 0 or str[i-1] != '.':
        return True
  return False

print(xyz_there('abcxyz'))
print(xyz_there('abc.xyz'))
print(xyz_there('xyz.abc'))
"""
for i in range(10):
  print(i)