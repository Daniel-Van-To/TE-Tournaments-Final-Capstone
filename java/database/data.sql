BEGIN TRANSACTION;

INSERT INTO users (username, display_name, password_hash,role) VALUES ('user', 'user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username, display_name, password_hash,role) VALUES ('admin', 'admin', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO users (username, display_name, password_hash,role) VALUES ('harry_simpson', 'harry', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
--Harry is user 3
INSERT INTO users (username, display_name, password_hash,role) VALUES ('daniel_to', 'daniel', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
--Daniel is user 4
INSERT INTO users (username, display_name, password_hash,role) VALUES ('revathi_ajjarapu', 'revathi', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
--Reva is user 5

INSERT INTO game (game_name,max_players)
VALUES ('running', 1), ('valorant', 5), ('cricket', 11), ('soccer', 11), ('football', 11), ('baseball', 26), ('basketball', 13) ('tennis', 2), ('dancesport', 2), ('parkour', 1), ('pole sports', 1), ('canoeing', 2), ('water polo', 7), ('swimming', 1);

INSERT INTO team (team_captain_id, team_name, game_name, accepting_members)
VALUES (3, 'find pearls', 'running', false), (4, 'pho 95', 'valorant', true), (5, 'raystar', 'running', false);

INSERT INTO tournament (host_id,tournament_name,entry_fee,game_name)
VALUES (2, 'test tournament', 0, 'running');

INSERT INTO team_user (user_id, team_id)
VALUES (3, 1), (5, 3), (4, 2), (3, 2);
--links harry to 'find pearls'
--links revathi to 'raystar'
--links daniel to 'pho 95'
--links harry to 'pho 95'

INSERT INTO team_tournament (team_id, tournament_id)
VALUES (3, 1), (1, 1);
--links 'raystar' to test tournament
--links 'find pearl' to test tournament

COMMIT TRANSACTION;
