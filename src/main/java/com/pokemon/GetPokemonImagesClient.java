package com.pokemon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class GetPokemonImagesClient {

    private static final Logger LOG = LoggerFactory.getLogger(GetPokemonImagesClient.class);

    @Autowired
    RestTemplateClient restTemplate;


    @Value("${pokemon.get.url}")
    private String pokemonServiceUrl;



    public PokemonResponse getResponse() {
        PokemonResponse pokemonResponse = null;
        try {
            LOG.info("Making API call to-> "+ pokemonServiceUrl);
            pokemonResponse = restTemplate.restTemplate().getForObject(pokemonServiceUrl, PokemonResponse.class);
            LOG.info("API call to "+ pokemonServiceUrl + " has recieved a response.");

        } catch (Exception e) {
            LOG.error(String.format("Error calling service[%s]", pokemonServiceUrl), e.getMessage());
        }
        return pokemonResponse;
    }
}
