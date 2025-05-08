package intern.demo.domain.user.controller;

import intern.demo.domain.user.dto.UserCsvDto;
import intern.demo.domain.user.service.UserCsvService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserCsvController {

    private final UserCsvService userCsvService;

    @GetMapping("/upload")
    public String home() {
        return "/user/uploadCsv";
    }

    // 파일 업로드 및 가공
    @PostMapping("/upload/csv")
    public ResponseEntity<List<UserCsvDto>> uploadUserCsv(@RequestParam("file") MultipartFile file) throws IOException {
        List<UserCsvDto> userCsvDtoList = userCsvService.processUserCsv(file);

        return new ResponseEntity<>(userCsvDtoList, HttpStatus.OK);
    }
}
