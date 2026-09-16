package es.upsa.ssi.common.datasource;

import es.upsa.ssi.common.exceptions.BookshopException;
import es.upsa.ssi.common.mpconfig.CustomDatasourceProperties;
import jakarta.enterprise.inject.InjectionException;
import jakarta.enterprise.inject.spi.InjectionPoint;
import org.eclipse.microprofile.config.Config;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;

public class Utils {

    public static <T> T findQualifier(InjectionPoint injectionPoint, Class<T> qualifierClass, T defaultQualifier) {
        return injectionPoint.getQualifiers()
                .stream()
                .filter( qualifierClass::isInstance )
                .findFirst()
                .map( qualifierClass::cast )
                .orElse( defaultQualifier );
    }


    public static DataSource createDataSource(Config config, String dataSourceName) {
        CustomDatasourceProperties datasourceProperties = CustomDatasourceProperties.from(config, dataSourceName);
        return createDataSource( datasourceProperties );
    }


    public static DataSource createDataSource(CustomDatasourceProperties datasourceProperties) {
        try {
            Object dataSource = datasourceProperties.getDataSourceClass().getConstructor().newInstance();
            invokeMethod(dataSource, "setUrl", datasourceProperties.getUrl() );
            invokeMethod(dataSource, "setUser", datasourceProperties.getUser() );
            invokeMethod(dataSource, "setPassword", datasourceProperties.getPassword() );
            return DataSource.class.cast( dataSource );

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException exception) {
            throw new InjectionException( exception );
        } catch (BookshopException bookshopException)   {
            throw new InjectionException( bookshopException.getCause() );
        }
    }


    private static Object invokeMethod(Object object, String methodName, Object... actualParameters) throws BookshopException {
        Class[] formalParameters = new Class[actualParameters.length ];
        for(int i=0; i < actualParameters.length; ++i) formalParameters[i] = actualParameters[i].getClass();
        try {
            return object.getClass().getMethod(methodName, formalParameters).invoke(object, actualParameters);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException exception) {
            throw new BookshopException(exception);
        }
    }
}
