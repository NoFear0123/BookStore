package utils;/*
 *@author 刘治成
 *@create 2021-10-26 17:41
 */

import org.apache.commons.beanutils.BeanUtils;
import pojo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class WebUtils {
    //调用此方法就返回带参实例对象
    //使用泛型，直接返回对应的类（避免类型转换）
    public static <T> T copyParamToBean(Map value, T bean){
        try {
            //把所有请求的参数封装到Map中
            //将map中值注入到JavaBean属性中
            BeanUtils.populate(bean,value);
            //System.out.println(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
    //将字符串转化为int类型
    public static int parseInt(String string, int defaultValue){
        try {
            return Integer.parseInt(string);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return defaultValue;
    }
}
