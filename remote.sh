#!/bin/sh

host=$1
folder="testDespliegue2"
#./gradlew build
p="computacion"
sshpass -p $p ssh -o StrictHostKeyChecking=no computacion@$host mkdir -p $folder
sshpass -p $p scp -o StrictHostKeyChecking=no server/build/libs/*.jar computacion@$host:$folder
sshpass -p $p ssh -o StrictHostKeyChecking=no computacion@$host "nohup java -jar $folder/server.jar >> $folder/out.log &" &

