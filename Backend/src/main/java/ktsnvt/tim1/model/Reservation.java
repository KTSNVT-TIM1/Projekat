package ktsnvt.tim1.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column
    private String orderId;

    @Column(nullable = false)
    private Boolean isCancelled = false;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "reservation")
    private Set<Ticket> tickets = new HashSet<>();

    @ManyToOne()
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private RegisteredUser registeredUser;

    public Reservation() {
    }

    public Reservation(Long id, String orderId, Boolean isCancelled, RegisteredUser registeredUser, Event event) {
        this.id = id;
        this.orderId = orderId;
        this.isCancelled = isCancelled;
        this.registeredUser = registeredUser;
        this.tickets = new HashSet<>();
        this.event = event;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Boolean getCancelled() {
        return isCancelled;
    }

    public void setCancelled(Boolean cancelled) {
        isCancelled = cancelled;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public RegisteredUser getRegisteredUser() {
        return registeredUser;
    }

    public void setRegisteredUser(RegisteredUser registeredUser) {
        this.registeredUser = registeredUser;
    }
}
