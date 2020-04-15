# <center> Cloud Computing mini Project 3 <center/> 



##  **_Description_**

>This project is to set up a Cassandra cluster and run several programs.
>
>There are mainly two parts : 
>
>* set up Cassandra environment on virtial machines and start a cluster
>* develop and run 4 programs to deal with the given problems 



## *_Quick Start_*

* use ssh to connect to our virtial machine

  * IP address:167.172.25.152
  * code

  ​       `ssh -i key_student student@167.172.25.152`

## _Import dataset_

**preprocessing**

> Convert log file to CSV file by preprocess.py.
> Cassandra Copy suggest that input data shall not be greater than 200w each time,so preprocess.py is used to split the original file to CSV file,.Each file has a maximum size of 100W.
> preprocess.py also gives id to every row, from 1 to 4477843.

**CQL used**

```sql
CREATE KEYSPACE access_ks  WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 3};
CREATE TABLE access_tb (id int,ip text,atime text,method text,uri text,http text,status text,length text,PRIMARY KEY (id, ip, uri)) WITH comment='Access Table';
copy access_tb  from '/home/student/access_log1.csv' with header = true ;
copy access_tb  from '/home/student/access_log2.csv' with header = true ;
copy access_tb  from '/home/student/access_log3.csv' with header = true ;
copy access_tb  from '/home/student/access_log4.csv' with header = true ;
copy access_tb  from '/home/student/access_log5.csv' with header = true ;
```

**show parts of the importing data**

![WechatIMG12.png](https://github.com/yiz132/cloud_computing_2020spring/blob/master/project3_Cassandra/img/WechatIMG12.png?raw=true)

## **_Mannual_**

>mannual for setting up the Cassandra environment

####Required Environment

- java8:

[![img](https://github.com/yiz132/cloud_computing_2020spring/raw/master/project3_Cassandra/img/%E6%88%AA%E5%B1%8F2020-04-10%2016.15.24.png)](https://github.com/yiz132/cloud_computing_2020spring/raw/master/project3_Cassandra/img/截屏2020-04-10 16.15.24.png)

- python3.7:

[![img](https://github.com/yiz132/cloud_computing_2020spring/raw/master/project3_Cassandra/img/%E6%88%AA%E5%B1%8F2020-04-10%2016.21.01.png)](https://github.com/yiz132/cloud_computing_2020spring/raw/master/project3_Cassandra/img/截屏2020-04-10 16.21.01.png)

####Install Cassandra

[![img](https://github.com/yiz132/cloud_computing_2020spring/raw/master/project3_Cassandra/img/%E6%88%AA%E5%B1%8F2020-04-10%2016.26.19.png)](https://github.com/yiz132/cloud_computing_2020spring/raw/master/project3_Cassandra/img/截屏2020-04-10 16.26.19.png)

####Configuration

[![img](https://github.com/yiz132/cloud_computing_2020spring/raw/master/project3_Cassandra/img/%E6%88%AA%E5%B1%8F2020-04-10%2016.28.47.png)](https://github.com/yiz132/cloud_computing_2020spring/raw/master/project3_Cassandra/img/截屏2020-04-10 16.28.47.png)[![img](https://github.com/yiz132/cloud_computing_2020spring/raw/master/project3_Cassandra/img/%E6%88%AA%E5%B1%8F2020-04-10%2016.30.18.png)](https://github.com/yiz132/cloud_computing_2020spring/raw/master/project3_Cassandra/img/截屏2020-04-10 16.30.18.png) [![img](https://github.com/yiz132/cloud_computing_2020spring/raw/master/project3_Cassandra/img/%E6%88%AA%E5%B1%8F2020-04-10%2016.30.41.png)](https://github.com/yiz132/cloud_computing_2020spring/raw/master/project3_Cassandra/img/截屏2020-04-10 16.30.41.png)

####Start the Service

[![img](https://github.com/yiz132/cloud_computing_2020spring/raw/master/project3_Cassandra/img/%E6%88%AA%E5%B1%8F2020-04-10%2016.33.42.png)](https://github.com/yiz132/cloud_computing_2020spring/raw/master/project3_Cassandra/img/截屏2020-04-10 16.33.42.png)

####Start CQL Client

[![img](https://github.com/yiz132/cloud_computing_2020spring/raw/master/project3_Cassandra/img/%E6%88%AA%E5%B1%8F2020-04-10%2016.52.31.png)](https://github.com/yiz132/cloud_computing_2020spring/raw/master/project3_Cassandra/img/截屏2020-04-10 16.52.31.png)

####Try Test Case

[![img](https://github.com/yiz132/cloud_computing_2020spring/raw/master/project3_Cassandra/img/%E6%88%AA%E5%B1%8F2020-04-10%2017.23.50.png)](https://github.com/yiz132/cloud_computing_2020spring/raw/master/project3_Cassandra/img/截屏2020-04-10 17.23.50.png)





>Mannuel for  run Cassandra programs.

**Run task 1 with following cqlsh commands:**

```sql
cqlsh> use access_ks;
cqlsh:access_ks> select count(*) from access_tb where uri='/assets/img/release-schedule-logo.png' ALLOW FILTERING ;
```

**Run task 2 with following commands:**

```sql
cqlsh> use access_ks;
cqlsh:access_ks> select count(*) from access_tb where ip='10.207.188.188' ALLOW FILTERING ;
```

**Run task 3 with following commands:**

```sql
cqlsh> use access_ks;
cqlsh:access_ks> select hitAggregate(uri) from access_tb;
```

**Run task 4 with following commands:**

```sql
cqlsh> use access_ks;
cqlsh:access_ks> select hitAggregate(ip) from access_tb;
```



###**Screenshots of results shown below**

_task 1_

![1-website.JPG](https://github.com/yiz132/cloud_computing_2020spring/blob/master/project3_Cassandra/img/1-website.JPG?raw=true)



_task 2_

![2-ip.JPG](https://github.com/yiz132/cloud_computing_2020spring/blob/master/project3_Cassandra/img/2-ip.JPG?raw=true)



_task 3_

![3-max-path.JPG](https://github.com/yiz132/cloud_computing_2020spring/blob/master/project3_Cassandra/img/3-max-path.JPG?raw=true)



_Task 4_

![4-max-ip.JPG](https://github.com/yiz132/cloud_computing_2020spring/blob/master/project3_Cassandra/img/4-max-ip.JPG?raw=true)

