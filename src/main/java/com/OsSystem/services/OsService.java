package com.OsSystem.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OsSystem.domain.Cliente;
import com.OsSystem.domain.Os;
import com.OsSystem.domain.Tecnico;
import com.OsSystem.domain.enuns.Prioridade;
import com.OsSystem.domain.enuns.Status;
import com.OsSystem.dto.OsDTO;
import com.OsSystem.repositorys.OsRepository;
import com.OsSystem.services.exception.ObjectNotFoundException;

@Service
public class OsService {

	@Autowired
	private OsRepository osRepository;

	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;

	public Os findById(Integer id) {
		Optional<Os> obj = osRepository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado Id: " + id + "Tipo" + Os.class.getName()));
	}
	
	public List<Os> findAll(){
		return osRepository.findAll();
	}

	public Os create(@Valid OsDTO obj){
		return fromDTO(obj);
	}

	public Os update(@Valid OsDTO obj){
		findById(obj.getId());
		return fromDTO(obj);
	}

	private Os fromDTO(OsDTO obj){
		Os newObj = new Os();
		newObj.setId(obj.getId());
		newObj.setObservacoes(obj.getObservacoes());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		newObj.setStatus(Status.toEnum(obj.getStatus()));

		Tecnico tec = tecnicoService.findById(obj.getTecnico());
		Cliente cli = clienteService.findById(obj.getCliente());

		newObj.setTecnico(tec);
		newObj.setCliente(cli);

		if(newObj.getStatus().getCod().equals(2)){
			newObj.setDataFechamento(LocalDateTime.now());
		}

		return osRepository.save(newObj);


	}

}
