package com.fabiano.pizzaria.servicos.remotos;

import java.util.List;

import com.fabiano.pizzaria.abstratas.Classe;
import com.fabiano.pizzaria.abstratas.ClasseActivity;
import com.fabiano.pizzaria.excecoes.Erro;
import com.fabiano.pizzaria.orm.modelos.Movimentacao;
import com.fabiano.pizzaria.orm.modelos.enums.TipoMovimento;
import com.fabiano.pizzaria.ws.implementacoes.MovimentacaoWS;

public class ServicoMovimentacaoRemoto extends Classe {

	private static ServicoMovimentacaoRemoto instancia;
	private ClasseActivity contexto;
	
	public void cadastrar(Movimentacao o) throws Erro{
		//getBO().inserir(o);
		MovimentacaoWS.getInstancia(contexto).cadastrar(o);
	}
	
	public void alterar(Movimentacao o) throws Erro {
		//getBO().alterar(o);
		MovimentacaoWS.getInstancia(contexto).alterar(o);
	}
	
	public void excluir(Movimentacao o) throws Erro {
		//getBO().excluir(movimentacao);
		MovimentacaoWS.getInstancia(contexto).excluir(o);
	}
	
	
	public List<Movimentacao> pesquisarPorLoginETipoMovimento(String login, TipoMovimento tipo, Long dataInicioPesquisa, Long dataFimPesquisa) throws Erro {
		//return getBO().pesquisarPorLoginETipoMovimento(login, tipo, dataInicioPesquisa, dataFimPesquisa);
		return MovimentacaoWS.getInstancia(contexto).pesquisarPorLoginETipoMovimento(login, tipo, dataInicioPesquisa, dataFimPesquisa);
	}
	
	public static ServicoMovimentacaoRemoto getInstancia(ClasseActivity contexto){
		if (naoExiste(instancia)) {
			instancia = new ServicoMovimentacaoRemoto();
			instancia.contexto = contexto;
		}
		return instancia;
	}
}
