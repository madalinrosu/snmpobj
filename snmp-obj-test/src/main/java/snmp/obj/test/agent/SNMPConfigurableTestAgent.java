package snmp.obj.test.agent;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.snmp4j.agent.example.SampleAgent;

public class SNMPConfigurableTestAgent extends SampleAgent {

	private ExecutorService executor = Executors.newSingleThreadExecutor();
	
	private Future<?> task;

	public SNMPConfigurableTestAgent() throws Exception {
		this(defaultConfig());
	}

	public SNMPConfigurableTestAgent(Map<String, List> cfg) throws Exception {
		super(cfg);
	}
	
	@SuppressWarnings("rawtypes")
	private static Map<String, List> defaultConfig() throws IOException {
		Map<String, List> cfg = new HashMap<>();
		cfg.put("c", Arrays.asList(File.createTempFile("SampleAgent", "cfg").getAbsolutePath()));
		cfg.put("bc", Arrays.asList(File.createTempFile("SampleAgent", "bc").getAbsolutePath()));
		cfg.put("tls-version", Arrays.asList("TLSv1"));
		cfg.put("address", Arrays.asList("udp:127.0.0.1/10161"));
		return cfg;
	}
	
	public void start() throws IOException {
		task = executor.submit(new Runnable(){

			@Override
			public void run() {
				SNMPConfigurableTestAgent.this.run();
			}
			
		});
	}

	public void stop() {
		if(task != null) {
//			agent.shutdown();
			executor.shutdown();
			if(!task.isDone()) {
				agent.shutdown();
			}
			try {
				executor.awaitTermination(5, TimeUnit.SECONDS);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
