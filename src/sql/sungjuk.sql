select * from sungjuk
;


--국어점수 60이상 조회
select * from SUNGJUK
where kor>=60
;

select * from tb_student
;

--행추가
INSERT INTO sungjuk(sno, uname, kor, eng, mat, addr, wdate)
VALUES(sungjuk_seq.nextval, '박지성', 99, 33, 22, 'Suwon', sysdate)
;
