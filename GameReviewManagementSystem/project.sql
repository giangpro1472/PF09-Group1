CREATE DATABASE gms_project;
USE gms_project;

/* FOR ACCOUNT */
CREATE TABLE user_role(
 role_id INT,
 user_title NVARCHAR(10) NOT NULL,
 PRIMARY KEY (role_id)
);

INSERT INTO user_role VALUES (1, 'Admin'), (2, 'User');

CREATE TABLE accounts(
 username NVARCHAR(20),
 pass_word NVARCHAR(50) NOT NULL,
 email NVARCHAR(30) NOT NULL,
 gender NVARCHAR (10) NOT NULL,
 address NVARCHAR(50) NOT NULL,
 phone NVARCHAR(15) NOT NULL, 
 account_status NVARCHAR(15) DEFAULT('Active'),
 create_date DATE DEFAULT(CURRENT_DATE()),
 role_id INT,
 last_date DATE DEFAULT(CURRENT_DATE()), 
 PRIMARY KEY (username),
 FOREIGN KEY (role_id) REFERENCES user_role(role_id),
 UNIQUE (email, phone)
);

/* PROCEDURE FOR ACCOUNTS */
DELIMITER //
CREATE PROCEDURE register
(IN user_name NVARCHAR(50), IN pass NVARCHAR(200), IN mail NVARCHAR(50),
IN gen NVARCHAR(10), IN ad NVARCHAR(50), IN num NVARCHAR(15), IN us_role INT)
BEGIN 
   INSERT INTO accounts(username, pass_word, email, gender,
                        address, phone, role_id) VALUES
   (user_name, pass, mail, gen, ad, num, us_role);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE disable_account(IN userna NVARCHAR(30))
BEGIN 
  UPDATE accounts
  SET account_status = 'Disable'
  WHERE username = userna;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE unlock_account(IN userna NVARCHAR(30))
BEGIN 
  UPDATE accounts
  SET account_status = 'Active'
  WHERE username = userna;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE checkAccount(IN user_name NVARCHAR(30), IN pass NVARCHAR(200))
BEGIN
  SELECT username, pass_word
  FROM accounts
  WHERE username = user_name AND pass_word = pass;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE checkStatus(IN user_name NVARCHAR(30))
BEGIN
  SELECT account_status
  FROM accounts
  WHERE username = user_name;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE checkRole(IN user_name NVARCHAR(30))
BEGIN
  SELECT role_id
  FROM accounts
  WHERE username = user_name;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE checkUsername(IN user_name NVARCHAR(30))
BEGIN
    SELECT username 
    FROM accounts
    WHERE username = user_name;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE checkEmail(IN mail NVARCHAR(30))
BEGIN
    SELECT email 
    FROM accounts
    WHERE email = mail;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE checkPhone(IN num NVARCHAR(30))
BEGIN
    SELECT phone 
    FROM accounts
    WHERE phone = num;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE last_login(IN uname NVARCHAR(30))
BEGIN
    UPDATE accounts
    SET last_date = CURRENT_DATE()
    WHERE username = uname;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE edit_profile(IN userna NVARCHAR(30), IN mail NVARCHAR(20), IN gen NVARCHAR(5),
IN ad NVARCHAR(30), IN num NVARCHAR(15))
BEGIN 
  UPDATE accounts
  SET email = mail 
  WHERE username = userna;
  
  UPDATE accounts
  SET gender = gen
  WHERE username = userna;
  
  UPDATE accounts
  SET address = ad
  WHERE username = userna;
  
  UPDATE accounts
  SET phone = num
  WHERE username = userna;
  
END //
DELIMITER ;

CREATE VIEW disable_users AS 
SELECT a.username, a.pass_word, a.email, a.gender, a.address,
a.phone, a.account_status, a. create_date, a.role_id, u.user_title, a.last_date 
FROM accounts AS a INNER JOIN user_role AS u ON a.role_id = u.role_id
WHERE a.account_status = 'Disable';

CREATE VIEW active_users AS 
SELECT a.username, a.pass_word, a.email, a.gender, a.address,
a.phone, a.account_status, a. create_date, a.role_id, u.user_title, a.last_date 
FROM accounts AS a INNER JOIN user_role AS u ON a.role_id = u.role_id
WHERE a.account_status = 'Active';

/* FOR REVIEW */

CREATE TABLE genres (
 genre_id INT,
 genre_name NVARCHAR (20) NOT NULL,
 PRIMARY KEY (genre_id)
);

