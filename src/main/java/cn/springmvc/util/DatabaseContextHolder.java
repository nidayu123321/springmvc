package cn.springmvc.util;

/**
 * 配置多数据源控制类
 *
 * @author Carl
 */
public class DatabaseContextHolder {

    public static final String DATA_SOURCE_META = "dataSource_meta";
    public static final String DATA_SOURCE_READ = "dataSource_read";
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setCustomerType(String customerType) {
        contextHolder.set(customerType);
    }

    public static String getCustomerType() {
        return contextHolder.get();
    }

    public static void clearCustomerType() {
        contextHolder.remove();
    }

}
