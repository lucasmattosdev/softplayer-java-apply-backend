package br.com.lucasmattos.softplayer.java.apply.backend.repository;

import br.com.lucasmattos.softplayer.java.apply.backend.entity.PessoaEntity;
import br.com.lucasmattos.softplayer.java.apply.backend.service.PessoaService;
import br.com.lucasmattos.softplayer.java.apply.backend.dto.PaginationDTO;
import br.com.lucasmattos.softplayer.java.apply.backend.dto.PaginationResultDTO;
import br.com.lucasmattos.softplayer.java.apply.backend.dto.PessoaDTO;
import br.com.lucasmattos.softplayer.java.apply.backend.exception.RepositoryException;
import br.com.lucasmattos.softplayer.java.apply.backend.mapper.PessoaMapper;
import br.com.lucasmattos.softplayer.java.apply.backend.util.PessoaUtil;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Named
public class PessoaRepository extends AbstractRepository<PessoaMapper, PessoaService> implements Serializable {

    @Inject
    PessoaMapper mapper;

    public PessoaDTO getById(Long idPessoa){
        return mapper.toDTO(service.getById(idPessoa));
    }

    @Transactional
    public void criarPessoa(PessoaDTO pessoaDTO) throws RepositoryException {
        if (pessoaDTO.getId() != null) throw new RepositoryException(Response.Status.NOT_ACCEPTABLE, "Não pode criar uma pessoa com ID");
        if (validarPessoa(pessoaDTO)) {
            PessoaEntity pessoaEntity = mapper.toObject(pessoaDTO);
            pessoaEntity.setCriadoEm(new Timestamp(System.currentTimeMillis()));
            service.save(pessoaEntity);
        }
    }

    @Transactional
    public void alterarPessoa(PessoaDTO pessoaDTO) throws RepositoryException {
        if (pessoaDTO.getId() == null) throw new RepositoryException(Response.Status.NOT_ACCEPTABLE, "Não é possível localizar a pessoa com este ID");
        if (validarPessoa(pessoaDTO)) {
            PessoaEntity pessoaEntity = mapper.toObject(pessoaDTO);
            pessoaEntity.setUltimaModificacao(new Timestamp(System.currentTimeMillis()));
            service.save(pessoaEntity);
        }
    }

    public boolean validarPessoa(PessoaDTO pessoaDTO) throws RepositoryException {
        if (pessoaDTO.getDataNascimento() != null && !pessoaDTO.getDataNascimento().before(new Date(System.currentTimeMillis()))) throw new RepositoryException(Response.Status.NOT_ACCEPTABLE, "A Data de Nascimento deve ser anterior a Data de Hoje");
        String cpf = pessoaDTO.getCpf().replaceAll("\\D+","");
        if (pessoaDTO.getCpf() != null && !PessoaUtil.validarCpf(cpf)) throw new RepositoryException(Response.Status.NOT_ACCEPTABLE, "O CPF é inválido");
        if (pessoaDTO.getCpf() != null && service.existsOtherCPF(pessoaDTO.getId(), cpf)) throw new RepositoryException(Response.Status.NOT_ACCEPTABLE, "Já existe outra pessoa com este CPF");
        if (pessoaDTO.getEmail() != null && !PessoaUtil.validarEmail(pessoaDTO.getEmail())) throw new RepositoryException(Response.Status.NOT_ACCEPTABLE, "O email é inválido");
        return true;
    }

    @Transactional
    public void deletarPessoa(Long idPessoa){
        service.delete(service.getById(idPessoa));
    }

    public PaginationResultDTO consultarPessoas(PaginationDTO paginationDTO){
        int page = paginationDTO.getPage();
        String orderColumn = paginationDTO.getOrderColumn();
        String orderBy = paginationDTO.getOrderBy()!=null?paginationDTO.getOrderBy().name():null;
        HashMap<String, String> filter = paginationDTO.getFilter();

        List<PessoaEntity> pessoaEntities = service.consultarPessoas(page, orderColumn, orderBy, filter);
        Long countPessoaEntities = service.countTotalPessoas(filter);
        return new PaginationResultDTO(mapper.toDTOs(pessoaEntities), countPessoaEntities);
    }

}
