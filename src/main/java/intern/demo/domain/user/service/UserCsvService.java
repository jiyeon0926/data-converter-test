package intern.demo.domain.user.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import intern.demo.domain.user.dto.UserCsvDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserCsvService {

    // 데이터 처리 및 파싱
    public List<UserCsvDto> processUserCsv(MultipartFile file) {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            CsvToBean<UserCsvDto> csvToBean = new CsvToBeanBuilder<UserCsvDto>(reader)
                    .withType(UserCsvDto.class)
                    .withIgnoreLeadingWhiteSpace(false)
                    .build();

            List<UserCsvDto> userCsvDtoList = csvToBean.parse().stream()
                    .map(userCsvDto -> {
                        userCsvDto.setId(UUID.randomUUID().toString());

                        String formattedCreatedDate = formatDate(userCsvDto.getCreated_date());
                        userCsvDto.setCreated_date(formattedCreatedDate);

                        String formattedModifiedDate = formatDate(userCsvDto.getModified_date());
                        userCsvDto.setModified_date(formattedModifiedDate);

                        return userCsvDto;
                    })
                    .collect(Collectors.toList());

            return userCsvDtoList;

        } catch (IOException e) {
            throw new RuntimeException("CSV 파싱 실패", e);
        }
    }

    private String formatDate(String dt) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm a", Locale.US);
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date date = inputFormat.parse(dt);
            String formattedDate = outputFormat.format(date);

            return formattedDate + "+00";
        } catch (ParseException e) {
            throw new RuntimeException("날짜 변환 실패: " + dt, e);
        }
    }
}
