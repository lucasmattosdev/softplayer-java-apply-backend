package br.com.lucasmattos.softplayer.java.apply.backend.dto;

import br.com.lucasmattos.softplayer.java.apply.backend.enums.OrderByEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Setter
@Getter
public class PaginationDTO {
    private int page = 1;
    private String orderColumn;
    private OrderByEnum orderBy;
    private HashMap<String, String> filter = new HashMap<>();
}
