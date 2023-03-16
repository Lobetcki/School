package ru.hogwarts.school.dto;

import lombok.Data;
import ru.hogwarts.school.model.AppInfo;

@Data
public class AppInfoDTO {
    
    private String appName;
    private String appVersion;
    private String appEnvironment;


    public static AppInfoDTO fromAppInfo() {
        AppInfoDTO dto = new AppInfoDTO();
        dto.setAppName(AppInfo.APP_NAME);
        dto.setAppVersion(AppInfo.APP_VERSION);
        dto.setAppEnvironment(AppInfo.APP_ENVIRONMENT);
        return dto;
    }

//    public AppInfo toAppInfo() {
//        AppInfo appInfo = new AppInfo();
//        appInfo.setAppName(this.getAppName());
//        appInfo.setAppVersion(this.getAppVersion());
//        appInfo.setAppEnvironment(this.getAppEnvironment());
//        return appInfo;
//    }
    
}
