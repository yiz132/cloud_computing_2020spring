# Preparation:

- Download Spark package:

  <img src="https://raw.githubusercontent.com/yiz132/cloud_computing_2020spring/master/project2_spark/report/img/image-20200331101055670.png" style="zoom:50%;" />

- Unpack:

  <img src="https://raw.githubusercontent.com/yiz132/cloud_computing_2020spring/master/project2_spark/report/img/image-20200331101154552.png" alt="image-20200331101228574" style="zoom:50%;" />
  <img src="https://github.com/yiz132/cloud_computing_2020spring/raw/master/project2_spark/report/img/image-20200331101228574.png" style="zoom:50%;" />

- Install Scala:

   <img src="https://github.com/yiz132/cloud_computing_2020spring/raw/master/project2_spark/report/img/image-20200331101535776.png" style="zoom:50%;" />

- Try Spark shell on local mode:
  <img src="https://github.com/yiz132/cloud_computing_2020spring/raw/master/project2_spark/report/img/image-20200331101804477.png" style="zoom:50%;" />
  <img src="https://github.com/yiz132/cloud_computing_2020spring/raw/master/project2_spark/report/img/image-20200331103209021.png" style="zoom:50%;" />
 



# Run Spark Shell with YARN

- Start hadoop cluster

<img src="https://github.com/yiz132/cloud_computing_2020spring/raw/master/project2_spark/report/img/image-20200331103854141.png" style="zoom:50%;" />

- Add the path of hadoop to the source file and refresh it

  <img src="https://github.com/yiz132/cloud_computing_2020spring/raw/master/project2_spark/report/img/image-20200331104032741.png" style="zoom:50%;" />

  <img src="https://github.com/yiz132/cloud_computing_2020spring/raw/master/project2_spark/report/img/image-20200331104324800.png" style="zoom:50%;" />

- Pi test on hadoop cluster

  <img src="https://github.com/yiz132/cloud_computing_2020spring/raw/master/project2_spark/report/img/image-20200331105126529.png" style="zoom:50%;" />

  <img src="https://github.com/yiz132/cloud_computing_2020spring/raw/master/project2_spark/report/img/image-20200331105349629.png" style="zoom:50%;" />

- SimpleApp test on hadoop cluster

  - Use maven to package the program

    <img src="https://github.com/yiz132/cloud_computing_2020spring/raw/master/project2_spark/report/img/image-20200331110231612.png" style="zoom:50%;" />

  - Put the example file into HDFS

    <img src="https://github.com/yiz132/cloud_computing_2020spring/raw/master/project2_spark/report/img/image-20200331110132966.png" style="zoom:50%;" />

  - Run the program with spark-submit

    <img src="https://github.com/yiz132/cloud_computing_2020spring/raw/master/project2_spark/report/img/image-20200331110747436.png" style="zoom:50%;" />

    <img src="https://github.com/yiz132/cloud_computing_2020spring/raw/master/project2_spark/report/img/image-20200331110717103.png" style="zoom:50%;" />

