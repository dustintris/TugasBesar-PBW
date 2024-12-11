package com.example.tugasBesar.tracking;

import java.util.Optional;

public interface ActivityRepository {
    void save(Activity activity) throws Exception;
    Optional<Activity> findById(int id);
    Optional<Activity> findByUserId(int userId);
    public void deleteById(Long id) throws Exception;
}
