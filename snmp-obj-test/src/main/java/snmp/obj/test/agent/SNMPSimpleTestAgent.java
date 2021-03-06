package snmp.obj.test.agent;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.snmp4j.agent.io.ImportMode;
import org.snmp4j.agent.test.TestAgent;
import org.snmp4j.smi.OctetString;

// TODO: replace TestAgent with AgentConfigManager
public class SNMPSimpleTestAgent extends TestAgent implements SNMPTestAgent {

	private ExecutorService executor = Executors.newSingleThreadExecutor();
	
	private Future<?> task;

	public SNMPSimpleTestAgent() throws Exception {
		this("127.0.0.1/10161");
	}

	public SNMPSimpleTestAgent(String address) throws Exception {
		super(new File(SNMPSimpleTestAgent.class.getResource("/SNMP4JTestAgent.bc").toURI()), 
				new File(SNMPSimpleTestAgent.class.getResource("/SNMP4JTestAgent.cfg").toURI()));
		super.address = address;
	}
	
	@Override
	public void init() throws IOException {
        super.init();
        loadConfig(ImportMode.REPLACE_CREATE);
        addShutdownHook();
        getServer().addContext(new OctetString("public"));
        finishInit();
	}

	
	@Override
	public void start() throws IOException {
		init();
		task = executor.submit(this);
	}

	@Override
	public void stop() {
		if(task != null) {
			super.stop();
			executor.shutdown();
			if(!task.isDone()) {
				super.stop();
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
