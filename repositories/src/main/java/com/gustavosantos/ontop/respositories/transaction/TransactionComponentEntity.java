package com.gustavosantos.ontop.respositories.transaction;

import com.gustavosantos.ontop.core.domain.TransactionComponent;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "transaction_components")
public class TransactionComponentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false)
    private TransactionComponent.Type type;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    public static TransactionComponentEntity from(TransactionComponent component) {
        return new TransactionComponentEntityBuilder()
                .type(component.type())
                .value(component.value())
                .build() ;
    }

}
