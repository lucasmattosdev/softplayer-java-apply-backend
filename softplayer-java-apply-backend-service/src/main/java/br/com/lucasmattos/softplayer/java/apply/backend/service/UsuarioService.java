package br.com.lucasmattos.softplayer.java.apply.backend.service;

import br.com.lucasmattos.softplayer.java.apply.backend.entity.UsuarioEntity;
import org.hibernate.query.Query;

import javax.inject.Named;

@Named
public class UsuarioService extends AbstractService<UsuarioEntity>{

    public UsuarioEntity getUserByNomeSenha(String nome, String senha){
        String hql = "from UsuarioEntity as usuario where usuario.nome = :nome AND usuario.senha = :senha";
        Query query = getSession().createQuery(hql);
        query.setParameter("nome", nome);
        query.setParameter("senha", senha);
        return (UsuarioEntity) query.uniqueResult();
    }

    @Override
    protected Class<UsuarioEntity> getServiceClass() {
        return UsuarioEntity.class;
    }
}
