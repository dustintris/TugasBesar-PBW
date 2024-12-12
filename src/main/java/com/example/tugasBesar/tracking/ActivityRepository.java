package com.example.tugasBesar.tracking;

import java.util.List;
import java.util.Optional;

// ActivityRepository.java
public interface ActivityRepository {
    void save(Activity activity) throws Exception;
    Optional<Activity> findById(int id);
    Optional<Activity> findByUserId(int userId);
    List<Activity> findAll();  // New method to fetch all activities
    public void deleteById(Long id) throws Exception;
}

