package com.wgn.storm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

public class CallLogCounterBolt implements IRichBolt {
	Map<String, Integer> counterMap;
	private OutputCollector collector;
	private Connection conn = null;
	private PreparedStatement pstmt = null;

	@Override
	public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
		this.counterMap = new HashMap<String, Integer>();
		this.collector = collector;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");// 找到oracle驱动器所在的类
			String url = "jdbc:oracle:thin:@10.1.241.133:1521:MVNO_DXTBM_10GR2"; // URL地址
			String username = "ad";
			String password = "ad";
			this.conn = DriverManager.getConnection(url, username, password);
			String sql = "insert into t_user(call,duration)values(?,?)";
			this.pstmt = this.conn.prepareStatement(sql);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void execute(Tuple tuple) {
		String call = tuple.getString(0);
		Integer duration = tuple.getInteger(1);

		if (!counterMap.containsKey(call)) {
			counterMap.put(call, 1);
		} else {
			Integer c = counterMap.get(call) + 1;
			counterMap.put(call, c);
		}

		collector.ack(tuple);
	}

	@Override
	public void cleanup() {
		try {
			for (Map.Entry<String, Integer> entry : counterMap.entrySet()) {
				System.out.println(entry.getKey() + " : " + entry.getValue());

				this.pstmt.setString(1, entry.getKey());
				this.pstmt.setInt(2, entry.getValue());
				this.pstmt.addBatch();
			}
			this.pstmt.executeBatch();
			this.conn.commit();
			this.pstmt.close();
			this.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("call"));
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}
