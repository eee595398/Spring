-- 이메일 인증키 테이블
DROP TABLE "AUTH_KEY";
CREATE TABLE "AUTH_KEY" (
	"AUTH_KEY_NO"	NUMBER		NOT NULL,
	"CODE"	CHAR(6)		NOT NULL,
	"EMAIL"	VARCHAR2(50)		NOT NULL,
	"CREATE_TIME"	DATE	DEFAULT SYSDATE	NOT NULL
);
COMMENT ON COLUMN "AUTH_KEY"."AUTH_KEY_NO" IS '인증키 구분 번호(SEQ_AUTH_KEY_NO)';
COMMENT ON COLUMN "AUTH_KEY"."CODE" IS '코드';
COMMENT ON COLUMN "AUTH_KEY"."EMAIL" IS '이메일';
COMMENT ON COLUMN "AUTH_KEY"."CREATE_TIME" IS '인증 코드 생성 시간';
ALTER TABLE "AUTH_KEY" ADD CONSTRAINT "PK_AUTH_KEY" PRIMARY KEY (
	"AUTH_KEY_NO"
);
CREATE SEQUENCE SEQ_AUTH_KEY_NO NOCACHE;


SELECT *
FROM "AUTH_KEY";




SELECT * FROM board;

DROP TABLE board CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_BOARD_NO;

DROP SEQUENCE SEQ_IMG_NO;

DROP SEQUENCE SEQ_COMMENT_NO;

CREATE SEQUENCE SEQ_BOARD_NO NOCACHE; -- 게시글 번호
CREATE SEQUENCE SEQ_IMG_NO NOCACHE; -- 게시글 이미지 번호
CREATE SEQUENCE SEQ_COMMENT_NO NOCACHE;

DELETE FROM "BOARD_TYPE" WHERE "BOARD_CODE" >5;





-- 게시판 종류
CREATE TABLE "BOARD_TYPE"(
	"BOARD_CODE" NUMBER CONSTRAINT "PK_BOARD_TYPE" PRIMARY KEY,
	"BOARD_NAME" VARCHAR2(30) NOT NULL
);
COMMENT ON COLUMN "BOARD_TYPE"."BOARD_CODE"
IS '게시판 코드(SEQ_BOARD_CODE)';
COMMENT ON COLUMN "BOARD_TYPE"."BOARD_NAME"
IS '게시판 이름';
CREATE SEQUENCE SEQ_BOARD_CODE NOCACHE;
-- 게시판 종류 추가
INSERT INTO "BOARD_TYPE" VALUES(SEQ_BOARD_CODE.NEXTVAL, '공지사항');
INSERT INTO "BOARD_TYPE" VALUES(SEQ_BOARD_CODE.NEXTVAL, '자유 게시판');
INSERT INTO "BOARD_TYPE" VALUES(SEQ_BOARD_CODE.NEXTVAL, '테스트 게시판');
INSERT INTO "BOARD_TYPE" VALUES(SEQ_BOARD_CODE.NEXTVAL, '질문 게시판');
INSERT INTO "BOARD_TYPE" VALUES(SEQ_BOARD_CODE.NEXTVAL, '점심 게시판');
COMMIT;
SELECT * FROM "BOARD_TYPE";


