0：jps

Java版的ps -ef查看所有JVM进程。 


1：jstack

查看JVM中运行线程的状态，比较重要。可以定位CPU占用过高位置，定位死锁位置。 

/export/Logs/jvm/jstack_377063_2019-10-14-10-41-06.txt

2：jstat

jinfo查看JVM的运行环境参数，比如默认的JVM参数等。jstat统计信息监视工具。 

/export/Logs/jvm/jstat_377063_2019-10-14-10-43-16.txt

3：jmap

JVM内存映像工具。 

jmap -histo
/export/Logs/jvm/jmap_377063_2019-10-14-10-43-51.txt

jmap -heap
/export/Logs/jvm/jmap_377063_2019-10-14-10-44-37.txt

jmap -dump
Dumping heap to /export/Logs/jvm/jmap_377063_2019-10-14-10-44-54.txt ...
Heap dump file created
/export/Logs/jvm/jmap_377063_2019-10-14-10-44-54.txt
