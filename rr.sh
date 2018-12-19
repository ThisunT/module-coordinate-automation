#!/bin/bash

foo () {
    cd $1
    java -cp ".:../jars/*" PomReader
    cd ..
}

while IFS='' read -r line || [[ -n "$line" ]]; do
    read sshUrl <<< $(echo "$line" | cut -d',' -f 1)
    read branch <<< $(echo "$line" | cut -d',' -f 2)
    read reponame <<< $(echo "$sshUrl" | cut -d'/' -f 5 | cut -d'.' -f 1)

    $(git clone --branch "$branch" "$sshUrl");  
    $(cp PomReader.class "$reponame");
    foo "$reponame"

done < "$1"

