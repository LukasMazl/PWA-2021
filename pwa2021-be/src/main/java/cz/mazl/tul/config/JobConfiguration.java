package cz.mazl.tul.config;

import cz.mazl.tul.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class JobConfiguration {

    @Autowired
    private UserService userService;

    @Scheduled(fixedDelay = 10000)
    public void sendOnlineDevices() {
       userService.sendOnlineUserBrowcast();
    }
}
