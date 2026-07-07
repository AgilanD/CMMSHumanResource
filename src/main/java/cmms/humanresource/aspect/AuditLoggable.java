package cmms.humanresource.aspect;


import cmms.humanresource.common.entity.dto.AuditLogsRequestDto;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditLoggable {
    String tableName() default "employee";
    AuditLogsRequestDto.AuditAction action();
}
