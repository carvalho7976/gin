package br.ufc.quixada.cti.gin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public enum Situacao {
	USO,AUXENTE,DEPOSITO,BAIXA,MANUTENCAO,TERMO_RESPONSABILIDADE
}