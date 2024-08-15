package hello.member;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {
    private final JdbcTemplate template;

    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }

    public void initTable() {
        template.execute("CREATE TABLE MEMBER (MEMBER_ID VARCHAR(255) primary key, NAME VARCHAR(255))");
    }

    public void save(Member member) {
        template.update("INSERT INTO MEMBER (MEMBER_ID, NAME) VALUES (?, ?)", member.getMemberId(), member.getName());
    }

    public Member find(String memberId) {
        return template.queryForObject("SELECT MEMBER_ID, NAME FROM MEMBER WHERE MEMBER_ID = ?",
                BeanPropertyRowMapper.newInstance(Member.class), memberId);
    }

    public List<Member> findAll() {
        return template.query("SELECT MEMBER_ID, NAME FROM MEMBER", BeanPropertyRowMapper.newInstance(Member.class));
    }
}
