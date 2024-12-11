package com.example.tugasBesar.tracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    /**
     * Saves a new activity to the database.
     * @param activity The activity object to save.
     * @return true if the activity was successfully saved, false otherwise.
     */
    public boolean saveActivity(Activity activity) {
        try {
            activityRepository.save(activity);
            System.out.println("Activity successfully saved with title: " + activity.getTitle());
            return true;
        } catch (Exception e) {
            System.out.println("Error saving activity: " + e.getMessage());
            return false;
        }
    }

    /**
     * Fetches an activity by its ID.
     * @param id The ID of the activity.
     * @return An Optional containing the activity if found, or empty if not found.
     */
    public Optional<Activity> getActivityById(int id) {
        return activityRepository.findById(id);
    }

    /**
     * Fetches all activities for a specific user.
     * @param userId The ID of the user.
     * @return A list of activities for the specified user.
     */
    Optional<Activity> getActivitiesByUserId(int userId) {
        return activityRepository.findByUserId(userId);
    }

    /**
     * Deletes an activity by its ID.
     * @param id The ID of the activity to delete.
     * @return true if the activity was successfully deleted, false otherwise.
     */
    public boolean deleteActivityById(Long id) {
        try {
            activityRepository.deleteById(id);
            System.out.println("Activity successfully deleted with ID: " + id);
            return true;
        } catch (Exception e) {
            System.out.println("Error deleting activity: " + e.getMessage());
            return false;
        }
    }
}
