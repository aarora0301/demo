INSERT INTO aircraft (id, name , model, manufacturing_year) VALUES
  (1, 'Indigo', 'A560', '2013');

  INSERT INTO aircraft (id, name , model, manufacturing_year) VALUES
  (2, 'AirAsia', 'A560', '2013');


--  INSERT INTO seat (id, aircraft_id, is_available) VALUES
--   (1, 1, true);
--   INSERT INTO seat (id, aircraft_id, is_available) VALUES
--   (2, 1, true);
--     INSERT INTO seat (id, aircraft_id, is_available) VALUES
--   (3, 1, true);
--     INSERT INTO seat (id, aircraft_id, is_available) VALUES
--   (4, 1, true);
--     INSERT INTO seat (id, aircraft_id, is_available) VALUES
--   (5, 1, true);
--     INSERT INTO seat (id, aircraft_id, is_available) VALUES
--   (6, 1, true);


  INSERT INTO airport(id, name, code) VALUES  (1, 'Delhi International', 'DEL');
  INSERT INTO airport(id, name, code) VALUES  (2, 'Kualalumpur International', 'KL');
  INSERT INTO airport(id, name, code) VALUES  (3, 'Berlin International', 'BER');


  INSERT  INTO flight(id, airport_to,  airport_from, duration_in_minutes )
  VALUES (1, 2, 1, 900);
   INSERT  INTO flight(id, airport_to,  airport_from, duration_in_minutes )
  VALUES (2, 2, 3, 1500);