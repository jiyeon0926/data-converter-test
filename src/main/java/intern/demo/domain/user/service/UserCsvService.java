package intern.demo.domain.user.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import intern.demo.domain.user.dto.UserCsvDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserCsvService {

    // 업로드된 파일의 데이터 가공
    public List<UserCsvDto> processUserCsv(MultipartFile file) {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<UserCsvDto> csvToBean = new CsvToBeanBuilder<UserCsvDto>(reader)
                    .withType(UserCsvDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<UserCsvDto> userCsvDtoList = csvToBean.parse();

            return userCsvDtoList.stream()
                    .map(userCsvDto -> {
                        userCsvDto.setId(UUID.randomUUID().toString());

                        String formattedCreatedDate = formatDate(userCsvDto.getCreated_date());
                        userCsvDto.setCreated_date(formattedCreatedDate);
                        String formattedModifiedDate = formatDate(userCsvDto.getModified_date());
                        userCsvDto.setModified_date(formattedModifiedDate);

                        return userCsvDto;
                    })
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException("CSV 파싱 실패", e);
        }
    }

    private String formatDate(String inputDate) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm a", Locale.US);
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date date = inputFormat.parse(inputDate);
            String formattedDate = outputFormat.format(date);

            return formattedDate + "+00";
        } catch (ParseException e) {
            throw new RuntimeException("날짜 변환 실패: " + inputDate, e);
        }
    }
}
