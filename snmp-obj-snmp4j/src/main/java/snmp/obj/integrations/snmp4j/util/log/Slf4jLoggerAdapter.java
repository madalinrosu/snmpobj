package snmp.obj.integrations.snmp4j.util.log;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;

import org.slf4j.Logger;
import org.snmp4j.log.LogAdapter;
import org.snmp4j.log.LogLevel;

public class Slf4jLoggerAdapter implements LogAdapter {

	private final Logger logger;

	public Slf4jLoggerAdapter(Logger logger) {
		this.logger = logger;
	}

	@Override
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	@Override
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	@Override
	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	@Override
	public void debug(Serializable message) {
		logger.debug(message.toString());
	}

	@Override
	public void info(CharSequence message) {
		logger.info(message.toString());
	}

	@Override
	public void warn(Serializable message) {
		logger.warn(message.toString());
	}

	@Override
	public void error(Serializable message) {
		logger.error(message.toString());
	}

	@Override
	public void error(CharSequence message, Throwable throwable) {
		logger.error(message.toString(), throwable);
	}

	@Override
	public void fatal(Object message) {
		logger.error(message.toString());
	}

	@Override
	public void fatal(CharSequence message, Throwable throwable) {
		logger.error(message.toString(), throwable);
	}

	@Override
	public void setLogLevel(LogLevel level) {
		// TODO Auto-generated method stub

	}

	@Override
	public LogLevel getLogLevel() {
		if(logger.isTraceEnabled()) {
			return LogLevel.TRACE;
		}
		else if(logger.isDebugEnabled()) {
			return LogLevel.DEBUG;
		}
		else if(logger.isInfoEnabled()) {
			return LogLevel.INFO;
		}
		else if(logger.isWarnEnabled()) {
			return LogLevel.WARN;
		}
		else if(logger.isErrorEnabled()) {
			return LogLevel.ERROR;
		}
		return LogLevel.OFF;
	}

	@Override
	public LogLevel getEffectiveLogLevel() {
		return getLogLevel();
	}

	@Override
	public String getName() {
		return logger.getName();
	}

	@Override
	public Iterator<?> getLogHandler() {
	    return Collections.EMPTY_LIST.iterator();
	}

}
