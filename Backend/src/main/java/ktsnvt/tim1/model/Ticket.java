package ktsnvt.tim1.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long id;

    @ManyToMany(mappedBy = "tickets", fetch = FetchType.LAZY)
    private Set<ReservableSeatGroup> reservableSeatGroups;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Seat> seats;

    public Ticket() {
        this.reservableSeatGroups = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ReservableSeatGroup> getReservableSeatGroups() {
        return reservableSeatGroups;
    }

    public void setReservableSeatGroups(Set<ReservableSeatGroup> reservableSeatGroups) {
        this.reservableSeatGroups = reservableSeatGroups;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }
}