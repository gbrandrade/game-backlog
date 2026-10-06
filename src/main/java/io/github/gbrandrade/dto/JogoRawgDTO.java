package io.github.gbrandrade.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JogoRawgDTO {

    private Long id;
    private String name;

    @JsonProperty("background_image")
    private String imagemCapa;

    @JsonProperty("released")
    private String dataLancamento;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getImagemCapa() { return imagemCapa; }
    public void setImagemCapa(String imagemCapa) { this.imagemCapa = imagemCapa; }

    public String getDataLancamento() { return dataLancamento; }
    public void setDataLancamento(String dataLancamento) { this.dataLancamento = dataLancamento; }
}