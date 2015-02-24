package com.waiso.pizzaria.servicos.remotos;

import java.util.List;

import com.waiso.pizzaria.abstratas.Classe;
import com.waiso.pizzaria.abstratas.ClasseActivity;
import com.waiso.pizzaria.excecoes.Erro;
import com.waiso.pizzaria.orm.modelos.Movimentacao;
import com.waiso.pizzaria.orm.modelos.enums.TipoMovimento;
import com.waiso.pizzaria.ws.implementacoes.MovimentacaoWS;

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
