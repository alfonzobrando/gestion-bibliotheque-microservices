package es.upsa.ssi.common.mpconfig;


import es.upsa.ssi.common.qualifiers.CustomDataSource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@ConfigProperties(prefix = CustomDatasourceProperties.DEFAULT_PREFIX)
public class CustomDatasourceProperties {

    public static final String DEFAULT_PREFIX = "local.datasource";

    private static String createConfigPropertiesPrefixFrom(String dataSourceName) {
        if ( CustomDataSource.DEFAULT_VALUE.equals(dataSourceName) ) return DEFAULT_PREFIX;
        return DEFAULT_PREFIX + "." + dataSourceName;
    }

    public static CustomDatasourceProperties from(Config config, String dataSourceName) {
        String prefix = createConfigPropertiesPrefixFrom(dataSourceName);
        return CustomDatasourceProperties.builder()
                .withDataSourceClass( config.getValue(getPropertyFullName(prefix, "datasource-class"), Class.class ) )
                .withUrl( config.getValue(getPropertyFullName(prefix, "url"), String.class ) )
                .withUser( config.getValue(getPropertyFullName(prefix, "user"), String.class ) )
                .withPassword( config.getValue(getPropertyFullName(prefix, "password"), String.class ) )
                .build();
    }

    private static String getPropertyFullName(String prefix, String propertyName) {
        return prefix + "." + propertyName;
    }


    @ConfigProperty(name = "datasource-class")
    private Class dataSourceClass;
    private String url;
    private String user;
    private String password;
}
