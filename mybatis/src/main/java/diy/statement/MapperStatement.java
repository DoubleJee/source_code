package diy.statement;

import diy.annotation.Select;

import java.lang.annotation.Annotation;

// MapperSQL语句信息 负责记录每个Mapper方法的sql语句及相关信息
public class MapperStatement {

    // sql信息id 为Mapper接口类名+方法名
    private String id;

    // sql语句
    private String sqlStatement;

    // sql类型
    private Annotation sqlType;

    // 结果类型
    private Class<?> resultType;


    public MapperStatement(String id, Annotation sqlType, Class<?> resultType) {
        this.id = id;
        this.sqlType = sqlType;
        this.resultType = resultType;
        parseSqlStatement();
    }

    // 解析sql语句
    private void parseSqlStatement(){
        if (sqlType instanceof Select){
            sqlStatement = ((Select)sqlType).value();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSqlStatement() {
        return sqlStatement;
    }

    public void setSqlStatement(String sqlStatement) {
        this.sqlStatement = sqlStatement;
    }

    public Annotation getSqlType() {
        return sqlType;
    }

    public void setSqlType(Annotation sqlType) {
        this.sqlType = sqlType;
    }

    public Class<?> getResultType() {
        return resultType;
    }

    public void setResultType(Class<?> resultType) {
        this.resultType = resultType;
    }

}
