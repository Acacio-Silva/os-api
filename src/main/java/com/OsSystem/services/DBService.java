package com.OsSystem.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OsSystem.domain.Cliente;
import com.OsSystem.domain.Os;
import com.OsSystem.domain.Tecnico;
import com.OsSystem.domain.enuns.Prioridade;
import com.OsSystem.domain.enuns.Status;
import com.OsSystem.repositorys.ClienteRepository;
import com.OsSystem.repositorys.OsRepository;
import com.OsSystem.repositorys.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tr;

	@Autowired
	private ClienteRepository cr;

	@Autowired
	private OsRepository or;

	public void instanciaDB() {

		Tecnico t1 = new Tecnico(null, "Tecnico 1", "881.502.830-72", "(88) 9 9999-9999");
		Tecnico t2 = new Tecnico(null, "Tecnico 2", "329.682.930-01", "(88) 9 9999-9999");
		Cliente c1 = new Cliente(null, "cliente 1", "327.651.670-52", "(88) 9 8888-8888");
		Cliente c2 = new Cliente(null, "cliente 2", "266.451.700-76", "(88) 9 8888-8888");
		Os os1 = new Os(null, Prioridade.ALTA, "teste de criação", Status.ABERTO, t1, c1);

		t1.getList().add(os1);
		t2.getList().add(os1);
		c1.getList().add(os1);

		tr.saveAll(Arrays.asList(t1, t2));
		cr.saveAll(Arrays.asList(c1, c2));
		or.saveAll(Arrays.asList(os1));
	}

}
