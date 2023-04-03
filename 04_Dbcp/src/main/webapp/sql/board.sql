-- 시퀀스
DROP SEQUENCE BOARD_SEQ;
CREATE SEQUENCE BOARD_SEQ NOCACHE;

-- 테이블
DROP TABLE BOARD;
CREATE TABLE BOARD (
	BOARD_NO 		NUMBER NOT NULL,
	TITLE 			VARCHAR2(1000BYTE) NOT NULL,
	CONTENT 		CLOB, -- 데이터가 넘칠거 같으면 사이즈 지정안하고 뒤에 CLOB이라고 적으면 된다
	MODIFIED_DATE 	DATE,
	CREATED_DATE 	DATE NOT NULL, 
	CONSTRAINT PK_BOARD PRIMARY KEY(BOARD_NO)
);

-- 행 
INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, '[공지]월요일알림', 'DBCP는 DataBase Connection Pool을 의미한다.', NULL, SYSDATE); -- 시퀀스를 이용한 하나씩 증가하는 기능 EX) 번호표
INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, '[협조]내일준비물', '물감, 리코더, 공책 가져오기', NULL, SYSDATE); -- 시퀀스를 이용한 하나씩 증가하는 기능 EX) 번호표
-- 뒤에 마침표시(;)는 하면 오류가 나므로 (생략), COMMIT은 굳이 안해줘도 된다 알아서 이클립스에서 AUTOCOMMIT을 해준다. 