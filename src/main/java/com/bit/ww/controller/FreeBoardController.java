package com.bit.ww.controller;

import com.bit.ww.mapper.FreeBoardMapper;
import com.bit.ww.repository.FreeBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FreeBoardController {

    @Autowired
    FreeBoardMapper freeBoardMapper;

    @Autowired
    FreeBoardRepository freeBoardRepository;



}
