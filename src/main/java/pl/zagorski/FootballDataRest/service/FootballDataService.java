package pl.zagorski.FootballDataRest.service;

import pl.zagorski.FootballDataRest.model.ResponseFootballData;

public interface FootballDataService {
    /**
     * 1.
     * Metoda, która wyświetli wszystkie dane pobrane z api.football-data zmapowane na obiekty klas zawartych w
     * pakiecie model. Uzyskane dane zawierają informacje o meczach piłki nożnej rozegranych w bieżącym sezonie
     * ligi włoskiej.
     */
    ResponseFootballData getAllData();
}
