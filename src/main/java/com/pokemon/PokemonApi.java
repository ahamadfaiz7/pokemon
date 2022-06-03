package com.pokemon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


/**
 * @RestController annotation is used in order to simplify the creation of RESTful web services.<br/>
 * It's a convenient annotation that combines @Controller and @ResponseBody, <br/>
 * which eliminates the need to annotate every class with the @ResponseBody annotation.<br/>
 */
@RestController
@RequestMapping("/api/v1/")
public class PokemonApi {

    private static final Logger LOG = LoggerFactory.getLogger(PokemonApi.class);
    private GetPokemonImagesClient getPokemonImagesClient;


    @Autowired
    public void setGetPokemonImagesClient(GetPokemonImagesClient getPokemonImagesClient) {
        this.getPokemonImagesClient = getPokemonImagesClient;
    }


    /**
     * Jackson serializer will take care of converting to json
     *
     * @param
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value ="/images")
    public PokemonResponse processRemoteMessage() {
        try {
            PokemonResponse response = getPokemonImagesClient.getResponse();
            return response;
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return null;
    }
}
