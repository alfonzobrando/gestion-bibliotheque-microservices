package es.upsa.ssi.common.qualifiers;

import jakarta.enterprise.util.AnnotationLiteral;
import jakarta.enterprise.util.Nonbinding;
import jakarta.inject.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Qualifier
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
public @interface CustomDataSource {

    public static final String DEFAULT_VALUE = "es.upsa.ssi.common.qualifiers.CustomDataSource.value.DEFAULT";

    @Nonbinding
    public String value() default DEFAULT_VALUE;

    public static class Literal extends AnnotationLiteral<CustomDataSource> implements CustomDataSource {
        public static final CustomDataSource DEFAULT = of(DEFAULT_VALUE);

        public static CustomDataSource of(String value) {
            return new Literal(value);
        }

        private String value;

        private Literal(String value) {
            this.value = value;
        }

        @Override
        public String value() {
            return value;
        }
    }
}
