package com.example.cineclubreservasventas.shopping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "dateEmition")
    private Date dateEmition;

    @Column(name = "dateModify")
    private int dateModify;

    @Column(name = "numberSeats")
    private int numberSeats;

    @Column(name = "userId")
    private long userId;

    @Column(name = "totalPrice")
    private float totalPrice;

    private long movieFutureId;
    private int status;
}
