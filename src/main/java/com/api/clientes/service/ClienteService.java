package com.api.clientes.service;


import com.api.clientes.entity.Cliente;
import com.api.clientes.entity.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> buscaTodos(){
        return clienteRepository.findAll();
    }

    public Cliente buscarPorEmail(String email) {
        return clienteRepository.buscarPorEmail(email)
                .orElseThrow(() -> new RuntimeException(String.format("Cliente nao encontrado para o e-mail: %s",email)));
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.buscaPorId(id);

    }

    public Page<Cliente> buscaPaginada(
            String busca,
            int page,
            int size) {
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC ,
                "nome");

        return clienteRepository.buscaPaginada(
                busca.toLowerCase(),
                pageRequest);
    }


    public Page<Cliente> buscaTodosPaginado(){

            int page = 0;
            int size = 10;
            PageRequest pageRequest = PageRequest.of(
                    page,
                    size,
                    Sort.Direction.DESC,
                    "nome");
            return new PageImpl<>(
                    clienteRepository.findAll(),
                    pageRequest, size);
        }






    public Cliente inserir(Cliente cliente){

        return clienteRepository.save(cliente);

    }

    public Cliente atualizar(Cliente cliente, Long id) {

        var clienteEntity = clienteRepository.findById(id).orElse(null);
        if (Objects.nonNull(clienteEntity)) {
            clienteEntity.setNome(cliente.getNome());
            clienteEntity.setEmail(cliente.getEmail());
            clienteEntity.setTelefone(cliente.getTelefone());
            clienteEntity.setTelefone(cliente.getEndereco());
            return clienteRepository.save(clienteEntity);


        }
    throw new RuntimeException(String.format("Cliente não encontrado na base com e-mail: %s",cliente.getEmail()));

    }

   public void excluir(Long id){
        clienteRepository.excluirCliente(id);

    }

}
