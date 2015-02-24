/**
 * 
 */
package com.waiso.pizzaria.orm.bo;

import java.util.List;

import android.util.Log;

import com.waiso.pizzaria.abstratas.Classe;
import com.waiso.pizzaria.abstratas.ClasseActivity;
import com.waiso.pizzaria.excecoes.Erro;
import com.waiso.pizzaria.excecoes.SysErr;
import com.waiso.pizzaria.interfaces.IMovimentacaoBO;
import com.waiso.pizzaria.orm.dao.DAOMovimentacao;
import com.waiso.pizzaria.orm.dao.finder.FinderMovimentacao;
import com.waiso.pizzaria.orm.modelos.Movimentacao;
import com.waiso.pizzaria.orm.modelos.enums.TipoMovimento;

/**
 * 
 *
 */
public class BOMovimentacao extends Classe implements IMovimentacaoBO {

	private static BOMovimentacao instancia;
	private ClasseActivity contexto;
	
	private BOMovimentacao(ClasseActivity contexto){
		this.contexto = contexto;
	}
	
	@Override
	public void inserir(Movimentacao o) throws Erro {
		Log.i(CLASSE_NOME, "Inserindo "+getNomeEntidade());
		getDAO().incluir(o);
	}

	@Override
	public void alterar(Movimentacao o) throws SysErr {
		log("Alterando "+getNomeEntidade());
		getDAO().atualizar(o);
	}

	@Override
	public void excluir(Movimentacao o) throws SysErr {
		log("Excluindo "+getNomeEntidade());
		getDAO().excluir(o);
	}
	
	public Movimentacao pesquisarPorCodigo (String codigo){
		if (existe(codigo) && !codigo.isEmpty()) {
			log(getNomeEntidade()+" pesquisando por codigo: "+codigo);
			Movimentacao m = new Movimentacao();
			m.comCodigo(Long.valueOf(codigo));
			return getFinder().findById(m);
		}
		return null;		
	}
	
	public List<Movimentacao> pesquisarPorLoginETipoMovimento(String login, TipoMovimento tipo, Long dataInicioPesquisa, Long dataFimPesquisa){
		if (existe(login) && !login.isEmpty()) {
			log(getNomeEntidade()+" pesquisando por login: "+login);
			return getFinder().pesquisarPorLoginETipoMovimento(login, tipo, dataInicioPesquisa, dataFimPesquisa);
		}
		return null;
	}
	
	public List<Movimentacao> listar(){
		return getFinder().listar();
	}
	
	private String getNomeEntidade(){
		return CLASSE_NOME.substring(2);
	}
	
	public static BOMovimentacao getInstancia(ClasseActivity contexto) {
		if(instancia == null){
			instancia = new BOMovimentacao(contexto);
			instancia.contexto = contexto;
		}
		return instancia;
	}
	
	private DAOMovimentacao getDAO(){
		return DAOMovimentacao.getInstancia(contexto);
	}
	
	private FinderMovimentacao getFinder(){
		return FinderMovimentacao.getInstancia(contexto);
	}
}
