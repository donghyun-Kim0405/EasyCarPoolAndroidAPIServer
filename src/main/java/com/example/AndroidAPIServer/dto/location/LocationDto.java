package com.example.AndroidAPIServer.dto.location;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDto {
    private String roomId;
    private String writer;
    private Double lon;
    private Double lat;
}
