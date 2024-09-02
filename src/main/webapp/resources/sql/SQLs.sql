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

select * from member;
select * from board;
INSERT INTO MEMBER VALUES("imfirst",sha1(md5("first1234")), "희성", "010-9936-8901", "khb113322@gmail.com", now(), "avatar.png", 100);
INSERT INTO MEMBER VALUES("imsecond",sha1(md5("second1234")), "토성", "010-9936-8902", "second@naver.com", now(), "avatar.png", 100);
INSERT INTO board(title, content, writer)
VALUES ('1빠', '냉무', 'imfirst');
INSERT INTO board(title, content, writer)
VALUES ('2빠요', '냉무요', 'imsecond');
delete from member where userId = 'imfirst';
commit;