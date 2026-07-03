package cmms.humanresource.entity;

import cmms.humanresource.usercontext.UserContext;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SoftDelete(strategy = SoftDeleteType.DELETED, columnName = "is_deleted")
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "contact_number", nullable = false)
    private String contactNumber;

    @Email(message = "Invalid email format")
    @Column(nullable = false)
    private String email;

    @Column(columnDefinition = "text", nullable = false)
    private String address;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @CreatedDate
    @Builder.Default
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.parse("2026-06-23T19:54:30");

    @CreatedBy
    @Builder.Default
    @Column(name = "created_by", nullable = false)
    private Long createdBy = 1L;

    @LastModifiedDate
    @Builder.Default
    @Column(name = "last_modified_at", nullable = false)
    private LocalDateTime lastModifiedAt = LocalDateTime.parse("2026-06-23T19:54:30");

    @LastModifiedBy
    @Builder.Default
    @Column(name = "last_modified_by", nullable = false)
    private Long lastModifiedBy = 1L;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now(java.time.ZoneOffset.UTC);
        this.lastModifiedAt = LocalDateTime.now(java.time.ZoneOffset.UTC);

        this.createdBy = UserContext.getUserId();
        this.lastModifiedBy = UserContext.getUserId();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastModifiedAt = LocalDateTime.now(java.time.ZoneOffset.UTC);
        this.lastModifiedBy = UserContext.getUserId();
    }

}
