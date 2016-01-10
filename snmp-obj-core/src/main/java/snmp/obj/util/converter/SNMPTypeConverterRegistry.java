package snmp.obj.util.converter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import snmp.obj.mib.Syntax;

public class SNMPTypeConverterRegistry {
	
	private final static Logger logger = LoggerFactory.getLogger(SNMPTypeConverterRegistry.class);

	public final static SNMPTypeConverterRegistry DEFAULT_REGISTRY = new SNMPTypeConverterRegistry();

	private Map<Syntax, Map<Class<?>, SNMPTypeConverter<?, ?>>> cache = new HashMap<>();
	private Map<Syntax, Map<Class<?>, Class<? extends SNMPTypeConverter<?, ?>>>> classMap = new HashMap<>();
	
	public SNMPTypeConverterRegistry() {
	}


	public <T> void registerConverterClass(Syntax syntax, Class<T> type, Class<? extends SNMPTypeConverter<?, ?>> clazz) {
		Map<Class<?>, Class<? extends SNMPTypeConverter<?, ?>>> map = classMap.get(syntax);
		if(map == null) {
			map = new HashMap<>();
			classMap.put(syntax, map);
		}
		map.put(type, clazz);
		logger.info(String.format("Registered conveter class : %s, %s, %s", syntax.name(), type.getSimpleName(), clazz.getName()));
	}

	public <T> Class<? extends SNMPTypeConverter<T, ?>> unregisterConverterClass(Syntax syntax, Class<T> type) {
		Map<Class<?>, Class<? extends SNMPTypeConverter<?, ?>>> map = classMap.get(syntax);
		Class<? extends SNMPTypeConverter<T, ?>> clazz = null;
		if(map != null) {
			clazz = (Class<? extends SNMPTypeConverter<T, ?>>) map.remove(type);
			if(map.isEmpty()) {
				classMap.remove(syntax);
			}
		}
		return clazz;
	}

	public <T> Class<? extends SNMPTypeConverter<T, ?>> findConverterClass(Syntax syntax, Class<T> type) {
		Map<Class<?>, Class<? extends SNMPTypeConverter<?, ?>>> map = classMap.get(syntax);
		Class<? extends SNMPTypeConverter<T, ?>> clazz = null;
		if(map != null) {
			clazz = (Class<? extends SNMPTypeConverter<T, ?>>) map.get(type);
		}
		return clazz;
	}

	public <T> void registerConverter(Syntax syntax, Class<T> type, SNMPTypeConverter<T, ?> converter) {
		Map<Class<?>, SNMPTypeConverter<?, ?>> map = cache.get(syntax);
		if(map == null) {
			map = new HashMap<>();
			cache.put(syntax, map);
		}
		map.put(type, converter);
		logger.info(String.format("Registered conveter : %s, %s, %s", syntax.name(), type.getSimpleName(), converter.getClass().getName()));
	}

	public <T> SNMPTypeConverter<T, ?> unregisterConverter(Syntax syntax, Class<T> type) {
		Map<Class<?>, SNMPTypeConverter<?, ?>> map = cache.get(syntax);
		SNMPTypeConverter<T, ?> converter = null;
		if(map != null) {
			converter = (SNMPTypeConverter<T, ?>) map.remove(type);
			if(map.isEmpty()) {
				cache.remove(syntax);
			}
		}
		return converter;
	}
	
	public <T> SNMPTypeConverter<T, ?> findConverter(Syntax syntax, Class<T> type) {
		Map<Class<?>, SNMPTypeConverter<?, ?>> map = cache.get(syntax);
		SNMPTypeConverter<T, ?> converter = null;
		if(map != null) {
			converter = (SNMPTypeConverter<T, ?>) map.get(type);
		}
		if(converter == null) {
			// auto-register converter from class
			if(Enum.class.isAssignableFrom(type)) {
				Class<? extends SNMPTypeConverter<T, ?>> clazz = (Class<? extends SNMPTypeConverter<T, ?>>) findConverterClass(syntax, Enum.class);
				if(clazz != null) {
					Constructor<? extends SNMPTypeConverter<T, ?>> ctor;
					try {
						ctor = (Constructor<? extends SNMPTypeConverter<T, ?>>) clazz.getConstructor(Class.class);
						converter = ctor.newInstance(type);
					} 
					catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
			else {
				Class<? extends SNMPTypeConverter<T, ?>> clazz = findConverterClass(syntax, type);
				if(clazz != null) {
					try {
						converter = clazz.newInstance();
					} 
					catch (InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					} 
				}
			}
			if(converter != null) {
				registerConverter(syntax, type, converter);
			}
			else {
				logger.warn(String.format("Cannot find conveter : %s, %s", syntax.name(), type.getSimpleName()));
			}
		}
		return converter;
	}

}