-- [게시판 DB 설정]
CREATE TABLE "BOARD" (
	"BOARD_NO"	NUMBER		NOT NULL,
	"BOARD_TITLE"	VARCHAR2(150)		NOT NULL,
	"BOARD_CONTENT"	VARCHAR2(4000)		NOT NULL,
	"B_CREATE_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"B_UPDATE_DATE"	DATE		NULL,
	"READ_COUNT"	NUMBER	DEFAULT 0	NOT NULL,
	"BOARD_DEL_FL"	CHAR(1)	DEFAULT 'N'	NOT NULL,
	"MEMBER_NO"	NUMBER		NOT NULL,
	"BOARD_CODE"	NUMBER		NOT NULL
);
COMMENT ON COLUMN "BOARD"."BOARD_NO" IS '게시글 번호(SEQ_BOARD_NO)';
COMMENT ON COLUMN "BOARD"."BOARD_TITLE" IS '게시글 제목';
COMMENT ON COLUMN "BOARD"."BOARD_CONTENT" IS '게시글 내용';
COMMENT ON COLUMN "BOARD"."B_CREATE_DATE" IS '게시글 작성일';
COMMENT ON COLUMN "BOARD"."B_UPDATE_DATE" IS '마지막 수정일(수정 시 UPDATE)';
COMMENT ON COLUMN "BOARD"."READ_COUNT" IS '조회수';
COMMENT ON COLUMN "BOARD"."BOARD_DEL_FL" IS '삭제 여부(N : 삭제X , Y : 삭제O)';
COMMENT ON COLUMN "BOARD"."MEMBER_NO" IS '작성자 회원 번호';
COMMENT ON COLUMN "BOARD"."BOARD_CODE" IS '게시판 코드 번호';
----------------------------------------------------------------------
CREATE TABLE "BOARD_IMG" (
	"IMG_NO"	NUMBER		NOT NULL,
	"IMG_PATH"	VARCHAR2(300)		NOT NULL,
	"IMG_RENAME"	VARCHAR2(30)		NOT NULL,
	"IMG_ORIGINAL"	VARCHAR2(300)		NOT NULL,
	"IMG_ORDER"	NUMBER		NOT NULL,
	"BOARD_NO"	NUMBER		NOT NULL
);
COMMENT ON COLUMN "BOARD_IMG"."IMG_NO" IS '이미지 번호(SEQ_IMG_NO)';
COMMENT ON COLUMN "BOARD_IMG"."IMG_PATH" IS '이미지 저장 폴더 경로';
COMMENT ON COLUMN "BOARD_IMG"."IMG_RENAME" IS '변경된 이미지 파일 이름';
COMMENT ON COLUMN "BOARD_IMG"."IMG_ORIGINAL" IS '원본 이미지 파일 이름';
COMMENT ON COLUMN "BOARD_IMG"."IMG_ORDER" IS '이미지 파일 순서 번호';
COMMENT ON COLUMN "BOARD_IMG"."BOARD_NO" IS '이미지가 첨부된 게시글 번호';
----------------------------------------------------------------------
CREATE TABLE "BOARD_LIKE" (
	"BOARD_NO"	NUMBER		NOT NULL,
	"MEMBER_NO"	NUMBER		NOT NULL
);
COMMENT ON COLUMN "BOARD_LIKE"."BOARD_NO" IS '게시글 번호';
COMMENT ON COLUMN "BOARD_LIKE"."MEMBER_NO" IS '좋아요 누른 회원 번호';
----------------------------------------------------------------------
CREATE TABLE "COMMENT" (
	"COMMENT_NO"	NUMBER		NOT NULL,
	"COMMENT_CONTENT"	VARCHAR2(4000)		NOT NULL,
	"C_CREATE_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"COMMENT_DEL_FL"	CHAR(1)	DEFAULT 'N'	NOT NULL,
	"BOARD_NO"	NUMBER		NOT NULL,
	"MEMBER_NO"	NUMBER		NOT NULL,
	"PARENT_NO"	NUMBER	
);
COMMENT ON COLUMN "COMMENT"."COMMENT_NO" IS '댓글 번호(SEQ_COMMENT_NO)';
COMMENT ON COLUMN "COMMENT"."COMMENT_CONTENT" IS '댓글 내용';
COMMENT ON COLUMN "COMMENT"."C_CREATE_DATE" IS '댓글 작성일';
COMMENT ON COLUMN "COMMENT"."COMMENT_DEL_FL" IS '댓글 삭제 여부(N : 삭제X, Y : 삭제O)';
COMMENT ON COLUMN "COMMENT"."BOARD_NO" IS '댓글이 작성된 게시글 번호';
COMMENT ON COLUMN "COMMENT"."MEMBER_NO" IS '댓글 작성자 회원 번호';
COMMENT ON COLUMN "COMMENT"."PARENT_NO" IS '부모 댓글 번호';
----------------------------------------------------------------------
ALTER TABLE "BOARD" ADD CONSTRAINT "PK_BOARD" PRIMARY KEY (
	"BOARD_NO"
);
ALTER TABLE "BOARD_IMG" ADD CONSTRAINT "PK_BOARD_IMG" PRIMARY KEY (
	"IMG_NO"
);
ALTER TABLE "BOARD_LIKE" ADD CONSTRAINT "PK_BOARD_LIKE" PRIMARY KEY (
	"BOARD_NO",
	"MEMBER_NO"
);
ALTER TABLE "COMMENT" ADD CONSTRAINT "PK_COMMENT" PRIMARY KEY (
	"COMMENT_NO"
);
ALTER TABLE "BOARD" ADD CONSTRAINT "FK_MEMBER_TO_BOARD_1" FOREIGN KEY (
	"MEMBER_NO"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
);
ALTER TABLE "BOARD" ADD CONSTRAINT "FK_BOARD_TYPE_TO_BOARD_1" FOREIGN KEY (
	"BOARD_CODE"
)
REFERENCES "BOARD_TYPE" (
	"BOARD_CODE"
);
ALTER TABLE "BOARD_IMG" ADD CONSTRAINT "FK_BOARD_TO_BOARD_IMG_1" FOREIGN KEY (
	"BOARD_NO"
)
REFERENCES "BOARD" (
	"BOARD_NO"
);
ALTER TABLE "BOARD_LIKE" ADD CONSTRAINT "FK_BOARD_TO_BOARD_LIKE_1" FOREIGN KEY (
	"BOARD_NO"
)
REFERENCES "BOARD" (
	"BOARD_NO"
);
ALTER TABLE "BOARD_LIKE" ADD CONSTRAINT "FK_MEMBER_TO_BOARD_LIKE_1" FOREIGN KEY (
	"MEMBER_NO"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
);
ALTER TABLE "COMMENT" ADD CONSTRAINT "FK_BOARD_TO_COMMENT_1" FOREIGN KEY (
	"BOARD_NO"
)
REFERENCES "BOARD" (
	"BOARD_NO"
);
ALTER TABLE "COMMENT" ADD CONSTRAINT "FK_MEMBER_TO_COMMENT_1" FOREIGN KEY (
	"MEMBER_NO"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
);
ALTER TABLE "COMMENT" ADD CONSTRAINT "FK_COMMENT_TO_COMMENT_1" FOREIGN KEY (
	"PARENT_NO"
)
REFERENCES "COMMENT" (
	"COMMENT_NO"
);
-- 시퀀스 생성
CREATE SEQUENCE SEQ_BOARD_NO NOCACHE; -- 게시글 번호
CREATE SEQUENCE SEQ_IMG_NO NOCACHE; -- 게시글 이미지 번호
CREATE SEQUENCE SEQ_COMMENT_NO NOCACHE; -- 댓글 번호
-- BOARD 테이블 샘플 데이터 삽입(PL/SQL)
BEGIN
FOR I IN 1..2000 LOOP
INSERT INTO BOARD
VALUES( SEQ_BOARD_NO.NEXTVAL,
SEQ_BOARD_NO.CURRVAL || '번째 게시글',
SEQ_BOARD_NO.CURRVAL || '번째 게시글 내용 입니다.',
DEFAULT, DEFAULT, DEFAULT, DEFAULT, 1,
CEIL(DBMS_RANDOM.VALUE(0,4))
);
END LOOP;
END;
COMMIT;


