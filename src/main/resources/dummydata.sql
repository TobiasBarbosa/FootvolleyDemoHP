-- =========================================
-- CLEAN START (valgfrit)
-- =========================================
DELETE FROM booking;
DELETE FROM event;
DELETE FROM user_account;

-- =========================================
-- USERS
-- =========================================
INSERT INTO user_account (id, first_name, last_name, email, password, role, is_member, city, country)
VALUES
    (1, 'Tobias', 'Barbosa', 'tobias@test.com', '1234', 'USER', true, 'Copenhagen', 'Denmark'),
    (2, 'Anna', 'Jensen', 'anna@test.com', '1234', 'USER', false, 'Aarhus', 'Denmark');

-- =========================================
-- EVENTS
-- =========================================
INSERT INTO event (id, title, description, date, start_time, end_time, price, max_participants, location_name, location_address, event_type, training_level)
VALUES
    (1, 'Morning Training', 'Basic footvolley training', '2026-06-01', '10:00:00', '12:00:00', 100, 10, 'Beach Court', 'Copenhagen Beach', 'TRAINING', 'BEGINNER'),

    (2, 'Advanced Session', 'Hard training session', '2026-06-02', '14:00:00', '16:00:00', 150, 8, 'Indoor Hall', 'Sport Center', 'TRAINING', 'ADVANCED');

-- =========================================
-- BOOKINGS
-- =========================================
INSERT INTO booking (booking_time, training_event_id, user_id)
VALUES
    (CURRENT_TIMESTAMP, 1, 1),
    (CURRENT_TIMESTAMP, 1, 2);