package com.bsofts.fidelity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bsofts.fidelity.models.Notifications;

public interface NotificationsRepository extends JpaRepository<Notifications,Long> {

}
