package thi.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.io.Serializable;
import java.time.Instant;

/**
 * Base abstract class for DTO which will hold definitions for created, last modified by and created,
 * last modified by date.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractDTOEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ReadOnlyProperty
    private Instant createDate = Instant.now();

    @ReadOnlyProperty
    private String createBy;

    private Instant modifiedDate = Instant.now();

    private String modifiedBy;
}
