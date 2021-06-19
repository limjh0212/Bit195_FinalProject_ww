package com.bit.ww.controller;

import com.bit.ww.dto.ImgDTO;
import com.bit.ww.service.ImgService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/cmnty")
public class ImgController {
    private final ImgService imgService;
    // Todo: 이미지 + 포스트 수정 필요.
    // Todo: swagger에서 불가능
    @CrossOrigin(origins = {"http://localhost:8081"})
    @ApiOperation(value = "이미지 저장 테스트", notes = "이미지 저장 테스트")
    @PostMapping("/postimg")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestPart(value = "images", required = false)List<MultipartFile> files
    ) throws Exception {
        return imgService.addImg(files);
    }
    // Todo: swagger에서 가능, vue 방법 모르겠음.
    @CrossOrigin(origins = {"http://localhost:8081/getimg"})
    @ApiOperation(value = "이미지 출력 테스트", notes = "이미지 출력 테스트")
    @GetMapping(value = "/getimg/{num}", produces = {MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
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
}
