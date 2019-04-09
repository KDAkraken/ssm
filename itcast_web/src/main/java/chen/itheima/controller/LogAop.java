package chen.itheima.controller;

import chen.itheima.domain.SysLog;
import chen.itheima.domain.UserInfo;
import chen.itheima.service.impl.ISysLogServiceimpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.security.Security;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISysLogServiceimpl iSysLogServiceimpl;
    private Date startTime;
    private Class executionClass;
    private Method method;


    @Before("execution(* chen.itheima.controller.*.*(..))")
    public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {
        startTime = new Date();
        executionClass = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            method = executionClass.getMethod(methodName);
        } else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method = executionClass.getMethod(methodName, classArgs);
        }
    }

    @After("execution(* chen.itheima.controller.*.*(..))")
    public void doAfter(JoinPoint joinPoint) throws Exception {
        if (executionClass != SysLogController.class) {
            RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);

            if (classAnnotation != null) {
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String url = "";
                    url = classAnnotation.value()[0] + methodAnnotation.value()[0];
                    SysLog sysLog = new SysLog();
                    Long time = new Date().getTime() - startTime.getTime();
                    sysLog.setUrl(url);
                    String remoteAddr = request.getRemoteAddr();
                    sysLog.setIp(remoteAddr);
                    SecurityContext context = SecurityContextHolder.getContext();
                    sysLog.setVisitTime(startTime);

                    User user= (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();
                    sysLog.setUsername(username);
                    sysLog.setMethod(executionClass.getName() + method.getName());
                    sysLog.setExecutionTime(time);
                    iSysLogServiceimpl.save(sysLog);
                }
            }
        }
    }
}
