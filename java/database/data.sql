BEGIN TRANSACTION;

INSERT INTO users (username, display_name, email, password_hash,role)
VALUES ('user', 'user', 'user@hotmail.com', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username, display_name, email, password_hash,role)
VALUES ('admin', 'admin', 'admin@hotmail.com', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO users (username, display_name, email, password_hash,role)
VALUES ('harry_simpson', 'harry', 'harry@hotmail.com', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
--Harry is user 3
INSERT INTO users (username, display_name, email, password_hash,role)
VALUES ('daniel_to', 'daniel', 'daniel@hotmail.com', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
--Daniel is user 4
INSERT INTO users (username, display_name, email, password_hash,role)
VALUES ('revathi_ajjarapu', 'revathi', 'revathi@hotmail.com', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
--Reva is user 5

INSERT INTO users(username, display_name, email, password_hash, role)
VALUES ('silliesimps', 'harrys_alt_account', 'silly_boy_47@msn.com', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER'),
--user id 6
('chandra_lane', 'chandra', 'chandra_lane_47@aol.com', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER'),
--user id 7
('Ben', 'Lord Bennington', 'ben_ben_ben_ben@benben.com', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER'),
--user id 8
('cat_attack', 'catwoman', 'leChat@catsrule.com', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER'),
--user id 9
('batman', 'Batman', 'iamthebatman@gmail.com', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
--user id 10

INSERT INTO game (game_name,max_players)
VALUES ('Reset Filter', 0),
('running', 1),
('valorant', 5),
('cricket', 11),
('soccer', 11),
('football', 11),
('baseball', 26),
('basketball', 13),
('tennis', 2),
('dancesport', 2),
('parkour', 1),
('pole sports', 1),
('canoeing', 2),
('water polo', 7),
('swimming', 1);

--Dummy Team used for creating empty scores
INSERT INTO team (team_id, team_captain_id, team_name, game_name, accepting_members)
VALUES (0,4, 'dummy', 'running', false);

INSERT INTO team (team_captain_id, team_name, game_name, accepting_members)
VALUES (3, 'find pearls', 'running', false), --team_id 1
(4, 'pho 95', 'valorant', true), --team_id 2
(5, 'raystar', 'running', false), --team_id 3
--swimming teams for 8 person tourney
(3, 'flying turtles', 'swimming', false), --team_id 4
(4, 'pho 96', 'swimming', false), --team_id 5
(5, 'Ray Stars', 'swimming', false), --team_id 6
(6, 'Your Boy', 'swimming', false), --team_id 7
(7, 'Chandras Champion', 'swimming', false), --team_id 8
(8, 'Bens team', 'swimming', false), --team_id 9
(9, 'Wet cats', 'swimming', false), --team_id 10
(10, 'Wet bats', 'swimming', false); --team_id 11

INSERT INTO tournament (tournament_id, host_id,tournament_name,entry_fee,game_name, accepting_teams, tournament_status, maximum_participants)
VALUES(0, 2, 'dummy tournament', 0, 'valorant', false, 'o',10);

INSERT INTO tournament (host_id,tournament_name,entry_fee,game_name, accepting_teams, tournament_status, maximum_participants)
VALUES (2, 'test tournament', 0, 'running', true, 'o',32),
(3, 'test swimming tournament', 0, 'swimming', true, 'o', 8); -- tournament_id 2


INSERT INTO team_user (user_id, team_id)
VALUES (3, 1), --links harry to 'find pearls'
(5, 3), --links revathi to 'raystar'
(4, 2), --links daniel to 'pho 95'
(3, 2), --links harry to 'pho 95'
(4, 0), --links daniel to 'dummy'

--linking everyone to their own swimming team
(3, 4),
(4, 5),
(5, 6),
(6, 7),
(7, 8), --links Chandra to 'Chandra's Champion'
(8, 9),
(9, 10),
(10, 11);

INSERT INTO team_tournament (team_id, tournament_id)
VALUES (3, 1), --links 'raystar' to test tournament
(1, 1), --links 'find pearl' to test tournament

--links all 8 swimming teams to test swimming tournament
(4, 2),
(5, 2),
(6, 2),
(7, 2),
(8, 2),
(9, 2),
(10, 2),
(11, 2);


INSERT INTO scores (tournament_id, team_id, bracket_position, score)
VALUES (2, 4, 1, 'L'),
(2, 5, 2, 'W'),
(2, 6, 3, 'W'),
(2, 7, 4, 'L'),
(2, 8, 5, 'W'),
(2, 9, 6, 'L'),
(2, 10, 7, 'L'),
(2, 11, 8, 'W'), --END OF ROUND 1

(2, 5, 9, 'W'),
(2, 6, 10, 'L'),
(2, 8, 11, 'L'),
(2, 11, 12, 'W'); -- END OF ROUND 2, championship left to swim


COMMIT TRANSACTION;