INSERT INTO genres VALUES (1, 'Action'),(2, 'Horror'),(3, 'Simulation'),
						  (4, 'Puzzle'),(5, 'Indie'),(6, 'Stratedy'),
						  (7, 'Survival'), (8, 'Moba'), (9, 'RPG'),
                          (10, 'Role-play');

CREATE TABLE games(
 game_id INT AUTO_INCREMENT,
 game_name NVARCHAR(150) NOT NULL,
 genre_id INT,
 developers NVARCHAR(100) NOT NULL,
 release_date DATE NOT NULL,
 UNIQUE (game_name),
 PRIMARY KEY (game_id),
 FOREIGN KEY (genre_id) REFERENCES genres(genre_id)
);

CREATE TABLE game_images(
 image_id INT AUTO_INCREMENT,
 game_id INT, 
 image_name NVARCHAR(20),
 PRIMARY KEY (image_id),
 FOREIGN KEY (game_id) REFERENCES games(game_id)
);

-- For games --------------------------------------------------------------

DELIMITER //
CREATE PROCEDURE insert_game
(IN gname NVARCHAR (150), IN id INT ,IN dev NVARCHAR(100), IN rdate DATE)
BEGIN 
  INSERT INTO games (game_name, genre_id, developers, release_date)
  VALUES (gname, id, dev, rdate);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE insert_images (IN id INT, IN iname NVARCHAR(30))
BEGIN 
  INSERT INTO game_images (game_id, image_name)
  VALUES (id, iname);
END //
DELIMITER ;

CREATE VIEW getGameID AS
SELECT game_id 
FROM games
ORDER BY game_id DESC
LIMIT 1;
-- -------------------------------------------------------------------

CREATE TABLE user_reviews(
 review_id INT AUTO_INCREMENT,
 username NVARCHAR(20),
 game_id INT, 
 user_score INT NOT NULL,
 post_date DATE DEFAULT(CURRENT_DATE()),
 content NVARCHAR (5000) NOT NULL,
 PRIMARY KEY (review_id),
 FOREIGN KEY (username) REFERENCES accounts(username),
 FOREIGN KEY (game_id) REFERENCES games(game_id)
);

DELIMITER //
CREATE PROCEDURE getReviews (IN id INT)
BEGIN 
  SELECT *
  FROM user_reviews
  WHERE game_id = id ;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE checkGamesID(IN id INT)
BEGIN
    SELECT game_id 
    FROM games
    WHERE game_id = id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE checkReviewID(IN id INT)
BEGIN
    SELECT review_id 
    FROM user_reviews
    WHERE review_id = id;
END //
DELIMITER ;

CREATE VIEW games_scores AS
SELECT g.game_id, g.game_name, ge.genre_name, ge.genre_id ,g.developers, g.release_date, AVG(u.user_score) AS score
FROM games AS g INNER JOIN genres AS ge ON ge.genre_id = g.genre_id
INNER JOIN user_reviews AS u ON u.game_id = g.game_id
GROUP BY g.game_id;

/* PROCEDURE FOR REVIEWS */
CREATE VIEW ranking_games AS 
SELECT * 
FROM games_scores
ORDER BY score DESC
LIMIT 10;

DELIMITER //
CREATE PROCEDURE searchGameID (IN id INT)
BEGIN 
  SELECT *
  FROM games_scores
  WHERE game_id LIKE id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE post_review
(IN uname NVARCHAR (20), IN id INT, IN score INT ,IN cont NVARCHAR(5000))
BEGIN 
  INSERT INTO user_reviews (username, game_id, user_score, content)
  VALUES (uname, id, score, cont);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE search_name (IN gname NVARCHAR(30))
BEGIN 
  SELECT *
  FROM games_scores
  WHERE game_name LIKE gname;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE search_genre (IN id INT)
BEGIN 
  SELECT *
  FROM games_scores
  WHERE genre_id = id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE deleteGame(IN id INT)
BEGIN 
  DELETE FROM games
  WHERE game_id = id;
END //
DELIMITER ;


/* VIEW */

CREATE VIEW review_in_day AS
SELECT * 
FROM user_reviews
WHERE post_date = CURRENT_DATE();

DELIMITER //
CREATE PROCEDURE deleteReview(IN id INT)
BEGIN 
  DELETE FROM user_reviews
  WHERE review_id = id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE checkGameName(IN gname NVARCHAR(30))
