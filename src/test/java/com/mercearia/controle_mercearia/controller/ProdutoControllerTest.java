package com.mercearia.controle_mercearia.controller;

import com.mercearia.controle_mercearia.model.Produto;
import com.mercearia.controle_mercearia.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ProdutoController.class)
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProdutoService produtoService;

    @InjectMocks
    private ProdutoController produtoController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(produtoController).build();
    }

    @Test
    public void testCriarProduto() throws Exception {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Arroz");
        produto.setPreco(10.50);
        produto.setQuantidadeEstoque(100);

        Mockito.when(produtoService.criarProduto(Mockito.any(Produto.class))).thenReturn(produto);

        mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Arroz\",\"preco\":10.50,\"quantidadeEstoque\":100}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Arroz"))
                .andExpect(jsonPath("$.preco").value(10.50))
                .andExpect(jsonPath("$.quantidadeEstoque").value(100));
    }

    @Test
    public void testBuscarProdutoPorId() throws Exception {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Arroz");
        produto.setPreco(10.50);
        produto.setQuantidadeEstoque(100);

        Mockito.when(produtoService.buscarProdutoPorId(1L)).thenReturn(produto);

        mockMvc.perform(get("/produtos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Arroz"))
                .andExpect(jsonPath("$.preco").value(10.50))
                .andExpect(jsonPath("$.quantidadeEstoque").value(100));
    }

    @Test
    public void testExcluirProduto() throws Exception {
        Mockito.doNothing().when(produtoService).excluirProduto(1L);

        mockMvc.perform(delete("/produtos/1"))
                .andExpect(status().isNoContent());
    }
}
