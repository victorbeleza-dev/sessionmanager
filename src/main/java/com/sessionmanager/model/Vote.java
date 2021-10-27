package com.sessionmanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vote implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "idPauta cannot be null")
    private Long idAssociated;

    @NotNull(message = "idSession cannot be null")
    private Long idSession;

    @NotBlank(message = "vote cannot be null")
    private String vote;

    @NotBlank(message = "CPF cannot be null")
    private String CPF;

    private Date dtVote;
}
