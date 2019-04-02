package br.com.fiap.orderservice.controllers;

import br.com.fiap.orderservice.dto.OrderDTO;
import br.com.fiap.orderservice.exceptions.EntityNotFoundException;
import br.com.fiap.orderservice.exceptions.ExceptionResponse;
import br.com.fiap.orderservice.exceptions.OrderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("orders")
public class OrderController {

    @GetMapping()
    public ResponseEntity<ArrayList<OrderDTO>> all(){

        log.info("All orders");

        ArrayList<OrderDTO> orders = new ArrayList<>();

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<OrderDTO> findById(@PathVariable(value = "uuid", required = true) String uuid){

        log.info("Load order {}", uuid);

        if(uuid != "e442a9e68eb2d051adcc6af6f8a56c54c92279be")
            throw new OrderNotFoundException("Ordem de serviço não encontrada");

        OrderDTO orderDTO = new OrderDTO();

        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<OrderDTO> save(@RequestBody OrderDTO orderDTO){

        log.info("Save order {}", orderDTO);

        return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<OrderDTO> update(@PathVariable("uuid") String uuid, @RequestBody OrderDTO orderDTO) throws Exception {

        log.info("Update order {} {}", uuid, orderDTO);

        if(uuid != "e442a9e68eb2d051adcc6af6f8a56c54c92279be")
            throw new EntityNotFoundException("Registro inválido");

        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<OrderDTO> delete(@PathVariable("uuid") String uuid){

        log.info("Delete order {}", uuid);

        return new ResponseEntity(uuid, HttpStatus.OK);
    }

}
