package br.com.lucasmattos.softplayer.java.apply.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class PaginationResultDTO<T extends AbstractEntityDTO> {
    private List<T> results;
    private Long totalResultCount;
}
