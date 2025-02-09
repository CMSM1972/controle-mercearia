package com.mercearia.controle_mercearia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do produto é obrigatório")
    private String nome;

    @Positive(message = "O preço deve ser maior que zero")
    private Double preco;

    @Min(value = 0, message = "A quantidade em estoque não pode ser negativa")
    private Integer quantidadeEstoque;

    // Construtor personalizado (opcional)
    public Produto(String nome, Double preco, Integer quantidadeEstoque) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }
}