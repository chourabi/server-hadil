package com.bsofts.fidelity.scheduledMethod;

/*import com.bsofts.fidelity.models.BandeAchat;
import com.bsofts.fidelity.models.EBandeAchat;
import com.bsofts.fidelity.repositories.BandeAchatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@EnableScheduling
public class EtatBandeCron {
    @Autowired
    BandeAchatRepository bandeAchatRepository;

    List<BandeAchat> bandeAchats= bandeAchatRepository.findAll();

    @Scheduled(cron = "0 0 * * *",zone = "Africa/Tunis")
    public void changeEtatBande(){
        LocalDateTime today=LocalDateTime.now();

        for (BandeAchat bandeAchat:bandeAchats) {
            if (today.isAfter(bandeAchat.getDateExpiration())) {
                bandeAchat.setEtat(EBandeAchat.EXPIREE);
                bandeAchatRepository.save(bandeAchat);
            }
            
        }


    }

    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTask() {
        System.out.println(
                "Fixed rate task - " + System.currentTimeMillis() / 1000);
    }


}*/
