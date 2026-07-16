package cmms.humanresource.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.AccessDeniedException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<APIResponse> handleCustomerNotFoundException(CustomerNotFoundException ex, WebRequest request) {
        APIResponse response = new APIResponse(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<APIResponse> handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
        HttpStatus status = HttpStatus.resolve(ex.getStatusCode().value());
        String errorName = (status != null) ? status.getReasonPhrase() : "Error";

        APIResponse response = new APIResponse(
                ex.getStatusCode().value(),
                errorName,
                ex.getReason(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(response, ex.getStatusCode());
    }

    @ExceptionHandler(SecurityRoleException.class)
    public ResponseEntity<APIResponse> handleSecurityRoleException(SecurityRoleException ex, WebRequest request) {
        APIResponse response = new APIResponse(
                HttpStatus.FORBIDDEN.value(),
                "Forbidden Role Assignment",
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<APIResponse> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        APIResponse response = new APIResponse(
                HttpStatus.FORBIDDEN.value(),
                "Access Denied",
                "You lack the necessary privileges for this resource.",
                request.getDescription(false)
        );
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        APIResponse response = new APIResponse(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(org.springframework.web.servlet.resource.NoResourceFoundException.class)
    public ResponseEntity<APIResponse> handleNoResourceFoundException(org.springframework.web.servlet.resource.NoResourceFoundException ex, WebRequest request) {
        APIResponse response = new APIResponse(
                HttpStatus.NOT_FOUND.value(),
                "Endpoint Not Found",
                "The requested URL path does not exist. Please check your spelling.",
                request.getDescription(false)
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HumanResourceCheckedException.class)
    public ResponseEntity<APIResponse> handleHRCheckedException(HumanResourceCheckedException ex, WebRequest request) {
        APIResponse response = new APIResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Process Business Error",
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse> handleValidationErrors(MethodArgumentNotValidException ex, WebRequest request) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));

        APIResponse response = new APIResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed",
                errors,
                request.getDescription(false)
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<APIResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        String msg = String.format("Parameter '%s' must be of type %s", ex.getName(), ex.getRequiredType().getSimpleName());
        APIResponse response = new APIResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Type Mismatch",
                msg,
                request.getDescription(false)
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(java.util.NoSuchElementException.class)
    public ResponseEntity<APIResponse> handleNoSuchElementException(java.util.NoSuchElementException ex, WebRequest request) {
        APIResponse response = new APIResponse(
                HttpStatus.NOT_FOUND.value(),
                "Resource Not Found",
                "The requested record does not exist in the system.",
                request.getDescription(false)
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<APIResponse> handleNullPointerException(NullPointerException ex, WebRequest request) {
        APIResponse response = new APIResponse(
                HttpStatus.NOT_FOUND.value(),
                "Data Error",
                "The requested resource was not found or contains empty data.",
                request.getDescription(false)
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


}
