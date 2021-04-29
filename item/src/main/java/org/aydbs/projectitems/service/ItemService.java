package org.aydbs.projectitems.service;

import org.aydbs.projectitems.dao.ItemRepository;
import org.aydbs.projectitems.dto.RegionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class ItemService {
    private final ItemRepository itemRepository;
    private final RestTemplate restTemplate;

    public ItemService(ItemRepository repository,  RestTemplate restTemplate){
        this.itemRepository = repository;
        this.restTemplate = restTemplate;
    }


    public RegionDTO[] findAllRegions(){
        try{
            ResponseEntity<RegionDTO[]> result = restTemplate.getForEntity(
                    "https://geo.api.gouv.fr/regions", RegionDTO[].class);
            System.out.println(result);
            return result.getBody() != null ? result.getBody() : null;
        }catch(RestClientException e){
            return null;
        }
    }
}
