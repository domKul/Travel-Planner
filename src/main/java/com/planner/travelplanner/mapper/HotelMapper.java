package com.planner.travelplanner.mapper;

import com.planner.travelplanner.domain.Hotel;
import com.planner.travelplanner.domain.dto.hotel.HotelDTOForGet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HotelMapper {
    public HotelDTOForGet mapToHotelResultDTO(Hotel hotel) {
        return new HotelDTOForGet(hotel.getHotelId(),
                hotel.getIdName(),
                hotel.getName(),
                hotel.getCountryCode(),
                hotel.getCurrency(),
                hotel.getHotelPrice());
    }

    public List<HotelDTOForGet> hotelResultDTOList(final List<Hotel> hotels) {
        return hotels.stream()
                .map(this::mapToHotelResultDTO)
                .toList();
    }


}
