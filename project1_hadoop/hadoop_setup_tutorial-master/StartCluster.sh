
cd ~/hadoop

#start cluster
sbin/start-dfs.sh
sbin/start-yarn.sh

#start job history server
sbin/mr-jobhistory-daemon.sh --config etc/hadoop/ start historyserver

#test with jps
jps
ssh CC-demo-2
jps

ssh CC-demo-3
jps
exit

# test with create an directory on HDFS
export PATH=$PATH:~/hadoop/bin
hdfs dfs -ls /
hdfs dfs -mkdir /user
hdfs dfs -mkdir /user/student
hdfs dfs -mkdir input
hdfs dfs -put etc/hadoop/*.xml input
hdfs dfs -ls /user/student/input


# test a pi example on the cluster
hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-3.2.1.jar pi 2 5

# test a wordcount example on the cluster
hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-3.2.1.jar wordcount input/ output/
hdfs dfs -cat output/*
#hdfs dfs -cat 
hdfs dfs -rmr output

# http://167.172.25.152:9870    HDFS
# http://167.172.25.152:8088/   YARN Resource manager       
# http://167.172.25.152:19888    MapReduce History
