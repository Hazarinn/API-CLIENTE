package com.api.clientes.controller;


import com.api.clientes.entity.Cliente;
import com.api.clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarTodosClientes(){

        return clienteService.buscaTodos();
    }

    @GetMapping("/detalhar")
    public Cliente listarClientePorEmail(@RequestParam("email")String email){
        return clienteService.buscarPorEmail(email);
    }

    @GetMapping("/{id}")
    public Cliente buscarClientePorId(@PathVariable("id") Long id){
        return clienteService.buscarPorId(id);
    }

    @GetMapping("/buscaPaginada")
    public Page<Cliente> buscaPorPaginacao(@RequestParam("busca")String busca, @RequestParam(value = "page", required = false, defaultValue ="1")
    int page, @RequestParam(value = "size", required = false, defaultValue = "10")int size){
        return clienteService.buscaPaginada(busca, page, size);
    }

    @GetMapping("/todosPaginado")
    public Page<Cliente> pegaTodosPaginado(){
        return clienteService.buscaTodosPaginado();
    }


    @PostMapping
    public Cliente cadastrar(@RequestBody Cliente cliente){
        return clienteService.inserir(cliente);
    }

    @PutMapping("/{id}")
    public Cliente alterar(@PathVariable("id") Long id, @RequestBody  Cliente cliente){
        return clienteService.atualizar(cliente,id);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable("id") Long id) {
         clienteService.excluir(id);
    }




}
