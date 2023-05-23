package com.planner.travelplanner.service;

import com.planner.travelplanner.domain.dto.hotel.HotelDTOForGet;
import com.planner.travelplanner.domain.exception.HotelNotFoundException;
import com.planner.travelplanner.mapper.HotelMapper;
import com.planner.travelplanner.repository.HotelRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    private final HotelMapper hotelMapper;
    private final HotelRepository hotelRepository;

    public HotelService(HotelMapper hotelMapper, HotelRepository hotelRepository) {
        this.hotelMapper = hotelMapper;
        this.hotelRepository = hotelRepository;
    }

    @Transactional
    public void deleteHotelById(long hotelId) {
        if (hotelRepository.existsById(hotelId)) {
            hotelRepository.deleteById(hotelId);
        } else {
            throw new HotelNotFoundException();
        }
    }

    public List<HotelDTOForGet> getAllHotelsInDB() {
        return hotelMapper.hotelResultDTOList(hotelRepository.findAll());
    }

    public HotelDTOForGet getHotelById(long hotelId) {
        if (hotelRepository.existsById(hotelId)) {
            return hotelMapper.mapToHotelResultDTO(hotelRepository.findById(hotelId).get());
        } else {
            throw new HotelNotFoundException();
        }
    }


}
