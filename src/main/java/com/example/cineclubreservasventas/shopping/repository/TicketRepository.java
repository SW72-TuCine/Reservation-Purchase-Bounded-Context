package com.example.cineclubreservasventas.shopping.repository;

import com.example.cineclubreservasventas.shopping.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    public List<Ticket> findTicketById(long Id);
}
