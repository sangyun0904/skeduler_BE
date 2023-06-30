package com.example.skeduler.repositories;

import com.example.skeduler.model.MoneyTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyTransferRepository  extends JpaRepository<MoneyTransfer, Long> {
}
