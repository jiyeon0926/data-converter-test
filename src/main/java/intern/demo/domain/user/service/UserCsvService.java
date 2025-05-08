package intern.demo.domain.user.service;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import intern.demo.domain.user.dto.UserCsvDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class UserCsvService {

    // 업로드된 파일의 데이터 가공
    public String processUserCsv(MultipartFile file) {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<UserCsvDto> csvToBean = new CsvToBeanBuilder<UserCsvDto>(reader)
                    .withType(UserCsvDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
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

            // 파일로 저장
            String filePath = saveCsvToFile(userCsvDtoList);

            return filePath;

        } catch (IOException e) {
            throw new RuntimeException("CSV 파싱 실패", e);
        }
    }

    public File getFileToDownload(String filename) {
        File file = new File(filename);

        if (!file.exists()) {
            throw new RuntimeException("파일이 존재하지 않습니다.");
        }

        return file;
    }

    private String saveCsvToFile(List<UserCsvDto> userCsvDtoList) {
        String fileName = "user_formatted.csv";
        File csvOutputFile = new File(fileName);

        try (Writer writer = new FileWriter(csvOutputFile)) {
            StatefulBeanToCsv<UserCsvDto> beanToCsv = new StatefulBeanToCsvBuilder<UserCsvDto>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withSeparator(',')
                    .withOrderedResults(true)
                    .build();
            beanToCsv.write(userCsvDtoList);
        } catch (Exception e) {
            throw new RuntimeException("CSV 파일 저장 실패", e);
        }

        return csvOutputFile.getAbsolutePath();
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
