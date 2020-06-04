#!/bin/sh
nohup java -jar /alumni.jar --spring.profiles.active=cloud > alumni.log &
nohup java -jar /user.jar --spring.profiles.active=cloud > user.log &
nohup java -jar /zuul.jar --spring.profiles.active=cloud > zuul.log &