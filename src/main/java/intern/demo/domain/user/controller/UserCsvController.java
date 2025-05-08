package intern.demo.domain.user.controller;

import intern.demo.domain.user.dto.UserCsvDto;
import intern.demo.domain.user.service.UserCsvService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserCsvController {

    private final UserCsvService userCsvService;

    // 파일 업로드
    @PostMapping("/upload/csv")
    public ResponseEntity<List<UserCsvDto>> uploadUserCsv(@RequestParam("file") MultipartFile file) throws IOException {
        List<UserCsvDto> userCsvDtoList = userCsvService.processUserCsv(file);

        return new ResponseEntity<>(userCsvDtoList, HttpStatus.OK);
    }
}
