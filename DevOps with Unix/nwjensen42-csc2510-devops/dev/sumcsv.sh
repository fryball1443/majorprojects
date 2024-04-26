#!/bin/bash

# Open the csv file for reading
exec 3<input.csv

# Loop through each line in the csv file
while read -u 3 line; do
  # Extract the two numbers separated by a comma
  IFS=',' read -ra nums <<< "$line"
  num1=${nums[0]}
  num2=${nums[1]}

  # Add the two numbers together
  sum=$((num1 + num2))

  # Output the sum
  echo $sum
done

# Close the csv file
exec 3<&-
