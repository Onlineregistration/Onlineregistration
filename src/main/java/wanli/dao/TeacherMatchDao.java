package wanli.dao;

import org.apache.ibatis.annotations.Param;
import wanli.pojo.TeacherMatch;

public interface TeacherMatchDao {
    Integer deleteTeacherMatch(Integer matchId);

    Integer insertTeacherMatch(@Param("teacherId") String teacherId,
                               @Param("matchId") Integer matchId);

    TeacherMatch selectTeacherMatch(@Param("matchId") Integer matchId,
                                    @Param("teacherId")String teacherId);
}
