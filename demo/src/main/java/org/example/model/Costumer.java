package org.example.model;

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

        @Column(nullable = false, length = 250, unique = true)
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z ]*$", message = "O campo nome permite apenas letras")
        private String nome;

        @Column(nullable = false, length = 250, unique = true)
        @Email(message = "O email precisa conter '.' e '@'")  //Tratar o email
        private String email;

        @Column(nullable = false,unique = true)
        private String senha;

        @JsonFormat(pattern = "dd/MM/yyyy")
        @Column(name = "data_Nascimento")
        private LocalDate dataNascimento ;

        @CPF
        @Column(nullable = false, unique = true) // -> tratar cpf
        private String cpf;

        @Column(nullable = false, length = 15, unique = true)
        @Pattern(regexp = "^[0-9 ]*$", message = "O telefone permite apenas números")
        private String telefone;


}
