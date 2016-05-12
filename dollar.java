import java.util.*;
import java.lang.*;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


class Dollar {

        public <T> T $(Object varName) {
            try {
                return (T) getClass().getDeclaredField(varName.toString()).get(this);
            } catch (Throwable t) {
                try {
                    return (T) t;
                } catch (Throwable e) {
                    return null;
                }
            }
        }
        
        private static Field findField(Class<?> current, String varName) {
	        try {
	            return current.getDeclaredField(varName);
	        } catch (NoSuchFieldException e) {
	            e.printStackTrace();
	        }
	        List<Class<?>> classesToGo = new ArrayList<>();
	        Class<?> sup = current.getSuperclass();
	        classesToGo.add(sup);
	        classesToGo.addAll(Arrays.asList(current.getInterfaces()));
	        for (Class<?> c : classesToGo) {
	            if (c != null && !c.equals(Object.class)) {
	                try {
	                    Field f = findField(c, varName);
	                    if (f != null) {
	                        return f;
	                    }
	                } catch (Exception e) {
	                    
	                }
	            }
	        }
	        return null;
    	}

        
    }
