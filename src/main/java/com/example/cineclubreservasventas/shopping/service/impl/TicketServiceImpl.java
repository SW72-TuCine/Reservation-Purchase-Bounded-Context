package com.example.cineclubreservasventas.shopping.service.impl;

import com.example.cineclubreservasventas.shopping.entity.Promotion;
import com.example.cineclubreservasventas.shopping.entity.Ticket;
import com.example.cineclubreservasventas.shopping.repository.TicketRepository;
import com.example.cineclubreservasventas.shopping.service.inter.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Override
    public Ticket createTicket(Ticket ticket) {
        List<Ticket> tickets = ticketRepository.findTicketById(ticket.getId());
        if (tickets != null && !tickets.isEmpty()) {
            for (Ticket ticketDB : tickets) {
                if (ticketDB.getId() == (ticket.getId())) {
                    return ticketDB;
                }
            }
        }
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket modifyTicket(Long id, Ticket ticket) {
        Ticket tickedDB = getTicket(ticket.getId());
        if (tickedDB == null) {
            return null;
        }
        tickedDB.setDateModify(ticket.getDateModify());
        tickedDB.setNumberSeats(ticket.getNumberSeats());
        tickedDB.setUserId(ticket.getUserId());
        tickedDB.setTotalPrice(ticket.getTotalPrice());
        tickedDB.setStatus(ticket.getStatus());
        return ticketRepository.save(tickedDB);
    }

    @Override
    public void deleteTicket(Long id) {
        Ticket existingTicket = ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Can't find a ticket with that Id"));
        ticketRepository.delete(existingTicket);
    }

    @Override
    public Ticket applyPromotion(Promotion promotion, Ticket ticket) {
        Ticket ticketDB = getTicket(ticket.getId());
        if (ticketDB == null) {
            return null;
        }
        double discountPercentage = promotion.getDiscountPercentage();
        double discountedPrice = ticketDB.getTotalPrice() * (1 - discountPercentage / 100.0);
        ticketDB.setTotalPrice((float) discountedPrice);
        return ticketRepository.save(ticketDB);
    }

    @Override
    public Ticket getTicket(Long id) {
        Ticket ticketDB = ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Can't find a ticket with that Id"));
        return ticketDB;
    }
}
