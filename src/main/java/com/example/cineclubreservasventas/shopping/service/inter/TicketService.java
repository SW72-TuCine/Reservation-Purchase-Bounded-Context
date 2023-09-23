package com.example.cineclubreservasventas.shopping.service.inter;

import com.example.cineclubreservasventas.shopping.entity.Promotion;
import com.example.cineclubreservasventas.shopping.entity.Ticket;

public interface TicketService {
    public Ticket createTicket(Ticket ticket);
    public Ticket modifyTicket(Long id, Ticket ticket);
    public void deleteTicket(Long id);
    public Ticket applyPromotion(Promotion promotion, Ticket ticket);
    public Ticket getTicket(Long id);
}
