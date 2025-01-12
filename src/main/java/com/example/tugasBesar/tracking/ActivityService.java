package com.example.tugasBesar.tracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public boolean saveActivity(Activity activity) {
        try {
            activityRepository.save(activity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}