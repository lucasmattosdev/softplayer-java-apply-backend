package br.com.lucasmattos.softplayer.java.apply.backend.service;

import br.com.lucasmattos.softplayer.java.apply.backend.entity.PessoaEntity;
import org.hibernate.query.Query;

import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Named
public class PessoaService extends AbstractService<PessoaEntity>{

    public List<PessoaEntity> consultarPessoas(int page, String orderColumn, String orderBy, Map<String, String> filter) {
        StringBuilder hql = new StringBuilder("FROM PessoaEntity WHERE 1=1 ");
        for (Map.Entry<String, String> pair: filter.entrySet()){
            hql.append("AND ").append(pair.getKey()).append(" ilike ").append(pair.getValue()).append(" ");
        }
        if (orderColumn != null && orderBy != null){
            hql.append("ORDER BY ").append(orderColumn).append(" ").append(orderBy);
        }
        Query query = getSession().createQuery(hql.toString());
        query.setFirstResult((page-1) * 10);
        query.setMaxResults(10);
        return query.getResultList();
    }

    public Long countTotalPessoas(Map<String, String> filter){
        StringBuilder hql = new StringBuilder("SELECT count(*) FROM PessoaEntity WHERE 1=1 ");
        for (Map.Entry<String, String> pair: filter.entrySet()){
            hql.append("AND ").append(pair.getKey()).append(" ilike ").append(pair.getValue()).append(" ");
        }
        Query query = getSession().createQuery(hql.toString());
        return (Long) query.uniqueResult();
    }

    @Transactional
    public boolean existsOtherCPF(Long idPessoa, String cpfPessoa){
        String hql = "FROM PessoaEntity as pessoa WHERE pessoa.cpf = :cpfPessoa ";
        if (idPessoa != null){
            hql += "AND pessoa.id != :idPessoa ";
        }
        Query query = getSession().createQuery(hql);
        if (idPessoa != null) {
            query.setParameter("idPessoa", idPessoa);
        }
        query.setParameter("cpfPessoa", cpfPessoa);
        return !query.list().isEmpty();
    }

    @Override
    protected Class<PessoaEntity> getServiceClass() {
        return PessoaEntity.class;
    }

}
