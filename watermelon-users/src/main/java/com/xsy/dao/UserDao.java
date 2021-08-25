package com.xsy.dao;
import com.xsy.entity.user.Favorite;
import com.xsy.entity.user.Played;
import com.xsy.entity.user.User;
import com.xsy.entity.video.Comment;
import com.xsy.entity.video.CommentVO;
import com.xsy.entity.video.VideoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户(User)表数据库访问层
 *
 * @author makejava
 * @since 2021-08-06 16:37:09
 */
@Mapper
public interface UserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    List<User> queryAll(User user);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<User> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<User> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<User> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<User> entities);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    User queryByPhone(String phone);

    void insertFavorite(Favorite favorite);
    Favorite queryFavorite(Integer uid,Integer video_id);

    void deleteFavorite(Integer id, Integer video_id);


    List<VideoVO> queryPagePlayed(int start, Integer pageSize, Integer id);

    List<VideoVO> queryPageFavorite(int start, Integer pageSize, Integer id);

    int queryFavoriteCount(Integer id);
    int queryPlayedCount(Integer id);

    List<CommentVO> queryComments(Integer video_id,Integer start,Integer pageSize);

    int queryCommentsCount(Integer video_id);


    void insertComments(Comment comment);
}