BEGIN
    SELECT game_name 
    FROM games
    WHERE game_name LIKE gname;
END //
DELIMITER ;

DELIMITER $$
CREATE TRIGGER bf_delete_games
BEFORE DELETE ON games
FOR EACH ROW
BEGIN
  DELETE FROM game_images
  WHERE game_id = old.game_id;

  DELETE FROM user_reviews
  WHERE game_id = old.game_id;
END $$
DELIMITER ; 


CALL register("admin","21232f297a57a5a743894a0e4a801fc3","admin@gmail.com","M","Ha Noi",0980980987,1);
CALL register("wjbuchua",'3193552c1f44766e9e69f441afd008dc',"wibugod@gmail.com","M","Isekai",0987654321,1);
CALL register("pewdiepie",'c83e986814118cb34e3bdf0bbd12ab55',"edgar@gmail.com","M","England", 112 ,2);
CALL register("nigahiga",'465318ee255235720a5fd2d9ab92735e',"notryanhiga@gmail.com","M","US",113,2);
CALL register("onlygame",'779f985589fde0b76262441749decd47',"thompsons@gmail.com","F","Ha Noi",0379680828,2);
CALL register("pedobear",'893b56e3cfe153fb770a120b83bac20c',"pedobear@gmail.com","M","US",123456789,2);
CALL register("naruto",'cf9ee5bcb36b4936dd7064ee9b2f139e',"naruto@gmail.com","M","Konoha",11111,2);
CALL register("sasuke",'93207db25ad357906be2fd0c3f65f3dc',"sasuke@gmail.com","M","Konoha", 11112 ,2);
CALL register("batman",'ec0e2603172c73a8b644bb9456c1ff6e',"notbatman@gmail.com","M","Gotham City",11113,2);
CALL register("pianta",'8b0ff9f7684fbc4de319f77f8eb20544',"pianta@gmail.com","M","US",123231114,2);
CALL register("phantichgame",'85bb8d6055f9fe235e380b6b06e1cd1c',"ptg@gmail.com","M","Ha Noi",11115,1);
CALL register("duong404",'736e454e1bdc2c8da6280f84363532f7',"duong@gmail.com","M","Thanh Hoa",1222115, 2);

