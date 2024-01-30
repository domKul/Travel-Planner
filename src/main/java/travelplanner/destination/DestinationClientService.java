package travelplanner.destination;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
class DestinationClientService {

    private final RestTemplate restTemplate;
    private final DestinationRepository destinationRepository;
    private final DestinationHttpClientConfig destinationHttpClientConfig;

    DestinationClientService(RestTemplateBuilder builder,
                             DestinationRepository destinationRepository,
                             DestinationHttpClientConfig destinationHttpClientConfig) {
        this.restTemplate = builder.build();
        this.destinationRepository = destinationRepository;
        this.destinationHttpClientConfig = destinationHttpClientConfig;
    }



    ResponseEntity<DestinationlDTO> searchHotelWithSaveToData(String orderedBy, int adults_number, String checkin_date,
                                                              String filter_by_currency, int dest_id,
                                                              String locale, String checkout_date, String units,
                                                              int room_number, String dest_type) {

        HttpEntity<String> entity = new HttpEntity<>("parameters", destinationHttpClientConfig.header());
        String url = destinationHttpClientConfig.urlBuilder(orderedBy, adults_number, checkin_date,
                filter_by_currency, dest_id,
                locale, checkout_date, units, room_number, dest_type).toString();
        ResponseEntity<DestinationlDTO> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, DestinationlDTO.class);
        List<Destination> destinations = new ArrayList<>();

        sumDestinations(responseEntity, destinations);
        destinationRepository.saveAll(destinations);
        return responseEntity;
    }

    private void sumDestinations(ResponseEntity<DestinationlDTO> responseEntity, List<Destination> destinations) {
        List<Destination> sss = Objects.requireNonNull(responseEntity.getBody()).getResults().stream()
                .filter(hotelDTO -> !destinationRepository.existsByName(hotelDTO.getName()))
                .map(hotelDTO -> {
                    Destination destination = new Destination();
                    destination.setIdName(hotelDTO.getId());
                    destination.setCountryCode(hotelDTO.getCountryCode());
                    destination.setName(hotelDTO.getName());
                    if (hotelDTO.getPriceBreakdown() != null) {
                        destination.setCurrency(hotelDTO.getPriceBreakdown().getGrossPrice().getCurrency());
                        destination.setDestinationPrice(hotelDTO.getPriceBreakdown().getGrossPrice().getValue());
                    }
                    destinations.add(destination);
                    return destination;
                })
                .toList();
    }

    ResponseEntity<DestinationlDTO> searchHotel(String orderedBy, int adults_number, String checkin_date,
                                                String filter_by_currency, int dest_id,
                                                String locale, String checkout_date, String units,
                                                int room_number, String dest_type) {
        HttpEntity<String> entity = new HttpEntity<>("parameters", destinationHttpClientConfig.header());
        String url = destinationHttpClientConfig.urlBuilder(orderedBy, adults_number, checkin_date,
                filter_by_currency, dest_id,
                locale, checkout_date, units, room_number, dest_type).toString();
        return restTemplate.exchange(url, HttpMethod.GET, entity, DestinationlDTO.class);
    }
}
