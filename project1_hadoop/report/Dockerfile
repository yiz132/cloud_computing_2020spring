FROM ubuntu:18.04
USER root
MAINTAINER rulezhuang1029@gmail.com
#Install JDK
RUN apt-get update && apt-get -y install openjdk-8-jdk \
    && rm -rf /var/lib/apt/lists/*

RUN apt-get update && apt-get -y install wget \
    && rm -rf /var/lib/apt/lists/*
#Install Hadoop
RUN cd ~ \
    && wget http://mirrors.advancedhosters.com/apache/hadoop/common/hadoop-3.2.1/hadoop-3.2.1.tar.gz \
    && tar -zxvf hadoop-3.2.1.tar.gz \
    && ln -s hadoop-3.2.1 hadoop
RUN echo 'export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64' >> ~/hadoop/etc/hadoop/hadoop-env.sh

COPY bootstrap.sh /etc
RUN chmod 777 /etc/bootstrap.sh

#Configure the java path in Hadoop

CMD ["/etc/bootstrap.sh","-d"]



