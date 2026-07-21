package com.example.TiffinManagement.repository;

import com.example.TiffinManagement.model.TiffinLog;
import com.example.TiffinManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TiffinLogRepository  extends JpaRepository <TiffinLog,Long>{

    List<TiffinLog> findByProvider(User provider);

    List<TiffinLog> findByReceiver(User receiver);

    List<TiffinLog> findByProviderAndReceiver(User Provider,User receiver);
}
