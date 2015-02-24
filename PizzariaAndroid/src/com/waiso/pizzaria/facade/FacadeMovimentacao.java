/**
 * 
 */
package com.waiso.pizzaria.facade;

import java.util.ArrayList;
import java.util.List;

import com.waiso.pizzaria.abstratas.Classe;
import com.waiso.pizzaria.abstratas.ClasseActivity;
import com.waiso.pizzaria.excecoes.Erro;
import com.waiso.pizzaria.orm.modelos.Movimentacao;
import com.waiso.pizzaria.orm.modelos.enums.TipoMovimento;
import com.waiso.pizzaria.servicos.ServicoMovimentacaoLocal;
import com.waiso.pizzaria.servicos.remotos.ServicoMovimentacaoRemoto;

/**
 *  <br/>
 * Classe que verifica se o dispositivo esta conectado e
 * chama os servicos local e remoto para realizar as operacoes
 * de seus metodos.
 */
public class FacadeMovimentacao extends Classe {

	private static FacadeMovimentacao instancia;
	private ClasseActivity contexto;
	
	public void cadastrar(Movimentacao movimentacao) throws Erro {
		boolean estaConectado = temInternet();
		if (estaConectado) {
			getServicoRemoto().cadastrar(movimentacao);
		}
		getServicoLocal().cadastrar(movimentacao);
	}
	
	public void alterar(Movimentacao movimentacao) throws Erro {
		boolean estaConectado = temInternet();
		if (estaConectado) {
			getServicoRemoto().alterar(movimentacao);
		}
		getServicoLocal().alterar(movimentacao);		
	}
	

	public void excluir(Movimentacao movimentacao) throws Erro {
		boolean estaConectado = temInternet();
		if (estaConectado) {
			getServicoRemoto().excluir(movimentacao);
		}
		getServicoLocal().excluir(movimentacao);
	}

	public List<Movimentacao> pesquisarPorLoginETipoMovimento(String login,
			TipoMovimento tipo, Long dataInicioPesquisa, Long dataFimPesquisa) throws Erro {
		boolean estaConectado = temInternet();
		List<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
		if (estaConectado) {
			movimentacoes = getServicoRemoto().pesquisarPorLoginETipoMovimento(login,
					tipo, dataInicioPesquisa, dataFimPesquisa);
		} else {
			movimentacoes = getServicoLocal().pesquisarPorLoginETipoMovimento(login,
					tipo, dataInicioPesquisa, dataFimPesquisa);
		}
			
		return movimentacoes;
	}
	
	public void pesquisarPorId(Movimentacao movimentacao) throws Erro {
		ServicoMovimentacaoRemoto.getInstancia(contexto).cadastrar(movimentacao);		
	}
	
	public static FacadeMovimentacao getInstancia(ClasseActivity contexto){
		if (naoExiste(instancia)) {
			instancia = new FacadeMovimentacao();
			instancia.contexto = contexto;
		}
		return instancia;
	}
	
	private ServicoMovimentacaoLocal getServicoLocal(){
		return ServicoMovimentacaoLocal.getInstancia(contexto);
	}
	
	private ServicoMovimentacaoRemoto getServicoRemoto(){
		return ServicoMovimentacaoRemoto.getInstancia(contexto);
	}

	private boolean temInternet(){
		return true;
	}

}
