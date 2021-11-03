package com.OsSystem.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.OsSystem.domain.Os;
import com.fasterxml.jackson.annotation.JsonFormat;

public class OsDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataAbertuda;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataFechamento;
	private Integer prioridade;
	@NotEmpty(message = "O campo observações é requerido!")
	private String observacoes;
	private Integer status;
	
	private Integer tecnico;
	
	private Integer cliente;

	public OsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OsDTO(Os obj) {
		super();
		this.id = obj.getId();
		this.dataAbertuda = obj.getDataAbertuda();
		this.dataFechamento = obj.getDataFechamento();
		this.prioridade = obj.getPrioridade().getCod();
		this.observacoes = obj.getObservacoes();
		this.status = obj.getStatus().getCod();
		this.tecnico = obj.getTecnico().getId();
		this.cliente = obj.getCliente().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataAbertuda() {
		return dataAbertuda;
	}

	public void setDataAbertuda(LocalDateTime dataAbertuda) {
		this.dataAbertuda = dataAbertuda;
	}

	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTecnico() {
		return tecnico;
	}

	public void setTecnico(Integer tecnico) {
		this.tecnico = tecnico;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

}
