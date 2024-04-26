function read_hosts {
  hosts=$(cat $1)
  count=1
  for host in $hosts
  do
    hosts_array[$count]=$host
    ((count++))
  done
}
