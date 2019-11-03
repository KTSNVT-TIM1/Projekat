package ktsnvt.tim1.repositories;

import ktsnvt.tim1.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Page<Reservation> findByOrderIdIsNull(Pageable pageable);

    Page<Reservation> findByOrderIdIsNotNull(Pageable pageable);
}