------------------------------------------------------
-- COMMENT 테이블 샘플 데이터 삽입(PL/SQL)
BEGIN
FOR I IN 1..1000 LOOP
	 INSERT INTO "COMMENT"
		VALUES(SEQ_COMMENT_NO.NEXTVAL,
				SEQ_COMMENT_NO.CURRVAL || '번째 댓글',
				DEFAULT, DEFAULT,
				 CEIL(DBMS_RANDOM.VALUE(0,2000)),
				 4, NULL);
END LOOP;
END;
COMMIT;


-- 게시글 샘플 이미지
INSERT INTO BOARD_IMG
VALUES(SEQ_IMG_NO.NEXTVAL, '/resources/images/board/',
		'20230508115013_00001.jpg', 'cat1.jpg', 0, 1996);
	
INSERT INTO BOARD_IMG
VALUES(SEQ_IMG_NO.NEXTVAL, '/resources/images/board/',
		'20230508115013_00002.jpg', 'cat2.jpg', 0, 1995);
	
INSERT INTO BOARD_IMG
VALUES(SEQ_IMG_NO.NEXTVAL, '/resources/images/board/',
		'20230508115013_00003.jpg', 'cat3.jpg', 0, 1994);
	
INSERT INTO BOARD_IMG
VALUES(SEQ_IMG_NO.NEXTVAL, '/resources/images/board/',
		'20230508115013_00004.jpg', 'cat4.jpg', 0, 1993);
	
