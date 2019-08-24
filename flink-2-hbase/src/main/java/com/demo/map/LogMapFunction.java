package com.demo.map;

import com.demo.client.HbaseClient;
import com.demo.domain.LogEntity;
import com.demo.util.LogToEntity;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * @author XINZE
 */
public class LogMapFunction implements MapFunction<String, LogEntity> {

    private static final String TABLE_NAME = "con";

    private static final String COLUMN_FAMILY = "log";

    /**
     * 从字符串解析出log后插入HBase表中
     * @param s
     * @return
     * @throws Exception
     */
    @Override
    public LogEntity map(String s) throws Exception {

        LogEntity log = LogToEntity.getLog(s);
        if (null != log) {
            String rowKey = log.getUserId() + "_" + log.getProductId() + "_" + log.getTime();
            HbaseClient.putData(TABLE_NAME, rowKey, COLUMN_FAMILY, "userid", String.valueOf(log.getUserId()));
            HbaseClient.putData(TABLE_NAME, rowKey, COLUMN_FAMILY, "productid", String.valueOf(log.getProductId()));
            HbaseClient.putData(TABLE_NAME, rowKey, COLUMN_FAMILY, "time", log.getTime().toString());
            HbaseClient.putData(TABLE_NAME, rowKey, COLUMN_FAMILY, "action", log.getAction());
        }
        return log;
    }
}
