SK - Create Metadata tables in postgresql
MO - Create 2 data sets for recon based on Metedata
CG - Java Program - connect to postgresql and retrieve metadata from the postgresql tables. Need to use AWS SDK. Write Java program to create report
    from Spark dataframes
AJ -  Create Spark Cluster with multiple data notes
PR- Write Spark SQL for Recon using metadata



JAVA-
sudo yum install java-1.8.0
sudo yum remove java-1.7.0-openjdk

-Default User is ec2-user


Copy from Local to Ec2
cp -i /c/ssh-keys/rekhari.pem tt ec2-user@ec2-18-221-128-232.us-east-2.compute.amazonaws.com:~

-- SPARK
cd ~
wget http://www-us.apache.org/dist/spark/spark-2.3.0/spark-2.3.0-bin-hadoop2.7.tgz

# Unpack Spark in the /opt directory
sudo tar zxvf spark-2.3.0-bin-hadoop2.7.tgz -C /opt

# Update permissions on installation
sudo chown -R ec2-user:ec2-user /opt/spark-2.3.0-bin-hadoop2.7

# Create a symbolic link to make it easier to access
sudo ln -fs spark-2.3.0-bin-hadoop2.7 /opt/spark

# Insert these lines into your ~/.bash_profile:
export SPARK_HOME=/opt/spark
PATH=$PATH:$SPARK_HOME/bin
export PATH
# Then exit the text editor and return to the command line.


# Reload environment variables
source ~/.bash_profile

# Confirm that spark-submit is now in the PATH.
spark-submit --version
# (Should display a version number)
