package ru.hogwarts.school.service;

import liquibase.pro.packaged.S;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.AppInfoDTO;
import ru.hogwarts.school.dto.StudentDTO;

@Service
public class AppInfoService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public AppInfoDTO getAppInfo() {
        logger.info("Was invoked method for get info");

        return AppInfoDTO.fromAppInfo();
    }

}
