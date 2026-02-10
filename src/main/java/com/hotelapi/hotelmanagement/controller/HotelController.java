package com.hotelapi.hotelmanagement.controller;

import com.hotelapi.hotelmanagement.infrastructure.model.Hotel;
import com.hotelapi.hotelmanagement.business.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Tag(name = "Hotéis", description = "Endpoints para gerenciamento de hotéis")
@RestController
@RequestMapping("/hoteis")
@RequiredArgsConstructor
@CrossOrigin("*")
public class HotelController {

    private final HotelService hotelService;

    @Operation(
            summary = "Cadastrar hotel",
            description = "Realiza o cadastro de um novo hotel no sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Hotel criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<Hotel> criar(
            @RequestBody
            @Parameter(description = "Dados do hotel a ser criado")
            Hotel hotel) {

        Hotel criado = hotelService.criarHotel(hotel);

        return ResponseEntity
                .created(URI.create("/hoteis/" + criado.getId()))
                .body(criado);
    }


    @Operation(
            summary = "Listar todos os hotéis",
            description = "Retorna a lista completa de hotéis cadastrados"
    )
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<Hotel>> listarTodos() {

        List<Hotel> lista = hotelService.listarTodos();

        return ResponseEntity.ok(lista);
    }


    @Operation(
            summary = "Buscar hotel por ID",
            description = "Retorna um hotel específico pelo seu identificador"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Hotel encontrado"),
            @ApiResponse(responseCode = "404", description = "Hotel não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> buscarPorId(
            @Parameter(description = "ID do hotel", example = "1")
            @PathVariable Long id) {

        Hotel hotel = hotelService.buscarPorId(id);

        return ResponseEntity.ok(hotel);
    }


    @Operation(
            summary = "Buscar hotéis por cidade",
            description = "Retorna todos os hotéis localizados na cidade informada"
    )
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @GetMapping("/cidade/{cidade}")
    public ResponseEntity<List<Hotel>> buscarPorCidade(
            @Parameter(description = "Nome da cidade", example = "Fortaleza")
            @PathVariable String cidade) {

        return ResponseEntity.ok(
                hotelService.buscarPorCidade(cidade)
        );
    }


    @Operation(
            summary = "Buscar hotéis por categoria",
            description = "Retorna todos os hotéis de uma determinada categoria"
    )
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Hotel>> buscarPorCategoria(
            @Parameter(description = "Categoria do hotel", example = "Luxo")
            @PathVariable String categoria) {

        return ResponseEntity.ok(
                hotelService.buscarPorCategoria(categoria)
        );
    }


    @Operation(
            summary = "Atualizar hotel",
            description = "Atualiza os dados de um hotel existente"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Hotel atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Hotel não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Hotel> atualizar(
            @Parameter(description = "ID do hotel")
            @PathVariable Long id,

            @RequestBody
            @Parameter(description = "Dados atualizados do hotel")
            Hotel hotel) {

        Hotel atualizado = hotelService.atualizarHotel(id, hotel);

        return ResponseEntity.ok(atualizado);
    }


    @Operation(
            summary = "Remover hotel",
            description = "Exclui um hotel do sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Hotel removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Hotel não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(
            @Parameter(description = "ID do hotel")
            @PathVariable Long id) {

        hotelService.removerHotel(id);

        return ResponseEntity.noContent().build();
    }
}
