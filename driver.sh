#!/bin/bash

HADOOP_HOME=/usr/lib/hadoop
JOB_HOME=~felix/adhoc/jar/sid/
JOB_NAME=mobile-inventory.jar
MAIN_CLASS=mobileinventory.Main

INPUT_DATE=`date --date="yesterday" +%Y-%m-%d`
export INPUT_PATH=/data/jobs/user-profiles/user-profile-flat-from-logs/output
export OUTPUT=sid/mobile-inventory/${INPUT_DATE}

echo "$HADOOP_HOME/bin/hadoop jar $JOB_HOME/$JOB_NAME \
-D mapred.output.compress=false  \
-D mapred.child.java.opts=-Xmx2048m \
-D mapred.reduce.tasks=20 \
-D mapred.job.priority=HIGH \
$MAIN_CLASS \
--hdfs \
--input $INPUT_PATH/$INPUT_DATE/unsampled/audience/part*.avro \
--output $OUTPUT"

$HADOOP_HOME/bin/hadoop jar $JOB_HOME/$JOB_NAME \
-D mapred.output.compress=false  \
-D mapred.child.java.opts=-Xmx2048m \
-D mapred.reduce.tasks=20 \
-D mapred.job.priority=HIGH \
$MAIN_CLASS \
--hdfs \
--input $INPUT_PATH/$INPUT_DATE/unsampled/audience/part*.avro \
--output $OUTPUT


#hadoop jar /Users/felix/NetBeansProjects/MobileInventory/build/libs/mobile-inventory.jar mobileinventory.Main --local --input /temp/user_profiles/*/*.avro --output /temp/test_user_profile_output
