package com.example.cineclubreservasventas.shopping.service.impl;

import com.example.cineclubreservasventas.shopping.dto.common.TicketDto;
import com.example.cineclubreservasventas.shopping.dto.recieved.TicketRecievedDto;
import com.example.cineclubreservasventas.shopping.entity.Promotion;
import com.example.cineclubreservasventas.shopping.entity.Ticket;
import com.example.cineclubreservasventas.shopping.repository.TicketRepository;
import com.example.cineclubreservasventas.shopping.service.inter.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    private ModelMapper modelMapper;
    TicketServiceImpl(){
        modelMapper=new ModelMapper();
    }
    public TicketDto EntityToDto(Ticket ticket){
        return modelMapper.map(ticket, TicketDto.class);
    }
    public Ticket DtoToEntity(TicketDto ticket){
        return modelMapper.map(ticket, Ticket.class);
    }
    @Override
    public TicketDto createTicket(TicketRecievedDto ticket) {
        validateTicket(ticket);
        TicketDto ticketDto=modelMapper.map(ticket,TicketDto.class);
        Ticket ticket1 = DtoToEntity(ticketDto);

        return EntityToDto(ticketRepository.save(ticket1));
    }

    @Override
    public TicketDto modifyTicket(Long id, TicketRecievedDto ticket) {
        Ticket existingTicket = ticketRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Can't find a ticket with that Id"));
        existingTicket.setDateModify(ticket.getDateModify());
        existingTicket.setNumberSeats(ticket.getNumberSeats());
        existingTicket.setUserId(ticket.getUserId());
        existingTicket.setTotalPrice(ticket.getTotalPrice());
        existingTicket.setStatus(ticket.getStatus());

        Ticket updatedTicket = ticketRepository.save(existingTicket);

        return EntityToDto(updatedTicket);
    }

    @Override
    public void deleteTicket(Long id) {
        Ticket existingTicket = ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Can't find a ticket with that Id"));
        ticketRepository.delete(existingTicket);
    }

    @Override
    public Ticket applyPromotion(Promotion promotion, Ticket ticket) {
        Ticket ticketDB = getTicketHelper(ticket.getId());
        if (ticketDB == null) {
            return null;
        }
        double discountPercentage = promotion.getDiscountPercentage();
        double discountedPrice = ticketDB.getTotalPrice() * (1 - discountPercentage / 100.0);
        ticketDB.setTotalPrice((float) discountedPrice);
        return ticketRepository.save(ticketDB);
    }


    @Override
    public List<TicketDto> getTicket() {
        List<Ticket> promotions = ticketRepository.findAll();
        return promotions.stream()
                .map(this::EntityToDto)
                .collect(Collectors.toList());
    }

    private void validateTicket(TicketRecievedDto ticket){
        if(ticket.getDateEmition() == null){
            throw new IllegalArgumentException("DateEmition is obligatory");
        }
        if(ticket.getDateModify() == 0){
            throw new IllegalArgumentException("DateModify is obligatory");
        }
        if(ticket.getNumberSeats() == 0){
            throw new IllegalArgumentException("NumberSeats are zero");
        }
    }

    private Ticket getTicketHelper(Long id) {
        Ticket ticketDB = ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Can't find a ticket with that Id"));
        return ticketDB;
    }


}
