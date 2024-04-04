BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE team (
    team_id SERIAL,
    team_name varchar(50) NOT NULL UNIQUE,
    team_captain_name varchar(50) NOT NULL,
    game_name varchar(50) NOT NULL,
    member_id integer[],
    accepting_members boolean,
    enrolled_tournaments int[],
    CONSTRAINT PK_team PRIMARY KEY (team_id),
    CONSTRAINT FK_team_user FOREIGN KEY (team_captain_name) REFERENCES user(user_id)
    CONSTRAINT FK_team_game FOREIGN KEY (game_name) REFERENCES game(game_name)

);

CREATE TABLE tournament (
    tournament_id SERIAL,
    host_id integer NOT NULL,
    tournament_name varchar(50) NOT NULL,
    entry_fee decimal(5,2),
    game_name varchar(50) NOT NULL,
    CONSTRAINT PK_tournament PRIMARY KEY (tournament_id),
    CONSTRAINT FK_tournament_user FOREIGN KEY (host_id) REFERENCES user(user_id),
    CONSTRAINT FK_tournament_game FOREIGN KEY (game_name) REFERENCE game(game_name)
);

CREATE TABLE game (
    game_id SERIAL,
    game_name varchar(50) NOT NULL,
    max_players integer,
    CONSTRAINT PK_game PRIMARY KEY (game_id),
);

CREATE TABLE team_user (
    user_id integer NOT NULL,
    team_id integer NOT NULL,
    CONSTRAINT PK_team_user PRIMARY KEY (user_id, team_id),
    CONSTRAINT FK_team_user_user FOREIGN KEY (user_id) REFERENCES user(user_id),
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
