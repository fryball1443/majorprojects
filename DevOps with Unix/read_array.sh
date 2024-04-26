
#!/bin/bash

function read_array() {
        fruit=('Apple' 'Orange')
        echo "${fruit[*]}"

        for fruit in ${fruit[*]}
        do
                echo $fruit
        done

        printf '%s\n' "${fruit[@]}"

        while read line || [[ -n "$line" ]]
        do 
                armaments_array[$count]=$line
                ((count++))
        printf '%s\n' "${armaments_array[@]}"

}

read_array