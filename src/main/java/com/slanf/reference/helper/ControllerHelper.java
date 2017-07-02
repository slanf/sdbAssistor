package com.slanf.reference.helper;

import com.slanf.reference.annotation.ConstValue.RequestMethod;
import com.slanf.reference.annotation.Controller;
import com.slanf.reference.annotation.RequestURI;
import com.slanf.reference.helper.beans.Handler;
import com.slanf.reference.helper.beans.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Song on 2016/11/3.
 * @since v0.0
 * :@Controller标签处理类
 */
public class ControllerHelper {
    private static Logger logger = LoggerFactory.getLogger(ControllerHelper.class);
    /**
     * 存放请求与处理逻辑的映射关系
     */
    private static final Map<Request,Handler> ACTION_MAP = new HashMap<Request, Handler>();

    static {
        Set<Class<?>> controllerSet = ClassHelper.getClassSetOfAnnotation(Controller.class);
        for(Class<?> cls:controllerSet){
            Method [] methods = cls.getDeclaredMethods();
            for(Method method:methods){
                if(method.isAnnotationPresent(RequestURI.class)){
                    RequestURI annotation = method.getAnnotation(RequestURI.class);
                    String uri = annotation.URI();
                    RequestMethod requestType = annotation.METHOD();
                    ACTION_MAP.put(new Request(requestType,uri),new Handler(cls,method));
                    logger.info("Controller Mapping: "+uri+" ----->"+cls.getName()+"."+method.getName());
                }
            }
        }
    }

    /**
     * 根据请求类型，获得对应的处理器
     * @param requestMethod 请求类型
     * @param uri 请求路径
     * @return
     */
    public static Handler getHandler(RequestMethod requestMethod,String uri){
        return ACTION_MAP.get(new Request(requestMethod,uri));
    }
}
