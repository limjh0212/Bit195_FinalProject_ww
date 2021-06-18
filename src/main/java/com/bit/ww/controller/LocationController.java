package com.bit.ww.controller;

import com.bit.ww.entity.LocationEntity;
import com.bit.ww.entity.UserLocationEntity;
import com.bit.ww.repository.LocationRepository;
import com.bit.ww.repository.UserLocationRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/weather")
public class LocationController {
    private final LocationRepository locationRepository;
    private final UserLocationRepository userLocationRepository;


    @ApiOperation(value = "행정구역 검색", notes = "행정구역 검색")
    @GetMapping("/location")
    public List<LocationEntity> findbyaddress2(String keyword) {
        return locationRepository.findByAddress2Containing(keyword);
    }

    @ApiOperation(value = "사용자 위치 저장", notes = "사용자 위치 저장")
    @PostMapping("/savelocation")
    public UserLocationEntity saveAddress(@RequestBody UserLocationEntity userLocationEntity) {
        return userLocationRepository.save(userLocationEntity);
    }


    @ApiOperation(value = "사용자 저장 위치 조회", notes = "사용자 저장 위치 조회")
    @GetMapping("/userlocation")
    public List<UserLocationEntity> findbyuserid(String userid) {
        return userLocationRepository.findByUserid(userid);
    }

    @ApiOperation(value = "사용자 저장 위치 조회", notes = "사용자 저장 위치 조회")
    @GetMapping("/countlocation")
    public int countbyuserid(String userid) {
        return userLocationRepository.countByUserid(userid);
    }
}
