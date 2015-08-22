package cn.springmvc.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 配置多数据源工具类
 * @author Carl
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DatabaseContextHolder.getCustomerType();
	}

}
