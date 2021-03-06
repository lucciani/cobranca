package com.lucciani.cobranca.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Titulo {

	/*
	 * @id
	 * 
	 * @GeneratedValue
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	/*
	 * @NotBlack
	 * 
	 * @Size
	 * 
	 */
	@NotBlank(message = "O campo NOME é obrigatório")
	@Size(max = 50, message = "O nome não pode conter mais de 50 caracteres")
	private String nome;

	/*
	 * @NotNull
	 * 
	 * @DateTimeFormat
	 * 
	 * @Temporal
	 * 
	 */
	@NotNull(message = "Data de vencimento é obrigatório")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;

	/*
	 * 
	 * @NumberFormat
	 * 
	 */
	@NotNull(message = "O campo VALOR é obrigatorio")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valor;

	/*
	 * 
	 * @Enumerated
	 * 
	 */
	@Enumerated(EnumType.STRING)
	private StatusTitulo status;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public StatusTitulo getStatus() {
		return status;
	}

	public void setStatus(StatusTitulo status) {
		this.status = status;
	}

	// Metodo para validar o status pendente do titulo
	public boolean isPendente() {
		return StatusTitulo.PENDENTE.equals(this.status);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Titulo other = (Titulo) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
