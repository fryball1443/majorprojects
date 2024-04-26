funcion read_hosts {
  hosts_array=() # Initialize the array
  count=0
  while IFS= read -r host; do
    hosts_array+=("$host")
    ((count++))
  done < "$1"
}

if [ "$#" -ne 1 ]; then
  echo "Usage: $0 <host_file>"
  exit 1
fi

read_hosts "$1"
done=0

while [ $done -eq 0 ]; do
  # Display menu
  echo "Menu:"
  echo "P) Ping a host"
  echo "N) Lookup DNS for a host"
  echo "Q) Quit"

  # Read user's choice
  read -p "Enter your choice (P/N/Q): " cmd

  case "$cmd" in
    P|p)
      echo "Select a host to ping:"
      select host in "${hosts_array[@]}"; do
        if [[ -n "$host" ]]; then
          echo "Ping: $host"
          ping -c 1 "$host"
          break
        else
          echo "Invalid choice. Please select a valid host."
        fi
      done
      ;;

    N|n)
      echo "Select a host to lookup DNS for:"
      select host in "${hosts_array[@]}"; do
        if [[ -n "$host" ]]; then
          echo "DNS Lookup for: $host"
          nslookup "$host"
          break
        else
          echo "Invalid choice. Please select a valid host."
        fi
      done
      ;;

    Q|q)
      done=1
      ;;

    *)
      echo "Invalid choice. Please select P, N, or Q."
      ;;
  esac
done


t
