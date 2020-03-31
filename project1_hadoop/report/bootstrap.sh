#!/bin/bash

~/hadoop/bin/hadoop

cd ~/hadoop
mkdir input
cp etc/hadoop/*.xml input

bin/hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-3.2.1.jar grep input output 'dfs[a-z.]+'
cat output/*
 
