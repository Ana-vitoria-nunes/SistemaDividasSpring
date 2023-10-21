package org.example.core.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Component
public class Costumer {

        @Id
        @Column(name = "id_Cliente")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id_Cliente;

        @NotBlank
        @Column(name = "external_Id",unique = true)
        private String externalId;

        @Column(nullable = false, length = 250, unique = true)
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z ]*$", message = "O campo nome permite apenas letras")
        private String name;

        @Column(nullable = false, length = 250, unique = true)
        @Email(message = "O email precisa conter '.' e '@'")  //Tratar o email
        private String email;

        @Column(nullable = false,unique = true)
        private String password;

        @JsonFormat(pattern = "dd/MM/yyyy")
        @Column(name = "data_Nascimento")
        private LocalDate birthDate ;

        @CPF
        @Column(nullable = false, unique = true) // -> tratar cpf
        private String cpf;

        @Column(nullable = false, length = 15, unique = true)
        @Pattern(regexp = "^[0-9 ]*$", message = "O telefone permite apenas números")
        private String phone;

        public void generateAndSetExternalId() {
              this.externalId = UUID.randomUUID().toString();
        }


}
