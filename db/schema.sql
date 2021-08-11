# DB 생성
DROP DATABASE IF EXISTS wikenDevelopment;
CREATE DATABASE wikenDevelopment;
USE wikenDevelopment;

# 게시물 테이블 생성
CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    title CHAR(100) NOT NULL,
    `body` TEXT NOT NULL
);

# 게시물, 테스트 데이터 생성
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목 1',
`body` = '내용 1';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목 2',
`body` = '내용 2';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목 3',
`body` = '내용 3';

# 회원 테이블 생성
CREATE TABLE `member` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId VARCHAR(256) NOT NULL,
    loginPw VARCHAR(256) NOT NULL,
    authLevel SMALLINT(2) UNSIGNED DEFAULT 3 COMMENT '권한레벨(3=일반,7=관리자)',
    `name` VARCHAR(256) NOT NULL,
    nickname VARCHAR(256) NOT NULL,
    cellphoneNo VARCHAR(256) NOT NULL,
    email VARCHAR(256) NOT NULL,
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '탈퇴여부(0=탈퇴전,1=탈퇴)',
    delDate DATETIME COMMENT '탈퇴날짜'
);

# 회원, 테스트 데이터 생성(관리자 회원)
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'admin',
loginPw = 'admin',
authLevel = 7,
`name` = '관리자',
nickname = '관리자',
cellphoneNo = '01011111111',
email = 'jangka512@gmail.com';

# 회원, 테스트 데이터 생성(일반 회원)
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user1',
loginPw = 'user1',
`name` = '사용자1',
nickname = '사용자1',
cellphoneNo = '01011111111',
email = 'jangka512@gmail.com';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user2',
loginPw = 'user2',
`name` = '사용자2',
nickname = '사용자2',
cellphoneNo = '01011111111',
email = 'jangka512@gmail.com';

# 게시물 테이블에 회원정보 추가
ALTER TABLE article ADD COLUMN memberId INT(10) UNSIGNED NOT NULL AFTER `updateDate`;

# 기존 게시물의 작성자를 2번호으로 지정
UPDATE article
SET memberId = 2
WHERE memberId = 0;

# 문서 테이블
CREATE TABLE ken (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    title CHAR(100) NOT NULL,
    `source` MEDIUMTEXT NOT NULL,
    result MEDIUMTEXT NOT NULL,
    typeCode CHAR(20) NOT NULL COMMENT '종류 코드',
    type2Code CHAR(20) NOT NULL COMMENT '종류2 코드',
    `docTypeVersion` SMALLINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '문서버전',
    publishDate DATETIME COMMENT '공개된 날짜',
    publishStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '공개여부(0=미공개,1=공개)',
    delDate DATETIME COMMENT '삭제된 날짜',
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '삭제여부(0=미삭제,1=삭제)'
);

# 문서 테이블
INSERT INTO ken
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
title = '무제 1',
`source` = '# 내용1',
result = '<h1>내용1</h1>',
typeCode = 'common',
type2Code = 'markdown';

INSERT INTO ken
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
title = '무제 2',
`source` = '# 내용2',
result = '<h1>내용2</h1>',
typeCode = 'common',
type2Code = 'markdown';

SELECT * FROM `member`;

SELECT LENGTH('8833cb57eb821d629899ba0015aec1aa8efc7c2472f0590bedd1cebe28107cd6');

DESC MEMBER