CALL insert_game('Five Nights at Freddy 1', 2, 'Scott Cawthon', '2014/08/18');
CALL insert_images(1, 'foxy.jpg');
CALL insert_images(1, 'bonny.jpg');
CALL insert_game('Five Nights at Freddy 2', 2, 'Scott Cawthon', '2014/11/11');
CALL insert_images(2, 'chica.jpg');
CALL insert_images(2, 'muppet.jpg');
CALL insert_game('Five Nights at Freddy 3', 2, 'Scott Cawthon', '2015/03/02');
CALL insert_images(3, 'springtrap.jpg');
CALL insert_images(3, 'freddy.jpg');
CALL insert_game('Five Nights at Freddy 4', 2, 'Scott Cawthon', '2015/07/24');
CALL insert_images(4, 'cryboy.jpg');
CALL insert_game('Sister Location', 2, 'Scott Cawthon', '2016/10/07');
CALL insert_images(5, 'baby.jpg');
CALL insert_images(5, 'ballora.jpg');
CALL insert_game('Five Nights at Freddy 6', 2, 'Scott Cawthon', '2017/12/05');
CALL insert_images(6, 'purple.jpg');
CALL insert_images(6, 'man.jpg');
CALL insert_game('DARK SOULS', 1, 'FromSoftware', '2012/08/24');
CALL insert_images(7, 'boss.jpg');
CALL insert_images(7, 'crepp.jpg');
CALL insert_game('DARK SOULS II', 1, 'FromSoftware', '2014/04/25');
CALL insert_images(8, 'boss.jpg');
CALL insert_images(8, 'crepp.jpg');
CALL insert_game('DARK SOULS III', 1, 'FromSoftware', '2016/04/12');
CALL insert_images(9, 'boss.jpg');
CALL insert_images(9, 'crepp.jpg');
CALL insert_game('Minecraft', 7, 'Mojang', '2011/11/18');
CALL insert_images(10, 'steve.jpg');
CALL insert_images(10, 'nether.jgg');
CALL insert_game('Terraria', 7, 'Re-Logic', '2011/05/17');
CALL insert_images(11, 'guide.jpg');
CALL insert_images(11, 'eyes.jpg');
CALL insert_game('Pony Island', 2, 'Daniel Mullins Games', '2016/05/16');
CALL insert_images(12, 'satan.jpg');
CALL insert_images(12, 'pony.jpg');
CALL insert_game('The Hex', 2, 'Daniel Mullins Games', '2018/10/16');
CALL insert_images(13, 'survival.jpg');
CALL insert_images(13, 'crimminal.jpg');
CALL insert_game('Inunaki Tunnel', 2, 'Chilla Art', '2019/11/19');
CALL insert_images(14, 'dragon.jpg');
CALL insert_images(14, 'dog.jpg');
CALL insert_game('ParanormalHK', 2, 'Ghostpie Studio', '2020/01/06');
CALL insert_images(15, 'ghost.jpg');
CALL insert_images(15, 'theface.jpg');
CALL insert_game('Paper Dolls: Original', 2, 'Beijing Litchi Culture Media', '2019/04/19');
CALL insert_images(16, 'ghost.jpg');
CALL insert_images(16, 'doll.jpg');
CALL insert_game('Among Us', 7, 'Innersloth', '2018/11/17');
CALL insert_images(17, 'immposter.jpg');
CALL insert_images(17, 'kill.jpg');
CALL insert_game('Rogue Warrior', 1, 'Rebellion', '2009/12/02');
CALL insert_images(18, 'warrior.jpg');
CALL insert_images(18, 'russia.jpg');
CALL insert_game('Undertale', 5, 'tobyfox', '2015/09/15');
CALL insert_images(19, 'flowey.jpg');
CALL insert_images(19, 'sans.jpg');
CALL insert_game('THE QUIET MAN', 1, 'Square Enix', '2018/11/01');
CALL insert_images(20, 'deef.jpg');
CALL insert_images(20, 'fight.jpg');
CALL insert_game('Goat Simulator', 3, 'Coffee Stain Studios', '2014/04/02');
CALL insert_images(21, 'goat.jpg');
CALL insert_images(21, 'cow.jpg');
CALL insert_game('Rambo The Video Game', 1, 'Teyon', '2014/02/22');
CALL insert_images(22, 'rambo.jpg');
CALL insert_images(22, 'plane.jpg');
CALL insert_game('Flight Simulator', 3, 'Asobo Studio', '2020/08/18');
CALL insert_images(23, 'maybay.jpg');
CALL insert_images(23, 'hanoi.jpg');
CALL insert_game('League of Legend', 8, 'Riot Games', '2009/12/02');
CALL insert_images(24, 'ashe.jpg');
CALL insert_images(24, 'abc.jpg');
CALL insert_game('Dota2', 8, 'Valve Software', '2013/07/09');
CALL insert_images(25, 'invoker.jpg');
CALL insert_images(25, 'teachies.jpg');
CALL insert_game('Arena of Valor', 8, ' Tencent Games', '2016/10/14');
CALL insert_images(26, 'garen.jpg');
CALL insert_images(26, 'xinzhao;jpg');
CALL insert_game('Vainglory', 8, 'Super Evil Megacorp', '2015/09/15');
CALL insert_images(27, 'battle.jpg');
CALL insert_images(27, 'stage.jpg');
CALL insert_game('Stardew Valley', 3, 'ConcernedApe', '2016/02/27');
CALL insert_images(28, 'farm.jpg');
CALL insert_images(28, 'abc.jpg');
CALL insert_game('Toilet Simulator 2020', 3, 'Kurlik Industries', '2019/01/18');
CALL insert_images(29, 'pepe.jpg');
CALL insert_images(29, 'popo.jpg');
CALL insert_game('Plants vs. Zombies', 7, 'PopCap Games', '2009/05/06');
CALL insert_images(30, 'zombie.jpg');
CALL insert_images(30, 'plant.jpg');

CALL post_review('admin', 1, 9, 'Game hay day');
CALL post_review('pedobear', 1, 10, 'game duoc day');
CALL post_review('pewdiepie', 1, 8, 'this game is hard');
CALL post_review('duong404', 1, 7, 'toi den duoc dem thu 3 r nghi');
CALL post_review('wjbuchua', 1, 10, 'game duoc day');

CALL post_review('phantichgame', 2, 8, 'game van hay day');
CALL post_review('pianta', 2, 9, 'toi chua choi phan 1 :v');
CALL post_review('pewdiepie', 2, 8, 'this game still change nothing');
CALL post_review('duong404', 2, 6, 'game lon');
CALL post_review('wjbuchua', 2, 7, 'game nhu lon dhs toi van choi');



