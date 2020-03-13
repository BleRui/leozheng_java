package net.canway.demo.mvc.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.util.Objects;

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PageInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (!(invocation.getTarget() instanceof StatementHandler)) {
            return invocation.proceed();
        }
        MetaObject metaObject = SystemMetaObject.forObject(invocation.getTarget());
        RowBounds rowBounds = (RowBounds) metaObject.getValue("delegate.rowBounds");
        if (Objects.isNull(rowBounds) || RowBounds.DEFAULT == rowBounds) {
            return invocation.proceed();
        }
        String sql = (String) metaObject.getValue("delegate.boundSql.sql");
        sql += (" limit " + rowBounds.getOffset() + ", " + rowBounds.getLimit());
        metaObject.setValue("delegate.boundSql.sql", sql);
        return invocation.proceed();
    }
}
