select * from sungjuk
;


--�������� 60�̻� ��ȸ
select * from SUNGJUK
where kor>=60
;

select * from tb_student
;

--���߰�
INSERT INTO sungjuk(sno, uname, kor, eng, mat, addr, wdate)
VALUES(sungjuk_seq.nextval, '������', 99, 33, 22, 'Suwon', sysdate)
;
