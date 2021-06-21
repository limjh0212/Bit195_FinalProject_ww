package com.bit.ww.controller;

import com.bit.ww.dto.BoardDTO;
import com.bit.ww.dto.ImgDTO;
import com.bit.ww.dto.PostDTO;
import com.bit.ww.entity.PostEntity;
import com.bit.ww.service.BoardService;
import com.bit.ww.service.ImgService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ImgController {
    private final BoardService boardService;
    private final ImgService imgService;
    // Todo: 이미지 + 포스트 수정 필요.
    @CrossOrigin(origins = {"http://localhost:8081"})
    @ApiOperation(value = "OOTD 이미지 저장", notes = "OOTD 이미지 저장")
    @PostMapping("/api/cmnty/ootdpost")
    @ResponseStatus(HttpStatus.CREATED)
    public PostEntity ootdpost(@Validated @RequestParam("boardname") String boardname,
                               @Validated @RequestParam("uid") String uid,
                               @Validated @RequestParam("title") String title,
                               @Validated @RequestParam("content") String content,
                               @Validated @RequestPart("images") List<MultipartFile> files
                               ) throws Exception {
        BoardDTO boardDTO = boardService.findBoard(boardname);
        int lastnum = boardDTO.getLastnum()+1;
        boardDTO.setLastnum(lastnum);
        boardService.saveBoard(boardDTO);
        return imgService.addPost(PostDTO.builder()
                        .boardname(boardname)
                        .postnum(lastnum)
                        .uid(uid)
                        .title(title)
                        .content(content)
                        .img(files.size())
                        .build(),files);
    }
    // Todo: swagger에서 불가능
    @CrossOrigin(origins = {"http://localhost:8081"})
    @ApiOperation(value = "이미지 저장 테스트", notes = "이미지 저장 테스트")
    @PostMapping("/api/cmnty/postimg")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestPart(value = "images", required = false)List<MultipartFile> files
    ) throws Exception {
        return imgService.addImg(files);
    }
    @CrossOrigin(origins = {"http://localhost:8081/"})
    @ApiOperation(value = "이미지 출력", notes = "이미지 출력")
    @GetMapping(value = "/api/member/getimg/{num}", produces = {MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public ResponseEntity<byte[]> getImage(@PathVariable int num) throws Exception{
        ImgDTO imgDTO = imgService.findImg(num);
        String absolutePath = new File("").getAbsolutePath()+File.separator+File.separator;
        String path = imgDTO.getStoredpath();
        // 맥에서 출력되지 않는 문제 해결을 위해
//        System.out.println(path);
//        String[] paths = path.split("\\\\");
//        String imagesFolder = paths[0];
//        String dateFolder = paths[1];
//        String filename = paths[2];
//        path = imagesFolder+"/"+dateFolder+"/"+filename;
//        System.out.println(path);
        // escape 문자라서 \\\\네개 사용함.
        InputStream imageStream = new FileInputStream(absolutePath+path);
        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();
        return new ResponseEntity<>(imageByteArray, HttpStatus.OK);
    }
    // OOTD 상세보기 imgList test
    @CrossOrigin(origins = {"http://localhost:8081/getimg"})
    @ApiOperation(value = "OOTD 상세보기 이미지 출력 테스트", notes = "OOTD 상세보기 이미지 출력 테스트")
    @GetMapping(value = "/api/cmnty/getimglist/{num}")
    public ResponseEntity<List<byte[]>> getImageList(@PathVariable int num) throws Exception{
        List<byte[]> imgByteList = new ArrayList<>();
        List<ImgDTO> imgDTOList = imgService.findPostImgs(num);
        System.out.println(imgDTOList);
        for( int i =0; i<imgDTOList.size(); i++) {
            String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
            String path = imgDTOList.get(i).getStoredpath();
            // 맥에서 출력되지 않는 문제 해결을 위해
//            String[] paths = path.split("\\\\");
//            String imagesFolder = paths[0];
//            String dateFolder = paths[1];
//            String filename = paths[2];
//            path = imagesFolder + "/" + dateFolder + "/" + filename;
            // escape 문자라서 \\\\네개 사용함.
            InputStream imageStream = new FileInputStream(absolutePath + path);
            byte[] imageByteArray = IOUtils.toByteArray(imageStream);
            imageStream.close();
            imgByteList.add(imageByteArray);
        }
        return new ResponseEntity<>(imgByteList, HttpStatus.OK);
    }
}
