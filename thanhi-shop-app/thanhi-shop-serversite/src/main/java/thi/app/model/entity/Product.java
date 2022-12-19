package thi.app.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_products")
@Indexed
public class Product extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(length = 255)
    @FullTextField
    private String name;

    @Size(max = 256)
    @Column(length = 256)
    private String imageUrl;

    @Min(1)
    @Column
    private BigDecimal sellPrice;

    @Min(1)
    @Column
    private BigDecimal originPrice;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "sub_cate_id")
    private SubCategory subCategory;

    @Column
    private Instant hot = Instant.now();

    @Column
    @Min(0)
    private Integer quantity;

    @Size(min = 1,max = 500)
    @Column(length = 500)
    private String description;

    @Min(0)
    @Column
    private Integer sold;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<OrderDetail> orderDetailList;
}
