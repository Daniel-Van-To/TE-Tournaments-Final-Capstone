BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO users (username,password_hash,role) VALUES ('harry_simpson','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('daniel_to','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('revathi_ajjarapu','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');

INSERT INTO game (game_name,max_players)
VALUES ('running', 1)
VALUES ('valorant', 5)
VALUES ('cricket', 11)

INSERT INTO team (team_captain_id, team_name, game_name, accepting_members)
VALUES (3, 'find pearls', 'running', true)
VALUES (4, 'pho 95', 'valorant', true)
VALUES (5, 'raystar', 'running', true)

INSERT INTO tournament (host_id,tournament_name,entry_fee,game_name)
VALUES (2, 'test tournament', 0, 'running')

INSERT INTO team_user (user_id, team_id)
VALUES (3, 1)
--links harry to 'find pearls'
VALUES (5, 3)
--links revathi to 'raystar'
VALUES (4, 2)
--links daniel to 'pho 95'

INSERT INTO team_tournament (team_id, tournament_id)
VALUES (3, 1)
--links 'raystar' to test tournament
VALUES (1, 1)
--links 'find pearl' to test tournament

COMMIT TRANSACTION;
