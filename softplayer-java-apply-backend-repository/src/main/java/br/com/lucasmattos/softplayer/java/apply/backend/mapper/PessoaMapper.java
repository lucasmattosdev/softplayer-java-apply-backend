package br.com.lucasmattos.softplayer.java.apply.backend.mapper;

import br.com.lucasmattos.softplayer.java.apply.backend.dto.PessoaDTO;
import br.com.lucasmattos.softplayer.java.apply.backend.entity.PessoaEntity;
import br.com.lucasmattos.softplayer.java.apply.backend.service.PessoaService;
import br.com.lucasmattos.softplayer.java.apply.backend.util.PessoaUtil;

import javax.inject.Inject;

public class PessoaMapper implements AbstractMapper<PessoaEntity, PessoaDTO> {

    @Inject
    PessoaService pessoaService;

    @Override
    public PessoaDTO toDTO(PessoaEntity obj) {
        if (obj == null) {
            return null;
        }
        PessoaDTO dto = new PessoaDTO();
        dto.setId(obj.getId());
        dto.setNome(obj.getNome());
        dto.setSexo(obj.getSexo());
        dto.setEmail(obj.getEmail());
        dto.setDataNascimento(obj.getDataNascimento());
        dto.setNaturalidade(obj.getNaturalidade());
        dto.setNacionalidade(obj.getNacionalidade());
        dto.setCpf(PessoaUtil.formatarCpf(obj.getCpf()));
        dto.setCriadoEm(obj.getCriadoEm());
        dto.setUltimaModificacao(obj.getUltimaModificacao());
        return dto;
    }

    @Override
    public PessoaEntity toObject(PessoaDTO dto) {
        PessoaEntity obj = new PessoaEntity();
        if (dto.getId() != null) {
            obj = pessoaService.getById(dto.getId());
        }
        if (dto.getNome() != null) {
            obj.setNome(dto.getNome());
        }
        if (dto.getSexo() != null) {
            obj.setSexo(dto.getSexo());
        }
        if (dto.getEmail() != null) {
            obj.setEmail(dto.getEmail());
        }
        if (dto.getDataNascimento() != null) {
            obj.setDataNascimento(dto.getDataNascimento());
        }
        if (dto.getNaturalidade() != null) {
            obj.setNaturalidade(dto.getNaturalidade());
        }
        if (dto.getNacionalidade() != null) {
            obj.setNacionalidade(dto.getNacionalidade());
        }
        if (dto.getCpf() != null) {
            obj.setCpf(dto.getCpf().replaceAll("\\D+",""));
        }
        return obj;
    }
}
