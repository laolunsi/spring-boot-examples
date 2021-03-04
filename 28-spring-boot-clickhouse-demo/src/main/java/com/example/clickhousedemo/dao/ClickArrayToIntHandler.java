package com.example.clickhousedemo.dao;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import ru.yandex.clickhouse.ClickHouseArray;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Java Int 数组与 ClockHouse Array Int 转换器
 * @version 1.0
 * @since 2019/11/14 9:59
 */
public class ClickArrayToIntHandler extends BaseTypeHandler<Integer[]> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Integer[] integers, JdbcType jdbcType) throws SQLException {
        preparedStatement.setObject(i, integers);
    }

    @Override
    public Integer[] getNullableResult(ResultSet resultSet, String s) throws SQLException {
        Object obj = resultSet.getObject(s);
        return parseClickHouseArrayToInt(obj);
    }

    @Override
    public Integer[] getNullableResult(ResultSet resultSet, int i) throws SQLException {
        Object obj = resultSet.getObject(i);
        return parseClickHouseArrayToInt(obj);
    }

    @Override
    public Integer[] getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        Object obj = callableStatement.getObject(i);
        return parseClickHouseArrayToInt(obj);
    }

    private Integer[] parseClickHouseArrayToInt(Object obj) {
        if (obj instanceof ClickHouseArray) {
            int[] res = new int[0];
            try {
                res = (int[]) ((ClickHouseArray) obj).getArray();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            if (res != null && res.length > 0) {
                Integer[] resI = new Integer[res.length];
                for (int i = 0; i < res.length; i++) {
                    resI[i] = res[i];
                }

                return resI;
            }
        }
        return new Integer[0];
    }
}
