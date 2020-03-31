

# test none-password SSH login
ssh CC-demo-2

# back to the master node
exit

#Configure cluster
# go to your account home directory
cd ~/hadoop

# include the hadoop bionaries in the PATH
export PATH=$PATH:~/hadoop/bin

# test if "hadoop" can be directly used
hadoop

#assign slaves on master node
#master and slave nodes both act as slaves
# put one hostname for each line
# it should looks like:
############
#
# CC-demo-2
# CC-demo-3
#
###########
nano etc/hadoop/workers


# allow the master to listen to 9000
sudo ufw allow 9000


#configure the setting
cd etc/hadoop/
#See the example in the HadoopTutorial package
nano core-site.xml
nano hdfs-site.xml
nano yarn-site.xml
nano mapred-site.xml

#copy the setting to every slaves
scp -r * student@CC-MON-10:~/hadoop/etc/hadoop/

scp -r * student@CC-MON-11:~/hadoop/etc/hadoop/

#Formate the namenode
hadoop namenode -format
