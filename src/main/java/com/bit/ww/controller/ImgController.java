package com.bit.ww.controller;

import com.bit.ww.dto.BoardDTO;
import com.bit.ww.dto.PostDTO;
import com.bit.ww.service.BoardService;
import com.bit.ww.service.ImgService;
import com.bit.ww.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/cmnty")
public class ImgController {
    private final ImgService imgService;
    private final BoardService boardService;
    private final MemberService memberService;

    @CrossOrigin(origins = {"http://localhost:8081"})
    @ApiOperation(value = "이미지 저장 테스트", notes = "이미지 저장 테스트")
    @PostMapping(value="/postimg")
    public ResponseEntity<?> createPost
            (@Validated @RequestParam("boardname") String boardname,
             @Validated @RequestParam("uid") String uid,
             @Validated @RequestParam("title") String title,
             @Validated @RequestParam("content") String content,
             @Validated @RequestParam("files") List<MultipartFile> files
            ) throws Exception{
        BoardDTO boardDTO = boardService.findBoard(boardname);
        int lastnum = boardDTO.getLastnum()+1;
        PostDTO postDTO = boardService.convertEntityToDTO(imgService.addPost(PostDTO.builder()
                .boardname(boardname)
                .boardnum(boardService.findBoard(boardname).getBoardnum())
                .postnum(lastnum)
                .uid(uid)
                .writer(memberService.findbyId(uid).get().getNickname())
                .title(title)
                .content(content)
                .build(), files));
        boardDTO.setLastnum(lastnum);
        boardService.saveBoard(boardDTO);
        URI uriLocation = new URI("/postimg/" + postDTO.getNum());
        return ResponseEntity.created(uriLocation).body("{}");
    }
//        @PostMapping("/postimg")
//        public ResponseEntity<String> createPost(@RequestParam("file") MultipartFile file){
//            Date date = new Date();
//            StringBuilder sb = new StringBuilder();
//            if (file.isEmpty()){
//                sb.append("none");
//            }else {
//                sb.append(date.getTime());
//                sb.append(file.getOriginalFilename());
//            }
//            if(!file.isEmpty()){
//                File dest = new File("C://Users/bitcamp/desktop/finalProject/src/main/resources/static/img/"+sb.toString());
//                try {
//                    file.transferTo(dest);
//                } catch (IllegalStateException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            return new ResponseEntity<String>("ok", HttpStatus.OK);
//        }
//            @GetMapping(value = "/image/{imagename}", produces = MediaType.IMAGE_PNG_VALUE)
//            public ResponseEntity<byte[]> getImg(@PathVariable("imagename") String imagename) throws IOException{
//                InputStream imageStream = new FileInputStream("C://Users/bitcamp/desktop/finalProject/src/main/resources/static/img/"+imagename);
//                byte[] imageByteArray = IOUtils.toByteArray(imageStream);
//                imageStream.close();
//                return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
//            }
}