INSERT INTO BOARD_IMG
VALUES(SEQ_IMG_NO.NEXTVAL, '/resources/images/board/',
		'20230508115013_00005.jpg', 'cat5.jpg', 0, 1989);
COMMIT;


DROP TABLE BOARD_IMG CASCADE CONSTRAINTS; 





SELECT * FROM board;


SELECT COUNT(*) FROM BOARD
		WHERE BOARD_DEL_FL ='N'
		
		AND BOARD_CODE = 3;








-- 게시글 상세 조회
SELECT BOARD_NO, BOARD_TITLE, BOARD_CONTENT, BOARD_CODE,
	READ_COUNT, MEMBER_NICKNAME, MEMBER_NO, PROFILE_IMG,
	TO_CHAR(B_CREATE_DATE, 'YYYY"년" MM"월" DD"일" HH24:MI:SS') B_CREATE_DATE,
	TO_CHAR(B_UPDATE_DATE, 'YYYY"년" MM"월" DD"일" HH24:MI:SS') B_UPDATE_DATE,
	(SELECT COUNT(*)
	FROM "BOARD_LIKE" L
	WHERE L.BOARD_NO = B.BOARD_NO) LIKE_COUNT
FROM "BOARD" B
JOIN "MEMBER" USING(MEMBER_NO)
WHERE BOARD_DEL_FL = 'N'
AND BOARD_CODE = 1
AND BOARD_NO = 1998;


-- 게시글 좋아용 샘플 데이터 삽입
INSERT INTO "BOARD_LIKE" VALUES(1998, 2);
INSERT INTO "BOARD_LIKE" VALUES(1998, 3);
COMMIT;





-- 특정 게시글에 대한 이미지 조회 
SELECT * FROM BOARD_IMG
WHERE BOARD_NO = 1998
ORDER BY IMG_ORDER; 

INSERT INTO BOARD_IMG
VALUES(SEQ_IMG_NO.NEXTVAL,
	'/resources/images/board/',
	'20230508115013_00001.jpg',
	'cat1.jpg',
	1,
	1998
);



INSERT INTO BOARD_IMG
VALUES(SEQ_IMG_NO.NEXTVAL,
	'/resources/images/board/',
	'20230508115013_00003.jpg',
	'cat3.jpg',
	2,
	1998
);

COMMIT;




-- 특정 게시글에 대한 댓글 목록 조회
SELECT COMMENT_NO, COMMENT_CONTENT,
TO_CHAR(C_CREATE_DATE, 'YYYY"년" MM"월" DD"일" HH24"시" MI"분" SS"초"') C_CREATE_DATE,
BOARD_NO, MEMBER_NO, MEMBER_NICKNAME, PROFILE_IMG, PARENT_NO, COMMENT_DEL_FL
FROM "COMMENT"
JOIN MEMBER USING(MEMBER_NO)
WHERE BOARD_NO = 1900
ORDER BY COMMENT_NO;





























-- 댓글 목록 조회
SELECT COMMENT_NO, COMMENT_CONTENT,
TO_CHAR(C_CREATE_DATE, 'YYYY"년" MM"월" DD"일" HH24"시" MI"분" SS"초"') C_CREATE_DATE,
BOARD_NO, MEMBER_NO, MEMBER_NICKNAME, PROFILE_IMG, PARENT_NO, COMMENT_DEL_FL
FROM "COMMENT"
JOIN MEMBER USING(MEMBER_NO)
WHERE BOARD_NO = 2002
ORDER BY COMMENT_NO
;


SELECT * FROM "BOARD"
ORDER BY BOARD_NO DESC;




