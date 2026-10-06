package io.github.gbrandrade.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespostaBuscaRawgDTO {
    private List<JogoRawgDTO> results;

    public List<JogoRawgDTO> getResults() { return results; }
    public void setResults(List<JogoRawgDTO> results) { this.results = results; }
}