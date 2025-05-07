package intern.demo.domain.user.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserCsvService {

    private Path lastSavedPath;

    public String processCsv(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "파일이 비어있습니다.");
        }

        // 파일명 확장자 확인
        String filename = file.getOriginalFilename();
        if (!filename.endsWith(".csv")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "csv 파일이 아닙니다.");
        }

        Path tempFile = Files.createTempFile("uploaded-", ".csv");

        try (Reader reader = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8);
             CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
             BufferedWriter writer = Files.newBufferedWriter(tempFile, StandardCharsets.UTF_8)
        ) {
            List<String> headers = new ArrayList<>(parser.getHeaderNames());
            headers.add("id");

            try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(headers.toArray(new String[0])))) {
                for (CSVRecord record : parser) {
                    List<String> values = new ArrayList<>();

                    for (String header : parser.getHeaderNames()) {
                        values.add(record.get(header));
                    }

                    values.add(UUID.randomUUID().toString());
                    csvPrinter.printRecord(values);
                }
            }

        } catch (IOException e) {
            throw new IOException("CSV 파일 처리 실패", e);
        }
        lastSavedPath = tempFile;

        return tempFile.toString();
    }

    private String[] getUpdatedHeaders(CSVParser parser) {
        List<String> headers = new ArrayList<>(parser.getHeaderNames());
        headers.add("id");

        return headers.toArray(new String[0]);
    }

    public FileSystemResource getLastSavedFile() {
        if (lastSavedPath == null || !Files.exists(lastSavedPath)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "저장된 CSV 파일이 없습니다.");
        }

        return new FileSystemResource(lastSavedPath);
    }
}
