package com.bit.ww.controller;

import com.bit.ww.dto.FreeBoardDTO;
import com.bit.ww.service.FreeBoardService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class FreeBoardController {
    private FreeBoardService freeBoardService;

    @GetMapping("/boards")
    public List list(Model model){
        List<FreeBoardDTO> freeboardList = freeBoardService.getFreeBoardList();
        model.addAttribute("freeBoardList", freeboardList);
        return freeboardList;
    }

    @GetMapping("/boards/post")
    public String write(){
        return "postForm";
    }

    @PostMapping("/boards/post")
    public String write(FreeBoardDTO freeBoardDTO){
        freeBoardService.savePost(freeBoardDTO);
        return "post";
    }

    @RequestMapping(value = "/boards/edit/{num}", method = RequestMethod.GET)
    public String edit(@PathVariable("num") int num, Model model){
        FreeBoardDTO freeBoardDTO = freeBoardService.getPost(num);
        model.addAttribute("freeBoardDTO", freeBoardDTO);
        return "editForm";
    }

    @RequestMapping(value = "/boards/edit/{num}", method = {RequestMethod.POST, RequestMethod.PUT})
    public String update(FreeBoardDTO freeBoardDTO){
        freeBoardService.savePost(freeBoardDTO);
        return "update";
    }

    @RequestMapping(value = "/boards/detail/{num}", method = RequestMethod.GET)
    public String detail(@PathVariable("num") int num, Model model){
        FreeBoardDTO freeBoardDTO = freeBoardService.getPost(num);
        model.addAttribute("freeBoardDTO", freeBoardDTO);
        //확인용
        String fbStr = freeBoardDTO.toString();
        return fbStr;
    }

    @RequestMapping(value = "/boards/detail/{num}", method = {RequestMethod.POST, RequestMethod.DELETE})
    public String delete(@PathVariable("num") int num){
        freeBoardService.deletePost(num);
        return "delete";
    }

}
