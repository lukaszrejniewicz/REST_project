package pl.zagorski.FootballDataRest.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.zagorski.FootballDataRest.model.ResponseFootballData;

import java.util.Arrays;

@Service
public class FootballDataServiceImpl implements FootballDataService {

    @Value("${api.token.name}")
    private String tokenName;
    @Value("${api.token.value}")
    private String tokenValue;

    private static final String URL = "http://api.football-data.org/v2/competitions/2019/matches";

    @Override
    public ResponseFootballData getAllData() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(tokenName, tokenValue);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>("parameters", httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResponseFootballData> exchange = restTemplate.exchange(FootballDataServiceImpl.URL, HttpMethod.GET, httpEntity, ResponseFootballData.class);
        return exchange.getBody();
    }
}
