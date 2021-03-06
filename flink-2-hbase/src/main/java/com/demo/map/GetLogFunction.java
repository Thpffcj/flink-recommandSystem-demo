package com.demo.map;

import com.demo.domain.LogEntity;
import com.demo.util.LogToEntity;
import org.apache.flink.api.common.functions.MapFunction;

/*
 * 将kafka的数据转为Log类
 */
public class GetLogFunction implements MapFunction<String, LogEntity> {

    @Override
    public LogEntity map(String s) throws Exception {

        LogEntity log = LogToEntity.getLog(s);
        return log;
    }
}
