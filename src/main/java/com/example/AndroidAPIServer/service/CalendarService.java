package com.example.AndroidAPIServer.service;


import com.example.AndroidAPIServer.domain.entity.PostDriver;
import com.example.AndroidAPIServer.domain.entity.PostPassenger;
import com.example.AndroidAPIServer.dto.calendar.CalendarPostDto;
import com.example.AndroidAPIServer.dto.chat.ReservedPostDto;
import com.example.AndroidAPIServer.dto.post.PostDto;
import com.example.AndroidAPIServer.dto.post.UserPostDto;
import com.example.AndroidAPIServer.dto.user.AndroidLocalUserDto;
import com.example.AndroidAPIServer.repository.PostDriverRepository;
import com.example.AndroidAPIServer.repository.PostPassengerRepository;
import com.example.AndroidAPIServer.repository.ReservedPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CalendarService {

    private final PostPassengerRepository postPassengerRepository;
    private final PostDriverRepository postDriverRepository;
    private final ReservedPostRepository reservedPostRepository;


    @Transactional
    public List<ReservedPostDto> getCalendarPostData(AndroidLocalUserDto androidLocalUserDto) {

        /*List<CalendarPostDto> postPassengers = postPassengerRepository.findPassengerPostByEmail(androidLocalUserDto.getEmail()).stream()
                .map(CalendarPostDto::new)
                .collect(Collectors.toList());

        List<CalendarPostDto> postDrivers = postDriverRepository.findDriverPostByEmail(androidLocalUserDto.getEmail()).stream()
                .map(CalendarPostDto::new)
                .collect(Collectors.toList());

        List<CalendarPostDto> mergedList = new ArrayList<>();

        mergedList.addAll(postPassengers);
        mergedList.addAll(postDrivers);

        return mergedList;*/

        List<ReservedPostDto> reservedPostDtos = reservedPostRepository.findReservedPostByEmail(androidLocalUserDto.getEmail()).stream()
                .map(ReservedPostDto::new)
                .collect(Collectors.toList());

        return reservedPostDtos;
    }//getUserPostDat()

    public PostDto getPostDtoById(ReservedPostDto reservedPostDto) {
        if(reservedPostDto.getPostType().equals("passenger")){ //태워주세요 테이블 조회

            PostPassenger postPassenger = postPassengerRepository.findById(reservedPostDto.getPostId())
                    .orElseThrow(()->
                            new IllegalArgumentException("해당 게시글이 없습니다."));

            return new PostDto(postPassenger);

        }else { //타세요 테이블 조회

            PostDriver postDriver = postDriverRepository.findById(reservedPostDto.getPostId())
                    .orElseThrow(()->
                            new IllegalArgumentException("해당 게시글이 없습니다."));

            return new PostDto(postDriver);
        }
    } // getPostDtoById()

}
