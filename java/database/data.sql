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
('batman', 'Batman', 'iamthebatman@gmail.com', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER'),
--user id 10
('phil', 'PhillyD', 'phillyD@gmail.com', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER'), --User Id 11
('jeff', 'Jeff the Man', 'jeff@gmail.com', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER'), --User Id 12
('tommy', 'Tommy Thompson', 'iamthebatman@gmail.com', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER'), -- User Id 13
('jimmy', 'Jimmy Neutron', 'iamthebatman@gmail.com', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER'), -- User Id 14
('carl', 'Carl Wheezer', 'iamthebatman@gmail.com', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER'), -- User Id 15
('sheen', 'Sheen Estevez', 'iamthebatman@gmail.com', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER'); -- User Id 16


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
('swimming', 1),
('chess', 1),
('kayaking', 1);

--Dummy Team used for creating empty scores
INSERT INTO team (team_id, team_captain_id, team_name, game_name, accepting_members)
VALUES (0,4, 'dummy', 'running', false);

INSERT INTO team (team_captain_id, team_name, game_name, accepting_members)
VALUES (3, 'find pearls', 'running', false), --team_id 1
(4, 'pho 95', 'valorant', true), --team_id 2
(5, 'raystar', 'running', false), --team_id 3

--swimming teams for 8 person tourney
(3, 'Flying Turtle', 'swimming', false), --team_id 4
(4, 'Pho 96', 'swimming', false), --team_id 5
(5, 'Ray Stars', 'swimming', false), --team_id 6
(6, 'Self Phelp', 'swimming', false), --team_id 7
(7, 'Chandra''s Joy', 'swimming', false), --team_id 8
(8, 'Ben''s team', 'swimming', false), --team_id 9
(9, 'Scaly Croc', 'swimming', false), --team_id 10
(10, 'Fast ''R Us', 'swimming', false), --team_id 11

-- kayaking test team for testing tournament initialization
(3, 'Harrys Kayaking team', 'kayaking', false),--team_id 12
(4, 'Daniels Kayaking team', 'kayaking', false),--team_id 13
(5, 'Revathis Kayaking team', 'kayaking', false),--team_id 14
(6, 'Silliesimps Kayaking team', 'kayaking', false),--team_id 15
(7, 'Chandras Kayaking team', 'kayaking', false),--team_id 16
(8, 'Bens Kayaking team', 'kayaking', false),--team_id 17
(9, 'Catwomans Kayaking team', 'kayaking', false),--team_id 18
(10, 'Batmans Kayaking team', 'kayaking', false),--team_id 19

-- cricket teams to flesh out the database
(7, 'Chandras Cricket team', 'cricket', true),--team_id 20
(8, 'Bens Cricket team', 'cricket', true),--team_id 21
(9, 'Catwomans Cricket team', 'cricket', true),--team_id 22
(10, 'Batmans Cricket team', 'cricket', true),--team_id 23
(11, 'PhillyD''s Cricket Team', 'cricket', true),--team_id 24

--More Swimming Teams for Harry's 16 person tournament
(3, 'Billie Jean', 'swimming', false), --team_id 25
(4, 'Dancing Queen', 'swimming', false), --team_id 26
(5, 'Sweet Child O'' Mine', 'swimming', false), --team_id 27
(6, 'Stairway to Heaven', 'swimming', false), --team_id 28
(7, 'Bohemian Rhapsody', 'swimming', false), --team_id 29
(8, 'Tank!', 'swimming', false), --team_id 30
(9, 'Atom Bomb Baby', 'swimming', false), --team_id 31
(10, 'Rocket 69', 'swimming', false); --team_id 32



INSERT INTO tournament (tournament_id, host_id,tournament_name,entry_fee,game_name, accepting_teams, tournament_status, maximum_participants)
VALUES(0, 2, 'dummy tournament', 0, 'valorant', false, 'o',10);

INSERT INTO tournament (host_id,tournament_name,entry_fee,game_name, accepting_teams, tournament_status, maximum_participants)
VALUES (2, 'test tournament', 0, 'running', true, 'o',32),
(3, 'Harry''s Swimming Tournament', 0, 'swimming', true, 'o', 8), -- tournament_id 2
(3, 'Fresh swimming tournament', 0, 'swimming', true, 's', 32), -- tournament_id 3
(3, 'Really Awesome White-Water Kayaking Challenge', 0, 'kayaking', true, 's', 8), -- tournament_id 4
(3, 'Swimmer''s Paradise ft. Coolio Tournament', 0, 'swimming', true, 's', 8), -- tournament_id 5

--CHESS
(3, 'Harry''s Chess Bonanza', 0, 'chess', true,'s',16), -- tournament_id 6
(4, 'Big Chess Bonanza', 0, 'chess', true,'s', 16), -- tournament_id 7
(12, 'Bigger Chess Bonanza', 0, 'chess', true, 's', 8), -- tournament_id 8
(12, 'Biggest Chess Bonanza', 0, 'chess', true, 's', 8), -- tournament_id 9

-- SOCCER
(4, 'Country Roads', 0, 'soccer', true, 's', 8), -- tournament_id 10
(13, 'Take Me Home', 0, 'soccer', true, 's', 8), -- tournament_id 11
(13, 'To the Place', 0, 'soccer', true, 's', 8), -- tournament_id 12
(13, 'I Belong', 0, 'soccer', true, 's', 8), -- tournament_id 13
(13, 'WEST VIRGINIA', 0, 'soccer', true, 's', 8), -- tournament_id 14

--BASEBALL
(16, 'A Big Iron On His Hip!', 0, 'baseball', true, 's', 8), -- tournament_id 15

--CRICKET
(14, 'COUNTRY ROADS', 0, 'cricket', true, 's', 8), -- tournament_id 16
(12, 'TAKE ME HOME', 0, 'cricket', true, 's', 8), -- tournament_id 17
(11, 'TO THE PLACE I BELONG', 0, 'cricket', true, 's', 8), -- tournament_id 18


--KAYAKING
(5, 'I Don''t Want To Set The World On FIRE', 0, 'kayaking', true, 's', 8), -- tournament_id 19

(3, 'BIG IRON', 0, 'swimming', true, 's', 16); -- tournament_id 20





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
(10, 11),


--linking everyone to their own kayaking team
(3, 12),
(4, 13),
(5, 14),
(6, 15),
(7, 16),
(8, 17),
(9, 18),
(10, 19),
(7, 20),
(8, 21),
(9, 22),
(10, 23),
(11, 24);



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
(11, 2),

--links some teams to the unstarted test swimming tournament
(5, 3),
(6, 3),
(10, 3),
(11, 3),
(8, 3), -- odd number :)

--linking kayaking teams to test kayaking tournament
(12, 4),
(13, 4),
(14, 4),
(15, 4),
(16, 4),
(17, 4),
(18, 4),
(19, 4),

--linking 6 teams to this 8 person tourney for Demo
(4, 5),
(6, 5),
(7, 5),
(8, 5),
(9, 5),
(10, 5),

--linking 14 teams to this 16 person tournament for potential Demo
(4, 20),
(6, 20),
(7, 20),
(8, 20),
(9, 20),
(10, 20),
(11, 20),
(3, 20),
(25, 20),
(26, 20),
(27, 20),
(28, 20),
(29, 20),
(30, 20),
(31, 20),
(32, 20);




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
(2, 11, 12, 'W'), -- END OF ROUND 2, championship left to swim

(2, 0, 13, ''),
(2, 0, 14, ''),
(2, 0, 15, '');


COMMIT TRANSACTION;
