BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, team, tournament, game, team_user, team_tournament, request;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	display_name varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	email varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE game (
   -- game_id SERIAL,
    game_name varchar(50) NOT NULL,
    max_players integer,
    CONSTRAINT PK_game PRIMARY KEY (game_name)
);

CREATE TABLE team (
    team_id SERIAL,
    team_name varchar(50) NOT NULL UNIQUE,
    team_captain_id integer NOT NULL,
    game_name varchar(50) NOT NULL,
    accepting_members boolean NOT NULL,
    CONSTRAINT PK_team PRIMARY KEY (team_id),
    CONSTRAINT FK_team_users FOREIGN KEY (team_captain_id) REFERENCES users(user_id),
    CONSTRAINT FK_team_game FOREIGN KEY (game_name) REFERENCES game(game_name)
);

CREATE TABLE tournament (
    tournament_id SERIAL,
    host_id integer NOT NULL,
    tournament_name varchar(50) NOT NULL,
    entry_fee decimal(5,2),
    game_name varchar(50) NOT NULL,
    accepting_teams boolean NOT NULL,
    tournament_status varchar(5) NOT NULL,
    CONSTRAINT PK_tournament PRIMARY KEY (tournament_id),
    CONSTRAINT FK_tournament_host_user_id FOREIGN KEY (host_id) REFERENCES users(user_id),
    CONSTRAINT FK_tournament_game FOREIGN KEY (game_name) REFERENCES game(game_name)
);

CREATE TABLE request (

    request_id SERIAL,
    tournament_id integer,
    team_id integer,
    game_name varchar(50),
    request_status varchar(1),
    requester_id integer,
    CONSTRAINT PK_request PRIMARY KEY (request_id),
    CONSTRAINT FK_tournament_join_request FOREIGN KEY (tournament_id) REFERENCES tournament(tournament_id),
    CONSTRAINT FK_team_join_request FOREIGN KEY (team_id) REFERENCES team(team_id),
    CONSTRAINT FK_new_game_name_request FOREIGN KEY (game_name) REFERENCES game(game_name),
    CONSTRAINT FK_requester_user_id FOREIGN KEY (requester_id) REFERENCES users(user_id)

);


CREATE TABLE team_user (
    user_id integer NOT NULL,
    team_id integer NOT NULL,
    CONSTRAINT PK_team_user PRIMARY KEY (user_id, team_id),
    CONSTRAINT FK_team_user_users FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT FK_team_user_team FOREIGN KEY (team_id) REFERENCES team(team_id)
);

CREATE TABLE team_tournament (
    team_id integer NOT NULL,
    tournament_id integer NOT NULL,
    CONSTRAINT PK_team_tournament PRIMARY KEY (team_id, tournament_id),
    CONSTRAINT FK_team_tournament_team FOREIGN KEY (team_id) REFERENCES team(team_id),
    CONSTRAINT FK_team_tournament_tournament FOREIGN KEY (tournament_id) REFERENCES tournament(tournament_id)
);

COMMIT TRANSACTION;
