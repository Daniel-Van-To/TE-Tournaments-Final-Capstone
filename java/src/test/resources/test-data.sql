BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user1','user1','ROLE_USER'); -- id 1
INSERT INTO users (username,password_hash,role) VALUES ('user2','user2','ROLE_USER'); -- id 2
INSERT INTO users (username,password_hash,role) VALUES ('user3','user3','ROLE_USER'); -- id 3

--test games
INSERT INTO game(game_name,max_players)
VALUES('running', 1),
('valorant', 5),
('tennis', 2);

--test teams
INSERT INTO team(team_name,team_captain_id,game_name,accepting_members)
VALUES('Team1', 1, 'valorant', true),   -- team_id is 1
('Team2', 2, 'tennis', true),           -- team_id is 2
('Team3', 3, 'running', false);         -- team_id is 3

COMMIT TRANSACTION;
