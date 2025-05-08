package intern.demo.domain.user.controller;

import intern.demo.domain.user.service.UserCsvService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserCsvController {

    private final UserCsvService userCsvService;

    // 파일 업로드
    @PostMapping("/upload/csv")
    public ResponseEntity<String> uploadUserCsv(@RequestParam("file") MultipartFile file) throws IOException {
        String filePath = userCsvService.processUserCsv(file);

        return new ResponseEntity<>(filePath, HttpStatus.OK);
    }

    // 다운로드
    @GetMapping("/download/csv")
    public void downloadUserCsv(@RequestParam("file") String filename, HttpServletResponse response) {
        File file = userCsvService.getFileToDownload(filename);

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

        try (InputStream in = new FileInputStream(file);
             OutputStream out = response.getOutputStream()) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            out.flush();
        } catch (IOException e) {
            throw new RuntimeException("파일 다운로드 실패", e);
        }
    }
}
