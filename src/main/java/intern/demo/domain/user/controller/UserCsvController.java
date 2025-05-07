package intern.demo.domain.user.controller;

import intern.demo.domain.user.service.UserCsvService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserCsvController {

    private final UserCsvService userCsvService;

    @PostMapping("/upload/csv")
    public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) throws IOException {
        userCsvService.processCsv(file);

        return new ResponseEntity<>("csv 파일 업로드 성공", HttpStatus.OK);
    }

    @GetMapping("/download/csv")
    public ResponseEntity<FileSystemResource> downloadCsv() {
        FileSystemResource resource = userCsvService.getLastSavedFile();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=user_complete.csv");
        headers.add(HttpHeaders.CONTENT_TYPE, "text/csv; charset=UTF-8");

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}