INSERT INTO "COMMENT"									-- 게시글, 회원
VALUES(SEQ_COMMENT_NO.NEXTVAL, '부모 댓글 1', DEFAULT, DEFAULT, 2002, 1, NULL);
INSERT INTO "COMMENT"									-- 게시글, 회원
VALUES(SEQ_COMMENT_NO.NEXTVAL, '부모 댓글 2', DEFAULT, DEFAULT, 2002, 1, NULL);


--  만약 INSERT 한 댓글  넘버가 1002 ,1003이라면 


INSERT INTO "COMMENT"									-- 게시글, 회원
VALUES(SEQ_COMMENT_NO.NEXTVAL, '자식 댓글 1-1', DEFAULT, DEFAULT, 2002, 2, 2);
INSERT INTO "COMMENT"									-- 게시글, 회원
VALUES(SEQ_COMMENT_NO.NEXTVAL, '자식 댓글 1-2', DEFAULT, DEFAULT, 2002, 2, 2);
INSERT INTO "COMMENT"									-- 게시글, 회원
VALUES(SEQ_COMMENT_NO.NEXTVAL, '자식 댓글 1-3', DEFAULT, DEFAULT, 2002, 2, 2);
INSERT INTO "COMMENT"									-- 게시글, 회원
VALUES(SEQ_COMMENT_NO.NEXTVAL, '자식 댓글 2-1', DEFAULT, DEFAULT, 2002, 2, 3);
INSERT INTO "COMMENT"									-- 게시글, 회원
VALUES(SEQ_COMMENT_NO.NEXTVAL, '자식 댓글 2-2', DEFAULT, DEFAULT, 2002, 2, 3);
INSERT INTO "COMMENT"									-- 게시글, 회원
VALUES(SEQ_COMMENT_NO.NEXTVAL, '자식의 자식 댓글 2-1-1', DEFAULT, DEFAULT, 2002, 2, 7);
COMMIT;




/* 계층형 쿼리(START WITH, CONNECT BY, ORDER SIBLINGS BY)
- 상위 타입과 하위 타입간의 관계를 계층식으로 표현할 수 있게하는 질의어(SELECT)
- START WITH : 상위 타입(최상위 부모)으로 사용될 행을 지정 (서브쿼리로 지정 가능)
- CONNECT BY
-> 상위 타입과 하위 타입 사이의 관계를 규정
-> PRIOR(이전의) 연산자와 같이 사용하여
현재 행 이전에 상위 타입 또는 하위 타입이 있을지 규정
1) 부모 -> 자식 계층 구조
CONNECT BY PRIOR 자식 컬럼 = 부모 컬럼
2) 자식 -> 부모 계층 구조
CONNECT BY PRIOR 부모 컬럼 = 자식 컬럼
- ORDER SIBLINGS BY : 계층 구조 정렬
** 계층형 쿼리가 적용 SELECT 해석 순서 **
5 : SELECT
1 : FROM (+JOIN)
4 : WHERE
2 : START WITH
3 : CONNECT BY
6 : ORDER SIBLINGS BY
- WHERE절이 계층형 쿼리보다 해석 순서가 늦기 때문에
먼저 조건을 반영하고 싶은 경우 FROM절 서브쿼리(인라인뷰)를 이용
* */

-- 댓글 목록 조회(계층형 쿼리 적용)
SELECT LEVEL,C.*FROM
	(SELECT COMMENT_NO, COMMENT_CONTENT,
	TO_CHAR(C_CREATE_DATE, 'YYYY"년" MM"월" DD"일" HH24"시" MI"분" SS"초"') C_CREATE_DATE,
	BOARD_NO, MEMBER_NO, MEMBER_NICKNAME, PROFILE_IMG, PARENT_NO, COMMENT_DEL_FL
	FROM "COMMENT"
	JOIN MEMBER USING(MEMBER_NO)
	WHERE BOARD_NO = 2002) C
WHERE COMMENT_DEL_FL = 'N'
START WITH PARENT_NO IS NULL
CONNECT BY PRIOR COMMENT_NO = PARENT_NO
ORDER SIBLINGS BY COMMENT_NO
;

SELECT * FROM BOARD WHERE BOARD_NO ='1'; 

