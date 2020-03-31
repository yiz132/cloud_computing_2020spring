
#ssh login
ssh -i "key_student" student@VM_IP_ADDRESS

######WARNING: 
#change the password to a strong enough password if you do not want to lose what you did in the VM
##############

# Commands on every node
#setup JAVA 8
# sudo add-apt-repository ppa:webupd8team/java
# sudo apt-get update
# sudo apt-get install oracle-java8-installer
# sudo update-alternatives --config java

# install the OpenJDK 8
sudo apt update
sudo apt install openjdk-8-jdk

#test java setup
java -version


#download Hadoop and test locally
# suppose to use the home directory to place the hadoop files
cd ~
wget http://mirrors.advancedhosters.com/apache/hadoop/common/hadoop-3.2.1/hadoop-3.2.1.tar.gz
tar -zxf hadoop-3.2.1.tar.gz
ln -s hadoop-3.2.1 hadoop
cd hadoop

# set to the root of your Java installation
# for linux vm:
# export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
# for local mac:
# export JAVA_HOME=/usr/libexec/java_home
nano etc/hadoop/hadoop-env.sh

#test single node hadoop
mkdir input
cp etc/hadoop/*.xml input
bin/hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-3.2.1.jar grep input output 'dfs[a-z.]+'
cat output/*

######### setup etc/hosts with the IP and Node Name on every node
# 159.203.111.45 CC-demo-1
# 64.225.19.213 CC-demo-2
# 64.225.26.23 CC-demo-3
# ......
# Warning: delete every IP setting with 127.0.1.1 and 127.0.0.1 with your hostname
# for example:
# 127.0.0.1 CC-demo-01 CC-demo-01
# please delete the above line in /etc/hosts
sudo nano /etc/hosts

# check the hosts configuration
ping CC-demo-1
ping CC-demo-2
ping CC-demo-3


