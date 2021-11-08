package user_service.config.type_handlers;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(String[].class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class StringToStringArrayHandler implements TypeHandler<String[]> {
    @Override
    public void setParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public String[] getResult(ResultSet rs, String columnName) throws SQLException {
        String toParse = rs.getString(columnName);

        if (toParse != null) {
            return toParse.split(";");
        }
        return new String[0];
    }

    @Override
    public String[] getResult(ResultSet rs, int columnIndex) throws SQLException {
        String toParse = rs.getString(columnIndex);
        if (toParse != null) {
            return toParse.split(";");
        }
        return new String[0];
    }

    @Override
    public String[] getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String toParse = cs.getString(columnIndex);
        if (toParse != null) {
            return toParse.split(";");
        }
        return new String[0];
    }
}
