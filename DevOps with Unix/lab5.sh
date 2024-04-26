#!/bin/bash

# Check if correct number of arguments is received
if [ "$#" -ne 2 ]; then
  echo "Usage: $0 <filename> <username>"
  exit 1
fi

# Check if file exists
if [ ! -f "$1" ]; then
  echo "Error: File $1 does not exist."
  exit 1
fi

# Check if username already exists in file
if grep -Fxq "$2" "$1"; then
  echo "Username $2 already exists in file $1."
else
  # Add username to end of file
  echo "$2" >> "$1"
  echo "Username $2 added to file $1."
fi

