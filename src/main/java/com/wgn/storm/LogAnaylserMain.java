package com.wgn.storm;

import java.util.Arrays;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;
import org.junit.Test;

//Create main class LogAnalyserStorm submit topology.
public class LogAnaylserMain{
	public void main(String args[]) {
		try {
			// Create Config instance for cluster configuration
			Config config = new Config();
			config.setDebug(true);
			//
			TopologyBuilder builder = new TopologyBuilder();
			builder.setSpout("call-log-reader-spout", new FakeCallLogReaderSpout());

			builder.setBolt("call-log-creator-bolt", new CallLogCreatorBolt()).shuffleGrouping("call-log-reader-spout");

			builder.setBolt("call-log-counter-bolt", new CallLogCounterBolt()).fieldsGrouping("call-log-creator-bolt",
					new Fields("call"));
			Config conf = new Config();
			String[] NIMBUS_SEEDS={"10.1.241.131", "10.1.241.133", "10.1.241.134"};
			conf.put(Config.NIMBUS_SEEDS, Arrays.asList(NIMBUS_SEEDS)); // 配置nimbus连接主机地址，比如：192.168.10.1
			conf.put(Config.NIMBUS_THRIFT_PORT, 6627);// 配置nimbus连接端口，默认 6627
			String[] STORM_ZOOKEEPER_SERVERS = { "10.1.241.131", "10.1.241.133", "10.1.241.134" };
			conf.put(Config.STORM_ZOOKEEPER_SERVERS, Arrays.asList(STORM_ZOOKEEPER_SERVERS)); // 配置zookeeper连接主机地址，可以使用集合存放多个
			conf.put(Config.STORM_ZOOKEEPER_PORT, 2181); // 配置zookeeper连接端口，默认2181
			conf.setDebug(true);
			conf.setNumWorkers(3);

			// 非常关键的一步，使用StormSubmitter提交拓扑时，不管怎么样，都是需要将所需的jar提交到nimbus上去，如果不指定jar文件路径，
			// storm默认会使用System.getProperty("storm.jar")去取，如果不设定，就不能提交
			System.setProperty("storm.jar", "G:\\mystorm.jar");
			try {
				StormSubmitter.submitTopology("LogAnalyserStorm", conf, builder.createTopology());
			} catch (AlreadyAliveException | InvalidTopologyException | AuthorizationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread.sleep(10000);
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
