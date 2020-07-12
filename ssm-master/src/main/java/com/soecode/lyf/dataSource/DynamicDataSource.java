package com.soecode.lyf.dataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

	public static final String masterDataSource = "masterDataSource";

	public static final String slaveDataSource = "slaveDataSource";

	// 本地线程，获取当前正在执行的currentThread
	public static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setCustomerType(String customerType) {
		contextHolder.set(customerType);
		System.out.println("当前数据源=="+customerType);

	}

	public static String getCustomerType() {
		return contextHolder.get();
	}

	public static void clearCustomerType() {
		contextHolder.remove();
	}

	@Override
	protected Object determineCurrentLookupKey() {
		return getCustomerType();
	}

}
