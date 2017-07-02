package com.slanf;

import com.slanf.reference.annotation.ScanPath;
import com.slanf.reference.annotation.SdbAssistor;
import com.slanf.reference.scanner.ClassScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Created by Song on 2017/6/6.
 * 系统入口
 */
public final class SdbAssistorApplication {
    private static final Logger logger = LoggerFactory.getLogger(SdbAssistorApplication.class);
    public static Set<Class<?>> CLASS_SET = null;
    public static void run(Class<?> clas){
        if(!clas.isAnnotationPresent(SdbAssistor.class)) {
            logger.error("This Program not support SdbAssitor ");
            return;
        }
        String basePackage = clas.getPackage().toString();
        ScanPath an = clas.getAnnotation(ScanPath.class);
        if(null != an && !an.value().equals("")){
            basePackage = an.value();
        }
        CLASS_SET = ClassScanner.getClassSet(basePackage);

    }
}
