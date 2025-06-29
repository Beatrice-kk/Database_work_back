package cn.lonesome.sms.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yale
 * @version 1.0
 */
@Configuration
@RequiredArgsConstructor
public class ProfileConfig {
    private final ApplicationContext context;

    public String getActiveProfile() {
        return context.getEnvironment().getActiveProfiles()[0];
    }

    public boolean isDev() {
        String activeProfile = getActiveProfile();
        return "dev".equals(activeProfile);
    }
}