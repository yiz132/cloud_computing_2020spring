# <center> Cloud Computing mini Project one <center/> 



##  **_Description_**

>This project is to set up a hadoop cluster and run several programs based on map reduce.
>
>There are mainly two parts : 
>
>* set up hadoop environment on virtial machines and start a cluster
>* develop and run 5 programs to deal with the given problems 



## *_Quick Start_*

* use ssh to connect to our virtial machine

  * IP address:167.172.25.152
  * code

  ​       `ssh -i key_student student@167.172.25.152`



## **_Mannual_**

>Mannuel for  running map reduce programs.

**Command line to compile .java.**

```java
hadoop com.sun.tools.javac.Main {name}.java
hadoop com.sun.tools.javac.Main {name}.java
```

**Package**

```java
jar cf {package_name}.jar {name}*.class
```

**Upload input directory on HDFS:**

```java
hadoop fs -mkdir -p /tmp/input
hadoop fs -put part4_1/access_log /tmp/input
```

**Run tasks 3 and 4 with following commands:**

```java
Task 3:
hadoop jar ngram/Ngram.jar Ngram /tmp/input /tmp/part3_out

Task 4:
hadoop jar part4_1/Part4_1.jar Part4_1 /tmp/input /tmp/part4_1_out
hadoop jar part4_2/Part4_2.jar Part4_2 /tmp/input /tmp/part4_2_out
hadoop jar part4_3/Part4_3.jar Part4_3 /tmp/input /tmp/part4_3_out
hadoop jar part4_4/Part4_4.jar Part4_4 /tmp/input /tmp/part4_4_out
```

**Check out results:**

```java
hdfs dfs -cat /tmp/part3_out/*
hdfs dfs -cat /tmp/part4_1_out/*
hdfs dfs -cat /tmp/part4_2_out/*
hdfs dfs -cat /tmp/part4_3_out/*
hdfs dfs -cat /tmp/part4_4_out/*
```

**Screenshots of previous examples shown below**

![img](https://lh5.googleusercontent.com/6IECk1bzHmlIwRh9YVKS7kCzHYUrThMc82kZdcFyIUWgd-4Yc-4uXPXvBlKtBOAU9vz8DpHssc9DJqQ6Oj6qzt6aF3WxjxEwjYCfzO6aZzulOOi4G6J60z-7T6BUet8qratT8F22)



![img](https://lh5.googleusercontent.com/2pRnfr_fad-HB09FJDsXhh4nwufRhjtn4pTtHwx3ByL1q5ud_S_3qY0uRIRhJlNeib9pq-pHUkDcCngeNQIWnQ3YomWs8e8OxQxTSERl11Um0gEu2BY_HM6F5j-zt6Fq2_eetqTB)





![img](https://lh3.googleusercontent.com/m26bVLl3xLLlsY9yMmzwdE-JgiASCFIZ65xAHDJh1phuanzfm6F7aa4KljK_NfEnHx4KTV8lvwEP5CDO8n54L8Yqf8mbJjlSbxTW1VuuvP35FGDNadnMLOp1mOZyvbBj5iO4JFqK)

![img](https://lh5.googleusercontent.com/PXu1lgkk9gbbI-gRufe6tC6tQ3yuI7ueevN_rKi_PnswvEITUUPxmIhmx1OmN7_ArIj4GEfm7Nb4QDGr0P9ealgj91OrRx2lrkaPxRquT8G7ggobEHE_Bgwwb1M8P0G-Yyp98Mav)

![img](https://lh4.googleusercontent.com/2crL_Um7vFk18Q85UPKOWkuZw0dLEBnh0xhkYhtkDXbjGYhIlFXqTMuopx05s269YoNL7wH2qk7v9H5OWC9oOR6YkUeMcBYTopFE_tqHQoCSrzMWimfA5JoLs113yyiyv157t5rv)

![img](https://lh4.googleusercontent.com/2Yi-b2Y4smieMVf4Xlr15IrXEcbTKSVdHDLzb-3YJBMaxTW5a8TGqTkKgdeo2AUFvZdc2oKa8jQXowvFNIONu8DdQ70a38HyJr_Y14xfYjPimDsPSsp3-wzthGNFo_JVzC0v1VEB)

![img](https://lh4.googleusercontent.com/nupQqnxkuC0d_OcUwlYJf12tFfWgMU0p1-msyU8zO253BOrCUVAqt5GKZQs4Q5F9__NXW1U_KRO2QGDprDzUqPQh0QgropjFqw25rGigLwcfDxTpghfTH9n-BVFJWZsbr8f2K6aO)

