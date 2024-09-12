CREATE TABLE `yhs`.`member` (
  `userId` VARCHAR(8) NOT NULL,
  `userPwd` VARCHAR(200) NOT NULL,
  `userName` VARCHAR(12) NULL,
  `mobile` VARCHAR(13) NULL,
  `email` VARCHAR(50) NULL,
  `registerDate` DATETIME NULL DEFAULT now(),
  `userImg` VARCHAR(50) NOT NULL DEFAULT 'avatar.png',
  `userPoint` INT NULL DEFAULT 100,
  PRIMARY KEY (`userId`),
  UNIQUE INDEX `mobile_UNIQUE` (`mobile` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);
  
  CREATE TABLE `yhs`.`hboard` (
  `boardNo` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(20) NOT NULL,
  `content` VARCHAR(2000) NULL,
  `writer` VARCHAR(8) NULL,
  `postDate` DATETIME NULL DEFAULT now(),
  `readCount` INT NULL DEFAULT 0,
  `ref` INT NULL DEFAULT 0,
  `step` INT NULL DEFAULT 0,
  `refOrder` INT NULL DEFAULT 0,
  PRIMARY KEY (`boardNo`),
  INDEX `hboard_member_fk_idx` (`writer` ASC) VISIBLE,
  CONSTRAINT `hboard_member_fk`
    FOREIGN KEY (`writer`)
    REFERENCES `sky`.`member` (`userId`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION)
COMMENT = '계층형게시판';

CREATE TABLE `sky`.`pointdef` (
  `pointWhy` VARCHAR(20) NOT NULL,
  `pointScore` INT NULL,
  PRIMARY KEY (`pointWhy`))
COMMENT = '유저에게 적립할 포인트에 대한 정책 정의 테이블\n어떤 사유로 몇 포인트를 지급하는지에 대해 정의';

CREATE TABLE `yhs`.`boardupfiles` (
  `originFileName` VARCHAR(100) NOT NULL,
  `thumbFileName` VARCHAR(100) NULL,
  `ext` VARCHAR(20) NULL,
  `size` INT NULL,
  `boardNo` INT NULL,
  `base64Img` TEXT NULL,
  `boardFileNo` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`boardFileNo`),
  INDEX `boardupfiles_boardNo_fk_idx` (`boardNo` ASC) VISIBLE,
  CONSTRAINT `boardupfiles_boardNo_fk`
    FOREIGN KEY (`boardNo`)
    REFERENCES `yhs`.`board` (`boardNo`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

CREATE TABLE `yhs`.`boardreadlog` (
  `boardReadLogNo` INT NOT NULL AUTO_INCREMENT,
  `who` VARCHAR(130) NOT NULL,
  `when` DATETIME NOT NULL DEFAULT now(),
  `boardNo` INT NULL,
  PRIMARY KEY (`boardReadLogNo`),
  INDEX `boardReadLog_boardNo_fk_idx` (`boardNo` ASC) VISIBLE,
  CONSTRAINT `boardReadLog_boardNo_fk`
    FOREIGN KEY (`boardNo`)
    REFERENCES `yhs`.`board` (`boardNo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


select * from member;
select * from board;
select * from pointdef;
INSERT INTO MEMBER VALUES("imfirst",sha1(md5("first1234")), "희성", "010-9936-8901", "khb113322@gmail.com", now(), "avatar.png", 100);
INSERT INTO MEMBER VALUES("imsecond",sha1(md5("second1234")), "토성", "010-9936-8902", "second@naver.com", now(), "avatar.png", 100);
INSERT INTO board(title, content, writer)
VALUES ('1빠', '냉무', 'imfirst');
INSERT INTO board(title, content, writer)
VALUES ('2빠요', '냉무요', 'imsecond');
delete from member where userId = 'imfirst';
commit;
select * from pointlog;
commit;
ROLLBACK;

insert into pointlog(pointWho, pointWhy, pointScore)
SELECT m.userId, p.pointWhy, p.pointScore
FROM member m, pointdef p
WHERE m.userId = 'imfirst'
AND p.pointWhy = '회원가입';

UPDATE board
SET refOrder = refOrder + 1
WHERE ref=22;
select * from board order by boardno desc;
insert into board(title, content, writer, postDate, readCount, ref, step, refOrder)
values('답글1', '답글내용', 'imsecond', now(), 1, 0, 22, 1);
insert into boardupfiles(boardNo, originFileName)
values(1, '몰라.png');
commit;
SELECT b.*, f.*, m.email
FROM board b LEFT OUTER JOIN boardupfiles f
ON b.boardNo = f.boardNo
inner JOIN member m
ON b.writer = m.userId
WHERE b.boardNo = 1;
SELECT readWhen
FROM boardreadlog;
SELECT IFNULL(datediff(now(), (
	SELECT readwhen 
    FROM boardreadlog 
    WHERE readwho = '127.0.0.1'
    AND boardNo = 21)), -1);
    
    SELECT COUNT(*)
		FROM board
		WHERE ref = 22;
         SET foreign_key_checks = 1;
select * from board order byboardupfiles boardno desc;
delete from board where boardNo = 25;
select * from board 
order by ref desc, reforder;

alter table board add column isDelete varchar(20);
SELECT boardNo,
CASE
WHEN isDelete LIKE 'Y' THEN '삭제된 게시글입니다.'
ELSE title
END as title, content, writer, postDate, readCount, ref, step, refOrder, isDelete
FROM board
ORDER BY ref desc, refOrder