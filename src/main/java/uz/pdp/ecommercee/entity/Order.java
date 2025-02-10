package uz.pdp.ecommercee.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
@Table(name = "orders")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    @SequenceGenerator(name = "order_id_seq",sequenceName ="order_id_seq",initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "order_id_seq")
    private Integer id;

    @CreationTimestamp
    private LocalDate orderedAt;


}
