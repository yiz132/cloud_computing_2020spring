# Preparation:

- Download Spark package:

  <img src="/Users/zhuangruhao/Library/Application Support/typora-user-images/image-20200331101055670.png" alt="image-20200331101055670" style="zoom:50%;" />

- Unpack:

  <img src="/Users/zhuangruhao/Library/Application Support/typora-user-images/image-20200331101154552.png" alt="image-20200331101154552" style="zoom:50%;" /><img src="/Users/zhuangruhao/Library/Application Support/typora-user-images/image-20200331101228574.png" alt="image-20200331101228574" style="zoom:50%;" />

- Install Scala:

  <img src="/Users/zhuangruhao/Library/Application Support/typora-user-images/image-20200331101535776.png" style="zoom:50%;" />

- Try Spark shell on local mode:

  <img src="/Users/zhuangruhao/Library/Application Support/typora-user-images/image-20200331101804477.png" alt="image-20200331101804477" style="zoom:50%;" />

<img src="/Users/zhuangruhao/Library/Application Support/typora-user-images/image-20200331103209021.png" alt="image-20200331103209021" style="zoom:50%;" />



# Run Spark Shell with YARN

- Start hadoop cluster

<img src="/Users/zhuangruhao/Library/Application Support/typora-user-images/image-20200331103854141.png" alt="image-20200331103854141" style="zoom:50%;" />

- Add the path of hadoop to the source file and refresh it

  <img src="/Users/zhuangruhao/Library/Application Support/typora-user-images/image-20200331104032741.png" alt="image-20200331104032741" style="zoom:50%;" />

<img src="/Users/zhuangruhao/Library/Application Support/typora-user-images/image-20200331104324800.png" alt="image-20200331104324800" style="zoom:50%;" />

- Pi test on hadoop cluster

  <img src="/Users/zhuangruhao/Library/Application Support/typora-user-images/image-20200331105126529.png" alt="image-20200331105126529" style="zoom:50%;" />

<img src="/Users/zhuangruhao/Library/Application Support/typora-user-images/image-20200331105349629.png" alt="image-20200331105349629" style="zoom:50%;" />

- SimpleApp test on hadoop cluster

  - Use maven to package the program

    <img src="/Users/zhuangruhao/Library/Application Support/typora-user-images/image-20200331110231612.png" alt="image-20200331110231612" style="zoom:50%;" />

  - Put the example file into HDFS

    <img src="/Users/zhuangruhao/Library/Application Support/typora-user-images/image-20200331110132966.png" alt="image-20200331110132966" style="zoom:50%;" />

  - Run the program with spark-submit

    <img src="/Users/zhuangruhao/Library/Application Support/typora-user-images/image-20200331110747436.png" alt="image-20200331110747436" style="zoom:50%;" />

    <img src="/Users/zhuangruhao/Library/Application Support/typora-user-images/image-20200331110717103.png" alt="image-20200331110717103" style="zoom:50%;" />

