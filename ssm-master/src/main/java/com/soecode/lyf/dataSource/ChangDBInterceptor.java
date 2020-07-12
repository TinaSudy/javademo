package com.soecode.lyf.dataSource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

public class ChangDBInterceptor {
	
	public Object changeDB(ProceedingJoinPoint pjp) throws Throwable {
        //AOP切点在Service的 包名.类名
        Class<?> joinClass=pjp.getTarget().getClass();
        Signature signature=pjp.getSignature();
        String path=signature.toString();
        
        if (path.indexOf("master")!=-1){
            DynamicDataSource.setCustomerType(DynamicDataSource.masterDataSource);
        }else if (path.indexOf("slave")!=-1){
            DynamicDataSource.setCustomerType(DynamicDataSource.slaveDataSource);
        }
        return pjp.proceed();
    }   

}
