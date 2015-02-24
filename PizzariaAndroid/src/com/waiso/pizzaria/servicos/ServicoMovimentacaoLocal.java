package com.waiso.pizzaria.servicos;

import java.util.List;

import android.content.Context;

import com.waiso.pizzaria.abstratas.Classe;
import com.waiso.pizzaria.abstratas.ClasseActivity;
import com.waiso.pizzaria.excecoes.Erro;
import com.waiso.pizzaria.excecoes.SysErr;
import com.waiso.pizzaria.orm.bo.BOMovimentacao;
import com.waiso.pizzaria.orm.modelos.Movimentacao;
import com.waiso.pizzaria.orm.modelos.enums.TipoMovimento;

public class ServicoMovimentacaoLocal extends Classe {

	private static ServicoMovimentacaoLocal instancia;
	private ClasseActivity contexto;
	
	public void cadastrar(Movimentacao o) throws Erro{
		getBO().inserir(o);
	}
	
	public void alterar(Movimentacao o) throws Erro {
		getBO().alterar(o);
	}
	
	public void excluir(Movimentacao movimentacao) throws SysErr {
		getBO().excluir(movimentacao);
	}
	
	
	public List<Movimentacao> pesquisarPorLoginETipoMovimento(String login, TipoMovimento tipo, Long dataInicioPesquisa, Long dataFimPesquisa) {
		return getBO().pesquisarPorLoginETipoMovimento(login, tipo, dataInicioPesquisa, dataFimPesquisa);
	}
	
	public static ServicoMovimentacaoLocal getInstancia(ClasseActivity contexto){
		if (naoExiste(instancia)) {
			instancia = new ServicoMovimentacaoLocal();
			instancia.contexto = contexto;
		}
		return instancia;
	}
	
	private BOMovimentacao getBO(){
		return BOMovimentacao.getInstancia(contexto);
	}
}
