package intern.demo.domain.user.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import intern.demo.domain.user.dto.UserCsvDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
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

                        return userCsvDto;
                    })
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException("CSV 파싱 실패", e);
        }
    }
}
