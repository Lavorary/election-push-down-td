CREATE TABLE candidate (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL
    );

CREATE TABLE voter(
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TYPE vote_type AS ENUM ('VALID', 'BLANK', 'NULL');

CREATE TABLE VOTE (
    id SERIAL PRIMARY KEY,
    candidate_id INT REFERENCES  candidate(id),
    voter_id INT REFERENCES voter(id),
    vote_type vote_type NOT NULL
);