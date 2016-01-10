package snmp.obj.integrations.snmp4j.util.log;

import java.util.Collections;
import java.util.Iterator;

import org.slf4j.LoggerFactory;
import org.snmp4j.log.LogAdapter;
import org.snmp4j.log.LogFactory;

public class Slf4jLoggerFactory extends LogFactory {

	public Slf4jLoggerFactory() {
	}

	protected LogAdapter createLogger(Class clazz) {
		return new Slf4jLoggerAdapter(LoggerFactory.getLogger(clazz));
	}

	protected LogAdapter createLogger(String className) {
		return new Slf4jLoggerAdapter(LoggerFactory.getLogger(className));
	}

	public LogAdapter getRootLogger() {
		return new Slf4jLoggerAdapter(LoggerFactory.getLogger(""));
	}

	public Iterator loggers() {
	    return Collections.EMPTY_LIST.iterator();
	}

}
