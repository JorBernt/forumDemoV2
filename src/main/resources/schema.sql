CREATE TABLE Users (
    username varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null,
    primary key (username)
);

CREATE TABLE Category (
    catID INTEGER IDENTITY NOT NULL,
    title VARCHAR (150) NOT NULL,
    description VARCHAR (500) NOT NULL,
    primary key (catID)
);

CREATE TABLE Post (
    postID INTEGER IDENTITY NOT NULL,
    title varchar (150) NOT NULL,
    username varchar (255) NOT NULL,
    time TIMESTAMP not null,
    catID INTEGER NOT NULL,
    PRIMARY KEY (postID),
    FOREIGN KEY (username) REFERENCES Users (username),
    FOREIGN KEY (catID) REFERENCES Category (catID)
);

CREATE TABLE Comment (
    commentID INTEGER IDENTITY NOT NULL,
    text VARCHAR(2000) NOT NULL,
    username VARCHAR(255) NOT NULL,
    time TIMESTAMP not null,
    postID INTEGER NOT NULL,
    PRIMARY KEY (commentID),
    FOREIGN KEY (username) REFERENCES Users (username),
    FOREIGN KEY (postID) REFERENCES Post (postID)
);


