# Subtree-split and Postorder-split

Subtree-split and Postorder-split algorithms implementation.

```
 ~/.profile
alias hstart="/usr/local/Cellar/hadoop/2.7.1/sbin/start-dfs.sh;/usr/local/Cellar/hadoop/2.6.0/sbin/start-yarn.sh"
alias hstop="/usr/local/Cellar/hadoop/2.7.1/sbin/stop-yarn.sh;/usr/local/Cellar/hadoop/2.6.0/sbin/stop-dfs.sh"
source ~/.profile

hstart

mvn install

hadoop jar ./target/NumberCount.jar dataSet.txt  /numberCount
hadoop jar ./target/CalculateAll-1.0.jar /dataSet.txt  /calculateAll
hadoop jar ./target/LargestInteger-1.0.jar /dataSet.txt  /largestInteger
hadoop jar ./target/AverageAll-1.0.jar /dataSet.txt  /averageAll

hadoop dfs -rm /numberCount/*
hadoop dfs -rmdir --ignore-fail-on-non-empty /numberCount/

hadoop dfs -rm /calculateAll/*
hadoop dfs -rmdir --ignore-fail-on-non-empty /calculateAll/

hadoop dfs -rm /uniqueAll/*
hadoop dfs -rmdir --ignore-fail-on-non-empty /uniqueAll/

hadoop dfs -rm /averageAll/*
hadoop dfs -rmdir --ignore-fail-on-non-empty /averageAll/

hadoop dfs -copyToLocal /numberCount/part-00000 ~/Desktop/numberCount.txt
hadoop dfs -copyToLocal /uniqueAll/part-00000 ~/Desktop/uniqueAll.txt
hadoop dfs -copyToLocal /largestInteger/part-00000 ~/Desktop/largestInteger.txt
hadoop dfs -copyToLocal /averageAll/part-00000 ~/Desktop/averageAll.txt

hstop
```
