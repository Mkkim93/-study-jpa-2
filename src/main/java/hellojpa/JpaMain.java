package hellojpa;

import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {

        // persistenceUnitName 을 넘긴다 = "hello" (pom.xml), EntityManagerFactory 를 생성 시 DB 와 DATA 연동
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 1. 트랜잭션 생성
        tx.begin(); // begin() : 2. 트랜잭션 시작

        try {
            Member findMember = em.find(Member.class, 2L); // 찾을 id 번호 입력
            System.out.println("findMember.getId() = " + findMember.getId()); // id 조회
            System.out.println("findMember.getName() = " + findMember.getName()); // Name 조회
            tx.commit(); // 5. commit() : data 저장, 트랜잭션 종료
        } catch (Exception e) {
            tx.rollback(); // 문제 발생 시 rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
