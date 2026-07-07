package cmms.humanresource.aspect;

import cmms.humanresource.common.entity.dto.AuditLogsRequestDto;
import cmms.humanresource.aspect.AuditLoggable;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditLogAspect {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private static final String AUDIT_SERVICE_URL = "http://localhost:8085/System/AddAuditLogs";

    @AfterReturning(value = "@annotation(auditLoggable)", returning = "result")
    @Async
    public void logAudit(JoinPoint joinPoint, AuditLoggable auditLoggable, Object result) {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

            String ipAddress = request.getRemoteAddr();

            String userIdHeader = request.getHeader("X-User-Id");
            Long performedById = (userIdHeader != null) ? Long.parseLong(userIdHeader) : 0L;

            Long recordId = extractRecordId(joinPoint, result);

            String changedData = "";
            if (result != null) {
                changedData = objectMapper.writeValueAsString(result);
            } else if (joinPoint.getArgs().length > 0) {
                changedData = objectMapper.writeValueAsString(joinPoint.getArgs());
            }

            AuditLogsRequestDto auditLog = AuditLogsRequestDto.builder()
                    .tableName(auditLoggable.tableName())
                    .action(auditLoggable.action())
                    .recordId(recordId)
                    .changedData(changedData)
                    .performedById(performedById)
                    .ipAddress(ipAddress)
                    .build();

            restTemplate.postForObject(AUDIT_SERVICE_URL, auditLog, Void.class);

        } catch (Exception e) {
            System.err.println("Failed to send audit log: " + e.getMessage());
        }
    }

    private Long extractRecordId(JoinPoint joinPoint, Object result) {

        if (result != null) {
            try {
                Method getIdMethod = result.getClass().getMethod("getId");
                Object id = getIdMethod.invoke(result);
                if (id instanceof Long) {
                    return (Long) id;
                }
            } catch (Exception ignored) {

            }
        }

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof Long) {
                return (Long) arg;
            }
        }

        return null;
    }
}